package com.midian.ppaddress.itemtpl;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.midian.ppaddress.R;
import com.midian.ppaddress.bean.MemberCompareListBean;
import com.midian.ppaddress.ui.chooseaddres.CarrierContrastActivity;

import java.util.ArrayList;

import midian.baselib.bean.NetResult;
import midian.baselib.view.BaseTpl;

/**
 * Created by chu on 2016/4/25.
 */
public class CarrierTpl extends BaseTpl implements View.OnClickListener {
    private ImageView image_iv;
    private TextView title_tv;
    private View item_ll;
    private String id, carrierType;

    public CarrierTpl(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CarrierTpl(Context context) {
        super(context);
    }

    @Override
    protected void initView() {
        image_iv = (ImageView) findView(R.id.image_iv);
        title_tv = (TextView) findView(R.id.title_tv);
        item_ll = findView(R.id.item_ll);
//        item_ll.setOnClickListener(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.item_carrier_tpl;
    }


    @Override
    public void setBean(NetResult bean, int position) {
        if (bean instanceof MemberCompareListBean.CompareData) {
            MemberCompareListBean.CompareData itemBean = (MemberCompareListBean.CompareData) bean;
            if (itemBean.getCover() == null && itemBean.getCover().equals("")) {
                ac.setImage(image_iv, R.drawable.default_bg);
            } else {
                ac.setImage(image_iv, itemBean.getCover());
            }
            title_tv.setText(itemBean.getCarrierName());
            id = itemBean.getId();//对比id
            carrierType = itemBean.getCarrierType();

        }

    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent();
        intent.putExtra("id", id);
        ((CarrierContrastActivity) _activity).setResult(Activity.RESULT_OK, intent);
        Log.d("-=-=-=再次添加对比的id-=-=-=-", "onClick: onClick" + id);
        ((CarrierContrastActivity) _activity).finish();
    }
}
