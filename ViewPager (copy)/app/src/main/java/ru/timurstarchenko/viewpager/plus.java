package ru.timurstarchenko.viewpager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class plus extends AppCompatActivity {
    EditText etComment, etSum, etLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plus);
        //кнопка назад
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        etComment = (EditText)findViewById(R.id.editComment);
        etSum = (EditText)findViewById(R.id.editSum);
        etLocation= (EditText)findViewById(R.id.editLocation);

    }

    // обработчик кнопки «назад»
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

    public void addInfClick(View view) {
        Intent intent = new Intent();
        if(etSum.getText().length() == 0){
            Toast.makeText(getApplicationContext(), "Введите сумму", Toast.LENGTH_SHORT).show();
        }else{
            intent.putExtra("comment", etComment.getText().toString());
            intent.putExtra("sum", Integer.parseInt(etSum.getText().toString()));
            setResult(RESULT_OK,intent);
            finish();
        }
    }
}
