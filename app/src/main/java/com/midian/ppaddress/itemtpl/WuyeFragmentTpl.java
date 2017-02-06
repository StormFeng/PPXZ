package com.midian.ppaddress.itemtpl;

import android.content.Context;
import android.util.AttributeSet;

import com.midian.ppaddress.R;

import midian.baselib.bean.NetResult;
import midian.baselib.view.BaseTpl;

/**
 * Created by chu on 2016/4/21.
 */
public class WuyeFragmentTpl extends BaseTpl{
    public WuyeFragmentTpl(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public WuyeFragmentTpl(Context context) {
        super(context);
    }

    @Override
    protected void initView() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.item_wu_ye_fragment_tpl;
    }

    @Override
    public void setBean(NetResult bean, int position) {

    }
}
