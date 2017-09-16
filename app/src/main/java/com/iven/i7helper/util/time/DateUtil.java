package com.iven.i7helper.util.time;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Iven on 2017/9/16.
 */

public final class DateUtil {
    private DateUtil(){}

    public static String timeStamp2String(long time,String dateFormat){
        String res = null;
        if(time == 0) {
            return "";
        }
        if (dateFormat == null || dateFormat.isEmpty()) {
            dateFormat = "yyyy-MM-dd \nHH:mm:ss";
        }
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        res = sdf.format(new Date(time));
        return res;
    }

    public static long date2TimeStamp(String date,String format){

        try {
            SimpleDateFormat sf = new SimpleDateFormat(format);
            return sf.parse(date).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
