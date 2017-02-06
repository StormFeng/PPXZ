package com.midian.ppaddress.ui.chooseaddres;

import com.midian.ppaddress.datasource.ContrastDataSource;
import com.midian.ppaddress.itemtpl.AddContrastTpl;

import java.util.ArrayList;

import midian.baselib.base.BaseListActivity;
import midian.baselib.shizhefei.view.listviewhelper.IDataAdapter;
import midian.baselib.shizhefei.view.listviewhelper.IDataSource;

/**
 * Created by chu on 2016/6/1.
 */
public class AddContrastActivity extends BaseListActivity{
    @Override
    protected IDataSource<ArrayList> getDataSource() {
        return new ContrastDataSource(_activity, mBundle.getString("carriertype"));
    }

    @Override
    protected Class getTemplateClass() {
        return AddContrastTpl.class;
    }

    @Override
    public void onEndRefresh(IDataAdapter adapter, Object result) {

    }

    @Override
    public void onEndLoadMore(IDataAdapter adapter, Object result) {

    }
}
