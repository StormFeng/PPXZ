package com.midian.ppaddress.bean;

import com.google.gson.JsonSyntaxException;

import java.util.List;

import midian.baselib.app.AppException;
import midian.baselib.bean.NetResult;

/**
 * 16.1选址-载体详情-一键预约页
 * Created by chu on 2016/5/9.
 */
public class MemberOrderMemberShowBean extends NetResult {
    public static MemberOrderMemberShowBean parse(String json) throws AppException {
        MemberOrderMemberShowBean res = new MemberOrderMemberShowBean();
        try {
            res = gson.fromJson(json, MemberOrderMemberShowBean.class);
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
        private Member member;
        private Carrier carrier;
        private List<Counselors> counselors;

        public Member getMember() {
            return member;
        }

        public void setMember(Member member) {
            this.member = member;
        }

        public Carrier getCarrier() {
            return carrier;
        }

        public void setCarrier(Carrier carrier) {
            this.carrier = carrier;
        }

        public List<Counselors> getCounselors() {
            return counselors;
        }

        public void setCounselors(List<Counselors> counselors) {
            this.counselors = counselors;
        }
    }

    public class Member extends NetResult {
        private String mobilephone;//": "13827864920",
        private String memberid;//": 53

        public String getMobilephone() {
            return mobilephone;
        }

        public void setMobilephone(String mobilephone) {
            this.mobilephone = mobilephone;
        }

        public String getMemberid() {
            return memberid;
        }

        public void setMemberid(String memberid) {
            this.memberid = memberid;
        }
    }

    public class Carrier extends NetResult {
        private String landArea;//": 10,
        private String saleRental;//": 1,
        private String priceRent;//": 20,
        private String county;//": "黄埔",
        private String carrierType;//": 4,
        private String images;//": "http://1c166fe4155d.jpg",
        private String carrierName;//": "敏捷·御峰国际",
        private String priceSell;//": 30,
        private String buildingArea;//": 20,
        private String city;//": "广州
        private String property;//": "",
        private String carrierid;//": 50
        private String floor;//

        public String getProperty() {
            return property;
        }

        public void setProperty(String property) {
            this.property = property;
        }

        public String getCarrierid() {
            return carrierid;
        }

        public void setCarrierid(String carrierid) {
            this.carrierid = carrierid;
        }

        public String getFloor() {
            return floor;
        }

        public void setFloor(String floor) {
            this.floor = floor;
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

        public String getCounty() {
            return county;
        }

        public void setCounty(String county) {
            this.county = county;
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

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }
    }

    public class Counselors extends NetResult {
        private String portrait;//": "http://i435f3972628f.jpg",
        private String counselorid;//": 54,
        private String fullname;//": "姚总

        public String getPortrait() {
            return portrait;
        }

        public void setPortrait(String portrait) {
            this.portrait = portrait;
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
