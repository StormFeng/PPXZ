package com.midian.ppaddress.ui.personal;

import android.os.Bundle;
import android.widget.TextView;

import com.midian.ppaddress.R;
import com.midian.ppaddress.datasource.HelpDataSource;
import com.midian.ppaddress.itemtpl.HelpTpl;

import java.util.ArrayList;

import midian.baselib.base.BaseActivity;
import midian.baselib.base.BaseListActivity;
import midian.baselib.shizhefei.view.listviewhelper.IDataSource;
import midian.baselib.utils.UIHelper;
import midian.baselib.widget.BaseLibTopbarView;

/**
 * Created by Administrator on 2016/4/28.
 */
public class HelpActivity extends BaseListActivity{
    private BaseLibTopbarView topbar;
    private TextView tv_item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    @Override
    protected IDataSource<ArrayList> getDataSource() {
        return new HelpDataSource(_activity);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.comman_list_item;
    }

    @Override
    protected Class getTemplateClass() {
        return HelpTpl.class;
    }

    private void initView() {
        topbar=findView(R.id.topbar);
        topbar.setTitle("帮助中心").setLeftImageButton(R.drawable.icon_back, UIHelper.finish(this));
    }
}
