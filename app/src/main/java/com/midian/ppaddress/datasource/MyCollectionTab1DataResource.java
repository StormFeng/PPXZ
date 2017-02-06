package com.midian.ppaddress.datasource;

import android.content.Context;

import com.midian.ppaddress.bean.MemberFavoritePageCarrierBean;
import com.midian.ppaddress.bean.MemberFavoritePageCarrierBean.CarrierCollectList;
import com.midian.ppaddress.utils.AppUtil;

import java.util.ArrayList;

import midian.baselib.base.BaseListDataSource;

/**
 * Created by chu on 2016/2/23.
 */
public class MyCollectionTab1DataResource extends BaseListDataSource {
    private int pageSize=10;

    public MyCollectionTab1DataResource(Context context) {
        super(context);
    }

    @Override
    protected ArrayList load(int page) throws Exception {
        ArrayList<CarrierCollectList> models = new ArrayList<CarrierCollectList>();
        this.page=page;
        MemberFavoritePageCarrierBean bean= AppUtil.getPpApiClient(ac).getMemberFavoritePageCarrier(page,pageSize);
        if (bean.isOK()) {
            models.addAll(bean.getData().getList());
            if (bean.getData().getList().size() < 10) {
                hasMore = false;
            } else {
                hasMore = true;
            }
        } else {
            ac.handleErrorCode(context, bean.errorcode);
        }
        return models;
    }
}
