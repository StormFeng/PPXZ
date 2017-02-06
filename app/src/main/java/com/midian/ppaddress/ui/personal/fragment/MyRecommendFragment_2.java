package com.midian.ppaddress.ui.personal.fragment;

import com.midian.ppaddress.R;
import com.midian.ppaddress.datasource.MyOrderFragment_2DataResource;
import com.midian.ppaddress.datasource.MyRecommendF_2DataResource;
import com.midian.ppaddress.itemtpl.MyOrderFragment_2Tpl;
import com.midian.ppaddress.itemtpl.MyRecommendF_2Tpl;

import java.util.ArrayList;

import midian.baselib.base.BaseListFragment;
import midian.baselib.shizhefei.view.listviewhelper.IDataSource;

/**
 * 首页
 * Created by chu on 2016/2/15.
 */
public class MyRecommendFragment_2 extends BaseListFragment {

    @Override
    protected int getLayoutId() {
        return R.layout.comman_list_item_notitle;
    }

    @Override
    protected IDataSource<ArrayList> getDataSource() {
        return new MyRecommendF_2DataResource(_activity);
    }

    @Override
    protected Class getTemplateClass() {
        return MyRecommendF_2Tpl.class;
    }
}
