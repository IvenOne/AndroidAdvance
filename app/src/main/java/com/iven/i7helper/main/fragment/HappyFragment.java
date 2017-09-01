package com.iven.i7helper.main.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.iven.i7helper.R;
import com.iven.i7helper.base.BaseFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class HappyFragment extends BaseFragment {

    private WebView mWebview;


    public HappyFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        LinearLayout layout = (LinearLayout) inflater.inflate(R.layout.fragment_happy, container, false);

        mWebview = (WebView) layout.findViewById(R.id.fragment_happy_id_webview);

        initWebview();

        return layout;
    }

    private void initWebview() {

        mWebview.loadUrl("file:///android_asset/love.html");


        WebSettings webSettings = mWebview.getSettings();
        webSettings.setJavaScriptEnabled(true);//设置支持JavaScript脚本
        webSettings.setLoadWithOverviewMode(true);  //These two lines enable the browser to show in a screen.
        webSettings.setUseWideViewPort(true);
        webSettings.setBuiltInZoomControls(true);//设置支持手势缩放

    }

}
