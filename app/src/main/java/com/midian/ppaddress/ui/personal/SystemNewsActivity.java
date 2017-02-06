package com.midian.ppaddress.ui.personal;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.TextView;

import com.midian.ppaddress.R;
import com.midian.ppaddress.bean.MemberMessageShowSysMessagesBean;
import com.midian.ppaddress.utils.AppUtil;

import midian.baselib.base.BaseActivity;
import midian.baselib.bean.NetResult;
import midian.baselib.utils.UIHelper;
import midian.baselib.widget.BaseLibTopbarView;

/**
 * 消息详情
 */
public class SystemNewsActivity extends BaseActivity {
    private BaseLibTopbarView topbar;
    private TextView tv_sendtime, tv_title;
    private WebView webView;
    private String messageid;
    private String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_system_news);
        messageid = mBundle.getString("messageid");
        topbar = findView(R.id.topbar);
        topbar.setTitle("消息详情").setLeftImageButton(R.drawable.icon_back, UIHelper.finish(this));
        webView=findView(R.id.webView);
        if("1".equals(mBundle.getString("flag"))){
            url = mBundle.getString("url");
            webView.setVisibility(View.VISIBLE);
            webView.loadUrl(url);
        }else{
            webView.setVisibility(View.GONE);
        }
        tv_sendtime = (TextView) findViewById(R.id.tv_sendtime);
        tv_title = (TextView) findViewById(R.id.tv_title);
        AppUtil.getPpApiClient(ac).memberMessageShowSysMessages(messageid, this);
    }

    @Override
    public void onApiSuccess(NetResult res, String tag) {
        super.onApiSuccess(res, tag);
        MemberMessageShowSysMessagesBean bean = (MemberMessageShowSysMessagesBean) res;
        tv_sendtime.setText(bean.getData().getSendTime());
        tv_title.setText(bean.getData().getTitle());
    }
}
