package com.midian.ppaddress.datasource;

import android.content.Context;

import com.midian.ppaddress.app.Constant;
import com.midian.ppaddress.bean.BusinessCarrierDetailPageUnitsBean;
import com.midian.ppaddress.utils.AppUtil;

import java.util.ArrayList;

import midian.baselib.base.BaseListDataSource;
import midian.baselib.bean.NetResult;

/**
 * Created by chu on 2016/4/22.
 */
public class MoreOptionalUnitDataSource extends BaseListDataSource {
    private String carrierid;

    public MoreOptionalUnitDataSource(Context context, String carrierid) {
        super(context);
        this.carrierid = carrierid;
    }

    @Override
    protected ArrayList load(int page) throws Exception {
        ArrayList<NetResult> morelist = new ArrayList<NetResult>();
        this.page = page;
        BusinessCarrierDetailPageUnitsBean bean = AppUtil.getPpApiClient(ac).businessCarrierDetailPageUnits(carrierid, ac.user_type, this.page+"", Constant.SIZE);
        if (bean.isOK()) {
            if (bean != null) {
                System.out.println("可选单元的bean。size=" + bean.getData().getList().size());
                morelist.addAll(bean.getData().getList());
                if ( bean.getData().getList().size() == 0) {
                    hasMore = false;
                } else {
                    hasMore = true;
                }
            }
        }
        return morelist;
    }
}

