package com.midian.ppaddress.datasource;

import android.content.Context;

import com.midian.ppaddress.bean.BusinessLocationCityCountysBean;
import com.midian.ppaddress.bean.BusinessLocationProvinceCitysBean;
import com.midian.ppaddress.utils.AppUtil;

import java.util.ArrayList;

import midian.baselib.base.BaseListDataSource;
import midian.baselib.bean.NetResult;

public class ChooseCitysDataSource_2 extends BaseListDataSource{

    private String cityid;


    public ChooseCitysDataSource_2(Context context, String cityid) {
        super(context);
        this.cityid=cityid;
    }

    @Override
    protected ArrayList load(int page) throws Exception {
        ArrayList<NetResult> moreList = new ArrayList<>();
        BusinessLocationCityCountysBean bean=AppUtil.getPpApiClient(ac).getcityCountysBean(cityid);
        if(bean.isOK()){
            moreList.addAll(bean.getData());
        }
        hasMore = false;
        return moreList;
    }
}
