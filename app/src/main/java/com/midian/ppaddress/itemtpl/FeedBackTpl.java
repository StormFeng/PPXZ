package com.midian.ppaddress.itemtpl;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.TextView;

import com.midian.ppaddress.R;
import com.midian.ppaddress.bean.AnswerMulBean;
import com.midian.ppaddress.bean.FeedBackListBean;

import midian.baselib.view.BaseTpl;

public class FeedBackTpl extends BaseTpl<FeedBackListBean>{

    private TextView tv_item;

    public FeedBackTpl(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public FeedBackTpl(Context context) {
        super(context);
    }

    @Override
    protected void initView() {
        tv_item=findView(R.id.tv_item);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_feedback_2;
    }

    @Override
    public void setBean(FeedBackListBean bean, int position) {
        if(bean.isOK()){
            tv_item.setText(bean.getData().get(position).getName());
        }
    }
}
