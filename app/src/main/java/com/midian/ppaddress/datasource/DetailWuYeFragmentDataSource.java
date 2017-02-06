package com.midian.ppaddress.datasource;

import android.content.Context;

import com.midian.ppaddress.bean.BusinessCarrierCommentsPageCounselorsBean;
import com.midian.ppaddress.bean.BusinessCommentsMembersBean;
import com.midian.ppaddress.utils.AppUtil;

import java.util.ArrayList;

import midian.baselib.base.BaseListDataSource;
import midian.baselib.bean.NetResult;

/**
 * Created by chu on 2016/4/21.
 */
public class DetailWuYeFragmentDataSource extends BaseListDataSource {
    private String carrierid;

    public DetailWuYeFragmentDataSource(Context context, String carrierid) {
        super(context);
        this.carrierid = carrierid;
    }

    @Override
    protected ArrayList load(int page) throws Exception {
        ArrayList<NetResult> morelist = new ArrayList<NetResult>();
        BusinessCarrierCommentsPageCounselorsBean bean = AppUtil.getPpApiClient(ac).businessCarrierCommentsPageCounselors(carrierid,null, null);
        if (bean.isOK()) {
            if (bean != null) {
                morelist.addAll(bean.getData().getList());
            }
        }
        hasMore = false;
        return morelist;
    }
}
