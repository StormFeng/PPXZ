package com.midian.ppaddress.ui.chooseaddres;

import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.midian.ppaddress.R;

import midian.baselib.base.BaseActivity;
import midian.baselib.utils.UIHelper;
import midian.baselib.widget.BaseLibTopbarView;

/**
 * 3D实景
 * Created by chu on 2016/5/27.
 */
public class ThirdViewActivity extends BaseActivity {
    private BaseLibTopbarView topbar;
    private WebView webview;
    private String thirdView,title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third_view);
        topbar = findView(R.id.topbar);
        webview = findView(R.id.webview);
        title = mBundle.getString("title");
        thirdView = mBundle.getString("thirdView");
        topbar.setTitle(title).setLeftImageButton(R.drawable.icon_back, UIHelper.finish(_activity));
        webview.getSettings().setJavaScriptEnabled(true);
        webview.loadUrl(thirdView);//"http://m.mdkg.net/"
        webview.setWebViewClient(new WebViewClient());

    }
}
