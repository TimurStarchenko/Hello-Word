package ru.timurstarchenko.lights;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private RelativeLayout  mRelativeLayout;
    private TextView mInfoTextVview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRelativeLayout = (RelativeLayout)findViewById(R.id.relativeLayout);
        mInfoTextVview = (TextView)findViewById(R.id.textView);
    }

    public void onRedButtonClick(View view) {
        mInfoTextVview.setText(R.string.red);
        mRelativeLayout.setBackgroundColor(getResources().getColor(R.color.redColor));
    }

    public void onGreenButtonClick(View view) {
        mInfoTextVview.setText(R.string.green);
        mRelativeLayout.setBackgroundColor(getResources().getColor(R.color.greenColor));
    }

    public void onYellowButtonClick(View view) {
        mInfoTextVview.setText(R.string.Yellow);
        mRelativeLayout.setBackgroundColor(getResources().getColor(R.color.yellowColor));
    }
}
