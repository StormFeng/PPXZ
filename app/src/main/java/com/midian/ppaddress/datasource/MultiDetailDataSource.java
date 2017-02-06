package com.midian.ppaddress.datasource;

import android.content.Context;

import com.midian.ppaddress.bean.BusinessCarrierDetailShowBean;
import com.midian.ppaddress.bean.BusinessCarrierDetailShowBean.CarrierDetailData;
import com.midian.ppaddress.bean.DetailMultiItemBean;
import com.midian.ppaddress.utils.AppUtil;

import java.util.ArrayList;

import midian.baselib.base.BaseListDataSource;
import midian.baselib.bean.NetResult;

/**
 * 载体详情dataSource
 * Created by chu on 2016/4/22.
 */
public class MultiDetailDataSource extends BaseListDataSource {
    private String carrierid;

    public MultiDetailDataSource(Context context, String carrierid) {
        super(context);
        this.carrierid = carrierid;
    }

    @Override
    protected ArrayList load(int page) throws Exception {
        ArrayList<DetailMultiItemBean> morelist = new ArrayList<DetailMultiItemBean>();
        BusinessCarrierDetailShowBean bean = AppUtil.getPpApiClient(ac).businessCarrierDetailShow(carrierid, ac.user_id, ac.getProperty("user_type"));
        if (bean != null) {
            if (bean.isOK()) {
                DetailMultiItemBean item0 = new DetailMultiItemBean();
                item0.setItemViewType(0);
                item0.detailData = bean.getData();
                morelist.add(item0);
                DetailMultiItemBean item1 = new DetailMultiItemBean();
                item1.setItemViewType(1);
                item1.detailData = bean.getData();
                morelist.add(item1);
                DetailMultiItemBean item2 = new DetailMultiItemBean();
                item2.setItemViewType(2);
                item2.detailData = bean.getData();
                morelist.add(item2);
            }
        }
        hasMore = false;
        return morelist;
    }
}
