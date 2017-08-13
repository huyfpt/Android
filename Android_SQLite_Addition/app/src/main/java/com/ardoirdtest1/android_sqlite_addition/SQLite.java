package com.ardoirdtest1.android_sqlite_addition;

/**
 * Created by duchuy on 4/3/2017.
 */

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
/**
 * Created by duchuy on 4/3/2017.
 */
public class SQLite extends SQLiteOpenHelper {
    public SQLite(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    //stetament not return
    public void QueryData(String sql) {
        SQLiteDatabase database = getWritableDatabase();
        database.execSQL(sql);
    }

    // insert image (type blob) va sqlite--Parameter arrangement table in sql
    public void InsertEvent(String activity,String location, String date, String time,String name, byte[] image) {
        SQLiteDatabase database = getWritableDatabase();
        String sql = "Insert into Event values (null,?,?,?,?,?,?)"; //?1...?2....?3
        SQLiteStatement statement = database.compileStatement(sql);// SQLiteStatement goi câu lệnh và biên dịch
        statement.clearBindings();//biên dịch những ? và xóa bộ nhớ đệm
        //biên dịch những ?
        statement.bindString(1, activity);//index ?1 = 1, value = activity
        statement.bindString(2, location);
        statement.bindString(3, date);
        statement.bindString(4, time);
        statement.bindString(5, name);
        statement.bindBlob(6, image); //index ?6= 6, value = image
        //câu lệnh thực thi Insert
        statement.executeInsert();
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
