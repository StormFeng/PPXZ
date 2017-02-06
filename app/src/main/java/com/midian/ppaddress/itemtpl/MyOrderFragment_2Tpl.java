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
import com.midian.ppaddress.ui.personal.LookContractActivity;
import com.midian.ppaddress.ui.personal.UploadContractActivity;
import com.midian.ppaddress.ui.personal.UploadContractActivity1;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import midian.baselib.bean.NetResult;
import midian.baselib.utils.UIHelper;
import midian.baselib.view.BaseTpl;

/**
 * 我的订单--已完成条目
 */
public class MyOrderFragment_2Tpl extends BaseTpl<MemberOrderCounselorPageOrderBean.Lists> implements View.OnClickListener{
    private ImageView iv_image,iv_portrait;
    private TextView tv_booknumber,tv_name,tv_type,tv_id,tv_fullname,tv_number,tv_time,tv_money,tv_rmb;
    private Button btn_LookContract;
    private String orderid;
    private String carriertype,carriertitle,carrierid;

    public MyOrderFragment_2Tpl(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyOrderFragment_2Tpl(Context context) {
        super(context);
    }

    @Override
    protected void initView() {
        iv_image=findView(R.id.iv_image);
        iv_portrait=findView(R.id.iv_portrait);
        tv_booknumber=findView(R.id.tv_booknumber);
        tv_name=findView(R.id.tv_name);
        tv_type=findView(R.id.tv_type);
        tv_id=findView(R.id.tv_id);
        tv_fullname=findView(R.id.tv_fullname);
        tv_number= findView(R.id.tv_number);
        tv_time=findView(R.id.tv_time);
        tv_money=findView(R.id.tv_money);
        tv_rmb=findView(R.id.tv_rmb);
        btn_LookContract=findView(R.id.btn_lookContract);
        btn_LookContract.setOnClickListener(this);
        findView(R.id.ll_item).setOnClickListener(this);
    }

    @Override
    protected int
    getLayoutId() {
        return R.layout.item_myorder_completed;
    }

    @Override
    public void setBean(MemberOrderCounselorPageOrderBean.Lists bean, int position) {
        if(bean!=null){
            ac.setImage(iv_image,bean.getImages());
            ac.setImage(iv_portrait,bean.getPortrait());
            tv_booknumber.setText("订单号: "+bean.getNumber());
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
//            tv_time.setText("创建时间: "+bean.getStartTime());
            if("11".equals(bean.getStage())){
                tv_money.setText("获得奖金 ");
                tv_rmb.setText(bean.getMoney());
            }else{
                tv_money.setText("");
                tv_rmb.setText("未打赏");
            }
            if("3".equals(bean.getCarrierType())){
                tv_type.setText("土地");
            }
            if("4".equals(bean.getCarrierType())){
                tv_type.setText("写字楼");
            }
            if("6".equals(bean.getCarrierType())){
                tv_type.setText("厂房");
            }
            if("8".equals(bean.getCarrierType())){
                tv_type.setText("仓库");
            }
            orderid=bean.getId();
            carriertitle=bean.getCarrierName();
            carriertype=bean.getCarrierType();
            carrierid=bean.getCarrierid();
        }
    }

    @Override
    public void onClick(View view) {
        Bundle bundle=new Bundle();
        switch (view.getId()){
            case R.id.btn_lookContract:
                //查看合同
                bundle.putString("type", "look");
                bundle.putString("orderid", orderid);
//                UIHelper.jump(_activity, LookContractActivity.class,bundle);
                UIHelper.jump(_activity, UploadContractActivity1.class,bundle);
                break;
            case R.id.tv_number:
                String phoneNumber = tv_number.getText().toString().trim();
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phoneNumber));
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                _activity.startActivity(intent);
                break;
            case R.id.ll_item:
                bundle.putString("carriertitle",carriertitle);
                bundle.putString("carriertype",carriertype);
                bundle.putString("carrierid",carrierid);
                UIHelper.jump(_activity, ParkDetailActivity.class,bundle);
                break;
        }
    }
}
