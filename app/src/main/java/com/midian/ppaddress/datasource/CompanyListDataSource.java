package com.midian.ppaddress.datasource;

import android.content.Context;

import com.midian.ppaddress.bean.AppIndexBean;
import com.midian.ppaddress.bean.BusinessAgencyScreenPageAgencyBean;
import com.midian.ppaddress.utils.AppUtil;

import java.util.ArrayList;

import midian.baselib.base.BaseListDataSource;
import midian.baselib.bean.NetResult;


public class CompanyListDataSource extends BaseListDataSource {

    private int pageNo=1,industryid;
    public CompanyListDataSource(Context context) {
        super(context);
    }
    public CompanyListDataSource(Context context,int industryid) {
        super(context);
        this.industryid=industryid;
    }

    @Override
    protected ArrayList load(int page) throws Exception {
        ArrayList<NetResult> morelist = new ArrayList<NetResult>();
        BusinessAgencyScreenPageAgencyBean bean= AppUtil.getPpApiClient(ac).getAgencyBean(pageNo,industryid);
        if(bean != null){
            if(bean.isOK()) {
                morelist.addAll(bean.getData().getList());
            }
        }
        hasMore=false;
        return morelist;
    }
}
