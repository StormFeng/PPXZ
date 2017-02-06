package com.midian.ppaddress.ui.homepage;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.map.UiSettings;
import com.baidu.mapapi.model.LatLng;
import com.midian.maplib.MapFragment;
import com.midian.maplib.map.ItemBean;
import com.midian.ppaddress.R;
import com.midian.ppaddress.bean.BusinessAppsearchMapcarriersBean;
import com.midian.ppaddress.bean.BusinessAppsearchSoMapBean;
import com.midian.ppaddress.bean.BusinessAppsearchSoMapBean.Data;
import com.midian.ppaddress.ui.chooseaddres.CarrierMapLocationActivity;
import com.midian.ppaddress.utils.AppUtil;
import com.midian.ppaddress.utils.DialogCarrierList;

import java.util.ArrayList;
import java.util.List;

import midian.baselib.base.BaseActivity;
import midian.baselib.base.BaseFragmentActivity;
import midian.baselib.bean.NetResult;
import midian.baselib.utils.FDDataUtils;
import midian.baselib.utils.Func;
import midian.baselib.utils.UIHelper;
import midian.baselib.widget.BaseLibTopbarView;

/**
 * 选址地图模式
 * Created by chu on 2016/3/17.
 */
public class MapActivity extends BaseActivity implements  View.OnClickListener, TextView.OnEditorActionListener {
    private BaseLibTopbarView topbar;
    private EditText input_et;
    private TextView loc_tv;
    // 当前数据的缩放等级
    private float cuttentZoom = 11;//地图初始缩放等级为市级11，点击后自动放大到区县级设为13

    private String carrierType = null;
    private String labelids = null;//标签id集合
    private String saleRental = null;//租售类型
    private String startPrice = null;
    private String endPrice = null;
    private String startArea = null;
    private String endArea = null;
    private String cityId = null;
    private String countyid= null;//区县id
    private String keyword = null;//关键字
    private String left_top_lon,  left_top_lat,  right_bottom_lon, right_bottom_lat;

    //地图相关
    private MapView mMapView;
    private BaiduMap mBaiduMap;
    //定位相关
    private LocationClient mLocClient;
    public MapLocationListenner myListener = new MapLocationListenner();
    boolean isFirstLoc = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.map_activity);
        topbar = findView(R.id.topbar);
        topbar.setMode(BaseLibTopbarView.MODE_WITH_INPUT).setLeftImageButton(R.drawable.icon_back, UIHelper.finish(_activity));
        topbar.getInput_et().setHint("大家都在搜:办公楼");
        topbar.getRight_tv().setCompoundDrawablePadding(Func.Dp2Px(_activity, 6));
        topbar.setRightText("筛选", this).showRight_tv();
        topbar.getRight_tv().setCompoundDrawablesWithIntrinsicBounds(null, null, getResources().getDrawable(R.drawable.icon_address_pull_down), null);
        input_et = topbar.getInput_et();
        input_et.setImeOptions(EditorInfo.IME_ACTION_SEARCH);
        input_et.setOnEditorActionListener(this);
        loc_tv = findView(R.id.loc_tv);//定位按钮
        loc_tv.setOnClickListener(this);

        mMapView = findView(R.id.bmapView);
        mMapView.showZoomControls(false);
        mBaiduMap = mMapView.getMap();
//        MapStatusUpdate mapStatusUpdate = MapStatusUpdateFactory.zoomTo(cuttentZoom);
//        mBaiduMap.setMapStatus(mapStatusUpdate);
        Log.d("=-=-=-=-", "onApiSuccess: 地图初始化城市经纬度="+ac.lon+"======="+ac.lat);
        UiSettings mapui = mBaiduMap.getUiSettings();
        mapui.setCompassEnabled(false);
        mapui.setOverlookingGesturesEnabled(false);
        mapui.setZoomGesturesEnabled(true);
        MapStatus ms = new MapStatus.Builder().overlook(0).zoom(cuttentZoom).build();
        MapStatusUpdate u1 = MapStatusUpdateFactory.newMapStatus(ms);
        mBaiduMap.setMapStatus(u1);

        MyLocationData locData = new MyLocationData.Builder()
                .direction(100).latitude(FDDataUtils.getFloat(ac.lat))
                .longitude(FDDataUtils.getFloat(ac.lon)).build();
        mBaiduMap.setMyLocationData(locData);
    }



    @Override
    public void onApiSuccess(NetResult res, String tag) {
        super.onApiSuccess(res, tag);
        hideLoadingDlg();
        if (res.isOK()) {

        } else {
            ac.handleErrorCode(_activity, res.errorcode);
        }
    }

    @Override
    public void onApiStart(String tag) {
        super.onApiStart(tag);
        showLoadingDlg();
    }

    @Override
    public void onApiFailure(Throwable t, int errorNo, String strMsg, String tag) {
        super.onApiFailure(t, errorNo, strMsg, tag);
        hideLoadingDlg();
        if ("网络异常".equals(strMsg)) {
            finish();
        }
    }







    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.right_tv:
                Bundle bundle = new Bundle();
                bundle.putString("type", "2");//1为首页载体类型筛选 2为地图筛选
                UIHelper.jumpForResult(_activity, SearchConditionActivity.class, bundle,10086);//筛选条件页
                _activity.overridePendingTransition(R.anim.enter, 0);//从下至上打开页面的动画
                break;
            case R.id.loc_tv://定位
                isFirstLoc = true;
                // 开启定位图层
                mBaiduMap.setMyLocationEnabled(true);
                // 定位初始化
                mLocClient = new LocationClient(this);
                mLocClient.registerLocationListener(myListener);
                LocationClientOption option = new LocationClientOption();
                option.setOpenGps(true); // 打开gps
                option.setCoorType("bd09ll"); // 设置坐标类型
                option.setScanSpan(1000);
                mLocClient.setLocOption(option);
                mLocClient.start();
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        System.out.println("onActivityResult=" + requestCode);
        if (resultCode == RESULT_OK) {
            if (requestCode == 10086) {// TODO: 2016/6/2 接收从搜索标签页返回的搜索数据
                System.out.println("标签页返回数据carrierTypeId=" + data.getStringExtra("carrierTypeId") + "lableids=" + data.getStringExtra("lableids"));
                carrierType = data.getStringExtra("carrierTypeId");
                labelids = data.getStringExtra("lableids");
                saleRental = data.getStringExtra("saleRental");
                startPrice = data.getStringExtra("startPrice");
                endPrice = data.getStringExtra("endPrice");
                startArea = data.getStringExtra("startArea");
                endArea = data.getStringExtra("endArea");
                if (cuttentZoom >= 14) {//取区级数据
                    AppUtil.getPpApiClient(ac).businessAppsearchSomap(carrierType, labelids, saleRental, null, null, null, null, ac.city_id, null, keyword, left_top_lon, left_top_lat
                            , right_bottom_lon, right_bottom_lat, 1 + "", this);
                }
            }
        }
    }

    /**
     * 监听editText
     *
     * @param textView
     * @param actionId
     * @param keyEvent
     * @return
     */
    @Override
    public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
        if (cuttentZoom <= 12) {
            UIHelper.t(_activity, "请放大地图进行搜索载体");
            return false;
        } else {
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {

            }
        }
        return false;
    }

    /**
     * 定位SDK监听函数
     */
    public class MapLocationListenner implements BDLocationListener {

        @Override
        public void onReceiveLocation(BDLocation location) {
            // map view 销毁后不在处理新接收的位置
            if (location == null || mMapView == null) {
                return;
            }
            MyLocationData locData = new MyLocationData.Builder()
                    .accuracy(location.getRadius())
                    // 此处设置开发者获取到的方向信息，顺时针0-360
                    .direction(100).latitude(location.getLatitude())
                    .longitude(location.getLongitude()).build();
            mBaiduMap.setMyLocationData(locData);
            if (isFirstLoc) {
                isFirstLoc = false;
                LatLng ll = new LatLng(location.getLatitude(), location.getLongitude());
                MapStatus.Builder builder = new MapStatus.Builder();
                builder.target(ll);
                mBaiduMap.animateMapStatus(MapStatusUpdateFactory.newMapStatus(builder.build()));
            }
        }

        public void onReceivePoi(BDLocation poiLocation) {
        }
    }
}
