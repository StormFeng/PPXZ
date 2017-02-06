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
public class CommentFragmentDataSource extends BaseListDataSource {

    private String carrierid;

    public CommentFragmentDataSource(Context context, String carrierid) {
        super(context);
        this.carrierid = carrierid;
    }

    @Override
    protected ArrayList load(int page) throws Exception {
        ArrayList<NetResult> morelist = new ArrayList<NetResult>();
        BusinessCommentsMembersBean bean = AppUtil.getPpApiClient(ac).businessCarrierCommentsPageMembers(carrierid, null, null);
        if (bean.isOK()) {
            if (bean != null) {
                morelist.addAll(bean.getData().getList());
            }
        }
        hasMore = false;
        return morelist;
    }
}

