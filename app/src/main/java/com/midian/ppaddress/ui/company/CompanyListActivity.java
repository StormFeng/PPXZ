package com.midian.ppaddress.ui.company;

import android.os.Bundle;

import com.midian.ppaddress.R;
import com.midian.ppaddress.datasource.CompanyListDataSource;
import com.midian.ppaddress.itemtpl.CompanyListActivityTpl;

import java.util.ArrayList;
import midian.baselib.base.BaseListActivity;
import midian.baselib.shizhefei.view.listviewhelper.IDataSource;
import midian.baselib.utils.UIHelper;
import midian.baselib.widget.BaseLibTopbarView;

/**
 * Created by Administrator on 2016/4/26.
 */
public class CompanyListActivity extends BaseListActivity{
    private BaseLibTopbarView topbar;
    private int industryid;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();

    }
    private void initView() {
        topbar = findView(R.id.topbar);
        topbar.setTitle(mBundle.getString("title")).setLeftImageButton(R.drawable.icon_back, UIHelper.finish(_activity));
    }

    @Override
    protected IDataSource<ArrayList> getDataSource() {
        industryid=mBundle.getInt("industryid");
        return new CompanyListDataSource(_activity,industryid);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_companylist;
    }

    @Override
    protected Class getTemplateClass() {
        return CompanyListActivityTpl.class;
    }

}
