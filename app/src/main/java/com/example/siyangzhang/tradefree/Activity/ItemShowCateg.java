package com.example.siyangzhang.tradefree.Activity;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.siyangzhang.tradefree.Bean.Item;
import com.example.siyangzhang.tradefree.Bean.Db;
import com.example.siyangzhang.tradefree.Bean.Photo;
import com.example.siyangzhang.tradefree.R;
import com.example.siyangzhang.tradefree.Fragment.IndexFragment;
import com.example.siyangzhang.tradefree.Util.PictureUtils;

import java.io.FileNotFoundException;

/**
 * Created by siyangzhang on 2/17/17.
 */

public class ItemShowCateg extends Activity {

    private TextView itemShow;
    private ImageView imageShow;
    private final String TAG = "ItemShow";

    private Db db;
    private SQLiteDatabase dbRead, dbWrite;
    private Item item = new Item();
    private String itemTitle;
    private String detail;
    private String price;
    private String photo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "Activity onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_itemshow);

        db = new Db(this);
        dbRead = db.getReadableDatabase();



        itemShow = (TextView) findViewById(R.id.item_show);
        imageShow = (ImageView) findViewById(R.id.image_show);
        Intent intent = getIntent();
        int id = intent.getIntExtra(ViewCategActivity.ID, 0);
        db = new Db(this);
        dbRead = db.getReadableDatabase();
        dbWrite = db.getWritableDatabase();
        Cursor c = dbRead.query("ITEM", null, "_id =?",
                new String[] { String.valueOf(id) }, null, null, null);
        while(c.moveToNext()) {
            int itemTitleIndex=c.getColumnIndex("ItemTitle");
            itemTitle=c.getString(itemTitleIndex);
            int priceIndex=c.getColumnIndex("Price");
            price=c.getString(priceIndex);
            int detialIndex=c.getColumnIndex("Detail");
            detail=c.getString(detialIndex);
            int photoIndex=c.getColumnIndex("Photo");
            photo=c.getString(photoIndex);
        }

        itemShow.setText("Title:" + itemTitle + " \n" + "Price:" + price + " \n" + "Detail:" + detail);
        String path = getFileStreamPath(photo).getAbsolutePath();
        BitmapDrawable b = PictureUtils.getScaledDrawable(this, path);
        imageShow.setImageDrawable(b);
        imageShow.setRotation(90);
        /*
        BitmapDrawable b = PictureUtils.getScaledDrawable(this, path);
        imageShow.setImageDrawable(b);
        imageShow.setRotation(90);
        */
    }

    @Override
    protected void onResume() {
        Log.d(TAG, "Activity onResume");
        super.onResume();
    }

    @Override
    protected void onStop() {
        Log.d(TAG, "Activity onStop");
        super.onStop();
    }

    @Override
    protected void onPause() {
        Log.d(TAG, "Activity onPause");
        super.onPause();
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
