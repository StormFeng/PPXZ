package com.midian.ppaddress.itemtpl;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.midian.ppaddress.R;
import com.midian.ppaddress.bean.BusinessCarrierDetailPageUnitsBean;
import com.midian.ppaddress.bean.BusinessCarrierDetailPageUnitsBean.UnitsData;
import com.midian.ppaddress.bean.BusinessCarrierDetailPageUnitsBean.UnitsList;

import midian.baselib.bean.NetResult;
import midian.baselib.utils.FDDataUtils;
import midian.baselib.view.BaseTpl;

/**
 * 可选单元条目模板
 * Created by chu on 2016/4/22.
 */
public class OptionalUnitTpl extends BaseTpl<NetResult> {
    private ImageView image_iv;
    private TextView height_tv, decorate_tv, floor_tv, area_tv, shang_tv, type_tv, price_tv;
    private String carrier_type;
    private View shang_ll;

    public OptionalUnitTpl(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public OptionalUnitTpl(Context context) {
        super(context);
    }

    @Override
    protected void initView() {
        image_iv = findView(R.id.image_iv);
        height_tv = findView(R.id.height_tv);
        decorate_tv = findView(R.id.decorate_tv);
        floor_tv = findView(R.id.floor_tv);
        area_tv = findView(R.id.area_tv);
        shang_tv = findView(R.id.shang_tv);
        type_tv = findView(R.id.type_tv);
        price_tv = findView(R.id.price_tv);
        shang_ll = findView(R.id.shang_ll);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.item_optional_unit_tpl;
    }

    @Override
    public void setBean(NetResult netResult, final int position) {
        if (netResult instanceof UnitsList) {
            UnitsList bean = (UnitsList) netResult;
            if (bean.getCoverimg() == null && bean.getCoverimg().equals("")) {
                ac.setImage(image_iv, R.drawable.default_bg);
            } else {
                ac.setImage(image_iv, bean.getCoverimg());
            }
            //载体类型，1为园区，2为综合体，3为土地，4为写字楼，5为写字楼单元，6为厂房，7为厂房单元，8为仓库，9为仓库单元
            //只有写字楼，仓库，厂房三个载体类型才有可选单元
            if (bean.getCarrierType().equals("7") || bean.getCarrierType().equals("9")) { //BeamHeight只有厂房和仓库单元才有含有该字段返
                height_tv.setText("层高：" + bean.getBeamHeight()+"米");
            }
            if (bean.getCarrierType().equals("5")) {//只有写字楼单元才有含有该字段返回
                height_tv.setText("装修：" + bean.getDecor());
            }
            floor_tv.setText("楼层：" + bean.getFloor());
            area_tv.setText(bean.getBuildingArea() + "m²");
            //账号类型，0为客商，1为客商中介，2为物业顾问，3为业主，4为服务达人
            if ("1".equals(ac.getProperty("user_type")) || "2".equals(ac.getProperty("user_type"))) {
                shang_ll.setVisibility(View.VISIBLE);//客商中介，物业顾问才展示该字段
                if (!TextUtils.isEmpty(bean.getReward())) {
                    shang_tv.setText(bean.getReward());
                }
            } else {
                shang_ll.setVisibility(View.GONE);
            }


            if ("0".equals(bean.getSaleRental())) {//0为租售，1为租，2为售
                type_tv.setText("租售");
                if (bean.getSaleRental().equals("0")) {
                    price_tv.setText("面议");
                } else {
                    price_tv.setText(FDDataUtils.addComma(bean.getPriceRent()) + "元/m²·月 起");
                }
            } else if ("1".equals(FDDataUtils.addComma(bean.getSaleRental()))) {
                type_tv.setText("租");
                if (bean.getSaleRental().equals("0")) {
                    price_tv.setText("面议");
                } else {
                    price_tv.setText(FDDataUtils.addComma(bean.getPriceRent()) + "元/m²·月 起");
                }
            }
            if ("2".equals(bean.getSaleRental())) {
                type_tv.setText("售");
                if (bean.getSaleRental().equals("0")) {
                    price_tv.setText("面议");
                } else {
                    price_tv.setText(FDDataUtils.addComma(bean.getPriceSell()) + "元/m² 起");
                }
            }


        }
    }
}
