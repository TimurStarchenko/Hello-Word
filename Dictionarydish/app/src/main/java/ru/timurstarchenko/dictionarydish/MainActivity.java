package ru.timurstarchenko.dictionarydish;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {
    private String[] main_dishes = {
            "Лазанья",
            "Плов",
            "Баранина с белыми грибами и помидорами",
            "Ростбиф",
            "Куринное филе запеченые с опятыми",
            "Ленивые вареники",
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listView = (ListView)findViewById(R.id.listView);
        listView.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, main_dishes));

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> a, View v, int position, long id) {
                Intent intent = new Intent(MainActivity.this, Information.class);
                intent.putExtra("head", position);
                startActivity(intent);
            }
        });
    }
}
