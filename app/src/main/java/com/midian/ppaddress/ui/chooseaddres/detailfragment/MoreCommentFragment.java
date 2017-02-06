package com.midian.ppaddress.ui.chooseaddres.detailfragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AbsListView;

import com.midian.ppaddress.R;
import com.midian.ppaddress.datasource.MoreCommentDataSource;
import com.midian.ppaddress.itemtpl.MoreCommentFragmentTpl;

import java.util.ArrayList;

import midian.baselib.base.BaseListFragment;
import midian.baselib.shizhefei.view.listviewhelper.IDataAdapter;
import midian.baselib.shizhefei.view.listviewhelper.IDataSource;
import midian.baselib.shizhefei.view.listviewhelper.OnStateChangeListener;

/**
 * 用户点评---更多点评页
 * Created by chu on 2016/3/23.
 */
public class MoreCommentFragment extends BaseListFragment  {
    private String carrierid;

    public String getCarrierid() {
        return carrierid;
    }

    public void setCarrierid(String carrierid) {
        this.carrierid = carrierid;
    }



 /*   @Override
    protected int getLayoutId() {
        return R.layout.fragment_more_comment;
    }*/

    @Override
    protected IDataSource<ArrayList> getDataSource() {
        return new MoreCommentDataSource(_activity, carrierid);
    }

    @Override
    protected Class getTemplateClass() {
        return MoreCommentFragmentTpl.class;
    }



}
