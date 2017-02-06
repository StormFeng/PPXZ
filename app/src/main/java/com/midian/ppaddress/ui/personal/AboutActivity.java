package com.midian.ppaddress.ui.personal;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.midian.maplib.ServerConstant;
import com.midian.ppaddress.R;
import com.midian.ppaddress.app.Constant;

import midian.baselib.base.BaseActivity;
import midian.baselib.utils.UIHelper;
import midian.baselib.widget.BaseLibTopbarView;

/**
 * 关于我们
 * Created by chu on 2016/2/18.
 */
public class AboutActivity extends BaseActivity {

    private BaseLibTopbarView topbar;
    private WebView webview;
    private String title = "";
    private String url = Constant.BBASEURL+"views/aboutus";//业务中心域名/views/aboutus

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.midian.baselib.R.layout.activity_webview);
        initView();
    }

    private void initView() {
        topbar = (BaseLibTopbarView) findViewById(com.midian.baselib.R.id.topbar);
        topbar.setTitle(title)
                .setLeftImageButton(com.midian.baselib.R.drawable.icon_back,
                        new View.OnClickListener() {

                            @Override
                            public void onClick(View arg0) {
                                if (webview != null)
                                    webview = null;
                                if (webViewClient != null)
                                    webViewClient = null;
                                System.out.println("finish");
                                finish();
                            }
                        }).setLeftText("返回", null);
        webview = (WebView) findViewById(com.midian.baselib.R.id.webview);
        webview.getSettings().setJavaScriptEnabled(true);

        webview.getSettings().setDomStorageEnabled(true);
        webview.getSettings().setUseWideViewPort(true);
        webview.getSettings().setLoadWithOverviewMode(true);
        webview.loadUrl(url);
        topbar.showProgressBar();
        webview.setWebViewClient(webViewClient);

    }

    private WebViewClient webViewClient = new WebViewClient() {

        public void onPageFinished(WebView view, String url) {
            topbar.hideProgressBar();
        };

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            topbar.hideProgressBar();
            view.loadUrl(url);
            return super.shouldOverrideUrlLoading(view, url);
        }

        @Override
        public void onReceivedError(WebView view, int errorCode,
                                    String description, String failingUrl) {
            super.onReceivedError(view, errorCode, description, failingUrl);
        }

    };

    protected void onDestroy() {
        super.onDestroy();
        if (webview != null)
            webview = null;
        if (webViewClient != null)
            webViewClient = null;
    };

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_HOME) {
        }
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (webview.canGoBack()) {
                webview.goBack();
            } else {
                this.finish();
            }

            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

}
