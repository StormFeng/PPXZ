package com.midian.ppaddress.itemtpl;

import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.midian.login.view.LoginActivity;
import com.midian.ppaddress.R;
import com.midian.ppaddress.bean.BusinessCarrierDetailPageCounselorBean;
import com.midian.ppaddress.ui.chooseaddres.ConsultantDetailActivity;
import com.midian.ppaddress.ui.chooseaddres.ConsultantDetailActivity1;
import com.midian.ppaddress.ui.chooseaddres.MakeAppointmentActivity;

import midian.baselib.utils.UIHelper;
import midian.baselib.view.BaseTpl;
import midian.baselib.widget.CircleImageView;

/**
 * 物业顾问条目模板
 * <p/>
 * Created by chu on 2016/4/25.
 */
public class ConsultantTpl extends BaseTpl<BusinessCarrierDetailPageCounselorBean.CarrierList> implements View.OnClickListener {
    private CircleImageView head_cv;
    private TextView name_tv, company_tv, comment_tv, comments_tv, sub_tv, sign_tv;
    private View item_ll;
    private String counselorid;//物业顾问会员id
    private String carrier_id;//载体id


    public ConsultantTpl(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ConsultantTpl(Context context) {
        super(context);

    }

    @Override
    protected void initView() {
        item_ll = findView(R.id.item_ll);
        head_cv = findView(R.id.head_cv);
        name_tv = findView(R.id.name_tv);
        company_tv = findView(R.id.company_tv);
        comment_tv = findView(R.id.comment_tv);
        comments_tv = findView(R.id.comments_tv);
        sub_tv = findView(R.id.sub_tv);
        sign_tv = findView(R.id.sign_tv);
        sub_tv.setOnClickListener(this);
        item_ll.setOnClickListener(this);// _activity.getIntent().getStringExtra("type");
        carrier_id = _activity.getIntent().getStringExtra("carrierid");//载体id

    }

    @Override
    protected int getLayoutId() {
        return R.layout.item_consultant_tpl;
    }

    @Override
    public void setBean(BusinessCarrierDetailPageCounselorBean.CarrierList bean, int position) {
        if(bean!=null) {
            if (bean.getPortrait() == null) {
                ac.setImage(head_cv, R.drawable.head1);
            } else {
                ac.setImage(head_cv, bean.getPortrait());
            }
            counselorid = bean.getCounselorid();//物业顾问会员id‘
            name_tv.setText(bean.getFullname());
            company_tv.setText(bean.getAgencyCompany());
            comment_tv.setText(bean.getGoodRate() + "%");
            comments_tv.setText(bean.getCounts());
            sign_tv.setText(bean.getDeclaration());
            if ("2".equals(ac.getProperty("user_type"))) {
                sub_tv.setVisibility(GONE);
            }
        }
    }

    @Override
    public void onClick(View view) {
        Bundle bundle = new Bundle();
        switch (view.getId()) {
            case R.id.sub_tv://转一键预约页
                if (!ac.isUserIdExsit()) {
                    UIHelper.jump(_activity, LoginActivity.class);
                    return;
                }
                if (!"0".equals(ac.getProperty("user_type"))) {
                    UIHelper.t(_activity,"只有客商身份才能操作");
                    return;
                }
                bundle.putString("carrierid", carrier_id);//载体id
                bundle.putString("counselorid", counselorid);//驻守的物业顾问id
                UIHelper.jump(_activity, MakeAppointmentActivity.class, bundle);//物业顾问列表条目>>一键预约
                break;
            case R.id.item_ll:
//                UIHelper.jump(_activity,ConsultantDetailActivity.class);//物业顾问详情
                if (counselorid == null) {
                    UIHelper.t(_activity, "id获取失败");
                    return;
                } else {
                    bundle.putString("counselorid", counselorid);//驻守的物业顾问id
                    UIHelper.jump(_activity, ConsultantDetailActivity1.class, bundle);//物业顾问详情
                }

                break;
        }
    }
}
