package com.midian.ppaddress.itemtpl;

import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.midian.ppaddress.R;
import com.midian.ppaddress.bean.EntercarrierPageEnterBean;
import com.midian.ppaddress.ui.chooseaddres.MultiDetailActivity;
import com.midian.ppaddress.ui.chooseaddres.ParkDetailActivity;
import com.midian.ppaddress.ui.personal.UploadContractActivity;

import midian.baselib.bean.NetResult;
import midian.baselib.utils.FDDataUtils;
import midian.baselib.utils.UIHelper;
import midian.baselib.view.BaseTpl;
public class MyBuildingResourceTpl extends BaseTpl<EntercarrierPageEnterBean.Lists> implements View.OnClickListener{

    private TextView tv_id,tv_name,tv_type,tv_saleRental,tv_price,tv_area;
    private ImageView iv_image;
    private String carriertype,carriertitle,carrierid;

    public MyBuildingResourceTpl(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyBuildingResourceTpl(Context context) {
        super(context);
    }

    @Override
    protected void initView() {
        iv_image=findView(R.id.iv_image);
        tv_id=findView(R.id.tv_id);
        tv_price=findView(R.id.tv_price);
        tv_name=findView(R.id.tv_name);
        tv_type=findView(R.id.tv_type);
        tv_saleRental=findView(R.id.tv_salerental);
        tv_area=findView(R.id.tv_area);
        findView(R.id.ll_item).setOnClickListener(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.item_mybuildingresource ;
    }

    @Override
    public void setBean(EntercarrierPageEnterBean.Lists bean, int position) {
        tv_id.setText("ID: "+ bean.getCarrierCode());
        tv_name.setText(bean.getCarrierName());
        tv_name.setText(bean.getCarrierName());
        ac.setImage(iv_image,bean.getImages());
        if("1".equals(bean.getCarrierType())){
            tv_type.setText("园区");
            tv_saleRental.setVisibility(VISIBLE);
            CommenType1_2_4(bean);
        }
        if("2".equals(bean.getCarrierType())){
            tv_type.setText("综合体");
            tv_saleRental.setVisibility(VISIBLE);
            CommenType1_2_4(bean);
        }
        if("3".equals(bean.getCarrierType())){
            tv_type.setText("土地");
            tv_saleRental.setVisibility(GONE);
            tv_price.setText("土地面积  "+ FDDataUtils.addComma(bean.getLandArea())+"m²");
            tv_area.setText(bean.getProperty());
        }
        if("4".equals(bean.getCarrierType())){
            tv_type.setText("写字楼");
            tv_saleRental.setVisibility(VISIBLE);
            CommenType1_2_4(bean);
        }

        if("6".equals(bean.getCarrierType())){
            tv_type.setText("厂房");
            tv_saleRental.setVisibility(GONE);
            tv_area.setText(bean.getFloor()+"层");
            tv_price.setText("待租售面积  "+FDDataUtils.addComma(bean.getBuildingArea())+"m²");
        }
        if("8".equals(bean.getCarrierType())){
            tv_type.setText("仓库");
            tv_saleRental.setVisibility(GONE);
            tv_area.setText(bean.getFloor()+"层");
            tv_price.setText("待租售面积  "+FDDataUtils.addComma(bean.getBuildingArea())+"m²");
        }
        carriertitle=bean.getCarrierName();
        carriertype=bean.getCarrierType();
        carrierid=bean.getCarrierid();
    }

    //园区、综合体、写字楼共有字段
    private void CommenType1_2_4(EntercarrierPageEnterBean.Lists list) {
        tv_area.setText("待租售面积  "+FDDataUtils.addComma(list.getBuildingArea())+"m²");
        if("0".equals(list.getSaleRental())||"1".equals(list.getSaleRental())){
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
        switch (view.getId()) {
            case R.id.ll_item:
                Bundle bundle = new Bundle();
                bundle.putString("carriertitle", carriertitle);
                bundle.putString("carriertype", carriertype);
                bundle.putString("carrierid", carrierid);
                UIHelper.jump(_activity, ParkDetailActivity.class, bundle);
                break;
        }
    }
}
