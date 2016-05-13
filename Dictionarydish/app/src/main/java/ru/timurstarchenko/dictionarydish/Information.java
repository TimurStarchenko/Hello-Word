package ru.timurstarchenko.dictionarydish;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.webkit.WebView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Information extends AppCompatActivity {
    public static final String TAG = "myLogs";
    DataBaseHelper mDataBaseHelper;
    SQLiteDatabase db;
    Cursor userCursor;
    TextView mTextView;
    String recipe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        Log.d(TAG, "Найдем View элементы");
        mTextView = (TextView) findViewById(R.id.textView);

        mDataBaseHelper = new DataBaseHelper(getApplicationContext());

        db = mDataBaseHelper.getReadableDatabase();
        userCursor = db.query("Dish", new String[]{DataBaseHelper.DISH_NAME_RECIPE_COLUMN}, null, null, null, null, null);

        while (userCursor.moveToNext()) {
            //int id = userCursor.getInt(userCursor.getColumnIndex(DataBaseHelper.ID_COLUMS));
            recipe = userCursor.getString(userCursor.getColumnIndex(DataBaseHelper.DISH_NAME_RECIPE_COLUMN));
            Log.d(TAG, "recipe" + recipe);

            Intent intent = getIntent();
            String resName = recipe + intent.getIntExtra("head", 0);
            mTextView.setText(resName);
        }

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