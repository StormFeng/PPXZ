package com.midian.ppaddress.ui.chooseaddres.detailfragment;

import com.midian.ppaddress.bean.BusinessCommentsMembersBean;
import com.midian.ppaddress.datasource.CommentFragmentDataSource;
import com.midian.ppaddress.itemtpl.CommentFragmentTpl;

import java.util.ArrayList;

import midian.baselib.base.BaseListFragment;
import midian.baselib.shizhefei.view.listviewhelper.IDataAdapter;
import midian.baselib.shizhefei.view.listviewhelper.IDataSource;

/**
 * 用户点评fragment
 * Created by chu on 2016/3/23.
 */
public class DetailCommentFragment extends BaseListFragment{

    private String carrierid;


    public String getCarrierid() {
        return carrierid;
    }

    public void setCarrierid(String carrierid) {
        this.carrierid = carrierid;
    }
    @Override
    protected IDataSource<ArrayList> getDataSource() {
        return new CommentFragmentDataSource(_activity,  carrierid);
    }

    @Override
    protected Class getTemplateClass() {
        return CommentFragmentTpl.class;
    }


    @Override
    public void onEndRefresh(IDataAdapter adapter, Object result) {

    }

    @Override
    public void onEndLoadMore(IDataAdapter adapter, Object result) {

    }
}
