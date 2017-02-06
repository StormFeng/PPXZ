package com.midian.ppaddress.itemtpl;

import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.midian.ppaddress.R;
import com.midian.ppaddress.bean.BusinessAppsearchSopageBean.CarrierList;
import com.midian.ppaddress.ui.chooseaddres.ConsultantActivity;
import com.midian.ppaddress.ui.chooseaddres.MultiDetailActivity;
import com.midian.ppaddress.ui.chooseaddres.ParkDetailActivity;

import midian.baselib.utils.UIHelper;
import midian.baselib.view.BaseTpl;
import midian.baselib.widget.CircleImageView;

/**
 * Created by chu on 2016/5/23.
 */
public class IndexSearchTpl extends BaseTpl<CarrierList> implements View.OnClickListener {
    private TextView status_tv, title_tv, price_tv, addresss_tv, type_tv, name_tv, booking_tv;
    private CircleImageView head;
    private ImageView image_iv;
    private View has_enter_ll;
    private String type;
    private String carrierid, carrier_uuid, sex, enter_memberid;
    private CarrierList bean;

    public IndexSearchTpl(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public IndexSearchTpl(Context context) {
        super(context);
    }

    @Override
    protected void initView() {
        image_iv = (ImageView) findView(R.id.image_iv);
        has_enter_ll = findView(R.id.has_enter_ll);
        status_tv = (TextView) findView(R.id.status);//租售类型
        title_tv = (TextView) findView(R.id.title);
        price_tv = (TextView) findView(R.id.price);
        addresss_tv = (TextView) findView(R.id.address);
        type_tv = (TextView) findView(R.id.type);//载体类型
        name_tv = (TextView) findView(R.id.name);
        booking_tv = (TextView) findView(R.id.booking);
        head = (CircleImageView) findView(R.id.head);
        root.setOnClickListener(this);
        booking_tv.setOnClickListener(this);

    }

    @Override
    protected int getLayoutId() {
        return R.layout.item_index_search;
    }

    @Override
    public void setBean(CarrierList bean, int position) {
        this.bean = bean;
        carrierid = bean.getCarrierid();//载体id
        carrier_uuid = bean.getCarrieruuid();//载体uuid
        if (bean.getImages() == null) {
            ac.setImage(image_iv, R.drawable.default_bg);
        } else {
            ac.setImage(image_iv, bean.getImages());
        }
        title_tv.setText(bean.getShowName());
        String saleRental = bean.getSaleRental();//租售方式，0为租售，1为租，2为售
        if ("0".equals(saleRental)) {
            status_tv.setText("租售");
            price_tv.setText(bean.getPriceRent() + "元/m`*月 起");
        } else if ("1".equals(saleRental)) {
            status_tv.setText("租");
            price_tv.setText(bean.getPriceRent() + "元/m`*月 起");
        } else if ("2".equals(saleRental)) {
            status_tv.setText("售");
            price_tv.setText(bean.getPriceSell() + "元/m`*月 起");
        }
        String county = bean.getCounty();//区县名称
        if (county != null) {
            addresss_tv.setText(county);
        }
        String carrier_type = bean.getCarrierType();//类型，1为园区，2为综合体，3为土地，4为写字楼，6为厂房，8为仓库

        if ("1".equals(carrier_type)) {
            type_tv.setText("园区");
        } else if ("2".equals(carrier_type)) {
            type_tv.setText("综合体");
        } else if ("3".equals(carrier_type)) {
            type_tv.setText("土地");
        } else if ("4".equals(carrier_type)) {
            type_tv.setText("写字楼");
        } else if ("6".equals(carrier_type)) {
            type_tv.setText("厂房");
        } else if ("8".equals(carrier_type)) {
            type_tv.setText("仓库");
        }
        String hasEnter = bean.getHasEnter();//0是没顾问驻守，1是有顾问驻守
        if ("0".equals(hasEnter)) {
            has_enter_ll.setVisibility(View.GONE);
        } else if ("1".equals(hasEnter)) {
            has_enter_ll.setVisibility(View.VISIBLE);
            if (bean.getPortrait() == null) {
                ac.setImage(head, R.drawable.head1);
            } else {
                ac.setImage(head, bean.getPortrait());
            }
            if (bean.getSex().equals("0")) {//0是女，1是男
                sex = "女";
            } else {
                sex = "男";
            }
            name_tv.setText(bean.getFullname() + " | " + sex + " | 经纪人");
            enter_memberid = bean.getMemberid();
        }
    }

    @Override
    public void onClick(View v) {
        Bundle mBundle = new Bundle();
        switch (v.getId()) {
            case R.id.booking:
                mBundle.putString("carrierid", carrierid);//载体id
                UIHelper.jump(_activity, ConsultantActivity.class, mBundle);//物业顾问列表
                break;
            default:
                mBundle.putString("title", title_tv.getText().toString().trim());
                mBundle.putString("type", type);
                mBundle.putString("carriertitle", bean.getShowName());//标题
                mBundle.putString("carriertype", bean.getCarrierType());//载体类型
                mBundle.putString("carrierid", bean.getCarrierid());//载体id
                UIHelper.jump(_activity, ParkDetailActivity.class, mBundle);
                break;
        }
    }
}
