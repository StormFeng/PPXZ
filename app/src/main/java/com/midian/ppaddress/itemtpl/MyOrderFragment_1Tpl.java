package com.midian.ppaddress.itemtpl;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.midian.ppaddress.R;
import com.midian.ppaddress.bean.MemberOrderCounselorPageOrderBean;
import com.midian.ppaddress.ui.chooseaddres.MultiDetailActivity;
import com.midian.ppaddress.ui.chooseaddres.ParkDetailActivity;
import com.midian.ppaddress.ui.personal.UploadContractActivity;
import com.midian.ppaddress.ui.personal.UploadContractActivity1;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import midian.baselib.bean.NetResult;
import midian.baselib.utils.UIHelper;
import midian.baselib.view.BaseTpl;

/**
 * 我的订单条目
 */
public class MyOrderFragment_1Tpl extends BaseTpl<MemberOrderCounselorPageOrderBean.Lists> implements View.OnClickListener {

    private ImageView iv_image, iv_portrait;
    private TextView tv_booknumber, tv_name, tv_type, tv_id, tv_fullname, tv_number, tv_time, tv_stage;
    private Button btn_upload;
    private String carriertype, carriertitle, carrierid;
    private String orderid;//订单id
    private int stage;

    public MyOrderFragment_1Tpl(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyOrderFragment_1Tpl(Context context) {
        super(context);
    }

    @Override
    protected void initView() {
        iv_image = findView(R.id.iv_image);
        iv_portrait = findView(R.id.iv_portrait);
        tv_booknumber = findView(R.id.tv_booknumber);
        tv_name = findView(R.id.tv_name);
        tv_type = findView(R.id.tv_type);
        tv_id = findView(R.id.tv_id);
        tv_fullname = findView(R.id.tv_fullname);
        tv_number = findView(R.id.tv_number);
        tv_time = findView(R.id.tv_time);
        tv_stage = findView(R.id.tv_stage);
        btn_upload = findView(R.id.btn_upload);
        btn_upload.setOnClickListener(this);
        tv_number.setOnClickListener(this);
        findView(R.id.ll_item).setOnClickListener(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.item_myorder_booked;
    }

    @Override
    public void setBean(MemberOrderCounselorPageOrderBean.Lists bean, int position) {
        if (bean!=null) {
            orderid = bean.getId();
            ac.setImage(iv_image, bean.getImages());
            ac.setImage(iv_portrait, bean.getPortrait());
            tv_booknumber.setText("订单号: " + bean.getNumber());
            tv_name.setText(bean.getCarrierName());
            tv_id.setText(bean.getCode());
            tv_fullname.setText(bean.getMemberName());
            tv_number.setText(bean.getMobilephone());
            String sTime = bean.getStartTime();
            if(!TextUtils.isEmpty(sTime)) {
                Date date = null;
                try {
                    date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(sTime);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                SimpleDateFormat sp = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                sTime = sp.format(date);
            }
            tv_time.setText("创建时间: " + sTime);

            //订单阶段，0为取消预约，1为预约，2为已预约，3为已考察，4为意向同意，5为已签约，6为已上传合同，7为待审核合同，8为审核不通过合同，9为审核通过合同/待付款到平台，10为付款到平台/待打赏，11为已打赏

            stage = Integer.parseInt(bean.getStage());
            if ("0".equals(bean.getStage()))
                tv_stage.setText("取消预约");
            if ("1".equals(bean.getStage()))
                tv_stage.setText("预约");
            if ("2".equals(bean.getStage()))
                tv_stage.setText("已预约");
            if ("3".equals(bean.getStage()))
                tv_stage.setText("已考察");
            if ("4".equals(bean.getStage()))
                tv_stage.setText("意向同意");
            if ("5".equals(bean.getStage()))
                tv_stage.setText("已签约");
            btn_upload.setText("上传合同");
            if ("6".equals(bean.getStage())) {
                tv_stage.setText("已上传合同");
                btn_upload.setText("编辑合同");
            }
            if ("7".equals(bean.getStage())) {
                tv_stage.setText("待审核合同");
                btn_upload.setText("编辑合同");
            }
            if ("8".equals(bean.getStage())) {
                tv_stage.setText("合同审核不通过");
                btn_upload.setText("重新上传合同");
            }
            if ("9".equals(bean.getStage())) {
                tv_stage.setText("合同审核通过");
                btn_upload.setText("查看合同");
            }
            if ("10".equals(bean.getStage())) {
                tv_stage.setText("待打赏");
                btn_upload.setText("查看合同");
            }
            if ("11".equals(bean.getStage())) {
                tv_stage.setText("已打赏");
                btn_upload.setText("查看合同");
            }


            if ("3".equals(bean.getCarrierType())) {
                tv_type.setText("土地");
            }
            if ("4".equals(bean.getCarrierType())) {
                tv_type.setText("写字楼");
            }
            if ("6".equals(bean.getCarrierType())) {
                tv_type.setText("厂房");
            }
            if ("8".equals(bean.getCarrierType())) {
                tv_type.setText("仓库");
            }
            carriertitle = bean.getCarrierName();
            carriertype = bean.getCarrierType();
            carrierid = bean.getCarrierid();
        }
    }

    @Override
    public void onClick(View view) {
        Bundle bundle = new Bundle();
        switch (view.getId()) {
            case R.id.btn_upload:
                //阶段为5时，显示“上传合同”；阶段为6、7时，显示“编辑合同”；阶段为8时，显示“重新上传合同”；阶段为9以后，显示“查看合同”；
                if (5 == stage) {
                    //上传合同
                    bundle.putString("type", "upload");
                    bundle.putString("orderid", orderid);
                } else if (6 == stage || 7 == stage) {
                    //编辑合同
                    bundle.putString("type", "normal");
                    bundle.putString("orderid", orderid);
                } else if (8 == stage) {
                    //重新上传合同
                    bundle.putString("type", "again");
                    bundle.putString("orderid", orderid);
                } else if (9 >= stage) {
                    //查看合同
                    bundle.putString("type", "look");
                    bundle.putString("orderid", orderid);
                }
                UIHelper.jump(_activity, UploadContractActivity1.class, bundle);//上传合同
                break;
            case R.id.tv_number:
                String phoneNumber = tv_number.getText().toString().trim();
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phoneNumber));
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                _activity.startActivity(intent);
                break;
            case R.id.ll_item:
                bundle.putString("carriertitle", carriertitle);
                bundle.putString("carriertype", carriertype);
                bundle.putString("carrierid", carrierid);
                UIHelper.jump(_activity, ParkDetailActivity.class, bundle);
                break;
        }
    }
}
