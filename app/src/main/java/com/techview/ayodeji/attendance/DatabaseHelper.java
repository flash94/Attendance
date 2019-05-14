package com.techview.ayodeji.attendance;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper{
    public static final String DATABASE_NAME="register.db";
    public static final String TABLE_NAME ="Register";
    public static final String MATRIC_TABLE = "matric_number";
    public static final String COL1 = "ID";
    public static final String COL2 = "Fname";
    public static final String COL3 = "Staffid";
    public static final String COL4 = "Department";
    public static final String COL5 = "Password";
    public static final String cos = "course";
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME + "(ID INTEGER PRIMARY KEY AUTOINCREMENT,Fname TEXT,Staffid TEXT,Department TEXT,Password TEXT)");
        db.execSQL("CREATE TABLE " + MATRIC_TABLE + "(ID INTEGER PRIMARY KEY AUTOINCREMENT,matric_number TEXT, course Text)");

    }
    @Override
    public void  onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME); //Drop older table if exists
        db.execSQL("DROP TABLE IF EXISTS " + MATRIC_TABLE); //Drop older table if exists
        onCreate(db);
    }
}
