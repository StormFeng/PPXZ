package com.midian.ppaddress.itemtpl;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.midian.login.bean.ProvincesBean.City;
import com.midian.ppaddress.R;
import com.midian.ppaddress.bean.BusinessLocationProvincesBean;
import com.midian.ppaddress.bean.CityMultiItem;
import com.midian.ppaddress.ui.personal.ChooseCityActivity_1;

import java.util.ArrayList;

import midian.baselib.utils.UIHelper;
import midian.baselib.view.BaseTpl;

public class ChooseCitysViewTpl extends BaseTpl<BusinessLocationProvincesBean.Data> implements OnClickListener {
    private TextView cityName_tv;
    private String city_id,city_name;
    private String declaration;
    private String position;
    private ArrayList<City> citys = new ArrayList<City>();

    public ChooseCitysViewTpl(Context context) {
        super(context);
    }

    public ChooseCitysViewTpl(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void initView() {
        cityName_tv = findView(R.id.city_name);
        declaration=_activity.getIntent().getStringExtra("declaration");
        position=_activity.getIntent().getStringExtra("position");
        root.setOnClickListener(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.item_choose_citys_tpl;
    }

    @Override
    public void setBean(BusinessLocationProvincesBean.Data bean, int position) {
        city_id = bean.getId();
        city_name = bean.getProvince();
        cityName_tv.setText(city_name);
    }

    @Override
    public void onClick(View v) {
        Bundle mBundle = new Bundle();
        mBundle.putString("provinceid",city_id);
        mBundle.putString("declaration",declaration);
        mBundle.putString("position",position);
        UIHelper.jumpForResult(_activity,ChooseCityActivity_1.class,mBundle,20002);
    }
}
