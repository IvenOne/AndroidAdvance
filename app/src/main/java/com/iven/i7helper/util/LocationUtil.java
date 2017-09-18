package com.iven.i7helper.util;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;

import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.iven.i7helper.bean.LocationBean;
import com.iven.i7helper.main.I7HelperApplication;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Iven on 2017/9/17.
 */

public class LocationUtil {

    public static LocationUtil mLocationUtil;
    private LocationClient mLocationClient;
    private LocationBean locationInfo = new LocationBean();
    private LocationClientOption mLocationClientOp = new LocationClientOption();


    public LocationUtil() {
        mLocationClient = new LocationClient(I7HelperApplication.getContext());
        mLocationClientOp.setIsNeedAddress(true);
        mLocationClientOp.setScanSpan(10*60*1000);
        mLocationClient.registerLocationListener(new BDAbstractLocationListener() {
            @Override
            public void onReceiveLocation(BDLocation bdLocation) {
                locationInfo.setLatitude(bdLocation.getLatitude());
                locationInfo.setLongitude(bdLocation.getLongitude());
                locationInfo.setCurrent(new StringBuilder().append(bdLocation.getCountry()).append(" " + bdLocation.getProvince() + " ")
                        .append(bdLocation.getCity() + " ").append(bdLocation.getDistrict() + " ").append(bdLocation.getStreet() + " ")
                        .append(bdLocation.getStreetNumber() + "号").toString());
                if (bdLocation.getLocType() == BDLocation.TypeGpsLocation) {
                    locationInfo.setLoc_type("GPS");
                } else if (bdLocation.getLocType() == BDLocation.TypeNetWorkLocation) {
                    locationInfo.setLoc_type("网络");
                } else {
                    locationInfo.setLoc_type("未知");
                }
                locationInfo.setCurrent_date(new Date());
//                ToolUtil.showMessage(I7HelperApplication.getContext(),locationInfo.getCurrent()+"定位方式："+locationInfo.getLoc_type());
                locationInfo.save();
            }
        });

    }

    public static LocationUtil getLocationUtil(){
        if(mLocationUtil == null){
            mLocationUtil = new LocationUtil();
        }
        return mLocationUtil;
    }

    public LocationClientOption getmLocationClientOp() {
        return mLocationClientOp;
    }

    public void setmLocationClientOp(LocationClientOption mLocationClientOp) {
        this.mLocationClientOp = mLocationClientOp;
    }

    public void requestLocation(){
        mLocationClient.setLocOption(mLocationClientOp);
        mLocationClient.start();
    }

    public LocationClient getmLocationClient() {
        return mLocationClient;
    }


    /**************由此定位*************/
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        LocationUtil lu = LocationUtil.getLocationUtil();
        switch (requestCode){
            case 1:
                if(grantResults.length > 0){
                    for(int i : grantResults){
                        if(i != PackageManager.PERMISSION_GRANTED){
                            return;
                        }
                    }
                    lu.requestLocation();
                }
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
            ActivityCompat.requestPermissions((Activity) I7HelperApplication.getContext(), peli, 1);
        }
    }

        /**************由此定位*************/
}
