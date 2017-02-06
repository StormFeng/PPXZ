package com.midian.ppaddress.ui.personal;

import android.os.Bundle;

import com.midian.ppaddress.R;
import com.midian.ppaddress.datasource.ChooseCitysDataSource_1;
import com.midian.ppaddress.datasource.ChooseCitysDataSource_2;
import com.midian.ppaddress.itemtpl.ChooseCitysView_1Tpl;
import com.midian.ppaddress.itemtpl.ChooseCitysView_2Tpl;

import java.util.ArrayList;

import midian.baselib.base.BaseListActivity;
import midian.baselib.shizhefei.view.listviewhelper.IDataSource;
import midian.baselib.utils.UIHelper;
import midian.baselib.widget.BaseLibTopbarView;

public class ChooseCityActivity_2 extends BaseListActivity{

    private BaseLibTopbarView topbar;
    private String cityid;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        topbar = (BaseLibTopbarView) findViewById(com.midian.login.R.id.topbar);
        topbar.setTitle("地区");
        topbar.setLeftImageButton(com.midian.login.R.drawable.icon_back, null).setLeftText("返回", UIHelper.finish(_activity));
    }

    @Override
    protected int getLayoutId() {
        return R.layout.comman_list_item;
    }
    @Override
    protected IDataSource<ArrayList> getDataSource() {
        cityid = mBundle.getString("city_id");
        return new ChooseCitysDataSource_2(_activity,cityid);
    }
    @Override
    protected Class getTemplateClass() {
        return ChooseCitysView_2Tpl.class;
    }
}

