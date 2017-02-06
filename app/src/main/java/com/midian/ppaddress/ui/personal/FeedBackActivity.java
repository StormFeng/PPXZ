package com.midian.ppaddress.ui.personal;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.midian.ppaddress.R;
import com.midian.ppaddress.utils.AppUtil;

import midian.baselib.base.BaseActivity;
import midian.baselib.bean.NetResult;
import midian.baselib.utils.UIHelper;
import midian.baselib.widget.BaseLibTopbarView;

public class FeedBackActivity extends BaseActivity {
    private BaseLibTopbarView topbar;
    private EditText et_content;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        topbar = findView(R.id.topbar);
        et_content=findView(R.id.et_content);
        topbar.setTitle("意见反馈").setLeftImageButton(R.drawable.icon_back, UIHelper.finish(_activity));
        findView(R.id.btn_commit).setOnClickListener(this);
    }

    @Override
    public void onClick(View arg0) {
        super.onClick(arg0);
        String str=et_content.getText().toString().trim();
        if(TextUtils.isEmpty(str)){
            UIHelper.t(_activity,"请填写反馈内容！");
        }else {
            AppUtil.getPpApiClient(ac).memberMembershipOpinionsSave(ac.user_id, str, this);
        }
    }

    @Override
    public void onApiSuccess(NetResult res, String tag) {
        super.onApiSuccess(res, tag);
        if (res.isOK()) {
            UIHelper.t(_activity, "提交意见反馈成功!");
            finish();
        } else {
            ac.handleErrorCode(_activity, res.message);
        }
    }
}
