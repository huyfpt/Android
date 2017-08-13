package com.ardoirdtest1.android_sqlite_addition;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by duchuy on 4/3/2017.
 */

public class IdiscoveryAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private List<Idiscovery> idiscovery_list;
    private ArrayList<Idiscovery> idiscovery_arr;
    public IdiscoveryAdapter(Context context, int layout, List<Idiscovery> idiscovery_list) {
        this.context = context;
        this.layout = layout;
        this.idiscovery_list = idiscovery_list;
        this.idiscovery_arr = new ArrayList<Idiscovery>();
        this.idiscovery_arr.addAll(idiscovery_list);
    }
    @Override
    public int getCount() {
        return idiscovery_list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }
    private class ViewHolder{
        TextView txtActivity ;
        TextView txtLocation;
        TextView txtDate  ;
        TextView txtTime ;
        TextView txtName ;
        ImageView imgView ;

    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView == null){
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(layout, null);
            holder.txtActivity = (TextView) convertView.findViewById(R.id.tActivity);
            holder.txtLocation = (TextView) convertView.findViewById(R.id.tLocation);
            holder.txtDate = (TextView) convertView.findViewById(R.id.tDate);
            holder.txtTime = (TextView) convertView.findViewById(R.id.tTime);
            holder.txtName = (TextView) convertView.findViewById(R.id.tName);
            holder.imgView = (ImageView) convertView.findViewById(R.id.imageShow);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }
        Idiscovery idis = idiscovery_list.get(position);
        Bitmap bitmap = BitmapFactory.decodeByteArray(idis.image, 0, idis.image.length);
        holder.txtActivity.setText(idis.activity);
        holder.txtLocation.setText(idis.location);
        holder.txtDate.setText(idis.date);
        holder.txtTime.setText(idis.time);
        holder.txtName.setText(idis.name);
        holder.imgView.setImageBitmap(bitmap);
        return convertView;
    }
    public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        idiscovery_list.clear();
        if (charText.length() == 0) {
            idiscovery_list.addAll(idiscovery_arr);
        } else {
            for (Idiscovery idis : idiscovery_arr) {
                if (idis.activity.toLowerCase(Locale.getDefault()).contains(charText)) {
                    idiscovery_list.add(idis);
                }
            }
        }
        notifyDataSetChanged();
    }
}
