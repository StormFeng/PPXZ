package com.midian.ppaddress.ui.personal;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.midian.login.utils.ObjectAnimatorTools;
import com.midian.login.utils.ValidateTools;
import com.midian.ppaddress.R;
import com.midian.ppaddress.utils.AppUtil;

import midian.baselib.base.BaseActivity;
import midian.baselib.bean.NetResult;
import midian.baselib.utils.UIHelper;
import midian.baselib.widget.BaseLibTopbarView;

/**
 * Created by Administrator on 2016/4/28.
 */
public class ChangeBankCardActivity_2 extends BaseActivity implements View.OnClickListener{
    private BaseLibTopbarView topbar;
    private EditText et_Name, et_Bank, et_BankCard;
    private Button btn_Complete;
    private LinearLayout ll_pwd1,ll_pwd2,ll_pwd3;
    private String phone,code;
    private int type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_changebankcard_2);
        phone=mBundle.getString("phone");
        type=mBundle.getInt("type");
        code=mBundle.getString("code");
        initView();
    }

    private void initView() {
        topbar=findView(R.id.topbar);
        topbar.setTitle("修改密码").setLeftImageButton(R.drawable.icon_back, UIHelper.finish(this));
        findViewById(R.id.btn_complete).setOnClickListener(this);//完成
        ll_pwd1=findView(R.id.ll_pwd1);
        ll_pwd2=findView(R.id.ll_pwd2);
        ll_pwd3=findView(R.id.ll_pwd3);
        btn_Complete=findView(R.id.btn_complete);
        et_Name =findView(R.id.et_password);
        et_Bank =findView(R.id.et_password2);
        et_BankCard =findView(R.id.et_password3);
        btn_Complete.setOnClickListener(this);
    }

    @Override
    public void onApiSuccess(NetResult res, String tag) {
        super.onApiSuccess(res, tag);
        UIHelper.t(_activity,res.message);
        Bundle bundle = new Bundle();
        setResult(RESULT_OK, bundle);
        finish();
    }

    @Override
    public void onClick(View arg0) {
        super.onClick(arg0);
        int memberid=Integer.valueOf(ac.user_id).intValue();
        String name= et_Name.getText().toString().trim();
        String bank= et_Bank.getText().toString().trim();
        String bankcard= et_BankCard.getText().toString().trim();

        if (ValidateTools.isEmptyString(name)) {
            ObjectAnimatorTools.onVibrationView(ll_pwd1);
            UIHelper.t(_activity, "姓名不能为空");
            return;
        }
        if (name.length()>4) {
            ObjectAnimatorTools.onVibrationView(ll_pwd1);
            UIHelper.t(_activity, "请正确输入姓名");
            return;
        }
        if (ValidateTools.isEmptyString(bank)) {
            ObjectAnimatorTools.onVibrationView(ll_pwd1);
            UIHelper.t(_activity, "开户行名称不能为空");
            return;
        }
        if (ValidateTools.isEmptyString(bankcard)) {
            ObjectAnimatorTools.onVibrationView(ll_pwd1);
            UIHelper.t(_activity, "银行卡账号不能为空");
            return;
        }
        AppUtil.getPpApiClient(ac).updatePayinfo(memberid,phone,type,code,name,bank,bankcard,this);
    }
}
