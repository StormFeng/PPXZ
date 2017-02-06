package com.midian.ppaddress.ui.personal;

import android.content.Intent;
import android.graphics.BitmapRegionDecoder;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.midian.login.utils.ObjectAnimatorTools;
import com.midian.ppaddress.R;

import midian.baselib.base.BaseActivity;
import midian.baselib.utils.UIHelper;
import midian.baselib.widget.BaseLibTopbarView;

/**
 * 账号升级1
 * Created by chu on 2016/5/3.
 */
public class AccountUpgradeActivity1 extends BaseActivity {
    private BaseLibTopbarView topbar;
    private EditText name_et, code_et;
    private Button next_btn;
    private String type,name,personalId;
    private TextView hint_tv;
    private LinearLayout ll_name,ll_personalId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity1_account_upgrade);
        topbar = findView(R.id.topbar);
        hint_tv = findView(R.id.hint_tv);
        topbar.setLeftImageButton(R.drawable.icon_back, UIHelper.finish(_activity));
        type = mBundle.getString("type");
        if ("1".equals(type)) {
            topbar.setTitle("客商中介");
            hint_tv.setText("升级客商中介须验证您的身份证号");
        } else if ("2".equals(type)) {
            topbar.setTitle("物业顾问");
            hint_tv.setText("升级物业顾问须验证您的身份证号");
        }else if ("4".equals(type)) {
            topbar.setTitle("服务达人");
            hint_tv.setText("升级服务达人须验证您的身份证号");
        }
        name_et = findView(R.id.name_et);
        code_et = findView(R.id.code_et);
        next_btn = findView(R.id.next_btn);
        ll_name=findView(R.id.ll_account_et);
        ll_personalId=findView(R.id.ll_auth_code_et);
        next_btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        name=name_et.getText().toString().trim();
        personalId=code_et.getText().toString().trim();
        switch (v.getId()) {
            case R.id.next_btn:
                if(TextUtils.isEmpty(name)){
                    ObjectAnimatorTools.onVibrationView(ll_name);
                    UIHelper.t(_activity, "姓名不能为空");
                    return;
                }
                if(TextUtils.isEmpty(personalId)){
                    ObjectAnimatorTools.onVibrationView(ll_personalId);
                    UIHelper.t(_activity, "身份证号码不能为空");
                    return;
                }
                if(personalId.length()!=18){
                    ObjectAnimatorTools.onVibrationView(ll_personalId);
                    UIHelper.t(_activity, "身份证号码格式不正确");
                    return;
                }
                mBundle.putString("type", type);
                mBundle.putString("idcard",personalId);
                mBundle.putString("fullname",name);
//                String fullname=name_et.getText().toString().trim();
//                String idcard=code_et.getText().toString().trim();
//                Intent intent=new Intent();
//                intent.setClass(_activity, AccountUpgradeActivity2.class,mBundle);
                UIHelper.jump(_activity, AccountUpgradeActivity2.class,mBundle);
                finish();
                break;
        }
    }
}
