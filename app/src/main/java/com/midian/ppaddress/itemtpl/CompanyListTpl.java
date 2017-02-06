package com.midian.ppaddress.itemtpl;

import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import com.midian.ppaddress.R;
import com.midian.ppaddress.ui.chooseaddres.ParkDetailActivity;

import midian.baselib.bean.NetResult;
import midian.baselib.utils.UIHelper;
import midian.baselib.view.BaseTpl;
import midian.baselib.widget.CircleImageView;

/**
 * 首页推荐列表
 * Created by chu on 2016/2/23.
 */
public class CompanyListTpl extends BaseTpl implements View.OnClickListener {


    public CompanyListTpl(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CompanyListTpl(Context context) {
        super(context);
    }

    @Override
    protected void initView() {
    }

    @Override
    protected int getLayoutId() {
        return R.layout.item_company_evaluate;
    }

    @Override
    public void setBean(NetResult bean, int position) {

    }

    @Override
    public void onClick(View view) {

    }
}
