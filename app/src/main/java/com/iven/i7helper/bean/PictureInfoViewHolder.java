package com.iven.i7helper.bean;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.iven.i7helper.R;

/**
 * Created by Iven on 2017/9/11.
 */

public class PictureInfoViewHolder extends RecyclerView.ViewHolder {

    public ImageView imageView;
    public TextView tv_time;
    public TextView tv_text;


    public PictureInfoViewHolder(View itemView) {
        super(itemView);
        tv_time = (TextView) itemView.findViewById(R.id.item_timeline_time);
        imageView = (ImageView) itemView.findViewById(R.id.memory_item_smallP);
        tv_text = (TextView) itemView.findViewById(R.id.memory_item_content);

    }
}
