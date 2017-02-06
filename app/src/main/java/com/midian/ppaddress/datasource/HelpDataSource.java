package com.midian.ppaddress.datasource;

import android.content.Context;

import com.midian.ppaddress.bean.FeedBackListBean;
import com.midian.ppaddress.bean.HelpListBean;
import com.midian.ppaddress.utils.AppUtil;

import java.util.ArrayList;

import midian.baselib.base.BaseListDataSource;

public class HelpDataSource extends BaseListDataSource {

    private String pageNo="1";

    public HelpDataSource(Context context) {
        super(context);
    }


    @Override
    protected ArrayList load(int page) throws Exception {
        ArrayList<HelpListBean> models=new ArrayList<>();
        HelpListBean bean=AppUtil.getPpApiClient(ac).getHelpList(ac.user_id,pageNo);
        if(bean.isOK()){
            for(HelpListBean.Lists item : bean.getData().getList()){
                models.add(bean);
            }
        }
        hasMore=false;
        return models;
    }
}
