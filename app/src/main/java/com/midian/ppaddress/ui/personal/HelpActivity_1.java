package com.midian.ppaddress.ui.personal;

import android.os.Bundle;
import android.webkit.WebView;
import android.widget.TextView;

import com.midian.maplib.AppConstant;
import com.midian.ppaddress.R;
import com.midian.ppaddress.app.Constant;
import com.midian.ppaddress.datasource.HelpDataSource;
import com.midian.ppaddress.itemtpl.HelpTpl;

import java.util.ArrayList;

import midian.baselib.base.BaseActivity;
import midian.baselib.base.BaseListActivity;
import midian.baselib.shizhefei.view.listviewhelper.IDataSource;
import midian.baselib.utils.UIHelper;
import midian.baselib.widget.BaseLibTopbarView;

public class HelpActivity_1 extends BaseActivity {
    private BaseLibTopbarView topbar;
    private WebView webView;
    private String url;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        url=mBundle.getString("url");
        setContentView(R.layout.activity_help_1);
        initView();
    }
    private void initView() {
        topbar=findView(R.id.topbar);
        topbar.setTitle("帮助中心").setLeftImageButton(R.drawable.icon_back, UIHelper.finish(this));
        webView=findView(R.id.webView);
        webView.loadUrl(url);
    }
}
