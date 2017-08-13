package com.demo14.demo_gridview_image;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import java.util.ArrayList;

/**
 * Created by duchuy on 10/28/2016.
 */
public class CustomerAdapter extends ArrayAdapter<Customer>  {
    public CustomerAdapter(Context context, int customer_view, ArrayList<Customer> customers){
        super(context,0,customers );
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Customer customers = getItem(position);
    if(convertView == null){
        convertView = LayoutInflater.from(getContext()).inflate(R.layout.customer_view, parent, false);
    }
        ImageView imgView = (ImageView)convertView.findViewById(R.id.imageView);
        imgView.setImageResource(Integer.parseInt(customers.image+""));
        return convertView;
    }
}
