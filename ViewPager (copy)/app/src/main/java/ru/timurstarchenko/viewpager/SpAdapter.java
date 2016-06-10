package ru.timurstarchenko.viewpager;

import android.content.Context;
import android.util.Log;
import android.widget.SimpleAdapter;

import java.util.List;
import java.util.Map;

public class SpAdapter extends SimpleAdapter {
    public SpAdapter(Context context, List<? extends Map<String, ?>> data, int resource, String[] from, int[] to) {
        super(context, data, resource, from, to);
    }
    @Override
    public boolean isEnabled(int position) {
        return false;
    }
}
