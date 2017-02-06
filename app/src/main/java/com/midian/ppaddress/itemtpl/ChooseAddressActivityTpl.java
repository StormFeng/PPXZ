package com.midian.ppaddress.itemtpl;

import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.midian.ppaddress.R;
import com.midian.ppaddress.bean.BusinessCarrierDetailGetChildrenCarriersBean.ChildrenCarriersList;
import com.midian.ppaddress.ui.chooseaddres.MultiDetailActivity;
import com.midian.ppaddress.ui.chooseaddres.ParkDetailActivity;

import midian.baselib.utils.FDDataUtils;
import midian.baselib.utils.UIHelper;
import midian.baselib.view.BaseTpl;
import midian.baselib.widget.FlowLayout;

/**
 * 载体详情-----进入选址
 * Created by chu on 2016/4/21.
 */
public class ChooseAddressActivityTpl extends BaseTpl<ChildrenCarriersList> implements View.OnClickListener {

    private ImageView img_iv;
    private TextView name_tv, type_tv, rent_tv, price_tv, area_tv;
    private FlowLayout lable_flow;
    private String carrierid, carrier_type, carriertitle;

    public ChooseAddressActivityTpl(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ChooseAddressActivityTpl(Context context) {
        super(context);
    }

    @Override
    protected void initView() {
        img_iv = findView(R.id.img_iv);
        name_tv = findView(R.id.name_tv);
        type_tv = findView(R.id.type_tv);
        rent_tv = findView(R.id.rent_tv);
        price_tv = findView(R.id.price_tv);
        area_tv = findView(R.id.area_tv);
        lable_flow = findView(R.id.lable_flow);
        root.setOnClickListener(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.item_choose_address_activity;
    }

    @Override
    public void setBean(ChildrenCarriersList bean, int position) {
        if (bean.getImages() == null && bean.getImages().equals("")) {
            ac.setImage(img_iv, R.drawable.default_bg);
        } else {
            ac.setImage(img_iv, bean.getImages());
        }
        carriertitle = bean.getCarrierName();
        name_tv.setText(bean.getCarrierName());
        carrier_type = bean.getCarrierType();
        carrierid = bean.getCarrierid();
        //载体类型，1为园区，2为综合体，3为土地，4为写字楼，5为写字楼单元，6为厂房，7为厂房单元，8为仓库，9为仓库单元
        if ("1".equals(carrier_type)) {
            type_tv.setText("园区");
        } else if ("2".equals(carrier_type)) {
            type_tv.setText("综合体");
        } else if ("3".equals(carrier_type)) {
            type_tv.setText("土地");
        } else if ("4".equals(carrier_type)) {
            type_tv.setText("写字楼");
        } else if ("5".equals(carrier_type)) {
            type_tv.setText("写字楼单元");
        } else if ("6".equals(carrier_type)) {
            type_tv.setText("厂房");
        } else if ("8".equals(carrier_type)) {
            type_tv.setText("仓库");
        }

        if ("1".equals(carrier_type) || "2".equals(carrier_type) || "4".equals(carrier_type)) {//写字楼特有的
            rent_tv.setVisibility(View.VISIBLE);
            String saleRental = bean.getSaleRental();//0为租售，1为租，2为售
            if ("1".equals(saleRental) || "0".equals(saleRental)) {
                rent_tv.setText("租");
                price_tv.setText(FDDataUtils.addComma(bean.getPriceRent()) + "元/m²·月 起");
            } else if ("2".equals(saleRental)) {
                rent_tv.setText("售");
                price_tv.setText(FDDataUtils.addComma(bean.getPriceSell()) + "元/m²");//售价
            }
            area_tv.setText("待租售面积" + FDDataUtils.addComma(bean.getBuildingArea()) + "m²");
        } else {
            rent_tv.setVisibility(View.GONE);
        }
        if ("6".equals(carrier_type) || "8".equals(carrier_type)) {//仓库、厂房共有字段
            area_tv.setText(bean.getFloor() + "层");
            price_tv.setText("待租售面积" + FDDataUtils.addComma(bean.getBuildingArea()) + "m²");
        } else if ("3".equals(carrier_type)) {//土地特有的
            area_tv.setText(bean.getProperty());
            price_tv.setText("土地面积" + FDDataUtils.addComma(bean.getLandArea()) + "m²");
        }

        lable_flow.removeAllViews();
        for (int i = 0; i < bean.getLabels().size(); i++) {
            if (i < 3) {
                String key = bean.getLabels().get(i).getLabel();
                addTextView(key);
            }
        }
    }

    /**
     * 把textview加入到流布局
     */
    private void addTextView(final String keyword) {
        final TextView tv = (TextView) LayoutInflater.from(_activity).inflate(R.layout.tv, lable_flow, false);
        tv.setText(keyword);
        lable_flow.addView(tv);
    }

    @Override
    public void onClick(View view) {
        Bundle mBundle = new Bundle();
        mBundle.putString("carriertitle", carriertitle);//标题
        mBundle.putString("carriertype", carrier_type);//载体类型
        mBundle.putString("carrierid", carrierid);//载体id
        UIHelper.jump(_activity, ParkDetailActivity.class, mBundle);
    }
}
