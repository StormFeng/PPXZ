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
import com.midian.ppaddress.ui.main.ChooseAddressFragment;

import midian.baselib.utils.TDevice;
import midian.baselib.utils.UIHelper;
import midian.baselib.view.BaseTpl;
import midian.baselib.widget.CircleImageView;
import midian.baselib.widget.RoundCornerImageView;

/**
 * 选址 Fragment tpl
 * Created by chu on 2016/2/16.
 */
public class ChooseAddressTpl extends BaseTpl<CarrierList> implements View.OnClickListener {

    private TextView status_tv, title_tv, price_tv, addresss_tv, type_tv, name_tv, booking_tv;
    private CircleImageView head;
    private ImageView image_iv;
    private View has_enter_ll;
    private String type;
    private String carrierid, carrier_uuid, sex, enter_memberid;
    private String carriertitle, carriertype;//载体标题、类型
    private LinearLayout has_list;
    private View has_enter;


    public ChooseAddressTpl(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ChooseAddressTpl(Context context) {
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
        has_list = (LinearLayout) findView(R.id.has_list);
        has_enter = findView(R.id.has_enter);
        root.setOnClickListener(this);
        booking_tv.setOnClickListener(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.item_choose_address_tpl;
    }

    @Override
    public void setBean(CarrierList bean, int position) {
        carrierid = bean.getCarrierid();//载体id
        carriertitle = bean.getShowName();

        carrier_uuid = bean.getCarrieruuid();//载体uuid
        if (bean.getImages() == null || bean.getImages().equals("")) {
            ac.setImage(image_iv, R.drawable.default_bg);
        } else {
            ac.setImage(image_iv, bean.getImages());
        }
        title_tv.setText(bean.getShowName());
        String saleRental = bean.getSaleRental();//租售方式，0为租售，1为租，2为售
        if ("0".equals(saleRental) || "1".equals(saleRental)) {
            status_tv.setText("租");
            price_tv.setText(bean.getPriceRent() + "元/m²·月 起");
        } else if ("2".equals(saleRental)) {
            status_tv.setText("售");
            price_tv.setText(bean.getPriceSell() + "元/m²·月 起");
        }
        String county = bean.getCounty();//区县名称
        if (county != null) {
            addresss_tv.setText(county);
        }
        carriertype = bean.getCarrierType();
        bean.getCarrierType();//类型，1为园区，2为综合体，3为土地，4为写字楼，6为厂房，8为仓库

        if ("1".equals(carriertype)) {
            type_tv.setText("园区");
        } else if ("2".equals(carriertype)) {
            type_tv.setText("综合体");
        } else if ("3".equals(carriertype)) {
            type_tv.setText("土地");
        } else if ("4".equals(carriertype)) {
            type_tv.setText("写字楼");
        } else if ("6".equals(carriertype)) {
            type_tv.setText("厂房");
        } else if ("8".equals(carriertype)) {
            type_tv.setText("仓库");
        }

        String hasEnter = bean.getHasEnter();//0是没顾问驻守，1是有顾问驻守
        has_list.removeAllViews();

        if ("1".equals(hasEnter)) {
            has_enter.setVisibility(VISIBLE);
            if (carriertype.equals("1") || carriertype.equals("2")) {
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
                name_tv.setText(bean.getFullname() + " | " + sex + " | 物业顾问");
                enter_memberid = bean.getMemberid();
            }
        } else {
            has_enter.setVisibility(GONE);
        }
    }

    @Override
    public void onClick(View v) {
        Bundle mBundle = new Bundle();
        if(ChooseAddressFragment.dimedView.isShowing()){
            ChooseAddressFragment.dimedView.close();
        }else{
            switch (v.getId()) {
                case R.id.booking:
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
                    mBundle.putString("carriertitle", carriertitle);//标题
                    mBundle.putString("carriertype", carriertype);//载体类型
                    mBundle.putString("carrierid", carrierid);//载体id
                    UIHelper.jump(_activity, ParkDetailActivity.class, mBundle);
                    break;
            }
        }
    }
}
