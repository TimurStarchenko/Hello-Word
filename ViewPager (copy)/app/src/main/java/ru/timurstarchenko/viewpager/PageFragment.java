package ru.timurstarchenko.viewpager;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.mikhaellopez.circularprogressbar.CircularProgressBar;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import at.markushi.ui.CircleButton;

public  class PageFragment extends Fragment {

    // имена атрибутов для Map
    final String ATTRIBUTE_NAME_TEXT = "text";
    final String ATTRIBUTE_NAME_TEXT_2 = "text2";
    final String ATTRIBUTE_NAME_TEXT_PROFIT = "textProfit";
    final String ATTRIBUTE_NAME_IMAGE = "image";
    final String ATTRIBUTE_NAME_TIME = "time";
    final String ATTRIBUTE_NAME_IMAGE_PIN = "imagePin";

    TextView viewProfit;
    ImageView imageProfit;
    ListView lvSimple;
    SpAdapter sAdapter;
    ArrayList<Map<String, Object>> data1;
    Map<String, Object> m;
    View header, header_0;
    View footer,footer_1,footer_archive;
    int sum, textSum;
    String comment;

    static final String ARGUMENT_PAGE_NUMBER = "section_number";
    int pageNumber;
    final String TAG = "myLogs";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pageNumber = getArguments().getInt(ARGUMENT_PAGE_NUMBER);

    }

    static PageFragment newInstance(int sectionNumber) {
        PageFragment pageFragment = new PageFragment();
        Bundle args = new Bundle();
        args.putInt(ARGUMENT_PAGE_NUMBER, sectionNumber);
        pageFragment.setArguments(args);
        return pageFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
         View rootView = null;
        //первая страница фрагмента
        if(pageNumber==1){
            rootView = inflater.inflate(R.layout.fragment_main, null);
            //список
            lvSimple = (ListView)rootView.findViewById(R.id.listView);

            header_0 = getLayoutInflater(null).inflate(R.layout.header_0,null);
            header = getLayoutInflater(null).inflate(R.layout.header, null);
            footer = getLayoutInflater(null).inflate(R.layout.footer, null);
            footer_1 = getActivity().getLayoutInflater().inflate(R.layout.footer_1, lvSimple,false);
            footer_archive = getActivity().getLayoutInflater().inflate(R.layout.footer_archive, lvSimple,false);

            data1 = new ArrayList<Map<String, Object>>();
            // массив имен атрибутов, из которых будут читаться данные
            String[] from = { ATTRIBUTE_NAME_TEXT, ATTRIBUTE_NAME_TEXT_2,
                    ATTRIBUTE_NAME_IMAGE, ATTRIBUTE_NAME_TIME, ATTRIBUTE_NAME_IMAGE_PIN,
                    ATTRIBUTE_NAME_TEXT_PROFIT};
            // массив ID View-компонентов, в которые будут вставлять данные
            int[] to = { R.id.sum, R.id.comment, R.id.ivImg, R.id.textTime,
                    R.id.imagePin,R.id.textProfit};

            lvSimple.addHeaderView(header_0,null, false);

            // создаем адаптер
            sAdapter = new SpAdapter(getActivity(), data1, R.layout.item, from, to);
            lvSimple.setAdapter(sAdapter);

            //Прогресс бар
            CircularProgressBar circularProgressBar = (CircularProgressBar)rootView.findViewById(R.id.prBar);
            //circularProgressBar.setProgress(70);
            int animationDuration = 2500; // 2500ms = 2,5s
            circularProgressBar.setProgressWithAnimation(70, animationDuration);


            //кнопка добавить расход
            CircleButton addContent = (CircleButton) rootView.findViewById(R.id.addContent);
            addContent.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.d(TAG, "Click");
                    Intent intent = new Intent(getActivity(), plus.class);
                    startActivityForResult(intent,2);
                }
            });

            CircleButton minus = (CircleButton) rootView.findViewById(R.id.minus);
            minus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getActivity(), minus.class);
                    startActivity(intent);
                }
            });

            viewProfit = (TextView)header_0.findViewById(R.id.viewProfit);
            imageProfit =(ImageView)rootView.findViewById(R.id.imagePin);

        }//вторая страница фрагмента
        else if(pageNumber ==2){
            rootView = inflater.inflate(R.layout.fragment_1, null);
        }//третья страница фрагмента
        else if(pageNumber==3){
            rootView = inflater.inflate(R.layout.fragment_2, null);
        }
        return rootView;
    }

    //прием данных из класса plus
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if(data == null) {return;}
        //время
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm, dd MMM");
        String time = sdf.format(new Date(System.currentTimeMillis()));

        comment = data.getStringExtra("comment");
        sum = data.getIntExtra("sum",0);
        textSum += data.getIntExtra("sum",0);

        m = new HashMap<String, Object>();
        m.put(ATTRIBUTE_NAME_TEXT, sum + " KZT");
        m.put(ATTRIBUTE_NAME_TEXT_2, comment);
        m.put(ATTRIBUTE_NAME_IMAGE, R.drawable.image_income_road);
        m.put(ATTRIBUTE_NAME_TIME, time);
        m.put(ATTRIBUTE_NAME_IMAGE_PIN, R.drawable.pin_profit_2x);
        m.put(ATTRIBUTE_NAME_TEXT_PROFIT, "Доход");
        data1.add(m);
        sAdapter.notifyDataSetChanged();

        viewProfit.setText(""+textSum);


        if(lvSimple.getCount() == 2){
            lvSimple.addHeaderView(header, null ,false);
            lvSimple.addFooterView(footer,null,false);
            lvSimple.addFooterView(footer_1,null,false);
            lvSimple.addFooterView(footer_archive, null, false);
        }
         //кнопка стоп
        FloatingActionButton btnStop = (FloatingActionButton)getActivity().findViewById(R.id.btnStop);
        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(),"Stop",Toast.LENGTH_SHORT).show();
            }
        });
    }
}