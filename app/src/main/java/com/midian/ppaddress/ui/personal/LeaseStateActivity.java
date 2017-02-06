package com.midian.ppaddress.ui.personal;

import android.content.Intent;
import android.os.Bundle;

import com.midian.ppaddress.R;
import com.midian.ppaddress.datasource.LeaseStateDataResource;
import com.midian.ppaddress.datasource.MyPropertyDataResource;
import com.midian.ppaddress.itemtpl.LeaseStateTpl;
import com.midian.ppaddress.itemtpl.MyPropertyTpl;

import java.util.ArrayList;

import midian.baselib.base.BaseListActivity;
import midian.baselib.shizhefei.view.listviewhelper.IDataSource;
import midian.baselib.utils.UIHelper;
import midian.baselib.widget.BaseLibTopbarView;

public class LeaseStateActivity extends BaseListActivity{

    private BaseLibTopbarView topbar;
    private String carrierid;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        topbar=findView(R.id.topbar);
        Intent intent=getIntent();
        String title=intent.getStringExtra("title");
        topbar.setTitle(title).setLeftImageButton(R.drawable.icon_back,UIHelper.finish(this));
    }

    @Override
    protected int getLayoutId() {
        return R.layout.comman_list_item;
    }

    @Override
    protected IDataSource<ArrayList> getDataSource() {
        carrierid=getIntent().getStringExtra("carrierid");
        return new LeaseStateDataResource(carrierid,_activity);
    }

    @Override
    protected Class getTemplateClass() {
        return LeaseStateTpl.class;
    }
}
