package com.midian.ppaddress.datasource;

import android.content.Context;

import com.midian.ppaddress.bean.TradingInListBean;
import com.midian.ppaddress.utils.AppUtil;

import java.security.Policy;
import java.util.ArrayList;

import midian.baselib.base.BaseListDataSource;
import midian.baselib.bean.NetResult;

public class MyRecommendF_1DataResource extends BaseListDataSource {

    private String memberid;
    private int type=1,pageNo=1,pageSize=10; //请求参数，type=[1]：交易中，[2]：已成交

    public MyRecommendF_1DataResource(Context context) {
        super(context);
    }

    @Override
    protected ArrayList load(int page) throws Exception {
        this.page=page;
        memberid=ac.user_id;
        ArrayList<TradingInListBean> models = new ArrayList<>();
        TradingInListBean bean= AppUtil.getPpApiClient(ac).getRecomsharesPageTradingIn(memberid,type,pageNo,pageSize);
        if(bean.isOK()){
            for(TradingInListBean.TradingInList item : bean.getData().getList()){
                models.add(bean);
            }
        }
        hasMore=false;
        return models;
    }
}
