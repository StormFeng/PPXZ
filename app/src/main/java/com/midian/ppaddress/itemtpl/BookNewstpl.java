package com.midian.ppaddress.itemtpl;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import com.midian.ppaddress.R;
import com.midian.ppaddress.bean.MemberMessagePageOrderMessagesBean;
import com.midian.ppaddress.ui.personal.BookingActivity;
import com.midian.ppaddress.ui.personal.BookingActivity_Counselor;

import midian.baselib.bean.NetResult;
import midian.baselib.utils.UIHelper;
import midian.baselib.view.BaseTpl;
public class BookNewstpl extends BaseTpl<MemberMessagePageOrderMessagesBean.OrderMessagesList> implements View.OnClickListener {

    private TextView tv_sendtime,tv_title,tv_content;

    public BookNewstpl(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public BookNewstpl(Context context) {
        super(context);
    }

    @Override
    protected void initView() {
        tv_sendtime= (TextView) findViewById(R.id.tv_sendtime);
        tv_content= (TextView) findViewById(R.id.tv_content);
        tv_title= (TextView) findViewById(R.id.tv_title);
        root.setOnClickListener(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.item_book_news_activity;
    }

    @Override
    public void setBean(MemberMessagePageOrderMessagesBean.OrderMessagesList bean, int position) {
        if(bean!=null) {
            tv_sendtime.setText(bean.getSendTime());
            tv_content.setText(bean.getContent());
            tv_title.setText(bean.getTitle());
            if ("1".equals(bean.getIsread())) {
                tv_title.setTextColor(getResources().getColor(R.color.text_bg90));
            }else{
                tv_title.setTextColor(getResources().getColor(R.color.text_bg20));
            }
        }
    }

    @Override
    public void onClick(View view) {
        if("0".equals(ac.getProperty("user_type"))){
            //普通客商——我的预约
            UIHelper.jumpForResult(_activity, BookingActivity.class,1100);
        }else if("2".equals(ac.getProperty("user_type"))){
            //物业顾问——我的预约
            UIHelper.jumpForResult(_activity, BookingActivity_Counselor.class,1100);
        }
    }
}
