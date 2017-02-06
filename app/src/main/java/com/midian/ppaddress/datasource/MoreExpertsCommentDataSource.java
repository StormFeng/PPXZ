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
public class MoreExpertsCommentDataSource extends BaseListDataSource{

    private String carrierid;
    public MoreExpertsCommentDataSource(Context context, String carrierid) {
        super(context);
        this.carrierid = carrierid;
    }

    @Override
    protected ArrayList load(int page) throws Exception {
        this.page=page;
        ArrayList<NetResult> morelist = new ArrayList<NetResult>();
        BusinessCarrierCommentsPageExpertsBean bean = AppUtil.getPpApiClient(ac).businessCarrierCommentsPageExperts(carrierid, page + "", "10");
        if (bean.isOK()) {
            morelist.addAll(bean.getData().getList());
            if (bean.getData().getList().size() < 10) {
                hasMore = false;
            } else {
                hasMore = true;
            }
        }
        return morelist;
    }
}
