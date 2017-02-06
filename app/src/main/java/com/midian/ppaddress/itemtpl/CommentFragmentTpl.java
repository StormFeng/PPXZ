package com.midian.ppaddress.itemtpl;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import com.midian.ppaddress.R;
import com.midian.ppaddress.bean.BusinessCommentsMembersBean.CommentMembersList;

import midian.baselib.view.BaseTpl;
import midian.baselib.widget.CircleImageView;

/**
 * 用户点评条目
 * 曾经苍海难为水
 * Created by chu on 2016/4/21.
 */
public class CommentFragmentTpl extends BaseTpl<CommentMembersList> {

    private TextView name_tv, time_tv, content_tv, state_tv;
    private CircleImageView head_cv;

    public CommentFragmentTpl(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CommentFragmentTpl(Context context) {
        super(context);
    }

    @Override
    protected void initView() {
        head_cv = (CircleImageView) findViewById(R.id.head_cv);
        name_tv = (TextView) findViewById(R.id.name_tv);
        time_tv = (TextView) findViewById(R.id.time_tv);
        content_tv = (TextView) findViewById(R.id.content_tv);
        state_tv = (TextView) findViewById(R.id.state_tv);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.item_comment_tpl;
    }

    @Override
    public void setBean(CommentMembersList bean, int position) {
        if (bean.getImages() == null) {
            ac.setImage(head_cv, R.drawable.head1);
        } else {
            ac.setImage(head_cv, bean.getImages());
        }
        name_tv.setText(bean.getName());
        time_tv.setText(bean.getCreateTime());
        content_tv.setText(bean.getContent());
        String rate = bean.getRate();//评论等级，0无，1为好评，2为中评，3为差评
        if ("0".equals(rate)) {
            state_tv.setVisibility(View.GONE);
        } else if ("1".equals(rate)) {
            state_tv.setText("好评");
        } else if ("2".equals(rate)) {
            state_tv.setText("中评");
        } else if ("3".equals(rate)) {
            state_tv.setText("差评");
        }

    }
}
