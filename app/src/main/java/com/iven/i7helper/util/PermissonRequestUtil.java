package com.iven.i7helper.util;

import android.content.pm.PackageManager;
import android.support.v4.content.ContextCompat;

import com.iven.i7helper.main.I7HelperApplication;

/**
 * Created by Iven on 2017/9/18.
 */

public class PermissonRequestUtil {
    private PermissonRequestUtil(){}

    public static boolean check(String permisson){
        if(ContextCompat.checkSelfPermission(I7HelperApplication.getContext(), permisson)
                != PackageManager.PERMISSION_GRANTED){
            return false;
        }
        return true;
    }


}
