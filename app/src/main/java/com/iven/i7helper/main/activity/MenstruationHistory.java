package com.iven.i7helper.main.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.iven.i7helper.R;
import com.iven.i7helper.adapter.MenstruationAdapter;
import com.iven.i7helper.bean.MenstruationBean;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;

public class MenstruationHistory extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private List<MenstruationBean> mList = new ArrayList<>();
    private TextView mTextview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menstruation_history);

        mTextview = (TextView) findViewById(R.id.activity_empty_hint);
        mRecyclerView = (RecyclerView) findViewById(R.id.activity_history_recyclerview);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    @Override
    protected void onResume() {
        init();
        super.onResume();
    }

    private void init() {
        mList = DataSupport.findAll(MenstruationBean.class);
        if(mList.isEmpty()){
            mRecyclerView.setVisibility(View.GONE);
            mTextview.setVisibility(View.VISIBLE);
        }else {
            mRecyclerView.setVisibility(View.VISIBLE);
            mTextview.setVisibility(View.GONE);
            MenstruationAdapter ma = new MenstruationAdapter(mList);
            mRecyclerView.setAdapter(ma);

        }
    }
}
