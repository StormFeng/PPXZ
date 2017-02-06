package com.midian.ppaddress.itemtpl;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.midian.ppaddress.R;
import com.midian.ppaddress.bean.BusinessLocationCityCountysBean;
import com.midian.ppaddress.bean.BusinessLocationProvinceCitysBean;
import com.midian.ppaddress.ui.personal.ChooseCityActivity_2;
import com.midian.ppaddress.utils.AppUtil;

import java.io.FileNotFoundException;

import midian.baselib.api.ApiCallback;
import midian.baselib.bean.NetResult;
import midian.baselib.utils.UIHelper;
import midian.baselib.view.BaseTpl;

public class ChooseCitysView_2Tpl extends BaseTpl<BusinessLocationCityCountysBean.Data> implements OnClickListener, ApiCallback {
    private TextView cityName_tv;
    private String provinceid;
    private String city_id;
    private String countyid;
    private String declaration;
    private String position;

    public ChooseCitysView_2Tpl(Context context) {
        super(context);
    }

    @Override
    protected void initView() {
        cityName_tv=findView(R.id.city_name);
        provinceid=_activity.getIntent().getStringExtra("provinceid");
        city_id=_activity.getIntent().getStringExtra("city_id");
        declaration=_activity.getIntent().getStringExtra("declaration");
        position=_activity.getIntent().getStringExtra("position");
        root.setOnClickListener(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.item_choose_citys_tpl;
    }

    @Override
    public void setBean(BusinessLocationCityCountysBean.Data bean, int position) {
        if(bean!=null){
            cityName_tv.setText(bean.getCounty());
            countyid=bean.getId();
        }
    }

    @Override
    public void onClick(View v) {
        int userId=Integer.valueOf(ac.user_id);
        if("2".equals(ac.getProperty("user_type"))) {
            position=null;
        }else if("4".equals(ac.getProperty("user_type"))){
        }else{
            declaration=null;
            position=null;
        }
        try {
            AppUtil.getPpApiClient(ac).memberMembershipInfoUpdate(userId, null, ac.sex, provinceid, city_id, countyid,
                    ac.getProperty("user_type"), null, declaration, position, null, this);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onApiStart(String tag) {

    }

    @Override
    public void onApiLoading(long count, long current, String tag) {

    }

    @Override
    public void onApiSuccess(NetResult res, String tag) {
        UIHelper.t(_activity,res.message);
        _activity.setResult(Activity.RESULT_OK);
        _activity.finish();
    }

    @Override
    public void onApiFailure(Throwable t, int errorNo, String strMsg, String tag) {

    }

    @Override
    public void onParseError(String tag) {

    }
}
