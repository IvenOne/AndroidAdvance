package com.iven.i7helper.util;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by Iven on 2017/9/2.
 */

public class ToolUtil {


    private static final boolean DEVELOP_MODE = true;

    private ToolUtil(){}

    public static void showMessage(Context context,String msg){
        Toast.makeText(context,msg,Toast.LENGTH_SHORT).show();
    }

    public static void showLog(String TAG,String msg){
        if(DEVELOP_MODE){
            Log.d(TAG, "showLog: "+msg);
        }
    }


}
