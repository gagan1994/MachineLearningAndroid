package com.example.gagan.mlandroid.dectector;

import android.content.Context;
import android.net.Uri;

import com.example.gagan.mlandroid.fragments.DetectionFragment;

import java.io.Serializable;

/**
 * Created by Gagan on 6/8/2018.
 */

public abstract class BaseDetector implements Serializable {

    protected DetectionFragment detectionFragment;

    public abstract String getTitle();

    public abstract void initFirebaseDetectionObj();

    public abstract void initFirebaseVision(Uri uri, Context context);

    public void addCallBack(DetectionFragment detectionFragment) {
        this.detectionFragment = detectionFragment;
    }
}
