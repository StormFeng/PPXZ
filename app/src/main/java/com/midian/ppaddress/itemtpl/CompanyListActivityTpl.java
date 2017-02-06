package com.midian.ppaddress.itemtpl;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.midian.ppaddress.R;
import com.midian.ppaddress.bean.BusinessAgencyScreenPageAgencyBean;
import com.midian.ppaddress.ui.company.CompanyDetialActivity;
import midian.baselib.utils.UIHelper;
import midian.baselib.view.BaseTpl;
import midian.baselib.widget.FlowLayout;

public class CompanyListActivityTpl extends BaseTpl<BusinessAgencyScreenPageAgencyBean.AgencyList> implements View.OnClickListener{
    private ImageView iv_logo;
    private TextView tv_goodRate,tv_label1,tv_label2,tv_label3,tv_serviceCount,tv_name;
    private String agencyid;
    private FlowLayout lable_layout;//标签流布局
    public CompanyListActivityTpl(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    public CompanyListActivityTpl(Context context) {
        super(context);
    }

    @Override
    protected void initView() {
        iv_logo=findView(R.id.iv_icon);
        tv_goodRate=findView(R.id.tv_goodRate);
        tv_label1=findView(R.id.tv_label1);
        tv_label2=findView(R.id.tv_label2);
        tv_label3=findView(R.id.tv_label3);
        tv_serviceCount=findView(R.id.tv_serviceCount);
        tv_name=findView(R.id.tv_name);
        lable_layout = findView(R.id.lable_layout);
        root.setOnClickListener(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.item_choose_company_activity;
    }

    @Override
    public void setBean(BusinessAgencyScreenPageAgencyBean.AgencyList bean, int position) {
        if(bean == null){}
        else{
            if(TextUtils.isEmpty(bean.getLogo())){ac.setImage(iv_logo,R.drawable.default_button);
            }else{
                ac.setImage(iv_logo,bean.getLogo());
            }
            tv_name.setText(bean.getName());
            tv_goodRate.setText(bean.getGoodRate()+"%");
            tv_serviceCount.setText(bean.getServiceCount());
            String[] str=bean.getLabels().split(",");
            lable_layout.removeAllViews();
            for (int i = 0; i < str.length; i++) {
                addTextView(str[i]);
            }


           /* try {
                tv_label1.setText(str[0]);
                tv_label2.setText(str[1]);
                tv_label3.setText(str[2]);
            } catch (Exception e) {
                e.printStackTrace();
            }
            if("".equals(tv_label1.getText().toString().trim())){
                tv_label1.setVisibility(GONE);
            }
            if("".equals(tv_label2.getText().toString().trim())){
                tv_label2.setVisibility(GONE);
            }
            if("".equals(tv_label3.getText().toString().trim())){
                tv_label3.setVisibility(GONE);
            }*/
            agencyid=bean.getId();
        }
    }
    /**
     * 把textview加入到流布局
     */
    private void addTextView(final String keyword) {
        final TextView tv = (TextView) LayoutInflater.from(_activity).inflate(R.layout.tv, lable_layout, false);
        tv.setText(keyword);
        tv.setTextColor(getResources().getColor(R.color.lable_text_bg));
        tv.setBackgroundColor(getResources().getColor(R.color.lable_bg));
        lable_layout.addView(tv);
    }

    @Override
    public void onClick(View view) {
//        Intent intent=new Intent();
//        intent.putExtra("agencyid",id);
//        intent.setClass(_activity,CompanyDetial.class);
//        _activity.startActivity(intent);

        Bundle bundle=new Bundle();
        bundle.putString("agencyid",agencyid);
        UIHelper.jump(_activity,CompanyDetialActivity.class,bundle);
//        UIHelper.jump(_activity,CompanyDetialActivity1.class,bundle);
    }
}
