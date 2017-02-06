package com.midian.ppaddress.datasource;

import android.content.Context;
import android.util.Log;

import com.midian.ppaddress.bean.BusinessAgencyDetailPageCommentsBean;
import com.midian.ppaddress.bean.BusinessAgencyDetailShowDetailBean;
import com.midian.ppaddress.bean.CompanyDetailBean;
import com.midian.ppaddress.utils.AppUtil;

import java.util.ArrayList;

import midian.baselib.base.BaseListDataSource;
import midian.baselib.bean.NetResult;

public class CompanyDeatilDataResource extends BaseListDataSource {

    private String agencyid;

    public CompanyDeatilDataResource(Context context) {
        super(context);
    }
    public CompanyDeatilDataResource(Context context,String agencyid) {
        super(context);
        this.agencyid=agencyid;
    }

    @Override
    protected ArrayList load(int page) throws Exception {
        this.page=page;
        ArrayList<CompanyDetailBean> models = new ArrayList<>();
        BusinessAgencyDetailShowDetailBean topBean = AppUtil.getPpApiClient(ac).getBusinessDetailPage(agencyid, ac.user_id);
        if(page==1) {
            CompanyDetailBean item0 = new CompanyDetailBean();
            item0.setItemViewType(0);
            item0.topBean = topBean;
            models.add(item0);
        }
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
