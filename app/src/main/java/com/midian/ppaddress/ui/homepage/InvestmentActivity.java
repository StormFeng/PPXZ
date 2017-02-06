package com.midian.ppaddress.ui.homepage;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.midian.ppaddress.R;

import midian.baselib.base.BaseActivity;
import midian.baselib.utils.UIHelper;
import midian.baselib.widget.BaseLibTopbarView;

/**
 * 城市投资环境
 * Created by chu on 2016/3/25.
 */
public class InvestmentActivity extends BaseActivity {
    private BaseLibTopbarView topbar;
    private WebView webview;
    private String url;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_investment);
        url = mBundle.getString("url");
        topbar = findView(R.id.topbar);
        topbar.setTitle("投资环境");
        topbar.setLeftImageButton(R.drawable.icon_back, UIHelper.finish(_activity));
        topbar.setRightImageButton(R.drawable.icon_web_close, UIHelper.finish(_activity)).showRight_ib();
        topbar.setRight2ImageButton(R.drawable.icon_web_refresh, this);
        webview = findView(R.id.webview);
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

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.area_tv:
                break;
            case R.id.right2_ib:
                webview.reload();
                break;
        }
    }


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
