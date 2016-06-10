package ru.timurstarchenko.viewpager;

import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ListDialog extends DialogFragment {

    public View OnCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        getDialog().setTitle("Title");
        View v = inflater.inflate(R.layout.for_list_dialog,null);
        v.findViewById(R.id.imagePin);
        v.findViewById(R.id.text);
        return v;
    }

    public Void onClick(View v){

    }
}
