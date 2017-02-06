package com.midian.ppaddress.ui.chooseaddres;

import android.os.Bundle;

import com.midian.ppaddress.R;
import com.midian.ppaddress.datasource.ChooseAddressActivityDataSource;
import com.midian.ppaddress.itemtpl.ChooseAddressActivityTpl;

import java.util.ArrayList;

import midian.baselib.base.BaseListActivity;
import midian.baselib.shizhefei.view.listviewhelper.IDataSource;
import midian.baselib.utils.UIHelper;
import midian.baselib.widget.BaseLibTopbarView;

/**
 * 园区选址列表
 * Created by chu on 2016/4/21.
 */
public class ChooseAddressActivity extends BaseListActivity{
    private BaseLibTopbarView topbar;
    private String title;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        title = mBundle.getString("title");
        initView();
    }

    private void initView() {
        topbar = findView(R.id.topbar);
        topbar.setTitle(title).setLeftText(" ",null).setLeftImageButton(R.drawable.icon_back, UIHelper.finish(_activity));
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_choose_address;
    }

    @Override
    protected IDataSource<ArrayList> getDataSource() {
        return new ChooseAddressActivityDataSource(_activity,mBundle.getString("carrierid"));
    }

    @Override
    protected Class getTemplateClass() {
        return ChooseAddressActivityTpl.class;//载体详情-----进入选址
    }


}
