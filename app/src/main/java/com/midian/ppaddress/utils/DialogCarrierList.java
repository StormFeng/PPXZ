package com.midian.ppaddress.utils;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.midian.ppaddress.R;
import com.midian.ppaddress.bean.BusinessAppsearchMapcarriersBean;
import com.midian.ppaddress.bean.BusinessAppsearchMapcarriersBean.Carrier;
import com.midian.ppaddress.bean.BusinessAppsearchMapcarriersBean.CarrierList;
import com.midian.ppaddress.ui.chooseaddres.MultiDetailActivity;
import com.midian.ppaddress.ui.chooseaddres.ParkDetailActivity;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

import java.util.ArrayList;
import java.util.List;

import midian.baselib.app.AppContext;
import midian.baselib.utils.FDDataUtils;
import midian.baselib.utils.UIHelper;
import midian.baselib.widget.FlowLayout;


public class DialogCarrierList extends Dialog implements View.OnClickListener, AdapterView.OnItemClickListener {


    private Context context;
    private ListView dialog_list;
    private TextView carrierName_tv, carrierArea_tv;
    private FlowLayout lable_layut;
    private DialogAdapter dialogAdapter;
    private BusinessAppsearchMapcarriersBean.MapListData listData;
    private List<CarrierList> carrierList = new ArrayList<>();
    private String carrierid, carrier_type, carriertitle;
    private TextView choose_tv;


    public DialogCarrierList(Context context) {
        super(context, R.style.dialog_list);
        init(context);
    }

    public DialogCarrierList(Context context, int themeResId) {
        super(context, R.style.dialog_list);
        init(context);
    }

    private void init(Context context) {

        this.context = context;
        Window w = this.getWindow();
        WindowManager.LayoutParams lp = w.getAttributes();
        lp.gravity = Gravity.BOTTOM;
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        w.setAttributes(lp);
        this.setCanceledOnTouchOutside(true);
        View contentView = View.inflate(context, R.layout.carrier_list_dialog, null);
        carrierName_tv = (TextView) contentView.findViewById(R.id.carrierName_tv);
        carrierArea_tv = (TextView) contentView.findViewById(R.id.carrierArea_tv);
        choose_tv = (TextView) contentView.findViewById(R.id.choose_tv);
        lable_layut = (FlowLayout) contentView.findViewById(R.id.lable_flow);


        dialog_list = (ListView) contentView.findViewById(R.id.dialog_list);
        dialogAdapter = new DialogAdapter();
        dialog_list.setAdapter(dialogAdapter);
        dialog_list.setOnItemClickListener(this);

        this.setContentView(contentView);
    }

    public void show(BusinessAppsearchMapcarriersBean.MapListData beanData) {
        this.listData = beanData;
        render();
        super.show();
    }

    private void render() {
        //载体类型，1为园区，2为综合体，3为土地，4为写字楼，5为写字楼单元，6为厂房，7为厂房单元，8为仓库，9为仓库单元
        Carrier carrier = listData.getCarrier();
        carrierList = listData.getList();
        dialogAdapter.notifyDataSetChanged();
        carrierName_tv.setText(carrier.getCarrierName());

        String carrierType = carrier.getCarrierType();
        if ("1".equals(carrierType)) {
            choose_tv.setText("园区可选物业");
            carrierArea_tv.setText("园区总面积 " + FDDataUtils.addComma(carrier.getBuildingArea()) + "m²");
        } else if ("2".equals(carrierType)) {
            choose_tv.setText("综合体可选物业");
            carrierArea_tv.setText("综合体总面积 " + FDDataUtils.addComma(carrier.getBuildingArea()) + "m²");
        }
        String[] str = carrier.getLabels().split(",");
        for (int i = 0; i < str.length; i++) {
            if (i < 3) {
                final TextView tv = (TextView) LayoutInflater.from(context).inflate(R.layout.tv, lable_layut, false);
                tv.setText(str[i]);
                tv.setTextColor(getContext().getResources().getColor(R.color.lable_text_bg));
                tv.setBackgroundColor(getContext().getResources().getColor(R.color.lable_bg));
                lable_layut.addView(tv);
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.wechat:
                break;
            case R.id.cancel:
                dismiss();
                break;
        }
        dismiss();
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Bundle mBundle = new Bundle();
        mBundle.putString("carriertitle", carriertitle);//标题
        mBundle.putString("carriertype", carrier_type);//载体类型
        mBundle.putString("carrierid", carrierid);//载体id
        UIHelper.jump((Activity) context, ParkDetailActivity.class, mBundle);
    }

    private class DialogAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            if (carrierList != null) {
                return carrierList.size();
            }
            return 0;
        }

        @Override
        public Object getItem(int position) {
            return carrierList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View view, ViewGroup viewGroup) {
            ViewHolder mHolder;
            if (view == null) {
                mHolder = new ViewHolder();
                LayoutInflater inflater = LayoutInflater.from(context);
                view = inflater.inflate(R.layout.item_carrier_list_dialog, null);
                mHolder.img_iv = (ImageView) view.findViewById(R.id.img_iv);
                mHolder.carrier_name_tv = (TextView) view.findViewById(R.id.carrier_name_tv);
                mHolder.type_tv = (TextView) view.findViewById(R.id.type_tv);
                mHolder.rent_tv = (TextView) view.findViewById(R.id.rent_tv);
                mHolder.price_tv = (TextView) view.findViewById(R.id.price_tv);
                mHolder.list_area_tv = (TextView) view.findViewById(R.id.list_area_tv);
                mHolder.lable_flow = (FlowLayout) view.findViewById(R.id.lable_flow);
                view.setTag(mHolder);
            }
            mHolder = (ViewHolder) view.getTag();
            //渲染数据
            CarrierList item = carrierList.get(position);
            if (TextUtils.isEmpty(item.getImages())) {
                mHolder.img_iv.setBackgroundResource(R.drawable.icon_bg1);
            } else {
//                mHolder.img_iv.
                AppContext.context().imageLoader.displayImage(item.getImages(), mHolder.img_iv, new ImageLoadingListener() {
                    @Override
                    public void onLoadingStarted(String s, View view) {

                    }

                    @Override
                    public void onLoadingFailed(String s, View view, FailReason failReason) {

                    }

                    @Override
                    public void onLoadingComplete(String s, View view, Bitmap bitmap) {
                        System.out.println("bitmap:::" + bitmap);
                        ImageView iv = (ImageView) view;
                        iv.setImageBitmap(bitmap);
                    }

                    @Override
                    public void onLoadingCancelled(String s, View view) {

                    }
                });

            }
            mHolder.carrier_name_tv.setText(item.getCarrierName());
            carriertitle = item.getCarrierName();
            carrier_type = item.getCarrierType();
            carrierid = item.getCarrierid();
            //载体类型，1为园区，2为综合体，3为土地，4为写字楼，5为写字楼单元，6为厂房，7为厂房单元，8为仓库，9为仓库单元
            String carrier_type = item.getCarrierType();
            if ("1".equals(carrier_type)) {
                mHolder.type_tv.setText("园区");
            } else if ("2".equals(carrier_type)) {
                mHolder.type_tv.setText("综合体");
            } else if ("3".equals(carrier_type)) {
                mHolder.type_tv.setText("土地");
            } else if ("4".equals(carrier_type)) {
                mHolder.type_tv.setText("写字楼");
            } else if ("6".equals(carrier_type)) {
                mHolder.type_tv.setText("厂房");
            } else if ("8".equals(carrier_type)) {
                mHolder.type_tv.setText("仓库");
            }

            if ("1".equals(carrier_type) || "2".equals(carrier_type) || "4".equals(carrier_type)) {//写字楼特有的
                mHolder.rent_tv.setVisibility(View.VISIBLE);
                String saleRental = item.getSaleRental();//0为租售，1为租，2为售
                if ("0".equals(saleRental)) {
                    mHolder.rent_tv.setText("租售");
                    mHolder.price_tv.setText(FDDataUtils.addComma(item.getPriceRent()) + "元/m²·月 起");//租价
                } else if ("1".equals(saleRental)) {
                    mHolder.rent_tv.setText("租");
                    mHolder.price_tv.setText(FDDataUtils.addComma(item.getPriceRent()) + "元/m²·月 起");
                } else if ("2".equals(saleRental)) {
                    mHolder.rent_tv.setText("售");
                    mHolder.price_tv.setText(FDDataUtils.addComma(item.getPriceSell()) + "元/m²");//售价
                }
                mHolder.list_area_tv.setText("待租售面积 " + FDDataUtils.addComma(item.getBuildingArea()) + "m²");
            } else {
                mHolder.rent_tv.setVisibility(View.GONE);
            }
            if ("6".equals(carrier_type) || "8".equals(carrier_type)) {//仓库、厂房共有字段
                mHolder.price_tv.setText(item.getFloor() + "层");
                mHolder.list_area_tv.setText("待租售面积 " + FDDataUtils.addComma(item.getBuildingArea()) + "m²");
            } else if ("3".equals(carrier_type)) {//土地特有的
                mHolder.price_tv.setText("土地面积：" + FDDataUtils.addComma(item.getLandArea()) + "m²");
                mHolder.list_area_tv.setText(item.getProperty());
            }

            mHolder.lable_flow.removeAllViews();
            String[] str = item.getLabels().split(",");
            mHolder.lable_flow.removeAllViews();
            for (int i = 0; i < str.length; i++) {
                if (i<3) {
                    final TextView tv = (TextView) LayoutInflater.from(context).inflate(R.layout.tv, mHolder.lable_flow, false);
                    tv.setText(str[i]);
                    mHolder.lable_flow.addView(tv);
                }
            }
            return view;
        }

        public class ViewHolder {
            private ImageView img_iv;
            private TextView carrier_name_tv;
            private TextView type_tv;
            private TextView rent_tv;
            private TextView price_tv;
            private TextView list_area_tv;
            private FlowLayout lable_flow;
        }
    }
}
