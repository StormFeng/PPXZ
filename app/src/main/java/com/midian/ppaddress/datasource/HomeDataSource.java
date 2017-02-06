package com.midian.ppaddress.datasource;

import android.content.Context;

import com.midian.ppaddress.bean.AppIndexBean;
import com.midian.ppaddress.bean.IndexMultiItem;
import com.midian.ppaddress.utils.AppUtil;

import java.util.ArrayList;

import midian.baselib.base.BaseFragmentActivity;
import midian.baselib.base.BaseListDataSource;
import midian.baselib.bean.NetResult;
import midian.baselib.shizhefei.view.listviewhelper.IDataSource;

/**
 * 首页data【暂时不用】
 * Created by chu on 2016/2/23.
 */
public class HomeDataSource extends BaseListDataSource {
    public HomeDataSource(Context context) {
        super(context);
    }

    @Override
    protected ArrayList load(int page) throws Exception {
        ArrayList<NetResult> models = new ArrayList<NetResult>();

        AppIndexBean bean= AppUtil.getPpApiClient(ac).businessAppIndex();
        if (bean.isOK()) {
            if (bean != null) {
                IndexMultiItem item1 = new IndexMultiItem();
                item1.setItemViewType(0);
                item1.carrierses = bean.getData().getRecommendCarriers();
                item1.investEnvironUrl = bean.getData().getInvestEnvironUrl();
                models.add(item1);
                for (AppIndexBean.RecommendCounselors counselors : bean.getData().getRecommendCounselors()) {
                    IndexMultiItem item2 = new IndexMultiItem();
                    item2.setItemViewType(1);
                    item2.counselorses= counselors;
                    models.add(item2);
                }
            }
        }
        hasMore = false;
        return models;
    }
}
