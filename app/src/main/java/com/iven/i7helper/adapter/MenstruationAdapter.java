package com.iven.i7helper.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.iven.i7helper.R;
import com.iven.i7helper.base.Config;
import com.iven.i7helper.bean.MenstruationBean;
import com.iven.i7helper.main.I7HelperApplication;
import com.iven.i7helper.util.time.DateUtil;

import java.util.List;

/**
 * Created by Iven on 2017/9/18.
 */

public class MenstruationAdapter extends RecyclerView.Adapter {
    private List<MenstruationBean> mData;
    public MenstruationAdapter(List list){
        this.mData = list;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(I7HelperApplication.getContext()).inflate(R.layout.menstruation_item_view,parent,false);
        return new MenstruationViewHolder(v);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        MenstruationBean m = mData.get(position);
        MenstruationViewHolder mv = (MenstruationViewHolder) holder;
        mv.start.setText(DateUtil.timeStamp2String(m.getStart_time().getTime(), Config.DATE_FORMAT));
        mv.end.setText(DateUtil.timeStamp2String(m.getEnd_time().getTime(), Config.DATE_FORMAT));
        if(m.getRemark() != null ) {
            mv.remarks.setText(m.getRemark());
        }
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }


    class MenstruationViewHolder extends RecyclerView.ViewHolder{
        TextView start,end,remarks;

        public MenstruationViewHolder(View itemView) {
            super(itemView);
            start = (TextView) itemView.findViewById(R.id.menstruation_item_starttime);
            end = (TextView) itemView.findViewById(R.id.menstruation_item_endtime);
            remarks = (TextView) itemView.findViewById(R.id.menstruation_item_remarks);

        }
    }
}
