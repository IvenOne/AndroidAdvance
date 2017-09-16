package com.iven.i7helper.main.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.iven.i7helper.R;
import com.iven.i7helper.adapter.IvenRecyclerViewAdapter;
import com.iven.i7helper.base.BaseFragment;
import com.iven.i7helper.bean.PictureInfo;
import com.iven.i7helper.bean.PictureInfoViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class MemoryFragment extends BaseFragment {

    private RecyclerView mRecyclerview;
    private List<PictureInfo> mList = new ArrayList<>();
    private IvenRecyclerViewAdapter mIvenRecyclerViewAdapter;


    public MemoryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_memory,container,false);
        mRecyclerview = (RecyclerView) v.findViewById(R.id.fragment_memory_recyclerview);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerview.setLayoutManager(llm);
//        mRecyclerview.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
        mRecyclerview.setHasFixedSize(true);
        mRecyclerview.setItemAnimator(new DefaultItemAnimator());
        initData();
       return v;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    private void initData() {

        PictureInfo p1 = new PictureInfo(R.mipmap.meal1,"2016-04-02\n 11:20","这是我们在认识后比较腼腆一次吃饭，好害羞(✿◡‿◡)");
        PictureInfo p2 = new PictureInfo(R.mipmap.meal2,"2016-04-02\n 11:23","大脸猫都不让我拍脸，O(∩_∩)O哈哈~");
        PictureInfo p3 = new PictureInfo(R.mipmap.my1,"2015-02-15 \n 14:33","春天万物复苏的季节，偶见了一个盛开的蒲公英,希望回临沂会有一个好兆头。。。");
        PictureInfo p4 = new PictureInfo(R.mipmap.my2,"2016-02-17\n 13:00","终于遇见了心目中的女神，然后努力找到了工作，工作的第一项任务是放一张自拍给行政，哈哈");
        PictureInfo p5 = new PictureInfo(R.mipmap.my3,"2016-01-18\n 12:00","刚从北京回临沂，会不会遇见自己的女神呢？");
        PictureInfo p6 = new PictureInfo(R.mipmap.our1,"2016-05-20\n 19:00","终于有机会一起去玩耍了。。。");
        PictureInfo p7 = new PictureInfo(R.mipmap.our2,"2016-05-20 \n 19:23","两人的世界里，我仿佛看到了和她在一起未来的憧憬。。。");
        PictureInfo p8 = new PictureInfo(R.mipmap.our3,"2016-08-11\n 14:20","记得今天妹妹过生日，带她去蒙山玩了一圈，女神医院有任务走不开，刻下对她的眷恋，么么哒...");
        PictureInfo p9 = new PictureInfo(R.mipmap.our3,"2016-08-11\n 14:25","来张远景的瞧一瞧，^_^");
        PictureInfo p10 = new PictureInfo(R.mipmap.our20161002s,"2016-10-02\n 14:25","带老婆第一次去见公公，哈哈，（后面是老妈的菜园）来张合影。。。");
        PictureInfo p11 = new PictureInfo(R.mipmap.our20161004,"2016-10-04\n 13:25","在青岛玩了几天该回去了，看见了久违的大海，胶州湾大桥，来和女神留个合影");

        mList.add(p3);
        mList.add(p5);
        mList.add(p4);
        mList.add(p1);
        mList.add(p2);
        mList.add(p6);
        mList.add(p7);
        mList.add(p8);
        mList.add(p9);
        mList.add(p10);
        mList.add(p11);


        mIvenRecyclerViewAdapter = new IvenRecyclerViewAdapter(mList);
        mRecyclerview.setAdapter(mIvenRecyclerViewAdapter);
    }
}
