package com.iven.i7helper.main.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.iven.i7helper.R;
import com.iven.i7helper.base.BaseFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class MemoryFragment extends BaseFragment {


    public MemoryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       return inflater.inflate(R.layout.fragment_memory,container,false);
    }

}
