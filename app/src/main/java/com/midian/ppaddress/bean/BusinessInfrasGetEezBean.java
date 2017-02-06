package com.midian.ppaddress.bean;

import com.google.gson.JsonSyntaxException;

import java.util.List;

import midian.baselib.app.AppException;
import midian.baselib.bean.NetResult;

/**
 * 首页1
 * Created by chu on 2016/5/8.
 */
public class BusinessInfrasGetEezBean extends NetResult {
    public static BusinessInfrasGetEezBean parse(String json) throws AppException {
        BusinessInfrasGetEezBean res = new BusinessInfrasGetEezBean();
        try {
            res = gson.fromJson(json, BusinessInfrasGetEezBean.class);
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
        private String name;
        public String getId() {
            return id;
        }
        public void setId(String id) {
            this.id = id;
        }

        public String getCity() {
            return name;
        }

        public void setCity(String name) {
            this.name = name;
        }
    }
}
