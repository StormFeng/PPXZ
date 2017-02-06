package com.midian.ppaddress.bean;

import com.google.gson.JsonSyntaxException;

import java.util.List;

import midian.baselib.app.AppException;
import midian.baselib.bean.NetResult;

/**
 * Created by chu on 2016/5/6.
 */
public class MemberCompareDetailComplexBean extends NetResult {
    public static MemberCompareDetailComplexBean parse(String json) throws AppException {
        MemberCompareDetailComplexBean res = new MemberCompareDetailComplexBean();
        try {
            res = gson.fromJson(json, MemberCompareDetailComplexBean.class);
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
        private String province;
        private String city;
        private String street;
        private String supports;
        private String labels;
        private String intention;
        private String traffics;
        private String complexPriceRent;
        private String complexGasSupply;
        private String complexWaterSupply;
        private String complexInvestment;
        private String complexName;
        private String complexParkingSpaceCount;
        private String complexPriceManage;
        private String complexSaleRental;
        private String complexNetworkDesc;
        private String complexLandArea;
        private String complexBuildingArea;
        private String complexIsNetwork;
        private String complexPowerSupply;
        private String complexIsParkingSpace;
        private String complexPriceSell;
        private String complexDisposeSewage;
        private String complexEnterCompany;
        private String carrierid;

        public String getProvince() {
            return province;
        }

        public void setProvince(String province) {
            this.province = province;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getStreet() {
            return street;
        }

        public void setStreet(String street) {
            this.street = street;
        }

        public String getSupports() {
            return supports;
        }

        public void setSupports(String supports) {
            this.supports = supports;
        }

        public String getLabels() {
            return labels;
        }

        public void setLabels(String labels) {
            this.labels = labels;
        }

        public String getIntention() {
            return intention;
        }

        public void setIntention(String intention) {
            this.intention = intention;
        }

        public String getTraffics() {
            return traffics;
        }

        public void setTraffics(String traffics) {
            this.traffics = traffics;
        }

        public String getComplexPriceRent() {
            return complexPriceRent;
        }

        public void setComplexPriceRent(String complexPriceRent) {
            this.complexPriceRent = complexPriceRent;
        }

        public String getComplexGasSupply() {
            return complexGasSupply;
        }

        public void setComplexGasSupply(String complexGasSupply) {
            this.complexGasSupply = complexGasSupply;
        }

        public String getComplexWaterSupply() {
            return complexWaterSupply;
        }

        public void setComplexWaterSupply(String complexWaterSupply) {
            this.complexWaterSupply = complexWaterSupply;
        }

        public String getComplexInvestment() {
            return complexInvestment;
        }

        public void setComplexInvestment(String complexInvestment) {
            this.complexInvestment = complexInvestment;
        }

        public String getComplexName() {
            return complexName;
        }

        public void setComplexName(String complexName) {
            this.complexName = complexName;
        }

        public String getComplexParkingSpaceCount() {
            return complexParkingSpaceCount;
        }

        public void setComplexParkingSpaceCount(String complexParkingSpaceCount) {
            this.complexParkingSpaceCount = complexParkingSpaceCount;
        }

        public String getComplexPriceManage() {
            return complexPriceManage;
        }

        public void setComplexPriceManage(String complexPriceManage) {
            this.complexPriceManage = complexPriceManage;
        }

        public String getComplexSaleRental() {
            return complexSaleRental;
        }

        public void setComplexSaleRental(String complexSaleRental) {
            this.complexSaleRental = complexSaleRental;
        }

        public String getComplexNetworkDesc() {
            return complexNetworkDesc;
        }

        public void setComplexNetworkDesc(String complexNetworkDesc) {
            this.complexNetworkDesc = complexNetworkDesc;
        }

        public String getComplexLandArea() {
            return complexLandArea;
        }

        public void setComplexLandArea(String complexLandArea) {
            this.complexLandArea = complexLandArea;
        }

        public String getComplexBuildingArea() {
            return complexBuildingArea;
        }

        public void setComplexBuildingArea(String complexBuildingArea) {
            this.complexBuildingArea = complexBuildingArea;
        }

        public String getComplexIsNetwork() {
            return complexIsNetwork;
        }

        public void setComplexIsNetwork(String complexIsNetwork) {
            this.complexIsNetwork = complexIsNetwork;
        }

        public String getComplexPowerSupply() {
            return complexPowerSupply;
        }

        public void setComplexPowerSupply(String complexPowerSupply) {
            this.complexPowerSupply = complexPowerSupply;
        }

        public String getComplexIsParkingSpace() {
            return complexIsParkingSpace;
        }

        public void setComplexIsParkingSpace(String complexIsParkingSpace) {
            this.complexIsParkingSpace = complexIsParkingSpace;
        }

        public String getComplexPriceSell() {
            return complexPriceSell;
        }

        public void setComplexPriceSell(String complexPriceSell) {
            this.complexPriceSell = complexPriceSell;
        }

        public String getComplexDisposeSewage() {
            return complexDisposeSewage;
        }

        public void setComplexDisposeSewage(String complexDisposeSewage) {
            this.complexDisposeSewage = complexDisposeSewage;
        }

        public String getComplexEnterCompany() {
            return complexEnterCompany;
        }

        public void setComplexEnterCompany(String complexEnterCompany) {
            this.complexEnterCompany = complexEnterCompany;
        }

        public String getCarrierid() {
            return carrierid;
        }

        public void setCarrierid(String carrierid) {
            this.carrierid = carrierid;
        }
    }
}
