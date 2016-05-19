package com.example.dictionary;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

public class Information extends AppCompatActivity {
    public static final String TAG = "myLogs";
    DataBaseHelper mDataBaseHelper;
    SQLiteDatabase db;
    Cursor userCursor;
    TextView mTextView;
    String recipe, recipe_1, recipe_2;
    int ID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        //Log.d(TAG, "Найдем View элементы");
        mTextView = (TextView) findViewById(R.id.textView);


        mDataBaseHelper = new DataBaseHelper(getApplicationContext());

        db = mDataBaseHelper.getReadableDatabase();
        String[] columns = new String[]{DataBaseHelper.DISH_NAME_RECIPE_COLUMN, DataBaseHelper.ID_COLUMN,
                DataBaseHelper.DISH_NAME_RECIPE_COLUMN_1,DataBaseHelper.DISH_NAME_RECIPE_COLUMN_2};
        userCursor = db.query("Dish", columns, null, null, null, null, null); //чтение таблицы
        //Log.d(TAG, "запрос из БД");

        Intent intent = getIntent();
        int pos = intent.getIntExtra("key", 0);

        userCursor.moveToPosition(pos);//перемещает курсор на указанную позицию
            //получаем порядковые номера столбцов по их номерам
         int idIndex = userCursor.getColumnIndex(DataBaseHelper.ID_COLUMN);
         int recipeIndex = userCursor.getColumnIndex(DataBaseHelper.DISH_NAME_RECIPE_COLUMN);
         int recipeIndex_1 = userCursor.getColumnIndex(DataBaseHelper.DISH_NAME_RECIPE_COLUMN_1);
         int recipeIndex_2 = userCursor.getColumnIndex(DataBaseHelper.DISH_NAME_RECIPE_COLUMN_2);

         //чтение данных
         ID = userCursor.getInt(idIndex);
         recipe = userCursor.getString(recipeIndex);
         recipe_1 = userCursor.getString(recipeIndex_1);
         recipe_2 = userCursor.getString(recipeIndex_2);


        //recipe = userCursor.getString(userCursor.getColumnIndex(DataBaseHelper.DISH_NAME_RECIPE_COLUMN));
        //Log.d(TAG, "ID = " + ID + " " + "recipe = " + recipe);

        int pos_1 = intent.getIntExtra("key_1", 0);
        Log.d(TAG, "pos_1 = " + pos_1);

        if (pos_1 == 0 & (pos +1) == ID){
            mTextView.setText(recipe);
        } else if(pos_1==1 & (pos +1) == ID){
            mTextView.setText(recipe_1);
        } else if(pos_1==2 & (pos +1) == ID) {
            mTextView.setText(recipe_2);
        }else{
            mTextView.setText("хаха");
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        userCursor.close();
        db.close();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


}

