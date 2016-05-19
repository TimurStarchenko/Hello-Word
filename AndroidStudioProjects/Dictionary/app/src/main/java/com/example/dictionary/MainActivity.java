package com.example.dictionary;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.SimpleExpandableListAdapter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    ExpandableListView mListView;
    DataBaseHelper mDataBaseHelper;
    SQLiteDatabase db;
    Cursor userCursor;
    public static final String TAG = "myLogs";

    String[] groups = new String[] {"Первые блюда", "Вторые блюда", "Десерты"};

    // коллекция для групп
    ArrayList<Map<String, String>> groupData;

    // коллекция для элементов одной группы
    ArrayList<Map<String, String>> childDataItem;

    // общая коллекция для коллекций элементов
    ArrayList<ArrayList<Map<String, String>>> childData;
    // в итоге получится childData = ArrayList<childDataItem>

    // список аттрибутов группы или элемента
    Map<String, String> m;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDataBaseHelper = new DataBaseHelper(getApplicationContext());
        mListView = (ExpandableListView) findViewById(R.id.listView);
        db = mDataBaseHelper.getReadableDatabase();
        String[] columns = new String[]{DataBaseHelper.DISH_NAME_COLUMN,DataBaseHelper.DISH_NAME_COLUMN_1,DataBaseHelper.DISH_NAME_COLUMN_2}; //колонка названия блюд
        userCursor = db.query("Dish", columns, null, null, null, null, null); //запрос из БД
        //названия блюд берем из БД  и помещаем в массив
        String[] name = new String[userCursor.getCount()];
        String[] name_1 = new String[userCursor.getCount()];
        String[] name_2 = new String[userCursor.getCount()];
        if(userCursor.moveToFirst()) {
            int nameIndex = userCursor.getColumnIndex(DataBaseHelper.DISH_NAME_COLUMN);
            for (int i = 0; i < userCursor.getCount(); i++) {
                name[i] = userCursor.getString(nameIndex);
                //ID = userCursor.getInt(idIndex);
                userCursor.moveToNext();
                Log.d(TAG, "name " + Arrays.toString(name));
            }
        }

        if(userCursor.moveToFirst()) {
            int nameIndex_1 = userCursor.getColumnIndex(DataBaseHelper.DISH_NAME_COLUMN_1);
            for (int i = 0; i < userCursor.getCount(); i++) {
                name_1[i] = userCursor.getString(nameIndex_1);
                userCursor.moveToNext();
                Log.d(TAG, "name_1 " + Arrays.toString(name_1));
            }
        }

        if(userCursor.moveToFirst()) {
            int nameIndex_2 = userCursor.getColumnIndex(DataBaseHelper.DISH_NAME_COLUMN_2);
            for (int i = 0; i < userCursor.getCount(); i++) {
                name_2[i] = userCursor.getString(nameIndex_2);
                userCursor.moveToNext();
                Log.d(TAG, "name_2 " + Arrays.toString(name_2));
            }
        }

        // заполняем список групп из массива с названиями групп
        groupData = new ArrayList<Map<String, String>>();
        for (String group : groups) {
            // заполняем список аттрибутов для каждой группы
            m = new HashMap<String, String>();
            m.put("groupName", group); // имя компании
            groupData.add(m);
        }

        // список аттрибутов групп для чтения
        String groupFrom[] = new String[] {"groupName"};
        // список ID view-элементов, в которые будет помещены аттрибуты групп
        int groupTo[] = new int[] {android.R.id.text1};

        // создаем коллекцию для коллекций элементов
        childData = new ArrayList<ArrayList<Map<String, String>>>();

        // создаем коллекцию элементов для первой группы
        childDataItem = new ArrayList<Map<String, String>>();
        // заполняем список аттрибутов для каждого элемента
        for (String dish : name) {
            m = new HashMap<String, String>();
            m.put("dishName", dish); // название блюда
            childDataItem.add(m);
        }
        // добавляем в коллекцию коллекций
        childData.add(childDataItem);

        // создаем коллекцию элементов для второй группы
        childDataItem = new ArrayList<Map<String, String>>();
        for (String dish : name_1) {
            m = new HashMap<String, String>();
            m.put("dishName", dish);
            childDataItem.add(m);
        }
        childData.add(childDataItem);

        // создаем коллекцию элементов для третьей группы
        childDataItem = new ArrayList<Map<String, String>>();
        for (String dish : name_2) {
            m = new HashMap<String, String>();
            m.put("dishName", dish);
            childDataItem.add(m);
        }
        childData.add(childDataItem);

        // список аттрибутов элементов для чтения
        String childFrom[] = new String[] {"dishName"};
        // список ID view-элементов, в которые будет помещены аттрибуты элементов
        int childTo[] = new int[] {android.R.id.text1};

        SimpleExpandableListAdapter adapter = new SimpleExpandableListAdapter(
                this,
                groupData,
                android.R.layout.simple_expandable_list_item_1,
                groupFrom,
                groupTo,
                childData,
                R.layout.my_list_item,
                childFrom,
                childTo);

        mListView.setAdapter(adapter);
        mListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                Intent intent = new Intent(MainActivity.this, Information.class);
                Log.d(TAG,"groupPosition= "+ groupPosition + "posithion= " + childPosition + " " + "ID= "+ id);
                intent.putExtra("key", childPosition);
                intent.putExtra("key_1", groupPosition);
                startActivity(intent);
                return false;
            }
        });
        userCursor.close();
        db.close();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        userCursor.close();
        db.close();

    }

}
