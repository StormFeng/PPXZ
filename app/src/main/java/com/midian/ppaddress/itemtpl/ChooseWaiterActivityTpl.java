package com.midian.ppaddress.itemtpl;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.midian.ppaddress.R;
import com.midian.ppaddress.bean.BusinessAgencyConsultAskQuestionBean;
import com.midian.ppaddress.bean.BusinessAgencyDetailListAgencyexpertBean;
import com.midian.ppaddress.ui.company.AnswerQuestionActivity;
import com.midian.ppaddress.ui.company.ChooseWaiterActivity;
import com.midian.ppaddress.utils.AppUtil;

import midian.baselib.api.ApiCallback;
import midian.baselib.bean.NetResult;
import midian.baselib.utils.UIHelper;
import midian.baselib.view.BaseTpl;
import midian.baselib.widget.CircleImageView;

/**
 * Created by chu on 2016/4/21.
 */
public class ChooseWaiterActivityTpl extends BaseTpl<BusinessAgencyDetailListAgencyexpertBean.Agencyexpert> implements View.OnClickListener{
    private TextView tv_position,tv_goodRate,tv_serviceCount,tv_fullname;
    private CircleImageView iv_portrait;
    private String agencyexpertId;

    private  BusinessAgencyConsultAskQuestionBean bean;

    public ChooseWaiterActivityTpl(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ChooseWaiterActivityTpl(Context context) {
        super(context);
    }

    @Override
    protected void initView() {
        iv_portrait=findView(R.id.iv_portrait);
        tv_position=findView(R.id.tv_position);
        tv_goodRate=findView(R.id.tv_goodRate);
        tv_fullname=findView(R.id.tv_name);
        tv_serviceCount=findView(R.id.tv_serviceCount);
        if(!"0".equals(ac.getProperty("user_type"))){
            findView(R.id.query).setVisibility(GONE);
        }else{
            findView(R.id.query).setVisibility(VISIBLE);
        }
        root.findViewById(R.id.query).setOnClickListener(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.item_choose_waiter_activity;
    }

    @Override
    public void setBean(BusinessAgencyDetailListAgencyexpertBean.Agencyexpert bean, int position) {
        if(bean!=null){
            tv_position.setText(bean.getPosition());
            tv_goodRate.setText(bean.getGoodRate()+"%");
            tv_serviceCount.setText(bean.getServiceCount());
            tv_fullname.setText(bean.getFullname());
            ac.setImage(iv_portrait,bean.getPortrait());
            agencyexpertId=bean.getId();
        }
    }

    @Override
    public void onClick(View view) {
        final Dialog dialog=new Dialog(_activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_fragment);
        dialog.show();
        final Window window=dialog.getWindow();
        window.findViewById(R.id.btn_commit).setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                EditText et_Question= (EditText) window.findViewById(R.id.et_question);
                String str=et_Question.getText().toString();
//                String id=ac.user_id;
//                int memberid=Integer.valueOf(id).intValue();
                if(TextUtils.isEmpty(str)){UIHelper.t(_activity,"请输入您的问题");}
                    else {
                    AppUtil.getPpApiClient(ac).businessAgencyConsultAskQuestion(ac.user_id, agencyexpertId, str, new ApiCallback() {
                        @Override
                        public void onApiStart(String tag) {
                        }

                        @Override
                        public void onApiLoading(long count, long current, String tag) {

                        }

                        @Override
                        public void onApiSuccess(NetResult res, String tag) {
                            dialog.dismiss();
                            bean= (BusinessAgencyConsultAskQuestionBean) res;
                            String questionId=bean.getData().getId();
//                            Intent intent=new Intent();
//                            intent.setClass(_activity, AnswerQuestionActivity.class);
//                            intent.putExtra("questionId",questionId);
//                            intent.putExtra("agencyexpertId",agencyexpertId);
//                            _activity.startActivity(intent);
                            Bundle bundle=new Bundle();
                            bundle.putString("questionId",questionId);
                            bundle.putString("agencyexpertId",agencyexpertId);
                            UIHelper.jump(_activity,AnswerQuestionActivity.class,bundle);
//                            UIHelper.jump(_activity, AnswerQuestionActivity.class);
                        }

                        @Override
                        public void onApiFailure(Throwable t, int errorNo, String strMsg, String tag) {
                        }

                        @Override
                        public void onParseError(String tag) {

                        }
                    });
                }
            }
        });
        window.findViewById(R.id.icon_back).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
    }
}
