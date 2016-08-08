package com.zlb.demos.androiddemos.labels.fragments;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zlb.demos.androiddemos.R;

/**
 * A placeholder fragment containing a simple view.
 */
public class FirstTestActivityFragment extends Fragment {

    public FirstTestActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_first_test, container, false);
    }
}
