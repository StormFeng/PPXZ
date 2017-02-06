package com.midian.ppaddress.itemtpl;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import com.midian.ppaddress.R;
import com.midian.ppaddress.bean.AnswerMulBean;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Formatter;

import midian.baselib.view.BaseTpl;

public class AnswerBTpl extends BaseTpl<AnswerMulBean>{

    private TextView tv_closeTime;

    public AnswerBTpl(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public AnswerBTpl(Context context) {
        super(context);
    }

    @Override
    protected void initView() {
        tv_closeTime=findView(R.id.tv_closeTime);
        _activity.findViewById(R.id.ll_btn).setVisibility(View.GONE);
        _activity.findViewById(R.id.iv_refresh).setVisibility(GONE);
        _activity.findViewById(R.id.topbar).findViewById(R.id.right_tv).setVisibility(GONE);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_answer_bot_tpl;
    }

    @Override
    public void setBean(AnswerMulBean bean, int position) {
        if(bean.getItemViewType()==3){
            String time=_activity.getIntent().getStringExtra("time");
            if(TextUtils.isEmpty(time)){
                SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date date = new Date(System.currentTimeMillis());
                time= sf.format(date);
            }
            tv_closeTime.setText("您结束了咨询回话 "+time);
        }
    }
}
