package com.midian.ppaddress.ui.personal;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.TextView;

import com.midian.ppaddress.R;
import com.midian.ppaddress.bean.RewardPageOwnerCommissionBean;
import com.midian.ppaddress.datasource.MyBuildingResourceDataResource;
import com.midian.ppaddress.datasource.MyRewardDataResource;
import com.midian.ppaddress.itemtpl.MyBuildingResourceTpl;
import com.midian.ppaddress.itemtpl.MyRewardTpl;
import com.midian.ppaddress.utils.AppUtil;

import java.util.ArrayList;

import midian.baselib.base.BaseListActivity;
import midian.baselib.bean.NetResult;
import midian.baselib.shizhefei.view.listviewhelper.IDataSource;
import midian.baselib.utils.UIHelper;
import midian.baselib.widget.BaseLibTopbarView;

public class MyRewardActivity extends BaseListActivity{

    private BaseLibTopbarView topbar;
    private int pageNo=1,pageSize=10;
    private TextView tv_money;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        topbar=findView(R.id.topbar);
        tv_money=findView(R.id.tv_money);
        topbar.setTitle("我的打赏").setLeftImageButton(R.drawable.icon_back,UIHelper.finish(this));
        AppUtil.getPpApiClient(ac).rewardPageOwnerCommission(ac.user_id,ac.getProperty("user_type"),pageNo,pageSize,this);
    }

    @Override
    public void onApiSuccess(NetResult res, String tag) {
        super.onApiSuccess(res, tag);
        RewardPageOwnerCommissionBean bean= (RewardPageOwnerCommissionBean) res;
        String allMoney=bean.getData().getTotalMoney();
        String payMoney=bean.getData().getHasPay();
        if(allMoney.contains("E")){
            allMoney = getString(allMoney);
        }
        if(payMoney.contains("E")){
            payMoney = getString(payMoney);
        }
        tv_money.setText("赏金总额:"+allMoney+"元 "+"(已支付:"+payMoney+"元)");
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
    protected int getLayoutId() {
        return R.layout.activity_myreward;
    }

    @Override
    protected IDataSource<ArrayList> getDataSource() {
        return new MyRewardDataResource(_activity);
    }

    @Override
    protected Class getTemplateClass() {
        return MyRewardTpl.class;
    }
}
