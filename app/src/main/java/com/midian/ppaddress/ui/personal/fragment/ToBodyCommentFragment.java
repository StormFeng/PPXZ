package com.midian.ppaddress.ui.personal.fragment;

import com.midian.ppaddress.datasource.ToBodyCommentDataSource;
import com.midian.ppaddress.itemtpl.ToBodyCommentTpl;

import java.util.ArrayList;

import midian.baselib.base.BaseFragmentActivity;
import midian.baselib.base.BaseListFragment;
import midian.baselib.shizhefei.view.listviewhelper.IDataAdapter;
import midian.baselib.shizhefei.view.listviewhelper.IDataSource;

/**
 * Created by chu on 2016/5/5.
 */
public class ToBodyCommentFragment extends BaseListFragment{
    @Override
    protected IDataSource<ArrayList> getDataSource() {
        return new ToBodyCommentDataSource(_activity);
    }

    @Override
    protected Class getTemplateClass() {
        return ToBodyCommentTpl.class;
    }


}
