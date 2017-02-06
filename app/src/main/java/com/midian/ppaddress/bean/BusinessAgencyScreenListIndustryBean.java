package com.midian.ppaddress.bean;

import com.google.gson.JsonSyntaxException;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import midian.baselib.app.AppException;
import midian.baselib.bean.NetResult;

public class BusinessAgencyScreenListIndustryBean extends NetResult {
    public static BusinessAgencyScreenListIndustryBean parse(String json) throws AppException {
        BusinessAgencyScreenListIndustryBean res = new BusinessAgencyScreenListIndustryBean();
        try {
            res = gson.fromJson(json, BusinessAgencyScreenListIndustryBean.class);
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
            throw AppException.json(e);
        }
        return res;
    }

    private ArrayList<Data> data;
    public ArrayList<Data> getData() {
        return data;
    }

    public void setData(ArrayList<Data> data) {
        this.data = data;
    }

    public class Data extends NetResult {
        private String id;
        private String icon;
        private String industryName;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public String getIndustryName() {
            return industryName;
        }

        public void setIndustryName(String industryName) {
            this.industryName = industryName;
        }
    }
}
