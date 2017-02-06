package com.midian.ppaddress.datasource;

import android.content.Context;

import com.midian.ppaddress.bean.MemberOrderCounselorPageBean;
import com.midian.ppaddress.utils.AppUtil;

import java.util.ArrayList;

import midian.baselib.base.BaseListDataSource;
import midian.baselib.bean.NetResult;

public class AllBookingCounselorDataResource extends BaseListDataSource {
    private String memberid;
    private int pageNo=1,pageSize=10;
    private String stage;

    public AllBookingCounselorDataResource(Context context) {
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
        memberid=ac.user_id;
        this.page=page;
        ArrayList<NetResult> models = new ArrayList<>();
        MemberOrderCounselorPageBean bean=AppUtil.getPpApiClient(ac).getMemberOrderCounselorPage(memberid,stage,page,pageSize);
        if(bean.isOK()){
            models.addAll(bean.getData().getList());
            if(bean.getData().getList().size()<10){
                hasMore=false;
            }else{
                hasMore=true;
            }
        }else{
            ac.handleErrorCode(context, bean.message);
        }
        return models;
    }
}
