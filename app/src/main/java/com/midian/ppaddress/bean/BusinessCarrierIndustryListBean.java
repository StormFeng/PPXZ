package com.midian.ppaddress.bean;

import com.google.gson.JsonSyntaxException;

import java.util.List;

import midian.baselib.app.AppException;
import midian.baselib.bean.NetResult;

/**
 * 首页1
 * Created by chu on 2016/5/8.
 */
public class BusinessCarrierIndustryListBean extends NetResult {
    public static BusinessCarrierIndustryListBean parse(String json) throws AppException {
        BusinessCarrierIndustryListBean res = new BusinessCarrierIndustryListBean();
        try {
            res = gson.fromJson(json, BusinessCarrierIndustryListBean.class);
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
            throw AppException.json(e);
        }
        return res;
    }

    private List<Data> data;

    public List<Data> getData() {
        return data;
    }

    public void setData(List<Data> data) {
        this.data = data;
    }

    public class Data extends NetResult {
        private String id;
        private String industryName;
        public String getId() {
            return id;
        }
        public void setId(String id) {
            this.id = id;
        }

        public String getCity() {
            return industryName;
        }

        public void setCity(String industryName) {
            this.industryName = industryName;
        }
    }
}
