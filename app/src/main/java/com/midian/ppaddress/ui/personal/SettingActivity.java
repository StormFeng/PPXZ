package com.midian.ppaddress.ui.personal;

import android.app.Dialog;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.midian.login.view.LoginActivity;
import com.midian.ppaddress.R;

import midian.baselib.base.BaseActivity;
import midian.baselib.common.WebViewActivity;
import midian.baselib.utils.UIHelper;
import midian.baselib.version.VersionUpdateUtil;
import midian.baselib.widget.BaseLibTopbarView;

/**
 * 设置
 * Created by chu on 2016/2/16.
 */
public class SettingActivity extends BaseActivity {
    private BaseLibTopbarView topbar;
    private View change_pwd_ll, change_phone_ll, update_ll, back_ll, share_ll, feedback_ll, account_upgrade_ll, help_ll, about_ll;
    private Button cancel;
    private TextView version;
    private VersionUpdateUtil mVersionUpdateUtil;
    private TextView tv_ask_count;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        initView();
    }

    private void initView() {
        topbar = findView(R.id.topbar);
        topbar.setTitle("设置").setLeftImageButton(R.drawable.icon_back, UIHelper.finish(_activity));
        change_pwd_ll = findView(R.id.change_pwd_ll);
        change_phone_ll = findView(R.id.change_phone_ll);
        update_ll = findView(R.id.update_ll);
        version = findView(R.id.version);
        back_ll = findView(R.id.back_ll);
        share_ll = findView(R.id.share_ll);
        feedback_ll = findView(R.id.feedback_ll);
        account_upgrade_ll = findView(R.id.account_upgrade_ll);
        help_ll = findView(R.id.help_ll);
        about_ll = findView(R.id.about_ll);
        cancel = findView(R.id.cancel);
        tv_ask_count = findView(R.id.tv_ask_count);
        if(ac.isUserIdExsit()) {
            if (!"0".equals(ac.getProperty("user_type"))) {
                account_upgrade_ll.setVisibility(View.GONE);
            }
        }
        change_pwd_ll.setOnClickListener(this);
        change_phone_ll.setOnClickListener(this);
        update_ll.setOnClickListener(this);
        back_ll.setOnClickListener(this);
        share_ll.setOnClickListener(this);
        feedback_ll.setOnClickListener(this);
        account_upgrade_ll.setOnClickListener(this);
        help_ll.setOnClickListener(this);
        about_ll.setOnClickListener(this);
        cancel.setOnClickListener(this);

        //检查版本更新
        if (ac.isHasNewVersion) {
            version.setText("有新版本");
            tv_ask_count.setVisibility(View.VISIBLE);
        } else {
            tv_ask_count.setVisibility(View.GONE);
            version.setText(getVersion());
        }
    }


    @Override
    protected void onResume() {
        super.onResume();
        if (!ac.isAccess()) {
            cancel.setText("立即登录");
        } else {
            cancel.setText("退出登录");
        }
    }
    @Override
    public void onClick(View v) {
        super.onClick(v);
        if(!ac.isUserIdExsit()){
            if(v.getId()!=R.id.help_ll||v.getId()!=R.id.about_ll||v.getId()!=R.id.update_ll){
                UIHelper.jump(_activity, LoginActivity.class);
                return;
            }
        }
        switch (v.getId()) {
            case R.id.change_pwd_ll://更改密码
                UIHelper.jump(_activity,ChangePasswordActivity_1.class);
                break;
            case R.id.change_phone_ll://更改手机号
                UIHelper.jump(_activity,ChangeNumberActivity_1.class);
                break;
            case R.id.update_ll://检查更新
                UIHelper.t(_activity, R.string.update_text);
                if (ac.isHasNewVersion) {
                    mVersionUpdateUtil.BDCheckUpdate();
                } else {
                    UIHelper.t(_activity,"已经是最新版本");
                }
                break;
            case R.id.back_ll://意见反馈
                UIHelper.jump(_activity,FeedBackActivity.class);
                break;
            case R.id.share_ll://分享
                UIHelper.jump(_activity,ShareAppActivity.class);
                break;
            case R.id.account_upgrade_ll://账号升级
                UIHelper.jump(_activity,AccountUpgradeActivity.class);
                break;
            case R.id.feedback_ll://投诉反馈
                UIHelper.jump(_activity,FeedBackActivity_1.class);
                break;
            case R.id.help_ll://帮助中心
                UIHelper.jump(_activity, HelpActivity.class);
                break;
            case R.id.about_ll://关于我们
                UIHelper.jump(_activity, AboutActivity.class);
                break;
            case R.id.cancel:
                if (cancel.getText().toString().equals("立即登录")) {
                    UIHelper.jump(_activity, LoginActivity.class);
                    return;
                } else {
                    final Dialog dialog=new Dialog(_activity,R.style.add_dialog);
                    Window window=dialog.getWindow();
                    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                    dialog.setContentView(R.layout.dialog_logout);
                    dialog.show();
                    window.findViewById(R.id.btn_cancel).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            dialog.dismiss();
                        }
                    });
                    window.findViewById(R.id.btn_ok).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            ac.clearUserInfo();
                            dialog.dismiss();
                            finish();
                        }
                    });
                }
                break;
        }
    }

    /**
     * 获取版本号
     * @return 当前应用的版本号
     */
    public String getVersion() {
        try {
            PackageManager manager = this.getPackageManager();
            PackageInfo info = manager.getPackageInfo(this.getPackageName(), 0);
            String version = info.versionName;
            return  version;//this.getString(R.string.version_name) +
        } catch (Exception e) {
            e.printStackTrace();
            return this.getString(R.string.can_not_find_version_name);
        }
    }
}
