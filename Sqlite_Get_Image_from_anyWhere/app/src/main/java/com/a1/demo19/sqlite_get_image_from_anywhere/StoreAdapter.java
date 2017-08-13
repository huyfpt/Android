package com.a1.demo19.sqlite_get_image_from_anywhere;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by duchuy on 12/5/2016.
 */
public class StoreAdapter extends ArrayAdapter<Store> {
    public StoreAdapter(Context context, int customer_view, ArrayList<Store> stores) {
        super(context, 0, stores);
}
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.customer_view, parent, false);
        }
        TextView txtName = (TextView) convertView.findViewById(R.id.textViewListView);
        TextView txtPrice = (TextView) convertView.findViewById(R.id.textViewListView2);
        ImageView imgView = (ImageView) convertView.findViewById(R.id.imageViewListView);
        Store store = getItem(position);
        Bitmap bitmap = BitmapFactory.decodeByteArray(store.image, 0, store.image.length);
        txtName.setText(store.name);
        txtPrice.setText(store.price + "");
        imgView.setImageBitmap(bitmap);
        return convertView;
    }
}