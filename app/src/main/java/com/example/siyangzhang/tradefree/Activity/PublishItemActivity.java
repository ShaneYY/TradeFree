package com.example.siyangzhang.tradefree.Activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.util.Log;
import com.example.siyangzhang.tradefree.ACache;
import com.example.siyangzhang.tradefree.Bean.User;
import com.example.siyangzhang.tradefree.R;
import com.example.siyangzhang.tradefree.fragment.IndexFragment;
import com.example.siyangzhang.tradefree.Bean.Item;

import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
public class PublishItemActivity extends FragmentActivity {

    private ViewPager mViewpager;
    private List<Fragment> mTab = new ArrayList<>();
    private List<TextView> mIndicatorList = new ArrayList<>();
    private FragmentPagerAdapter mAdapter;
    private FrameLayout mIndicator;
    private Button publish;
    private int IndicatorWidth;
    private ImageView addPictures;
    private List<Item> Testitem=new ArrayList<>();

    private EditText mTitle;
    private EditText mPrice;
    private EditText mDetail;
    private EditText mType;

    public static final int CROP_PHOTO=1;
    public static final int SELECT_PHOTO=2;

    private static final String TAG = "PublishItem";

    Intent intent;

    private SimpleDateFormat simpleDateFormat;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "PublishItemActivity onCreate");

        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_item);

        publish = (Button) findViewById(R.id.publish);
        simpleDateFormat=new SimpleDateFormat("yyyy-MM");
        initEvents();
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

    private void initEvents() {
        publish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTitle = (EditText) findViewById(R.id.title);
                mDetail = (EditText) findViewById(R.id.detail);
                User user = new User();
                user.setUserId(UUID.randomUUID());
                user.setName("Shane");
                Item item = new Item();
                item.setItemId(UUID.randomUUID());
                item.setTitle(mTitle.getText().toString());
                item.setDetail(mDetail.getText().toString());
                item.setUser(user);
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                item.setTime(simpleDateFormat.format(new Date()));

                intent = new Intent();
                intent.putExtra(IndexFragment.Item_Info, item);
                MainActivity.mCache.put(simpleDateFormat.format(new Date()) + "", item, 2 * ACache.TIME_DAY);
                setResult(IndexFragment.PUBLISHITEM, intent);

                finish();
            }
        });
    }

    public void hideAddPictures() {
        if (addPictures.getVisibility() == View.VISIBLE) {
            addPictures.setVisibility(View.GONE);
        }
    }

    public void showAddPictures() {
        if (addPictures.getVisibility() == View.GONE) {
            addPictures.setVisibility(View.VISIBLE);
        }
    }

    public static void startPublishItemActivity(Context context, String... data) {
        Intent intent = new Intent(context, PublishItemActivity.class);
        intent.putExtra("Data", data);
        context.startActivity(intent);
    }

    public void getImage(){
        Intent SysImageintent=new Intent();
        SysImageintent.setAction(Intent.ACTION_OPEN_DOCUMENT);
        SysImageintent.setType("image/*");
        SysImageintent.putExtra("scale", true);
        SysImageintent.putExtra("crop",true);
        startActivityForResult(SysImageintent, CROP_PHOTO);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Uri imageUri;
        if (data!=null) {
            imageUri = data.getData();

            switch (requestCode) {
                case SELECT_PHOTO:

                    Intent intent = new Intent("com.android.camera.action.CROP");
                    intent.setDataAndType(imageUri, "image/*");
                    intent.putExtra("scale", true);
                    intent.putExtra("crop", true);
                    // aspectX aspectY
                    intent.putExtra("aspectX", 1);
                    intent.putExtra("aspectY", 1);

                    // outputX,outputY
                    intent.putExtra("outputX", 150);
                    intent.putExtra("outputY", 150);
                    intent.putExtra("return-data", true);
                    startActivityForResult(intent, CROP_PHOTO);
                    break;
                case CROP_PHOTO:
                    try {
                        Bitmap bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(imageUri));
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    break;
                default:
                    break;
            }
        }else{
            return;
        }
    }
}



