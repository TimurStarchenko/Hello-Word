package ru.timurstarchenko.viewpager;


import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.Map;

public class selectDialog extends DialogFragment{
    final String ATTRIBUTE_NAME_TEXT = "text";
    String[] value = new String[]{"one", "two", "three", "four", "five", "six","seven", "eight", "nine", "ten"};
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        ArrayList<Map<String,Object>> data = new ArrayList<Map<String,Object>>(value.length);
        Map<String,Object> m;

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setItems(value, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        minus callingActivity = (minus) getActivity();
                        callingActivity.onUserSelectValue(value[which]);
                        dialog.dismiss();

                    }
                });

        return builder.create();
    }
}
