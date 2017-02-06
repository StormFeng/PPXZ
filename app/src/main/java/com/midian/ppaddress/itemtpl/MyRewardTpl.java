package com.midian.ppaddress.itemtpl;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.midian.ppaddress.R;
import com.midian.ppaddress.bean.RewardPageOwnerCommissionBean;

import midian.baselib.bean.NetResult;
import midian.baselib.view.BaseTpl;

/**
 * 详情-评论条目
 * Created by chu on 2016/4/25.
 */
public class MyRewardTpl extends BaseTpl<RewardPageOwnerCommissionBean.MoneyList> implements View.OnClickListener{

    private TextView tv_name,tv_money,tv_booknumber,tv_stage,tv_time;

    public MyRewardTpl(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyRewardTpl(Context context) {
        super(context);
    }

    @Override
    protected void initView() {
        tv_name=findView(R.id.tv_name);
        tv_money=findView(R.id.tv_moneynumber);
        tv_booknumber=findView(R.id.tv_booknumber);
        tv_stage=findView(R.id.tv_stage);
        tv_time=findView(R.id.tv_time);

    }

    @Override
    protected int getLayoutId() {
        return R.layout.item_myreward ;
    }

    @Override
    public void setBean(RewardPageOwnerCommissionBean.MoneyList bean, int position) {
        if(bean!=null){
//            RewardPageOwnerCommissionBean.MoneyList textList = bean.getData().getCommissions().getList().get(position);
            tv_name.setText(bean.getCarrierName());
            String money=bean.getMoney();
            if(money.contains("E")){
                money = getString(money);
            }
            tv_money.setText("¥"+money);
            tv_booknumber.setText("载体ID: "+bean.getCarrierCode());
            tv_time.setText(bean.getCreateTime());
            if("9".equals(bean.getStage())||"10".equals(bean.getStage())){
                tv_stage.setText("已签约");
                tv_stage.setTextColor(0xFFE56032);
            }
            if("11".equals(bean.getStage())){
                tv_stage.setText("已打赏");
                tv_stage.setTextColor(getResources().getColor(R.color.text_bg90));
            }
        }
    }
    @NonNull
    private String getString(String allMoney) {
        String s[] = allMoney.split("E");
        String a[]=s[0].split("[.]");
        int n=Integer.valueOf(s[1]).intValue()-a[1].length();
        allMoney=a[0]+a[1];
        for(int i=0;i<n;i++){
            allMoney+="0";
        }
        return allMoney;
    }

    @Override
    public void onClick(View view){
    }
}
