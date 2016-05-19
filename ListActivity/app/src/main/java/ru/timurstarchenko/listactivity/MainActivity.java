package ru.timurstarchenko.listactivity;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends ListActivity implements AdapterView.OnItemLongClickListener {
    public final String[] catNamesArray = {"Рыжик","Барсик","Мурзик",
            "Мурка", "Васька", "Томасина", "Бобик", "Кристина", "Пушок",
            "Дымка", "Кузя", "Китти", "Барбос", "Масяня", "Симба"};

    private ArrayAdapter<String> mArrayAdapter;
    private ArrayList<String> catNamesList = new ArrayList<>(Arrays.asList(catNamesArray)); // создали переменную что бы можно было изменять массив.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        mArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,catNamesList);
        setListAdapter(mArrayAdapter);
        getListView().setOnItemLongClickListener(this);
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        Toast.makeText(getApplicationContext(),"Вы выбрали " + l.getItemAtPosition(position),Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long l) {
        String selectedItem = parent.getItemAtPosition(position).toString();


        mArrayAdapter.remove(selectedItem);
        mArrayAdapter.notifyDataSetChanged();

        Toast.makeText(getApplicationContext(),selectedItem + " удален", Toast.LENGTH_SHORT).show();
        return true;
    }

}
