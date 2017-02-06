package com.midian.ppaddress.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.widget.Button;
import android.widget.ImageView;

import android.os.Handler;

import com.midian.ppaddress.R;
import com.midian.ppaddress.bean.AppBean;
import com.midian.ppaddress.ui.main.MainActivity;
import com.midian.ppaddress.utils.AppUtil;

import java.text.SimpleDateFormat;
import java.util.Date;

import midian.baselib.app.AppManager;
import midian.baselib.base.BaseActivity;
import midian.baselib.bean.NetResult;
import midian.baselib.utils.FDDataUtils;
import midian.baselib.utils.UIHelper;
public class WelActivity extends BaseActivity implements OnClickListener {
    private Button button;
    private Object app;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ImageView iv = (ImageView) findViewById(R.id.imageView1);
        button = (Button) findViewById(R.id.button1);
        button.setOnClickListener(this);
        button.setVisibility(View.GONE);
        // 一秒
        AlphaAnimation alphaAnimation = new AlphaAnimation(0f, 1);
        alphaAnimation.setDuration(1000);
        alphaAnimation.setAnimationListener(aListener);
        iv.setAnimation(alphaAnimation);
    }


    private AnimationListener aListener = new AnimationListener() {

        @Override
        public void onAnimationEnd(Animation arg0) {
            button.postDelayed(new Runnable() {

                @Override
                public void run() {
                    /*if (ac.getBoolean("app")) {
                        UIHelper.jump(WelActivity.this, MainActivity.class);
                    } else {
                        ac.setBoolean("app", true);
                        UIHelper.jump(WelActivity.this, GuideActivity.class);
                    }
                    finish();*/
                    getApp();
                }
            }, 3000);
        }

        @Override
        public void onAnimationRepeat(Animation arg0) {
        }

        @Override
        public void onAnimationStart(Animation arg0) {
        }
    };


    @Override
    public void onClick(View v) {
        super.onClick(v);
        v.setEnabled(false);
        UIHelper.jump(WelActivity.this, MainActivity.class);
        ac.setBoolean("app", true);
        finish();
    }

    private void getApp() {
//        AppUtil.getPpApiClient(ac).getApp(this);
        inApp();
    }

    @Override
    public void onApiSuccess(NetResult res, String tag) {
        super.onApiSuccess(res, tag);
        hideLoadingDlg();
        if (res instanceof AppBean) {
            AppBean appBean = (AppBean) res;//1是开放，2是关闭
            Log.d("-=-=-=-", "onApiSuccess: getProjectStatus=" + appBean.getProjectStatus());
            if ("2".equals(appBean.getProjectStatus())) {
                AppManager.getAppManager().appExit(_activity);
            } else {
                inApp();
            }
        }
    }

    @Override
    public void onApiFailure(Throwable t, int errorNo, String strMsg, String tag) {
        super.onApiFailure(t, errorNo, strMsg, tag);
        inApp();
    }


    @Override
    public void onParseError(String tag) {
        super.onParseError(tag);
        AppManager.getAppManager().appExit(_activity);
        inApp();
    }

    private  void inApp(){
        if (ac.getBoolean("app")) {
            UIHelper.jump(WelActivity.this, MainActivity.class);
        } else {
            ac.setBoolean("app", true);
            UIHelper.jump(WelActivity.this, GuideActivity.class);
        }
        finish();
    }
}
