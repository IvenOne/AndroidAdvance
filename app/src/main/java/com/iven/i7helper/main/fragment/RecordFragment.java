package com.iven.i7helper.main.fragment;


import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.util.TimeUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.iven.i7helper.R;
import com.iven.i7helper.base.BaseFragment;
import com.iven.i7helper.base.Config;
import com.iven.i7helper.bean.MenstruationBean;
import com.iven.i7helper.main.activity.MenstruationHistory;
import com.iven.i7helper.util.ToolUtil;
import com.iven.i7helper.util.time.DateUtil;

import java.util.Calendar;
import java.util.Date;

/**
 * A simple {@link Fragment} subclass.
 */
public class RecordFragment extends BaseFragment implements View.OnClickListener{


    private TextView startTime,endTime;
    private Button commit,cancel,history;
    private EditText remarks;

    public RecordFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_record,container,false);
        startTime = (TextView) v.findViewById(R.id.menstruation_starttime);
        endTime = (TextView) v.findViewById(R.id.menstruation_endtime);
        commit = (Button) v.findViewById(R.id.menstruation_ok);
        cancel = (Button) v.findViewById(R.id.menstruation_cancel);
        history = (Button) v.findViewById(R.id.menstruation_history);
        remarks = (EditText) v.findViewById(R.id.menstruation_remarks);

        startTime.setOnClickListener(this);
        endTime.setOnClickListener(this);
        commit.setOnClickListener(this);
        cancel.setOnClickListener(this);
        history.setOnClickListener(this);

        return v;
    }

    @Override
    public void onClick(View v) {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        switch (v.getId()){
            case R.id.menstruation_cancel:
                clearData();
                break;
            case R.id.menstruation_ok:
                 saveData();
                break;
            case R.id.menstruation_history:
                Intent intent = new Intent(this.getActivity(), MenstruationHistory.class);
                startActivity(intent);

                break;
            case R.id.menstruation_starttime:
                DatePickerDialog dp = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        startTime.setText(""+year+"年"+(month+1)+"月"+dayOfMonth+"日");
                    }
                },year,month,day);
                dp.show();
                break;
            case R.id.menstruation_endtime:
                DatePickerDialog dp2 = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        endTime.setText(""+year+"年"+(month+1)+"月"+dayOfMonth+"日");
                    }
                },year,month,day);
                dp2.show();
                break;
        }
    }

    private void saveData() {
        if(startTime.getText() == null || startTime.getText().toString().isEmpty()){
            ToolUtil.showMessage(getActivity(),"开始日期没有填写");
            return;
        }
        if(endTime.getText() == null || endTime.getText().toString().isEmpty()){
            ToolUtil.showMessage(getActivity(),"结束日期没有填写");
            return;
        }
        if(DateUtil.date2TimeStamp(endTime.getText().toString(), Config.DATE_FORMAT) <= DateUtil.date2TimeStamp(startTime.getText().toString(),Config.DATE_FORMAT)){
            ToolUtil.showMessage(getActivity(),"结束日期不能小于或等于开始日期");
            return;
        }

        MenstruationBean mb = new MenstruationBean();
        mb.setStart_time(new Date(DateUtil.date2TimeStamp(startTime.getText().toString(),Config.DATE_FORMAT)));
        mb.setEnd_time(new Date(DateUtil.date2TimeStamp(endTime.getText().toString(),Config.DATE_FORMAT)));
        mb.setRemark(remarks.getText().toString());
        if(mb.save()){
            ToolUtil.showMessage(getActivity(),"保存成功");
        }else{
            ToolUtil.showMessage(getActivity(),"保存异常，请重新尝试");
        }
        clearData();


    }

    private void clearData() {
        startTime.setText("");
        endTime.setText("");
        remarks.setText("");
    }
}
