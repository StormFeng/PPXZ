package com.midian.ppaddress.ui.chooseaddres;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

import com.midian.ppaddress.R;

import midian.baselib.bean.NetResult;
import midian.baselib.view.BaseTpl;
import midian.baselib.widget.CircleImageView;

/**
 * Created by chu on 2016/4/28.
 */
public class ConsultantCommentTpl extends BaseTpl{
    private CircleImageView head_cv;
    private TextView name_tv,comment_type_tv,time_tv,content_tv;

    public ConsultantCommentTpl(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ConsultantCommentTpl(Context context) {
        super(context);
    }

    @Override
    protected void initView() {
        head_cv = (CircleImageView) findView(R.id.head_cv);
        name_tv = (TextView) findView(R.id.name_tv);
        comment_type_tv = (TextView) findView(R.id.rate_tv);
        time_tv = (TextView) findView(R.id.time_tv);
        content_tv = (TextView) findView(R.id.content_tv);

    }

    @Override
    protected int getLayoutId() {
        return R.layout.item_consultant_comment_tpl;
    }

    @Override
    public void setBean(NetResult bean, int position) {

    }
}
