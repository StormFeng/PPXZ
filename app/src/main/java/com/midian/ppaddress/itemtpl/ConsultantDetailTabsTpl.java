package com.midian.ppaddress.itemtpl;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import com.midian.ppaddress.R;

import midian.baselib.bean.NetResult;
import midian.baselib.view.BaseTpl;

/**
 * Created by chu on 2016/4/27.
 */
public class ConsultantDetailTabsTpl extends BaseTpl implements View.OnClickListener {
    private View tab_ll;
    private TextView tab1, tab2;
    private int tabIndex;

    public ConsultantDetailTabsTpl(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ConsultantDetailTabsTpl(Context context) {
        super(context);
    }

    @Override
    protected void initView() {
        tab_ll = findView(R.id.tab_ll);
        tab1 = (TextView) findView(R.id.tab1);
        tab2 = (TextView) findView(R.id.tab2);
        tab1.setOnClickListener(this);
        tab2.setOnClickListener(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.item_consultant_detail_tabs_tpl;
    }

    @Override
    public void setBean(NetResult bean, int position) {

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.tab1:

                tabIndex = 0;
                break;
            case R.id.tab2:
                tabIndex = 1;
                break;
        }
    }
}
