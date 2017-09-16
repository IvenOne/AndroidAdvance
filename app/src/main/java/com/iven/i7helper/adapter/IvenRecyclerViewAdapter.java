package com.iven.i7helper.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.iven.i7helper.R;
import com.iven.i7helper.bean.PictureInfo;
import com.iven.i7helper.bean.PictureInfoViewHolder;
import com.iven.i7helper.main.I7HelperApplication;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Iven on 2017/9/11.
 */

public class IvenRecyclerViewAdapter extends RecyclerView.Adapter {

    private List<PictureInfo> mDataList = new ArrayList<>();

    public IvenRecyclerViewAdapter(List mDataList){
        this.mDataList = mDataList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_item_view,parent,false);
        return new PictureInfoViewHolder(v);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        PictureInfoViewHolder p = (PictureInfoViewHolder) holder;
        PictureInfo pictureInfo = mDataList.get(position);
        p.tv_text.setText(pictureInfo.getText());
        p.tv_time.setText(pictureInfo.getTime());
        Glide.with(I7HelperApplication.getContext()).load(pictureInfo.getSmallPID()).into(p.imageView);
    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }
}
