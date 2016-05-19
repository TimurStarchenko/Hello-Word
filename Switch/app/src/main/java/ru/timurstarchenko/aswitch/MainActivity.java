package ru.timurstarchenko.aswitch;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(View view) {
        EditText UserEditText = (EditText)findViewById(R.id.editText);
        EditText GiftEditText = (EditText)findViewById(R.id.editText2);
        EditText WhoEditText = (EditText)findViewById(R.id.editText3);

        Intent intent = new Intent(MainActivity.this, AboutActivity.class);

        intent.putExtra("username", UserEditText.getText().toString());
        intent.putExtra("gift", GiftEditText.getText().toString());
        intent.putExtra("who", WhoEditText.getText().toString());

        startActivity(intent);
    }

}
