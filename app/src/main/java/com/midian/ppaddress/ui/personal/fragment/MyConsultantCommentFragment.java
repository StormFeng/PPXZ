package com.midian.ppaddress.ui.personal.fragment;

import com.midian.ppaddress.datasource.MyConsultantCommentDataSource;
import com.midian.ppaddress.itemtpl.MyConsultantCommentListTpl;

import java.util.ArrayList;

import midian.baselib.base.BaseListFragment;
import midian.baselib.base.BaseMultiTypeListFragment;
import midian.baselib.shizhefei.view.listviewhelper.IDataAdapter;
import midian.baselib.shizhefei.view.listviewhelper.IDataSource;

/**
 * 我的评价--对物业顾问的评价
 * Created by chu on 2016/5/3.
 */
public class MyConsultantCommentFragment extends BaseListFragment{


    @Override
    protected IDataSource<ArrayList> getDataSource() {
        return new MyConsultantCommentDataSource(_activity);
    }

    @Override
    protected Class getTemplateClass() {
        return MyConsultantCommentListTpl.class;
    }

    @Override
    public void onResume() {
        super.onResume();
        listViewHelper.refresh();
    }
}
