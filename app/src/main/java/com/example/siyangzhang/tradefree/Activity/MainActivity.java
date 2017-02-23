package com.example.siyangzhang.tradefree.Activity;


import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.siyangzhang.tradefree.R;
import com.example.siyangzhang.tradefree.ACache;
import com.example.siyangzhang.tradefree.fragment.IndexFragment;
import com.example.siyangzhang.tradefree.fragment.CategFragment;
import com.example.siyangzhang.tradefree.fragment.NearbyFragment;
import com.example.siyangzhang.tradefree.fragment.ProfileFragment;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private FragmentManager fm;

    private LinearLayout mTabIndex, mTabClass, mTabFind, mTabMe;

    private ImageView mIndexImg, mClassImg, mFindImg, mMeImg;

    private Fragment tabindex, tabclass, tabfind, tabme;

    private TextView title_text;

    public static ACache mCache;

    public static final String TAG = "Main";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "Activity onCreate");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mCache= ACache.get(this);

        initView();
        initEvent();
        setSelect(0);
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

    private void initView() {

        mTabIndex = (LinearLayout) findViewById(R.id.id_index);
        mTabClass = (LinearLayout) findViewById(R.id.id_class);
        mTabFind = (LinearLayout) findViewById(R.id.id_find);
        mTabMe = (LinearLayout) findViewById(R.id.id_me);

        mIndexImg = (ImageView) findViewById(R.id.id_indeximg);
        mClassImg = (ImageView) findViewById(R.id.id_classimg);
        mFindImg = (ImageView) findViewById(R.id.id_findimg);
        mMeImg = (ImageView) findViewById(R.id.id_meimg);

        title_text = (TextView) findViewById(R.id.title_text);
    }

    private void initEvent() {
        mTabIndex.setOnClickListener(this);
        mTabClass.setOnClickListener(this);
        mTabFind.setOnClickListener(this);
        mTabMe.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.id_index:
                setSelect(0);
                break;
            case R.id.id_class:
                setSelect(1);
                break;
            case R.id.id_find:
                setSelect(2);
                break;
            case R.id.id_me:
                setSelect(3);
                break;
        }
    }

    private void setSelect(int i) {
        switch (i) {
            case 0:
                if (tabindex == null) {
                    tabindex = new IndexFragment();
                }
                changeFragment(tabindex);
                break;
            case 1:
                if (tabclass == null) {
                    tabclass = new CategFragment();
                }
                changeFragment(tabclass);
                break;
            case 2:
                if (tabfind == null) {
                    tabfind = new NearbyFragment();
                }
                changeFragment(tabfind);
                break;
            case 3:
                if (tabme == null) {
                    tabme = new ProfileFragment();
                }
                changeFragment(tabme);
                break;
        }
        setTab(i);

    }

    private void setTab(int i) {
        resetImgs();
        switch (i) {
            case 0:
                title_text.setVisibility(View.VISIBLE);
                title_text.setClickable(true);
                title_text.setText("The Ohio State University");
                title_text.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(MainActivity.this, "Currently unable to switch campus",
                                Toast.LENGTH_LONG).show();
                    }
                });
                mIndexImg.setImageResource(R.drawable.index_pressed);
                break;
            case 1:
                title_text.setVisibility(View.VISIBLE);
                title_text.setClickable(false);
                title_text.setText("Category");
                mClassImg.setImageResource(R.drawable.classes_pressed);
                break;
            case 2:
                title_text.setVisibility(View.VISIBLE);
                title_text.setClickable(false);
                title_text.setText("Nearby");
                mFindImg.setImageResource(R.drawable.find_pressed);
                break;
            case 3:
                title_text.setVisibility(View.VISIBLE);
                title_text.setClickable(false);
                title_text.setText("Profile");
                mMeImg.setImageResource(R.drawable.me_pressed);
                break;
        }
    }
    private void changeFragment(Fragment targetFragment){
        fm = getSupportFragmentManager();
        if(fm!=null) {
            fm.beginTransaction()
                    .replace(R.id.fmcontent, targetFragment, "fragment")
                    .setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                    .commit();
        }
    }
    private void resetImgs() {
        mIndexImg.setImageResource(R.drawable.index);
        mClassImg.setImageResource(R.drawable.classes);
        mFindImg.setImageResource(R.drawable.find);
        mMeImg.setImageResource(R.drawable.me);
    }

    Boolean ActionSheetFlag = false;
    private static long firstTime;


    @Override
    public void onBackPressed() {
        // TODO Auto-generated method stub
        if (ActionSheetFlag) {
            super.onBackPressed();
        } else {
            if (firstTime + 2000 > System.currentTimeMillis()) {
                super.onBackPressed();
            } else {
            }
            firstTime = System.currentTimeMillis();
        }
    }

    public void onIndexFragmentInteraction(Uri uri) {
        // Do stuff
    }
    public void onCategFragmentInteraction(Uri uri) {
        // Do stuff
    }
    public void onNearbyFragmentInteraction(Uri uri) {
        // Do stuff
    }
    public void onProfileFragmentInteraction(Uri uri) {
        // Do stuff
    }
}
