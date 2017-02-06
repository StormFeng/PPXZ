package com.midian.ppaddress.itemtpl;

import android.content.Context;
import android.util.AttributeSet;

import com.midian.ppaddress.R;

import midian.baselib.bean.NetResult;
import midian.baselib.view.BaseTpl;

/**
 * 详情-评论条目
 * Created by chu on 2016/4/25.
 */
public class BookFragment_2Tpl extends BaseTpl{

    public BookFragment_2Tpl(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public BookFragment_2Tpl(Context context) {
        super(context);
    }

    @Override
    protected void initView() {
    }

    @Override
    protected int getLayoutId() {
        return R.layout.item_bookfragment_1;
    }

    @Override
    public void setBean(NetResult bean, int position) {
    }
}
