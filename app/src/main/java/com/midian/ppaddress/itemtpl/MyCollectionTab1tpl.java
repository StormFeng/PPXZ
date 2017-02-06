package com.midian.ppaddress.itemtpl;

import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageView;
import android.widget.TextView;

import com.midian.ppaddress.R;
import com.midian.ppaddress.bean.MemberFavoritePageCarrierBean;
import com.midian.ppaddress.bean.MemberFavoritePageCarrierBean.CarrierCollectList;
import com.midian.ppaddress.ui.chooseaddres.MultiDetailActivity;
import com.midian.ppaddress.ui.chooseaddres.ParkDetailActivity;

import midian.baselib.utils.FDDataUtils;
import midian.baselib.utils.UIHelper;
import midian.baselib.view.BaseTpl;


public class MyCollectionTab1tpl extends BaseTpl<CarrierCollectList> implements View.OnClickListener{

    private TextView tv_name,tv_type,tv_saleRental,tv_price,tv_area;
    private ImageView iv_image;
    private String carriertype,carriertitle,carrierid;//跳转载体详情参数
    private CheckBox checkBox;
    private CarrierCollectList bean;
    private View item;
    private boolean isFrist = true;

    public MyCollectionTab1tpl(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyCollectionTab1tpl(Context context) {
        super(context);
    }

    @Override
    protected void initView() {
        tv_name=findView(R.id.tv_name);
        tv_type=findView(R.id.tv_type);
        tv_saleRental=findView(R.id.tv_salerental);
        tv_price=findView(R.id.tv_price);
        tv_area=findView(R.id.tv_area);
        iv_image=findView(R.id.iv_image);
        checkBox = (CheckBox) findViewById(R.id.check_box);
        item = findView(R.id.item);
        item.setOnClickListener(this);
        checkBox.setOnCheckedChangeListener(new OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                bean.setCheck(isChecked);

            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.item_collection_tpl1;
    }

    @Override
    public void setBean(CarrierCollectList bean, int position) {
        this.bean = bean;
        ac.setImage(iv_image,bean.getImages());
        tv_name.setText(bean.getCarrierName());
        if("1".equals(bean.getCarrierType())){
            tv_saleRental.setVisibility(VISIBLE);
            saleRental(bean);
            tv_type.setText("园区");
            tv_area.setText("园区总面积" + FDDataUtils.addComma(bean.getBuildingArea())+"m²");
        }
        if("2".equals(bean.getCarrierType())){
            tv_saleRental.setVisibility(VISIBLE);
            saleRental(bean);
            tv_type.setText("综合体");
            tv_area.setText("综合体总面积" + FDDataUtils.addComma(bean.getBuildingArea())+"m²");
        }
        if("3".equals(bean.getCarrierType())){
            tv_type.setText("土地");
            tv_saleRental.setVisibility(GONE);
            tv_area.setText(bean.getProperty());
            tv_price.setText("土地面积  "+ FDDataUtils.addComma(bean.getLandArea())+"m²");
        }
        if("4".equals(bean.getCarrierType())){
            tv_type.setText("写字楼");
            tv_saleRental.setVisibility(VISIBLE);
            saleRental(bean);
            tv_area.setText("待租售面积  "+FDDataUtils.addComma(bean.getBuildingArea())+"m²");
        }
        if("6".equals(bean.getCarrierType())){
            tv_saleRental.setVisibility(GONE);
            tv_type.setText("厂房");
            tv_price.setText("待租售面积  "+FDDataUtils.addComma(bean.getBuildingArea())+"m²");
            tv_area.setText(bean.getFloor()+"层");
        }
        if("8".equals(bean.getCarrierType())){
            tv_saleRental.setVisibility(GONE);
            tv_type.setText("仓库");
            tv_price.setText("待租售面积  "+FDDataUtils.addComma(bean.getBuildingArea())+"m²");
            tv_area.setText(bean.getFloor()+"层");
        }
        carriertitle=bean.getCarrierName();
        carriertype=bean.getCarrierType();
        carrierid=bean.getCarrierid();
        if (bean.isEdit() == true) {
            checkBox.setVisibility(View.VISIBLE);
            if (bean.isCheck() == true) {

                checkBox.setChecked(bean.isCheck());
            } else if (bean.isCheck() == false) {

                checkBox.setChecked(false);
            }
        } else if (bean.isEdit() == false) {
            item.setEnabled(true);
            checkBox.setVisibility(View.GONE);
        }
    }

    private void saleRental(CarrierCollectList bean) {
        if("0".equals(bean.getSaleRental())){
            tv_saleRental.setText("租");
            if("0".equals(bean.getPriceRent())){
                tv_price.setText("面议");
            }else{
                tv_price.setText(bean.getPriceRent()+"元/m²·月 起");
            }
        }
        if("1".equals(bean.getSaleRental())){
            tv_saleRental.setText("租");
            if("0".equals(bean.getPriceRent())){
                tv_price.setText("面议");
            }else{
                tv_price.setText(bean.getPriceRent()+"元/m²·月 起");
            }
        }
        if("2".equals(bean.getSaleRental())){
            tv_saleRental.setText("售");
            if("0".equals(bean.getPriceRent())){
                tv_price.setText("面议");
            }else{
                tv_price.setText(bean.getPriceSell()+"元/m²");
            }
        }
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.item:
                if (bean.isEdit == true) {
                    if (isFrist) {
                        System.out.println("bean.isEdit==true");
                        checkBox.setChecked(true);
                        isFrist = false;
                    } else if (!isFrist) {
                        checkBox.setChecked(false);
                        isFrist = true;
                    }
                } else {
                    Bundle bundle=new Bundle();
                    bundle.putString("carriertitle",carriertitle);
                    bundle.putString("carriertype",carriertype);
                    bundle.putString("carrierid",carrierid);
                    UIHelper.jump(_activity, ParkDetailActivity.class,bundle);
                }
                break;
        }
    }
}
