package com.midian.ppaddress.itemtpl;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.midian.login.view.LoginActivity;
import com.midian.ppaddress.R;
import com.midian.ppaddress.app.Constant;
import com.midian.ppaddress.bean.BusinessAgencyScreenPageActivityBean;
import com.midian.ppaddress.bean.IndexMultiItemBean;
import com.midian.ppaddress.ui.company.CompanyServicePage;

import java.util.ArrayList;

import midian.baselib.bean.NetResult;
import midian.baselib.utils.UIHelper;
import midian.baselib.view.BaseTpl;

/**
 * 服务的列表条目
 * Created by chu on 2016/2/16.
 */
public class ServicesContentTpl extends BaseTpl<IndexMultiItemBean> implements View.OnClickListener{
    private ImageView itemIcon;
    private TextView des,description;
    private String id;
    public ServicesContentTpl(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ServicesContentTpl(Context context) {
        super(context);
    }

    @Override
    protected void initView() {
        root.setOnClickListener(this);
        itemIcon= (ImageView) findViewById(R.id.iv_itemicon);
        des= (TextView) findViewById(R.id.tv_des);
        description= (TextView) findViewById(R.id.tv_description);
    }



    @Override
    protected int getLayoutId() {
        return R.layout.item_services_content_tpl;
    }

    @Override
    public void setBean(IndexMultiItemBean bean, int position) {
        if (bean.getItemViewType() == 1) {
           /* if(position>bean.botBean.size()){
            }else {
                id = bean.botBean.get(position - 1).getId();
                des.setText(bean.botBean.get(position - 1).getSlogan());
                description.setText(bean.botBean.get(position - 1).getSubslogan());
                if (TextUtils.isEmpty(bean.botBean.get(position - 1).getCoverImage())) {
                    itemIcon.setBackgroundResource(R.drawable.icon_home_bg);
                } else {
                    ac.setImage(itemIcon, bean.botBean.get(position - 1).getCoverImage());
                }
            }*/
            if (TextUtils.isEmpty(bean.botBeans.getCoverImage())) {
                itemIcon.setBackgroundResource(R.drawable.icon_home_bg);
            } else {
                ac.setImage(itemIcon, bean.botBeans.getCoverImage());
            }
            id=bean.botBeans.getId();
            des.setText(bean.botBeans.getSlogan());
            description.setText(bean.botBeans.getSubslogan());
        }
    }

    @Override
    public void onClick(View view) {
        if (!ac.isUserIdExsit()) {
            UIHelper.jump(_activity, LoginActivity.class);
            return;
        }
        Intent intent=new Intent();
        intent.setClass(_activity, CompanyServicePage.class);
        intent.putExtra("id",id);
        _activity.startActivity(intent);
//        UIHelper.jump(_activity, CompanyServicePage.class);
    }
}
