package com.iven.i7helper.bean;

import com.iven.i7helper.R;
import com.iven.i7helper.main.I7HelperApplication;

/**
 * Created by Iven on 2017/9/12.
 */

public class PictureInfo {
    private int smallPID;
    private String time;
    private String text;

    public PictureInfo(int smallPID,String time,String text){
        this.smallPID = smallPID;
        this.time = time;
        if(text == null){
            this.text = I7HelperApplication.getContext().getResources().getString(R.string.memory_item_title);
        }else{
            this.text = text;
        }
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getSmallPID() {
        return smallPID;
    }

    public void setSmallPID(int smallPID) {
        this.smallPID = smallPID;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
