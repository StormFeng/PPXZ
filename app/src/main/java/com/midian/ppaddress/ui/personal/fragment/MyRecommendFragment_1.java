package com.midian.ppaddress.ui.personal.fragment;

import com.midian.ppaddress.R;
import com.midian.ppaddress.datasource.MyOrderFragment_1DataResource;
import com.midian.ppaddress.datasource.MyRecommendF_1DataResource;
import com.midian.ppaddress.itemtpl.MyOrderFragment_1Tpl;
import com.midian.ppaddress.itemtpl.MyRecommendF_1Tpl;
import com.midian.ppaddress.itemtpl.MyRecommendF_2Tpl;

import java.util.ArrayList;

import midian.baselib.base.BaseListFragment;
import midian.baselib.shizhefei.view.listviewhelper.IDataSource;

public class MyRecommendFragment_1 extends BaseListFragment {

    @Override
    protected int getLayoutId() {
        return R.layout.comman_list_item_notitle;
    }

    @Override
    protected IDataSource<ArrayList> getDataSource() {
        return new MyRecommendF_1DataResource(_activity);
    }

    @Override
    protected Class getTemplateClass() {
        return MyRecommendF_1Tpl.class;
    }
}
