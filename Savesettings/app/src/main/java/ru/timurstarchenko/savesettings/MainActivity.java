package ru.timurstarchenko.savesettings;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private int mCountCat = 0;
    private int mCountCrow = 0;
    public TextView textViewCountCrow;
    public TextView textViewCountCat;
    public static final String APP_PREFERENCES = "mysettings";  //константа для имени файла настроек
    public static final String APP_PREFERENCES_COUNTER = "counter";//параметр который мы хотим сохранять в настройках
    public static final String APP_PREFERENCES_COUNTER_CROW = "counterCat";

    private SharedPreferences mSettings; //переменная представляющая экземпляр класса SharedPreferences

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textViewCountCrow = (TextView)findViewById(R.id.textView);
        textViewCountCat = (TextView)findViewById(R.id.textView2);

        mSettings = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE); //инициализируем переменную mSettings, передаем в указанный метод имя файла и...
    }


    public void Click1(View view) {
        textViewCountCrow = (TextView)findViewById(R.id.textView);
        textViewCountCrow.setText("Насчитали ворон: " + ++mCountCrow);
    }

    public void Click2(View view) {
        textViewCountCat = (TextView)findViewById(R.id.textView2);
        textViewCountCat.setText("Насчитали кошек: " + ++mCountCat);
    }

    @Override
    protected void onPause() {         //метод для сохранения данных
        super.onPause();
        //сохраняем данные
        SharedPreferences.Editor editor = mSettings.edit();
        editor.putInt(APP_PREFERENCES_COUNTER, mCountCat);
        editor.apply();
        editor.putInt(APP_PREFERENCES_COUNTER_CROW, mCountCrow);
        editor.apply();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(mSettings.contains(APP_PREFERENCES_COUNTER)){
            // Получаем число из настроек
            mCountCat = mSettings.getInt(APP_PREFERENCES_COUNTER,0);
            // Выводим на экран данные из настроек
            textViewCountCat.setText("Насчитали кошек: " + mCountCat);
        }
        if (mSettings.contains(APP_PREFERENCES_COUNTER_CROW)) {
            mCountCrow = mSettings.getInt(APP_PREFERENCES_COUNTER_CROW,0);
            textViewCountCrow.setText("Насчитали ворон: " + mCountCrow);
        }
    }
}
