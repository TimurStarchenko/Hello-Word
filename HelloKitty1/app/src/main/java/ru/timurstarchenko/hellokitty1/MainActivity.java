package ru.timurstarchenko.hellokitty1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private int mCount = 0;
    private Button mCrowsButtonCount;
    private int mCountCat;
    private TextView helloTextView;
    private TextView mInfoTextView;
    private TextView mTextViewCat;
    private static final String KEY_COUNT = "COUNT";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mInfoTextView = (TextView)findViewById(R.id.textCoundCrow);
        helloTextView = (TextView)findViewById(R.id.textView);
        mCrowsButtonCount = (Button)findViewById(R.id.buttonHelloCrowsCounter);

        mCrowsButtonCount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mInfoTextView.setText("Я насчитал " + ++mCount + " ворон");
            }
        });

        if(savedInstanceState != null){
            mCount = savedInstanceState.getInt(KEY_COUNT,0);
            mInfoTextView.setText("Я насчитал " + mCount + " ворон");
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);

        savedInstanceState.putInt(KEY_COUNT, mCount);
    }


    public void onClick(View view) {
        helloTextView = (TextView)findViewById(R.id.textView);
        helloTextView.setText("Hello Kitty");
    }

}
