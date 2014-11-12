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
public class favListAdapter extends ArrayAdapter<String> {
    private final Context context;
    private final String[] titles;
    private final String[] descs;

    public favListAdapter(Context context, String[] titles, String[] descs) {
        super(context, R.layout.titledesc_list_item, titles);
        this.context = context;
        this.titles = titles;
        this.descs = descs;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.titledesc_list_item, parent, false);
        TextView textView = (TextView) rowView.findViewById(R.id.favTitle);
        textView.setText(titles[position]);
        textView = (TextView) rowView.findViewById(R.id.favDesc);
        textView.setText(descs[position]);

        return rowView;
    }

}
