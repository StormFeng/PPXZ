package com.midian.ppaddress.ui.chooseaddres;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.midian.ppaddress.R;
import com.midian.ppaddress.bean.EntercarrierShowBean;
import com.midian.ppaddress.utils.AppUtil;

import midian.baselib.base.BaseActivity;
import midian.baselib.bean.NetResult;
import midian.baselib.utils.FDDataUtils;
import midian.baselib.utils.UIHelper;
import midian.baselib.widget.BaseLibTopbarView;

/**
 * 申请入驻
 * Created by chu on 2016/4/27.
 */
public class ApplyEnterActivity extends BaseActivity {
    private BaseLibTopbarView topbar;
    private ImageView image_iv;
    private TextView carrierName_tv, type_tv, rent_tv, price_tv, area_tv, name_tv, phone_tv;
    private EditText edit_et;
    private TextView confirm_tv;
    private String carrierid, counselorid;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apply_enter);
        carrierid = mBundle.getString("carrierid");
        topbar = findView(R.id.topbar);
        topbar.setTitle("申请入驻").setLeftImageButton(R.drawable.icon_back, UIHelper.finish(_activity));
        image_iv = findView(R.id.image_iv);
        carrierName_tv = findView(R.id.carrierName_tv);
        type_tv = findView(R.id.type_tv);
        rent_tv = findView(R.id.rent_tv);
        price_tv = findView(R.id.price_tv);
        area_tv = findView(R.id.area_tv);
        name_tv = findView(R.id.name_tv);
        phone_tv = findView(R.id.phone_tv);
        edit_et = findView(R.id.edit_et);
        confirm_tv = findView(R.id.confirm_tv);
        confirm_tv.setOnClickListener(this);

        loadData();
    }

    private void loadData() {
        AppUtil.getPpApiClient(ac).memberEntercarrierShow(carrierid, this);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.confirm_tv:
                if (carrierid != null && !carrierid.equals("")) {
                    String content = edit_et.getText().toString().trim();
                    AppUtil.getPpApiClient(ac).memberEntercarrierApply(ac.user_id, carrierid, content, this);
                }
                break;
        }
    }

    @Override
    public void onApiStart(String tag) {
        super.onApiStart(tag);
        showLoadingDlg();
    }


    @Override
    public void onApiSuccess(NetResult res, String tag) {
        super.onApiSuccess(res, tag);
        hideLoadingDlg();
        if (res.isOK()) {
            if ("memberEntercarrierApply".equals(tag)) {
                UIHelper.t(_activity, res.message);
                finish();
            }
            if ("memberEntercarrierShow".equals(tag)) {
                EntercarrierShowBean bean = (EntercarrierShowBean) res;
                if (bean.getData().getCarrier().getImages() == null && bean.getData().getCarrier().getImages().equals("")) {
                    ac.setImage(image_iv, R.drawable.default_bg);
                } else {
                    ac.setImage(image_iv, bean.getData().getCarrier().getImages());
                }
                carrierName_tv.setText(bean.getData().getCarrier().getCarrierName());
                //载体类型，1为园区，2为综合体，3为土地，4为写字楼，5为写字楼单元，6为厂房，7为厂房单元，8为仓库，9为仓库单元
                String carrier_type = bean.getData().getCarrier().getCarrierType();
                if ("1".equals(carrier_type)) {
                    type_tv.setText("园区");
                } else if ("2".equals(carrier_type)) {
                    type_tv.setText("综合体");
                } else if ("3".equals(carrier_type)) {
                    type_tv.setText("土地");
                } else if ("4".equals(carrier_type)) {
                    type_tv.setText("写字楼");
                } else if ("5".equals(carrier_type)) {
                    type_tv.setText("写字楼单元");
                } else if ("6".equals(carrier_type)) {
                    type_tv.setText("厂房");
                } else if ("8".equals(carrier_type)) {
                    type_tv.setText("仓库");
                }

                if ("4".equals(carrier_type) || "1".equals(carrier_type)) {//4写字楼、1园区
                    rent_tv.setVisibility(View.VISIBLE);
                    String saleRental = bean.getData().getCarrier().getSaleRental();//0为租售，1为租，2为售
                    if ("1".equals(saleRental) || "0".equals(saleRental)) {
                        rent_tv.setText("租");
                        price_tv.setText(bean.getData().getCarrier().getPriceRent() + "元/m²·月 起");
                    } else if ("2".equals(saleRental)) {
                        rent_tv.setText("售");
                        price_tv.setText(bean.getData().getCarrier().getPriceSell() + "元/m²");//售价
                    }
                    area_tv.setText("待租售面积 " + bean.getData().getCarrier().getBuildingArea() + "m²");
                } else {
                    rent_tv.setVisibility(View.GONE);
                }
                if ("6".equals(carrier_type) || "8".equals(carrier_type)) {//仓库、厂房共有字段
                    area_tv.setText(bean.getData().getCarrier().getFloor() + "层");
                    price_tv.setText("待租售面积 " + FDDataUtils.addComma(bean.getData().getCarrier().getBuildingArea()) + "m²");
                } else if ("3".equals(carrier_type)) {//土地特有的
                    area_tv.setText("土地面积 "+FDDataUtils.addComma(bean.getData().getCarrier().getLandArea()) + "m²");
                    price_tv.setText(bean.getData().getCarrier().getProperty());
                }

                name_tv.setText(bean.getData().getCounselor().getFullname());
                phone_tv.setText(bean.getData().getCounselor().getMobilephone());
                counselorid = bean.getData().getCounselor().getCounselorid();//物业顾问 id
            }

        } else {
            ac.handleErrorCode(_activity, res.message);
        }
    }

    @Override
    public void onApiFailure(Throwable t, int errorNo, String strMsg, String tag) {
        super.onApiFailure(t, errorNo, strMsg, tag);
        hideLoadingDlg();
        if ("memberEntercarrierShow".equals(tag)) {
            finish();
        }
    }
}
