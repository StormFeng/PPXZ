package com.midian.ppaddress.bean;

import com.google.gson.JsonSyntaxException;

import midian.baselib.app.AppException;
import midian.baselib.bean.NetResult;

/**
 * 17.4选址-写字楼详情-申请入驻
 * Created by chu on 2016/5/9.
 */
public class EntercarrierShowBean extends NetResult {
    public static EntercarrierShowBean parse(String json) throws AppException {
        EntercarrierShowBean res = new EntercarrierShowBean();
        try {
            res = gson.fromJson(json, EntercarrierShowBean.class);
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
            throw AppException.json(e);
        }
        return res;
    }

    private Data data;

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public class Data extends NetResult {
        private Carrier carrier;
        private Counselor counselor;

        public Carrier getCarrier() {
            return carrier;
        }

        public void setCarrier(Carrier carrier) {
            this.carrier = carrier;
        }

        public Counselor getCounselor() {
            return counselor;
        }

        public void setCounselor(Counselor counselor) {
            this.counselor = counselor;
        }
    }


    /**
     * 载体信息
     */
    public class Carrier extends NetResult {
        private String carrierid;//": 24,
        private String carrierType;//": 4,
        private String carrierName;//": "敏捷·御峰国际",
        private String images;//": "http://xxx.jpg",
        //园区、综合体、写字楼共有字段
        private String buildingArea;//": 20待出售面积
        private String saleRental;//": 1,
        private String priceRent;//": 20,
        private String priceSell;//": 30,

        //土地特有的
        private String landArea;//土地面积
        private String property;//用地性质
        //厂房、仓库共有字段
        private String floor;//	层高

        public String getLandArea() {
            return landArea;
        }

        public void setLandArea(String landArea) {
            this.landArea = landArea;
        }

        public String getProperty() {
            return property;
        }

        public void setProperty(String property) {
            this.property = property;
        }

        public String getFloor() {
            return floor;
        }

        public void setFloor(String floor) {
            this.floor = floor;
        }

        public String getSaleRental() {
            return saleRental;
        }

        public void setSaleRental(String saleRental) {
            this.saleRental = saleRental;
        }

        public String getPriceRent() {
            return priceRent;
        }

        public void setPriceRent(String priceRent) {
            this.priceRent = priceRent;
        }

        public String getCarrierType() {
            return carrierType;
        }

        public void setCarrierType(String carrierType) {
            this.carrierType = carrierType;
        }

        public String getImages() {
            return images;
        }

        public void setImages(String images) {
            this.images = images;
        }

        public String getCarrierName() {
            return carrierName;
        }

        public void setCarrierName(String carrierName) {
            this.carrierName = carrierName;
        }

        public String getCarrierid() {
            return carrierid;
        }

        public void setCarrierid(String carrierid) {
            this.carrierid = carrierid;
        }

        public String getPriceSell() {
            return priceSell;
        }

        public void setPriceSell(String priceSell) {
            this.priceSell = priceSell;
        }

        public String getBuildingArea() {
            return buildingArea;
        }

        public void setBuildingArea(String buildingArea) {
            this.buildingArea = buildingArea;
        }
    }

    /**
     * 个人信息
     */
    public class Counselor extends NetResult {
        private String mobilephone;
        private String counselorid;
        private String fullname;

        public String getMobilephone() {
            return mobilephone;
        }

        public void setMobilephone(String mobilephone) {
            this.mobilephone = mobilephone;
        }

        public String getCounselorid() {
            return counselorid;
        }

        public void setCounselorid(String counselorid) {
            this.counselorid = counselorid;
        }

        public String getFullname() {
            return fullname;
        }

        public void setFullname(String fullname) {
            this.fullname = fullname;
        }
    }
}
