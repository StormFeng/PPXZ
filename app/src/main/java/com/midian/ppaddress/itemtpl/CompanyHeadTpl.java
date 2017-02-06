package com.midian.ppaddress.itemtpl;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import com.midian.ppaddress.R;

import midian.baselib.bean.NetResult;
import midian.baselib.view.BaseTpl;

/**
 * 首页上部分
 * Created by chu on 2016/2/23.
 */
public class CompanyHeadTpl extends BaseTpl implements View.OnClickListener {

    public CompanyHeadTpl(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CompanyHeadTpl(Context context) {
        super(context);
    }

    @Override
    protected void initView() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.item_company_detail_tpl;
    }

    @Override
    public void setBean(NetResult bean, int position) {
    }

    @Override
    public void onClick(View view) {
    }
}
