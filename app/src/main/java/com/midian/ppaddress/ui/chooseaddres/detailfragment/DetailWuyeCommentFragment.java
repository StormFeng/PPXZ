package com.midian.ppaddress.ui.chooseaddres.detailfragment;

import com.midian.ppaddress.datasource.DetailWuYeFragmentDataSource;
import com.midian.ppaddress.itemtpl.DetailWuyeCommentFragmentTpl;

import java.util.ArrayList;

import midian.baselib.base.BaseListFragment;
import midian.baselib.shizhefei.view.listviewhelper.IDataSource;

/**
 * 物业点评
 * Created by chu on 2016/3/23.
 */
public class DetailWuyeCommentFragment extends BaseListFragment {

    private String carrierid;

    public String getCarrierid() {
        return carrierid;
    }

    public void setCarrierid(String carrierid) {
        this.carrierid = carrierid;
    }

    @Override
    protected IDataSource<ArrayList> getDataSource() {
        return new DetailWuYeFragmentDataSource(_activity,carrierid);
    }

    @Override
    protected Class getTemplateClass() {
        return DetailWuyeCommentFragmentTpl.class;
    }

}
