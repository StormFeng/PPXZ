package com.midian.ppaddress.itemtpl;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.midian.ppaddress.R;

import midian.baselib.bean.NetResult;
import midian.baselib.utils.UIHelper;
import midian.baselib.view.BaseTpl;
import midian.baselib.widget.FlowLayout;

/**
 * Created by chu on 2016/4/27.
 */
public class ConsultantDetailListTpl extends BaseTpl{
    private FlowLayout flow_layout;


    public ConsultantDetailListTpl(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ConsultantDetailListTpl(Context context) {
        super(context);
    }

    @Override
    protected void initView() {
        flow_layout = (FlowLayout) findView(R.id.flow_layout);


    }

    @Override
    protected int getLayoutId() {
        return R.layout.item_consultant_detail_list_tpl;
    }

    @Override
    public void setBean(NetResult bean, int position) {
        flow_layout.removeAllViews();
        for (int i = 1; i <4; i++) {
            String key ="整栋招商";
            addTextView(key+i);
        }
    }

    /**
     * 把textview加入到流布局
     */
    private void addTextView(final String keyword) {
        final TextView tv = (TextView) LayoutInflater.from(_activity).inflate(R.layout.tv, flow_layout, false);
        tv.setText(keyword);
        //把TextView加入流式布局
        flow_layout.addView(tv);
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //点击事件后将当前的关键字保存起来，如果该关键字已经存在就不执行保存
                UIHelper.t(_activity, tv.getText().toString());
            }
        });
    }
}
