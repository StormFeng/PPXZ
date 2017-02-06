package com.midian.ppaddress.datasource;

import android.content.Context;

import com.midian.ppaddress.bean.MemberFavoritePageAgencyBean;
import com.midian.ppaddress.utils.AppUtil;

import java.util.ArrayList;

import midian.baselib.base.BaseListDataSource;

public class MyCollectionTab2DataResource extends BaseListDataSource {
    private int pageSize=10;

    public MyCollectionTab2DataResource(Context context) {
        super(context);
    }

    @Override
    protected ArrayList load(int page) throws Exception {
        this.page=page;
        ArrayList<MemberFavoritePageAgencyBean.AgencyList> models = new ArrayList<>();
        MemberFavoritePageAgencyBean bean= AppUtil.getPpApiClient(ac).getMemberFavoritePageAgency(page,pageSize);
        if(bean.isOK()) {
            models.addAll(bean.getData().getList());
            if(bean.getData().getList().size()<10){
                hasMore=false;
            }else{
                hasMore=true;
            }
        }else{
            ac.handleErrorCode(context,bean.errorcode);
        }
        return models;
    }
}
