package com.example.gagan.mlandroid;

import android.content.Intent;
import android.os.Build;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.gagan.mlandroid.adapters.ViewPagerAdapter;
import com.example.gagan.mlandroid.dectector.BaseDetector;
import com.example.gagan.mlandroid.dectector.FaceDetectorHelper;
import com.example.gagan.mlandroid.dectector.TextDetectorHelper;
import com.example.gagan.mlandroid.fragments.BaseFragments;
import com.example.gagan.mlandroid.fragments.DetectionFragment;
import com.example.gagan.mlandroid.utils.Utils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    private DetectionFragment detectionFragment;
    private DetectionFragment textDetectionFragment;
    List<BaseFragments> fragments = new ArrayList<>();
    private static final int PERMISSION_REQ = 123;

    private void initFragment() {
        Utils.initDetectors();
//        addDetectionFragment(Utils.FACE);
        addDetectionFragment(Utils.TEXT);
    }

    private void addDetectionFragment(String detectorHelper) {
        detectionFragment = new DetectionFragment();
        Bundle bundle = new Bundle();
        bundle.putString(DetectionFragment.DETECTOR_HELPER, detectorHelper);
        detectionFragment.setArguments(bundle);
        detectionFragment.setDetectorHelper(Utils.getDetector(detectorHelper));
        fragments.add(detectionFragment);
    }

    @BindView(R.id.view_pager)
    ViewPager viewPager;
    @BindView(R.id.tab_item)
    TabLayout tabLayout;

    PagerAdapter pagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        checkPhoneStatePermission();
        init();
    }

    public void checkPhoneStatePermission() {

        // if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
        //ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_PHONE_STATE}, REQUEST_READ_PHONE_STATE);
        if (!Utils.hasPermissions(this, Utils.permissions)) {
            ActivityCompat.requestPermissions(this, Utils.permissions, PERMISSION_REQ);
        }
    }

    private void init() {
        ButterKnife.bind(this);
        initAdapter();
        tabLayout.setupWithViewPager(viewPager);
    }

    private void initAdapter() {
        initFragment();
        pagerAdapter = new ViewPagerAdapter(fragments, getSupportFragmentManager());
        viewPager.setAdapter(pagerAdapter);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case PERMISSION_REQ:
                    init();
                    break;
            }
        }
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus)
            if (Build.VERSION.SDK_INT >= 19) {
                getWindow().getDecorView().setSystemUiVisibility(
                        View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                                | View.SYSTEM_UI_FLAG_FULLSCREEN
                                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
            } else {
                if (Build.VERSION.SDK_INT > 10) {
                    findViewById(android.R.id.content).setSystemUiVisibility(View.SYSTEM_UI_FLAG_VISIBLE);
                    getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE);
                }
            }

    }
}
