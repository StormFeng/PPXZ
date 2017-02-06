package com.midian.ppaddress.itemtpl;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import com.midian.ppaddress.R;
import com.midian.ppaddress.bean.BusinessLocationProvinceCitysBean;
import com.midian.ppaddress.ui.personal.ChooseCityActivity_1;
import com.midian.ppaddress.ui.personal.ChooseCityActivity_2;

import midian.baselib.utils.UIHelper;
import midian.baselib.view.BaseTpl;

public class ChooseCitysView_1Tpl extends BaseTpl<BusinessLocationProvinceCitysBean.Data> implements OnClickListener {
    private TextView cityName_tv;
    private String city_id;
    private String provinceid;
    private String declaration;
    private String position;

    public ChooseCitysView_1Tpl(Context context) {
        super(context);
    }

    @Override
    protected void initView() {
        cityName_tv=findView(R.id.city_name);
        root.setOnClickListener(this);
        provinceid=_activity.getIntent().getStringExtra("provinceid");
        declaration=_activity.getIntent().getStringExtra("declaration");
        position=_activity.getIntent().getStringExtra("position");
    }

    @Override
    protected int getLayoutId() {
        return R.layout.item_choose_citys_tpl;
    }

    @Override
    public void setBean(BusinessLocationProvinceCitysBean.Data bean, int position) {
        if(bean!=null){
            cityName_tv.setText(bean.getCity());
            city_id=bean.getId();
        }
    }

    @Override
    public void onClick(View v) {
        Bundle mBundle = new Bundle();
        mBundle.putString("city_id",city_id);
        mBundle.putString("provinceid",provinceid);
        mBundle.putString("declaration",declaration);
        mBundle.putString("position",position);
        UIHelper.jumpForResult(_activity,ChooseCityActivity_2.class,mBundle,20002);
    }
}
