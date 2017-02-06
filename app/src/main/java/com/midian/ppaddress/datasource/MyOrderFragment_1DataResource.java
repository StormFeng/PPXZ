package com.midian.ppaddress.datasource;

import android.content.Context;

import com.midian.ppaddress.bean.MemberOrderCounselorPageOrderBean;
import com.midian.ppaddress.utils.AppUtil;

import java.util.ArrayList;

import midian.baselib.base.BaseListDataSource;
import midian.baselib.bean.NetResult;

/**
 * Created by chu on 2016/2/23.
 */
public class MyOrderFragment_1DataResource extends BaseListDataSource {

    private String type="1",pageNo="1",pageSize="10";//type的取值是1或2,1代表已签约,2代表已完成

    public MyOrderFragment_1DataResource(Context context) {
        super(context);
    }

    @Override
    protected ArrayList load(int page) throws Exception {
        this.page=page;
        ArrayList<NetResult> models = new ArrayList<>();
        MemberOrderCounselorPageOrderBean bean= AppUtil.getPpApiClient(ac).getMemberOrderCounselorPageOrder(ac.user_id,type,page+"",pageSize);
        if(bean.isOK()){
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
