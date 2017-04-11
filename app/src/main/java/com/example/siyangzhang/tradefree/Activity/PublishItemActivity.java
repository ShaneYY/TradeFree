package com.example.siyangzhang.tradefree.Activity;

import android.Manifest;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.util.Log;

import com.example.siyangzhang.tradefree.Bean.Db;
import com.example.siyangzhang.tradefree.Bean.Photo;
import com.example.siyangzhang.tradefree.Fragment.ImageFragment;
import com.example.siyangzhang.tradefree.Fragment.TestCameraFragment;
import com.example.siyangzhang.tradefree.Util.ACache;
import com.example.siyangzhang.tradefree.Bean.User;
import com.example.siyangzhang.tradefree.R;
import com.example.siyangzhang.tradefree.Fragment.IndexFragment;
import com.example.siyangzhang.tradefree.Bean.Item;
import com.example.siyangzhang.tradefree.Util.PictureUtils;

import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by siyangzhang on 2/21/17.
 */

public class PublishItemActivity extends FragmentActivity {

    private ViewPager mViewpager;
    private List<Fragment> mTab = new ArrayList<>();
    private List<TextView> mIndicatorList = new ArrayList<>();
    private FragmentPagerAdapter mAdapter;
    private FrameLayout mIndicator;
    private Button publish;
    private int IndicatorWidth;
    private ImageButton mPhotoButton;
    private ImageView mPhotoView;
    private List<Item> Testitem = new ArrayList<>();
    private User mUser = new User();
    private Item mItem = new Item();
    private EditText mTitle;
    private EditText mPrice;
    private EditText mDetail;
    private Spinner mType;
    //private EditText mType;

    private String selectedType;
    private String filename;
    private Callbacks mCallbacks;
    private double latitude;
    private double longitude;
    public static final int CROP_PHOTO = 1;
    public static final int SELECT_PHOTO = 2;
    public static final int REQUEST_PHOTO = 3;
    private static final String TAG = "PublishItem";
    private static final String DIALOG_IMAGE = "image";

    Intent intent;

    private SimpleDateFormat simpleDateFormat;

    private Db db;
    private SQLiteDatabase dbWrite;


    public interface Callbacks {
        void onItemUpdated(Item item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "PublishItemActivity onCreate");

        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_item);

        db = new Db(this);
        dbWrite = db.getWritableDatabase();

        mPhotoButton = (ImageButton) findViewById(R.id.add_pictures);
        mType = (Spinner) findViewById(R.id.type_spinner);
        publish = (Button) findViewById(R.id.publish);
        simpleDateFormat = new SimpleDateFormat("yyyy-MM");


        LocationManager locManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);

        locManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 35000, 10, locationListener);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            requestPermissions(new String[]{Manifest.permission.ACCESS_NETWORK_STATE}, 1);

            return;
        }
        Location location = locManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
        if(location != null){
            latitude = location.getLatitude(); //经度
            longitude = location.getLongitude(); //纬度
        }

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

        mType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(
                    AdapterView<?> adapterView, View view,
                    int i, long l) {
                selectedType = mType.getSelectedItem().toString();
            }

            public void onNothingSelected(
                    AdapterView<?> adapterView) {

            }
        });

        publish.setEnabled(false);
        // Photo Button
        mPhotoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(PublishItemActivity.this, TestCameraActivity.class);
                startActivityForResult(i, REQUEST_PHOTO);
            }
        });

        publish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTitle = (EditText) findViewById(R.id.title);
                mPrice = (EditText) findViewById(R.id.price);
                mDetail = (EditText) findViewById(R.id.detail);


                ContentValues cv = new ContentValues();
                cv.put("SellerID", 1);
                cv.put("ItemTitle", mTitle.getText().toString());
                cv.put("Price", mPrice.getText().toString());
                cv.put("Detail", mDetail.getText().toString());
                cv.put("Type", selectedType);
                cv.put("Photo", filename);
                cv.put("Longitude", longitude);
                cv.put("Latitude", latitude);
                dbWrite.insert("ITEM", null, cv);



                mUser.setUserId(UUID.randomUUID());
                mUser.setName("Shane");
                mItem.setItemId(UUID.randomUUID());
                mItem.setTitle(mTitle.getText().toString());
                mItem.setPrice(mPrice.getText().toString());
                mItem.setDetail(mDetail.getText().toString());
                mItem.setUser(mUser);
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                mItem.setTime(simpleDateFormat.format(new Date()));

                intent = new Intent();
                intent.putExtra(IndexFragment.Item_Info, mItem);
                MainActivity.mCache.put(simpleDateFormat.format(new Date()) + "", mItem, 2 * ACache.TIME_DAY);

                setResult(IndexFragment.PUBLISHITEM, intent);

                finish();
            }
        });

        // Photographic Evidence
        mPhotoView = (ImageView) findViewById(R.id.pica);
        mPhotoView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Photo p = mItem.getImage();
                if ( p == null ){
                    return;
                }
                FragmentManager fm = getSupportFragmentManager();
                String path = getFileStreamPath(p.getFilename()).getAbsolutePath();
                ImageFragment.newInstance(path).show(fm, DIALOG_IMAGE);
            }
        });

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
                case REQUEST_PHOTO:
                    // Create a new Photo object and attach it to the test
                    filename = data.getStringExtra(TestCameraFragment.EXTRA_PHOTO_FILENAME);

                    if ( filename != null ){
                        Photo p = new Photo(filename);
                        mItem.setImage(p);
                        //mCallbacks.onItemUpdated(mItem);
                        publish.setEnabled(true);
                        showPhoto();
                    }
                    break;
                default:
                    break;
            }
        }else{
            return;
        }
    }

    private void showPhoto() {
        // (Re)set the image button's image based on our photo
        Photo p = mItem.getImage();
        BitmapDrawable b = null;

        Log.d(TAG, "Inside showPhoto");

        if ( p != null ){
            String path = getFileStreamPath(p.getFilename()).getAbsolutePath();
            b = PictureUtils.getScaledDrawable(this, path);
        }
        mPhotoView.setImageDrawable(b);
    }

    LocationListener locationListener = new LocationListener() {
        // Provider的状态在可用、暂时不可用和无服务三个状态直接切换时触发此函数
        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        // Provider被enable时触发此函数，比如GPS被打开
        @Override
        public void onProviderEnabled(String provider) {

        }

        // Provider被disable时触发此函数，比如GPS被关闭
        @Override
        public void onProviderDisabled(String provider) {

        }

        //当坐标改变时触发此函数，如果Provider传进相同的坐标，它就不会被触发
        @Override
        public void onLocationChanged(Location location) {
            if (location != null) {
                Log.e("Map", "Location changed : Lat: "
                        + location.getLatitude() + " Lng: "
                        + location.getLongitude());
            }
        }
    };

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



