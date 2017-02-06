package com.midian.ppaddress.ui.company;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.midian.ppaddress.R;
import com.midian.ppaddress.utils.AppUtil;

import midian.baselib.base.BaseActivity;
import midian.baselib.bean.NetResult;
import midian.baselib.utils.UIHelper;
import midian.baselib.widget.BaseLibTopbarView;

public class CompanyApplyActivity extends BaseActivity implements View.OnClickListener{

    private BaseLibTopbarView topbar;
    private EditText et_phoneNumber;
    private String number;
    private int activityId;
    private TextView tv_number;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_applypage);
        initView();

    }

    private void initView() {
        et_phoneNumber=findView(R.id.et_number);
        topbar=findView(R.id.topbar);
        tv_number=findView(R.id.tv_number);
        tv_number.setText(ac.phone);
        topbar.setTitle("企业服务").setLeftImageButton(R.drawable.icon_back,UIHelper.finish(_activity)).setRightImageButton(R.drawable.icon_web_close,UIHelper.finish(_activity)).setRight2ImageButton(R.drawable.icon_web_refresh, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });
        findViewById(R.id.btn_apply).setOnClickListener(this);

    }

    @Override
    public void onApiSuccess(NetResult res, String tag) {
        super.onApiSuccess(res, tag);
        if(res.isOK()) {
            UIHelper.t(_activity, res.message);
            UIHelper.finish(_activity);
        }else{
            ac.handleErrorCode(_activity,res.message);
        }
    }

    @Override
    public void onClick(View view) {
        number=et_phoneNumber.getText().toString();
        if(TextUtils.isEmpty(number)){
            UIHelper.t(this,"请输入您的姓名");
        }else{
            if("0".equals(getIntent().getStringExtra("flag"))){
                AppUtil.getPpApiClient(ac).businessAgencyScreenOnekeyApply(ac.user_id,number,this);
            }else if("1".equals(getIntent().getStringExtra("flag"))){
                activityId=getIntent().getExtras().getInt("activityId");
                AppUtil.getPpApiClient(ac).businessAgencyActivityApply(activityId,ac.user_id,number,this);
            }
        }
    }
}

