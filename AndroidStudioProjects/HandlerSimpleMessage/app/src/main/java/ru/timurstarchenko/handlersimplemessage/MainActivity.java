package ru.timurstarchenko.handlersimplemessage;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {
    public String TAG = "myLogs";
    final int STATUS_NONE = 0; // нет подключения
    final int STATUS_CONNECTING = 1; // подключаемся
    final int STATUS_CONNECTED = 2; // подключено

    Handler h;
    TextView tvStatus;
    ProgressBar pbConnect;
    Button btnConnect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvStatus = (TextView)findViewById(R.id.tvStatus);
        btnConnect = (Button)findViewById(R.id.btnConnect);
        pbConnect = (ProgressBar)findViewById(R.id.progressBar);

        h = new Handler(){
            public void handleMessage(android.os.Message msg){
             switch (msg.what){
                 case STATUS_NONE:
                     btnConnect.setEnabled(true);
                     tvStatus.setText("Not Connected");
                     break;
                 case STATUS_CONNECTING:
                     btnConnect.setEnabled(false);
                     pbConnect.setVisibility(View.VISIBLE);
                     tvStatus.setText("Connecting");
                     break;
                 case STATUS_CONNECTED:
                     pbConnect.setVisibility(View.GONE);
                     tvStatus.setText("Connected");
                     break;
             }
            }
        };
        h.sendEmptyMessage(STATUS_NONE);
    }

    public void onClick(View view) {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    //устанавливаем подключение
                    h.sendEmptyMessage(STATUS_CONNECTING);
                    TimeUnit.SECONDS.sleep(2);

                    // установлено
                    h.sendEmptyMessage(STATUS_CONNECTED);

                    // выполняется какая-то работа
                    TimeUnit.SECONDS.sleep(3);

                    // разрываем подключение
                    h.sendEmptyMessage(STATUS_NONE);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        t.start();
    }
}
