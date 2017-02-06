package com.midian.ppaddress.datasource;

import android.content.Context;

import com.midian.ppaddress.bean.MemberMessagePageSysMessagesBean;
import com.midian.ppaddress.utils.AppUtil;

import java.util.ArrayList;

import midian.baselib.base.BaseListDataSource;
import midian.baselib.bean.NetResult;

public class NewsActivityDataResource extends BaseListDataSource {
    private int pageSize=10;

    public NewsActivityDataResource(Context context) {
        super(context);
    }

    @Override
    protected ArrayList load(int page) throws Exception {
        ArrayList<NetResult> models = new ArrayList<>();
        MemberMessagePageSysMessagesBean bean=AppUtil.getPpApiClient(ac).getMemberMessagePageSysMessages(ac.user_id,ac.getProperty("user_type"),page,pageSize);
        if(bean.isOK()){
            models.addAll(bean.getData().getList());
            if(bean.getData().getList().size()<10){
                hasMore=false;
            }else{
                hasMore=true;
            }
        }
        return models;
    }
}
