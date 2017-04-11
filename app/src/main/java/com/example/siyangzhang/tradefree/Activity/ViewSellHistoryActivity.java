package com.example.siyangzhang.tradefree.Activity;

import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;

import com.example.siyangzhang.tradefree.Bean.Db;
import com.example.siyangzhang.tradefree.R;

/**
 * Created by zijianhu on 4/9/17.
 */

public class ViewSellHistoryActivity extends ListActivity {
    private SimpleCursorAdapter adpater;
    private EditText Title;
    private Db db;
    private SQLiteDatabase dbRead, dbWrite;
    public static final String ID = "id";

    private AdapterView.OnItemClickListener itemclicklistener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Cursor c = adpater.getCursor();
            c.moveToPosition(position);
            //Log.d("onclick", "haaaaaahahahahahahhahahahaha");
            int itemId = c.getInt(c.getColumnIndex("_id"));
            //Log.d("onclick", "itemId = " + itemId);
            Intent intent = new Intent();
            intent.setClass(ViewSellHistoryActivity.this, ItemShowCateg.class);
            intent.putExtra(ID, itemId);
            startActivity(intent);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_showlist);

        Intent intent = getIntent();
        String SellerID = intent.getStringExtra("SellerID");
        //System.out.println(type);


        db = new Db(this);
        dbRead = db.getReadableDatabase();
        dbWrite = db.getWritableDatabase();
        // tableName, tableColumns, whereClause, whereArgs, groupBy, having, orderBy
        //Cursor c = dbRead.query("ITEM", null, "Type = ?", new String[]{type}, null, null, null);
        Cursor c = dbRead.query("ITEM", null, "SellerID = ?", new String[]{String.valueOf(1)}, null, null, null);
        //Cursor c = dbRead.query("ITEM", null, null, null, null, null, null);
        int count = 0;
        while(c.moveToNext()) {
            count++;
        }
        System.out.println(count);
        adpater = new SimpleCursorAdapter(this, R.layout.fragment_categ_list, c, new String[]{"ItemTitle"}, new int[]{R.id.tvName});
        //ListView Items = (ListView) findViewById(R.id.);
        setListAdapter(adpater);
        getListView().setOnItemClickListener(itemclicklistener);

        //refreshListView();
        db.close();
    }
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        // Read values from the "savedInstanceState"-object and put them in your textview
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        // Save the values you need from your textview into "outState"-object
        super.onSaveInstanceState(outState);
    }
}
