package com.example.nathanmorgenstern.contactlistapplication;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import java.util.ArrayList;

public class CustomAdapter extends ArrayAdapter<String> {
    private int layoutResourceId;
    private ArrayList<String> data;
    private View row;
    private boolean[] mItemChecked;

    private class ViewHolder {
        TextView text;
        CheckBox chBox;

    }

    public CustomAdapter(Context context, int layoutId, ArrayList<String> list) {
        super(context, layoutId, list);
        layoutResourceId = layoutId;
        data = list;
        mItemChecked = new boolean[list.size()];
    }

    @Override
    public View getView (final int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;

        LayoutInflater inflater =  LayoutInflater.from(getContext());

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.my_custom_view, parent, false);
            holder = new ViewHolder();
            holder.text = (TextView) convertView.findViewById(R.id.list_row_text);
            holder.chBox = (CheckBox) convertView.findViewById(R.id.list_row_box);
            convertView.setTag(holder);
        }
        else
            holder = (ViewHolder) convertView.getTag();

        holder.text.setText(data.get(position));
        //Important to remove previous listener before calling setChecked
        holder.chBox.setOnCheckedChangeListener(null);
        holder.chBox.setChecked(mItemChecked[position]);
        holder.chBox.setTag(position);

        holder.chBox.setOnCheckedChangeListener(
                new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        mItemChecked[position] = isChecked;
                        Log.v("Test", "Item checked " + isChecked);
                    }
                });

        return convertView;
    }
}
//Reference: http://www.howtobuildsoftware.com/index.php/how-do/nAZ/android-android-intent-listadapter-use-onclicklistener-in-custom-adapter-class