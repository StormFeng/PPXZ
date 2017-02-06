package com.midian.ppaddress.bean;

import com.google.gson.JsonSyntaxException;

import java.util.List;

import midian.baselib.app.AppException;
import midian.baselib.bean.NetResult;

/**
 * 13.1获取发布城市列表
 * Created by chu on 2016/5/8.
 */
public class BusinessLocationCityReleaseListBean extends NetResult {
    public static BusinessLocationCityReleaseListBean parse(String json) throws AppException {
        BusinessLocationCityReleaseListBean res = new BusinessLocationCityReleaseListBean();
        try {
            res = gson.fromJson(json, BusinessLocationCityReleaseListBean.class);
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
        private String id;//": 200,
        private String lng;//": "113.28064",
        private String lat;//": "23.125177",
        private String city;//": "广州

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getLng() {
            return lng;
        }

        public void setLng(String lng) {
            this.lng = lng;
        }

        public String getLat() {
            return lat;
        }

        public void setLat(String lat) {
            this.lat = lat;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }
    }
}
