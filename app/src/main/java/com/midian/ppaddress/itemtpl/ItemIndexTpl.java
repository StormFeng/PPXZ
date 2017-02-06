package com.midian.ppaddress.itemtpl;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.midian.ppaddress.R;
import com.midian.ppaddress.bean.AppIndexBean.RecommendCarriers;

import midian.baselib.bean.NetResult;
import midian.baselib.utils.TDevice;
import midian.baselib.view.BaseTpl;

/**
 * Created by chu on 2016/5/11.
 */
public class ItemIndexTpl extends BaseTpl<RecommendCarriers> {

    private TextView title_tv, sale_tv, type_tv, price_tv, loc_tv;
    private LinearLayout liner_list;


    public ItemIndexTpl(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ItemIndexTpl(Context context) {
        super(context);
    }

    @Override
    protected void initView() {
        title_tv = (TextView) findView(R.id.title_tv);
        sale_tv = (TextView) findView(R.id.sale_tv);
        type_tv = (TextView) findView(R.id.type_tv);
        price_tv = (TextView) findView(R.id.price_tv);
        loc_tv = (TextView) findView(R.id.loc_tv);
        liner_list = (LinearLayout) findView(R.id.liner_list);

    }

    @Override
    protected int getLayoutId() {
        return R.layout.item_view;
    }

    @Override
    public void setBean(RecommendCarriers bean, int position) {
        title_tv.setText(bean.getCarrierName());
        if ("1".equals(bean.getSaleRental())) {//租售方式，0为租售，1为租，2为售
            sale_tv.setText("出租");
            price_tv.setText(bean.getPriceRent());//出租单价
        } else if ("2".equals(bean.getSaleRental())) {
            sale_tv.setText("出售");
            price_tv.setText(bean.getPriceSell());//出售单价
        } else if ("0".equals(bean.getSaleRental())) {
            sale_tv.setText("租售");
            price_tv.setText(bean.getPriceSell());//出售单价
        }
        //载体类型 ，[1]：园区，[2]：综合体，[3]：土地，[4]：写字楼，[6]：厂房，[8]：仓库
        if ("1".equals(bean.getCarrierType())) {
            type_tv.setText("园区");
        } else if ("2".equals(bean.getCarrierType())) {
            type_tv.setText("综合体");
        } else if ("3".equals(bean.getCarrierType())) {
            type_tv.setText("土地");
        } else if ("4".equals(bean.getCarrierType())) {
            type_tv.setText("写字楼");
        } else if ("6".equals(bean.getCarrierType())) {
            type_tv.setText("厂房");
        } else if ("8".equals(bean.getCarrierType())) {
            type_tv.setText("仓库");
        }
        price_tv.setText(bean.getPriceSell());//出售单价
        loc_tv.setText(bean.getCity() + "   " + bean.getCounty() + "    园区总面积 " + bean.getLandArea() + "m²");
        for (int i=0;i<bean.getImages().size();i++) {
            ImageView image = (ImageView) View.inflate(_activity, com.midian.ppaddress.R.layout.card_view_item, null);
//            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams((int) TDevice.dpToPixel(60f), (int) TDevice.dpToPixel(60f));
//            params.rightMargin = (int) TDevice.dpToPixel(10f);
            ac.setImage(image, bean.getImages().get(i).getConver());
            liner_list.addView(image,params);
        }
    }
}
