package com.midian.ppaddress.bean;

import com.google.gson.JsonSyntaxException;

import java.util.List;

import midian.baselib.app.AppException;
import midian.baselib.bean.NetResult;

/**
 * Created by chu on 2016/5/6.
 */
public class BusinessCarrierDetailMapTrafficsBean extends NetResult {
    public static BusinessCarrierDetailMapTrafficsBean parse(String json) throws AppException {
        BusinessCarrierDetailMapTrafficsBean res = new BusinessCarrierDetailMapTrafficsBean();
        try {
            res = gson.fromJson(json, BusinessCarrierDetailMapTrafficsBean.class);
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
            throw AppException.json(e);
        }
        return res;
    }

    private TrafficsData data;


    public TrafficsData getData() {
        return data;
    }

    public void setData(TrafficsData data) {
        this.data = data;
    }

    public class TrafficsData extends NetResult {
        private List<Traffics> traffics;
        private Carrier carrier;

        public List<Traffics> getTraffics() {
            return traffics;
        }

        public void setTraffics(List<Traffics> traffics) {
            this.traffics = traffics;
        }

        public Carrier getCarrier() {
            return carrier;
        }

        public void setCarrier(Carrier carrier) {
            this.carrier = carrier;
        }


    }
    public class Traffics extends NetResult{
        private String trafficsid;
        private String trafficsName;
        private String trafficstype;
        private String trafficstypeid;
        private String lng;
        private String lat;

        public String getTrafficsid() {
            return trafficsid;
        }

        public void setTrafficsid(String trafficsid) {
            this.trafficsid = trafficsid;
        }

        public String getTrafficsName() {
            return trafficsName;
        }

        public void setTrafficsName(String trafficsName) {
            this.trafficsName = trafficsName;
        }

        public String getTrafficstype() {
            return trafficstype;
        }

        public void setTrafficstype(String trafficstype) {
            this.trafficstype = trafficstype;
        }

        public String getTrafficstypeid() {
            return trafficstypeid;
        }

        public void setTrafficstypeid(String trafficstypeid) {
            this.trafficstypeid = trafficstypeid;
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
    public class Carrier extends NetResult{
        private String carrierid;
        private String shortName;
        private String carrierType;
        private String lng;
        private String lat;

        public String getCarrierid() {
            return carrierid;
        }

        public void setCarrierid(String carrierid) {
            this.carrierid = carrierid;
        }

        public String getShortName() {
            return shortName;
        }

        public void setShortName(String shortName) {
            this.shortName = shortName;
        }

        public String getCarrierType() {
            return carrierType;
        }

        public void setCarrierType(String carrierType) {
            this.carrierType = carrierType;
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
