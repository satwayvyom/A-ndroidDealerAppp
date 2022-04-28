package com.Satway.gpstracker;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class VehicleAdapter extends ArrayAdapter<String> {

    Context context;
    String[] names;
    int[] images;



    public VehicleAdapter( Context context, String[] names, int[] images)
    {
        super(context, R.layout.custom_layout,names);
        this.context = context;
        this.names = names;
        this.images = images;
    }



    @Override
    public View getDropDownView(int position,  View convertView,  ViewGroup parent) {

            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View row= inflater.inflate(R.layout.custom_layout,null);
            TextView t1 = (TextView)row.findViewById(R.id.textView8);
            ImageView il = (ImageView)row.findViewById(R.id.imageView2);
                   t1.setText(names[position]);
                   il.setImageResource(images[position]);
        return row;
    }

    @Override
    public View getView(int position,  View convertView,  ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row= inflater.inflate(R.layout.custom_layout,null);
        TextView t1 = (TextView)row.findViewById(R.id.textView8);
        ImageView il = (ImageView)row.findViewById(R.id.imageView2);
        t1.setText(names[position]);
        il.setImageResource(images[position]);
        return row;
    }
}

