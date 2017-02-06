package com.midian.ppaddress.ui.company;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.midian.ppaddress.R;
import com.midian.ppaddress.datasource.ChooseWaiterActivityDataSource;
import com.midian.ppaddress.itemtpl.ChooseWaiterActivityTpl;
import java.util.ArrayList;
import midian.baselib.base.BaseListActivity;
import midian.baselib.shizhefei.view.listviewhelper.IDataSource;
import midian.baselib.utils.UIHelper;
import midian.baselib.widget.BaseLibTopbarView;

/**
 * Created by Administrator on 2016/4/26.
 */
public class ChooseWaiterActivity extends BaseListActivity{
    private BaseLibTopbarView topbar;
    private String agencyId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();

    }
    private void initView() {
        topbar = findView(R.id.topbar);
        topbar.setTitle("服务达人").setLeftImageButton(R.drawable.icon_back, UIHelper.finish(_activity));
    }

    @Override
    protected IDataSource<ArrayList> getDataSource() {
        agencyId=getIntent().getStringExtra("agencyId");
        return new ChooseWaiterActivityDataSource(_activity,agencyId);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_choose_waiter;
    }

    @Override
    protected Class getTemplateClass() {
        return ChooseWaiterActivityTpl.class;
    }

}
