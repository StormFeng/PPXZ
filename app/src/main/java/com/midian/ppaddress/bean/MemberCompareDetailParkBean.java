package com.midian.ppaddress.bean;

import com.google.gson.JsonSyntaxException;

import java.util.List;

import midian.baselib.app.AppException;
import midian.baselib.bean.NetResult;

/**
 * Created by chu on 2016/5/6.
 */
public class MemberCompareDetailParkBean extends NetResult {
    public static MemberCompareDetailParkBean parse(String json) throws AppException {
        MemberCompareDetailParkBean res = new MemberCompareDetailParkBean();
        try {
            res = gson.fromJson(json, MemberCompareDetailParkBean.class);
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
        private String parkPriceSell;
        private String parkNetworkDesc;
        private String parkEnterCompany;
        private String parkWaterSupply;
        private String parkBuildingArea;
        private String parkGasSupply;
        private String parkIsParkingSpace;
        private String parkIsNetwork;
        private String parkPriceManage;
        private String parkDisposeSewage;
        private String parkName;
        private String parkPriceSellparkSaleRental;
        private String parkInvestment;
        private String parkPowerSupply;
        private String parkPriceRent;
        private String parkLandArea;
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

        public String getParkPriceSell() {
            return parkPriceSell;
        }

        public void setParkPriceSell(String parkPriceSell) {
            this.parkPriceSell = parkPriceSell;
        }

        public String getParkNetworkDesc() {
            return parkNetworkDesc;
        }

        public void setParkNetworkDesc(String parkNetworkDesc) {
            this.parkNetworkDesc = parkNetworkDesc;
        }

        public String getParkEnterCompany() {
            return parkEnterCompany;
        }

        public void setParkEnterCompany(String parkEnterCompany) {
            this.parkEnterCompany = parkEnterCompany;
        }

        public String getParkWaterSupply() {
            return parkWaterSupply;
        }

        public void setParkWaterSupply(String parkWaterSupply) {
            this.parkWaterSupply = parkWaterSupply;
        }

        public String getParkBuildingArea() {
            return parkBuildingArea;
        }

        public void setParkBuildingArea(String parkBuildingArea) {
            this.parkBuildingArea = parkBuildingArea;
        }

        public String getParkGasSupply() {
            return parkGasSupply;
        }

        public void setParkGasSupply(String parkGasSupply) {
            this.parkGasSupply = parkGasSupply;
        }

        public String getParkIsParkingSpace() {
            return parkIsParkingSpace;
        }

        public void setParkIsParkingSpace(String parkIsParkingSpace) {
            this.parkIsParkingSpace = parkIsParkingSpace;
        }

        public String getParkIsNetwork() {
            return parkIsNetwork;
        }

        public void setParkIsNetwork(String parkIsNetwork) {
            this.parkIsNetwork = parkIsNetwork;
        }

        public String getParkPriceManage() {
            return parkPriceManage;
        }

        public void setParkPriceManage(String parkPriceManage) {
            this.parkPriceManage = parkPriceManage;
        }

        public String getParkDisposeSewage() {
            return parkDisposeSewage;
        }

        public void setParkDisposeSewage(String parkDisposeSewage) {
            this.parkDisposeSewage = parkDisposeSewage;
        }

        public String getParkName() {
            return parkName;
        }

        public void setParkName(String parkName) {
            this.parkName = parkName;
        }

        public String getParkPriceSellparkSaleRental() {
            return parkPriceSellparkSaleRental;
        }

        public void setParkPriceSellparkSaleRental(String parkPriceSellparkSaleRental) {
            this.parkPriceSellparkSaleRental = parkPriceSellparkSaleRental;
        }

        public String getParkInvestment() {
            return parkInvestment;
        }

        public void setParkInvestment(String parkInvestment) {
            this.parkInvestment = parkInvestment;
        }

        public String getParkPowerSupply() {
            return parkPowerSupply;
        }

        public void setParkPowerSupply(String parkPowerSupply) {
            this.parkPowerSupply = parkPowerSupply;
        }

        public String getParkPriceRent() {
            return parkPriceRent;
        }

        public void setParkPriceRent(String parkPriceRent) {
            this.parkPriceRent = parkPriceRent;
        }

        public String getParkLandArea() {
            return parkLandArea;
        }

        public void setParkLandArea(String parkLandArea) {
            this.parkLandArea = parkLandArea;
        }

        public String getCarrierid() {
            return carrierid;
        }

        public void setCarrierid(String carrierid) {
            this.carrierid = carrierid;
        }
    }
}
