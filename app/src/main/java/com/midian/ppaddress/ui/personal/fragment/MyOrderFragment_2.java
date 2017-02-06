package com.midian.ppaddress.ui.personal.fragment;

import com.midian.ppaddress.R;
import com.midian.ppaddress.datasource.BookFragment_1DataResource;
import com.midian.ppaddress.datasource.MyOrderFragment_2DataResource;
import com.midian.ppaddress.itemtpl.BookFragment_1Tpl;
import com.midian.ppaddress.itemtpl.MyOrderFragment_2Tpl;

import java.util.ArrayList;

import midian.baselib.base.BaseListFragment;
import midian.baselib.shizhefei.view.listviewhelper.IDataSource;

/**
 * 我的订单--已完成
 */
public class MyOrderFragment_2 extends BaseListFragment {

    @Override
    protected int getLayoutId() {
        return R.layout.comman_list_item_notitle;
    }

    @Override
    protected IDataSource<ArrayList> getDataSource() {
        return new MyOrderFragment_2DataResource(_activity);
    }

    @Override
    protected Class getTemplateClass() {
        return MyOrderFragment_2Tpl.class;
    }
}
