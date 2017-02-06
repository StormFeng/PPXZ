package com.midian.ppaddress.ui.chooseaddres;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.midian.login.view.LoginActivity;
import com.midian.ppaddress.R;
import com.midian.ppaddress.bean.MemberOrderMemberShowBean;
import com.midian.ppaddress.utils.AppUtil;

import java.util.ArrayList;
import java.util.List;

import midian.baselib.base.BaseActivity;
import midian.baselib.bean.NetResult;
import midian.baselib.utils.FDDataUtils;
import midian.baselib.utils.UIHelper;
import midian.baselib.widget.BaseLibTopbarView;
import midian.baselib.widget.CircleImageView;
import midian.baselib.widget.FlowLayout;
import midian.baselib.widget.flowlayout.FlowTagLayout;
import midian.baselib.widget.flowlayout.OnTagClickListener;
import midian.baselib.widget.flowlayout.OnTagSelectListener;

/**
 * 一键预约
 * Created by chu on 2016/4/27.
 */
public class MakeAppointmentActivity extends BaseActivity{
    private BaseLibTopbarView topbar;
    private ImageView image_iv;
    private TextView carrierName_tv, type_tv, rent_tv, price_tv, area_tv, phone_tv;
    private EditText edit_et;
    private TextView confirm_tv;
    private ImageView head_cv1,head_cv2,head_cv3,head_cv4;
    private TextView name_tv1,name_tv2,name_tv3,name_tv4;
    private CheckBox check_tv1,check_tv2,check_tv3,check_tv4;
    private LinearLayout ll_person,ll_person1,ll_person2,ll_person3,ll_person4;
    private String counselorid, contactPhone;
    private String carrierid;
    private CheckBox check_cb;

    List<MemberOrderMemberShowBean.Counselors> data = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_appointment);
        carrierid = mBundle.getString("carrierid");//载体id
        counselorid = mBundle.getString("counselorid");//驻守的物业顾问id
        initView();
    }

    private void initView() {
        topbar = findView(R.id.topbar);
        topbar.setTitle("预约").setLeftImageButton(R.drawable.icon_back, UIHelper.finish(_activity));
        image_iv = findView(R.id.image_iv);
        carrierName_tv = findView(R.id.carrierName_tv);
        type_tv = findView(R.id.type_tv);
        rent_tv = findView(R.id.rent_tv);
        price_tv = findView(R.id.price_tv);
        area_tv = findView(R.id.area_tv);
        phone_tv = findView(R.id.phone_tv);
        edit_et = findView(R.id.edit_et);
        check_cb=findView(R.id.check_cb);
        confirm_tv = findView(R.id.confirm_tv);

        head_cv1=findView(R.id.head_cv1);
        head_cv2=findView(R.id.head_cv2);
        head_cv3=findView(R.id.head_cv3);
        head_cv4=findView(R.id.head_cv4);

        name_tv1=findView(R.id.name_tv1);
        name_tv2=findView(R.id.name_tv2);
        name_tv3=findView(R.id.name_tv3);
        name_tv4=findView(R.id.name_tv4);

        check_tv1=findView(R.id.check_tv1);
        check_tv2=findView(R.id.check_tv2);
        check_tv3=findView(R.id.check_tv3);
        check_tv4=findView(R.id.check_tv4);

        ll_person=findView(R.id.ll_person);
        ll_person1=findView(R.id.ll_person1);
        ll_person2=findView(R.id.ll_person2);
        ll_person3=findView(R.id.ll_person3);
        ll_person4=findView(R.id.ll_person4);

        check_cb.setOnClickListener(this);
        ll_person1.setOnClickListener(this);
        ll_person2.setOnClickListener(this);
        ll_person3.setOnClickListener(this);
        ll_person4.setOnClickListener(this);
        confirm_tv.setOnClickListener(this);

        loadData();

    }

    private void loadData() {
        AppUtil.getPpApiClient(ac).memberOrderMemberShow(carrierid, this);
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
            if ("memberOrderMemberShow".equals(tag)) {
                MemberOrderMemberShowBean bean = (MemberOrderMemberShowBean) res;

                if (bean.getData().getCarrier().getImages() == null && bean.getData().getCarrier().getImages().equals("")) {
                    ac.setImage(image_iv, R.drawable.default_bg);
                } else {
                    ac.setImage(image_iv, bean.getData().getCarrier().getImages());
                }
                String carrierName = bean.getData().getCarrier().getCarrierName();
                carrierName_tv.setText(carrierName);
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
                    if ("1".equals(saleRental)||"0".equals(saleRental)) {
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
                    area_tv.setText("土地面积 " + FDDataUtils.addComma(bean.getData().getCarrier().getLandArea()) + "m²");
                    price_tv.setText(bean.getData().getCarrier().getProperty());
                }


                contactPhone = bean.getData().getMember().getMobilephone();
                phone_tv.setText(contactPhone);
                data = bean.getData().getCounselors();
                if (data.size() < 1) {
                    findView(R.id.ll_item).setVisibility(View.GONE);
                } else if (data.size() == 1) {
                    ll_person2.setVisibility(View.GONE);
                    ll_person3.setVisibility(View.GONE);
                    ll_person4.setVisibility(View.GONE);
                    initPerson();
                    initCheckBox();
                } else if (data.size() == 2) {
                    ll_person3.setVisibility(View.GONE);
                    ll_person4.setVisibility(View.GONE);
                    initPerson();
                    initCheckBox();
                } else if (data.size() == 3) {
                    ll_person4.setVisibility(View.GONE);
                    initPerson();
                    initCheckBox();
                } else {
                    initPerson();
                    initCheckBox();
                }

            }
            //一键预约接口回调
            if ("memberOrderMemberApply".equals(tag)) {
                UIHelper.t(_activity, res.message);
                finish();
            }

        } else {
            ac.handleErrorCode(_activity, res.message);
        }
    }

    private void initCheckBox() {
        try {
            if(counselorid.equals(data.get(0).getCounselorid())){
                ll_person1.performClick();
            }else if(counselorid.equals(data.get(1).getCounselorid())){
                ll_person2.performClick();
            }else if(counselorid.equals(data.get(2).getCounselorid())){
                ll_person3.performClick();
            }else if(counselorid.equals(data.get(3).getCounselorid())){
                ll_person4.performClick();
            }else{
                ll_person1.performClick();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setCheckBox(int arg0) {
        check_tv1.setChecked(false);
        check_tv2.setChecked(false);
        check_tv3.setChecked(false);
        check_tv4.setChecked(false);
        check_cb.setChecked(false);
        CheckBox cb= (CheckBox) findViewById(arg0);
        cb.setChecked(true);
    }

    private void initPerson() {
        try {
            ac.setImage(head_cv1,data.get(0).getPortrait());
            name_tv1.setText(data.get(0).getFullname());
            ac.setImage(head_cv2,data.get(1).getPortrait());
            name_tv2.setText(data.get(1).getFullname());
            ac.setImage(head_cv3,data.get(2).getPortrait());
            name_tv3.setText(data.get(2).getFullname());
            ac.setImage(head_cv4,data.get(3).getPortrait());
            name_tv4.setText(data.get(3).getFullname());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    public void onApiFailure(Throwable t, int errorNo, String strMsg, String tag) {
        super.onApiFailure(t, errorNo, strMsg, tag);
        hideLoadingDlg();
        if ("memberOrderMemberShow".equals(tag)) {
            finish();
        }
    }
    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.confirm_tv:
                String name = edit_et.getText().toString().trim();
                if (TextUtils.isEmpty(name)) {
                    UIHelper.t(_activity, "请填写您的真实姓名");
                    return;
                }
                if(check_tv1.isChecked()){
                    counselorid=data.get(0).getCounselorid();
                }else if(check_tv2.isChecked()){
                    counselorid=data.get(1).getCounselorid();
                }else if(check_tv3.isChecked()){
                    counselorid=data.get(2).getCounselorid();
                }else if(check_tv4.isChecked()){
                    counselorid=data.get(3).getCounselorid();
                }else if(check_tv4.isChecked()){
                    counselorid=null;
                }
                System.out.println("接口提交的物业顾问id+++counselorid=" + counselorid);
                AppUtil.getPpApiClient(ac).memberOrderMemberApply(ac.user_id, carrierid, name, counselorid, contactPhone, null, this);
                break;
            case R.id.ll_person1:
                setCheckBox(R.id.check_tv1);
                check_tv1.setTextColor(getResources().getColor(R.color.white));
                check_tv2.setTextColor(getResources().getColor(R.color.text_bg90));
                check_tv3.setTextColor(getResources().getColor(R.color.text_bg90));
                check_tv4.setTextColor(getResources().getColor(R.color.text_bg90));
                check_cb.setTextColor(getResources().getColor(R.color.text_bg90));
                break;
            case R.id.ll_person2:
                setCheckBox(R.id.check_tv2);
                check_tv1.setTextColor(getResources().getColor(R.color.text_bg90));
                check_tv2.setTextColor(getResources().getColor(R.color.white));
                check_tv3.setTextColor(getResources().getColor(R.color.text_bg90));
                check_tv4.setTextColor(getResources().getColor(R.color.text_bg90));
                check_cb.setTextColor(getResources().getColor(R.color.text_bg90));
                break;
            case R.id.ll_person3:
                setCheckBox(R.id.check_tv3);
                check_tv1.setTextColor(getResources().getColor(R.color.text_bg90));
                check_tv2.setTextColor(getResources().getColor(R.color.text_bg90));
                check_tv3.setTextColor(getResources().getColor(R.color.white));
                check_tv4.setTextColor(getResources().getColor(R.color.text_bg90));
                check_cb.setTextColor(getResources().getColor(R.color.text_bg90));
                break;
            case R.id.ll_person4:
                setCheckBox(R.id.check_tv4);
                check_tv1.setTextColor(getResources().getColor(R.color.text_bg90));
                check_tv2.setTextColor(getResources().getColor(R.color.text_bg90));
                check_tv3.setTextColor(getResources().getColor(R.color.text_bg90));
                check_tv4.setTextColor(getResources().getColor(R.color.white));
                check_cb.setTextColor(getResources().getColor(R.color.text_bg90));
                break;
            case R.id.check_cb:
                setCheckBox(R.id.check_cb);
                check_tv1.setTextColor(getResources().getColor(R.color.text_bg90));
                check_tv2.setTextColor(getResources().getColor(R.color.text_bg90));
                check_tv3.setTextColor(getResources().getColor(R.color.text_bg90));
                check_tv4.setTextColor(getResources().getColor(R.color.text_bg90));
                check_cb.setTextColor(getResources().getColor(R.color.white));
                break;
        }
    }
}
