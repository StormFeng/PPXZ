package com.midian.ppaddress.itemtpl;

import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import com.midian.ppaddress.R;
import com.midian.ppaddress.bean.FeedBackListBean;
import com.midian.ppaddress.bean.HelpListBean;
import com.midian.ppaddress.ui.personal.HelpActivity_1;

import midian.baselib.utils.UIHelper;
import midian.baselib.view.BaseTpl;

public class HelpTpl extends BaseTpl<HelpListBean> implements View.OnClickListener{

    private TextView tv_item;
    private String url;

    public HelpTpl(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public HelpTpl(Context context) {
        super(context);
    }

    @Override
    protected void initView() {
        tv_item=findView(R.id.tv_item);
        root.setOnClickListener(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_help;
    }

    @Override
    public void setBean(HelpListBean bean, int position) {
        if(bean.isOK()){
            tv_item.setText(bean.getData().getList().get(position).getTitle());
            url=bean.getData().getList().get(position).getUrl();
        }
    }

    @Override
    public void onClick(View view) {
        Bundle bundle=new Bundle();
        bundle.putString("url",url);
        UIHelper.jump(_activity, HelpActivity_1.class,bundle);
    }
}
