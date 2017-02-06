package com.midian.ppaddress.datasource;

import android.content.Context;

import com.midian.ppaddress.bean.EntercarrierPageRentsBean;
import com.midian.ppaddress.utils.AppUtil;

import java.util.ArrayList;

import midian.baselib.base.BaseListDataSource;
import midian.baselib.bean.NetResult;

/**
 * Created by chu on 2016/2/23.
 */
public class LeaseStateDataResource extends BaseListDataSource {

    private String carrierid;

    public LeaseStateDataResource(Context context) {
        super(context);
    }
    public LeaseStateDataResource(String carrierid,Context context) {
        super(context);
        this.carrierid=carrierid;
    }

    @Override
    protected ArrayList load(int page) throws Exception {
        ArrayList<EntercarrierPageRentsBean> models = new ArrayList<>();
        EntercarrierPageRentsBean bean= AppUtil.getPpApiClient(ac).getEntercarrierPageRents(carrierid,1,10);
        if(bean.isOK()){
            for(EntercarrierPageRentsBean.RentsList item : bean.getData().getRents().getList()){
                models.add(bean);
            }
        }
        hasMore = false;
        return models;
    }
}
