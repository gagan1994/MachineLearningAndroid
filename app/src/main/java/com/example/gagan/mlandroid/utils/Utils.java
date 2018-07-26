package com.example.gagan.mlandroid.utils;

import android.Manifest;
import android.content.pm.PackageManager;
import android.media.MediaActionSound;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

import com.example.gagan.mlandroid.MainActivity;
import com.example.gagan.mlandroid.dectector.BaseDetector;
import com.example.gagan.mlandroid.dectector.FaceDetectorHelper;
import com.example.gagan.mlandroid.dectector.TextDetectorHelper;
import com.example.gagan.mlandroid.type.Detectors;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Gagan on 6/8/2018.
 */

public class Utils {
    public static String[] permissions = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.INTERNET,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.CAMERA
    };
    public static final String FACE = "FACE";
    public static final String TEXT = "TEXT";
    public static final String BARCODE = "BARCODE";
    public static final String IMAGE = "IMAGE";
    public static final String LANDMARKS = "LANDMARKS";


    private static final HashMap<String, BaseDetector> _detectors = new HashMap<String, BaseDetector>();

    public static void initDetectors() {
        _detectors.put(FACE, new FaceDetectorHelper());
        _detectors.put(TEXT, new TextDetectorHelper());
    }

    public static boolean hasPermissions(MainActivity context, String[] permissions) {
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && context != null && permissions != null) {
            for (String permission : permissions) {
                if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
        }
        return true;
    }

    public static BaseDetector getDetector(String text) {
        return _detectors.get(text);
    }
}
