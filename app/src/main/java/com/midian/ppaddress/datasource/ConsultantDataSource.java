package com.midian.ppaddress.datasource;

import android.app.Activity;
import android.content.Context;

import com.midian.ppaddress.bean.BusinessCarrierDetailPageCounselorBean;
import com.midian.ppaddress.utils.AppUtil;

import java.util.ArrayList;

import midian.baselib.base.BaseListDataSource;
import midian.baselib.bean.NetResult;
import midian.baselib.shizhefei.view.listviewhelper.IDataSource;

/**
 * 物业顾问列表Datasource
 * Created by chu on 2016/4/25.
 */
public class ConsultantDataSource extends BaseListDataSource{
    private String carrier_id;
    public ConsultantDataSource(Context context, String carrier_id) {
        super(context);
        this.carrier_id = carrier_id;
    }

    @Override
    protected ArrayList load(int page) throws Exception {
        ArrayList<NetResult> morelist = new ArrayList<NetResult>();
        this.page = page;
        BusinessCarrierDetailPageCounselorBean bean = AppUtil.getPpApiClient(ac).businessCarrierDetailPageCounselor(carrier_id, page+"", null);
        if (bean != null) {
            if (bean.isOK()) {
                morelist.addAll(bean.getData().getList());
                if (bean.getData().getList().size()<10) {
                    hasMore = false;
                } else {
                    hasMore = true;
                }
            }
        }
        return morelist;
    }
}
