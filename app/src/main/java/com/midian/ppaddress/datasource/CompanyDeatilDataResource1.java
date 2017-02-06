package com.midian.ppaddress.datasource;

import android.content.Context;

import com.midian.ppaddress.bean.BusinessAgencyDetailPageCommentsBean;
import com.midian.ppaddress.bean.BusinessAgencyDetailShowDetailBean;
import com.midian.ppaddress.bean.CompanyDetailBean;
import com.midian.ppaddress.utils.AppUtil;

import java.util.ArrayList;

import midian.baselib.base.BaseListDataSource;

public class CompanyDeatilDataResource1 extends BaseListDataSource {

    private String agencyid;

    public CompanyDeatilDataResource1(Context context) {
        super(context);
    }
    public CompanyDeatilDataResource1(Context context, String agencyid) {
        super(context);
        this.agencyid=agencyid;
    }

    @Override
    protected ArrayList load(int page) throws Exception {
        this.page=page;
        ArrayList<CompanyDetailBean> models = new ArrayList<>();
        BusinessAgencyDetailPageCommentsBean botBean=AppUtil.getPpApiClient(ac).getCommentBean(page+"",agencyid);
        if(botBean.isOK()){
            for(BusinessAgencyDetailPageCommentsBean.CommentList bean : botBean.getData().getList()) {
                CompanyDetailBean item1 = new CompanyDetailBean();
                item1.setItemViewType(1);
                item1.botBean = bean;
                models.add(item1);
            }
            if(botBean.getData().getList().size()<10){
                hasMore=false;
            }else{
                hasMore=true;
            }
        }
        return models;
    }
}
