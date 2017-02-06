package com.midian.ppaddress.datasource;

import android.content.Context;

import com.midian.ppaddress.bean.RewardPageOwnerCommissionBean;
import com.midian.ppaddress.utils.AppUtil;

import java.util.ArrayList;

import midian.baselib.base.BaseListDataSource;
import midian.baselib.bean.NetResult;

/**
 * Created by chu on 2016/2/23.
 */
public class MyRewardDataResource extends BaseListDataSource {

    private int pageSize=10;
    public MyRewardDataResource(Context context) {
        super(context);
    }

    @Override
    protected ArrayList load(int page) throws Exception {
        this.page=page;
        ArrayList<NetResult> models = new ArrayList<>();
        RewardPageOwnerCommissionBean bean= AppUtil.getPpApiClient(ac).getRewardPageOwnerCommission(ac.user_id,ac.getProperty("user_type"),page,pageSize);
        if(bean.isOK()){
//            for(RewardPageOwnerCommissionBean.MoneyList item : bean.getData().getCommissions().getList()){
//                models.add(bean);
//            }
            models.addAll(bean.getData().getCommissions().getList());
            if(bean.getData().getCommissions().getList().size()<10){
                hasMore = false;
            }else{
                hasMore=true;
            }
        }else{
            ac.handleErrorCode(context,bean.errorcode);
        }
        return models;
    }
}
