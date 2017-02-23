package com.example.siyangzhang.tradefree.Activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.widget.TextView;

import com.example.siyangzhang.tradefree.R;
import com.example.siyangzhang.tradefree.fragment.IndexFragment;

import java.io.FileNotFoundException;

public class ItemShow extends Activity {

    private TextView itemShow;
    private final String TAG = "ItemShow";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "Activity onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_itemshow);

        itemShow = (TextView) findViewById(R.id.item_show);

        Intent intent = getIntent();
        String str = intent.getStringExtra(IndexFragment.Item_Info);
        itemShow.append(Html.fromHtml(str, imageGetter, null));
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
