package com.midian.ppaddress.ui.company;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.TextView;
import android.widget.Toast;

import com.midian.ppaddress.R;
import com.midian.ppaddress.app.Constant;
import com.midian.ppaddress.bean.BusinessAgencyActivityShowBean;
import com.midian.ppaddress.utils.AppUtil;

import midian.baselib.base.BaseActivity;
import midian.baselib.base.BaseListActivity;
import midian.baselib.bean.NetResult;
import midian.baselib.utils.UIHelper;
import midian.baselib.widget.BaseLibTopbarView;

public class CompanyServicePage extends BaseActivity implements View.OnClickListener{

    private BaseLibTopbarView topbar;
    private int activityId;
    private String title;
    private String url;
    private WebView webView;
    private String isapply;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_company_servicepage);
        setContentView(R.layout.activity_company_activitydetail);
        String id=getIntent().getStringExtra("id");
        activityId=Integer.valueOf(id).intValue();
        initView();
        loadData();
    }

    @Override
    public void onApiSuccess(NetResult res, String tag) {
        super.onApiSuccess(res, tag);
        if(res.isOK()){
            BusinessAgencyActivityShowBean bean= (BusinessAgencyActivityShowBean) res;
            url=bean.getData().getUrl();
            webView.loadUrl(url);
            isapply=bean.getData().getIsapply();
            if("0".equals(isapply)){
                findViewById(R.id.ll_btn).setVisibility(View.GONE);
            }
        }
    }

    private void loadData() {
        AppUtil.getPpApiClient(ac).businessAgencyActivityShow(activityId,ac.user_id,this);
    }

    private void initView() {
//        findViewById(R.id.btn_apply).setOnClickListener(this);
        topbar=findView(R.id.topbar);
        webView=findView(R.id.web);
        topbar.setTitle("企业服务").setLeftImageButton(R.drawable.icon_back,UIHelper.finish(_activity)).setRightImageButton(R.drawable.icon_web_close,UIHelper.finish(_activity)).setRight2ImageButton(R.drawable.icon_web_refresh, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                webView.reload();
            }
        });
        findViewById(R.id.btn_apply).setOnClickListener(this);
        if(!"0".equals(ac.getProperty("user_type"))){
            findViewById(R.id.ll_btn).setVisibility(View.GONE);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_apply:
                Intent intent = new Intent();
                intent.setClass(this, CompanyApplyActivity.class);
                intent.putExtra("activityId", activityId);
                intent.putExtra("flag", "1");
                startActivity(intent);
            break;
        }
    }
}

