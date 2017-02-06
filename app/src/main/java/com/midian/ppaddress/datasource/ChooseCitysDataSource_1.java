package com.midian.ppaddress.datasource;

import android.content.Context;

import com.midian.ppaddress.bean.BusinessLocationProvinceCitysBean;
import com.midian.ppaddress.utils.AppUtil;

import java.util.ArrayList;

import midian.baselib.base.BaseListDataSource;
import midian.baselib.bean.NetResult;

public class ChooseCitysDataSource_1 extends BaseListDataSource{

    private String cityid;


    public ChooseCitysDataSource_1(Context context,String cityid) {
        super(context);
        this.cityid=cityid;
    }

    @Override
    protected ArrayList load(int page) throws Exception {
        ArrayList<NetResult> moreList = new ArrayList<>();
        BusinessLocationProvinceCitysBean bean=AppUtil.getPpApiClient(ac).getprovincecitysBean(cityid);
        if(bean.isOK()){
            moreList.addAll(bean.getData());
        }
        hasMore = false;
        return moreList;
    }
}
