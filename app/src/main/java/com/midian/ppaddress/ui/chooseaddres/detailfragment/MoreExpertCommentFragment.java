package com.midian.ppaddress.ui.chooseaddres.detailfragment;

import com.midian.ppaddress.datasource.MoreExpertsCommentDataSource;
import com.midian.ppaddress.itemtpl.MoreExpertsCommentTpl;

import java.util.ArrayList;

import midian.baselib.base.BaseListFragment;
import midian.baselib.shizhefei.view.listviewhelper.IDataSource;

/**
 * 更多专家点评
 * Created by chu on 2016/3/24.
 */
public class MoreExpertCommentFragment extends BaseListFragment{   private String carrierid;

    public String getCarrierid() {
        return carrierid;
    }

    public void setCarrierid(String carrierid) {
        this.carrierid = carrierid;
    }

    @Override
    protected IDataSource<ArrayList> getDataSource() {
        return new MoreExpertsCommentDataSource(_activity,carrierid);
    }

    @Override
    protected Class getTemplateClass() {
        return MoreExpertsCommentTpl.class;
    }

}
