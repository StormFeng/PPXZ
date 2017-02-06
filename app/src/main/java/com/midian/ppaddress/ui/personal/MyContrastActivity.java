package com.midian.ppaddress.ui.personal;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;

import com.midian.ppaddress.R;
import com.midian.ppaddress.datasource.MyContrastDataResource;
import com.midian.ppaddress.itemtpl.MyContrastTpl;

import java.util.ArrayList;
import java.util.List;

import midian.baselib.base.BaseFragmentActivity;
import midian.baselib.base.BaseListActivity;
import midian.baselib.shizhefei.view.listviewhelper.IDataSource;
import midian.baselib.utils.UIHelper;
import midian.baselib.widget.BaseLibTopbarView;

public class MyContrastActivity extends BaseListActivity{
    private BaseLibTopbarView topbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.comman_list_item;
    }

    @Override
    protected IDataSource<ArrayList> getDataSource() {
        return new MyContrastDataResource(_activity);
    }

    @Override
    protected Class getTemplateClass() {
        return MyContrastTpl.class;
    }

    private void initView() {
        topbar = findView(R.id.topbar);
        topbar.setTitle("对比类型").setLeftImageButton(R.drawable.icon_back, UIHelper.finish(_activity));
    }

    @Override
    protected void onResume() {
        super.onResume();
        listViewHelper.refresh();
    }
}
