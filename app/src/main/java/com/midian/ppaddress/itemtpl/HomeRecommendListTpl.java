package com.midian.ppaddress.itemtpl;

import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.midian.maplib.ServerConstant;
import com.midian.ppaddress.R;
import com.midian.ppaddress.bean.AppIndexBean;
import com.midian.ppaddress.bean.IndexMultiItem;
import com.midian.ppaddress.ui.chooseaddres.MultiDetailActivity;
import com.midian.ppaddress.ui.chooseaddres.ParkDetailActivity;
import com.midian.ppaddress.ui.homepage.SearchConditionActivity;

import midian.baselib.bean.NetResult;
import midian.baselib.utils.UIHelper;
import midian.baselib.view.BaseTpl;
import midian.baselib.widget.CircleImageView;

/**
 * 首页推荐列表【暂时不用】
 * Created by chu on 2016/2/23.
 */
public class HomeRecommendListTpl extends BaseTpl<IndexMultiItem> implements View.OnClickListener {
    private ImageView img_iv;
    private TextView title_tv, price_tv;
    private CircleImageView head_cv;
    private TextView content_tv;
    private String carrierid,carriertitle,carriertype;//载体id
    private IndexMultiItem bean;

    public HomeRecommendListTpl(Context context) {
        super(context);
    }

    public HomeRecommendListTpl(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    @Override
    protected void initView() {
        img_iv = findView(R.id.img_iv);
        title_tv =  findView(R.id.title_tv);
        price_tv =  findView(R.id.price_tv);
        head_cv = findView(R.id.head_cv);
        content_tv =  findView(R.id.content_tv);

        root.setOnClickListener(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.item_home_recommend_list_tpl;
    }

    @Override
    public void setBean(final IndexMultiItem bean, int position) {
        if (bean.getItemViewType() == 1) {

            carrierid = bean.counselorses.getCarrierid();//载体id
            carriertitle = bean.counselorses.getCarrier().getCarrierName();
            carriertype = bean.counselorses.getCarrierType();
            title_tv.setText(bean.counselorses.getCarrier().getCarrierName());
            if (bean.counselorses.getCarrier().getImages() == null||bean.counselorses.getCarrier().getImages().equals("")) {
                ac.setImage(img_iv, R.drawable.default_bg);
            } else {
                ac.setImage(img_iv, bean.counselorses.getCarrier().getImages());
                System.out.println("推荐载体-载体图片url="+ bean.counselorses.getCarrier().getImages());
            }

            String saleRental = bean.counselorses.getCarrier().getSaleRental();//租售方式，0为租售，1为租，2为售
            if ("1".equals(saleRental)) {
                price_tv.setText(bean.counselorses.getCarrier().getPriceRent()+"元/m²·月 起");//出租价格
            } else if ("2".equals(saleRental)) {
                price_tv.setText(bean.counselorses.getCarrier().getPriceSell()+"元/m²·月 起");//出售价格
            } else if ("0".equals(saleRental)) {
                price_tv.setText(bean.counselorses.getCarrier().getPriceRent()+" / "+bean.counselorses.getCarrier().getPriceSell()+"元/m²·月 起");//租售（蛋疼的字段）
            }

            if (bean.counselorses.getPortrait() == null) {
                ac.setImage(head_cv, R.drawable.head1);
            } else {
                ac.setImage(head_cv, bean.counselorses.getPortrait());
            }
            content_tv.setText(bean.counselorses.getAdwords());

        }

    }

    @Override
    public void onClick(View view) {
        Bundle mBundle = new Bundle();
        mBundle.putString("carriertitle", carriertitle);//标题
        mBundle.putString("carriertype", carriertype);//载体类型
        mBundle.putString("carrierid", carrierid);//载体id
        UIHelper.jump(_activity, ParkDetailActivity.class,mBundle);
    }
}
