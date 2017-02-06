package com.midian.ppaddress.ui.homepage;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.baidu.location.BDLocation;
import com.midian.maplib.LocationUtil;
import com.midian.ppaddress.R;
import com.midian.ppaddress.bean.BusinessAppsearchSoscreenBean;
import com.midian.ppaddress.bean.BusinessLocationCityReleaseListBean;
import com.midian.ppaddress.ui.main.ChooseAddressFragment;
import com.midian.ppaddress.utils.AppUtil;
import com.midian.ppaddress.widget.tagflowlayout.FlowLayout;
import com.midian.ppaddress.widget.tagflowlayout.TagAdapter;
import com.midian.ppaddress.widget.tagflowlayout.TagFlowLayout;

import java.util.ArrayList;
import java.util.Set;

import midian.baselib.base.BaseActivity;
import midian.baselib.bean.NetResult;
import midian.baselib.utils.UIHelper;
import midian.baselib.widget.BaseLibTopbarView;

/**
 * 选址筛选条件页
 * Created by chu on 2016/3/28.
 */
public class FragmentSearchConditionActivity extends BaseActivity implements RadioGroup.OnCheckedChangeListener, CompoundButton.OnCheckedChangeListener {
    private BaseLibTopbarView topbar;
    private TextView search_lable_et;
    private TagFlowLayout tabflow_layout;
    private TagFlowLayout flow_carrier;//载体类型流布局
    private RadioGroup rent_type_gr;
    private RadioButton rent_cb, sale_cb;
    private EditText start_price_et, end_price_et, start_area_et, end_area_et;
    private TextView reset_tv, config_tv;
    private String saleRental;//租售类型
    private String carrierTypeId, lable_id, lable_name, carrierName;
    StringBuilder lableids;
    private View carrier_ll;
    private CheckBox loc_city;
    private ImageView up_down;
    private TextView reset_loc_tv;
    private View show_city_ll;//显示隐藏的城市View
    private TagFlowLayout area_list;//区域
    private TagFlowLayout city_list;//热闹城市
    private String countyid;
    private String cityid;
    private String cityname;
    private boolean isOpen = true;
    private BusinessAppsearchSoscreenBean bean;
    private ArrayList<String> labels = new ArrayList<>();
    private ArrayList<String> lable_Ids = new ArrayList<>();
    private ArrayList<String> carrierNames = new ArrayList<>();
    private ArrayList<String> carrierTypeIds = new ArrayList<>();
    private String type, startPrice, endPrice, startArea, endArea;
    private View dividing_v;
    SharedPreferences sp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_search_condition);
        type = mBundle.getString("type");
        saleRental = mBundle.getString("saleRental");
        carrierTypeId=mBundle.getString("carrierTypeId");
        countyid=mBundle.getString("countryId");
        if ("1".equals(type)) {//type=1为首页载体搜索  2为选址fragment进入搜索
            sp = _activity.getSharedPreferences("index_search", MODE_PRIVATE);
        } else {
            sp = _activity.getSharedPreferences("choose_search", MODE_PRIVATE);
        }
        initLocation();//定位城市
        initView();
    }


    private void initView() {
        topbar = findView(R.id.topbar);
        topbar.setTitle("更多").setLeftImageButton(R.drawable.icon_back, null).setLeftText("返回", this);
        flow_carrier = (TagFlowLayout) findViewById(R.id.flow_carrier);
        search_lable_et = findView(R.id.search_lable_et);//标签输入
        rent_type_gr = findView(R.id.rent_type_gr);
        tabflow_layout = (TagFlowLayout) findViewById(R.id.tabflow_layout);//标签布局
        rent_cb = findView(R.id.rent_cb);//租
        sale_cb = findView(R.id.sale_cb);//售
        start_price_et = findView(R.id.start_price_et);//开始价
        end_price_et = findView(R.id.end_price_et);//结束价
        start_area_et = findView(R.id.start_area_et);//开始区间
        end_area_et = findView(R.id.end_area_et);//结束区
        reset_tv = findView(R.id.reset_tv);//重置
        config_tv = findView(R.id.config_tv);//确定
        carrier_ll = findView(R.id.carrier_ll);
        up_down = findView(R.id.up_down);//显示隐藏城市区域的checkBox
        loc_city = findView(R.id.loc_city);//定位到的城市
        reset_loc_tv = findView(R.id.reset_loc_tv);//重新定位
        show_city_ll = findView(R.id.show_city_ll);//显示 或隐藏的城市区域View
        area_list = (TagFlowLayout) findViewById(R.id.area_list);//热门区域
        city_list = (TagFlowLayout) findViewById(R.id.city_list);//热门城市
        dividing_v = findView(R.id.dividing_v);

        reset_loc_tv.setClickable(false);
        loc_city.setClickable(false);
//        reset_loc_tv.setOnClickListener(this);
//        loc_city.setOnCheckedChangeListener(this);
        up_down.setOnClickListener(this);
        search_lable_et.setOnClickListener(this);
        rent_type_gr.setOnCheckedChangeListener(this);
        reset_tv.setOnClickListener(this);
        config_tv.setOnClickListener(this);
        lableids = new StringBuilder();

        initBeforeSrarch();//初始化上一次保存的搜索信息

        if ("1".equals(type)) {//type=1为首页载体搜索  2为选址fragment进入搜索
            carrier_ll.setVisibility(View.GONE);
            dividing_v.setVisibility(View.GONE);
        } else {
            carrier_ll.setVisibility(View.VISIBLE);
            dividing_v.setVisibility(View.VISIBLE);
        }
        loadCarrierData();//获取载体类型数据
    }

    /**
     * 初始化上一次保存的搜索信息
     */
    private void initBeforeSrarch() {
        String ids = sp.getString("lable_Ids", "");
        ids = ids.replace("[", "");
        ids = ids.replace("]", "");
        String[] lableids = ids.split(",");
        if(lableids.length!=0) {
            for (int i = 0; i < lableids.length; i++) {
                lable_Ids.add(lableids[i]);
            }
        }
        String names = sp.getString("labels", "");
        names = names.replace("[", "");
        names = names.replace("]", "");
        String[] lableName = names.split(",");
        for (int i = 0; i < lableName.length; i++) {
            labels.add(lableName[i]);
        }
        if (labels.size() > 0 && !TextUtils.isEmpty(labels.get(0))) {
            intiLabels();
        }
        if(TextUtils.isEmpty(saleRental)){
            saleRental = sp.getString("saleRental", "");
        }
        if (!TextUtils.isEmpty(saleRental)) {
            if ("1".equals(saleRental)) {
                rent_cb.setChecked(true);
                sale_cb.setChecked(false);
            } else if ("2".equals(saleRental)) {
                sale_cb.setChecked(true);
                rent_cb.setChecked(false);
            }
        }

        startPrice = sp.getString("startPrice", "");
        endPrice = sp.getString("endPrice", "");
        startArea = sp.getString("startArea", "");
        endArea = sp.getString("endArea", "");
        if (!TextUtils.isEmpty(startPrice) && !startPrice.equals("")) {
            start_price_et.setText(startPrice);
        } else {
            start_price_et.setHint("最小价格");
        }
        if (!TextUtils.isEmpty(endPrice) && !endPrice.equals("")) {
            end_price_et.setText(endPrice);
        } else {
            end_price_et.setHint("最大价格");
        }
        if (!TextUtils.isEmpty(startArea) && !startArea.equals("")) {
            start_area_et.setText(startArea);
        } else {
            start_area_et.setHint("最小面积");
        }
        if (!TextUtils.isEmpty(endArea) && !endArea.equals("")) {
            end_area_et.setText(endArea);
        } else {
            end_area_et.setHint("最大面积");
        }
    }


    private void loadCarrierData() {
        AppUtil.getPpApiClient(ac).businessAppsearchSoscreen(this);
    }


    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.search_lable_et:
                UIHelper.jumpForResult(_activity, SearchLableActivity.class, 10087);
                break;
            case R.id.reset_tv://重置
                loc_city.setChecked(false);
                rent_cb.setChecked(false);
                sale_cb.setChecked(false);
                rent_cb.setTextColor(Color.parseColor("#000000"));
                sale_cb.setTextColor(Color.parseColor("#000000"));
                tabflow_layout.removeAllViews();
                start_price_et.setText("");
                end_price_et.setText("");
                start_area_et.setText("");
                end_area_et.setText("");
                start_price_et.setHint("最小价格");
                end_price_et.setHint("最大价格");
                start_area_et.setHint("最小面积");
                end_area_et.setHint("最大面积");
                carrierTypeId="";
                carrierName="";
                lable_Ids=null;
                labels=null;
                saleRental="";
                try {
                    flow_carrier.getAdapter().setSelectedList();// TODO: 2016/6/29 0029 重置时报空指针异常
                    area_list.getAdapter().setSelectedList();
                    city_list.getAdapter().setSelectedList();
                } catch (Exception e) {
                    e.printStackTrace();
                }
//                if ("1".equals(type)) {//type=1为首页载体搜索  2为选址fragment进入搜索
//                    sp=getSharedPreferences("index_search", MODE_PRIVATE);
//                    sp.edit().clear().commit();
//                } else {
//                    sp=getSharedPreferences("choose_search", MODE_PRIVATE);
                    sp.edit().clear().commit();
//                }
                break;
            case R.id.config_tv://确定
                Log.d("wqf","carrierTypeId::"+carrierTypeId+">>carrierName::"+carrierName);
                startPrice = start_price_et.getText().toString().trim();
                endPrice = end_price_et.getText().toString().trim();
                startArea = start_area_et.getText().toString().trim();
                endArea = end_area_et.getText().toString().trim();
                Bundle bundle = new Bundle();
                if ("2".equals(type)) {//type=1为首页载体搜索  2为选址fragment进入搜索
                    if (carrierTypeId != null) {
                        bundle.putString("carrierTypeId", carrierTypeId);
                        ChooseAddressFragment.carrierTypeId=carrierTypeId;
                    }
                }
                if (lable_Ids != null && !lable_Ids.equals("")) {
                    try {
                        if(",".equals(lable_Ids.get(0))){
                            lable_Ids.remove(0);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    String ids = lable_Ids.toString();
                    ids = ids.replace("[", "");
                    ids = ids.replace("]", "");
                    bundle.putString("lableids", ids);
                }

                if (saleRental != null) {
                    bundle.putString("saleRental", saleRental);
                    ChooseAddressFragment.saleRental=saleRental;
                }
                if (startPrice != null) {
                    bundle.putString("startPrice", startPrice);
                }
                if (endPrice != null) {
                    bundle.putString("endPrice", endPrice);
                }
                if (startArea != null) {
                    bundle.putString("startArea", startArea);
                }
                if (endArea != null) {
                    bundle.putString("endArea", endArea);
                }
                if (cityid != null) {
                    bundle.putString("cityid", cityid);
                    bundle.putString("cityname",cityname);
                }else{
                    bundle.putString("countyid",countyid);
                }
                if (!TextUtils.isEmpty(startPrice)) {
                    if (saleRental == null || saleRental.equals("")) {
                        UIHelper.t(_activity, "请选择租售类型");
                        return;
                    }
                }
                SharedPreferences.Editor editor = sp.edit();
                editor.putString("carrierId", carrierTypeId);
                editor.putString("carrierName", carrierName);
                if(lable_Ids!=null) {
                    editor.putString("lable_Ids", lable_Ids.toString());
                    editor.putString("labels", labels.toString());
                }
                editor.putString("saleRental", saleRental);
                editor.putString("startPrice", startPrice);
                editor.putString("endPrice", endPrice);
                editor.putString("startArea", startArea);
                editor.putString("endArea", endArea);
                editor.commit();
                setResult(RESULT_OK, bundle);
                finish();
                break;
            case R.id.left_ll:
                finish();
//                _activity.overridePendingTransition(0, R.anim.exit);//activity退出动画
                break;
            case R.id.reset_loc_tv:
                initLocation();
                showLoadingDlg();
                break;
            case R.id.up_down:
                if (isOpen) {
                    up_down.setImageResource(R.drawable.icon_up_arrow);
                    show_city_ll.setVisibility(View.VISIBLE);
                    isOpen = false;
                } else {
                    up_down.setImageResource(R.drawable.icon_down_arrow);
                    show_city_ll.setVisibility(View.GONE);
                    isOpen = true;
                }
                break;
        }

    }

    //百度单次定位
    private void initLocation() {
        LocationUtil.getInstance(_activity).startOneLocation(new LocationUtil.OneLocationListener() {
            @Override
            public void complete(BDLocation location) {
                hideLoadingDlg();
                String lon = location.getLongitude() + "";
                String lat = location.getLatitude() + "";
                String province = location.getCity();//获取百度定位的当前所在城市
//                String city = location.getDistrict().replace("市", "");//获取百度定位当前所在的市区
                loc_city.setText(location.getCity());
//                for(int i=0)
//                System.out.println("百度自动定位值：：province=" + province + ":::城市city=:" + city);
//                BusinessLocationCityReleaseListBean cityList=null;
//                try {
//                    BusinessLocationCityReleaseListBean cityList = AppUtil.getPpApiClient(ac).getCityList();
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
                LocationUtil.getInstance(_activity).stopLocation();
//                cityid=ac.city_id;
            }
        });
    }


    @Override
    public void onApiStart(String tag) {
        super.onApiStart(tag);
        showLoadingDlg("",true);
    }

    @Override
    public void onApiSuccess(NetResult res, String tag) {
        super.onApiSuccess(res, tag);
        hideLoadingDlg();
        if (res.isOK()) {
            //地图筛选回调数据
            //载体类型流布局
//            final ArrayList<String> carrierNames = new ArrayList<>();
//            final ArrayList<String> carrierTypeIds = new ArrayList<>();
            bean = (BusinessAppsearchSoscreenBean) res;
            for (BusinessAppsearchSoscreenBean.CarrierType carrierType : bean.getData().getCarrierType()) {
                carrierNames.add(carrierType.getName());
                carrierTypeIds.add(carrierType.getId());
            }
            flow_carrier.setAdapter(new TagAdapter<String>(carrierNames) {
                @Override
                public View getView(FlowLayout parent, int position, String s) {
                    TextView tv = (TextView) LayoutInflater.from(_activity).inflate(R.layout.flow_carrier_view, flow_carrier, false);
                    tv.setText(s);
                    return tv;
                }
            });

            if(!TextUtils.isEmpty(carrierTypeId)){
                for(int i=0;i<carrierTypeIds.size();i++){
                    if(carrierTypeId.equals(carrierTypeIds.get(i))){
                        flow_carrier.getAdapter().setSelectedList(i);
                    }
                }
            }else {
                //初始化上一次保存的载体标签
                for (int i = 0; i < carrierNames.size(); i++) {
                    if (sp.getString("carrierName", "").equals(carrierNames.get(i))) {
                        flow_carrier.getAdapter().setSelectedList(i);
                        carrierTypeId = carrierTypeIds.get(i);
                    }
                }
            }
            //载体类型的标签监听
            flow_carrier.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
                @Override
                public boolean onTagClick(View view, int position, FlowLayout parent) {
                    flow_carrier.getAdapter().setSelectedList(position);
                    try {
                        carrierTypeId = carrierTypeIds.get(position);
                        carrierName = carrierNames.get(position);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    return true;
                }
            });

            //区域流布局
            ArrayList<String> econzoneNames = new ArrayList<>();
            ArrayList<String> econzoneIds = new ArrayList<>();
            for (BusinessAppsearchSoscreenBean.Econzones econzones : bean.getData().getEconzones()) {
                econzoneNames.add(econzones.getName());
                econzoneIds.add(econzones.getEezid());
            }
            area_list.setAdapter(new TagAdapter<String>(econzoneNames) {
                @Override
                public View getView(FlowLayout parent, int position, String s) {
                    TextView tv = (TextView) LayoutInflater.from(_activity).inflate(R.layout.city_layout_item, area_list, false);
                    tv.setText(s);
                    return tv;
                }
            });
            area_list.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
                @Override
                public boolean onTagClick(View view, int position, FlowLayout parent) {
                    area_list.getAdapter().setSelectedList(position);
                    loc_city.setChecked(false);
                    initCityLayout(bean);
                    city_list.getAdapter().setSelectedList();
                    return true;
                }
            });
            area_list.getAdapter().setSelectedList(0);
            initCityLayout(bean);
        } else {
            ac.handleErrorCode(_activity, res.errorcode);
            if (!"no_error".equals(res.errorcode)) {
                UIHelper.t(_activity, "网络错误，请重新进入");
                finish();
            }
        }
    }

    private void initCityLayout(BusinessAppsearchSoscreenBean bean) {
        //热门城市流布局
        final ArrayList<String> cityNames = new ArrayList<>();
        final ArrayList<String> cityIds = new ArrayList<>();
        String id = area_list.getSelectedList().toString().replace("[", "");
        id = id.replace("]", "");
        final int cityId = Integer.valueOf(id);
        for (BusinessAppsearchSoscreenBean.Citys citys : bean.getData().getEconzones().get(cityId).getCitys()) {
            cityNames.add(citys.getName());
            cityIds.add(citys.getId());
        }
        city_list.setAdapter(new TagAdapter<String>(cityNames) {

            @Override
            public View getView(FlowLayout parent, int position, String s) {
                TextView tv = (TextView) LayoutInflater.from(_activity).inflate(R.layout.city_layout_item, city_list, false);
                tv.setText(s);
                return tv;
            }
        });
        city_list.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
            @Override
            public boolean onTagClick(View view, int position, FlowLayout parent) {
                loc_city.setChecked(false);
                city_list.getAdapter().setSelectedList(position);
                try {
                    cityid = cityIds.get(Integer.valueOf(position));
                    cityname=cityNames.get(Integer.valueOf(position));
                    countyid="";
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return true;
            }
        });
        city_list.setOnSelectListener(new TagFlowLayout.OnSelectListener() {
            @Override
            public void onSelected(Set<Integer> selectPosSet) {

            }
        });
    }


    @Override
    public void onApiFailure(Throwable t, int errorNo, String strMsg, String tag) {
        super.onApiFailure(t, errorNo, strMsg, tag);
        hideLoadingDlg();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        System.out.println("requestCode=" + requestCode);
        if (resultCode == RESULT_OK) {
            if (requestCode == 10087) {//获取标签返回的数据
                System.out.println("id=" + data.getStringExtra("lable_id") + "---name=" + data.getStringExtra("lable_name"));
                String lable_id = data.getStringExtra("lable_id");
                String keywords = data.getStringExtra("lable_name");
                if(!labels.contains(keywords)){
                    labels.add(keywords);
                    lable_Ids.add(lable_id);
                }else{
                    UIHelper.t(_activity,"已添加该标签");
                }
                intiLabels();
            }
        }
    }

    /**
     * 初始化上一次搜索 的标签记录
     */
    private void intiLabels() {
        if(TextUtils.isEmpty(labels.get(0))){
            labels.remove(0);
            lable_Ids.remove(0);
        }
        tabflow_layout.setAdapter(new TagAdapter<String>(labels) {
            @Override
            public View getView(FlowLayout parent, final int position, String s) {
                final View v = LayoutInflater.from(_activity).inflate(R.layout.search_lable_text, tabflow_layout, false);
                TextView tv = (TextView) v.findViewById(R.id.tv);
                View iv = v.findViewById(R.id.iv);
                tv.setText(s);
                iv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        tabflow_layout.removeViewAt(position);
                        labels.remove(position);
                        lable_Ids.remove(position);
                        tabflow_layout.getAdapter().notifyDataChanged();
                    }
                });
                return v;
            }
        });
    }

    /**
     * 租售类型选择监听
     *
     * @param radioGroup
     * @param checkedId
     */
    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
        if (checkedId == R.id.rent_cb) {//0为租售，1为租，2为售
            saleRental = "1";
            rent_cb.setTextColor(Color.parseColor("#ffffff"));
            sale_cb.setTextColor(Color.parseColor("#000000"));
        } else if (checkedId == R.id.sale_cb) {
            saleRental = "2";
            sale_cb.setTextColor(Color.parseColor("#ffffff"));
            rent_cb.setTextColor(Color.parseColor("#000000"));
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        if (b) {
            compoundButton.setTextColor(Color.parseColor("#ffffff"));
            try {
                area_list.getAdapter().setSelectedList();//设置区域选择为空
                city_list.getAdapter().setSelectedList();//设置热门城市选择为空
            } catch (Exception e) {
                e.printStackTrace();
            }
            compoundButton.setEnabled(false);
        } else {
            compoundButton.setTextColor(Color.parseColor("#000000"));
            compoundButton.setChecked(false);
            compoundButton.setEnabled(true);
        }
    }
}
