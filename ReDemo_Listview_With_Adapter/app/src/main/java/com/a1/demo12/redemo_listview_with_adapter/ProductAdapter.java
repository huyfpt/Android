package com.a1.demo12.redemo_listview_with_adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * Created by duchuy on 10/26/2016.
 */
public class ProductAdapter extends ArrayAdapter<Products> {
    public ProductAdapter(Context context, int product_items_view, ArrayList<Products> productses){
        super(context ,0, productses);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Products products = getItem(position);
        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.product_items_view, parent, false);
        }
        TextView txtName = (TextView) convertView.findViewById(R.id.textViewName);
        TextView txtPlatform =(TextView) convertView.findViewById(R.id.textViewPlatform);
        TextView txtPrice = (TextView) convertView.findViewById(R.id.textViewPrice);
        ImageView imgImage = (ImageView) convertView.findViewById(R.id.imageViewImage);

        txtName.setText(products.name);
        txtPlatform.setText(products.platform);
        DecimalFormat formatPrice = new DecimalFormat("###,###,###");
        txtPrice.setText(formatPrice.format(products.price)+"vnd");
        imgImage.setImageResource(products.image);
        return  convertView;
    }
}
