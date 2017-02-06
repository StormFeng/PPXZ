package com.midian.ppaddress.itemtpl;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.midian.ppaddress.R;
import com.midian.ppaddress.bean.CounselorPageCarrierBean;
import com.midian.ppaddress.bean.EntercarrierPageOwnerCarrierBean;
import com.midian.ppaddress.ui.chooseaddres.ConsultantActivity;
import com.midian.ppaddress.ui.chooseaddres.MultiDetailActivity;
import com.midian.ppaddress.ui.chooseaddres.ParkDetailActivity;
import com.midian.ppaddress.ui.personal.LeaseStateActivity;

import midian.baselib.bean.NetResult;
import midian.baselib.utils.FDDataUtils;
import midian.baselib.utils.UIHelper;
import midian.baselib.view.BaseTpl;

public class MyPropertyTpl extends BaseTpl<EntercarrierPageOwnerCarrierBean.Lists> implements View.OnClickListener{
    private ImageView iv_image;
    private TextView tv_name,tv_type,tv_area,tv_price,tv_saleRental,tv_id;
    private String title;
    private String carrierid,carriertype,carriertitle;
    public MyPropertyTpl(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyPropertyTpl(Context context) {
        super(context);
    }

    @Override
    protected void initView() {
        findView(R.id.ll_ID).setOnClickListener(this);
        findView(R.id.ll_item).setOnClickListener(this);
        findView(R.id.btn).setOnClickListener(this);
        tv_id=findView(R.id.tv_id);
        iv_image=findView(R.id.iv_image);
        tv_name=findView(R.id.tv_name);
        tv_type=findView(R.id.tv_type);
        tv_area=findView(R.id.tv_area);
        tv_price=findView(R.id.tv_price);
        tv_saleRental=findView(R.id.tv_salerental);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.item_myproperty;
    }

    @Override
    public void setBean(EntercarrierPageOwnerCarrierBean.Lists bean, int position) {
        if(bean!=null){
//            EntercarrierPageOwnerCarrierBean.Lists textList = bean.getData().getList().get(position);
            ac.setImage(iv_image,bean.getImages());
            title=bean.getCarrierCode();
            tv_id.setText("ID:  "+bean.getCarrierCode());
            tv_name.setText(bean.getCarrierName());
            if("1".equals(bean.getCarrierType())){
                tv_type.setText("园区");
                tv_saleRental.setVisibility(GONE);
//                saleRentalType(textList);
                tv_area.setText("园区总面积  " + FDDataUtils.addComma(bean.getBuildingArea()) + "m²");
            }
            if("2".equals(bean.getCarrierType())){
                tv_type.setText("综合体");
                tv_saleRental.setVisibility(GONE);
//                saleRentalType(textList);
                tv_area.setText("综合体总面积  " + FDDataUtils.addComma(bean.getBuildingArea()) + "m²");
            }

            if("3".equals(bean.getCarrierType())) {
                tv_type.setText("土地");
                tv_saleRental.setVisibility(GONE);
                tv_price.setText("土地面积  "+FDDataUtils.addComma(bean.getLandArea())+"m²");
                tv_area.setText(bean.getProperty());
            }
            if("4".equals(bean.getCarrierType())) {
                tv_type.setText("写字楼");
                tv_saleRental.setVisibility(VISIBLE);
                saleRentalType(bean);
                tv_area.setText("待租售面积  "+FDDataUtils.addComma(bean.getBuildingArea())+"m²");
            }
            if("6".equals(bean.getCarrierType())) {
                tv_type.setText("厂房");
                tv_saleRental.setVisibility(VISIBLE);
                tv_saleRental.setVisibility(GONE);
                tv_price.setText("待租售面积 "+FDDataUtils.addComma(bean.getBuildingArea())+"m²");
                tv_area.setText(bean.getFloor()+"层");
            }
            if("8".equals(bean.getCarrierType())) {
                tv_type.setText("仓库");
                tv_saleRental.setVisibility(VISIBLE);
                tv_saleRental.setVisibility(GONE);
                saleRentalType(bean);
                tv_price.setText("待租售面积 "+FDDataUtils.addComma(bean.getBuildingArea())+"m²");
                tv_area.setText(bean.getFloor()+"层");
            }
            carriertitle=bean.getCarrierName();
            carriertype=bean.getCarrierType();
            carrierid=bean.getCarrierid();

        }
    }

    private void saleRentalType(EntercarrierPageOwnerCarrierBean.Lists list) {
        if("0".equals(list.getSaleRental())){
            tv_saleRental.setText("租");
            if("0".equals(list.getPriceRent())){
                tv_price.setText("面议");
            }else{
                tv_price.setText(list.getPriceRent()+"元/m²·月 起");
            }
        }
        if("1".equals(list.getSaleRental())){
            tv_saleRental.setText("租");
            if("0".equals(list.getPriceRent())){
                tv_price.setText("面议");
            }else{
                tv_price.setText(list.getPriceRent()+"元/m²·月 起");
            }
        }
        if("2".equals(list.getSaleRental())){
            tv_saleRental.setText("售");
            if("0".equals(list.getPriceRent())){
                tv_price.setText("面议");
            }else{
                tv_price.setText(list.getPriceSell()+"元/m² 起");
            }
        }
    }


    @Override
    public void onClick(View view){
        Bundle bundle = new Bundle();
        switch (view.getId()){
            case R.id.ll_ID:
                Intent intent=new Intent(_activity,LeaseStateActivity.class);
                intent.putExtra("title",title);
                intent.putExtra("carrierid",carrierid);
                _activity.startActivity(intent);
                break;
            case R.id.ll_item:
                bundle.putString("carriertitle", carriertitle);
                bundle.putString("carriertype", carriertype);
                bundle.putString("carrierid", carrierid);
                UIHelper.jump(_activity, ParkDetailActivity.class, bundle);
                break;
            case R.id.btn:
                bundle.putString("carrierid", carrierid);
                UIHelper.jump(_activity, ConsultantActivity.class, bundle);
                break;
        }
    }
}
