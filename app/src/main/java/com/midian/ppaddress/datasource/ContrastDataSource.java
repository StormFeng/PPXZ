package com.midian.ppaddress.datasource;

import android.content.Context;

import com.midian.ppaddress.bean.MemberCompareListBean;
import com.midian.ppaddress.bean.MemberCompareListBean.CompareData;
import com.midian.ppaddress.utils.AppUtil;

import java.util.ArrayList;

import midian.baselib.base.BaseListDataSource;
import midian.baselib.bean.NetResult;

/**
 * 对比DataSource
 * Created by chu on 2016/4/25.
 */
public class ContrastDataSource extends BaseListDataSource {

    private String carriertype;

    public ContrastDataSource(Context context, String carriertype) {
        super(context);
        this.carriertype = carriertype;
    }

    @Override
    protected ArrayList load(int page) throws Exception {
        ArrayList<NetResult> morelist = new ArrayList<NetResult>();
        MemberCompareListBean bean=AppUtil.getPpApiClient(ac).compareList(carriertype);
        if (bean != null) {
            if (bean.isOK()) {
                morelist.addAll(bean.getData());
            }
        }
        hasMore = false;
        return morelist;
    }
}
