package com.midian.ppaddress.datasource;

import android.content.Context;

import java.util.ArrayList;

import midian.baselib.base.BaseFragmentActivity;
import midian.baselib.base.BaseListDataSource;
import midian.baselib.bean.NetResult;
import midian.baselib.shizhefei.view.listviewhelper.IDataSource;

/**
 * Created by chu on 2016/4/28.
 */
public class ConsultantAgentDataSource extends BaseListDataSource{
    public ConsultantAgentDataSource(Context context) {
        super(context);
    }

    @Override
    protected ArrayList load(int page) throws Exception {
        ArrayList<NetResult> morelist = new ArrayList<NetResult>();
        morelist.add(new NetResult());
        morelist.add(new NetResult());
        morelist.add(new NetResult());
        morelist.add(new NetResult());
        return morelist;
    }
}
