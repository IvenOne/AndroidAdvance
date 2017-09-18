package com.iven.i7helper.base;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.widget.ImageView;

/**
 * Created by Iven on 2017/9/3.
 */

public class Config {
    private Config(){}

    /**
     * android 交互匹配格式
     */
    public final static String JS_SCHEME = "js";
    public final static String JS_AUTHORITY = "i7helper.scan";

    /**
     * CircleView Use
     */
    public final static int DEFAULT_BORDER_COLOR = Color.BLACK;
    public static final int DEFAULT_BORDER_WIDTH = 0;
    public static final int DEFAULT_FILL_COLOR = Color.TRANSPARENT;
    public static final boolean DEFAULT_BOOL_FILL = false;

    public static final ImageView.ScaleType DEFAULT_SCALETYPE = ImageView.ScaleType.CENTER_CROP;
    public static final Bitmap.Config BITMAP_CONFIG = Bitmap.Config.ARGB_8888;
    public static final int COLORDRAWABLE_DIMENSION = 2;



    public static final String DATE_FORMAT = "yyyy年MM月dd日";





}
