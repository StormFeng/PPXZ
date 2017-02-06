package com.midian.ppaddress.ui.homepage;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.TranslateAnimation;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.midian.maplib.MapFragment;
import com.midian.maplib.map.ItemBean;
import com.midian.ppaddress.R;
import com.midian.ppaddress.bean.BusinessAppsearchMapcarriersBean;
import com.midian.ppaddress.bean.BusinessAppsearchSoMapBean;
import com.midian.ppaddress.bean.BusinessAppsearchSoMapBean.Data;
import com.midian.ppaddress.utils.AppUtil;
import com.midian.ppaddress.utils.DialogCarrierList;
import com.midian.ppaddress.widget.tagflowlayout.FlowLayout;
import com.midian.ppaddress.widget.tagflowlayout.TagAdapter;
import com.midian.ppaddress.widget.tagflowlayout.TagFlowLayout;

import java.util.ArrayList;
import java.util.List;

import midian.baselib.api.ApiCallback;
import midian.baselib.base.BaseFragmentActivity;
import midian.baselib.bean.NetResult;
import midian.baselib.utils.Func;
import midian.baselib.utils.UIHelper;
import midian.baselib.widget.BaseLibTopbarView;

/**
 * 选址地图模式
 * Created by chu on 2016/3/17.
 */
public class MapChooseAddressActivity extends BaseFragmentActivity implements MapFragment.MapFragmentListener, View.OnClickListener, TextView.OnEditorActionListener {
    private BaseLibTopbarView topbar;
//    private EditText input_et;
    private View show;
    MapFragment mMapFragment;
    private List<Data> listBean = new ArrayList<Data>();
    List<ItemBean> itemBeans = new ArrayList<>();
    // 当前数据的缩放等级
    private float cuttentZoom = 0;
    private DialogCarrierList dialogCarrierList;
    String carrierType = null;
    String labelids = null;//标签id集合
    String saleRental = null;//租售类型
    String startPrice = null;
    String endPrice = null;
    String startArea = null;
    String endArea = null;
    String cityId = null;
    private String countyid = null;//区县id
    String keyword = null;//关键字
    String left_top_lon, left_top_lat, right_bottom_lon, right_bottom_lat;

    private LinearLayout ll_back;//左上角返回按钮
    private EditText input_et;//搜索框
    private Button right_bt;//右上角下拉菜单
    private int flag;//用于区分用户点击或者触摸屏幕，点击时需要设置中心点，并更新数据，触摸拖拉时只需要更新数据

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_choose_address);
        topbar = findView(R.id.topbar);
//        topbar.setMode(BaseLibTopbarView.MODE_WITH_INPUT).setLeftImageButton(R.drawable.icon_back, UIHelper.finish(_activity));
//        topbar.getInput_et().setHint("大家都在搜:办公楼");
//        topbar.getRight_tv().setCompoundDrawablePadding(Func.Dp2Px(_activity, 6));
//        topbar.setRightText("筛选", this).showRight_tv();
//        topbar.getRight_tv().setCompoundDrawablesWithIntrinsicBounds(null, null, getResources().getDrawable(R.drawable.icon_address_pull_down), null);
//        input_et = topbar.getInput_et();
//        input_et.setImeOptions(EditorInfo.IME_ACTION_SEARCH);
//        input_et.setOnEditorActionListener(this);
        ll_back=findView(R.id.ll_back);
        input_et=findView(R.id.input_et);
        right_bt=findView(R.id.right_bt);
        ll_back.setOnClickListener(this);
        right_bt.setOnClickListener(this);
        input_et.setImeOptions(EditorInfo.IME_ACTION_SEARCH);
        input_et.setOnEditorActionListener(this);

        show = findView(R.id.show);//定位按钮
        show.setOnClickListener(this);
        mMapFragment = new MapFragment();
        fm.beginTransaction().replace(getFragmentContentId(), mMapFragment).commit();
    }


    @Override
    public int getFragmentContentId() {
        return R.id.content_fl;
    }


    @Override
    public void getNetData(int flag,String left_top_lon, String left_top_lat, String right_bottom_lon, String right_bottom_lat, float cuttentZoom) {//数据请求
        this.flag=flag;
        this.cuttentZoom = cuttentZoom;
        this.left_top_lon = left_top_lon;
        this.left_top_lat = left_top_lat;
        this.right_bottom_lon = right_bottom_lon;
        this.right_bottom_lat = right_bottom_lat;
        System.out.println("ac.city_id==" + ac.city_id);
        System.out.println("地图当前缩放等级=" + cuttentZoom);
        if (cuttentZoom < 13) {//取市级数据
            //flag=0表示点击地图，flag=1.拖拉地图
            if(flag==0) {//在市级地图层面点击覆盖物时，则传输区县id来获取数据
                AppUtil.getPpApiClient(ac).businessAppsearchSomap(carrierType, labelids, saleRental, startPrice, endPrice, startArea, endArea, ac.city_id, countyid, keyword, null, null
                        , null, null, 0 + "", this);
            }else{
                AppUtil.getPpApiClient(ac).businessAppsearchSomap(carrierType, labelids, saleRental, startPrice, endPrice, startArea, endArea, ac.city_id, null, keyword, null, null
                        , null, null, 0 + "", this);
            }

        } else if (cuttentZoom >= 13) {//取区级数据
            if(flag==1) {//在区县地图层面，拖动、放大缩小地图的时候，则传经纬度获取数据；
                AppUtil.getPpApiClient(ac).businessAppsearchSomap(carrierType, labelids, saleRental, startPrice, endPrice, startArea, endArea, ac.city_id, null, keyword, left_top_lon, left_top_lat
                        , right_bottom_lon, right_bottom_lat, 1 + "", this);
            }else{
                AppUtil.getPpApiClient(ac).businessAppsearchSomap(carrierType, labelids, saleRental, startPrice, endPrice, startArea, endArea, ac.city_id, countyid, keyword, null, null
                        , null, null, 1 + "", this);
//                AppUtil.getPpApiClient(ac).businessAppsearchSomap(carrierType, labelids, saleRental, startPrice, endPrice, startArea, endArea, ac.city_id, countyid, keyword, left_top_lon, left_top_lat
//                        , right_bottom_lon, right_bottom_lat, 1 + "", this);
            }
        }
    }


    @Override
    public void onApiSuccess(NetResult res, String tag) {
        super.onApiSuccess(res, tag);
        hideLoadingDlg();
        if (res.isOK()) {
            if ("businessAppsearchSomap".equals(tag)) {
                BusinessAppsearchSoMapBean bean = (BusinessAppsearchSoMapBean) res;
                itemBeans.clear();
                listBean.clear();
                listBean = bean.getData();
                if ( bean.getData().size() == 0) {
                    UIHelper.t(_activity,"没有对应的数据");
                }
                for (Data content : listBean) {
                    countyid = content.getCountyid();
                    if (cuttentZoom < 13) {//市级数据回调
                        itemBeans.add(new ItemBean(content.getCountyid(), content.getLat(), content.getLng(), content.getName(), content.getCountyid(), content.getCarrierCount()));
                    } else if (cuttentZoom >= 13) {//区县级数据回调
                        itemBeans.add(new ItemBean(content.getCarrierid(), content.getLat(), content.getLng(), content.getName(), null, content.getCarrierCount()));
                    }
                }

                if (cuttentZoom >= 13 && listBean.size() > 0) {
                    mMapFragment.locationMagnify(flag,listBean.get(0).getLat(), listBean.get(0).getLng());//放大地图操作
                }
                mMapFragment.refreshMap(itemBeans);//装载数据
            }
            //地图缩放到区县级展示载体数据
            //8.3区县级地图覆盖物回调，点击弹窗的那咱【坑死爹了】
            if ("businessAppsearchMapCarriers".equals(tag)) {
                BusinessAppsearchMapcarriersBean bean = (BusinessAppsearchMapcarriersBean) res;
                dialogCarrierList = new DialogCarrierList(_activity);
                dialogCarrierList.show(bean.getData());
            }
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

    /**
     * 根据地图上的数据图标跳转到单个详情或多个列表
     */
    @Override
    public void onClickMark(boolean isOne, Bundle b, String countyid) {
        Log.d("onClickMark", "onClickMark: marker事件地图缩放等级=" + cuttentZoom + "第一级的区id"+countyid+"bbbbbbbb=" + b.getString("id"));
        if (cuttentZoom < 13) {// 取区县级数据
            this.countyid = countyid;
            cuttentZoom = 14;
            getNetData(0,left_top_lon,left_top_lat,right_bottom_lon,right_bottom_lat,cuttentZoom);
        } else if (cuttentZoom >= 13) { //区县内载体数据
//            Bundle bundle = new Bundle();
//            bundle.putString("carrierid", b.getString("id"));
            AppUtil.getPpApiClient(ac).businessAppsearchMapCarriers(b.getString("id"), this);//地图缩放到区县级时、请求载体接口
        }
    }

    @Override
    public void initMarkView(TextView title, TextView number, String titleStr, int count) {
        System.out.println("initMarkView---" + count + "name=" + titleStr);
        number.setVisibility(View.VISIBLE);
        if (count == 0) {
            title.setVisibility(View.GONE);
            number.setVisibility(View.GONE);
        } else {
            title.setText(titleStr);
            number.setText("" + count);
        }
    }

    @Override
    public void changeMarkView(TextView title, TextView number, String titleStr, int count, boolean select) {

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
//            case R.id.right_tv:
//                Bundle bundle = new Bundle();
//                bundle.putString("type", "2");//1为首页载体类型筛选 2为地图筛选
//                UIHelper.jumpForResult(_activity, SearchConditionActivity.class, bundle, 10086);//筛选条件页
//                _activity.overridePendingTransition(R.anim.enter, 0);//从下至上打开页面的动画
//                break;
            case R.id.show:
                mMapFragment.location();//定位
                break;
            case R.id.ll_back:
                finish();
                break;
            case R.id.right_bt:
                int[] location=new int[2];
                int l=topbar.getMeasuredHeight();
                topbar.getLocationOnScreen(location);
                Bundle bundle = new Bundle();
                bundle.putInt("l",l+location[1]);
                bundle.putString("type", "2");//1为首页载体类型筛选 2为地图筛选
                UIHelper.jumpForResult(_activity, SearchConditionActivity.class, bundle, 10086);//筛选条件页
                _activity.overridePendingTransition(R.anim.enter, 0);//从下至上打开页面的动画

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        System.out.println("onActivityResult=" + requestCode);
        if (resultCode == RESULT_OK) {
            if (requestCode == 10086) {
                System.out.println("标签页返回数据carrierTypeId=" + data.getStringExtra("carrierTypeId") + "lableids=" + data.getStringExtra("lableids"));
                carrierType = data.getStringExtra("carrierTypeId");
                labelids = data.getStringExtra("lableids");
                saleRental = data.getStringExtra("saleRental");
                startPrice = data.getStringExtra("startPrice");
                endPrice = data.getStringExtra("endPrice");
                startArea = data.getStringExtra("startArea");
                endArea = data.getStringExtra("endArea");
//                keyword = input_et.getText().toString().trim();// TODO: 2016/7/15 0015  PPLOC-573 安卓地图选址-从更多筛选页返回时带上参数keyword
                if (cuttentZoom < 13) {//市级搜索
                    AppUtil.getPpApiClient(ac).businessAppsearchSomap(carrierType, labelids, saleRental, startPrice, endPrice, startArea, endArea, ac.city_id, null, keyword, null, null, null, null, 0 + "", this);
//                    AppUtil.getPpApiClient(ac).businessAppsearchSomap("1", null, null, null, null, null, null, ac.city_id, null, null, null, null, null, null, 0 + "", this);
                } else if (cuttentZoom >= 13) {
                    //区县级搜索
                    AppUtil.getPpApiClient(ac).businessAppsearchSomap(carrierType, labelids, saleRental, startPrice, endPrice, startArea, endArea, ac.city_id, null, keyword, left_top_lon, left_top_lat, right_bottom_lon, right_bottom_lat, 1 + "", this);
                }
//                if (cuttentZoom <= 11) {//取市级数据
//                    AppUtil.getPpApiClient(ac).businessAppsearchSomap(carrierType, labelids, saleRental, startPrice, endPrice, startArea, endArea, ac.city_id, null, keyword, null, null, null, null, 0 + "", this);
//                } else if (cuttentZoom >= 13) {//取区级数据
//                    AppUtil.getPpApiClient(ac).businessAppsearchSomap(carrierType, labelids, saleRental, null, null, null, null, ac.city_id, countyid, keyword, left_top_lon, left_top_lat, right_bottom_lon, right_bottom_lat, 1 + "", this);
//                }
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
        keyword = input_et.getText().toString().trim();
        if (TextUtils.isEmpty(keyword)) {
            UIHelper.t(_activity, "关键不能为空");
            return false;
        } else {
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
//                if (cuttentZoom < 13) {//市级
//                    AppUtil.getPpApiClient(ac).businessAppsearchSomap(carrierType, labelids, saleRental, startPrice, endPrice, startArea, endArea, ac.city_id, countyid, keyword, null, null, null, null, 0 + "", this);
//                } else if (cuttentZoom >= 13) {
//                    AppUtil.getPpApiClient(ac).businessAppsearchSomap(carrierType, labelids, saleRental, null, null, null, null, ac.city_id, null, keyword, left_top_lon, left_top_lat, right_bottom_lon, right_bottom_lat, 1 + "", this);
//                }
                // TODO: 2016/7/15 0015  PPLOC-573 关键词搜索载体与更多条件搜索没有关联，与IOS保持一致
                if (cuttentZoom < 13) {//市级
                    AppUtil.getPpApiClient(ac).businessAppsearchSomap(null, null, null, null, null, null, null, ac.city_id, countyid, keyword, null, null, null, null, 0 + "", this);
                } else if (cuttentZoom >= 13) {
                    AppUtil.getPpApiClient(ac).businessAppsearchSomap(null, null, null, null, null, null, null, ac.city_id, null, keyword, left_top_lon, left_top_lat, right_bottom_lon, right_bottom_lat, 1 + "", this);
                }
            } else {
                keyword = null;
            }
        }
        return false;
    }
}
