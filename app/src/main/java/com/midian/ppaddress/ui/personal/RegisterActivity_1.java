package com.midian.ppaddress.ui.personal;

import android.os.Bundle;
import android.view.View;

import com.midian.ppaddress.R;

import midian.baselib.base.BaseActivity;
import midian.baselib.utils.UIHelper;
import midian.baselib.widget.BaseLibTopbarView;

/**
 * Created by Administrator on 2016/4/28.
 */
public class RegisterActivity_1 extends BaseActivity implements View.OnClickListener{
    private BaseLibTopbarView topbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_1);
        topbar=findView(R.id.topbar);
        topbar.setTitle("注册").setLeftImageButton(R.drawable.icon_back, UIHelper.finish(this));;
        findViewById(R.id.btn_next).setOnClickListener(this);
    }

    @Override
    public void onClick(View arg0) {
        super.onClick(arg0);
        UIHelper.jump(this,RegisterActivity_2.class);
    }
}
