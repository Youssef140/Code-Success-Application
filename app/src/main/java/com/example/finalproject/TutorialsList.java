package com.example.finalproject;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

//import com.java2blog.customlistviewexampleapp.R;

public class TutorialsList extends ArrayAdapter {
    private String[] toolNames;
    private Integer[] imageid;
    private Activity context;

    public TutorialsList(Activity context, String[] toolNames, Integer[] imageid) {
        super(context, R.layout.row_item, toolNames);
        this.context = context;
        this.toolNames = toolNames;
        this.imageid = imageid;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row=convertView;
        LayoutInflater inflater = context.getLayoutInflater();
        if(convertView==null)
            row = inflater.inflate(R.layout.row_item, null, true);
        TextView toolTextView = (TextView) row.findViewById(R.id.toolTextView);
        ImageView imageFlag = (ImageView) row.findViewById(R.id.imageViewFlag);

        toolTextView.setText(toolNames[position]);
        imageFlag.setImageResource(imageid[position]);
        return  row;
    }
}