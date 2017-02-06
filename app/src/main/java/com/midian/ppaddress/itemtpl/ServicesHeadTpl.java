package com.midian.ppaddress.itemtpl;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.midian.login.view.LoginActivity;
import com.midian.ppaddress.R;
import com.midian.ppaddress.bean.BusinessAgencyScreenListIndustryBean;
import com.midian.ppaddress.bean.IndexMultiItemBean;
import com.midian.ppaddress.ui.company.ChooseWaiterActivity;
import com.midian.ppaddress.ui.company.CompanyListActivity;
import com.midian.ppaddress.utils.AppUtil;

import java.util.ArrayList;
import java.util.List;

import midian.baselib.bean.NetResult;
import midian.baselib.utils.UIHelper;
import midian.baselib.view.BaseTpl;

/**
 * 首页上部分
 * Created by chu on 2016/2/23.
 */
public class ServicesHeadTpl extends BaseTpl<IndexMultiItemBean> implements View.OnClickListener {
    private TextView property;
    private TextView photo;
    private TextView environmental;
    private TextView security;
    private TextView building;
    private TextView taxation;
    private TextView adviser;
    private TextView policy;
    private TextView human;
    private ImageView iv_property;
    private ImageView iv_photo;
    private ImageView iv_environmental;
    private ImageView iv_security;
    private ImageView iv_building;
    private ImageView iv_taxation;
    private ImageView iv_adviser;
    private ImageView iv_policy;
    private ImageView iv_human;

    private String id_human;

    private ArrayList<BusinessAgencyScreenListIndustryBean.Data> topBean;

    public ServicesHeadTpl(Context context) {
        super(context);
    }

    public ServicesHeadTpl(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    @Override
    protected void initView() {
        property=findView(R.id.property);
        policy=findView(R.id.policy);
        photo=findView(R.id.photo);
        environmental=findView(R.id.environmental);
        security=findView(R.id.security);
        building=findView(R.id.building);
        taxation=findView(R.id.taxation);
        adviser=findView(R.id.adviser);
        human=findView(R.id.human);


        iv_property=findView(R.id.iv_property);
        iv_policy=findView(R.id.iv_policy);
        iv_photo=findView(R.id.iv_photo);
        iv_environmental=findView(R.id.iv_environmental);
        iv_security=findView(R.id.iv_security);
        iv_building=findView(R.id.iv_building);
        iv_taxation=findView(R.id.iv_taxation);
        iv_adviser=findView(R.id.iv_adviser);
        iv_human=findView(R.id.iv_human);

        findView(R.id.ll_property).setOnClickListener(this);
        findView(R.id.ll_policy).setOnClickListener(this);
        findView(R.id.ll_photo).setOnClickListener(this);
        findView(R.id.ll_environmental).setOnClickListener(this);
        findView(R.id.ll_security).setOnClickListener(this);
        findView(R.id.ll_building).setOnClickListener(this);
        findView(R.id.ll_taxation).setOnClickListener(this);
        findView(R.id.ll_adviser).setOnClickListener(this);
        findView(R.id.ll_human).setOnClickListener(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.item_services_head;
    }

    @Override
    public void setBean(IndexMultiItemBean bean, int position) {
        if(bean.getItemViewType()==0){
            topBean=bean.topBean;
            property.setText(topBean.get(0).getIndustryName());
            policy.setText(topBean.get(1).getIndustryName());
            photo.setText(topBean.get(2).getIndustryName());
            environmental.setText(topBean.get(3).getIndustryName());
            security.setText(topBean.get(4).getIndustryName());
            building.setText(topBean.get(5).getIndustryName());
            taxation.setText(topBean.get(6).getIndustryName());
            adviser.setText(topBean.get(7).getIndustryName());
            human.setText(topBean.get(8).getIndustryName());
            ac.setImage(iv_property,topBean.get(0).getIcon());
            ac.setImage(iv_policy,topBean.get(1).getIcon());
            ac.setImage(iv_photo,topBean.get(2).getIcon());
            ac.setImage(iv_environmental,topBean.get(3).getIcon());
            ac.setImage(iv_security,topBean.get(4).getIcon());
            ac.setImage(iv_building,topBean.get(5).getIcon());
            ac.setImage(iv_taxation,topBean.get(6).getIcon());
            ac.setImage(iv_adviser,topBean.get(7).getIcon());
            ac.setImage(iv_human,topBean.get(8).getIcon());
            id_human=topBean.get(8).getId();
        }
    }
    @Override
    public void onClick(View v) {
        if (!ac.isUserIdExsit()) {
            UIHelper.jump(_activity, LoginActivity.class);
            return;
        }
        Bundle bundle=new Bundle();
        switch (v.getId()){
            case R.id.ll_property://知识产权
                bundle.putInt("industryid",1);
                bundle.putString("title","知识产权");
                UIHelper.jump(_activity, CompanyListActivity.class,bundle);
                break;
            case R.id.ll_policy://政策咨询
                bundle.putInt("industryid",2);
                bundle.putString("title","政策咨询");
                UIHelper.jump(_activity, CompanyListActivity.class,bundle);
                break;
            case R.id.ll_photo://证照代办
                bundle.putInt("industryid",3);
                bundle.putString("title","证照代办");
                UIHelper.jump(_activity, CompanyListActivity.class,bundle);
                break;
            case R.id.ll_environmental://环保评估
                bundle.putInt("industryid",4);
                bundle.putString("title","环保评估");
                UIHelper.jump(_activity, CompanyListActivity.class,bundle);
                break;
            case R.id.ll_security://安全评估
                bundle.putInt("industryid",5);
                bundle.putString("title","安全评估");
                UIHelper.jump(_activity, CompanyListActivity.class,bundle);
                break;
            case R.id.ll_building://建筑设计
                bundle.putInt("industryid",6);
                bundle.putString("title","建筑设计");
                UIHelper.jump(_activity, CompanyListActivity.class,bundle);
                break;
            case R.id.ll_taxation://财务税务
                bundle.putInt("industryid",7);
                bundle.putString("title","财务税务");
                UIHelper.jump(_activity, CompanyListActivity.class,bundle);
                break;
            case R.id.ll_adviser://法律顾问
                bundle.putInt("industryid",8);
                bundle.putString("title","法律顾问");
                UIHelper.jump(_activity, CompanyListActivity.class,bundle);
                break;
            case R.id.ll_human://人力资源
                bundle.putInt("industryid",9);
                bundle.putString("title","人力资源");
                UIHelper.jump(_activity, CompanyListActivity.class,bundle);
                break;
        }
    }
}
