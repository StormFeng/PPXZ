package com.midian.ppaddress.itemtpl;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.midian.ppaddress.R;
import com.midian.ppaddress.bean.ConsultListBean;
import com.midian.ppaddress.ui.company.AnswerQuestionActivity;

import midian.baselib.bean.NetResult;
import midian.baselib.utils.UIHelper;
import midian.baselib.view.BaseTpl;

public class NewsHistorytpl extends BaseTpl<ConsultListBean.Lists> implements View.OnClickListener{

    private ImageView iv_image;
    private TextView tv_name,tv_position,tv_question,tv_isread;

    private String agencyexpertId,id;//服务达人ID,咨询问题id
    public NewsHistorytpl(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public NewsHistorytpl(Context context) {
        super(context);
    }

    @Override
    protected void initView() {
        iv_image=findView(R.id.image_iv);
        tv_name=findView(R.id.name_tv);
        tv_position=findView(R.id.position_tv);
        tv_question=findView(R.id.question_tv);
        tv_isread=findView(R.id.isread_tv);
        root.setOnClickListener(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.item_news_history_activity;
    }

    @Override
    public void setBean(ConsultListBean.Lists bean, int position) {
        if(bean!=null){
            if(bean.getItemViewType()==0) {
                ac.setImage(iv_image, bean.getPortrait());
                if("1".equals(bean.getIsread())){
                    tv_isread.setVisibility(GONE);
                }else{
                    tv_isread.setVisibility(VISIBLE);
                }
                if("4".equals(ac.getProperty("user_type"))){
                    tv_name.setText(bean.getMember());
                    tv_position.setVisibility(GONE);
                }else{
                    tv_name.setText(bean.getAgencyexpert());
                    tv_position.setText(bean.getPosition());
                }
                    tv_question.setText(bean.getContent());
               //memberid 服务达人调用才有该字段,代表向自己咨询的会员id,即对话者
                if("4".equals(ac.getProperty("user_type"))){
                    agencyexpertId=bean.getMemberid();
                }else {
                    agencyexpertId = bean.getAgencyexpertid();
                }
                id = bean.getId();
            }
        }
    }


    @Override
    public void onClick(View view) {
        Bundle bundle=new Bundle();
        bundle.putString("questionId",id);
        bundle.putString("agencyexpertId",agencyexpertId);
        bundle.putString("isclose","0");
        UIHelper.jumpForResult(_activity,AnswerQuestionActivity.class,bundle,1001);
//        _activity.startActivity(intent);
    }
}
