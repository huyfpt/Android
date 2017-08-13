package com.sqlite.dth.hoangle.sqlite2609;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SQLite database = new SQLite(this, "TruongHoc.sqlite", null, 1);
                                    // màn hình,
                                    // tên database nên để .sqlite, khi khởi tạo con trỏ để null,
                                    // versiom để 1

        // tạo bảng NẾU KO TỒN TẠI [TÊN BẢNG]
        // chuỗi sẽ ko có nhắc lệnh phải cẩn thận
        // khai báo thuộc tính (tên thuộc tính, kiểu dữ liệu, khóa chính KEY, tự động tăng dần)
        // -> sẽ cho số tự tăng để ko trùng tên thuộc tính
        // HoTen VARCHAR, NamSinh INTEGER
        database.QueryData("CREATE TABLE IF NOT EXISTS HocSinh(Id INTEGER PRIMARY KEY AUTOINCREMENT, HoTen VARCHAR, NamSinh INTEGER)");

        // thêm dữ liệu vào bảng HocSinh
        // phải dùng dòng sau
        // VALUES(khai báo theo thứ tự như dòng code trên)
        // --> Vì tham số Id đầu tiên ko có nên để null
        // nhận họ tên , nhập năm sinh
//        database.QueryData("INSERT INTO HocSinh VALUES(null, 'Nguyễn văn B', 2000)");
        Cursor cursorHS = database.GetData("Select * from hocsinh");
        while (cursorHS.moveToNext){
            String hoten = cursorHS.getString(1);

        }
    }
}
