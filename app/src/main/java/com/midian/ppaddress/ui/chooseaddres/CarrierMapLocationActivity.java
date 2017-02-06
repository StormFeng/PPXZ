package com.midian.ppaddress.ui.chooseaddres;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.model.LatLng;
import com.midian.ppaddress.R;
import com.midian.ppaddress.api.PpApiClient;
import com.midian.ppaddress.bean.BusinessCarrierDetailMapTrafficsBean;
import com.midian.ppaddress.bean.BusinessCarrierDetailMapTrafficsBean.TrafficsData;
import com.midian.ppaddress.utils.AppUtil;

import java.util.ArrayList;
import java.util.List;

import midian.baselib.app.AppContext;
import midian.baselib.base.BaseFragmentActivity;
import midian.baselib.bean.NetResult;
import midian.baselib.utils.FDDataUtils;
import midian.baselib.utils.UIHelper;
import midian.baselib.widget.BaseLibTopbarView;

/**
 * 载体详情--载体交通区位地图
 * 8.13接口
 * Created by chu on 2016/5/28.
 */
public class CarrierMapLocationActivity extends BaseFragmentActivity implements  View.OnClickListener {
    private BaseLibTopbarView topbar;
    private TextView loc_tv;
    private String carrierid,title;
    private TrafficsData trafficsData;
    private float zoomLevel = 13.0f;


    /**
     * MapView 是地图主控件
     */
    private MapView mMapView;
    private BaiduMap mBaiduMap;
    BitmapDescriptor bdCarrier = BitmapDescriptorFactory
            .fromResource(R.drawable.carrier_map_location);
    BitmapDescriptor bdAirport = BitmapDescriptorFactory
            .fromResource(R.drawable.carrier_map_airport);
    BitmapDescriptor bdPort = BitmapDescriptorFactory
            .fromResource(R.drawable.carrier_map_port);
    BitmapDescriptor bdRailwayStation = BitmapDescriptorFactory
            .fromResource(R.drawable.carrier_map_railway_station);
    BitmapDescriptor bdHighway = BitmapDescriptorFactory
            .fromResource(R.drawable.carrier_map_highway);
    BitmapDescriptor bdMetro = BitmapDescriptorFactory
            .fromResource(R.drawable.icon_carrier_map_filter_metro);//icon_carrier_map_filter_metro
    BitmapDescriptor bdBusStation = BitmapDescriptorFactory
            .fromResource(R.drawable.icon_carrier_map_gongjiao);


    // 定位相关
    LocationClient mLocClient;
    public MyLocationListenner myListener = new MyLocationListenner();
    boolean isFirstLoc = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carrier_map_location);
        carrierid = mBundle.getString("carrierid");
        title = mBundle.getString("title");
        topbar = findView(R.id.topbar);
        loc_tv = findView(R.id.loc_tv);
        topbar.setTitle(title).setLeftImageButton(R.drawable.icon_back, UIHelper.finish(_activity));
        loc_tv = findView(R.id.loc_tv);//定位按钮
        loc_tv.setOnClickListener(this);

        ImageView airport_iv = (ImageView)findViewById(R.id.filter_airport);
        ImageView port_iv = (ImageView)findViewById(R.id.filter_port);
        ImageView railway_station_iv = (ImageView)findViewById(R.id.filter_railway_station);
        ImageView highway_iv = (ImageView)findViewById(R.id.filter_highway);
        ImageView metro_iv = (ImageView)findViewById(R.id.filter_metro);
        ImageView bus_station_iv = (ImageView)findViewById(R.id.filter_bus_station);

        airport_iv.setOnClickListener(this);
        port_iv.setOnClickListener(this);
        railway_station_iv.setOnClickListener(this);
        highway_iv.setOnClickListener(this);
        metro_iv.setOnClickListener(this);
        bus_station_iv.setOnClickListener(this);

        mMapView = (MapView) findViewById(R.id.bmapView);
        mMapView.showZoomControls(false);
        mBaiduMap = mMapView.getMap();
        MapStatusUpdate msu = MapStatusUpdateFactory.zoomTo(zoomLevel);
        mBaiduMap.setMapStatus(msu);
        mBaiduMap.setMaxAndMinZoomLevel(14,3);

        PpApiClient apiClient = new PpApiClient(AppContext.context());
        apiClient.businessCarrierDetailMapTraffics(carrierid,this);//交通区位接口
    }
    @Override
    public int getFragmentContentId() {
        return R.id.content_fl;
    }




    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.loc_tv:
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
            case R.id.filter_airport://机场
                initOverlay(getCurrCarrierBeans("1"));
                break;
            case R.id.filter_port://港口
                initOverlay(getCurrCarrierBeans("2"));
                break;
            case R.id.filter_railway_station://火车站
                initOverlay(getCurrCarrierBeans("3"));
                break;
            case R.id.filter_highway://高速路口
                initOverlay(getCurrCarrierBeans("4"));
                break;
            case R.id.filter_metro://地铁站
                initOverlay(getCurrCarrierBeans("5"));
                break;
            case R.id.filter_bus_station://公交站
                initOverlay(getCurrCarrierBeans("6"));
                break;
        }

    }


    ArrayList<CarrierBean> currCarrierBeans;

    private  ArrayList<CarrierBean> getCurrCarrierBeans(String subType){

        if(currCarrierBeans==null){
            currCarrierBeans = new ArrayList<>();
        }else{
            currCarrierBeans.clear();
        }

        if(TextUtils.isEmpty(subType)){//为空时返回全部
            currCarrierBeans.addAll(carrierBeans);
            return currCarrierBeans;
        }

        for(CarrierBean carrerBean:carrierBeans){
            if(carrerBean.type==1){//载体
                currCarrierBeans.add(carrerBean);
            }else if(carrerBean.type==2){//交通
                if(carrerBean.subType.equals(subType)){
                    currCarrierBeans.add(carrerBean);
                }
            }
        }

        return currCarrierBeans;
    }
    ArrayList<CarrierBean> carrierBeans;

    @Override
    public void onApiStart(String tag) {
        super.onApiStart(tag);
        showLoadingDlg("请求中...", false);
    }

    @Override
    public void onApiSuccess(NetResult res, String tag) {
        super.onApiSuccess(res, tag);
        hideLoadingDlg();
        if (res.isOK()) {
            BusinessCarrierDetailMapTrafficsBean bean = (BusinessCarrierDetailMapTrafficsBean) res;
            trafficsData = bean.getData();

            BusinessCarrierDetailMapTrafficsBean.Carrier carrier = trafficsData.getCarrier();
            List<BusinessCarrierDetailMapTrafficsBean.Traffics> trafficsList = trafficsData.getTraffics();
            carrierBeans = new ArrayList<>();

            //载体
            CarrierBean carrierBean = new CarrierBean();
            carrierBean.lat = FDDataUtils.getDouble(carrier.getLat());
            carrierBean.lng = FDDataUtils.getDouble(carrier.getLng());
            carrierBean.type = 1;
            carrierBean.subType = "1";
            carrierBean.bitmapDescriptor = bdCarrier;
            carrierBeans.add(carrierBean);

            //交通
           for(BusinessCarrierDetailMapTrafficsBean.Traffics traffics:trafficsList){
               CarrierBean carrierBean1 = new CarrierBean();
               carrierBean1.lat = FDDataUtils.getDouble(traffics.getLat());
               carrierBean1.lng = FDDataUtils.getDouble(traffics.getLng());
               carrierBean1.type = 2;
               carrierBean1.subType = traffics.getTrafficstypeid();
               if("1".equals(carrierBean1.subType)){
                  carrierBean1.bitmapDescriptor = bdAirport;
               }else if("2".equals(carrierBean1.subType)){
                   carrierBean1.bitmapDescriptor = bdPort;
               }else if("3".equals(carrierBean1.subType)){
                   carrierBean1.bitmapDescriptor = bdRailwayStation;
               }else if("4".equals(carrierBean1.subType)){
                   carrierBean1.bitmapDescriptor = bdHighway;
               }else if("5".equals(carrierBean1.subType)){
                   carrierBean1.bitmapDescriptor = bdMetro;
               }else if("6".equals(carrierBean1.subType)){
                   carrierBean1.bitmapDescriptor = bdBusStation;
               }
               carrierBeans.add(carrierBean1);
           }
            initOverlay(carrierBeans);
        } else {
            ac.handleErrorCode(_activity, res.errorcode);
        }
    }






    @Override
    public void onApiFailure(Throwable t, int errorNo, String strMsg, String tag) {
        super.onApiFailure(t, errorNo, strMsg, tag);
        hideLoadingDlg();
    }

    boolean isFirstUpdate = true;

    public void initOverlay(List<CarrierBean> carrierBeans){
        //先清空所有Overlay
        mBaiduMap.clear();

        int count = carrierBeans.size();
        for(int i=0;i<count;i++){
            CarrierBean carrierBean = carrierBeans.get(i);
            LatLng ll = new LatLng(carrierBean.lat, carrierBean.lng);
            MarkerOptions markerOptions = new MarkerOptions().position(ll).icon(carrierBean.bitmapDescriptor);
            mBaiduMap.addOverlay(markerOptions);
        }

        MapStatusUpdate u =null;
        if(isFirstUpdate){//第一次才以载体为中心显示
            isFirstUpdate =false;
            u  = MapStatusUpdateFactory
                    .newLatLng(new LatLng(carrierBeans.get(0).lat, carrierBeans.get(0).lng));
        }else {
            u = MapStatusUpdateFactory.newMapStatus(null);
        }
        mBaiduMap.setMapStatus(u);
        mBaiduMap.setOnMarkerDragListener(new BaiduMap.OnMarkerDragListener() {
            public void onMarkerDrag(Marker marker) {
            }

            public void onMarkerDragEnd(Marker marker) {
                Toast.makeText(
                        CarrierMapLocationActivity.this,
                        "拖拽结束，新位置：" + marker.getPosition().latitude + ", "
                                + marker.getPosition().longitude,
                        Toast.LENGTH_LONG).show();
            }

            public void onMarkerDragStart(Marker marker) {
            }
        });
    }

    /**
     * 清除所有Overlay
     *
     * @param view
     */
    public void clearOverlay(View view) {
        mBaiduMap.clear();
    }

    /**
     * 重新添加Overlay
     *
     * @param view
     */
    public void resetOverlay(View view) {
        clearOverlay(null);
        initOverlay(carrierBeans);
    }

    @Override
    protected void onPause() {
        // MapView的生命周期与Activity同步，当activity挂起时需调用MapView.onPause()
        mMapView.onPause();
        super.onPause();
    }

    @Override
    protected void onResume() {
        // MapView的生命周期与Activity同步，当activity恢复时需调用MapView.onResume()
        mMapView.onResume();
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        // MapView的生命周期与Activity同步，当activity销毁时需调用MapView.destroy()
        mMapView.onDestroy();
        super.onDestroy();
        // 回收 bitmap 资源
        bdCarrier.recycle();
        bdAirport.recycle();
        bdPort.recycle();
        bdMetro.recycle();
        bdHighway.recycle();
        bdBusStation.recycle();
        bdRailwayStation.recycle();
    }


    public class CarrierBean {

        public double lng;
        public double lat;
        public int type;//1表示载体,2表示交通
        public String subType;//交通或载体的子类型
        public BitmapDescriptor bitmapDescriptor;
    }

    /**
     * 定位SDK监听函数
     */
    public class MyLocationListenner implements BDLocationListener {

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
                LatLng ll = new LatLng(location.getLatitude(),
                        location.getLongitude());
                MapStatus.Builder builder = new MapStatus.Builder();
                builder.target(ll);
                mBaiduMap.animateMapStatus(MapStatusUpdateFactory.newMapStatus(builder.build()));
            }
        }

        public void onReceivePoi(BDLocation poiLocation) {
        }
    }

}
