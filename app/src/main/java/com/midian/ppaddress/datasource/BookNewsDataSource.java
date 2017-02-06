package com.midian.ppaddress.datasource;

import android.content.Context;

import com.midian.ppaddress.bean.AppIndexBean;
import com.midian.ppaddress.bean.MemberMessagePageOrderMessagesBean;
import com.midian.ppaddress.utils.AppUtil;

import java.util.ArrayList;

import midian.baselib.base.BaseListDataSource;
import midian.baselib.bean.NetResult;

/**
 * Created by chu on 2016/2/23.
 */
public class BookNewsDataSource extends BaseListDataSource {
    private int pageSize=10;
    public BookNewsDataSource(Context context) {
        super(context);
    }

    @Override
    protected ArrayList load(int page) throws Exception {
        this.page=page;
        ArrayList<NetResult> models = new ArrayList<>();
        MemberMessagePageOrderMessagesBean bean= AppUtil.getPpApiClient(ac).getMemberMessagePageOrderMessages(ac.user_id,ac.getProperty("user_type"),page,pageSize);
        if(bean.isOK()) {
            models.addAll(bean.getData().getList());
            if (bean.getData().getList().size() < 10) {
                hasMore = false;
            } else {
                hasMore = true;
            }
        }
        return models;
    }
}
