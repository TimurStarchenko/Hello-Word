package ru.timurstarchenko.tost;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void showToast(View view) {
        Toast toast = Toast.makeText(getApplicationContext(),R.string.catfood,Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER,0,0);
        LinearLayout toastConteiner = (LinearLayout)toast.getView();
        ImageView catImage = new ImageView(getApplicationContext());
        catImage.setImageResource(R.drawable.whiskers_sam);
        toastConteiner.addView(catImage,0);
        toast.show();
    }
}
