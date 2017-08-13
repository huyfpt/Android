package com.demo25.json_demo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class Listview_customer extends ArrayAdapter<cus> {
    public Listview_customer(Context context, int activy_listview_customer, ArrayList<cus> cutom) {
        super(context, 0, cutom);
}

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.activity_listview_customer, parent, false);
        }
        TextView txtText = (TextView) convertView.findViewById(R.id.textViewcus);
        TextView txtText1 = (TextView) convertView.findViewById(R.id.textView);
        TextView txtText2 = (TextView) convertView.findViewById(R.id.textView2);
        TextView txtText3 = (TextView) convertView.findViewById(R.id.textView3);
        TextView txtText4 = (TextView) convertView.findViewById(R.id.textView4);
        TextView txtText5 = (TextView) convertView.findViewById(R.id.textView5);
        cus cust = getItem(position);
        DecimalFormat formatnum = new DecimalFormat("###,###,###");
        txtText.setText("khoá học: "+cust.text+"\n");
        txtText1.setText("học phí: "+cust.text2);
        txtText2.setText("id: "+cust.text3);
        txtText3.setText("name: "+cust.text4);
        txtText4.setText("price: "+cust.text5);
        txtText5.setText("des: "+cust.text6);
        //txtInt.setText(formatnum.format)
        return convertView;
    }
}