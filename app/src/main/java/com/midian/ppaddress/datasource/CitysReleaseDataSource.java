package com.midian.ppaddress.datasource;

import android.content.Context;

import com.midian.ppaddress.bean.BusinessLocationCityReleaseListBean;
import com.midian.ppaddress.bean.CityMultiItem;
import com.midian.ppaddress.utils.AppUtil;

import java.util.ArrayList;

import midian.baselib.base.BaseListDataSource;

public class CitysReleaseDataSource extends BaseListDataSource<CityMultiItem> {

    int index = 0;

    public CitysReleaseDataSource(Context context) {
        super(context);
    }

    @Override
    protected ArrayList<CityMultiItem> load(int page) throws Exception {
        ArrayList<CityMultiItem> moreList = new ArrayList<CityMultiItem>();

        CityMultiItem item1 = new CityMultiItem();
        item1.setItemViewType(0);
        moreList.add(item1);

        BusinessLocationCityReleaseListBean bean = AppUtil.getPpApiClient(ac).getCityList();
        if (bean != null) {
            if (bean.isOK()) {
                for (BusinessLocationCityReleaseListBean.Data content : bean.getData()) {
                    CityMultiItem item2 = new CityMultiItem();
                    item2.setItemViewType(1);
                    item2.data=content;
                    moreList.add(item2);
                }
            } else {
                ac.handleErrorCode(context, bean.errorcode);
            }
        }

        hasMore = false;
        return moreList;
    }

}
