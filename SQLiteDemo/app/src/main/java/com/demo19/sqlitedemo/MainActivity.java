package com.demo19.sqlitedemo;

import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    EditText edtName, edtAge;
    Button btnInsert, btnDelete, btnUpdate;
    CursorAdapter adapter = null;
    ArrayList<Student> arrayListstudents = new ArrayList<Student>();
    SQLite database;
    int idStudent = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edtName = (EditText) findViewById(R.id.editTextName);
        edtAge = (EditText) findViewById(R.id.editTextYear);
        btnInsert = (Button) findViewById(R.id.buttonInsert);
        // btnDelete = (Button) findViewById(R.id.buttonDelete);
        btnUpdate = (Button) findViewById(R.id.buttonUpdate);
        ListView listView = (ListView) findViewById(R.id.listView);
        adapter = new CursorAdapter(
                MainActivity.this,
                R.layout.student_listview,
                arrayListstudents
        );
        listView.setAdapter(adapter);

        database = new SQLite(this, "TruongHoc.sqlite", null, 1);
        // màn hình,
        // tên database nên để .sqlite, khi khởi tạo con trỏ để null,
        // versiom để 1

        // tạo bảng NẾU KO TỒN TẠI [TÊN BẢNG]
        // chuỗi sẽ ko có nhắc lệnh phải cẩn thận
        // khai báo thuộc tính (tên thuộc tính, kiểu dữ liệu, khóa chính KEY, tự động tăng dần)
        // -> sẽ cho số tự tăng để ko trùng tên thuộc tính
        // HoTen VARCHAR, NamSinh INTEGER
        database.QueryData("CREATE TABLE IF NOT EXISTS HocSinh(Id" +
                " INTEGER PRIMARY KEY AUTOINCREMENT, HoTen VARCHAR, NamSinh INTEGER)");
        // thêm dữ liệu vào bảng HocSinh
        // phải dùng dòng sau
        // VALUES(khai báo theo thứ tự như dòng code trên)
        // --> Vì tham số Id đầu tiên ko có nên để null
        // nhận họ tên , nhập năm sinh
        //bat even insert into database

        loadData();

        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name1 = edtName.getText().toString().trim();
                int age1 = Integer.parseInt(edtAge.getText().toString().trim());
                database.QueryData("Insert into HocSinh values(null,'" + name1 + "','" + age1 + "')");
                loadData();
            }

        });
        //update
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                edtName.setText(arrayListstudents.get(position).name);
                edtAge.setText(arrayListstudents.get(position).age + "");
                idStudent = arrayListstudents.get(position).id;
            }
        });
        loadData();
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (idStudent > 0) {
                    String Newname = edtName.getText().toString().trim();
                    int Newage = Integer.parseInt(edtAge.getText().toString().trim());
                    database.QueryData("UPDATE HocSinh SET HoTen ='" + Newname + "'," +
                            "NamSinh ='" + Newage + "' WHERE Id ='" + idStudent + "'");
                    Toast.makeText(MainActivity.this, "Update success", Toast.LENGTH_SHORT).show();
                    loadData();
                    idStudent = 0;
                } else {
                    Toast.makeText(MainActivity.this, "Student null", Toast.LENGTH_SHORT).show();
                }

            }
        });
        //delete
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                ConfirmRemove(arrayListstudents.get(position).name);
                return false;
            }
        });
        //search

    }


    private void loadData() {
        Cursor cursorHS = database.GetData("Select * from hocsinh");
        arrayListstudents.clear();// xáo mảng lập lại trong while
//        Select * : id = 0, hoten = 1; namsinh =2;
//        Select hoten, namsinh: hoten =0; namsinh=1
        adapter.notifyDataSetChanged();
        while (cursorHS.moveToNext()) { //moveToNext duyệt ng đầu tiên -> cuối cùng
            String name = cursorHS.getString(1);
            int id = cursorHS.getInt(0);
            int age = cursorHS.getInt(2);
            arrayListstudents.add(new Student(id, name, age));

        }

    }

    //delete
    private void ConfirmRemove(String name) {
        AlertDialog.Builder aBuilder = new AlertDialog.Builder(this);
        aBuilder.setTitle("Confirm Remove");
        aBuilder.setMessage("Do you want to delete" +"   "+ name + "?");
        aBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                database.QueryData("Delete From HocSinh where Id ='" + idStudent + "'");
                Log.d("AAAA","Delete From HocSinh where Id ='" + idStudent + "'");
                Toast.makeText(MainActivity.this, "Delete success", Toast.LENGTH_SHORT).show();
                loadData();
            }
        });
        aBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        aBuilder.show();
    }

}
