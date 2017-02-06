package com.midian.ppaddress.itemtpl;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.midian.ppaddress.bean.CityMultiItem;
import com.midian.login.bean.ProvincesBean.City;
import com.midian.ppaddress.R;
import com.midian.ppaddress.ui.personal.ChooseAreaActivity;
import com.midian.ppaddress.ui.personal.ChooseCitysActivity;

import java.util.ArrayList;

import midian.baselib.utils.UIHelper;
import midian.baselib.view.BaseTpl;

public class HomeChooseCitysViewTpl extends BaseTpl<CityMultiItem> implements OnClickListener {
    private TextView cityName_tv;
    private String city_id,city_name;
    private ArrayList<City> citys = new ArrayList<City>();

    public HomeChooseCitysViewTpl(Context context) {
        super(context);
    }

    public HomeChooseCitysViewTpl(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void initView() {
        cityName_tv = (TextView) findView(R.id.city_name);

//        root.setOnClickListener(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.item_choose_citys_tpl;
    }

    @Override
    public void setBean(CityMultiItem bean, int position ) {
        if (bean.getItemViewType() == 1) {
            city_id = bean.data.getId();
            city_name = bean.data.getCity();
            cityName_tv.setText(bean.data.getCity());
        }
//        citys=bean.data.getCity();
    }

    @Override
    public void onClick(View v) {
      /*
        mBundle.putSerializable("citys", citys);
        UIHelper.jump(_activity, ChooseAreaActivity.class, mBundle);*/


    }

}

