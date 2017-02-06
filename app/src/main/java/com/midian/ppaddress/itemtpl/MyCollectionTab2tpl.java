package com.midian.ppaddress.itemtpl;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.midian.ppaddress.R;
import com.midian.ppaddress.bean.MemberFavoritePageAgencyBean;
import com.midian.ppaddress.bean.MemberFavoritePageAgencyBean.AgencyList;
import com.midian.ppaddress.ui.company.CompanyDetialActivity;

import java.util.List;

import midian.baselib.bean.NetResult;
import midian.baselib.utils.UIHelper;
import midian.baselib.view.BaseTpl;
import midian.baselib.widget.FlowLayout;

public class MyCollectionTab2tpl extends BaseTpl<AgencyList> implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {

    private ImageView iv_logo;
    private TextView tv_goodRate, tv_label1, tv_label2, tv_label3, tv_serviceCount, tv_name;
    private String id;
    private View item;
    private CheckBox check_box_news;
    AgencyList bean;
    private FlowLayout lable_layout;//标签流布局
    private boolean isFrist = true;


    public MyCollectionTab2tpl(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyCollectionTab2tpl(Context context) {
        super(context);
    }

    @Override
    protected void initView() {
        iv_logo = findView(R.id.iv_icon);
        tv_goodRate = findView(R.id.tv_goodRate);
       /* tv_label1 = findView(R.id.tv_label1);
        tv_label2 = findView(R.id.tv_label2);
        tv_label3 = findView(R.id.tv_label3);*/
        tv_serviceCount = findView(R.id.tv_serviceCount);
        tv_name = findView(R.id.tv_name);
        item = findView(R.id.item);
        check_box_news = findView(R.id.check_box_news);
        lable_layout = findView(R.id.lable_layout);
        item.setOnClickListener(this);
        check_box_news.setOnCheckedChangeListener(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.item_choose_company_activity;//服务机构布局
    }

    @Override
    public void setBean(MemberFavoritePageAgencyBean.AgencyList bean, int position) {
        if (bean != null) {
//            MemberFavoritePageAgencyBean.AgencyList textList = bean.getData().getList().get(position);
            this.bean = bean;
            ac.setImage(iv_logo, bean.getLogo());
            String[] str = bean.getLabels().split(",");
//            int i = str.length;

            lable_layout.removeAllViews();
            for (int i = 0; i < str.length; i++) {
                addTextView(str[i]);
            }
           /* if (i > 2) {
                tv_label1.setText(str[0]);
                tv_label2.setText(str[1]);
                tv_label3.setText(str[2]);
            } else if (i > 1) {
                tv_label1.setText(str[0]);
                tv_label2.setText(str[1]);
                tv_label3.setVisibility(GONE);
            } else if (i > 0) {
                tv_label1.setText(str[0]);
                tv_label2.setVisibility(GONE);
                tv_label3.setVisibility(GONE);
            } else if (i == 0) {
                tv_label1.setVisibility(GONE);
                tv_label2.setVisibility(GONE);
                tv_label3.setVisibility(GONE);
            }*/
            tv_name.setText(bean.getName());
            tv_goodRate.setText(" " + bean.getGoodRate() + "%");
            tv_serviceCount.setText(" " + bean.getServiceCount());
            id = bean.getAgencyid();
            if (bean.isEdit() == true) {
                check_box_news.setVisibility(View.VISIBLE);
                if (bean.isCheck() == true) {
                    check_box_news.setChecked(bean.isCheck());

                } else if (bean.isCheck() == false) {

                    check_box_news.setChecked(false);
                }
            } else if (bean.isEdit() == false) {
                item.setEnabled(true);
                check_box_news.setVisibility(View.GONE);
            }
        }
    }



    /**
     * 把textview加入到流布局
     */
    private void addTextView(final String keyword) {
        final TextView tv = (TextView) LayoutInflater.from(_activity).inflate(R.layout.tv, lable_layout, false);
        /*android:textColor="#FF338ABD"
        android:background="#22338ABD"*/
        tv.setText(keyword);
        tv.setTextColor(getResources().getColor(R.color.lable_text_bg));
        tv.setBackgroundColor(getResources().getColor(R.color.lable_bg));
        lable_layout.addView(tv);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.item:
                if (bean.isEdit == true) {
                    if (isFrist) {
                        System.out.println("bean.isEdit==true");
                        check_box_news.setChecked(true);
                        isFrist = false;
                    } else if (!isFrist) {
                        check_box_news.setChecked(false);
                        isFrist = true;
                    }

                } else {
                    Bundle mBundle = new Bundle();
                    mBundle.putString("agencyid", id);
                    UIHelper.jump(_activity, CompanyDetialActivity.class, mBundle);
                }

                break;
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
        bean.setCheck(isChecked);
    }
}
