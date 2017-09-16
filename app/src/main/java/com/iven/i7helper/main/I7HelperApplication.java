package com.iven.i7helper.main;

import android.app.Application;
import android.content.Context;

/**
 * Created by Iven on 2017/9/16.
 */

public class I7HelperApplication extends Application {

    private static Context mContext;
    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
    }

    public static Context getContext(){
        return mContext;
    }
}
