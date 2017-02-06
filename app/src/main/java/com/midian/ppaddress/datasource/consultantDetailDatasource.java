package com.midian.ppaddress.datasource;

import android.app.Activity;
import android.content.Context;

import java.util.ArrayList;

import midian.baselib.base.BaseListDataSource;
import midian.baselib.bean.NetResult;
import midian.baselib.shizhefei.view.listviewhelper.IDataSource;

/**
 * Created by chu on 2016/4/27.
 */
public class consultantDetailDatasource extends BaseListDataSource {


    public consultantDetailDatasource(Context context) {
        super(context);
    }

    @Override
    protected ArrayList load(int page) throws Exception {
        ArrayList<NetResult> morelist = new ArrayList<NetResult>();
        NetResult item = new NetResult();
        item.setItemViewType(0);
        morelist.add(item);


        NetResult item1 = new NetResult();
        item1.setItemViewType(1);
        morelist.add(item1);

        for (int i = 0; i < 4; i++) {
            NetResult item2 = new NetResult();
            item2.setItemViewType(2);
            morelist.add(item2);
        }
        hasMore = false;
        return morelist;
    }
}
