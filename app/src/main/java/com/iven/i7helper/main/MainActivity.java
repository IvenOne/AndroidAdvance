package com.iven.i7helper.main;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.iven.i7helper.R;
import com.iven.i7helper.base.BaseActivity;
import com.iven.i7helper.main.fragment.HappyFragment;
import com.iven.i7helper.main.fragment.MemoryFragment;
import com.iven.i7helper.main.fragment.RecordFragment;
import com.iven.i7helper.util.LocationUtil;
import com.iven.i7helper.util.PermissonRequestUtil;
import com.iven.i7helper.util.ToolUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends BaseActivity implements View.OnClickListener{


    private ViewPager vp;
    private Map<Integer,Fragment> map = new HashMap<>();
    private MemoryFragment memoryFragment = new MemoryFragment();
    private HappyFragment happyFragment = new HappyFragment();
    private RecordFragment recordFragment = new RecordFragment();

    private ImageView memory_img,happy_img,record_img;
    private LinearLayout memory_lay,happy_lay,record_lay;

    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initMain();
    }

    private void initMain() {

        map.put(2, memoryFragment);
        map.put(0,happyFragment);
        map.put(1,recordFragment);

        vp = (ViewPager) findViewById(R.id.main_viewpage);

        tv = (TextView) findViewById(R.id.activity_main_head_title);

        memory_img = (ImageView) findViewById(R.id.main_bottom_img_memory);
        happy_img = (ImageView) findViewById(R.id.main_bottom_img_happy);
        record_img = (ImageView) findViewById(R.id.main_bottom_img_record);


        memory_lay = (LinearLayout) findViewById(R.id.main_bottom_id_memory);
        happy_lay = (LinearLayout) findViewById(R.id.main_bottom_id_happy);
        record_lay = (LinearLayout) findViewById(R.id.main_bottom_id_record);
        memory_lay.setOnClickListener(this);
        happy_lay.setOnClickListener(this);
        record_lay.setOnClickListener(this);


        vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                Log.d("test","postion is "+position+" positionOffset is "+ positionOffset+" positionOffsetPixels:"+positionOffsetPixels);

            }

            @Override
            public void onPageSelected(int position) {
                switch (position){
                    case 0:
                        happy_img.setImageResource(R.drawable.happy_selected);
                        memory_img.setImageResource(R.drawable.memory_selected_no);
                        record_img.setImageResource(R.drawable.record_selected_no);
                        tv.setText(getString(R.string.main_happy));
                        break;
                    case 1:
                        happy_img.setImageResource(R.drawable.happy_selected_no);
                        memory_img.setImageResource(R.drawable.memory_selected_no);
                        record_img.setImageResource(R.drawable.record_selected);
                        tv.setText(getString(R.string.main_menses));
                        break;

                    case 2:
                        happy_img.setImageResource(R.drawable.happy_selected_no);
                        memory_img.setImageResource(R.drawable.memory_selected);
                        record_img.setImageResource(R.drawable.record_selected_no);
                        tv.setText(getString(R.string.main_memory));
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                Log.d("test","state:" + state);

            }
        });
        vp.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return map.get(position);
            }

            @Override
            public int getCount() {
                return map.size();
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.main_bottom_id_memory:
                vp.setCurrentItem(2,true);
                break;
            case R.id.main_bottom_id_happy:
                vp.setCurrentItem(0,false);
                break;
            case R.id.main_bottom_id_record:
                vp.setCurrentItem(1);
                break;
        }
    }

    /**
     * For interacting with main activity in textview ,return the head title.
     * @return TextView
     */
    public TextView getHeadTextView(){
        return tv;
    }

    @Override
    protected void onResume() {
        requestPermission();
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        LocationUtil lu = LocationUtil.getLocationUtil();
        lu.getmLocationClient().stop();
        super.onDestroy();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        LocationUtil lu = LocationUtil.getLocationUtil();
        switch (requestCode){
            case 1:
                if(grantResults.length > 0){
                    for(int i : grantResults){
                        if(i != PackageManager.PERMISSION_GRANTED){
                            ToolUtil.showMessage(this,"不给权限会导致记录不准确");
                            return;
                        }
                    }
                }
                lu.requestLocation();
                break;
            default:
        }
    }

    private void requestPermission() {
        LocationUtil lu = LocationUtil.getLocationUtil();
        List<String> pl = new ArrayList<>();
        if (!PermissonRequestUtil.check(Manifest.permission.ACCESS_FINE_LOCATION)) {
            pl.add(Manifest.permission.ACCESS_FINE_LOCATION);
        }
        if (!PermissonRequestUtil.check(Manifest.permission.READ_PHONE_STATE)) {
            pl.add(Manifest.permission.READ_PHONE_STATE);
        }
        if (!PermissonRequestUtil.check(Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            pl.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }
        if (pl.isEmpty()) {
            lu.requestLocation();
        } else {
            String peli[] = (String[]) pl.toArray();
            ActivityCompat.requestPermissions(this, peli, 1);
        }
    }
}
