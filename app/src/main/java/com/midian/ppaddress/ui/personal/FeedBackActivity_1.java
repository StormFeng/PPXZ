package com.midian.ppaddress.ui.personal;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.midian.ppaddress.R;
import com.midian.ppaddress.utils.AppUtil;

import midian.baselib.base.BaseActivity;
import midian.baselib.bean.NetResult;
import midian.baselib.utils.UIHelper;
import midian.baselib.widget.BaseLibTopbarView;

/**
 * Created by Administrator on 2016/4/28.
 */
public class FeedBackActivity_1 extends BaseActivity {
    private BaseLibTopbarView topbar;
    private TextView tv_Type;
    private String type,classifyid;
    private EditText et_feedback,et_number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback_1);
        initView();


    }

    private void initView() {
        topbar=findView(R.id.topbar);
        tv_Type=findView(R.id.tv_type);
        et_feedback=findView(R.id.et_feedback);
        et_number=findView(R.id.et_number);
        topbar.setTitle("投诉反馈").setLeftImageButton(R.drawable.icon_back, UIHelper.finish(this));
        findViewById(R.id.ll_type).setOnClickListener(this);
        findView(R.id.btn_commit).setOnClickListener(this);
    }

    @Override
    public void onClick(View arg0) {
        super.onClick(arg0);
        switch (arg0.getId()){
            case R.id.ll_type:
                startActivityForResult(new Intent(FeedBackActivity_1.this,FeedBackActivity_2.class),1);
                break;
            case R.id.btn_commit:
                if("选择举报类型".equals(tv_Type.getText().toString().trim())){
                    UIHelper.t(_activity,"请选择举报类型");
                }else{
                    String feedback=et_feedback.getText().toString().trim();
                    String number=et_number.getText().toString().trim();
                    if("".equals(number)){
                        UIHelper.t(_activity,"请输入您的联系方式");
                    }else{
                        AppUtil.getPpApiClient(ac).CommitFeedback(ac.user_id,classifyid,feedback,number,this);
                    }
                }
                finish();
                break;
        }
    }

    @Override
    public void onApiSuccess(NetResult res, String tag) {
        super.onApiSuccess(res, tag);
        UIHelper.t(_activity,"投诉反馈成功");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK) {
            type = data.getExtras().getString("type");
            classifyid=data.getExtras().getString("classifyid");
            Log.d("wqf","type::"+type);
            tv_Type.setText(type);
        }
    }
}
