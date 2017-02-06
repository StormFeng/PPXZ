package com.midian.ppaddress.bean;

import com.google.gson.JsonSyntaxException;

import java.util.List;

import midian.baselib.app.AppException;
import midian.baselib.bean.NetResult;

/**
 * Created by chu on 2016/5/6.
 */
public class MemberCompareDetailPlantBean extends NetResult {
    public static MemberCompareDetailPlantBean parse(String json) throws AppException {
        MemberCompareDetailPlantBean res = new MemberCompareDetailPlantBean();
        try {
            res = gson.fromJson(json, MemberCompareDetailPlantBean.class);
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
        private String traffics;
        private String intention;
        private String labels;
        private String plantFloor;
        private String plantBeamHeight;
        private String plantParkingSpaceCount;
        private String plantIsNetwork;
        private String plantIsTwoCircuit;
        private String plantPriceSell;
        private String plantLandArea;
        private String plantIsParkingSpace;
        private String plantLoadBearing;
        private String plantSaleRental;
        private String plantPowerSupply;
        private String plantIsUnloading;
        private String plantGasSupply;
        private String plantName;
        private String plantMaxPass;
        private String plantLiveTime;
        private String plantEnterCompany;
        private String plantIsElevator;
        private String plantPriceRent;
        private String plantWaterSupply;
        private String plantMinPass;
        private String plantElevatorCount;
        private String plantDisposeSewage;
        private String plantPriceManage;
        private String plantInvestment;
        private String plantNetworkDesc;
        private String plantBuildingArea;
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

        public String getLabels() {
            return labels;
        }

        public void setLabels(String labels) {
            this.labels = labels;
        }

        public String getPlantFloor() {
            return plantFloor;
        }

        public void setPlantFloor(String plantFloor) {
            this.plantFloor = plantFloor;
        }

        public String getPlantBeamHeight() {
            return plantBeamHeight;
        }

        public void setPlantBeamHeight(String plantBeamHeight) {
            this.plantBeamHeight = plantBeamHeight;
        }

        public String getPlantParkingSpaceCount() {
            return plantParkingSpaceCount;
        }

        public void setPlantParkingSpaceCount(String plantParkingSpaceCount) {
            this.plantParkingSpaceCount = plantParkingSpaceCount;
        }

        public String getPlantIsNetwork() {
            return plantIsNetwork;
        }

        public void setPlantIsNetwork(String plantIsNetwork) {
            this.plantIsNetwork = plantIsNetwork;
        }

        public String getPlantIsTwoCircuit() {
            return plantIsTwoCircuit;
        }

        public void setPlantIsTwoCircuit(String plantIsTwoCircuit) {
            this.plantIsTwoCircuit = plantIsTwoCircuit;
        }

        public String getPlantPriceSell() {
            return plantPriceSell;
        }

        public void setPlantPriceSell(String plantPriceSell) {
            this.plantPriceSell = plantPriceSell;
        }

        public String getPlantLandArea() {
            return plantLandArea;
        }

        public void setPlantLandArea(String plantLandArea) {
            this.plantLandArea = plantLandArea;
        }

        public String getPlantIsParkingSpace() {
            return plantIsParkingSpace;
        }

        public void setPlantIsParkingSpace(String plantIsParkingSpace) {
            this.plantIsParkingSpace = plantIsParkingSpace;
        }

        public String getPlantLoadBearing() {
            return plantLoadBearing;
        }

        public void setPlantLoadBearing(String plantLoadBearing) {
            this.plantLoadBearing = plantLoadBearing;
        }

        public String getPlantSaleRental() {
            return plantSaleRental;
        }

        public void setPlantSaleRental(String plantSaleRental) {
            this.plantSaleRental = plantSaleRental;
        }

        public String getPlantPowerSupply() {
            return plantPowerSupply;
        }

        public void setPlantPowerSupply(String plantPowerSupply) {
            this.plantPowerSupply = plantPowerSupply;
        }

        public String getPlantIsUnloading() {
            return plantIsUnloading;
        }

        public void setPlantIsUnloading(String plantIsUnloading) {
            this.plantIsUnloading = plantIsUnloading;
        }

        public String getPlantGasSupply() {
            return plantGasSupply;
        }

        public void setPlantGasSupply(String plantGasSupply) {
            this.plantGasSupply = plantGasSupply;
        }

        public String getPlantName() {
            return plantName;
        }

        public void setPlantName(String plantName) {
            this.plantName = plantName;
        }

        public String getPlantMaxPass() {
            return plantMaxPass;
        }

        public void setPlantMaxPass(String plantMaxPass) {
            this.plantMaxPass = plantMaxPass;
        }

        public String getPlantLiveTime() {
            return plantLiveTime;
        }

        public void setPlantLiveTime(String plantLiveTime) {
            this.plantLiveTime = plantLiveTime;
        }

        public String getPlantEnterCompany() {
            return plantEnterCompany;
        }

        public void setPlantEnterCompany(String plantEnterCompany) {
            this.plantEnterCompany = plantEnterCompany;
        }

        public String getPlantIsElevator() {
            return plantIsElevator;
        }

        public void setPlantIsElevator(String plantIsElevator) {
            this.plantIsElevator = plantIsElevator;
        }

        public String getPlantPriceRent() {
            return plantPriceRent;
        }

        public void setPlantPriceRent(String plantPriceRent) {
            this.plantPriceRent = plantPriceRent;
        }

        public String getPlantWaterSupply() {
            return plantWaterSupply;
        }

        public void setPlantWaterSupply(String plantWaterSupply) {
            this.plantWaterSupply = plantWaterSupply;
        }

        public String getPlantMinPass() {
            return plantMinPass;
        }

        public void setPlantMinPass(String plantMinPass) {
            this.plantMinPass = plantMinPass;
        }

        public String getPlantElevatorCount() {
            return plantElevatorCount;
        }

        public void setPlantElevatorCount(String plantElevatorCount) {
            this.plantElevatorCount = plantElevatorCount;
        }

        public String getPlantDisposeSewage() {
            return plantDisposeSewage;
        }

        public void setPlantDisposeSewage(String plantDisposeSewage) {
            this.plantDisposeSewage = plantDisposeSewage;
        }

        public String getPlantPriceManage() {
            return plantPriceManage;
        }

        public void setPlantPriceManage(String plantPriceManage) {
            this.plantPriceManage = plantPriceManage;
        }

        public String getPlantInvestment() {
            return plantInvestment;
        }

        public void setPlantInvestment(String plantInvestment) {
            this.plantInvestment = plantInvestment;
        }

        public String getPlantNetworkDesc() {
            return plantNetworkDesc;
        }

        public void setPlantNetworkDesc(String plantNetworkDesc) {
            this.plantNetworkDesc = plantNetworkDesc;
        }

        public String getPlantBuildingArea() {
            return plantBuildingArea;
        }

        public void setPlantBuildingArea(String plantBuildingArea) {
            this.plantBuildingArea = plantBuildingArea;
        }

        public String getCarrierid() {
            return carrierid;
        }

        public void setCarrierid(String carrierid) {
            this.carrierid = carrierid;
        }
    }
}
