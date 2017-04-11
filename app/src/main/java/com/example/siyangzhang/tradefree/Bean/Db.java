package com.example.siyangzhang.tradefree.Bean;

import android.content.Context;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by zijianhu on 3/25/17.
 */

public class Db extends SQLiteOpenHelper {
    public Db(Context context) {
        super(context, "db", null, 1, null);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE ITEM(_id INTEGER PRIMARY KEY AUTOINCREMENT, SellerID INTEGER, ItemTitle varchar(20), " +
                "Price TEXT, Detail TEXT, Phone TEXT, Type varchar(20), Photo varchar(20), Longitude DOUBLE, Latitude DOUBLE);");
        //db.execSQL("CREATE TABLE USER(_id INTEGER PRIMARY KEY AUTOINCREMENT, UserID INTEGER, UserName varchar(20), Sex TEXT, Phone TEXT, Longitude INTEGER, Latitude INTEGER);");

    }



    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
