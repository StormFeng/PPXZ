package com.midian.ppaddress.datasource;

import android.content.Context;

import com.midian.ppaddress.bean.BusinessCommentsMembersBean;
import com.midian.ppaddress.utils.AppUtil;

import java.util.ArrayList;

import midian.baselib.base.BaseListDataSource;
import midian.baselib.bean.NetResult;

/**
 * Created by chu on 2016/5/26.
 */
public class MoreCommentDataSource extends BaseListDataSource {

    private String carrierid;

    public MoreCommentDataSource(Context context, String carrierid) {
        super(context);
        this.carrierid = carrierid;
    }

    @Override
    protected ArrayList load(int page) throws Exception {
        this.page=page;
        ArrayList<NetResult> morelist = new ArrayList<NetResult>();
        BusinessCommentsMembersBean bean = AppUtil.getPpApiClient(ac).businessCarrierCommentsPageMembers(carrierid, page+"", "10");
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

