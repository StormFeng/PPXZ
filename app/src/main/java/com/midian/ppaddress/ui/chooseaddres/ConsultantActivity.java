package com.midian.ppaddress.ui.chooseaddres;

import android.os.Bundle;

import com.midian.ppaddress.R;
import com.midian.ppaddress.datasource.ConsultantDataSource;
import com.midian.ppaddress.itemtpl.ConsultantTpl;

import java.util.ArrayList;

import midian.baselib.base.BaseListActivity;
import midian.baselib.shizhefei.view.listviewhelper.IDataAdapter;
import midian.baselib.shizhefei.view.listviewhelper.IDataSource;
import midian.baselib.utils.UIHelper;
import midian.baselib.widget.BaseLibTopbarView;

/**
 * 物业顾问列表
 * Created by chu on 2016/4/25.
 */
public class ConsultantActivity extends BaseListActivity {
    private BaseLibTopbarView topbar;
    private String carrierid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        carrierid = mBundle.getString("carrierid");//载体id
        System.out.println("物业顾问列表接收的carrierid");
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_consultant;
    }

    private void initView() {
        topbar = findView(R.id.topbar);
        topbar.setTitle("驻守物业顾问").setLeftImageButton(R.drawable.icon_back, UIHelper.finish(_activity));
    }

    @Override
    protected IDataSource<ArrayList> getDataSource() {
        return new ConsultantDataSource(_activity, mBundle.getString("carrierid"));
    }

    @Override
    protected Class getTemplateClass() {
        return ConsultantTpl.class;
    }

    @Override
    public void onEndRefresh(IDataAdapter adapter, Object result) {

    }

    @Override
    public void onEndLoadMore(IDataAdapter adapter, Object result) {

    }
}
