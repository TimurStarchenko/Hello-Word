package ru.timurstarchenko.handler;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {
    final String TAG = "myLogs";
    Handler h;
    TextView tvInfo;
    Button btnStart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvInfo = (TextView)findViewById(R.id.textView);
        btnStart = (Button)findViewById(R.id.btnStart);
        h = new Handler(){
            public void handleMessage(android.os.Message msg){
                tvInfo.setText("Закачено файлов: " + msg.what);
                if(msg.what == 10) btnStart.setEnabled(true);
            };
        };
    }

    public void onClickStart(View view) {
        switch (view.getId()){
            case R.id.btnStart:
                btnStart.setEnabled(false);
                Thread t  = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        for(int i = 1; i<=10; i++){
                            downloadFile();
                            h.sendEmptyMessage(i);
                            Log.d(TAG, "Закачено файлов: " +i);
                        }
                    }
                });
                    t.start();
                break;
            case R.id.btnTest:
                Log.d(TAG,"test");
                break;
            default:
                break;
        }
    }

    private void downloadFile() {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
