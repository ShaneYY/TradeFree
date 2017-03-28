package com.example.siyangzhang.tradefree.Activity;

import android.app.Activity;
import android.app.ListActivity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.siyangzhang.tradefree.Bean.Db;
import com.example.siyangzhang.tradefree.R;

/**
 * Created by zijianhu on 3/26/17.
 */

public class ViewCategActivity extends ListActivity {
    private SimpleCursorAdapter adpater;
    private EditText Title;
    private Db db;
    private SQLiteDatabase dbRead, dbWrite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_showlist);

        Intent intent = getIntent();
        String type = intent.getStringExtra("type");
        //System.out.println(type);

        db = new Db(this);
        dbRead = db.getReadableDatabase();
        dbWrite = db.getWritableDatabase();
        // tableName, tableColumns, whereClause, whereArgs, groupBy, having, orderBy
        //Cursor c = dbRead.query("ITEM", null, "Type = ?", new String[]{type}, null, null, null);
        Cursor c = dbRead.query("ITEM", null, "Type = ?", new String[]{type}, null, null, null);
        int count = 0;
        while(c.moveToNext()) {
            count++;
        }
        System.out.println(count);
        adpater = new SimpleCursorAdapter(this, R.layout.fragment_categ_list, c, new String[]{"ItemTitle"}, new int[]{R.id.tvName});
        //ListView Items = (ListView) findViewById(R.id.);
        setListAdapter(adpater);
        //refreshListView();
        db.close();

    }

    /*private void refreshListView() {
        // tableName, tableColumns, whereClause, whereArgs, groupBy, having, orderBy
        Cursor c = dbRead.query("ITEM", null, "Type = ?", new String[]{"TV"}, null, null, null);
        adpater.changeCursor(c);
    }*/
}
