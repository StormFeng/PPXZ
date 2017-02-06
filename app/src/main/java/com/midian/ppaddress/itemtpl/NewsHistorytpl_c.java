package com.midian.ppaddress.itemtpl;

import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.midian.ppaddress.R;
import com.midian.ppaddress.bean.ConsultListBean;
import com.midian.ppaddress.ui.company.AnswerQuestionActivity;

import midian.baselib.utils.UIHelper;
import midian.baselib.view.BaseTpl;

public class NewsHistorytpl_c extends BaseTpl<ConsultListBean.Lists> implements View.OnClickListener{

    public NewsHistorytpl_c(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public NewsHistorytpl_c(Context context) {
        super(context);
    }

    @Override
    protected void initView() {}

    @Override
    protected int getLayoutId() {
        return R.layout.item_news_history_activity_c;
    }

    @Override
    public void setBean(ConsultListBean.Lists bean, int position) {
        if(bean!=null){
            if(bean.getItemViewType()==1){
            }else{
                findViewById(R.id.ll_text).setVisibility(GONE);
            }
        }
    }
    @Override
    public void onClick(View view) {}
}
