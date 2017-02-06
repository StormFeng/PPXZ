package com.midian.ppaddress.datasource;

import android.content.Context;

import com.midian.ppaddress.bean.BusinessLocationProvincesBean;
import com.midian.ppaddress.utils.AppUtil;

import java.util.ArrayList;

import midian.baselib.base.BaseListDataSource;
import midian.baselib.bean.NetResult;

public class ChooseCitysDataSource extends BaseListDataSource{


    public ChooseCitysDataSource(Context context) {
        super(context);
    }

    @Override
    protected ArrayList load(int page) throws Exception {
        ArrayList<NetResult> moreList = new ArrayList<>();
        BusinessLocationProvincesBean bean = AppUtil.getPpApiClient(ac).getprovincesBean();
        if (bean.isOK()) {
                moreList.addAll(bean.getData());
        } else {
            ac.handleErrorCode(context, bean.errorcode);
        }
        hasMore = false;
        return moreList;
    }
}
