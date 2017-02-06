package com.midian.ppaddress.ui.personal;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.midian.ppaddress.R;

import midian.baselib.base.BaseActivity;
import midian.baselib.utils.UIHelper;
import midian.baselib.widget.BaseLibTopbarView;

/**
 * 账号升级---业主
 * Created by chu on 2016/4/27.
 */
public class UpgradeProprietorActivity extends BaseActivity {
    private BaseLibTopbarView topbar;
    private TextView phone_tv,confirm_tv;
    private EditText edit_et;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upgrade_proprietor);

        topbar = findView(R.id.topbar);
        topbar.setTitle("业主").setLeftImageButton(R.drawable.icon_back, UIHelper.finish(_activity));
        phone_tv = findView(R.id.phone_tv);
        confirm_tv = findView(R.id.confirm_tv);
        edit_et = findView(R.id.edit_et);
        phone_tv.setText(ac.getProperty("phone"));
        confirm_tv.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.confirm_tv:
                UIHelper.t(_activity,"已投递");
                finish();
                break;
        }
    }
}
