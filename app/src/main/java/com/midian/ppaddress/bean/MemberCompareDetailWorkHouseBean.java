package com.midian.ppaddress.bean;

import com.google.gson.JsonSyntaxException;

import java.util.List;

import midian.baselib.app.AppException;
import midian.baselib.bean.NetResult;

/**
 * Created by chu on 2016/5/6.
 */
public class MemberCompareDetailWorkHouseBean extends NetResult {
    public static MemberCompareDetailWorkHouseBean parse(String json) throws AppException {
        MemberCompareDetailWorkHouseBean res = new MemberCompareDetailWorkHouseBean();
        try {
            res = gson.fromJson(json, MemberCompareDetailWorkHouseBean.class);
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
        private String labels;
        private String street;
        private String traffics;
        private String intention;
        private String workhouseBuildingArea;
        private String workhouseIsTwoCircuit;
        private String workhouseParkingSpaceCount;
        private String workhousePriceRent;
        private String workhousePriceManage;
        private String workhouseEnterCompany;
        private String workhouseMaxPass;
        private String workhouseLiveTime;
        private String workhouseLoadBearing;
        private String workhouseElevatorCount;
        private String workhouseIsNetwork;
        private String workhouseLandArea;
        private String workhouseBeamHeight;
        private String workhouseIsParkingSpace;
        private String workhousePriceSell;
        private String workhouseFloor;
        private String workhouseGasSupply;
        private String workhouseInvestment;
        private String workhouseIsElevator;
        private String workhouseWaterSupply;
        private String workhouseIsUnloading;
        private String workhouseName;
        private String workhouseMinPass;
        private String workhousePowerSupply;
        private String workhouseNetworkDesc;
        private String workhouseSaleRental;
        private String workhouseDisposeSewage;
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

        public String getLabels() {
            return labels;
        }

        public void setLabels(String labels) {
            this.labels = labels;
        }

        public String getStreet() {
            return street;
        }

        public void setStreet(String street) {
            this.street = street;
        }

        public String getTraffics() {
            return traffics;
        }

        public void setTraffics(String traffics) {
            this.traffics = traffics;
        }

        public String getIntention() {
            return intention;
        }

        public void setIntention(String intention) {
            this.intention = intention;
        }

        public String getWorkhouseBuildingArea() {
            return workhouseBuildingArea;
        }

        public void setWorkhouseBuildingArea(String workhouseBuildingArea) {
            this.workhouseBuildingArea = workhouseBuildingArea;
        }

        public String getWorkhouseIsTwoCircuit() {
            return workhouseIsTwoCircuit;
        }

        public void setWorkhouseIsTwoCircuit(String workhouseIsTwoCircuit) {
            this.workhouseIsTwoCircuit = workhouseIsTwoCircuit;
        }

        public String getWorkhouseParkingSpaceCount() {
            return workhouseParkingSpaceCount;
        }

        public void setWorkhouseParkingSpaceCount(String workhouseParkingSpaceCount) {
            this.workhouseParkingSpaceCount = workhouseParkingSpaceCount;
        }

        public String getWorkhousePriceRent() {
            return workhousePriceRent;
        }

        public void setWorkhousePriceRent(String workhousePriceRent) {
            this.workhousePriceRent = workhousePriceRent;
        }

        public String getWorkhousePriceManage() {
            return workhousePriceManage;
        }

        public void setWorkhousePriceManage(String workhousePriceManage) {
            this.workhousePriceManage = workhousePriceManage;
        }

        public String getWorkhouseEnterCompany() {
            return workhouseEnterCompany;
        }

        public void setWorkhouseEnterCompany(String workhouseEnterCompany) {
            this.workhouseEnterCompany = workhouseEnterCompany;
        }

        public String getWorkhouseMaxPass() {
            return workhouseMaxPass;
        }

        public void setWorkhouseMaxPass(String workhouseMaxPass) {
            this.workhouseMaxPass = workhouseMaxPass;
        }

        public String getWorkhouseLiveTime() {
            return workhouseLiveTime;
        }

        public void setWorkhouseLiveTime(String workhouseLiveTime) {
            this.workhouseLiveTime = workhouseLiveTime;
        }

        public String getWorkhouseLoadBearing() {
            return workhouseLoadBearing;
        }

        public void setWorkhouseLoadBearing(String workhouseLoadBearing) {
            this.workhouseLoadBearing = workhouseLoadBearing;
        }

        public String getWorkhouseElevatorCount() {
            return workhouseElevatorCount;
        }

        public void setWorkhouseElevatorCount(String workhouseElevatorCount) {
            this.workhouseElevatorCount = workhouseElevatorCount;
        }

        public String getWorkhouseIsNetwork() {
            return workhouseIsNetwork;
        }

        public void setWorkhouseIsNetwork(String workhouseIsNetwork) {
            this.workhouseIsNetwork = workhouseIsNetwork;
        }

        public String getWorkhouseLandArea() {
            return workhouseLandArea;
        }

        public void setWorkhouseLandArea(String workhouseLandArea) {
            this.workhouseLandArea = workhouseLandArea;
        }

        public String getWorkhouseBeamHeight() {
            return workhouseBeamHeight;
        }

        public void setWorkhouseBeamHeight(String workhouseBeamHeight) {
            this.workhouseBeamHeight = workhouseBeamHeight;
        }

        public String getWorkhouseIsParkingSpace() {
            return workhouseIsParkingSpace;
        }

        public void setWorkhouseIsParkingSpace(String workhouseIsParkingSpace) {
            this.workhouseIsParkingSpace = workhouseIsParkingSpace;
        }

        public String getWorkhousePriceSell() {
            return workhousePriceSell;
        }

        public void setWorkhousePriceSell(String workhousePriceSell) {
            this.workhousePriceSell = workhousePriceSell;
        }

        public String getWorkhouseFloor() {
            return workhouseFloor;
        }

        public void setWorkhouseFloor(String workhouseFloor) {
            this.workhouseFloor = workhouseFloor;
        }

        public String getWorkhouseGasSupply() {
            return workhouseGasSupply;
        }

        public void setWorkhouseGasSupply(String workhouseGasSupply) {
            this.workhouseGasSupply = workhouseGasSupply;
        }

        public String getWorkhouseInvestment() {
            return workhouseInvestment;
        }

        public void setWorkhouseInvestment(String workhouseInvestment) {
            this.workhouseInvestment = workhouseInvestment;
        }

        public String getWorkhouseIsElevator() {
            return workhouseIsElevator;
        }

        public void setWorkhouseIsElevator(String workhouseIsElevator) {
            this.workhouseIsElevator = workhouseIsElevator;
        }

        public String getWorkhouseWaterSupply() {
            return workhouseWaterSupply;
        }

        public void setWorkhouseWaterSupply(String workhouseWaterSupply) {
            this.workhouseWaterSupply = workhouseWaterSupply;
        }

        public String getWorkhouseIsUnloading() {
            return workhouseIsUnloading;
        }

        public void setWorkhouseIsUnloading(String workhouseIsUnloading) {
            this.workhouseIsUnloading = workhouseIsUnloading;
        }

        public String getWorkhouseName() {
            return workhouseName;
        }

        public void setWorkhouseName(String workhouseName) {
            this.workhouseName = workhouseName;
        }

        public String getWorkhouseMinPass() {
            return workhouseMinPass;
        }

        public void setWorkhouseMinPass(String workhouseMinPass) {
            this.workhouseMinPass = workhouseMinPass;
        }

        public String getWorkhousePowerSupply() {
            return workhousePowerSupply;
        }

        public void setWorkhousePowerSupply(String workhousePowerSupply) {
            this.workhousePowerSupply = workhousePowerSupply;
        }

        public String getWorkhouseNetworkDesc() {
            return workhouseNetworkDesc;
        }

        public void setWorkhouseNetworkDesc(String workhouseNetworkDesc) {
            this.workhouseNetworkDesc = workhouseNetworkDesc;
        }

        public String getWorkhouseSaleRental() {
            return workhouseSaleRental;
        }

        public void setWorkhouseSaleRental(String workhouseSaleRental) {
            this.workhouseSaleRental = workhouseSaleRental;
        }

        public String getWorkhouseDisposeSewage() {
            return workhouseDisposeSewage;
        }

        public void setWorkhouseDisposeSewage(String workhouseDisposeSewage) {
            this.workhouseDisposeSewage = workhouseDisposeSewage;
        }

        public String getCarrierid() {
            return carrierid;
        }

        public void setCarrierid(String carrierid) {
            this.carrierid = carrierid;
        }
    }
}
