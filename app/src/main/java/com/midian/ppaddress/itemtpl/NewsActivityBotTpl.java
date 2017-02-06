package com.midian.ppaddress.itemtpl;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.midian.ppaddress.R;
import com.midian.ppaddress.bean.MemberMessagePageSysMessagesBean;
import com.midian.ppaddress.ui.personal.SystemNewsActivity;

import midian.baselib.bean.NetResult;
import midian.baselib.utils.UIHelper;
import midian.baselib.view.BaseTpl;

public class NewsActivityBotTpl extends BaseTpl<NetResult> implements View.OnClickListener {

    private TextView tv_sendtime, tv_title, tv_content;
    private ImageView iv_isread, iv_image;
    private String id;
    private String url;

    public NewsActivityBotTpl(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public NewsActivityBotTpl(Context context) {
        super(context);
    }

    @Override
    protected void initView() {
        tv_sendtime = (TextView) findViewById(R.id.tv_sendtime);
        tv_content = (TextView) findViewById(R.id.tv_content);
        tv_title = (TextView) findViewById(R.id.tv_title);
        iv_isread = (ImageView) findViewById(R.id.iv_isread);
        iv_image = (ImageView) findViewById(R.id.iv_image);
        root.setOnClickListener(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_news_bot_tpl;
    }

    @Override
    public void setBean(NetResult bean, int position) {
        if (bean instanceof MemberMessagePageSysMessagesBean.Lists) {
            MemberMessagePageSysMessagesBean.Lists item = (MemberMessagePageSysMessagesBean.Lists) bean;
            if (TextUtils.isEmpty(item.getImage())) {
                iv_image.setVisibility(GONE);
            } else {
                iv_image.setVisibility(VISIBLE);
                ac.setImage(iv_image, item.getImage());
            }
            tv_title.setText(item.getTitle());
            tv_content.setText(item.getContent());
            tv_sendtime.setText(item.getSendTime());
            if ("0".equals(item.getIsread())) {
                iv_isread.setVisibility(VISIBLE);
            }else{
                iv_isread.setVisibility(GONE);
            }
            id = item.getId();
            url=item.getUrl();
        }
    }

    @Override
    public void onClick(View view) {
        Bundle bundle = new Bundle();
        bundle.putString("messageid", id);
        bundle.putString("url", url);
        bundle.putString("flag","1");
        UIHelper.jump(_activity,SystemNewsActivity.class,bundle);
    }
}
