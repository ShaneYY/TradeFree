package com.example.siyangzhang.tradefree.Activity;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.siyangzhang.tradefree.Bean.Db;
import com.example.siyangzhang.tradefree.Bean.Photo;
import com.example.siyangzhang.tradefree.R;
import com.example.siyangzhang.tradefree.Fragment.IndexFragment;
import com.example.siyangzhang.tradefree.Util.PictureUtils;

import java.io.FileNotFoundException;

public class ItemShow extends Activity {

    private TextView itemShow;
    private ImageView imageShow;
    private final String TAG = "ItemShow";

    private Db db;
    private SQLiteDatabase dbRead;

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
        String str = intent.getStringExtra(IndexFragment.Item_Info);
        String path = getFileStreamPath(str).getAbsolutePath();
        BitmapDrawable b = PictureUtils.getScaledDrawable(this, path);

        imageShow.setImageDrawable(b);
        imageShow.setRotation(90);
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

    private Html.ImageGetter imageGetter = new Html.ImageGetter() {
        @Override
        public Drawable getDrawable(String source) {
            Drawable d = null;
            try {
                d = Drawable.createFromStream(getApplicationContext().getContentResolver()
                        .openInputStream(Uri.parse(source)), null);
                d.setBounds(0, 0, d.getIntrinsicWidth(), d.getIntrinsicHeight());
                Log.d("widthAndheight",d.getIntrinsicWidth()+"----"+d.getIntrinsicHeight()+"");
                return d;

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            return null;
        }
    };
}
