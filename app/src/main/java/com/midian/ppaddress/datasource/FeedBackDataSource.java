package com.midian.ppaddress.datasource;

import android.content.Context;

import com.midian.ppaddress.bean.AnswerMulBean;
import com.midian.ppaddress.bean.BusinessAgencyConsultPageRecordBean;
import com.midian.ppaddress.bean.FeedBackListBean;
import com.midian.ppaddress.utils.AppUtil;

import java.util.ArrayList;

import midian.baselib.base.BaseListDataSource;

public class FeedBackDataSource extends BaseListDataSource {


    public FeedBackDataSource(Context context) {
        super(context);
    }


    @Override
    protected ArrayList load(int page) throws Exception {
        ArrayList<FeedBackListBean> models=new ArrayList<>();
        FeedBackListBean bean=AppUtil.getPpApiClient(ac).getFeedbackList();
        if(bean.isOK()){
            for(FeedBackListBean.Data item : bean.getData()){
                models.add(bean);
            }
        }
        hasMore=false;
        return models;
    }
}
