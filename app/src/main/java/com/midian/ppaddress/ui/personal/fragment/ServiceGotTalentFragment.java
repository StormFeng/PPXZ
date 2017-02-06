package com.midian.ppaddress.ui.personal.fragment;

import com.midian.ppaddress.datasource.ServiceGotTalentDataSource;
import com.midian.ppaddress.itemtpl.ServiceGotTalentTpl;

import java.util.ArrayList;

import midian.baselib.base.BaseFragmentActivity;
import midian.baselib.base.BaseListFragment;
import midian.baselib.shizhefei.view.listviewhelper.IDataAdapter;
import midian.baselib.shizhefei.view.listviewhelper.IDataSource;

/**
 * 个人中心-我的评价--服务达人评价
 * Created by chu on 2016/5/3.
 */
public class ServiceGotTalentFragment extends BaseListFragment{
    @Override
    protected IDataSource<ArrayList> getDataSource() {
        return new ServiceGotTalentDataSource(_activity);
    }

    @Override
    protected Class getTemplateClass() {
        return ServiceGotTalentTpl.class;
    }

    @Override
    public void onResume() {
        super.onResume();
        listViewHelper.refresh();
    }

}
