package com.example.nathanmorgenstern.contactlistapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;
import java.util.ArrayList;

public class CustomAdapter extends ArrayAdapter<String> {
    private int layoutResourceId;
    private ArrayList<String> data;

    public CustomAdapter(Context context, int layoutId, ArrayList<String> list) {
        super(context, layoutId, list);
        layoutResourceId = layoutId;
        data = list;
    }

    @Override
    public View getView(int index, View row, ViewGroup parent) {


        LayoutInflater layoutInflater = LayoutInflater.from(getContext());
        row = layoutInflater.inflate(layoutResourceId, null);


        TextView text = (TextView) row.findViewById(R.id.list_row_text);
        text.setText(data.get(index));
        CheckBox checkBox = (CheckBox) row.findViewById(R.id.list_row_box);
        return row;
    }
}
