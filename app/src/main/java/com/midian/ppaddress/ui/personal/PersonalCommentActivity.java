package com.midian.ppaddress.ui.personal;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Checkable;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.baidu.mapapi.map.Text;
import com.midian.ppaddress.R;
import com.midian.ppaddress.bean.AddMemberMeAgencyexpertBean;
import com.midian.ppaddress.bean.AddMemberMeOrderBean;
import com.midian.ppaddress.utils.AppUtil;

import midian.baselib.base.BaseActivity;
import midian.baselib.bean.NetResult;
import midian.baselib.utils.FDDataUtils;
import midian.baselib.utils.UIHelper;
import midian.baselib.widget.BaseLibTopbarView;
import midian.baselib.widget.CircleImageView;

/**
 * 个人中心--我的评价--评价
 * Created by chu on 2016/5/3.
 */
public class PersonalCommentActivity extends BaseActivity implements View.OnClickListener {
    private String serverid;
    private int carrierid, rate_to_carrier, rate_to_service;
    private String content_to_service, content_to_carrier;

    private BaseLibTopbarView topbar;
    private String orderid;
    private CheckBox cb_rate1, cb_rate2, cb_rate3, cb_bot_rate1, cb_bot_rate2, cb_bot_rate3;
    private ImageView iv_image;
    private CircleImageView iv_portrait;
    private TextView tv_name, tv_type, tv_price, tv_area, tv_fullname, tv_company, tv_saleRental, tv_ok, tv_question, tv_time;
    private EditText con_to_service, con_to_carrier;
    private TextView position_tv;
    private String flag;
    private String questionid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        orderid = getIntent().getStringExtra("orderid");
        flag = getIntent().getStringExtra("flag");
        setContentView(R.layout.activity_personal_comment);
        initView();
        setCheckBox1(R.id.cb_rate1);
        setCheckBox2(R.id.cb_bot_rate1);

    }

    private void setCheckBox1(int arg0) {
        cb_rate1.setChecked(false);
        cb_rate2.setChecked(false);
        cb_rate3.setChecked(false);
        cb_rate1.setTextColor(getResources().getColor(R.color.text_bg90));
        cb_rate2.setTextColor(getResources().getColor(R.color.text_bg90));
        cb_rate3.setTextColor(getResources().getColor(R.color.text_bg90));
        CheckBox cb = (CheckBox) findViewById(arg0);
        cb.setChecked(true);
        cb.setTextColor(getResources().getColor(R.color.white));
    }

    private void setCheckBox2(int arg0) {
        cb_bot_rate1.setChecked(false);
        cb_bot_rate2.setChecked(false);
        cb_bot_rate3.setChecked(false);
        cb_bot_rate1.setTextColor(getResources().getColor(R.color.text_bg90));
        cb_bot_rate2.setTextColor(getResources().getColor(R.color.text_bg90));
        cb_bot_rate3.setTextColor(getResources().getColor(R.color.text_bg90));
        CheckBox cb = (CheckBox) findViewById(arg0);
        cb.setChecked(true);
        cb.setTextColor(getResources().getColor(R.color.white));
    }

    private void initView() {
        topbar = findView(R.id.topbar);
        topbar.setTitle("评价").setLeftImageButton(R.drawable.icon_back, UIHelper.finish(_activity));
        iv_image = (ImageView) findViewById(R.id.iv_image);
        iv_portrait = findView(R.id.iv_portrait);
        tv_name = (TextView) findViewById(R.id.tv_name);
        tv_type = (TextView) findViewById(R.id.tv_type);
        tv_saleRental = (TextView) findViewById(R.id.tv_salerental);
        tv_price = (TextView) findViewById(R.id.tv_price);
        tv_area = (TextView) findViewById(R.id.tv_area);
        tv_fullname = (TextView) findViewById(R.id.tv_fullname);
        position_tv = findView(R.id.position_tv);
        tv_company = (TextView) findViewById(R.id.tv_company);
        tv_question = findView(R.id.tv_question);
        tv_time = findView(R.id.tv_time);
        cb_rate1 = (CheckBox) findViewById(R.id.cb_rate1);
        cb_rate2 = (CheckBox) findViewById(R.id.cb_rate2);
        cb_rate3 = (CheckBox) findViewById(R.id.cb_rate3);
        cb_bot_rate1 = (CheckBox) findViewById(R.id.cb_bot_rate1);
        cb_bot_rate2 = (CheckBox) findViewById(R.id.cb_bot_rate2);
        cb_bot_rate3 = (CheckBox) findViewById(R.id.cb_bot_rate3);
        con_to_carrier = (EditText) findViewById(R.id.et_to_carrier);
        con_to_service = (EditText) findViewById(R.id.et_to_agency);
        cb_rate1.setOnClickListener(this);
        cb_rate2.setOnClickListener(this);
        cb_rate3.setOnClickListener(this);
        cb_bot_rate1.setOnClickListener(this);
        cb_bot_rate2.setOnClickListener(this);
        cb_bot_rate3.setOnClickListener(this);
        findView(R.id.tv_ok).setOnClickListener(this);
        if ("MyConsultantCommentListTpl".equals(flag)) {//从MyConsultantCommentListTpl 对物业顾问的评价跳转
            findView(R.id.ll_question).setVisibility(View.GONE);
            AppUtil.getPpApiClient(ac).memberCommentsMemberAddMemberMeOrder(orderid, this);
        } else if ("ServiceGotTalentTpl".equals(flag)) {//入口：对服务达人的评价
            findView(R.id.ll_carrier).setVisibility(View.GONE);
            con_to_service.setHint("请填写您对服务达人的点评！");
            questionid = getIntent().getStringExtra("questionid");
            AppUtil.getPpApiClient(ac).addMemberMeAgencyexpert(questionid, this);
        }
    }

    @Override
    public void onClick(View arg0) {
        super.onClick(arg0);
        content_to_service = con_to_service.getText().toString().trim();
        content_to_carrier = con_to_carrier.getText().toString().trim();
        cb_rate1.isChecked();
        if (cb_rate1.isChecked())
            rate_to_carrier = 1;
        else if (cb_rate2.isChecked())
            rate_to_carrier = 2;
        else if (cb_rate3.isChecked())
            rate_to_carrier = 3;

        if (cb_bot_rate1.isChecked())
            rate_to_service = 1;
        else if (cb_bot_rate2.isChecked())
            rate_to_service = 2;
        else if (cb_bot_rate3.isChecked())
            rate_to_service = 3;

        switch (arg0.getId()) {
            case R.id.cb_rate1:
                setCheckBox1(R.id.cb_rate1);

                break;
            case R.id.cb_rate2:
                setCheckBox1(R.id.cb_rate2);
                break;
            case R.id.cb_rate3:
                setCheckBox1(R.id.cb_rate3);
                break;
            case R.id.cb_bot_rate1:
                setCheckBox2(R.id.cb_bot_rate1);
                break;
            case R.id.cb_bot_rate2:
                setCheckBox2(R.id.cb_bot_rate2);
                break;
            case R.id.cb_bot_rate3:
                setCheckBox2(R.id.cb_bot_rate3);
                break;
            case R.id.tv_ok:
                if ("MyConsultantCommentListTpl".equals(flag)) {
                    if (TextUtils.isEmpty(content_to_carrier)) {
                        UIHelper.t(_activity, "请填写您对载体的评价！");
                    } else if (TextUtils.isEmpty(content_to_service)) {
                        UIHelper.t(_activity, "请填写您对物业顾问的评价！");
                    } else {
                        AppUtil.getPpApiClient(ac).commentToConsultant(orderid, ac.user_id, serverid, content_to_service,
                                rate_to_service, carrierid, content_to_carrier, rate_to_carrier, this);
                    }
                } else if ("ServiceGotTalentTpl".equals(flag)) {
                    if (TextUtils.isEmpty(content_to_service)) {
                        UIHelper.t(_activity, "请填写您对服务达人的评价！");
                    } else {
                        AppUtil.getPpApiClient(ac).commentMemberMeAgencyexpert(ac.user_id, questionid, serverid, content_to_service, rate_to_service, this);
                    }
                }
                break;
        }
    }

    @Override
    public void onApiSuccess(NetResult res, String tag) {
        super.onApiSuccess(res, tag);
        if (res.isOK()) {
            if ("memberCommentsMemberAddMemberMeOrder".equals(tag)) {
                AddMemberMeOrderBean bean = (AddMemberMeOrderBean) res;
                ac.setImage(iv_image, bean.getData().getImages());
                ac.setImage(iv_portrait, bean.getData().getCounselorPortrait());
                tv_name.setText(bean.getData().getCarrierName());
                tv_fullname.setText(bean.getData().getCounselorFullname());
                position_tv.setVisibility(View.GONE);
                tv_company.setText(bean.getData().getAgencyCompany());
                if ("0".equals(bean.getData().getSaleRental())) {
                    tv_saleRental.setText("租售");
                    if ("0".equals(bean.getData().getPriceRent())) {
                        tv_price.setText("面议");
                    } else
                        tv_price.setText(FDDataUtils.addComma(bean.getData().getPriceSell()) + "元/m²·月 起");
                } else if ("1".equals(bean.getData().getSaleRental())) {
                    tv_saleRental.setText("租");
                    tv_price.setText(FDDataUtils.addComma(bean.getData().getPriceRent()) + "元/m²·月 起");
                } else if ("2".equals(bean.getData().getSaleRental())) {
                    tv_saleRental.setText("售");
                    tv_price.setText(FDDataUtils.addComma(bean.getData().getPriceSell()) + "元/m²");
                }

                //carrierType载体类型，3为土地，4为写字楼，6为厂房，8为仓库
                if ("3".equals(bean.getData().getCarrierType())) {
                    tv_type.setText("土地");
                } else if ("4".equals(bean.getData().getCarrierType())) {
                    tv_type.setText("写字楼");
                } else if ("6".equals(bean.getData().getCarrierType())) {
                    tv_type.setText("厂房");
                } else if ("8".equals(bean.getData().getCarrierType())) {
                    tv_type.setText("仓库");
                }
                serverid = bean.getData().getCounselorid();
                carrierid = Integer.valueOf(bean.getData().getCarrierid()).intValue();
            }
            if ("commentToConsultant".equals(tag)) {
                UIHelper.t(this, "提交成功");
                finish();
            }
            if ("addMemberMeAgencyexpert".equals(tag)) {
                position_tv.setVisibility(View.VISIBLE);
                AddMemberMeAgencyexpertBean bean = (AddMemberMeAgencyexpertBean) res;
                ac.setImage(iv_portrait, bean.getData().getPortrait());
                tv_fullname.setText(bean.getData().getAgencyexpert());
                position_tv.setText(bean.getData().getPosition());
                tv_company.setText(bean.getData().getAgencyName());
                serverid = bean.getData().getMemberid();
                tv_question.setText("问题:" + bean.getData().getQuestion());
                tv_time.setText(bean.getData().getCreateTime());
            }
            if ("commentMemberMeAgencyexpert".equals(tag)) {
                UIHelper.t(this, "提交成功");
                finish();
            }
        } else {
            ac.handleErrorCode(_activity, res.message);
        }
    }
}
