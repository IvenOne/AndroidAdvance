package com.iven.i7helper.util.web;

import android.content.Context;
import android.net.Uri;
import android.webkit.JsPromptResult;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

import com.iven.i7helper.base.Config;
import com.iven.i7helper.util.ToolUtil;

import java.util.HashMap;
import java.util.Set;


/**
 * Created by Iven on 2017/9/2.
 * WebChromeClient 主要用于协助WebView 处理一些Javascript的对话框，网站图标，网站title，以及加在进度
 */

public class SelfWebChromeClient extends WebChromeClient {

    private Context mContext;
    private String TAG = this.getClass().getName();

    public SelfWebChromeClient (Context mContext){
        this.mContext = mContext;
    }
    @Override
    public void onReceivedTitle(WebView view, String title) {

        super.onReceivedTitle(view, title);
    }

    @Override
    public void onProgressChanged(WebView view, int newProgress) {
        super.onProgressChanged(view, newProgress);
    }


    @Override
    public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
        return super.onJsAlert(view, url, message, result);
    }

    @Override
    public boolean onJsConfirm(WebView view, String url, String message, JsResult result) {
        return super.onJsConfirm(view, url, message, result);
    }

    @Override
    public boolean onJsPrompt(WebView view, String url, String message, String defaultValue, JsPromptResult result) {
        Uri uri = Uri.parse(message);
        ToolUtil.showLog(TAG,uri.getAuthority()+" scheme:"+uri.getScheme()+" url:"+url + " message:"+message+" defaultVaulse:"+defaultValue);
        if(Config.JS_SCHEME.equals(uri.getScheme()) &&  Config.JS_AUTHORITY.equals(uri.getAuthority())){

            HashMap<String,String> map = new HashMap<>();
            Set<String> set = uri.getQueryParameterNames();
            for(String str : set) {
                String value = uri.getQueryParameter(str);
                ToolUtil.showLog(TAG, str+" v:" + value);
                map.put(str,value);
            }


            result.confirm("妮妮是个大坏蛋");

            return true;
        }
        return super.onJsPrompt(view, url, message, defaultValue, result);
    }
}

