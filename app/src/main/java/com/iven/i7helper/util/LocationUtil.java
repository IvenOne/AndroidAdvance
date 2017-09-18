package com.iven.i7helper.util;

import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.iven.i7helper.bean.LocationBean;
import com.iven.i7helper.main.I7HelperApplication;

/**
 * Created by Iven on 2017/9/17.
 */

public class LocationUtil {

    private LocationClient mLocationClient;
    private LocationBean locationInfo = new LocationBean();
    private LocationClientOption mLocationClientOp = new LocationClientOption();


    public LocationUtil() {
        mLocationClient = new LocationClient(I7HelperApplication.getContext());
        mLocationClientOp.setIsNeedAddress(true);
        mLocationClientOp.setScanSpan(3600*1000);
        mLocationClient.registerLocationListener(new BDAbstractLocationListener() {
            @Override
            public void onReceiveLocation(BDLocation bdLocation) {
                locationInfo.setLatitude(bdLocation.getLatitude());
                locationInfo.setLongitude(bdLocation.getLongitude());
                locationInfo.setCurrent(new StringBuilder().append(bdLocation.getCountry()).append(" " + bdLocation.getProvince() + "省 ")
                        .append(bdLocation.getCity() + "市 ").append(bdLocation.getDistrict() + "区 ").append(bdLocation.getStreet() + "街道 ")
                        .append(bdLocation.getStreetNumber() + "号").toString());
                if (bdLocation.getLocType() == BDLocation.TypeGpsLocation) {
                    locationInfo.setLoc_type("GPS");
                } else if (bdLocation.getLocType() == BDLocation.TypeNetWorkLocation) {
                    locationInfo.setLoc_type("网络");
                } else {
                    locationInfo.setLoc_type("未知");
                }
                locationInfo.save();
            }
        });

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


}
