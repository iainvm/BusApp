package com.example.iain.busapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.iain.busapp.R;

/**
 * Created by iain on 12/11/14.
 */
public class naviListAdapter extends ArrayAdapter<String> {
    private final Context context;
    private final String[] titles;
    private final int[] icons;

    public naviListAdapter(Context context, String[] titles, int[] icons) {
        super(context, R.layout.titledesc_list_item, titles);
        this.context = context;
        this.titles = titles;
        this.icons = icons;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.navi_list_item, parent, false);

        ImageView iconView = (ImageView) rowView.findViewById(R.id.listImage);
        iconView.setImageResource(icons[position]);
        TextView textView = (TextView) rowView.findViewById(R.id.text1);
        textView.setText(titles[position]);

        return rowView;
    }

}
