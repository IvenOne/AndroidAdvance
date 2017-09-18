package com.iven.i7helper.bean;

import org.litepal.crud.DataSupport;

import java.util.Date;

/**
 * Created by Iven on 2017/9/17.
 */

public class MenstruationBean extends DataSupport {
    private int id;
    private Date start_time;
    private Date end_time;
    private String remark;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getStart_time() {
        return start_time;
    }

    public void setStart_time(Date start_time) {
        this.start_time = start_time;
    }

    public Date getEnd_time() {
        return end_time;
    }

    public void setEnd_time(Date end_time) {
        this.end_time = end_time;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
