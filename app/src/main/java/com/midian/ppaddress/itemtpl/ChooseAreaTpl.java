package com.midian.ppaddress.itemtpl;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.midian.ppaddress.bean.CityMultiItem;
import com.midian.ppaddress.R;

import midian.baselib.view.BaseTpl;

public class ChooseAreaTpl extends BaseTpl<CityMultiItem> implements OnClickListener {

    private TextView areaName_tv;
    private String city_name, city_id;

    public ChooseAreaTpl(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ChooseAreaTpl(Context context) {
        super(context);
    }

    @Override
    protected void initView() {
        areaName_tv = findView(R.id.area_name);
        root.setOnClickListener(this);

    }

    @Override
    protected int getLayoutId() {
        return R.layout.item_choose_area_tpl;
    }

    @Override
    public void setBean(CityMultiItem bean, int position ) {

        city_name = bean.city.getCity_name();
        city_id = bean.city.getCity_id();
//        areaName_tv.setText("标题455");
        areaName_tv.setText(city_name);
    }

    @Override
    public void onClick(View v) {
//        ac.setProperty("city_name",city_name);
        ac.city_name = city_name;
        ac.city_id = city_id;
        ac.saveCityName(city_name,city_id);
//        Bundle mBundle = new Bundle();
//        mBundle.putString("city_id", city_id);
//        UIHelper.jump(_activity, ChooseAdvertActivity.class, mBundle);
        _activity.finish();
    }


}
