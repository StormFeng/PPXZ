package com.midian.ppaddress.itemtpl;

import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.midian.ppaddress.R;
import com.midian.ppaddress.bean.MemberCompareListTypeBean;
import com.midian.ppaddress.ui.personal.MyContrastChooseActivity;

import midian.baselib.bean.NetResult;
import midian.baselib.utils.UIHelper;
import midian.baselib.view.BaseTpl;

public class MyContrastTpl extends BaseTpl<MemberCompareListTypeBean> implements View.OnClickListener {

    private View item_ll;
    private TextView title_tv,count_tv;
    private String carrierType;


    public MyContrastTpl(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyContrastTpl(Context context) {
        super(context);
    }

    @Override
    protected void initView() {
        findView(R.id.item_ll).setOnClickListener(this);
        title_tv=findView(R.id.title_tv);
        count_tv=findView(R.id.count_tv);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.item_mycontrast_tpl;
    }

    @Override
    public void setBean(MemberCompareListTypeBean bean, int position) {
        if(bean.isOK()){
            title_tv.setText(bean.getData().get(position).getCarrierName());
            count_tv.setText(bean.getData().get(position).getCount());
            carrierType=bean.getData().get(position).getCarrierType();
        }
    }

    @Override
    public void onClick(View view) {
        Intent intent=new Intent();
        intent.setClass(_activity, MyContrastChooseActivity.class);//选择要对比的列表
        intent.putExtra("carrierType",carrierType);
        _activity.startActivity(intent);
//        UIHelper.jump(_activity, CarrierDetailContrastActivity.class);
    }
}
