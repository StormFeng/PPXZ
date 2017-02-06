package com.midian.ppaddress.itemtpl;

import android.content.Context;
import android.util.AttributeSet;

import com.midian.ppaddress.R;

import midian.baselib.bean.NetResult;
import midian.baselib.view.BaseTpl;

/**
 * Created by chu on 2016/5/5.
 */
public class TradingFragmentTpl extends BaseTpl{
    public TradingFragmentTpl(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TradingFragmentTpl(Context context) {
        super(context);
    }

    @Override
    protected void initView() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.item_trading_fragment_tpl;
    }

    @Override
    public void setBean(NetResult bean, int position) {

    }
}
