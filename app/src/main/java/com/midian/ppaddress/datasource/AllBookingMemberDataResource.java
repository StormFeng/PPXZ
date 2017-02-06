package com.midian.ppaddress.datasource;

import android.content.Context;
import android.util.Log;

import com.midian.ppaddress.bean.MemberOrderMemberPageBean;
import com.midian.ppaddress.utils.AppUtil;

import java.util.ArrayList;

import midian.baselib.base.BaseListDataSource;
import midian.baselib.bean.NetResult;

/**
 * 16.3 我（普通客商）的预约-全部的预约
 */
public class AllBookingMemberDataResource extends BaseListDataSource {
    private int pageSize=10;
    private String stage;

    public AllBookingMemberDataResource(Context context) {
        super(context);
    }

    public String getStage() {
        return stage;
    }

    public void setStage(String stage) {
        this.stage = stage;
    }


    @Override
    protected ArrayList load(int page) throws Exception {
        this.page=page;
        Log.d("wqf","page::"+page);
        ArrayList<NetResult> models = new ArrayList<>();
        MemberOrderMemberPageBean bean=AppUtil.getPpApiClient(ac).getOrderMemberPage(ac.user_id,stage,page,pageSize);
        if(bean.isOK()){
            models.addAll(bean.getData().getList());
            if("true".equals(bean.getData().getLastPage())){
                hasMore=false;
            }else{
                hasMore=true;
//                this.page++;
            }
        }else{
            ac.handleErrorCode(context, bean.errorcode);
        }
        return models;
    }
}
