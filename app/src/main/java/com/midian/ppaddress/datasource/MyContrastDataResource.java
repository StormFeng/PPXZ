package com.midian.ppaddress.datasource;

import android.content.Context;

import com.midian.ppaddress.bean.MemberCompareListTypeBean;
import com.midian.ppaddress.utils.AppUtil;

import java.util.ArrayList;

import midian.baselib.base.BaseListDataSource;
import midian.baselib.bean.NetResult;

public class MyContrastDataResource extends BaseListDataSource{
    private int memberid;

    public MyContrastDataResource(Context context) {
        super(context);
    }

    @Override
    protected ArrayList load(int page) throws Exception {
        this.page=page;
        memberid=Integer.valueOf(ac.user_id).intValue();
        ArrayList<MemberCompareListTypeBean> models = new ArrayList<>();
        MemberCompareListTypeBean bean= AppUtil.getPpApiClient(ac).compareTypeList(memberid);
        for(MemberCompareListTypeBean.Data item : bean.getData()){
            models.add(bean);
        }
        hasMore=false;
        return models;
    }
}
