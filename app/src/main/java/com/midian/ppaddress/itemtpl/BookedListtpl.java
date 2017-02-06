package com.midian.ppaddress.itemtpl;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.midian.ppaddress.R;
import com.midian.ppaddress.bean.EntercarrierPageEnterBean;
import com.midian.ppaddress.bean.MemberOrderMemberPageSignOrderBean;
import com.midian.ppaddress.ui.chooseaddres.MultiDetailActivity;
import com.midian.ppaddress.ui.chooseaddres.ParkDetailActivity;

import midian.baselib.utils.FDDataUtils;
import midian.baselib.utils.UIHelper;
import midian.baselib.view.BaseTpl;

public class BookedListtpl extends BaseTpl<MemberOrderMemberPageSignOrderBean.Lists> implements View.OnClickListener{

    private TextView tv_createTime,tv_stage,tv_name,tv_type,tv_price,tv_area,tv_fullname,tv_phone,tv_saleRental;
    private ImageView iv_image,iv_portrait;
    private String carriertype,carriertitle,carrierid;

    public BookedListtpl(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public BookedListtpl(Context context) {
        super(context);
    }

    @Override
    protected void initView() {
        tv_createTime=findView(R.id.tv_createtime);
        tv_stage=findView(R.id.tv_state);
        tv_name=findView(R.id.tv_name);
        tv_type=findView(R.id.tv_type);
        tv_price=findView(R.id.tv_price);
        tv_area=findView(R.id.tv_area);
        tv_fullname=findView(R.id.tv_fullname);
        tv_phone=findView(R.id.tv_phone);
        iv_image=findView(R.id.iv_image);
        iv_portrait=findView(R.id.iv_portrait);
        tv_saleRental=findView(R.id.tv_salerental);
        findView(R.id.tv_phone).setOnClickListener(this);
        findView(R.id.ll_item).setOnClickListener(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_booked_list;
    }

    @Override
    public void setBean(MemberOrderMemberPageSignOrderBean.Lists bean, int position) {
        if(bean!=null){
            tv_createTime.setText(bean.getCreateTime());
//
            if("11".equals(bean.getStage())) {
                tv_stage.setText("已支付");
                tv_stage.setTextColor(getResources().getColor(R.color.blue));
            }
            else{
                tv_stage.setText("未支付");
            }

            tv_name.setText(bean.getCarrierName());
            if("1".equals(bean.getCarrierType())){
                tv_type.setText("园区");
                tv_saleRental.setVisibility(GONE);
            }
            if("2".equals(bean.getCarrierType())){
                tv_type.setText("综合体");
                tv_saleRental.setVisibility(GONE);
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
            tv_fullname.setText(bean.getFullname());
            tv_phone.setText(bean.getMobilephone());
            ac.setImage(iv_image,bean.getImages());
            ac.setImage(iv_portrait,bean.getPortrait());
        }
    }
    //写字楼共有字段
    private void CommenType1_2_4(MemberOrderMemberPageSignOrderBean.Lists list) {
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
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_item:
                Bundle bundle = new Bundle();
                bundle.putString("carriertitle", carriertitle);
                bundle.putString("carriertype", carriertype);
                bundle.putString("carrierid", carrierid);
                UIHelper.jump(_activity, ParkDetailActivity.class, bundle);
                break;
            case R.id.tv_phone:
                String phoneNumber = tv_phone.getText().toString().trim();
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phoneNumber));
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                _activity.startActivity(intent);
                break;
        }
    }
}
