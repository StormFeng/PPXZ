package com.midian.ppaddress.datasource;

import android.content.Context;

import com.midian.ppaddress.bean.ClinchDealListBean;
import com.midian.ppaddress.utils.AppUtil;

import java.util.ArrayList;

import midian.baselib.base.BaseListDataSource;
import midian.baselib.bean.NetResult;

/**
 * Created by chu on 2016/2/23.
 */
public class MyRecommendF_2DataResource extends BaseListDataSource {
    private int type=2,pageNo=1,pageSize=10; //请求参数，type=[1]：交易中，[2]：已成交
    public MyRecommendF_2DataResource(Context context) {
        super(context);
    }

    @Override
    protected ArrayList load(int page) throws Exception {
        this.page=page;
        ArrayList<ClinchDealListBean> models = new ArrayList<>();
        ClinchDealListBean bean= AppUtil.getPpApiClient(ac).getRecomsharesPage(ac.user_id,type,pageNo,pageSize);
        if(bean.isOK()){
            for(ClinchDealListBean.ClinchDealList item : bean.getData().getList()){
                models.add(bean);
                if(bean.getData().getList().size()<10){
                    hasMore=false;
                }else{
                    hasMore=true;
                    this.page++;
                }
            }
        }
        return models;
    }
}
