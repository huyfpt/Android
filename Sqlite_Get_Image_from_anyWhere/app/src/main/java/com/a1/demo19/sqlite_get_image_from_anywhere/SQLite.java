package com.a1.demo19.sqlite_get_image_from_anywhere;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

/**
 * Created by duchuy on 12/5/2016.
 */
public class SQLite extends SQLiteOpenHelper {
    int idstores = 0;
    public SQLite(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    //truy vấn k trả về
    public void QueryData(String sql) {
        SQLiteDatabase database = getWritableDatabase();
        database.execSQL(sql);
    }

    // insert image (type blob) va sqlite--Parameter dduwjowjc xếp đúng như table in sql
       public void InsertProduct(String name, float price, byte[] image) {
        SQLiteDatabase database = getWritableDatabase();
        String sql = "Insert into Product values (null,?,?,?)"; //?1...?2....?3
        SQLiteStatement statement = database.compileStatement(sql);// SQLiteStatement goi câu lệnh và biên dịch
        statement.clearBindings();//biên dịch những ? và xóa bộ nhớ đệm
        //biên dịch những ?
        statement.bindString(1, name);//index ?1 = 1, value = name
        statement.bindDouble(2, price);// index ?2= 2, value = price
        statement.bindBlob(3, image); //index ?3 = 3, value = image
        //câu lệnh thực thi Insert
        statement.executeInsert();
    }
    public void UpdateProduct(String name, float price, byte[] image, int id) {

        SQLiteDatabase database = getWritableDatabase();
        String sql = "UPDATE Product SET ProductName =?," +
                "Price =?, ImageProduct =? WHERE Id ='" + id + "'";
        SQLiteStatement statement = database.compileStatement(sql);// SQLiteStatement goi câu lệnh và biên dịch
        statement.clearBindings();//biên dịch những ? và xóa bộ nhớ đệm
        //biên dịch những ?
        statement.bindString(1, name);//index ?1 = 1, value = name
        statement.bindDouble(2, price);// index ?2= 2, value = price
        statement.bindBlob(3, image); //index ?3 = 3, value = image
        //câu lệnh thực thi Insert
        statement.executeUpdateDelete();
    }

    //truy vấn trả về KQua
    public Cursor GetData(String sql) {
        SQLiteDatabase database = getReadableDatabase();
        return database.rawQuery(sql, null);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
