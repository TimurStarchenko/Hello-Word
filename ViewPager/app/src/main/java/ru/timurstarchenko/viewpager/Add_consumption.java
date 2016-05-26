package ru.timurstarchenko.viewpager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

public class Add_consumption extends AppCompatActivity {
    EditText etComment, etSum, etLocation;
    String comment;
    int sum;
    String location;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_consumption);
        //кнопка назад
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        etComment = (EditText)findViewById(R.id.editComment);
        etSum = (EditText)findViewById(R.id.editSum);
        etLocation= (EditText)findViewById(R.id.editLocation);

        comment = etComment.getText().toString();
        //sum = Integer.parseInt(etSum.getText().toString());
        location = etLocation.getText().toString();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {    // обработчик кнопки «назад»
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void addInf(View view) {
        Intent intent = new Intent(this, PageFragment.class);
        intent.putExtra("comment",comment);
        setResult(RESULT_OK,intent);
        finish();
    }
}
