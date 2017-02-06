package com.midian.ppaddress.ui.personal;

import android.os.Bundle;

import com.midian.ppaddress.R;
import com.midian.ppaddress.datasource.MyBuildingResourceDataResource;
import com.midian.ppaddress.datasource.MyPropertyDataResource;
import com.midian.ppaddress.itemtpl.MyBuildingResourceTpl;
import com.midian.ppaddress.itemtpl.MyPropertyTpl;
import com.midian.ppaddress.utils.AppUtil;

import java.util.ArrayList;

import midian.baselib.base.BaseListActivity;
import midian.baselib.shizhefei.view.listviewhelper.IDataSource;
import midian.baselib.utils.UIHelper;
import midian.baselib.widget.BaseLibTopbarView;

/**
 * 我的物业
 */
public class MyPropertyActivity extends BaseListActivity{

    private BaseLibTopbarView topbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        topbar=findView(R.id.topbar);
        topbar.setTitle("我的物业").setLeftImageButton(R.drawable.icon_back,UIHelper.finish(this));

    }

    @Override
    protected int getLayoutId() {
        return R.layout.comman_list_item;
    }

    @Override
    protected IDataSource<ArrayList> getDataSource() {
        return new MyPropertyDataResource(_activity);
    }

    @Override
    protected Class getTemplateClass() {
        return MyPropertyTpl.class;
    }
}
