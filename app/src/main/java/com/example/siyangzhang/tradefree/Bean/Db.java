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
        db.execSQL("CREATE TABLE ITEM(_id INTEGER PRIMARY KEY AUTOINCREMENT, SellerID INTEGER, ItemTitle varchar(20), Price REAL, Detail TEXT, Phone TEXT, Type varchar(20));");
        db.execSQL("INSERT INTO ITEM VALUES(10, 1,'myBigPC', 7000.0, 'it is good', '412-419-882','PC');");
        db.execSQL("INSERT INTO ITEM VALUES(11, 1,'mySmallPC', 3000.0, 'it is good', '412-419-883','PC');");
        db.execSQL("INSERT INTO ITEM VALUES(12, 1,'myBigTV', 1000.0, 'it is good', '412-419-880','TV');");
        db.execSQL("INSERT INTO ITEM VALUES(13, 2,'mySmallTV', 500.0, 'it is good', '412-419-881','TV');");
        db.execSQL("INSERT INTO ITEM VALUES(14, 1,'myBigCAL', 100.0, 'it is good', '412-419-884','CL');");
        db.execSQL("INSERT INTO ITEM VALUES(15, 3,'mySmallCAL', 50.0, 'it is good', '412-419-885','CL');");
        db.execSQL("INSERT INTO ITEM VALUES(16, 1,'myBigPHONE', 1000.0, 'it is good', '412-419-886','PH');");
        db.execSQL("INSERT INTO ITEM VALUES(17, 1,'mySmallPHONE', 700.0, 'it is good', '412-419-887','PH');");
        db.execSQL("INSERT INTO ITEM VALUES(18, 1,'myBigCAMERA', 1000.0, 'it is good', '412-419-886','CM');");
        db.execSQL("INSERT INTO ITEM VALUES(19, 1,'mySmallCAMERA', 700.0, 'it is good', '412-419-887','CM');");
        db.execSQL("INSERT INTO ITEM VALUES(20, 1,'myBigFDRIVE', 1000.0, 'it is good', '412-419-886','FD');");
        db.execSQL("INSERT INTO ITEM VALUES(21, 1,'mySmallFDRIVE', 700.0, 'it is good', '412-419-887','FD');");
    }



    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
