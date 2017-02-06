package com.midian.ppaddress.datasource;

import android.content.Context;

import com.midian.ppaddress.bean.BusinessCarrierCommentsPageExpertsBean;
import com.midian.ppaddress.utils.AppUtil;

import java.util.ArrayList;

import midian.baselib.base.BaseListDataSource;
import midian.baselib.bean.NetResult;

/**
 * Created by chu on 2016/4/21.
 */
public class ExpertsFragmentDataSource extends BaseListDataSource{

    private String carrierid;
    public ExpertsFragmentDataSource(Context context,String carrierid) {
        super(context);
        this.carrierid = carrierid;
    }

    @Override
    protected ArrayList load(int page) throws Exception {
        ArrayList<NetResult> morelist = new ArrayList<NetResult>();
        BusinessCarrierCommentsPageExpertsBean bean = AppUtil.getPpApiClient(ac).businessCarrierCommentsPageExperts(carrierid,null, null);
        if (bean.isOK()) {
            if (bean != null) {
                morelist.addAll(bean.getData().getList());
            }
        }
        hasMore = false;
        return morelist;
    }
}
