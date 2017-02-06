package com.midian.ppaddress.ui.homepage;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.midian.ppaddress.R;
import com.midian.ppaddress.bean.BusinessInfrasCarrierTypeBean;
import com.midian.ppaddress.utils.AppUtil;
import com.midian.ppaddress.widget.tagflowlayout.FlowLayout;
import com.midian.ppaddress.widget.tagflowlayout.TagAdapter;
import com.midian.ppaddress.widget.tagflowlayout.TagFlowLayout;

import java.util.ArrayList;

import midian.baselib.base.BaseActivity;
import midian.baselib.bean.NetResult;
import midian.baselib.utils.UIHelper;
import midian.baselib.widget.BaseLibTopbarView;

/**
 * 地图筛选条件页
 * Created by chu on 2016/3/28.
 */
public class SearchConditionActivity extends BaseActivity implements RadioGroup.OnCheckedChangeListener {
    private BaseLibTopbarView topbar;
    private TextView search_lable_et;
    private TagFlowLayout tagflow_layout;
    private TagFlowLayout flow_carrier;//载体类型流布局
    private RadioGroup rent_type_gr;
    private RadioButton rent_cb, sale_cb;
    private EditText start_price_et, end_price_et, start_area_et, end_area_et;
    private TextView reset_tv, config_tv;
    private String saleRental;//租售类型
    private String carrierTypeId, lable_id, lable_name;
    StringBuilder lableids;
    private ArrayList<String> labels = new ArrayList<>();
    private ArrayList<String> lable_Ids = new ArrayList<>();
    private String type;
    private View carrier_ll;
    private SharedPreferences sp;
    private String startPrice,endPrice,startArea,endArea;

    private int l;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_search_condition);
        //设置非全屏activity

        l = mBundle.getInt("l");
        config_tv = findView(R.id.config_tv);//确定
        Window window = this.getWindow();
        window.setGravity(Gravity.BOTTOM);
        WindowManager.LayoutParams lp=window.getAttributes();
        WindowManager m=getWindowManager();
        Display d=m.getDefaultDisplay();
        lp.width=d.getWidth();
        lp.height= (int) (d.getHeight()*(1-l/(float)d.getHeight()));
        Log.d("wqf","lp.height::"+lp.height);
        window.setAttributes(lp);

        type = mBundle.getString("type");
//        saleRental = mBundle.getString("saleRental");
        if("2".equals(type)) {
            sp = getSharedPreferences("map_search_info", MODE_PRIVATE);
        }
        initView();
    }


    private void initView() {
//        topbar = findView(R.id.topbar);
//        topbar.setTitle("选择搜索条件").setLeftImageButton(R.drawable.icon_back, null).setLeftText("返回", this);
        flow_carrier = findView(R.id.flow_carrier);
        search_lable_et = findView(R.id.search_lable_et);//标签输入
        rent_type_gr = findView(R.id.rent_type_gr);
        tagflow_layout = findView(R.id.tagflow_layout);//标签布局
        rent_cb = findView(R.id.rent_cb);//租
        sale_cb = findView(R.id.sale_cb);//售
        start_price_et = findView(R.id.start_price_et);//开始价
        end_price_et = findView(R.id.end_price_et);//结束价
        start_area_et = findView(R.id.start_area_et);//开始区间
        end_area_et = findView(R.id.end_area_et);//结束区
        reset_tv = findView(R.id.reset_tv);//重置
        config_tv = findView(R.id.config_tv);//确定
        carrier_ll = findView(R.id.carrier_ll);//载体布局

        search_lable_et.setOnClickListener(this);
        rent_type_gr.setOnCheckedChangeListener(this);
        reset_tv.setOnClickListener(this);
        config_tv.setOnClickListener(this);
        lableids = new StringBuilder();

        if (!TextUtils.isEmpty(saleRental)) {
            if ("1".equals(saleRental)) {
                rent_cb.setChecked(true);
                sale_cb.setChecked(false);
            } else if ("2".equals(saleRental)) {
                sale_cb.setChecked(true);
                rent_cb.setChecked(false);
            }
        }
        initBeforeSrarch();
        if ("1".equals(type)) {
            carrier_ll.setVisibility(View.GONE);
        } else {
            carrier_ll.setVisibility(View.VISIBLE);
            loadCarrierData();//获取载体类型数据
        }
    }

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
        carrierTypeId=sp.getString("carrierId","");
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
    /**
     * 初始化上一次搜索 的标签记录
     */
    private void intiLabels() {
        if(TextUtils.isEmpty(labels.get(0))){
            labels.remove(0);
            lable_Ids.remove(0);
        }
        tagflow_layout.setAdapter(new TagAdapter<String>(labels) {
            @Override
            public View getView(FlowLayout parent, final int position, String s) {
                final View v = LayoutInflater.from(_activity).inflate(R.layout.search_lable_text, tagflow_layout, false);
                TextView tv = (TextView) v.findViewById(R.id.tv);
                View iv = v.findViewById(R.id.iv);
                tv.setText(s);
                iv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        tagflow_layout.removeViewAt(position);
                        labels.remove(position);
                        lable_Ids.remove(position);
                        tagflow_layout.getAdapter().notifyDataChanged();
                    }
                });
                return v;
            }
        });
    }
    private void loadCarrierData() {
        AppUtil.getPpApiClient(ac).carrierTypeList(this);
    }


    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.search_lable_et:
                UIHelper.jumpForResult(_activity, SearchLableActivity.class, 10087);
                break;
            case R.id.reset_tv:
                tagflow_layout.removeAllViews();
                rent_cb.setChecked(false);
                sale_cb.setChecked(false);
                rent_cb.setTextColor(Color.parseColor("#000000"));
                sale_cb.setTextColor(Color.parseColor("#000000"));
                start_price_et.setText("");
                end_price_et.setText("");
                start_area_et.setText("");
                end_area_et.setText("");
                start_price_et.setHint("最小价格");
                end_price_et.setHint("最大价格");
                start_area_et.setHint("最小面积");
                end_area_et.setHint("最大面积");
                lable_Ids=null;
                labels=null;
                saleRental="";
                carrierTypeId="";
                try {
                    flow_carrier.getAdapter().setSelectedList();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (lable_Ids != null && lable_Ids.size() > 0) {
                    flow_carrier.getAdapter().setSelectedList();
                }
                sp.edit().clear().commit();
                break;
            case R.id.config_tv:
                startPrice = start_price_et.getText().toString().trim();
                endPrice = end_price_et.getText().toString().trim();
                startArea = start_area_et.getText().toString().trim();
                endArea = end_area_et.getText().toString().trim();
                Bundle bundle = new Bundle();
                if (carrierTypeId != null) {
                    bundle.putString("carrierTypeId", carrierTypeId);
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

                //如果填写 了价格就必选租售类型
                if (!TextUtils.isEmpty(startPrice)) {
                    if (saleRental == null || saleRental.equals("")) {
                        UIHelper.t(_activity, "请选择租售类型");
                        return;
                    }
                }

                SharedPreferences.Editor editor = sp.edit();
                editor.putString("carrierId", carrierTypeId);
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
                _activity.overridePendingTransition(0, R.anim.exit);//
                break;
        }

    }


    @Override
    public void onApiStart(String tag) {
        super.onApiStart(tag);
//        showLoadingDlg();
    }

    @Override
    public void onApiSuccess(NetResult res, String tag) {
        super.onApiSuccess(res, tag);
        hideLoadingDlg();
        if (res.isOK()) {
            //地图筛选回调数据
            //载体类型流布局
            ArrayList<String> carrierNames = new ArrayList<>();
            final ArrayList<String> carrierTypeIds = new ArrayList<>();
            //载体类型回调数据
            BusinessInfrasCarrierTypeBean bean = (BusinessInfrasCarrierTypeBean) res;
            for (BusinessInfrasCarrierTypeBean.Data data : bean.getData()) {
                if ("园区".equals(data.getName()) || "综合体".equals(data.getName())) {
                    carrierNames.add(data.getName());
                    carrierTypeIds.add(data.getId());
                }
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
                    Log.d("wqf","id::"+carrierTypeId);
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
            flow_carrier.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
                @Override
                public boolean onTagClick(View view, int position, com.midian.ppaddress.widget.tagflowlayout.FlowLayout parent) {
                    flow_carrier.getAdapter().setSelectedList(position);
                    try {
                        carrierTypeId = carrierTypeIds.get(position);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    return true;
                }
            });

        } else {
            ac.handleErrorCode(_activity, res.errorcode);
            if (!"no_error".equals(res.errorcode)) {
                UIHelper.t(_activity, "网络错误，请重新进入");
                finish();
            }
        }
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
//                if(labels==null) {
//                    labels = new ArrayList<>();
//                    lable_Ids = new ArrayList<>();
//                    labels.add(keywords);
//                    lable_Ids.add(lable_id);
//                }else {
//                    if (labels.contains(keywords)) {
//                        labels.add(keywords);
//                        lable_Ids.add(lable_id);
//                    } else {
//                        UIHelper.t(_activity, "已添加该标签");
//                    }
//                }
                intiLabels();
            }
        }
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

}
