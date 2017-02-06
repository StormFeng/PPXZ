package com.midian.ppaddress.datasource;

import android.content.Context;

import com.midian.ppaddress.bean.CounselorPageCarrierBean;
import com.midian.ppaddress.utils.AppUtil;

import java.util.ArrayList;

import midian.baselib.base.BaseListDataSource;
import midian.baselib.bean.NetResult;

/**
 * Created by chu on 2016/5/5.
 */
public class ToBodyCommentDataSource extends BaseListDataSource {
    private int pageNo=1,roletype=2;

    public ToBodyCommentDataSource(Context context) {
        super(context);
    }

    @Override
    protected ArrayList load(int page) throws Exception {
        this.page=page;
        ArrayList<NetResult> morelist = new ArrayList<>();
        CounselorPageCarrierBean bean= AppUtil.getPpApiClient(ac).getCounselorPageCarrier(ac.user_id,ac.getProperty("user_type"),page);
        if(bean.isOK()){
            morelist.addAll(bean.getData().getList());
            if(bean.getData().getList().size()<10){
                hasMore=false;
            }else{
                hasMore=true;
            }
        }else{
            ac.handleErrorCode(context,bean.errorcode);
        }
        return morelist;
    }
}
