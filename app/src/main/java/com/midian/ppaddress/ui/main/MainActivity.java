package com.midian.ppaddress.ui.main;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.midian.baidupush.DeviceMessage;
import com.midian.maplib.LocationUtil;
import com.midian.ppaddress.R;
import com.midian.ppaddress.bean.BusinessLocationCityReleaseListBean;
import com.midian.ppaddress.bean.MessageCountInnerMessageBean;
import com.midian.ppaddress.bean.PersonalIndexBean;
import com.midian.ppaddress.utils.AppUtil;

import midian.baselib.app.AppManager;
import midian.baselib.base.BaseFragmentActivity;
import midian.baselib.bean.NetResult;
import midian.baselib.utils.UIHelper;
import midian.baselib.widget.BaseLibMainFooterView;

/**
 * 首页
 */
public class MainActivity extends BaseFragmentActivity implements
        BaseLibMainFooterView.onTabChangeListener {
    private BaseLibMainFooterView mFooterView;
    public int[][] iconsArr = {
            {R.drawable.tab_home_n, R.drawable.tab_home_p},
            {R.drawable.tab_address_n, R.drawable.tab_address_p},
            {R.drawable.tab_services_n, R.drawable.tab_services_p},
            {R.drawable.tab_personal_n, R.drawable.tab_personal_p}};


    private HomeFragment homeFragment;
    private ChooseAddressFragment chooseAddressFragment;
    private ServicesFragment servicesFragment;
    private PersonalFragment personalFragment;
    private float exitTime;//退出时间
    private SharedPreferences sp;
    public static TextView tv_news;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv_news=findView(R.id.tv_news);
        mFooterView = (BaseLibMainFooterView) findViewById(R.id.footer);
        mFooterView.setOnTabChangeListener(this);
        mFooterView.initTab(new String[]{"首页", "选址", "服务", "个人"}, iconsArr);
        AppUtil.getMAppContext(ac).startPush();//启动百度推送
        //百度自动定位
        /*LocationUtil.getInstance(_activity).startOneLocation(new LocationUtil.OneLocationListener() {
            @Override
            public void complete(BDLocation location) {
                String lon = location.getLongitude() + "";
                String lat = location.getLatitude() + "";
                ac.loc_city = location.getCity();//获取百度定位的当前所在城市
//                ac. = location.getDistrict();//获取百度定位当前所在的市区
                ac.loc_city = ac.loc_city.replace("市", "");
                ac.saveCityName(ac.loc_city, "");
                ac.saveLocation(lon, lat);
                System.out.println("百度自动定位值：：ac.loc_citys::" + ac.loc_city + ":::城市id--ac.loc_city_id:" + ac.loc_city_id);
                LocationUtil.getInstance(_activity).stopLocation();
            }
        });*/
    /*    if (ac.city_id == null) {
            AppUtil.getPpApiClient(ac).getCityList(this);
        }*/

        AppUtil.getPpApiClient(ac).getCityList(this);//获取发布城市列表
        initFragment();//初始化主界面
        /*if (ac.isUserIdExsit()) {
            if (mBundle != null && mBundle.getBoolean("isFromNotify")) {
                //来自状态栏的点击分发跳转
                DeviceMessage deviceMessage = (DeviceMessage) mBundle.getSerializable("pushData");

            }
        }*/
    }



    private void initFragment() {
        homeFragment = new HomeFragment();
        chooseAddressFragment = new ChooseAddressFragment();
        servicesFragment = new ServicesFragment();
        personalFragment = new PersonalFragment();
        switchFragment(homeFragment);
    }

    @Override
    public int getFragmentContentId() {
        return R.id.fl_content;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //删除保存在sp中的搜索信息
        sp=getSharedPreferences("index_search", MODE_PRIVATE);//首页载体搜索
        sp.edit().clear().commit();
        sp=getSharedPreferences("choose_search", MODE_PRIVATE);//选址fragment进入搜索
        sp.edit().clear().commit();
        sp=getSharedPreferences("map_search_info", MODE_PRIVATE);//首页地图选址进入搜索
        sp.edit().clear().commit();
    }

    @Override
    public void onTabChange(int index) {
        switch (index) {
            case 0://首页;
                switchFragment(homeFragment);
                break;
            case 1://选址
                switchFragment(chooseAddressFragment);
                break;
            case 2://服务
                switchFragment(servicesFragment);
                servicesFragment.refreshCity(ac.getProperty("user_type"));
                break;
            case 3: //个人中心
                switchFragment(personalFragment);
                break;
        }
    }

    @Override
    public void onApiSuccess(NetResult res, String tag) {
        super.onApiSuccess(res, tag);
        if (res.isOK()) {
            if ("getCityList".equals(tag)) {//获取城市发布列表
                BusinessLocationCityReleaseListBean cityBean = (BusinessLocationCityReleaseListBean) res;
                for (int i = 0; i < cityBean.getData().size(); i++) {
                    if (i == 0) {
                        ac.saveCityName(cityBean.getData().get(i).getCity(), cityBean.getData().get(i).getId());
                        ac.saveLocation(cityBean.getData().get(i).getLng(), cityBean.getData().get(i).getLat());
                        Log.d("=-=-=-=-", "onApiSuccess: 首个发布城市经纬度="+ac.lon+"=-=-=-"+ac.lat);
                    }
                }
            }
        } else {
            ac.handleErrorCode(_activity,res.errorcode);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == _activity.RESULT_OK) {
            if (requestCode == 10001) {
                ac.saveCityName(data.getStringExtra("city_name"), data.getStringExtra("city_id"));
                ac.saveLocation(data.getStringExtra("lng"), data.getStringExtra("lat"));
                homeFragment.refreshCity();
            }
            if (requestCode == 10002) {
                ac.saveCityName(data.getStringExtra("city_name"), data.getStringExtra("city_id"));
                ac.saveLocation(data.getStringExtra("lng"), data.getStringExtra("lat"));
                chooseAddressFragment.refreshCity(data);
            }
            if (requestCode == 10087) {// TODO: 2016/6/2 接收从搜索标签页返回的搜索数据
                System.out.println("标签页返回数据carrierTypeId=" + data.getStringExtra("carrierTypeId") + "lableids=" + data.getStringExtra("lableids"));
                chooseAddressFragment.refreshCity(data);
            }
            if (requestCode == 1301) {
                personalFragment.refreshCity();
            }
        }
    }


    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exit();
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }

    private void exit() {
        if ((System.currentTimeMillis() - exitTime) > 2000) {
            UIHelper.t(getApplicationContext(), R.string.exit_text);
            exitTime = System.currentTimeMillis();
        } else {
            LocationUtil.getInstance(_activity).stopLocation();
            AppManager.getAppManager().appExit(this);
            finish();
        }
    }
}
