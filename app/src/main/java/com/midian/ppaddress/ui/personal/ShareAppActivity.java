package com.midian.ppaddress.ui.personal;

import android.os.Bundle;
import android.view.View;

import com.midian.UMengUtils.ShareContent;
import com.midian.UMengUtils.UMengShareUtil;
import com.midian.ppaddress.R;
import com.midian.ppaddress.bean.AppInfoBean;
import com.midian.ppaddress.utils.AppUtil;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.bean.SocializeEntity;

import midian.baselib.base.BaseActivity;
import midian.baselib.bean.NetResult;
import midian.baselib.utils.UIHelper;
import midian.baselib.widget.BaseLibTopbarView;

public class ShareAppActivity extends BaseActivity {
    private BaseLibTopbarView topbar;
    private UMengShareUtil mShareUtil;
    private ShareContent mImageContent = new ShareContent();
    private String title, content, url, img;
    private View wechat, wechat_friend, qzone, qq, weibo, sms;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share);
        mShareUtil = UMengShareUtil.getInstance((_activity));
        mShareUtil.setUMengShareUtilListener(mUMengShareUtilListener);

        topbar = findView(R.id.topbar);
        topbar.setTitle("分享").setLeftImageButton(R.drawable.icon_back, UIHelper.finish(_activity));
        findViewById(R.id.wechat).setOnClickListener(this);
        findViewById(R.id.wechat_friend).setOnClickListener(this);
        findViewById(R.id.qq).setOnClickListener(this);
        findViewById(R.id.qzone).setOnClickListener(this);
        findViewById(R.id.weibo).setOnClickListener(this);
        findViewById(R.id.sms).setOnClickListener(this);

        initLoad();
    }

    private void initLoad() {
        AppUtil.getPpApiClient(ac).getInfrasShareapp(this);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        if (mImageContent == null || mImageContent.equals("")) {
            UIHelper.t(_activity, "分享数据未获取成功");
            return;
        } else {
            switch (v.getId()) {
                case R.id.wechat:
                    mShareUtil.share(SHARE_MEDIA.WEIXIN, mImageContent);
                    break;
                case R.id.wechat_friend:
                    mShareUtil.share(SHARE_MEDIA.WEIXIN_CIRCLE, mImageContent);
                    break;
                case R.id.qq:
                    mShareUtil.share(SHARE_MEDIA.QQ, mImageContent);
                    break;
                case R.id.qzone:
                    mShareUtil.share(SHARE_MEDIA.QZONE, mImageContent);
                    break;
                case R.id.weibo:
                    mShareUtil.share(SHARE_MEDIA.SINA, mImageContent);
                    break;
                case R.id.sms:
                    mShareUtil.share(SHARE_MEDIA.SMS, mImageContent);
                    break;
            }
        }

    }

    UMengShareUtil.UMengShareUtilListener mUMengShareUtilListener = new UMengShareUtil.UMengShareUtilListener() {

        @Override
        public void onStart() {
        }

        @Override
        public void onComplete(SHARE_MEDIA platform, int eCode, SocializeEntity entity) {
        }

    };


    @Override
    public void onApiStart(String tag) {
        super.onApiStart(tag);
        showLoadingDlg();
    }

    @Override
    public void onApiSuccess(NetResult res, String tag) {
        super.onApiSuccess(res, tag);
        hideLoadingDlg();
        if (res.isOK()) {
            AppInfoBean bean = (AppInfoBean) res;
            title = bean.getData().getSharetitle();
            content = bean.getData().getSharetxt();
            url = bean.getData().getShareurl();
            img = bean.getData().getShareimg();

            mImageContent.setAppName("PP投服");
            mImageContent.setImage(bean.getData().getShareimg());// 分享的图片
            // mImageContent.setmBitmap(BitmapFactory.decodeResource(context.getResources(),
            // R.drawable.ic_launcher));// 传本地图片如果没一定传null
            mImageContent.setSummary(bean.getData().getSharetxt());// 分享的内容
            mImageContent.setTitle(bean.getData().getSharetitle());// 分享的标题
            mImageContent.setUrl(bean.getData().getShareurl());// 分享链接
        } else {
            ac.handleErrorCode(_activity, res.errorcode);
        }
    }
}
