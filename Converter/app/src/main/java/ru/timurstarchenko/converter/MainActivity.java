package ru.timurstarchenko.converter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public float Buy(float Tenge){
        return (float) (Tenge/333);
    }

    public float Sell(float Dollar){
        return (float) (Dollar*331);
    }

    public void onClick(View view) {
        RadioButton meterRadioButton = (RadioButton)findViewById(R.id.radioButton);

        EditText inputEditText = (EditText)findViewById(R.id.editText);

        if(inputEditText.getText().length() == 0) {
            Toast.makeText(getApplicationContext(), "Введите длину кота", Toast.LENGTH_SHORT).show();
            return;
        }

        float inputValue = Float.valueOf(String.valueOf(inputEditText.getText()));

        if(meterRadioButton.isChecked()){
            inputEditText.setText(String.valueOf(Buy(inputValue)));
        } else {
            inputEditText.setText(String.valueOf(Sell(inputValue)));
        }
    }
}
