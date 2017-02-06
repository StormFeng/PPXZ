package com.midian.ppaddress.datasource;

import android.content.Context;

import java.util.ArrayList;

import midian.baselib.base.BaseListDataSource;
import midian.baselib.bean.NetResult;

/**
 * Created by chu on 2016/2/23.
 */
public class BookFragment_1DataResource extends BaseListDataSource {
    public BookFragment_1DataResource(Context context) {
        super(context);
    }

    @Override
    protected ArrayList load(int page) throws Exception {
        ArrayList<NetResult> models = new ArrayList<NetResult>();
        NetResult result1 = new NetResult();
        result1.setItemViewType(0);
        models.add(result1);
        for (int i = 0; i < 3; i++) {
            NetResult result2 = new NetResult();
            result2.setItemViewType(1);
            models.add(result2);
        }
        hasMore = false;
        return models;
    }
}
