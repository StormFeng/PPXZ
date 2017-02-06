package com.midian.ppaddress.ui.personal;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
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
import midian.baselib.utils.UIHelper;
import midian.baselib.widget.BaseLibTopbarView;

/**
 * Created by Administrator on 2016/4/28.
 */
public class ChangePasswordActivity_2 extends BaseActivity implements View.OnClickListener{
    private BaseLibTopbarView topbar;
    private EditText et_Password,et_Password2;
    private Button btn_Complete;
    private LinearLayout ll_pwd1,ll_pwd2;
    private String phone,code;
    private int type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_changepassword_2);
        phone=mBundle.getString("phone");
        type=mBundle.getInt("type");
        code=mBundle.getString("code");
        initView();
    }

    private void initView() {
        topbar=findView(R.id.topbar);
        topbar.setTitle("修改密码").setLeftImageButton(R.drawable.icon_back, UIHelper.finish(this));
        findViewById(R.id.btn_complete).setOnClickListener(this);//完成
        ll_pwd1=findView(R.id.ll_pwd1);
        ll_pwd2=findView(R.id.ll_pwd2);
        btn_Complete=findView(R.id.btn_complete);
        et_Password=findView(R.id.et_password);
        et_Password2=findView(R.id.et_password2);
        btn_Complete.setOnClickListener(this);
    }

    @Override
    public void onApiSuccess(NetResult res, String tag) {
        super.onApiSuccess(res, tag);
        UIHelper.t(_activity,"修改密码成功！");
        finish();
    }

    @Override
    public void onClick(View arg0) {
        super.onClick(arg0);
        int memberid=Integer.valueOf(ac.user_id).intValue();
        String pwd=et_Password.getText().toString().trim();
        String pwd2=et_Password2.getText().toString().trim();
//        String oldPwd=ac.password;
//        if (oldPwd.equals(pwd)) {
//            ObjectAnimatorTools.onVibrationView(ll_pwd1);
//            UIHelper.t(_activity, "新密码不能与旧密码相同");
//            return;
//        }
        if (ValidateTools.isEmptyString(pwd)) {
            ObjectAnimatorTools.onVibrationView(ll_pwd1);
            UIHelper.t(_activity, com.midian.login.R.string.hint_pwd_not_empty);
            return;
        }
        if (pwd.length() < 6 || pwd.length() > 16) {
            ObjectAnimatorTools.onVibrationView(ll_pwd1);
            UIHelper.t(_activity, "密码长度须6到16位");
            return;
        }

        if (!pwd.equals(pwd2)) {
            ObjectAnimatorTools.onVibrationView(ll_pwd2);
            UIHelper.t(_activity, com.midian.login.R.string.hint_pwd2);
            return;
        }
        AppUtil.getPpApiClient(ac).memberMembershipInfoUpdatePassword(memberid,phone,type,code,pwd,this);
    }
}
