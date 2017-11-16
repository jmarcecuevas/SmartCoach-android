package com.luckycode.smartcoach.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.luckycode.smartcoach.R;

/**
 * Created by marcelocuevas on 11/11/17.
 */

public class SpinnerAdapter extends BaseAdapter {
    private String[] linesUp;
    private LayoutInflater inflter;

    public SpinnerAdapter(Context context,String[] linesUp) {
        this.linesUp = linesUp;
        inflter = (LayoutInflater.from(context));
    }

    @Override
    public int getCount() {
        return linesUp.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflter.inflate(R.layout.custom_spinner_item, null);
        TextView lineUp = (TextView) view.findViewById(R.id.textView);
        lineUp.setText(linesUp[i]);
        return view;
    }
}