package com.example.resq;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class databaseHelper extends SQLiteOpenHelper
{

    public databaseHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}

//public void onCreate(SQLiteDatabase db) {
//    db.execSQL("CREATE TABLE UserInput (id INTEGER PRIMARY KEY, input TEXT)");
//}
//SQLiteDatabase db = dbHelper.getWritableDatabase();
//ContentValues values = new ContentValues();
//values.put("input", userInput);
//db.insert("UserInput", null, values);