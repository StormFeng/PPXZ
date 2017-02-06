package com.midian.ppaddress.ui.company;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.midian.ppaddress.R;
import com.midian.ppaddress.bean.AddMemberMeAgencyexpertBean;
import com.midian.ppaddress.utils.AppUtil;

import java.util.ArrayList;

import midian.baselib.base.BaseActivity;
import midian.baselib.base.BaseListActivity;
import midian.baselib.bean.NetResult;
import midian.baselib.shizhefei.view.listviewhelper.IDataSource;
import midian.baselib.utils.UIHelper;
import midian.baselib.widget.BaseLibTopbarView;

//对服务大人评价
public class EvaluateActivity extends BaseActivity{

    private BaseLibTopbarView topbar;
    private Button btn_1,btn_2,btn_3;//好评，中评，差评
    private EditText et_Rate;
    private String questionid,agencyexpertId;
    private TextView tv_question,tv_time,tv_name,tv_position,tv_company;
    private ImageView head_cv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Intent intent=getIntent();
        questionid = intent.getStringExtra("questionid");
        super.onCreate(savedInstanceState);
//        agencyexpertId=mBundle.getString("agencyexpertId");
        setContentView(R.layout.activity_evaluate);
        initView();
        setButton(R.id.btn_1);
        AppUtil.getPpApiClient(ac).addMemberMeAgencyexpert(questionid,this);
    }


    @Override
    public void onApiSuccess(NetResult res, String tag) {
        super.onApiSuccess(res, tag);
        if("addMemberMeAgencyexpert".equals(tag)){
            AddMemberMeAgencyexpertBean bean= (AddMemberMeAgencyexpertBean) res;
            ac.setImage(head_cv,bean.getData().getPortrait());
            tv_question.setText(bean.getData().getQuestion());
            tv_time.setText(bean.getData().getCreateTime());
            tv_name.setText(bean.getData().getAgencyexpert());
            tv_position.setText(bean.getData().getPosition());
            tv_company.setText(bean.getData().getAgencyName());
            agencyexpertId=bean.getData().getMemberid();
        }
        if("commentMemberMeAgencyexpert".equals(tag)){
            UIHelper.t(_activity,res.message);
            setResult(RESULT_OK);
            finish();
        }
    }

    private void initView() {
        topbar = findView(R.id.topbar);
        topbar.setTitle("评价").setLeftImageButton(R.drawable.icon_back, UIHelper.finish(_activity));
        findViewById(R.id.btn_No_Evaluate).setOnClickListener(this);
        findViewById(R.id.btn_Ok).setOnClickListener(this);
        btn_1=findView(R.id.btn_1);
        btn_2=findView(R.id.btn_2);
        btn_3=findView(R.id.btn_3);
        et_Rate=findView(R.id.et_rate);
        btn_1.setOnClickListener(this);
        btn_2.setOnClickListener(this);
        btn_3.setOnClickListener(this);
        tv_question=findView(R.id.tv_question);
        tv_time=findView(R.id.tv_time);
        tv_name=findView(R.id.tv_name);
        tv_position=findView(R.id.tv_position);
        tv_company=findView(R.id.tv_company);
        head_cv=findView(R.id.head_cv);
    }

    @Override
    public void onClick(View arg0) {
        super.onClick(arg0);
        switch (arg0.getId()){
            case R.id.btn_No_Evaluate:
                setResult(RESULT_OK);
                finish();
                break;
            case R.id.btn_Ok:
                loadData();
                finish();
                break;
            case R.id.btn_1:
                setButton(R.id.btn_1);
                break;
            case R.id.btn_2:
                setButton(R.id.btn_2);
                break;
            case R.id.btn_3:
                setButton(R.id.btn_3);
                break;
        }
    }

    private void setButton(int id) {
        btn_1.setTextColor(getResources().getColor(R.color.text_bg90));
        btn_2.setTextColor(getResources().getColor(R.color.text_bg90));
        btn_3.setTextColor(getResources().getColor(R.color.text_bg90));
        btn_1.setBackgroundResource(R.drawable.radius_bg_graystroke);
        btn_2.setBackgroundResource(R.drawable.radius_bg_graystroke);
        btn_3.setBackgroundResource(R.drawable.radius_bg_graystroke);
        btn_1.setClickable(true);
        btn_2.setClickable(true);
        btn_3.setClickable(true);
        Button button = findView(id);
        button.setTextColor(getResources().getColor(R.color.white));
        button.setBackgroundResource(R.drawable.radius_bg_redsolid);
        button.setClickable(false);
    }

    private void loadData() {
        //15.10个人中心-我（普通客商）的评论-对服务达人的评论-提交评论
        //commentMemberMeAgencyexpert(int memberid, int questionid, int serverid, String content, int rate, ApiCallback callback)

        String rateContent=et_Rate.getText().toString();
        if(TextUtils.isEmpty(rateContent)){rateContent="好评！";}
        int rate=0;
        if(!btn_1.isClickable()){
            rate=1;
        }else if(!btn_2.isClickable()){
            rate=2;
        }else if(!btn_3.isClickable()){
            rate=3;
        }
        AppUtil.getPpApiClient(ac).commentMemberMeAgencyexpert(ac.user_id,questionid,agencyexpertId,rateContent,rate,this);
    }
}
