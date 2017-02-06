package com.midian.ppaddress.bean;

import com.google.gson.JsonSyntaxException;

import java.util.List;

import midian.baselib.app.AppException;
import midian.baselib.bean.NetResult;

/**
 * 首页1
 * Created by chu on 2016/5/8.
 */
public class BusinessInfrasListEezBean extends NetResult {
    public static BusinessInfrasListEezBean parse(String json) throws AppException {
        BusinessInfrasListEezBean res = new BusinessInfrasListEezBean();
        try {
            res = gson.fromJson(json, BusinessInfrasListEezBean.class);
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
