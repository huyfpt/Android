package com.demo12.listview_student;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by duchuy on 10/26/2016.
 */
public class StudentAdapter extends ArrayAdapter<Student> {
    public StudentAdapter(Context context, int student_view, ArrayList<Student> students) {
        super(context, 0, students);
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.student_view, parent, false);
        }
        TextView txtName = (TextView) convertView.findViewById(R.id.textViewName);
        TextView txtAddress = (TextView) convertView.findViewById(R.id.textViewAddress);
        TextView txtAge = (TextView) convertView.findViewById(R.id.textViewAge);
        ImageView imgImage = (ImageView) convertView.findViewById(R.id.imageViewImage);
        Student student = getItem(position);
        txtName.setText(student.name);
        txtAddress.setText(student.address);
        txtAge.setText(student.age+"");
        imgImage.setImageResource(student.image);
        return convertView;
    }
}
