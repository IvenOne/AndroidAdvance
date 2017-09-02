package com.iven.i7helper.main.fragment;


import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.ValueCallback;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.iven.i7helper.R;
import com.iven.i7helper.base.BaseFragment;
import com.iven.i7helper.main.MainActivity;
import com.iven.i7helper.util.ToolUtil;
import com.iven.i7helper.util.web.SelfWebChromeClient;
import com.iven.i7helper.util.web.SelfWebViewClient;

/**
 * A simple {@link Fragment} subclass.
 */
public class HappyFragment extends BaseFragment {

    private WebView mWebview;
    private SelfWebViewClient mSelfWebViewClient;
    private SelfWebChromeClient mSelfWebChromeClient;

    private int paintedEgg = 1; //彩蛋
    private TextView mTextview;


    public HappyFragment() {
        // Required empty public constructor
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mTextview = ((MainActivity)getActivity()).getHeadTextView();
        mTextview.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View v) {
                if(paintedEgg >10){
                    paintedEgg = 1;
                }else if(paintedEgg == 10){
                    sendJsRequest("javascript:callJs('hahahahahaha')");
                }
                paintedEgg++;
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        LinearLayout layout = (LinearLayout) inflater.inflate(R.layout.fragment_happy, container, false);

        mWebview = (WebView) layout.findViewById(R.id.fragment_happy_id_webview);
        mSelfWebChromeClient = new SelfWebChromeClient(getContext());
        mSelfWebViewClient = new SelfWebViewClient(getContext());
        mWebview.setWebViewClient(mSelfWebViewClient);
        mWebview.setWebChromeClient(mSelfWebChromeClient);

        initWebview();

        return layout;
    }

    private void initWebview() {

        //步骤2. 选择加载方式
        //方式1. 加载一个网页：
//        webView.loadUrl("http://www.google.com/");

        //方式2：加载apk包中的html页面
//        webView.loadUrl("file:///android_asset/test.html");

        //方式3：加载手机本地的html页面
//        webView.loadUrl("content://com.android.htmlfileprovider/sdcard/test.html");

        mWebview.loadUrl("file:///android_asset/love.html");
//        mWebview.loadUrl("https://www.baidu.com/");


        WebSettings webSettings = mWebview.getSettings();
        webSettings.setJavaScriptEnabled(true);//设置支持JavaScript脚本
        webSettings.setLoadWithOverviewMode(true);  //These two lines enable the browser to show in a screen.
        webSettings.setUseWideViewPort(true);
        webSettings.setBuiltInZoomControls(true);//设置支持手势缩放
        webSettings.setDisplayZoomControls(false);//隐藏屏幕中放大缩小按钮
        webSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);//设置缓存格式，此处设置为不缓存

    }

    @Override
    public void onDestroy() {
        if(mWebview != null){
            mWebview.loadDataWithBaseURL(null,"","text/html","utf-8",null);
            mWebview.clearCache(true);
            mWebview.clearHistory();
            mWebview.clearFormData();
            ((ViewGroup)(mWebview.getParent())).removeView(mWebview);
            mWebview.destroy();
            mWebview = null;
        }

        super.onDestroy();
    }


    /**
     * Send js request in Activity.
     */
    private void sendJsRequest(String content){
        if(Build.VERSION.SDK_INT <= 18){
            mWebview.loadUrl(content);
        }else{
            mWebview.evaluateJavascript(content, new ValueCallback<String>() {
                @Override
                public void onReceiveValue(String value) {
                    ToolUtil.showMessage(getContext(),value);
                }
            });
        }
    }


}
