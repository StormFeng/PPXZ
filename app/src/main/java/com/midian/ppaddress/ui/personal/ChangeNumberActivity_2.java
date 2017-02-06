package com.midian.ppaddress.ui.personal;

import android.app.Dialog;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.midian.login.api.LoginApiClient;
import com.midian.login.utils.ObjectAnimatorTools;
import com.midian.login.utils.ValidateTools;
import com.midian.ppaddress.R;
import com.midian.ppaddress.utils.AppUtil;

import midian.baselib.base.BaseActivity;
import midian.baselib.bean.NetResult;
import midian.baselib.utils.Func;
import midian.baselib.utils.UIHelper;
import midian.baselib.widget.BaseLibTopbarView;

/**
 * Created by Administrator on 2016/4/28.
 */
public class ChangeNumberActivity_2 extends BaseActivity implements View.OnClickListener{
    private BaseLibTopbarView topbar;
    private EditText et_Number,et_CheckCode;
    private Button btn_Next,btn_CheckCode;
    private LinearLayout ll_newNumber,ll_checkCode;
    private String oldNumber,newNumber,code;
    private int type;
    private CountDownTimer mCountDownTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        oldNumber = mBundle.getString("phone");
        type=mBundle.getInt("type");
        setContentView(R.layout.activity_changenumber_2);
        initView();
    }

    private void initView() {
        topbar=findView(R.id.topbar);
        topbar.setTitle("更换手机号").setLeftImageButton(R.drawable.icon_back, UIHelper.finish(this));
        findViewById(R.id.btn_next).setOnClickListener(this);
        ll_checkCode=findView(R.id.ll_checkcode);
        ll_newNumber=findView(R.id.ll_newNumber);
        btn_Next=findView(R.id.btn_next);
        btn_CheckCode=findView(R.id.btn_checkcode);
        et_Number=findView(R.id.et_number);
        et_CheckCode=findView(R.id.et_checkcode);
        btn_Next.setOnClickListener(this);
        btn_CheckCode.setOnClickListener(this);
    }

    @Override
    public void onApiSuccess(NetResult res, String tag) {
        super.onApiSuccess(res, tag);
        if(res.isOK()){
            if ("sendCode".equals(tag)) {
                UIHelper.t(_activity, "发送成功");
            }
            if ("validateCode".equals(tag)) {
                int memberid=Integer.valueOf(ac.user_id).intValue();
                AppUtil.getPpApiClient(ac).updatePhone(memberid,oldNumber,type,code,newNumber,this);
            }
            if("updatePhone".equals(tag)){
                final Dialog dialog=new Dialog(_activity);
                Window window=dialog.getWindow();
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.dialog_changenumber);
                dialog.show();
                window.findViewById(R.id.btn_finish).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
            }
        } else {
            ac.handleErrorCode(_activity, res.message);
        }
    }

    private void downTime() {
        mCountDownTimer = new CountDownTimer(59 * 1000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                String timeText = getResources().getString(
                        com.midian.login.R.string.hint_time_text);
                btn_CheckCode.setClickable(false);
                btn_CheckCode
                        .setText(millisUntilFinished / 1000 + timeText);
            }

            @Override
            public void onFinish() {
                btn_CheckCode.setClickable(true);
                btn_CheckCode.setText(com.midian.login.R.string.auth_code);
            }
        };
        mCountDownTimer.start();
    }

    @Override
    public void onClick(View arg0) {
        super.onClick(arg0);
        newNumber=et_Number.getText().toString().trim();
        code=et_CheckCode.getText().toString().trim();
        switch (arg0.getId()){
            case R.id.btn_checkcode:
                if (ValidateTools.isEmptyString(newNumber)) {
                    ObjectAnimatorTools.onVibrationView(ll_newNumber);
                    UIHelper.t(this, R.string.hint_phone_not_empty);
                    return;
                }
                if (!ValidateTools.isPhoneNumber(newNumber)) {
                    ObjectAnimatorTools.onVibrationView(ll_newNumber);
                    UIHelper.t(this, com.midian.login.R.string.hint_phone_error);
                    return;
                }
                ac.api.getApiClient(LoginApiClient.class).sendCode(newNumber, type, this);//发送验证码
                downTime();
                break;
            case R.id.btn_next:
                if (ValidateTools.isEmptyString(newNumber)) {
                    ObjectAnimatorTools.onVibrationView(ll_newNumber);
                    UIHelper.t(_activity, com.midian.login.R.string.hint_phone_not_empty);
                    return;
                }
                if (!Func.isMobileNO(newNumber)) {
                    ObjectAnimatorTools.onVibrationView(ll_newNumber);
                    UIHelper.t(_activity, com.midian.login.R.string.hint_phone_error);
                    return;
                }
                if (ValidateTools.isEmptyString(code)) {
                    ObjectAnimatorTools.onVibrationView(ll_checkCode);
                    UIHelper.t(_activity, com.midian.login.R.string.hint_auth_code_not_empty);
                    return;
                }
                ac.api.getApiClient(LoginApiClient.class).validateCode(newNumber,type, code, this);// 验证验证码
                break;
        }
    }
}
