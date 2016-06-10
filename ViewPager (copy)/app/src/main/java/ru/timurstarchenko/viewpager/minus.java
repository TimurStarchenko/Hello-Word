package ru.timurstarchenko.viewpager;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class minus extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_minus);
        //кнопка назад
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        //кнопка выбрать расход
        ImageButton btnConsuotion = (ImageButton)findViewById(R.id.imageBtnCons);
        assert btnConsuotion != null;
        btnConsuotion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                android.app.FragmentManager fm = getFragmentManager();
                selectDialog dialog = new selectDialog();
                dialog.show(fm,"my_tag");
            }
        });
    }

    public void onUserSelectValue(String selectedValue){
        TextView consumption = (TextView)findViewById(R.id.textAddCons);
        assert consumption != null;
        consumption.setText(selectedValue);
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
}
