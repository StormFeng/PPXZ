package com.midian.ppaddress.itemtpl;

import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.midian.ppaddress.R;
import com.midian.ppaddress.bean.CounselorPageCarrierBean;
import com.midian.ppaddress.ui.chooseaddres.MultiDetailActivity;
import com.midian.ppaddress.ui.chooseaddres.ParkDetailActivity;

import midian.baselib.bean.NetResult;
import midian.baselib.utils.FDDataUtils;
import midian.baselib.utils.UIHelper;
import midian.baselib.view.BaseTpl;

/**
 * Created by chu on 2016/5/5.
 */
public class ToBodyCommentTpl extends BaseTpl<CounselorPageCarrierBean.Lists> implements View.OnClickListener {

    private ImageView iv_image;
    private TextView tv_title,tv_type,tv_area,tv_price,tv_content,tv_saleRental;
    private String carriertype,carriertitle,carrierid;

    public ToBodyCommentTpl(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ToBodyCommentTpl(Context context) {
        super(context);
    }

    @Override
    protected void initView() {
        iv_image=findView(R.id.iv_image);
        tv_title=findView(R.id.tv_title);
        tv_type=findView(R.id.tv_type);
        tv_area=findView(R.id.tv_area);
        tv_price=findView(R.id.tv_price);
        tv_content=findView(R.id.tv_content);
        tv_saleRental=findView(R.id.tv_salerental);
        findView(R.id.ll_item).setOnClickListener(this);
    }
    @Override
    protected int getLayoutId() {
        return R.layout.item_to_body_comment_tpl;
    }

    @Override
    public void setBean(CounselorPageCarrierBean.Lists bean, int position) {
        if(bean!=null) {
            ac.setImage(iv_image,bean.getImages());
            tv_title.setText(bean.getCarrierName());
            tv_content.setText("\u3000\u3000"+bean.getContent());
            if("1".equals(bean.getCarrierType())){
                tv_type.setText("园区");
                saleRentalType(bean);
                tv_area.setText("园区总面积" + FDDataUtils.addComma(bean.getBuildingArea()) + " m²");
            }
            if("2".equals(bean.getCarrierType())) {
                tv_type.setText("综合体");
                saleRentalType(bean);
                tv_area.setText("综合体总面积" + FDDataUtils.addComma(bean.getBuildingArea()) + " m²");
            }
            if("3".equals(bean.getCarrierType())) {
                tv_type.setText("土地");
                tv_saleRental.setVisibility(GONE);
                tv_price.setText("土地面积"+FDDataUtils.addComma(bean.getLandArea())+" m²");
                tv_area.setText(bean.getProperty());
            }
            if("4".equals(bean.getCarrierType())) {
                tv_type.setText("写字楼");
                saleRentalType(bean);
                tv_area.setText("待租售面积"+FDDataUtils.addComma(bean.getBuildingArea())+" m²");
            }
            if("6".equals(bean.getCarrierType())) {
                tv_type.setText("厂房");
                tv_saleRental.setVisibility(GONE);
                tv_price.setText("待租售面积"+FDDataUtils.addComma(bean.getBuildingArea())+" m²");
                tv_area.setText(bean.getFloor()+"层");
            }
            if("8".equals(bean.getCarrierType())) {
                tv_type.setText("仓库");
                tv_saleRental.setVisibility(GONE);
                tv_price.setText("待租售面积"+FDDataUtils.addComma(bean.getBuildingArea())+" m²");
                tv_area.setText(bean.getFloor()+"层");
            }
            carriertitle=bean.getCarrierName();
            carriertype=bean.getCarrierType();
            carrierid=bean.getCarrierid();
        }
    }

    private void saleRentalType(CounselorPageCarrierBean.Lists list) {
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
                tv_price.setText(list.getPriceSell()+"元/ m² 起");
            }
        }
    }

    @Override
    public void onClick(View view) {
        Bundle bundle=new Bundle();
        bundle.putString("carriertitle",carriertitle);
        bundle.putString("carriertype",carriertype);
        bundle.putString("carrierid",carrierid);
        UIHelper.jump(_activity, ParkDetailActivity.class,bundle);
    }
}
