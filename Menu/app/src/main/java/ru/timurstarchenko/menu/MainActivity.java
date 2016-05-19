package ru.timurstarchenko.menu;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        TextView text = (TextView)findViewById(R.id.textView);

        int id = item.getItemId();

        switch (id) {
            case R.id.action_cat1:
                text.setText("Вы выбрали кота!");
                return true;

            case R.id.action_cat2:
                text.setText("Вы выбрали кошку!");
                return true;

            case R.id.action_cat3:
                text.setText("Вы выбрали котенка!");
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }


    public void ClickSettings(MenuItem item) {
        TextView text = (TextView)findViewById(R.id.textView);
        text.setText("Вы выбрали пункт Settings.");
    }
}
