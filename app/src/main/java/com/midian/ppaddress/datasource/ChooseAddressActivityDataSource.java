package com.midian.ppaddress.datasource;

import android.content.Context;

import com.midian.ppaddress.app.Constant;
import com.midian.ppaddress.bean.BusinessCarrierDetailGetChildrenCarriersBean;
import com.midian.ppaddress.utils.AppUtil;

import java.util.ArrayList;

import midian.baselib.base.BaseListDataSource;
import midian.baselib.bean.NetResult;

/**
 * Created by chu on 2016/4/21.
 */
public class ChooseAddressActivityDataSource extends BaseListDataSource {
    private String carrierid;
    public ChooseAddressActivityDataSource(Context context, String carrierid) {
        super(context);
        this.carrierid = carrierid;
    }

    @Override
    protected ArrayList load(int page) throws Exception {
        ArrayList<NetResult> morelist = new ArrayList<NetResult>();
        this.page = page;
        BusinessCarrierDetailGetChildrenCarriersBean bean=AppUtil.getPpApiClient(ac).chooseCarrierList(carrierid,this.page+"", Constant.SIZE);
        if (bean != null) {
            if (bean.isOK()) {
                morelist.addAll(bean.getData().getList());
                if ( bean.getData().getList().size() == 0) {
                    hasMore = false;
                } else {
                    hasMore = true;
                }
            } else {
                ac.handleErrorCode(context, bean.errorcode);
            }
        }
        return morelist;
    }
}
