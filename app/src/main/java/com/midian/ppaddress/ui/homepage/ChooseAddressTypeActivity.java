package com.midian.ppaddress.ui.homepage;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;

import com.midian.ppaddress.R;
import com.midian.ppaddress.bean.BusinessAppsearchSopageBean;
import com.midian.ppaddress.bean.BusinessInfrasCarrierTypeBean;
import com.midian.ppaddress.bean.BusinessLocationCountyListBean;
import com.midian.ppaddress.datasource.ChooseAddressTypeDataSource;
import com.midian.ppaddress.itemtpl.ChooseAddressTypeTpl;
import com.midian.ppaddress.itemtpl.ScreeningItemTpl;
import com.midian.ppaddress.utils.AppUtil;

import java.util.ArrayList;
import java.util.List;

import midian.baselib.base.BaseListActivity;
import midian.baselib.base.BaseListAdapter;
import midian.baselib.bean.NetResult;
import midian.baselib.shizhefei.view.listviewhelper.IDataSource;
import midian.baselib.utils.UIHelper;
import midian.baselib.widget.BaseLibTopbarView;
import midian.baselib.widget.DimedView;
import midian.baselib.widget.SelectFilterView;

/**
 * 选址类型-通用【旧页面，暂时不用，勿删除】
 * Created by chu on 2016/3/17.
 */
public class ChooseAddressTypeActivity extends BaseListActivity implements SelectFilterView.onTabChangeListener, DimedView.OpenCloseListener {

    private BaseLibTopbarView topbar;
    private SelectFilterView select;//选择过滤视图
    private DimedView dimedView;
    private View more;
    private String type;
    private ChooseAddressTypeDataSource chooseAddressTypeDataSource;
    private int index;
    private String tabCityName = "全广州";
    private String tabCarrierType = "载体类型";
    private String tabRentalType = "租/售";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        //自定义列表无数据时的提示文字和事件 DeFaultLoadViewFactory.java
//        listViewHelper.loadViewFactory.config(false, "暂无该类型的载体");
    }


    @Nullable
    @Override
    public View onCreateView(String name, Context context, AttributeSet attrs) {
        type = mBundle.getString("type");
        chooseAddressTypeDataSource = new ChooseAddressTypeDataSource(_activity, type);
        return super.onCreateView(name, context, attrs);
    }

    private void initView() {

        topbar = findView(R.id.topbar);
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
        topbar.setLeftImageButton(R.drawable.icon_back, UIHelper.finish(_activity));

        select = findView(R.id.select);
        dimedView = findView(R.id.dimeView);
        String[] tabs = {tabCityName, tabCarrierType, tabRentalType};
        select.initTab(tabs);
        select.setCurIndex(1);
        more = findView(R.id.more);//更多
        select.setOnTabChangeListener(this);
        dimedView.setOpenCloseListener(this);
        more.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.more:
                UIHelper.t(_activity, "更多^");
                break;
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_types_addressing;
    }

    @Override
    protected IDataSource<ArrayList<BusinessAppsearchSopageBean.CarrierList>> getDataSource() {
        return chooseAddressTypeDataSource;
    }

    @Override
    protected Class getTemplateClass() {
        return ChooseAddressTypeTpl.class;
    }


    /**
     * 监听tab过滤选择
     */
    @Override
    public void onTabChange(int index, boolean isSelect) {
        this.index = index;
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
            case 1://注释掉第二个tab选项，显示为默认
                /*if (isSelect) {
                    dimedView.show();
                    dimedView.getListView().setVisibility(View.GONE);
                    AppUtil.getPpApiClient(ac).carrierTypeList(this);
                } else {
                    dimedView.close();
                }*/
                break;
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
    }


    /**
     * 加载区域条目
     * @param cityData
     */
    private void initCity(List<BusinessLocationCountyListBean.Data> cityData) {
        ArrayList<ChooseTypeItem> morelist = new ArrayList<ChooseTypeItem>();
        morelist.add(new ChooseTypeItem(null, "全广州", 0));
        for (BusinessLocationCountyListBean.Data data : cityData) {
            morelist.add(new ChooseTypeItem(data.getId(), data.getCity(), 0));
        }
        initItemShow(morelist);
    }

    /**
     * 加载载体类型条目
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
//        morelist.add(new ChooseItem(null, "全部", 2));
        String[] typeArr = getResources().getStringArray(R.array.rentalType);
        for (int i = 0; i < typeArr.length; i++) {
            ChooseTypeItem item = new ChooseTypeItem(i+"", typeArr[i], 2);
            morelist.add(item);
        }
        initItemShow(morelist);
    }

    /**
     * 渲染筛选条目
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

                if (item.type == 0) {//为区域筛选
                    if (position == 0) {//条目第一个：全广州
                        chooseAddressTypeDataSource.setCountyid(null);
                        tabCityName = "全广州";
                    } else {
                        chooseAddressTypeDataSource.setCountyid(item.getId());
                        tabCityName = item.getName();
                        chooseAddressTypeDataSource = new ChooseAddressTypeDataSource(_activity, item.getName());
                    }
                    //自定义列表无数据时的提示文字和事件 DeFaultLoadViewFactory.java
//                    listViewHelper.loadViewFactory.config(false, "该区域暂无载体,请选择其他区域");
                } /*else if (item.type == 1) {//载体类型筛选
                    if (position == 0) {
                        chooseAddressTypeDataSource.setCarrierType(null);
                    } else {
                        chooseAddressTypeDataSource.setCarrierType(item.getId());
                    }
                    listViewHelper.loadViewFactory.config(false, "该类型暂无载体,请选择其他载体类型");
                } */else if (item.type == 2) {//租售筛选
                    if (position == 0) {
                        chooseAddressTypeDataSource.setSaleRental("0");//租售方式，0为租售，1为租，2为售
                        tabRentalType = "租售";
                    } else if (position == 1) {
                        chooseAddressTypeDataSource.setSaleRental("1");
                        tabRentalType = "租";
                    } else if (position == 2) {
                        chooseAddressTypeDataSource.setSaleRental("2");
                        tabRentalType = "售";
                    }
                    listViewHelper.loadViewFactory.config(false, "该条件暂无载体,请选择其他租售条件");
                }
                listViewHelper.refresh();
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
    }

    @Override
    public void close() {
        select.setchangeState(index, false);
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
