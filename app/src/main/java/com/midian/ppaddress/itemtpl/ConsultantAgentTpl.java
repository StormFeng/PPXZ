package com.midian.ppaddress.itemtpl;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.midian.ppaddress.R;
import com.midian.ppaddress.bean.EntercarrierPageEnterBean;

import java.util.List;

import midian.baselib.bean.NetResult;
import midian.baselib.utils.UIHelper;
import midian.baselib.view.BaseTpl;
import midian.baselib.widget.FlowLayout;

/**
 * Created by chu on 2016/4/28.
 */
public class ConsultantAgentTpl extends BaseTpl<EntercarrierPageEnterBean>{
    private ImageView iv_image;
    private TextView tv_name,tv_type,tv_label1,tv_label2,tv_label3,tv_saleRental,tv_price,tv_area;
    public ConsultantAgentTpl(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    public ConsultantAgentTpl(Context context) {
        super(context);
    }

    @Override
    protected void initView() {
        iv_image=findView(R.id.image_iv);
        tv_name=findView(R.id.title_tv);
        tv_type=findView(R.id.type_tv);
        tv_label1=findView(R.id.tv_tabel1);
        tv_label2=findView(R.id.tv_tabel2);
        tv_label3=findView(R.id.tv_tabel3);
        tv_saleRental=findView(R.id.rent_tv);
        tv_price=findView(R.id.price_tv);
        tv_area=findView(R.id.area_tv);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.item_consultant_agent_tpl;
    }

    @Override
    public void setBean(EntercarrierPageEnterBean bean, int position) {
        if(bean.isOK()){
            EntercarrierPageEnterBean.Lists list = bean.getData().getList().get(position);
            tv_name.setText(list.getCarrierName());
            tv_name.setText(list.getCarrierName());
            ac.setImage(iv_image,list.getImages());
            if("1".equals(list.getCarrierType())){
                tv_type.setText("园区");
                CommenType1_2_4(list);
            }
            if("2".equals(list.getCarrierType())){
                tv_type.setText("综合体");
                CommenType1_2_4(list);
            }
            if("3".equals(list.getCarrierType())){
                tv_type.setText("土地");
                tv_saleRental.setVisibility(GONE);
                tv_price.setText("土地面积 "+list.getLandArea());
                tv_area.setText(list.getProperty());
            }
            if("4".equals(list.getCarrierType())){
                tv_type.setText("写字楼");
                CommenType1_2_4(list);
            }
            if("6".equals(list.getCarrierType())){
                tv_type.setText("厂房");
                tv_saleRental.setVisibility(GONE);
                tv_area.setText(list.getFloor()+"层");
                tv_price.setText("待出售面积 "+list.getBuildingArea());
            }
            if("8".equals(list.getCarrierType())){
                tv_type.setText("仓库");
                tv_saleRental.setVisibility(GONE);
                tv_area.setText(list.getFloor()+"层");
                tv_price.setText("待出售面积 "+list.getBuildingArea());
            }
            List<EntercarrierPageEnterBean.Labels> labels=list.getLabels();
            try {
                tv_label1.setText(labels.get(0).getLabel());
                tv_label2.setText(labels.get(1).getLabel());
                tv_label3.setText(labels.get(2).getLabel());
            } catch (Exception e) {
                e.printStackTrace();
            }
            if("".equals(tv_label1.getText().toString().trim()))
                tv_label1.setVisibility(GONE);
            if("".equals(tv_label2.getText().toString().trim()))
                tv_label1.setVisibility(GONE);
            if("".equals(tv_label3.getText().toString().trim()))
                tv_label1.setVisibility(GONE);
        }
    }

    //园区、综合体、写字楼共有字段
    private void CommenType1_2_4(EntercarrierPageEnterBean.Lists list) {
        tv_area.setText("待出售面积 "+list.getBuildingArea());
        if("0".equals(list.getSaleRental())){
            tv_saleRental.setText("租售");
            if("0".equals(list.getPriceRent())){
                tv_price.setText("面议");
            }else{
                tv_price.setText(list.getPriceRent()+"元／㎡／月");
            }
        }
        if("1".equals(list.getSaleRental())){
            tv_saleRental.setText("租");
            if("0".equals(list.getPriceRent())){
                tv_price.setText("面议");
            }else{
                tv_price.setText(list.getPriceRent()+"元／㎡／月");
            }
        }
        if("2".equals(list.getSaleRental())){
            tv_saleRental.setText("售");
            if("0".equals(list.getPriceRent())){
                tv_price.setText("面议");
            }else{
                tv_price.setText(list.getPriceSell()+"元／㎡／月");
            }
        }
    }
}
