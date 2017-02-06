package com.midian.ppaddress.datasource;

import android.content.Context;

import com.midian.ppaddress.bean.MemberOrderMemberPageSignOrderBean;
import com.midian.ppaddress.utils.AppUtil;

import java.util.ArrayList;

import midian.baselib.base.BaseListDataSource;
import midian.baselib.bean.NetResult;

public class BookedDataResource extends BaseListDataSource {
    public BookedDataResource(Context context) {
        super(context);
    }

    @Override
    protected ArrayList load(int page) throws Exception {
        this.page=page;
        ArrayList<NetResult> models = new ArrayList<>();
        MemberOrderMemberPageSignOrderBean bean = AppUtil.getPpApiClient(ac).getMemberOrderMemberPageSignOrder(page,ac.user_id);
        if(bean.isOK()) {
            models.addAll(bean.getData().getList());
            if("true".equals(bean.getData().getLastPage())){
                hasMore=false;
            }else{
                hasMore=true;
            }
        }else{
            ac.handleErrorCode(context, bean.errorcode);
        }
        return models;
    }
}
