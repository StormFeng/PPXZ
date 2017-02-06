package com.midian.ppaddress.datasource;

import android.content.Context;

import java.util.ArrayList;

import com.midian.ppaddress.bean.BusinessCarrierDetailQueryPropertiesBean;
import com.midian.ppaddress.bean.BusinessCarrierDetailQueryPropertiesBean.PropertiesData;
import com.midian.ppaddress.utils.AppUtil;

import midian.baselib.base.BaseListDataSource;

/**
 * Created by chu on 2016/5/26.
 */
public class BasicFragemntDataSource extends BaseListDataSource {

    private String carrierid;

    public BasicFragemntDataSource(Context context, String carrierid) {
        super(context);
        this.carrierid = carrierid;
    }

    @Override
    protected ArrayList load(int page) throws Exception {
        ArrayList<PropertiesData> morelist = new ArrayList<PropertiesData>();
        /*final  ArrayList<PropertiesData> morelist = new ArrayList<PropertiesData>();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    BusinessCarrierDetailQueryPropertiesBean bean = AppUtil.getPpApiClient(BasicFragemntDataSource.this.ac).carrierDetailList(carrierid);
                    if (bean.isOK()) {
                        if (bean != null) {
                            morelist.addAll(bean.getData());
                            notifyAll();
                        }
                    }
                }catch (Exception e){

                }
            }
        }).start();*/
        BusinessCarrierDetailQueryPropertiesBean bean = AppUtil.getPpApiClient(ac).carrierDetailList(carrierid);
        if (bean.isOK()) {
            if (bean != null) {
                morelist.addAll(bean.getData());
            }
        }
        hasMore = false;
        return morelist;
    }
}
