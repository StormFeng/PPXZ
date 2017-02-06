package com.midian.ppaddress.ui.company;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.midian.ppaddress.R;
import com.midian.ppaddress.bean.MemberOrderMemberSitedetailsBean;
import com.midian.ppaddress.utils.AppUtil;

import midian.baselib.base.BaseActivity;
import midian.baselib.bean.NetResult;
import midian.baselib.utils.UIHelper;
import midian.baselib.widget.BaseLibTopbarView;

public class OneKeyAppActivity extends BaseActivity implements View.OnClickListener {

    private BaseLibTopbarView topbar;
    private EditText et_content;
    private TextView tv_title, tv_number, tv_type;
    private Button btn_commit;
    private String counselorid;

    private String flag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onekeyapp);
        initView();
    }

    private void initView() {
        et_content = findView(R.id.et_content);
        tv_title = findView(R.id.tv_title);
        tv_number = findView(R.id.tv_number);
        tv_number.setText(ac.getProperty("phone"));
        tv_type = findView(R.id.tv_type);
        topbar = findView(R.id.topbar);
        btn_commit = findView(R.id.btn_apply);
        btn_commit.setOnClickListener(this);
        flag = mBundle.getString("flag");
        counselorid = mBundle.getString("counselorid");

        if ("ConsultantDetailActivity".equals(flag)) {
            topbar.setLeftImageButton(R.drawable.icon_back, UIHelper.finish(_activity)).setTitle("投递选址需求");
            tv_title.setText("\u3000\u3000请将您的物业需求填写到以下表格，我们将会马上与您联系！");
            tv_type.setText("\u3000\u3000请填写您的选址需求");
            et_content.setHint("请填写您的物业需求，比如：需要300m²");
            btn_commit.setText("委托投递");
//            AppUtil.getPpApiClient(ac).memberOrderMemberSitedetails(ac.user_id, counselorid, this);
        }
        if ("ServicesFragment".equals(flag)) {
            topbar.setLeftImageButton(R.drawable.icon_back, UIHelper.finish(_activity)).setTitle("一键服务请求");
            tv_title.setText("\u3000\u3000请将您的服务需求填写到以下表格，我们将会马上与您联系！");
            tv_type.setText("\u3000\u3000请填写您的服务需求");
            et_content.setHint("请填写您的服务需求，比如：装修");
            btn_commit.setText("一键投递");
        }

    }

    @Override
    public void onApiSuccess(NetResult res, String tag) {
        super.onApiSuccess(res, tag);
        if (res.isOK()) {
            if ("memberOrderMemberSitedetails".equals(tag)) {
                MemberOrderMemberSitedetailsBean bean = (MemberOrderMemberSitedetailsBean) res;
                tv_number.setText(bean.getData().getMobilephone());
            }
            if ("memberOrderMemberSend".equals(tag)) {
                UIHelper.t(_activity, res.message);//"投递选址需求成功！"
                finish();
            }
            if ("businessAgencyScreenOnekeyApply".equals(tag)) {
                UIHelper.t(_activity, res.message);//"申请活动成功！"
                finish();
            }
        } else {
            ac.handleErrorCode(_activity, res.errorcode);
        }
    }

    @Override
    public void onClick(View view) {
        String content = et_content.getText().toString().trim();
        if ("ConsultantDetailActivity".equals(flag)) {
            if ("".equals(content)) {
                UIHelper.t(_activity, "请填写您的选址需求");
                return;
            }
            AppUtil.getPpApiClient(ac).memberOrderMemberSend(ac.user_id, counselorid, ac.user_type, content, this);
        } else if ("ServicesFragment".equals(flag)) {
            if ("".equals(content)) {
                UIHelper.t(_activity, "请填写您的服务需求");
                return;
            }
            AppUtil.getPpApiClient(ac).businessAgencyScreenOnekeyApply(ac.user_id, content, this);
        }
    }
}

