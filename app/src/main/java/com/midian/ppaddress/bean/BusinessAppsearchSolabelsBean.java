package com.midian.ppaddress.bean;

import com.google.gson.JsonSyntaxException;

import java.util.List;

import midian.baselib.app.AppException;
import midian.baselib.bean.NetResult;

/**
 * Created by chu on 2016/5/6.
 */
public class BusinessAppsearchSolabelsBean extends NetResult {
    public static BusinessAppsearchSolabelsBean parse(String json) throws AppException {
        BusinessAppsearchSolabelsBean res = new BusinessAppsearchSolabelsBean();
        try {
            res = gson.fromJson(json, BusinessAppsearchSolabelsBean.class);
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
        private String id;//标签ID
        private String label;//标签名称

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getLabel() {
            return label;
        }

        public void setLabel(String label) {
            this.label = label;
        }
    }
}
