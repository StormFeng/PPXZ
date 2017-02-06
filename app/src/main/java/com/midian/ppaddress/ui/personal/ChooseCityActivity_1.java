package com.midian.ppaddress.ui.personal;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;

import com.midian.ppaddress.R;
import com.midian.ppaddress.bean.BusinessLocationCountyListBean;
import com.midian.ppaddress.datasource.ChooseCitysDataSource_1;
import com.midian.ppaddress.datasource.MyPropertyDataResource;
import com.midian.ppaddress.itemtpl.ChooseCitysView_1Tpl;
import com.midian.ppaddress.itemtpl.MyPropertyTpl;

import java.util.ArrayList;

import midian.baselib.base.BaseListActivity;
import midian.baselib.shizhefei.view.listviewhelper.IDataSource;
import midian.baselib.utils.UIHelper;
import midian.baselib.widget.BaseLibTopbarView;

public class ChooseCityActivity_1 extends BaseListActivity{

    private BaseLibTopbarView topbar;
    private String provinceid;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        topbar = (BaseLibTopbarView) findViewById(com.midian.login.R.id.topbar);
        topbar.setTitle("地区");
        topbar.setLeftImageButton(com.midian.login.R.drawable.icon_back, null).setLeftText("返回", UIHelper.finish(_activity));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK){
            setResult(RESULT_OK);
            finish();
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.comman_list_item;
    }
    @Override
    protected IDataSource<ArrayList> getDataSource() {
        provinceid = mBundle.getString("provinceid");
        return new ChooseCitysDataSource_1(_activity,provinceid);
    }
    @Override
    protected Class getTemplateClass() {
        return ChooseCitysView_1Tpl.class;
    }
}

