package com.example.iain.busapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.iain.busapp.R;

/**
 * Created by iain on 12/11/14.
 */
public class naviListAdapter extends ArrayAdapter<String> {
    private final Context context;
    private final String[] titles;

    public naviListAdapter(Context context, String[] titles) {
        super(context, R.layout.titledesc_list_item, titles);
        this.context = context;
        this.titles = titles;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.navi_list_item, parent, false);
        TextView textView = (TextView) rowView.findViewById(R.id.text1);
        textView.setText(titles[position]);

        return rowView;
    }

}
