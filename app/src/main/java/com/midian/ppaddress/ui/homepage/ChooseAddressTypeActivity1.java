package com.midian.ppaddress.ui.homepage;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.midian.ppaddress.R;
import com.midian.ppaddress.bean.BusinessAppsearchSopageBean;
import com.midian.ppaddress.bean.BusinessInfrasCarrierTypeBean;
import com.midian.ppaddress.bean.BusinessLocationCountyListBean;
import com.midian.ppaddress.itemtpl.ScreeningItemTpl;
import com.midian.ppaddress.ui.chooseaddres.ChooseAddressActivity;
import com.midian.ppaddress.ui.chooseaddres.ConsultantActivity;
import com.midian.ppaddress.ui.chooseaddres.MultiDetailActivity;
import com.midian.ppaddress.ui.chooseaddres.ParkDetailActivity;
import com.midian.ppaddress.utils.AppUtil;

import java.util.ArrayList;
import java.util.List;

import midian.baselib.base.BaseActivity;
import midian.baselib.base.BaseListAdapter;
import midian.baselib.bean.NetResult;
import midian.baselib.utils.FDDataUtils;
import midian.baselib.utils.TDevice;
import midian.baselib.utils.UIHelper;
import midian.baselib.widget.BaseLibTopbarView;
import midian.baselib.widget.CircleImageView;
import midian.baselib.widget.DimedView;
import midian.baselib.widget.ListViewForScrollView;
import midian.baselib.widget.RoundCornerImageView;
import midian.baselib.widget.SelectFilterView;
import midian.baselib.widget.pulltorefresh.PullToRefreshBase;
import midian.baselib.widget.pulltorefresh.PullToRefreshScrollView;

/**
 * 选址类型-通用
 * Created by chu on 2016/3/17.
 */
public class ChooseAddressTypeActivity1 extends BaseActivity implements SelectFilterView.onTabChangeListener, DimedView.OpenCloseListener, AdapterView.OnItemClickListener {

    private BaseLibTopbarView topbar;
    private SelectFilterView select;//选择过滤视图
    private DimedView dimedView;
    private Button more;
    private String type;
    private ListViewForScrollView listview;
    private MyAdapter myadapter;
    private List<BusinessAppsearchSopageBean.CarrierList> dataList = new ArrayList<BusinessAppsearchSopageBean.CarrierList>();
    private int index;
    private TextView empty_tv;
    private PullToRefreshScrollView refreshScrollView;


    private String carriertype = null;
    private String labelids = null;
    private String saleRental = null;
    private String startPrice = null;
    private String endPrice = null;
    private String startArea = null;
    private String endArea = null;
    private String cityid = null;
    private String countyid = null;
    private String keyword = null;

    private int page = 1;

    private String tabCityName = "全部";
    private String tabCarrierType = "载体类型";
    private String tabRentalType = "租/售";

    private View bg_view;//dimedview打开时的背景

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_types_addressing1);
        type = mBundle.getString("type");
        carriertype = type;
        initView();
    }

    private void initView() {
        View view = LayoutInflater.from(_activity).inflate(R.layout.item_activity_types, null);
        topbar = findView(R.id.topbar);
        listview = (ListViewForScrollView) view.findViewById(R.id.listview);
        empty_tv = (TextView) view.findViewById(R.id.empty_tv);
        bg_view=findViewById(R.id.bg_view);
        if ("1,2".equals(type)) {
            topbar.setTitle("综合体/园区");
            tabCarrierType = "综合体/园区";
        } else if ("4".equals(type)) {
            topbar.setTitle("写字楼");
            tabCarrierType = "写字楼";
        } else if ("6,8".equals(type)) {
            topbar.setTitle("厂房/仓库");
            tabCarrierType = "厂房/仓库";
        } else if ("3".equals(type)) {
            topbar.setTitle("土地");
            tabCarrierType = "土地";
        }
        topbar.setLeftText("", null).setLeftImageButton(R.drawable.icon_back, UIHelper.finish(_activity));
        select = (SelectFilterView) findViewById(R.id.select);
        String[] tabs = {tabCityName, tabCarrierType, tabRentalType};
        select.initTab(tabs);
        select.hidTabState(1, false);
//        select.setCurIndex(1);
        dimedView = (DimedView) findViewById(R.id.dimeView);
        more = (Button) findViewById(R.id.more);//更多
        myadapter = new MyAdapter();
        listview.setAdapter(myadapter);
        select.setOnTabChangeListener(this);
        dimedView.setOpenCloseListener(this);
        more.setOnClickListener(this);
        listview.setOnItemClickListener(this);
        refreshScrollView = (PullToRefreshScrollView) findView(R.id.refreshScrollView);
        refreshScrollView.setPullLoadEnabled(false);
        refreshScrollView.scrollView.addView(view);
        refreshScrollView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ScrollView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ScrollView> refreshView) {
                loadData(countyid);
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ScrollView> refreshView) {

            }
        });
        loadData(countyid);
    }

    //从条件筛选页面返回时不需要传入countyid
    private void loadData(String countyid) {
        if (cityid == null) {
            cityid = ac.city_id;
        }
        AppUtil.getPpApiClient(ac).businessAppsearchSopage(carriertype, labelids, saleRental, startPrice, endPrice, startArea, endArea, cityid, countyid, keyword, page + "", this);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.more:
                Bundle bundle = new Bundle();
                bundle.putString("type", "1");//1为首页载体类型筛选 2为地图筛选
                bundle.putString("saleRental", saleRental);
//                UIHelper.jumpForResult(_activity, SearchConditionActivity.class, bundle,10086);//筛选条件页
                UIHelper.jumpForResult(_activity, FragmentSearchConditionActivity.class, bundle, 10087);//筛选条件页
                break;
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        System.out.println("onActivityResult=" + requestCode);
        if (resultCode == RESULT_OK) {
            /*dimedView.close();
//            select.setchangeState(index, false);
            select.setchangeState(0,false);*/
            if (requestCode == 10087) {//
                System.out.println("标签页返回数据carrierTypeId=" + data.getStringExtra("carrierTypeId") + "lableids=" + data.getStringExtra("lableids"));
                labelids = data.getStringExtra("lableids");
                saleRental = data.getStringExtra("saleRental");
                startPrice = data.getStringExtra("startPrice");
                endPrice = data.getStringExtra("endPrice");
                startArea = data.getStringExtra("startArea");
                endArea = data.getStringExtra("endArea");
                if ("1".equals(saleRental)) {
                    tabRentalType = "租";
                } else if ("2".equals(saleRental)) {
                    tabRentalType = "售";
                }
                String[] tabs = {tabCityName, tabCarrierType, tabRentalType};
                select.initTab(tabs);
                dimedView.close();
                select.setchangeState(index, false);
                select.hidTabState(1, false);
                loadData("");
            }
        }
    }


    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
        if(dimedView.isShowing()){
            dimedView.close();
        }else {
            Bundle mBundle = new Bundle();
            mBundle.putString("carriertitle", dataList.get(position).getShowName());//标题
            mBundle.putString("carriertype", dataList.get(position).getCarrierType());//载体类型
            mBundle.putString("carrierid", dataList.get(position).getCarrierid());//载体id
            UIHelper.jump(_activity, ParkDetailActivity.class, mBundle);
        }
    }


    public class MyAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            if (dataList != null && dataList.size() > 0) {
                return dataList.size();
            }
            return 0;
        }

        @Override
        public Object getItem(int position) {
            return dataList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View view, ViewGroup viewGroup) {
            ViewHolder mHolder;
            if (view == null) {
                mHolder = new ViewHolder();
                LayoutInflater inflater = LayoutInflater.from(_activity);
                view = inflater.inflate(R.layout.item_types_adress, null);
                mHolder.image_iv = (ImageView) view.findViewById(R.id.image_iv);
                mHolder.has_enter_ll = view.findViewById(R.id.has_enter_ll);
                mHolder.has_enter = view.findViewById(R.id.has_enter);
                mHolder.has_list = (LinearLayout) view.findViewById(R.id.has_list);
                mHolder.status_tv = (TextView) view.findViewById(R.id.status);//租售类型
                mHolder.title_tv = (TextView) view.findViewById(R.id.title);
                mHolder.price_tv = (TextView) view.findViewById(R.id.price);
                mHolder.addresss_tv = (TextView) view.findViewById(R.id.address);
                mHolder.type_tv = (TextView) view.findViewById(R.id.type);//载体类型
                mHolder.name_tv = (TextView) view.findViewById(R.id.name);
                mHolder.booking_tv = (TextView) view.findViewById(R.id.booking);
                mHolder.head = (CircleImageView) view.findViewById(R.id.head);
                view.setTag(mHolder);
            } else {
                mHolder = (ViewHolder) view.getTag();
            }
            final String carrierid = dataList.get(position).getCarrierid();//载体id
            mHolder.carrier_uuid = dataList.get(position).getCarrieruuid();//载体uuid
            if (dataList.get(position).getImages() == null || dataList.get(position).getImages().equals("")) {
                ac.setImage(mHolder.image_iv, R.drawable.default_bg);
            } else {
                ac.setImage(mHolder.image_iv, dataList.get(position).getImages());
            }
            mHolder.title_tv.setText(dataList.get(position).getShowName());
            String saleRental = dataList.get(position).getSaleRental();//租售方式，0为租售，1为租，2为售
            if ("1".equals(saleRental) || "0".equals(saleRental)) {
                mHolder.status_tv.setText("租");
                mHolder.price_tv.setText(FDDataUtils.addComma(dataList.get(position).getPriceRent()) + "元/m²·月 起");
            } else if ("2".equals(saleRental)) {
                mHolder.status_tv.setText("售");
                mHolder.price_tv.setText(FDDataUtils.addComma(dataList.get(position).getPriceSell()) + "元/m² 起");
            }
            String county = dataList.get(position).getCounty();//区县名称
            if (county != null) {
                mHolder.addresss_tv.setText(county);
            }
            final String carrier_type = dataList.get(position).getCarrierType();//类型，1为园区，2为综合体，3为土地，4为写字楼，6为厂房，8为仓库

            if ("1".equals(carrier_type)) {
                mHolder.type_tv.setText("园区");
            } else if ("2".equals(carrier_type)) {
                mHolder.type_tv.setText("综合体");
            } else if ("3".equals(carrier_type)) {
                mHolder.type_tv.setText("土地");
            } else if ("4".equals(carrier_type)) {
                mHolder.type_tv.setText("写字楼");
            } else if ("6".equals(carrier_type)) {
                mHolder.type_tv.setText("厂房");
            } else if ("8".equals(carrier_type)) {
                mHolder.type_tv.setText("仓库");
            }

            String hasEnter = dataList.get(position).getHasEnter();//0是没顾问驻守，1是有顾问驻守
            mHolder.has_list.removeAllViews();
            if ("1".equals(hasEnter)) {
                mHolder.has_enter.setVisibility(View.VISIBLE);
                if (carrier_type.equals("1") || carrier_type.equals("2")) {
                    mHolder.has_list.setVisibility(View.VISIBLE);
                    mHolder.has_enter_ll.setVisibility(View.GONE);
                    mHolder.booking_tv.setText("进入选址");
                    for (int i = 0; i < dataList.get(position).getIntroImages().size(); i++) {
                        RoundCornerImageView image = (RoundCornerImageView) View.inflate(_activity, R.layout.view_carriers_bg, null);
                        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams((int) TDevice.dpToPixel(30f), (int) TDevice.dpToPixel(30f));
                        params.rightMargin = (int) TDevice.dpToPixel(10f);
                        if (dataList.get(position).getIntroImages().get(i) == null || dataList.get(position).getIntroImages().get(i).equals("")) {
                            ac.setImage(image, R.drawable.head1);
                        } else {
                            ac.setImage(image, dataList.get(position).getIntroImages().get(i));
                        }
                        mHolder.has_list.addView(image, params);
                    }
                } else {
                    mHolder.has_list.setVisibility(View.GONE);
                    mHolder.has_enter_ll.setVisibility(View.VISIBLE);
                    if (dataList.get(position).getPortrait() == null || dataList.get(position).getPortrait().equals("")) {
                        ac.setImage(mHolder.head, R.drawable.head1);
                    } else {
                        ac.setImage(mHolder.head, dataList.get(position).getPortrait());
                    }
                    if (dataList.get(position).getSex().equals("0")) {//0是女，1是男
                        mHolder.sex = "女";
                    } else {
                        mHolder.sex = "男";
                    }
                    mHolder.name_tv.setText(dataList.get(position).getFullname() + " | " + mHolder.sex + " | 物业顾问");
                    mHolder.enter_memberid = dataList.get(position).getMemberid();
                }
            } else {
                mHolder.has_enter.setVisibility(View.GONE);
            }


            mHolder.booking_tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Bundle mBundle = new Bundle();
                    if (carrier_type.equals("1") || carrier_type.equals("2")) {
                        mBundle.putString("carrierid", carrierid);
                        mBundle.putString("title", dataList.get(position).getShowName());
                        UIHelper.jump(_activity, ChooseAddressActivity.class, mBundle);//进入选址
                    } else {
                        mBundle.putString("carrierid", carrierid);//载体id`
                        UIHelper.jump(_activity, ConsultantActivity.class, mBundle);//物业顾问列表
                    }
                }
            });

            return view;
        }

        public class ViewHolder {
            TextView status_tv, title_tv, price_tv, addresss_tv, type_tv, name_tv, booking_tv;
            CircleImageView head;
            ImageView image_iv;
            View has_enter_ll, has_enter;
            LinearLayout has_list;
            String carrier_uuid, sex, enter_memberid;
        }
    }

    /**
     * 监听tab过滤选择
     */
    @Override
    public void onTabChange(int index, boolean isSelect) {
        this.index = index;
        if (index == 1) {
            select.hidTabState(1, false);
        }
        switch (index) {
            case 0:
                if (isSelect) {
                    dimedView.show();
                    dimedView.getListView().setVisibility(View.GONE);
                    AppUtil.getPpApiClient(ac).getCountyList(ac.city_id, this);
                } else {
                    dimedView.close();
                }
                break;
           /* case 1://注释掉第二个项载体类型不能手动选择
               *//* if (isSelect) {
                    dimedView.show();
                    dimedView.getListView().setVisibility(View.GONE);
                    AppUtil.getPpApiClient(ac).carrierTypeList(this);
                } else {
                    dimedView.close();
                }*//*
                break;*/
            case 2:
                if (isSelect) {
                    dimedView.show();
                    dimedView.getListView().setVisibility(View.GONE);
                    initRentalType();//租售类型
                } else {
                    dimedView.close();
                }
                break;

        }
    }


    @Override
    public void onApiStart(String tag) {
        super.onApiStart(tag);
        showLoadingDlg();
    }

    @Override
    public void onApiSuccess(NetResult res, String tag) {
        super.onApiSuccess(res, tag);
        hideLoadingDlg();
        refreshScrollView.onPullDownRefreshComplete();
        refreshScrollView.onPullUpRefreshComplete();
        //区域回调
        if ("getCountyList".equals(tag)) {
            BusinessLocationCountyListBean cityBean = (BusinessLocationCountyListBean) res;
            initCity(cityBean.getData());//初始化区域筛选
        }
        //载体类型回调
        if ("carrierTypeList".equals(tag)) {
            BusinessInfrasCarrierTypeBean carrierTypeBean = (BusinessInfrasCarrierTypeBean) res;
            initCarrierType(carrierTypeBean.getData());
        }
        if ("businessAppsearchSopage".equals(tag)) {
            dataList.clear();
            BusinessAppsearchSopageBean carrierListbean = (BusinessAppsearchSopageBean) res;
            dataList.addAll(carrierListbean.getData().getList());
            if (dataList == null || dataList.size() == 0) {
                empty_tv.setVisibility(View.VISIBLE);
            } else {
                empty_tv.setVisibility(View.GONE);
            }
            myadapter.notifyDataSetChanged();
        }
    }


    /**
     * 加载区域条目
     *
     * @param cityData
     */
    private void initCity(List<BusinessLocationCountyListBean.Data> cityData) {
        ArrayList<ChooseTypeItem> morelist = new ArrayList<ChooseTypeItem>();
        morelist.add(new ChooseTypeItem(null, "全部", 0));
        for (BusinessLocationCountyListBean.Data data : cityData) {
            morelist.add(new ChooseTypeItem(data.getId(), data.getCity(), 0));
        }
        initItemShow(morelist);
    }

    /**
     * 加载载体类型条目
     *
     * @param carrierData
     */
    private void initCarrierType(List<BusinessInfrasCarrierTypeBean.Data> carrierData) {
        ArrayList<ChooseTypeItem> morelist = new ArrayList<ChooseTypeItem>();
        morelist.add(new ChooseTypeItem(null, "全部", 1));
        for (BusinessInfrasCarrierTypeBean.Data data : carrierData) {
            morelist.add(new ChooseTypeItem(data.getId(), data.getName(), 1));
        }
        initItemShow(morelist);
    }

    /**
     * 加载租售类型条目
     */
    private void initRentalType() {
        ArrayList<ChooseTypeItem> morelist = new ArrayList<ChooseTypeItem>();
//        morelist.add(new ChooseTypeItem(null, "全部", 2));
        String[] typeArr = getResources().getStringArray(R.array.rentalType);
        for (int i = 0; i < typeArr.length; i++) {
            ChooseTypeItem item = new ChooseTypeItem(i + "", typeArr[i], 2);
            morelist.add(item);
        }
        initItemShow(morelist);
    }


    /**
     * 渲染筛选条目
     *
     * @param morelist
     */
    private void initItemShow(ArrayList<ChooseTypeItem> morelist) {
        final BaseListAdapter<ChooseTypeItem> adapter = new BaseListAdapter<ChooseTypeItem>(dimedView.getListView(), _activity, morelist, ScreeningItemTpl.class, null);
        dimedView.getListView().setVisibility(View.VISIBLE);
        dimedView.getListView().setAdapter(adapter);
        dimedView.getListView().setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                ChooseTypeItem item = adapter.getData().get(position);
                String ids = item.getId();

                if (item.type == 0) {//为区域筛选
                    if (position == 0) {//条目第一个：全广州
                        countyid = null;
                        tabCityName = "全部";
                    } else {
                        countyid = item.getId();
                        tabCityName = item.getName();
                    }
                    loadData(countyid);
                    myadapter.notifyDataSetChanged();
                    //自定义列表无数据时的提示文字和事件 DeFaultLoadViewFactory.java
//                    listViewHelper.loadViewFactory.config(false, "该区域暂无载体,请选择其他区域");
                } /*else if (item.type == 1) {//载体类型筛选
                    if (position == 0) {
                        carriertype = null;
                        tabCarrierType = "载体类型";
                    } else {
                        carriertype = item.getId();
                        tabCarrierType = item.getName();
                    }
//                    listViewHelper.loadViewFactory.config(false, "该类型暂无载体,请选择其他载体类型");
                    loadData();
                    myadapter.notifyDataSetChanged();
                } */ else if (item.type == 2) {//租售筛选
                    if (position == 0) {
                       /* //全部条目
                        saleRental = null;
                        tabRentalType = "租/售";*/
                        saleRental = "0";//租售方式，0为租售，1为租，2为售
                        tabRentalType = "租售";
                    } else if (position == 1) {
                        saleRental = "1";
                        tabRentalType = "租";
                    } else if (position == 2) {
                        saleRental = "2";
                        tabRentalType = "售";
                    }
                    loadData(countyid);
                    myadapter.notifyDataSetChanged();
                }
                String[] tabs = {tabCityName, tabCarrierType, tabRentalType};
                select.initTab(tabs);
                dimedView.close();
            }
        });
    }

    /**
     * dimeView开关监听
     */
    @Override
    public void open() {
        select.hidTabState(1, false);
        dimedView.setBackgroundColor(Color.parseColor("#00ffffff"));
        bg_view.setVisibility(View.VISIBLE);
    }

    @Override
    public void close() {
        select.setchangeState(index, false);
        select.hidTabState(1, false);
        bg_view.setVisibility(View.GONE);
    }


    /**
     * 筛选条件实体
     */
    public class ChooseTypeItem extends NetResult {
        private String id;
        private String name;
        int type;

        public ChooseTypeItem(String id, String name, int type) {
            super();
            this.id = id;
            this.name = name;
            this.type = type;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
