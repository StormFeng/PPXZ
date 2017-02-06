package com.midian.ppaddress.ui.personal;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;

import com.midian.ppaddress.R;
import com.midian.ppaddress.bean.FeedBackListBean;
import com.midian.ppaddress.datasource.FeedBackDataSource;
import com.midian.ppaddress.itemtpl.FeedBackTpl;

import java.util.ArrayList;

import midian.baselib.base.BaseActivity;
import midian.baselib.base.BaseListActivity;
import midian.baselib.shizhefei.view.listviewhelper.IDataSource;
import midian.baselib.utils.UIHelper;
import midian.baselib.widget.BaseLibTopbarView;

/**
 * Created by Administrator on 2016/4/28.
 */
public class FeedBackActivity_2 extends BaseListActivity{
    private BaseLibTopbarView topbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.comman_list_item;
    }

    @Override
    protected IDataSource<ArrayList> getDataSource() {
        return new FeedBackDataSource(_activity);
    }

    @Override
    protected Class getTemplateClass() {
        return FeedBackTpl.class;
    }

    private void initView() {
        topbar=findView(R.id.topbar);
        topbar.setTitle("投诉反馈").setLeftImageButton(R.drawable.icon_back, UIHelper.finish(this));
    }

    @Override
    public void onItemClick(AdapterView parent, View view, int position, long id) {
        super.onItemClick(parent, view, position, id);
        FeedBackListBean bean = (FeedBackListBean) resultList.get(position);
        Bundle bundle=new Bundle();
        bundle.putString("type",bean.getData().get(position).getName());
        bundle.putString("classifyid",bean.getData().get(position).getId());
        setResult(RESULT_OK,bundle);
        finish();
    }
}
