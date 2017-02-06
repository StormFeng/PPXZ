package com.midian.ppaddress.bean;

import com.google.gson.JsonSyntaxException;

import java.util.List;

import midian.baselib.app.AppException;
import midian.baselib.bean.NetResult;

/**
 * 8.2选址-地图选址
 * Created by chu on 2016/5/8.
 */
public class BusinessAppsearchSoMapBean extends NetResult {
    public static BusinessAppsearchSoMapBean parse(String json) throws AppException {
        BusinessAppsearchSoMapBean res = new BusinessAppsearchSoMapBean();
        try {
            res = gson.fromJson(json, BusinessAppsearchSoMapBean.class);
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
        private String name;
        private String countyid;
        private String carrierCount;
        private String lng;
        private String lat;
        private String carrierid;

        public String getCarrierid() {
            return carrierid;
        }

        public void setCarrierid(String carrierid) {
            this.carrierid = carrierid;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getCountyid() {
            return countyid;
        }

        public void setCountyid(String countyid) {
            this.countyid = countyid;
        }

        public String getCarrierCount() {
            return carrierCount;
        }

        public void setCarrierCount(String carrierCount) {
            this.carrierCount = carrierCount;
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
    }

}
