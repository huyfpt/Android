package com.demo12.listview_student;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView lvlParent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView listView = (ListView) findViewById(R.id.listView);
        ArrayList<Student> students = new ArrayList<Student>();
        students.add(new Student("Nhut", "aaa/ass", 18, R.drawable.a1));
        students.add(new Student("Duc Huy", "aaa/ass", 20, R.drawable.b2));
        students.add(new Student("Thang", "aaa/ass", 21, R.drawable.c3));
        StudentAdapter adapter = new StudentAdapter(
                MainActivity.this,
                R.layout.student_view,
                students
        );
        listView.setAdapter(adapter);
    }
}