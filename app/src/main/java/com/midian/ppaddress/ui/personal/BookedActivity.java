package com.midian.ppaddress.ui.personal;

import android.os.Bundle;

import com.midian.ppaddress.R;
import com.midian.ppaddress.datasource.BookedDataResource;
import com.midian.ppaddress.datasource.BookingDataResource;
import com.midian.ppaddress.itemtpl.BookedListtpl;

import java.util.ArrayList;

import midian.baselib.base.BaseListActivity;
import midian.baselib.shizhefei.view.listviewhelper.IDataSource;
import midian.baselib.utils.UIHelper;
import midian.baselib.widget.BaseLibTopbarView;

public class BookedActivity extends BaseListActivity {
    private BaseLibTopbarView topbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        topbar=findView(R.id.topbar);
        topbar.setTitle("已签约").setLeftImageButton(R.drawable.icon_back, UIHelper.finish(_activity));
    }

    @Override
    protected int getLayoutId() {
        return R.layout.comman_list_item;
    }

    @Override
    protected IDataSource<ArrayList> getDataSource() {
        return new BookedDataResource(_activity);
    }

    @Override
    protected Class getTemplateClass() {
        return BookedListtpl.class;
    }
}
