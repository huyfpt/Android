package com.demo19.sqlitedemo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by duchuy on 11/30/2016.
 */
public class CursorAdapter extends ArrayAdapter<Student> {
    public CursorAdapter(Context context, int student_listview, ArrayList<Student> students) {
        super(context, 0, students);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.student_listview, parent, false);
        }
        TextView txtName = (TextView) convertView.findViewById(R.id.textView);
        TextView txtAge = (TextView) convertView.findViewById(R.id.textView2);
        TextView txtID = (TextView) convertView.findViewById(R.id.textView3);
        Student student = getItem(position);
        txtName.setText(student.name);
        txtAge.setText(student.age + "");
        txtID.setText(student.id + "");
        return convertView;
    }
}