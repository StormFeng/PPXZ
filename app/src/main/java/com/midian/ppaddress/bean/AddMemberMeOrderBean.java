package com.midian.ppaddress.bean;

import com.google.gson.JsonSyntaxException;

import midian.baselib.app.AppException;
import midian.baselib.bean.NetResult;

/**
 * 15.6个人中心-我（普通客商）的评论-对物业顾问的评论-新增评论页
 * Created by chu on 2016/5/9.
 */
public class AddMemberMeOrderBean extends NetResult {
    public static AddMemberMeOrderBean parse(String json) throws AppException {
        AddMemberMeOrderBean res = new AddMemberMeOrderBean();
        try {
            res = gson.fromJson(json, AddMemberMeOrderBean.class);
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
        private String id;//": 1,
        private String counselorid;//": 54,
        private String counselorFullname;//": "老总",
        private String counselorPortrait;//": "http://xxx/28f.jpg",
        private String agencyCompany;//": "优斯",
        private String carrierid;//": 77,
        private String carrieruuid;//": "39218f89731e47b6a5ffa65f0e953639",
        private String carrierType;//": 4,
        private String carrierName;//": "金地香山湖写字楼",
        private String images;//": "http://xxx/ca2.jpeg",
        private String floor;//": "30
        private String property;//": "住宅用地",
        private String landArea;//": 1
        private String saleRental;//": "",
        private String priceRent;//": "",
        private String priceSell;//": "",
        private String buildingArea;//

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getCounselorid() {
            return counselorid;
        }

        public void setCounselorid(String counselorid) {
            this.counselorid = counselorid;
        }

        public String getCounselorFullname() {
            return counselorFullname;
        }

        public void setCounselorFullname(String counselorFullname) {
            this.counselorFullname = counselorFullname;
        }

        public String getCounselorPortrait() {
            return counselorPortrait;
        }

        public void setCounselorPortrait(String counselorPortrait) {
            this.counselorPortrait = counselorPortrait;
        }

        public String getAgencyCompany() {
            return agencyCompany;
        }

        public void setAgencyCompany(String agencyCompany) {
            this.agencyCompany = agencyCompany;
        }

        public String getCarrierid() {
            return carrierid;
        }

        public void setCarrierid(String carrierid) {
            this.carrierid = carrierid;
        }

        public String getCarrieruuid() {
            return carrieruuid;
        }

        public void setCarrieruuid(String carrieruuid) {
            this.carrieruuid = carrieruuid;
        }

        public String getCarrierType() {
            return carrierType;
        }

        public void setCarrierType(String carrierType) {
            this.carrierType = carrierType;
        }

        public String getCarrierName() {
            return carrierName;
        }

        public void setCarrierName(String carrierName) {
            this.carrierName = carrierName;
        }

        public String getImages() {
            return images;
        }

        public void setImages(String images) {
            this.images = images;
        }

        public String getFloor() {
            return floor;
        }

        public void setFloor(String floor) {
            this.floor = floor;
        }

        public String getProperty() {
            return property;
        }

        public void setProperty(String property) {
            this.property = property;
        }

        public String getLandArea() {
            return landArea;
        }

        public void setLandArea(String landArea) {
            this.landArea = landArea;
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
}
