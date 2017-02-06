package com.midian.ppaddress.ui.chooseaddres.detailfragment;

import com.midian.ppaddress.datasource.ExpertsFragmentDataSource;
import com.midian.ppaddress.itemtpl.ExpertsFragmentTpl;

import java.util.ArrayList;

import midian.baselib.base.BaseListFragment;
import midian.baselib.shizhefei.view.listviewhelper.IDataSource;

/**
 * 专家点评
 * Created by chu on 2016/3/23.
 */
public class expertsCommentFragment extends BaseListFragment {

    private String carrierid;

    public String getCarrierid() {
        return carrierid;
    }

    public void setCarrierid(String carrierid) {
        this.carrierid = carrierid;
    }

    @Override
    protected IDataSource<ArrayList> getDataSource() {
        return new ExpertsFragmentDataSource(_activity,carrierid);
    }

    @Override
    protected Class getTemplateClass() {
        return ExpertsFragmentTpl.class;
    }

}
