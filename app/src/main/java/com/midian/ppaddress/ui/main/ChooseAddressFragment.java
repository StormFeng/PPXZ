package com.midian.ppaddress.ui.main;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.midian.ppaddress.R;
import com.midian.ppaddress.bean.BusinessAppsearchSopageBean.CarrierList;
import com.midian.ppaddress.bean.BusinessInfrasCarrierTypeBean;
import com.midian.ppaddress.bean.BusinessLocationCountyListBean;
import com.midian.ppaddress.datasource.ChooseAddressDataSource;
import com.midian.ppaddress.itemtpl.ChooseAddressTpl;
import com.midian.ppaddress.itemtpl.FragmentScreeningItemTpl;
import com.midian.ppaddress.ui.homepage.CitysReleaseListActivity;
import com.midian.ppaddress.ui.homepage.FragmentSearchConditionActivity;
import com.midian.ppaddress.ui.homepage.SearchConditionActivity;
import com.midian.ppaddress.utils.AppUtil;
import java.util.ArrayList;
import java.util.List;
import midian.baselib.api.ApiCallback;
import midian.baselib.base.BaseListAdapter;
import midian.baselib.base.BaseListFragment;
import midian.baselib.bean.NetResult;
import midian.baselib.shizhefei.view.listviewhelper.IDataAdapter;
import midian.baselib.shizhefei.view.listviewhelper.IDataSource;
import midian.baselib.utils.Func;
import midian.baselib.utils.UIHelper;
import midian.baselib.widget.BaseLibTopbarView;
import midian.baselib.widget.DimedView;
import midian.baselib.widget.SelectFilterView;

/**
 * 选址 Fragment
 * Created by chu on 2016/2/15.
 */
public class ChooseAddressFragment extends BaseListFragment implements SelectFilterView.onTabChangeListener, DimedView.OpenCloseListener, View.OnClickListener, ApiCallback, TextView.OnEditorActionListener {

//    private BaseLibTopbarView topbar;
    private SelectFilterView select;//选择过滤视图
    public static DimedView dimedView;
    private Button more;
    private EditText input_et;
    private Button right_bt;
    private ChooseAddressDataSource dataSource;
    private int index;
    private String cityName = "区域";
    private String carrierType = "载体类型";
    private String rentalType = "租/售";
    public static String saleRental = null;
    public static String carrierTypeId;
    private BaseListAdapter<ScreeningItem> adapter;
    private SharedPreferences sp;
    private String countryId;
    private String keywords;

    private View view;//dimedview打开时的背景


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        dataSource = new ChooseAddressDataSource(getActivity());
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
    }


    private void initView(View v) {
//        topbar = (BaseLibTopbarView) v.findViewById(R.id.topbar);
//        topbar.setMode(BaseLibTopbarView.MODE_WITH_INPUT);
        input_et = (EditText) v.findViewById(R.id.input_et);
        right_bt = (Button) v.findViewById(R.id.right_bt);
        right_bt.setOnClickListener(this);
//        topbar.getInput_et().setHint("大家都在搜:办公楼");
//        topbar.getRight_tv().setCompoundDrawablePadding(Func.Dp2Px(_activity, 6));
//        topbar.setRightText("广州", this).showRight_tv();
//        topbar.getRight_tv().setCompoundDrawablesWithIntrinsicBounds(null, null, getResources().getDrawable(R.drawable.icon_address_pull_down), null);
//        input_et = topbar.getInput_et();
        input_et.setImeOptions(EditorInfo.IME_ACTION_SEARCH);
        input_et.setOnEditorActionListener(this);
        view=v.findViewById(R.id.bg_view);
        select = (SelectFilterView) v.findViewById(R.id.select);
        dimedView = (DimedView) v.findViewById(R.id.dimeView);
        more = (Button) v.findViewById(R.id.more);//更多
        select.setOnTabChangeListener(this);
        dimedView.setOpenCloseListener(this);
        more.setOnClickListener(this);

    }

    @Override
    public void onResume() {
        super.onResume();

    }

    public void refreshCity(Intent data) {
        System.out.println("选址页接收返回数据carrierTypeId=" + data.getStringExtra("carrierTypeId") + "lableids=" + data.getStringExtra("lableids"));
//        topbar.setRightText(ac.city_name, this);
//        right_bt.setText(ac.city_name);
//        dataSource.setCityid(ac.city_id);
        Log.d("wqf","cityid::"+data.getStringExtra("city_id")+">>city_name::"+data.getStringExtra("city_name"));
        if(!TextUtils.isEmpty(data.getStringExtra("city_id"))){
            dataSource.setCityid(data.getStringExtra("city_id"));
//            topbar.getRight_tv().setText(data.getStringExtra("cityname"));
            right_bt.setText(data.getStringExtra("city_name"));
        }
        dataSource.setCarrierType(data.getStringExtra("carrierTypeId"));
        dataSource.setLabelids(data.getStringExtra("lableids"));
        dataSource.setSaleRental(data.getStringExtra("saleRental"));
        dataSource.setStartPrice(data.getStringExtra("startPrice"));
        dataSource.setEndPrice(data.getStringExtra("endPrice"));
        dataSource.setStartArea(data.getStringExtra("startArea"));
        dataSource.setEndArea(data.getStringExtra("endArea"));
        dataSource.setCountyid(data.getStringExtra("countyid"));
        keywords = input_et.getText().toString().trim();
        dataSource.setKeyword(keywords);
        if ("1".equals(data.getStringExtra("saleRental"))) {
            rentalType = "租";
        } else if ("2".equals(data.getStringExtra("saleRental"))) {
            rentalType = "售";
        }else if(TextUtils.isEmpty(data.getStringExtra(saleRental))){
            rentalType = "租/售";
        }
        String cId = data.getStringExtra("carrierTypeId");
        //载体类型，1为园区，2为综合体，3为土地，4为写字楼，6为厂房，8为仓库
        if ("1".equals(cId)) {
            carrierType = "园区";
        } else if ("2".equals(cId)) {
            carrierType = "综合体";
        } else if ("3".equals(cId)) {
            carrierType = "土地";
        } else if ("4".equals(cId)) {
            carrierType = "写字楼";
        } else if ("6".equals(cId)) {
            carrierType = "厂房";
        } else if ("8".equals(cId)) {
            carrierType = "仓库";
        } else if(TextUtils.isEmpty(cId)){
            carrierType = "全部";
        }
        String[] tabs = {cityName, carrierType, rentalType};
        select.initTab(tabs);
        dimedView.close();
        dataSource.setCountyid("");
        listViewHelper.refresh();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_choose_address;
    }

    @Override
    protected IDataSource<ArrayList<CarrierList>> getDataSource() {
        return dataSource;
    }


    @Override
    protected Class getTemplateClass() {
        return ChooseAddressTpl.class;
    }


    /**
     * 监听editText事件
     *
     * @param v
     * @param actionId
     * @param keyEvent
     * @return
     */
    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent keyEvent) {
        if (actionId == EditorInfo.IME_ACTION_SEARCH) {
            keywords = input_et.getText().toString().trim();
            //软键盘
            InputMethodManager im = ((InputMethodManager) _activity.getSystemService(_activity.INPUT_METHOD_SERVICE));
            im.showSoftInput(input_et, 0);
            if (!TextUtils.isEmpty(keywords)) {
                im.hideSoftInputFromWindow(v.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                dataSource.setKeyword(keywords);
                listViewHelper.refresh();
            } else {
                im.hideSoftInputFromWindow(v.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                dataSource.setKeyword("");
                listViewHelper.refresh();
//                UIHelper.t(_activity, "请输入关键字");
            }
            return true;
        }
        return false;
    }


    /**
     * 以下三个方法用于监听tab过滤条件改变
     */
    @Override
    public void onEndRefresh(IDataAdapter adapter, Object result) {

    }

    @Override
    public void onEndLoadMore(IDataAdapter adapter, Object result) {

    }

    /**
     * 监听tab过滤选择
     */
    @Override
    public void onTabChange(int index, boolean isSelect) {
        this.index = index;
        System.out.println("点击后的index:::" + index);
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
            case 1:
                if (isSelect) {
                    dimedView.show();
                    dimedView.getListView().setVisibility(View.GONE);
                    AppUtil.getPpApiClient(ac).carrierTypeList(this);
                } else {
                    dimedView.close();
                }
                break;
            case 2:
                if (isSelect) {
                    dimedView.show();
                    dimedView.getListView().setVisibility(View.GONE);
                    initRentalType();
                } else {
                    dimedView.close();
                }
                break;
        }
    }


    @Override
    public void onApiStart(String tag) {
        _activity.showLoadingDlg();
    }

    @Override
    public void onApiSuccess(NetResult res, String tag) {
        _activity.hideLoadingDlg();
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

    @Override
    public void onApiLoading(long count, long current, String tag) {
    }

    @Override
    public void onApiFailure(Throwable t, int errorNo, String strMsg, String tag) {
        _activity.hideLoadingDlg();
    }

    @Override
    public void onParseError(String tag) {
    }

    /**
     * 加载区域条目
     *
     * @param cityData
     */
    private void initCity(List<BusinessLocationCountyListBean.Data> cityData) {
        ArrayList<ScreeningItem> morelist = new ArrayList<ScreeningItem>();
        morelist.add(new ScreeningItem(null, "全部", 0));
        for (BusinessLocationCountyListBean.Data data : cityData) {
            morelist.add(new ScreeningItem(data.getId(), data.getCity(), 0));
        }
        initItemShow(morelist);
    }

    /**
     * 加载载体类型条目
     *
     * @param carrierData
     */
    private void initCarrierType(List<BusinessInfrasCarrierTypeBean.Data> carrierData) {
        ArrayList<ScreeningItem> morelist = new ArrayList<ScreeningItem>();
        morelist.add(new ScreeningItem(null, "全部", 1));
        for (BusinessInfrasCarrierTypeBean.Data data : carrierData) {
            morelist.add(new ScreeningItem(data.getId(), data.getName(), 1));
        }
        initItemShow(morelist);
    }

    /**
     * 加载租售类型条目
     */
    private void initRentalType() {
        ArrayList<ScreeningItem> morelist = new ArrayList<ScreeningItem>();
//        morelist.add(new ScreeningItem(null, "全部", 2));
        String[] typeArr = getResources().getStringArray(R.array.rentalType);
        for (int i = 0; i < typeArr.length; i++) {
            ScreeningItem item = new ScreeningItem(i + "", typeArr[i], 2);
            morelist.add(item);
        }
        initItemShow(morelist);
    }


    /**
     * 渲染筛选条目
     *
     * @param morelist
     */
    private void initItemShow(ArrayList<ScreeningItem> morelist) {
        adapter = new BaseListAdapter<ScreeningItem>(dimedView.getListView(), _activity, morelist, FragmentScreeningItemTpl.class, null);
        dimedView.getListView().setVisibility(View.VISIBLE);
        dimedView.getListView().setAdapter(adapter);
        dimedView.getListView().setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                ScreeningItem item = adapter.getData().get(position);
                if (item.type == 0) {//为区域筛选
                    if (position == 0) {//条目第一个：全广州
                        dataSource.setCountyid(null);
                        cityName = "全部";
                    } else {
                        countryId=item.getId();
                        dataSource.setCountyid(countryId);
                        cityName = item.getName();

                    }
                    //自定义列表无数据时的提示文字和事件 DeFaultLoadViewFactory.java
                    listViewHelper.loadViewFactory.config(false, "该区域暂无载体,请选择其他区域");
                } else if (item.type == 1) {//载体类型筛选
                    if (position == 0) {
                        dataSource.setCarrierType(null);
                        carrierType = "载体类型";
                    } else {
                        dataSource.setCarrierType(item.getId());
                        carrierType = item.getName();
                        carrierTypeId=item.getId();
                    }
                    listViewHelper.loadViewFactory.config(false, "该类型暂无载体,请选择其他载体类型");
                } else if (item.type == 2) {//租售筛选
                    if (position == 0) {
                        dataSource.setSaleRental("0");//租售方式，0为租售，1为租，2为售
                        rentalType = "租售";
                        saleRental = "0";//租售方式，0为租售，1为租，2为售
                    } else if (position == 1) {
                        dataSource.setSaleRental("1");
                        rentalType = "租";
                        saleRental = "1";//租售方式，0为租售，1为租，2为售
                    } else if (position == 2) {
                        dataSource.setSaleRental("2");
                        rentalType = "售";
                        saleRental = "2";//租售方式，0为租售，1为租，2为售
                    }
                    listViewHelper.loadViewFactory.config(false, "该条件暂无载体,请选择其他租售条件");
                }
                listViewHelper.refresh();
                String[] tabs = {cityName, carrierType, rentalType};
                select.initTab(tabs);
                dimedView.close();
            }
        });
    }


    /**
     * 以下两个方法用于监听dimeview的开关
     */
    @Override
    public void open() {
        dimedView.setBackgroundColor(Color.parseColor("#00ffffff"));
        view.setVisibility(View.VISIBLE);
    }

    @Override
    public void close() {
        select.setchangeState(index, false);
        view.setVisibility(View.GONE);
    }


    /**
     * 普通监听事件
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.more://更多筛选条件
//                sp=_activity.getSharedPreferences("search_info", Context.MODE_PRIVATE);
//                String sRental=sp.getString("saleRental","");
//                if(!TextUtils.isEmpty(sRental)){
//                    if(saleRental.equals(sRental))
//                }
                Bundle bundle = new Bundle();
                bundle.putString("type", "2");//1为首页载体类型筛选 2为选址筛选
                bundle.putString("saleRental", saleRental);
                bundle.putString("carrierTypeId", carrierTypeId);
                bundle.putString("countryId",countryId);
                UIHelper.jumpForResult(_activity, FragmentSearchConditionActivity.class, bundle, 10087);
                break;
            case R.id.right_bt://选择发布城市
                UIHelper.jumpForResult(_activity, CitysReleaseListActivity.class, 10002);
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        System.out.println("---------onActivityResult=" + requestCode + data.getStringExtra("city_name"));
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == 10001) {
//                topbar.setRightText(data.getStringExtra("city_name"), this);
                right_bt.setText(data.getStringExtra("city_name"));
            }
        }
    }


    /**
     * 筛选条件实体
     */
    public class ScreeningItem extends NetResult {
        private String id;
        private String name;
        int type;

        public ScreeningItem(String id, String name, int type) {
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
