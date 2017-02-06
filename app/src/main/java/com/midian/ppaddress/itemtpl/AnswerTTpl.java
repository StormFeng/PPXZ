package com.midian.ppaddress.itemtpl;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.TextView;

import com.midian.ppaddress.R;
import com.midian.ppaddress.bean.AnswerMulBean;
import com.midian.ppaddress.bean.BusinessAgencyConsultPageRecordBean;
import com.midian.ppaddress.ui.company.AnswerQuestionActivity;

import midian.baselib.view.BaseTpl;

public class AnswerTTpl extends BaseTpl<AnswerMulBean>{

    private TextView question,createTime;

    public AnswerTTpl(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public AnswerTTpl(Context context) {
        super(context);
    }

    @Override
    protected void initView() {
        question= (TextView) findViewById(R.id.tv_question);
        createTime= (TextView) findViewById(R.id.tv_createtime);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_answer_top_tpl;
    }

    @Override
    public void setBean(AnswerMulBean bean, int position) {
        if(bean.getItemViewType()==0){
            question.setText("问题："+bean.topBean.getData().getQuestion());
            createTime.setText(bean.topBean.getData().getCreateTime());
            AnswerQuestionActivity.receiverId=bean.topBean.getData().getReceiver();
        }
    }

}
