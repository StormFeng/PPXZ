package com.midian.ppaddress.datasource;

import android.content.Context;

import com.midian.ppaddress.bean.BusinessCarrierDetailPageUnitsBean;
import com.midian.ppaddress.utils.AppUtil;

import java.util.ArrayList;

import midian.baselib.base.BaseListDataSource;
import midian.baselib.bean.NetResult;

/**
 * Created by chu on 2016/4/22.
 */
public class OptionalUnitDataSource extends BaseListDataSource {
    private String carrierid;

    public OptionalUnitDataSource(Context context, String carrierid) {
        super(context);
        this.carrierid = carrierid;
    }

    @Override
    protected ArrayList load(int page) throws Exception {
        ArrayList<NetResult> morelist = new ArrayList<NetResult>();
        BusinessCarrierDetailPageUnitsBean bean = AppUtil.getPpApiClient(ac).businessCarrierDetailPageUnits(carrierid, ac.user_type, "1", "10");
        if (bean.isOK()) {
            if (bean != null) {
                morelist.addAll(bean.getData().getList());
            }
        }
        hasMore = false;
        return morelist;
    }
}

