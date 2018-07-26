package com.example.gagan.mlandroid.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.gagan.mlandroid.R;
import com.example.gagan.mlandroid.dectector.BaseDetector;

import butterknife.BindView;
import butterknife.Unbinder;

/**
 * Created by Gagan on 6/8/2018.
 */

public class BaseFragments extends Fragment {
    protected Unbinder unbinder;
    protected BaseDetector _detectorHelper;

    public void setDetectorHelper(@NonNull BaseDetector baseDetector) {
        _detectorHelper = baseDetector;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (unbinder != null)
            unbinder.unbind();
    }

    public String getTitleOfThis() {
        return "Help";
    }
}
