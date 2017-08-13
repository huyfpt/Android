package com.sqlite.dth.hoangle.sqlite2609;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by HoangLe on 28-Nov-16.
 */

public class SQLite extends SQLiteOpenHelper {
    public SQLite(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
    // context tên , tên của database , con trỏ trong SQLite : dùng để duyệt dữ liệu , kiểu int : phiên bản của CSDL
        super(context, name, factory, version);
    }

    // truy vấn ko trả kết quả
    public void QueryData(String sql){ // kiểu chuổi sql là tên tham số tự đặt
        SQLiteDatabase database = getWritableDatabase(); // cho phép ghi gì đó
                                                        // quyền ghi cao hơn quyền đọc
        database.execSQL(sql); // đưa vào lệnh để thực thi câu lệnh đó
    }

    // truy vấn có trả kết quả
    public Cursor GetData(String sql) { //phải dùng con trỏ Tên function
        SQLiteDatabase database = getReadableDatabase(); // quyền đọc chỉ có thể đọc
                                                        // quyền ghi cũng có thể đọc
        return database.rawQuery(sql,null);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
    // khi tạo cái gì đó
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    // khi nào nâng cấp CSDL thì sẽ chạy
    }
}
