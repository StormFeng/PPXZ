package com.midian.ppaddress.itemtpl;

import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.midian.ppaddress.R;
import com.midian.ppaddress.bean.BusinessAppsearchSopageBean.CarrierList;
import com.midian.ppaddress.ui.chooseaddres.ChooseAddressActivity;
import com.midian.ppaddress.ui.chooseaddres.ConsultantActivity;
import com.midian.ppaddress.ui.chooseaddres.MultiDetailActivity;
import com.midian.ppaddress.ui.chooseaddres.ParkDetailActivity;

import midian.baselib.utils.FDDataUtils;
import midian.baselib.utils.TDevice;
import midian.baselib.utils.UIHelper;
import midian.baselib.view.BaseTpl;
import midian.baselib.widget.CircleImageView;
import midian.baselib.widget.RoundCornerImageView;

/**
 * 选址类型-通用条目【旧页面，暂时不用，勿删除】
 * Created by chu on 2016/3/17.
 */
public class ChooseAddressTypeTpl extends BaseTpl<CarrierList> implements View.OnClickListener {
    private TextView status_tv, title_tv, price_tv, addresss_tv, type_tv, name_tv, booking_tv;
    private CircleImageView head;
    private ImageView image_iv;
    private View has_enter_ll;
    private String type;
    private String carrierid, carrier_uuid, sex, enter_memberid;
    private String carriertitle, carriertype;//载体标题、类型
    LinearLayout has_list;

    public ChooseAddressTypeTpl(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ChooseAddressTypeTpl(Context context) {
        super(context);
    }

    @Override
    protected void initView() {
        type = _activity.getIntent().getStringExtra("type");
        image_iv = findView(R.id.image_iv);
        has_enter_ll = findView(R.id.has_enter_ll);
        status_tv = findView(R.id.status);//租售类型
        title_tv = findView(R.id.title);
        price_tv = findView(R.id.price);
        addresss_tv = findView(R.id.address);
        type_tv = findView(R.id.type);//载体类型
        name_tv = findView(R.id.name);
        booking_tv = findView(R.id.booking);
        head = findView(R.id.head);
        has_list = findView(R.id.has_list);
        root.setOnClickListener(this);
        booking_tv.setOnClickListener(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.item_types_adress;
    }

    @Override
    public void setBean(CarrierList bean, int position) {
        carrierid = bean.getCarrierid();//载体id
        carriertype = bean.getCarrierType();
        carriertitle = bean.getShowName();
        carrier_uuid = bean.getCarrieruuid();//载体uuid
        if (bean.getImages() == null || bean.getImages().equals("")) {
            ac.setImage(image_iv, R.drawable.default_bg);
        } else {
            ac.setImage(image_iv, bean.getImages());
        }
        title_tv.setText(bean.getShowName());
        String saleRental = bean.getSaleRental();//租售方式，0为租售，1为租，2为售
        if ("0".equals(saleRental)) {
            status_tv.setText("租售");
            price_tv.setText(FDDataUtils.addComma(bean.getPriceRent()) + "元/m²·月 起");
        } else if ("1".equals(saleRental)) {
            status_tv.setText("租");
            price_tv.setText(FDDataUtils.addComma(bean.getPriceRent()) + "元/m²·月 起");
        } else if ("2".equals(saleRental)) {
            status_tv.setText("售");
            price_tv.setText(FDDataUtils.addComma(bean.getPriceSell()) + "元/m² 起");
        }
        String county = bean.getCounty();//区县名称
        if (county != null) {
            addresss_tv.setText(county);
        }
        final String carrier_type = bean.getCarrierType();//类型，1为园区，2为综合体，3为土地，4为写字楼，6为厂房，8为仓库

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
        has_list.removeAllViews();

        if ("1".equals(hasEnter)) {
            if (carrier_type.equals("1") || carrier_type.equals("2")) {
                has_list.setVisibility(View.VISIBLE);
                has_enter_ll.setVisibility(View.GONE);
                booking_tv.setText("进入选址");
                for (int i = 0; i < bean.getIntroImages().size(); i++) {
                    String img = bean.getIntroImages().get(i);
                    RoundCornerImageView image = (RoundCornerImageView) View.inflate(_activity, R.layout.view_carriers_bg, null);
                    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams((int) TDevice.dpToPixel(30f), (int) TDevice.dpToPixel(30f));
                    params.rightMargin = (int) TDevice.dpToPixel(10f);
                    if (bean.getIntroImages().get(i) == null || bean.getIntroImages().get(i).equals("")) {
                        ac.setImage(image, R.drawable.head1);
                    } else {
                        ac.setImage(image, bean.getIntroImages().get(i));
                    }
                    has_list.addView(image, params);
                }
            } else {
                has_list.setVisibility(View.GONE);
                has_enter_ll.setVisibility(View.VISIBLE);
                if (bean.getPortrait() == null || bean.getPortrait().equals("")) {
                    ac.setImage(head, R.drawable.head1);
                } else {
                    ac.setImage(head, bean.getPortrait());
                }
                if (bean.getSex().equals("0")) {//0是女，1是男
                    sex = "女";
                } else {
                    sex = "男";
                }
                name_tv.setText(bean.getFullname() + " | " + sex+" | 物业顾问");
                enter_memberid = bean.getMemberid();
            }
        }
    }

    @Override
    public void onClick(View v) {
        Bundle mBundle = new Bundle();
        switch (v.getId()) {
            case R.id.booking:
                mBundle.putString("carrierid", carrierid);//载体id
                UIHelper.jump(_activity, ConsultantActivity.class, mBundle);//物业顾问列表

                if (carriertype.equals("1") || carriertype.equals("2")) {
                    mBundle.putString("carrierid", carrierid);
                    mBundle.putString("title", carriertitle);
                    UIHelper.jump(_activity, ChooseAddressActivity.class, mBundle);//进入选址
                } else {
                    mBundle.putString("carrierid", carrierid);//载体id`
                    System.out.println("点击后的carrierid=" + carrierid);
                    UIHelper.jump(_activity, ConsultantActivity.class, mBundle);//物业顾问列表
                }
                break;
            default:
                System.out.println("条目的type:::" + type);
                mBundle.putString("carriertitle", carriertitle);//标题
                mBundle.putString("carriertype", carriertype);//载体类型
                mBundle.putString("carrierid", carrierid);//载体id
                UIHelper.jump(_activity, ParkDetailActivity.class, mBundle);
                break;
        }
    }

}
