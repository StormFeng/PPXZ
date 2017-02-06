package com.midian.ppaddress.ui.homepage;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.midian.ppaddress.R;
import com.midian.ppaddress.bean.AppIndexBean;
import com.midian.ppaddress.ui.chooseaddres.MultiDetailActivity;
import com.midian.ppaddress.ui.chooseaddres.ParkDetailActivity;

import midian.baselib.base.BaseFragment;
import midian.baselib.utils.FDDataUtils;
import midian.baselib.utils.TDevice;
import midian.baselib.utils.UIHelper;

/**
 * 首页推荐的载体
 * Created by chu on 2016/6/15.
 */
public class RecommendCarriersFragment extends BaseFragment implements View.OnClickListener {
    private AppIndexBean.RecommendCarriers recommendCarriers;
    private TextView title_tv, sale_tv, type_tv, price_tv, loc_tv, unit_tv;
    private LinearLayout liner_list;
    private LinearLayout h_item_ll;
    private String carriertitle,carriertype, carrierid;



    public AppIndexBean.RecommendCarriers getRecommendCarriers() {
        return recommendCarriers;
    }

    public void setRecommendCarriers(AppIndexBean.RecommendCarriers recommendCarriers) {
        this.recommendCarriers = recommendCarriers;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.item_view,null);
        initView(v);
        return v;
    }

    private void initView(View view) {
        title_tv = (TextView) view.findViewById(R.id.title_tv);
        sale_tv = (TextView) view.findViewById(R.id.sale_tv);
        type_tv = (TextView) view.findViewById(R.id.type_tv);
        price_tv = (TextView) view.findViewById(R.id.price_tv);
        unit_tv = (TextView) view.findViewById(R.id.unit_tv);
        loc_tv = (TextView) view.findViewById(R.id.loc_tv);
        liner_list = (LinearLayout) view.findViewById(R.id.liner_list);
        h_item_ll = (LinearLayout) view.findViewById(R.id.h_item_ll);
        h_item_ll.setOnClickListener(this);

        if (recommendCarriers != null) {
            carriertitle = recommendCarriers.getCarrierName();
            carriertype = recommendCarriers.getCarrierType();
            carrierid = recommendCarriers.getCarrierid();
            title_tv.setText(recommendCarriers.getCarrierName());
            if ("1".equals(recommendCarriers.getSaleRental())) {//租售方式，0为租售，1为租，2为售
                sale_tv.setText("出租");
                price_tv.setText(FDDataUtils.addComma(recommendCarriers.getPriceRent()));//出租单价
                unit_tv.setText("元/m²·月 起");
            } else if ("2".equals(recommendCarriers.getSaleRental())) {
                sale_tv.setText("出售");
                price_tv.setText(FDDataUtils.addComma(recommendCarriers.getPriceSell()));
                unit_tv.setText("元/m² 起");
            } else if ("0".equals(recommendCarriers.getSaleRental())) {
                sale_tv.setText("租售");
                price_tv.setText(FDDataUtils.addComma(recommendCarriers.getPriceRent()));
            }
            //载体类型 ，[1]：园区，[2]：综合体，[3]：土地，[4]：写字楼，[6]：厂房，[8]：仓库
            String carrierType = recommendCarriers.getCarrierType();
            if ("1".equals(carrierType)) {
                type_tv.setText("园区");
                loc_tv.setText(recommendCarriers.getCity() + "   " + recommendCarriers.getCounty() + "     园区总面积 " + FDDataUtils.addComma(recommendCarriers.getBuildingArea()) + "m²");//buildingArea该字段用于园区、综合体、厂房、仓库、写字楼显示
            } else if ("2".equals(carrierType)) {
                type_tv.setText("综合体");
                loc_tv.setText(recommendCarriers.getCity() + "   " + recommendCarriers.getCounty() + "     综合体总面积 " + FDDataUtils.addComma(recommendCarriers.getBuildingArea()) + "m²");
            } else if ("3".equals(carrierType)) {
                type_tv.setText("土地");
                price_tv.setText(FDDataUtils.addComma(recommendCarriers.getPriceSell()));
                unit_tv.setText("元/m² 起");
                loc_tv.setText(recommendCarriers.getCity() + "   " + recommendCarriers.getCounty() + "     土地面积 " + FDDataUtils.addComma(recommendCarriers.getLandArea()) + "m²");
            } else if ("4".equals(carrierType)) {
                type_tv.setText("写字楼");
                loc_tv.setText(recommendCarriers.getCity() + "   " + recommendCarriers.getCounty() + "     待租售面积 " + FDDataUtils.addComma(recommendCarriers.getBuildingArea()) + "m²");
            } else if ("6".equals(carrierType)) {
                type_tv.setText("厂房");
                loc_tv.setText(recommendCarriers.getCity() + "   " + recommendCarriers.getCounty() + "     待租售面积 " + FDDataUtils.addComma(recommendCarriers.getBuildingArea()) + "m²");
            } else if ("8".equals(carrierType)) {
                type_tv.setText("仓库");
                loc_tv.setText(recommendCarriers.getCity() + "   " + recommendCarriers.getCounty() + "     待租售面积 " + FDDataUtils.addComma(recommendCarriers.getBuildingArea()) + "m²");
            }

            if (recommendCarriers.getImages() != null) {
                liner_list.removeAllViews();
                for (int j = 0; j < 4; j++) {
                    ImageView image = (ImageView) View.inflate(_activity, R.layout.card_view_item, null);
                    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams((int) TDevice.dpToPixel(60f), (int) TDevice.dpToPixel(60f));
                    params.rightMargin = (int) TDevice.dpToPixel(15f);
                    LinearLayout tl = new LinearLayout(_activity);
                    tl.setOrientation(LinearLayout.HORIZONTAL);
                    tl.setGravity(Gravity.CENTER_HORIZONTAL);
                    tl.addView(image, params);
                    ac.setImage(image, recommendCarriers.getImages().get(j).getConver());
                    liner_list.addView(tl);
                }
            }
        }
    }


    @Override
    public void onClick(View view) {
        Bundle mBundle = new Bundle();
        mBundle.putString("carriertitle", carriertitle);
        mBundle.putString("carriertype", carriertype);
        mBundle.putString("carrierid", carrierid);//载体id
//        UIHelper.jump(_activity, MultiDetailActivity.class, mBundle);//载体详情
        UIHelper.jump(_activity, ParkDetailActivity.class, mBundle);//载体详情
    }
}
