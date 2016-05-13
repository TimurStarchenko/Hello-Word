package ru.timurstarchenko.dictionarydish;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class MainActivity extends AppCompatActivity {
    ListView mListView;
    DataBaseHelper mDataBaseHelper;
    SQLiteDatabase db;
    Cursor userCursor;
    SimpleCursorAdapter userAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mListView = (ListView)findViewById(R.id.listView);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> a, View v, int position, long id) { //position номер выбранного пункта
                Intent intent = new Intent(MainActivity.this, Information.class);
                intent.putExtra("key", "1");
                startActivity(intent);
            }
        });

        mDataBaseHelper = new DataBaseHelper(getApplicationContext());
    }

    @Override
    protected void onResume() {
        super.onResume();
        db = mDataBaseHelper.getReadableDatabase();
        userCursor = db.rawQuery("select * from "+ DataBaseHelper.DATABASE_TABLE_NAME, null);
        String[] columns = new String[]{DataBaseHelper.DISH_NAME_COLUMN}; //список столбцов
        userAdapter = new SimpleCursorAdapter(this, android.R.layout.two_line_list_item, userCursor, columns,
                new int[]{android.R.id.text1},0);
        mListView.setAdapter(userAdapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        db.close();
        userCursor.close();
    }

}
