package com.midian.ppaddress.itemtpl;

import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.midian.ppaddress.R;
import com.midian.ppaddress.bean.ConsultListBean;
import com.midian.ppaddress.ui.company.AnswerQuestionActivity;

import midian.baselib.utils.UIHelper;
import midian.baselib.view.BaseTpl;

public class NewsHistorytpl_b extends BaseTpl<ConsultListBean.Lists> implements View.OnClickListener{

    private ImageView iv_image;
    private TextView tv_name,tv_position,tv_rent,tv_question;
    private String time;

    private String agencyexpertId,id;//服务达人ID,咨询问题id
    public NewsHistorytpl_b(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public NewsHistorytpl_b(Context context) {
        super(context);
    }

    @Override
    protected void initView() {
        iv_image=findView(R.id.image_iv);
        tv_name=findView(R.id.name_tv);
        tv_position=findView(R.id.position_tv);
        tv_rent=findView(R.id.rent_tv);
        tv_question=findView(R.id.question_tv);
        root.setOnClickListener(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.item_news_history_activity;
    }

    @Override
    public void setBean(ConsultListBean.Lists bean, int position) {
        if(bean!=null){
            if(bean.getItemViewType()==2) {
                ac.setImage(iv_image, bean.getPortrait());
                findView(R.id.isread_tv).setVisibility(GONE);
                if("4".equals(ac.getProperty("user_type"))){
                    tv_name.setText(bean.getMember());
                    tv_position.setVisibility(GONE);
                }else{
                    tv_name.setText(bean.getAgencyexpert());
                    tv_position.setText(bean.getPosition());
                }
                time=bean.getCloseTime();
                tv_question.setText("结束时间:"+time);
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
        Intent intent=new Intent();
        intent.setClass(_activity, AnswerQuestionActivity.class);
        intent.putExtra("questionId",id);
        intent.putExtra("agencyexpertId",agencyexpertId);
        intent.putExtra("agencyexpertId",agencyexpertId);
        intent.putExtra("isclose","1");
        intent.putExtra("time",time);
        _activity.startActivity(intent);
    }
}
