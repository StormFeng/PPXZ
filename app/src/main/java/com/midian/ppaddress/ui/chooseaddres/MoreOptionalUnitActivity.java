package com.midian.ppaddress.ui.chooseaddres;

import android.os.Bundle;

import com.midian.ppaddress.R;
import com.midian.ppaddress.datasource.MoreOptionalUnitDataSource;
import com.midian.ppaddress.datasource.OptionalUnitDataSource;
import com.midian.ppaddress.itemtpl.OptionalUnitTpl;

import java.util.ArrayList;

import midian.baselib.base.BaseListActivity;
import midian.baselib.shizhefei.view.listviewhelper.IDataAdapter;
import midian.baselib.shizhefei.view.listviewhelper.IDataSource;
import midian.baselib.utils.UIHelper;
import midian.baselib.widget.BaseLibTopbarView;

/**
 * Created by chu on 2016/6/2.
 */
public class MoreOptionalUnitActivity extends BaseListActivity{

    private BaseLibTopbarView topbar;
    private String carrierid;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        carrierid = mBundle.getString("carrierid");
        topbar = findView(R.id.topbar);
        topbar.setTitle("可选单元").setLeftText(" ",null).setLeftImageButton(R.drawable.icon_back, UIHelper.finish(_activity));
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_more_optional_unit;
    }

    @Override
    protected IDataSource<ArrayList> getDataSource() {
        return new MoreOptionalUnitDataSource(_activity,mBundle.getString("carrierid"));
    }



    @Override
    protected Class getTemplateClass() {
        return OptionalUnitTpl.class;
    }


    @Override
    public void onEndRefresh(IDataAdapter adapter, Object result) {

    }

    @Override
    public void onEndLoadMore(IDataAdapter adapter, Object result) {

    }
}
