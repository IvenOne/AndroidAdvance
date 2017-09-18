package com.iven.i7helper.main;

import android.app.Application;
import android.content.Context;

import org.litepal.LitePalApplication;
import org.litepal.tablemanager.Connector;

/**
 * Created by Iven on 2017/9/16.
 */

public class I7HelperApplication extends Application {

    private static Context mContext;
    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
        LitePalApplication.initialize(mContext);
        Connector.getDatabase();
    }

    public static Context getContext(){
        return mContext;
    }
}
