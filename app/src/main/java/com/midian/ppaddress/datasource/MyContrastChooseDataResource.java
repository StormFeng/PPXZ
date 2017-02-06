package com.midian.ppaddress.datasource;

import android.content.Context;

import com.midian.ppaddress.bean.MemberCompareListBean;
import com.midian.ppaddress.utils.AppUtil;

import java.util.ArrayList;

import midian.baselib.base.BaseListDataSource;
import midian.baselib.bean.NetResult;

public class MyContrastChooseDataResource extends BaseListDataSource{
    private String carrierType;

    public MyContrastChooseDataResource(Context context) {
        super(context);
    }

    public MyContrastChooseDataResource(String carrierType,Context context) {
        super(context);
        this.carrierType=carrierType;
    }

    @Override
    protected ArrayList load(int page) throws Exception {
        this.page=page;
        ArrayList<MemberCompareListBean> models = new ArrayList<>();
        MemberCompareListBean bean= AppUtil.getPpApiClient(ac).compareList(carrierType);
        if(bean.isOK()){
            for(MemberCompareListBean.CompareData item : bean.getData()){
                models.add(bean);
            }
        }
        hasMore=false;
        return models;
    }
}
