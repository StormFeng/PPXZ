package com.midian.ppaddress.datasource;

import android.content.Context;

import com.midian.ppaddress.bean.BusinessAgencyDetailListAgencyexpertBean;
import com.midian.ppaddress.utils.AppUtil;

import java.util.ArrayList;

import midian.baselib.base.BaseListDataSource;

/**
 * Created by chu on 2016/4/21.
 */
public class ChooseWaiterActivityDataSource extends BaseListDataSource {
    private String id;

    public ChooseWaiterActivityDataSource(Context context) {
        super(context);
    }

    public ChooseWaiterActivityDataSource(Context context,String id) {
        super(context);
        this.id=id;
    }

    @Override
    protected ArrayList load(int page) throws Exception {
        ArrayList<BusinessAgencyDetailListAgencyexpertBean.Agencyexpert> models=new ArrayList<>();
        this.page=page;
        BusinessAgencyDetailListAgencyexpertBean bean= AppUtil.getPpApiClient(ac).getAgencyexpert(page+"",id);
        if(bean.isOK()){
            models.addAll(bean.getData().getList());
            if(bean.getData().getList().size()<10){
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
