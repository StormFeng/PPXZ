package com.midian.ppaddress.datasource;

import android.content.Context;

import com.midian.ppaddress.bean.CityMultiItem;
import com.midian.login.bean.ProvincesBean.City;

import java.util.ArrayList;
import java.util.List;

import midian.baselib.base.BaseListDataSource;

public class ChooseAreaDataSource extends BaseListDataSource<CityMultiItem> {
    private List<City> city = new ArrayList<City>();

    public ChooseAreaDataSource(Context context, List<City> city) {
        super(context);
        this.city = city;
    }

    @Override
    protected ArrayList<CityMultiItem> load(int page) throws Exception {
        ArrayList<CityMultiItem> moreList = new ArrayList<CityMultiItem>();

        for (City bean : city) {
            CityMultiItem item = new CityMultiItem();
            item.city = bean;
            moreList.add(item);
        }
        hasMore = false;
        return moreList;
    }
}
