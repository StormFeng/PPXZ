package com.midian.ppaddress.ui.personal;

import android.os.Bundle;
import android.webkit.WebView;

import com.midian.ppaddress.R;

import midian.baselib.base.BaseActivity;
import midian.baselib.utils.UIHelper;
import midian.baselib.widget.BaseLibTopbarView;

/**
 * 约看规则
 */
public class LookRuleActivity extends BaseActivity {
    private BaseLibTopbarView topbar;
    private WebView webview;
    private String url="http://app.business.pploc.com/views/inspectrules";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lookrule);
        topbar = findView(R.id.topbar);
        topbar.setTitle("约看规则").setLeftText("",null).setLeftImageButton(R.drawable.icon_back, UIHelper.finish(_activity));
        webview=findView(R.id.webview);
        webview.loadUrl(url);
    }
}
