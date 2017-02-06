package com.midian.ppaddress.ui.personal;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.midian.ppaddress.R;

import midian.baselib.base.BaseActivity;
import midian.baselib.utils.UIHelper;
import midian.baselib.widget.BaseLibTopbarView;

/**
 * 账号升级
 * Created by chu on 2016/5/3.
 */
public class AccountUpgradeActivity extends BaseActivity{
    private BaseLibTopbarView topbar;
    private TextView mediation_tv,consultant_tv,proprietor_tv, got_talent_tv;
    private  String type;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_upgrad);
        topbar = findView(R.id.topbar);
        topbar.setTitle("账号升级").setLeftImageButton(R.drawable.icon_back, UIHelper.finish(_activity));

        mediation_tv = findView(R.id.mediation_tv);//客商中介
        consultant_tv = findView(R.id.consultant_tv);//物业顾问
        proprietor_tv = findView(R.id.proprietor_tv);//业主
        got_talent_tv = findView(R.id.got_talent_tv);//服务达人
        mediation_tv.setOnClickListener(this);
        consultant_tv.setOnClickListener(this);
        proprietor_tv.setOnClickListener(this);
        got_talent_tv.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        Bundle mBundle = new Bundle();
        super.onClick(v);
        switch (v.getId()) {
            case R.id.mediation_tv://客商中介
                type = "1";
                mBundle.putString("type", type);
                UIHelper.jump(_activity,AccountUpgradeActivity1.class,mBundle);
                break;
            case R.id.consultant_tv://物业顾问
                type = "2";
                mBundle.putString("type", type);
                UIHelper.jump(_activity,AccountUpgradeActivity1.class,mBundle);
                break;
            case R.id.proprietor_tv://业主
                UIHelper.jump(_activity,UpgradeProprietorActivity.class);
                break;
            case R.id.got_talent_tv://服务达人
                type = "4";
                mBundle.putString("type", type);
                UIHelper.jump(_activity,AccountUpgradeActivity1.class,mBundle);
                break;
        }
    }
}
