package ru.timurstarchenko.viewpager;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mikhaellopez.circularprogressbar.CircularProgressBar;

import at.markushi.ui.CircleButton;

public  class PageFragment extends Fragment {
    TextView text2Row_1;

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
        if(pageNumber==1){
            rootView = inflater.inflate(R.layout.fragment_main, null);
            CircularProgressBar circularProgressBar = (CircularProgressBar)rootView.findViewById(R.id.prBar);
            //circularProgressBar.setProgress(70);

            text2Row_1 = (TextView)rootView.findViewById(R.id.text2Row_1);

            int animationDuration = 2500; // 2500ms = 2,5s
            circularProgressBar.setProgressWithAnimation(70, animationDuration);


            CircleButton addContent = (CircleButton) rootView.findViewById(R.id.addContent);
            addContent.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.d(TAG, "Click");
                    Intent intent = new Intent(getActivity(), Add_consumption.class);
                    startActivityForResult(intent,1);
                }
            });

        }else if(pageNumber ==2){
            rootView = inflater.inflate(R.layout.fragment_1, null);
        }else if(pageNumber==3){
            rootView = inflater.inflate(R.layout.fragment_2, null);
        }
        return rootView;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        //super.onActivityResult(requestCode, resultCode, data);
        if(data == null) {return;}
        String comment = data.getStringExtra("comment");
        text2Row_1.setText(comment);
    }
}