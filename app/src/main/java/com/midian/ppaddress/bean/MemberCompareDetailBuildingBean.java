package com.midian.ppaddress.bean;

import com.google.gson.JsonSyntaxException;

import java.util.List;

import midian.baselib.app.AppException;
import midian.baselib.bean.NetResult;

/**
 * Created by chu on 2016/5/6.
 */
public class MemberCompareDetailBuildingBean extends NetResult {
    public static MemberCompareDetailBuildingBean parse(String json) throws AppException {
        MemberCompareDetailBuildingBean res = new MemberCompareDetailBuildingBean();
        try {
            res = gson.fromJson(json, MemberCompareDetailBuildingBean.class);
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
        private String labels;
        private String traffics;
        private String intention;
        private String buildingGasSupply;
        private String buildingName;
        private String buildingLandArea;
        private String buildingBeamHeight;
        private String buildingWaterSupply;
        private String buildingPowerSupply;
        private String buildingSaleRental;
        private String buildingEnterCompany;
        private String buildingDisposeSewage;
        private String buildingBuildingRank;
        private String buildingFloor;
        private String buildingInvestment;
        private String buildingElevatorCount;
        private String buildingIsParkingSpace;
        private String buildingLoadBearing;
        private String buildingMaxPass;
        private String buildingPriceManage;
        private String buildingIsTwoCircuit;
        private String buildingBuildingArea;
        private String buildingMinPass;
        private String buildingNetworkDesc;
        private String buildingDecor;
        private String buildingPriceRent;
        private String buildingIsNetwork;
        private String buildingParkingSpaceCount;
        private String buildingIsElevator;
        private String buildingPriceSell;
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

        public String getLabels() {
            return labels;
        }

        public void setLabels(String labels) {
            this.labels = labels;
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

        public String getBuildingGasSupply() {
            return buildingGasSupply;
        }

        public void setBuildingGasSupply(String buildingGasSupply) {
            this.buildingGasSupply = buildingGasSupply;
        }

        public String getBuildingName() {
            return buildingName;
        }

        public void setBuildingName(String buildingName) {
            this.buildingName = buildingName;
        }

        public String getBuildingLandArea() {
            return buildingLandArea;
        }

        public void setBuildingLandArea(String buildingLandArea) {
            this.buildingLandArea = buildingLandArea;
        }

        public String getBuildingBeamHeight() {
            return buildingBeamHeight;
        }

        public void setBuildingBeamHeight(String buildingBeamHeight) {
            this.buildingBeamHeight = buildingBeamHeight;
        }

        public String getBuildingWaterSupply() {
            return buildingWaterSupply;
        }

        public void setBuildingWaterSupply(String buildingWaterSupply) {
            this.buildingWaterSupply = buildingWaterSupply;
        }

        public String getBuildingPowerSupply() {
            return buildingPowerSupply;
        }

        public void setBuildingPowerSupply(String buildingPowerSupply) {
            this.buildingPowerSupply = buildingPowerSupply;
        }

        public String getBuildingSaleRental() {
            return buildingSaleRental;
        }

        public void setBuildingSaleRental(String buildingSaleRental) {
            this.buildingSaleRental = buildingSaleRental;
        }

        public String getBuildingEnterCompany() {
            return buildingEnterCompany;
        }

        public void setBuildingEnterCompany(String buildingEnterCompany) {
            this.buildingEnterCompany = buildingEnterCompany;
        }

        public String getBuildingDisposeSewage() {
            return buildingDisposeSewage;
        }

        public void setBuildingDisposeSewage(String buildingDisposeSewage) {
            this.buildingDisposeSewage = buildingDisposeSewage;
        }

        public String getBuildingBuildingRank() {
            return buildingBuildingRank;
        }

        public void setBuildingBuildingRank(String buildingBuildingRank) {
            this.buildingBuildingRank = buildingBuildingRank;
        }

        public String getBuildingFloor() {
            return buildingFloor;
        }

        public void setBuildingFloor(String buildingFloor) {
            this.buildingFloor = buildingFloor;
        }

        public String getBuildingInvestment() {
            return buildingInvestment;
        }

        public void setBuildingInvestment(String buildingInvestment) {
            this.buildingInvestment = buildingInvestment;
        }

        public String getBuildingElevatorCount() {
            return buildingElevatorCount;
        }

        public void setBuildingElevatorCount(String buildingElevatorCount) {
            this.buildingElevatorCount = buildingElevatorCount;
        }

        public String getBuildingIsParkingSpace() {
            return buildingIsParkingSpace;
        }

        public void setBuildingIsParkingSpace(String buildingIsParkingSpace) {
            this.buildingIsParkingSpace = buildingIsParkingSpace;
        }

        public String getBuildingLoadBearing() {
            return buildingLoadBearing;
        }

        public void setBuildingLoadBearing(String buildingLoadBearing) {
            this.buildingLoadBearing = buildingLoadBearing;
        }

        public String getBuildingMaxPass() {
            return buildingMaxPass;
        }

        public void setBuildingMaxPass(String buildingMaxPass) {
            this.buildingMaxPass = buildingMaxPass;
        }

        public String getBuildingPriceManage() {
            return buildingPriceManage;
        }

        public void setBuildingPriceManage(String buildingPriceManage) {
            this.buildingPriceManage = buildingPriceManage;
        }

        public String getBuildingIsTwoCircuit() {
            return buildingIsTwoCircuit;
        }

        public void setBuildingIsTwoCircuit(String buildingIsTwoCircuit) {
            this.buildingIsTwoCircuit = buildingIsTwoCircuit;
        }

        public String getBuildingBuildingArea() {
            return buildingBuildingArea;
        }

        public void setBuildingBuildingArea(String buildingBuildingArea) {
            this.buildingBuildingArea = buildingBuildingArea;
        }

        public String getBuildingMinPass() {
            return buildingMinPass;
        }

        public void setBuildingMinPass(String buildingMinPass) {
            this.buildingMinPass = buildingMinPass;
        }

        public String getBuildingNetworkDesc() {
            return buildingNetworkDesc;
        }

        public void setBuildingNetworkDesc(String buildingNetworkDesc) {
            this.buildingNetworkDesc = buildingNetworkDesc;
        }

        public String getBuildingDecor() {
            return buildingDecor;
        }

        public void setBuildingDecor(String buildingDecor) {
            this.buildingDecor = buildingDecor;
        }

        public String getBuildingPriceRent() {
            return buildingPriceRent;
        }

        public void setBuildingPriceRent(String buildingPriceRent) {
            this.buildingPriceRent = buildingPriceRent;
        }

        public String getBuildingIsNetwork() {
            return buildingIsNetwork;
        }

        public void setBuildingIsNetwork(String buildingIsNetwork) {
            this.buildingIsNetwork = buildingIsNetwork;
        }

        public String getBuildingParkingSpaceCount() {
            return buildingParkingSpaceCount;
        }

        public void setBuildingParkingSpaceCount(String buildingParkingSpaceCount) {
            this.buildingParkingSpaceCount = buildingParkingSpaceCount;
        }

        public String getBuildingIsElevator() {
            return buildingIsElevator;
        }

        public void setBuildingIsElevator(String buildingIsElevator) {
            this.buildingIsElevator = buildingIsElevator;
        }

        public String getBuildingPriceSell() {
            return buildingPriceSell;
        }

        public void setBuildingPriceSell(String buildingPriceSell) {
            this.buildingPriceSell = buildingPriceSell;
        }

        public String getCarrierid() {
            return carrierid;
        }

        public void setCarrierid(String carrierid) {
            this.carrierid = carrierid;
        }
    }
}
