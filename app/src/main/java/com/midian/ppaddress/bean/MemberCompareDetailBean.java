package com.midian.ppaddress.bean;

import com.google.gson.JsonSyntaxException;

import java.util.List;

import midian.baselib.app.AppException;
import midian.baselib.bean.NetResult;

/**
 * 载体对比-表格对比页
 * Created by chu on 2016/5/6.
 */
public class MemberCompareDetailBean extends NetResult {
    public static MemberCompareDetailBean parse(String json) throws AppException {
        MemberCompareDetailBean res = new MemberCompareDetailBean();
        try {
            res = gson.fromJson(json, MemberCompareDetailBean.class);
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
            throw AppException.json(e);
        }
        return res;
    }

    private List<CompareData> data;

    public List<CompareData> getData() {
        return data;
    }

    public void setData(List<CompareData> data) {
        this.data = data;
    }

    public class CompareData extends NetResult {
        private String province;
        private String city;
        private String street;
        private String supports;
        private String labels;
        private String intention;
        private String traffics;
        private String carrierid;
        //园区
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
        private String parkSaleRental;//
        private String parkInvestment;
        private String parkPowerSupply;
        private String parkPriceRent;
        private String parkLandArea;

        //综合体
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

        //土地
        private String landSaleRental;
        private String landDisposeSewage;
        private String landGreenRatio;
        private String landNetworkDesc;
        private String landInvestment;
        private String landPriceManage;
        private String landGasSupply;
        private String landBuildingDensity;
        private String landReformMode;
        private String landUsageEnd;
        private String landWaterSupply;
        private String landIsNetwork;
        private String landReformArea;
        private String landUseage;
        private String landPriceRent;
        private String landIsThreeOld;
        private String landUsageStart;
        private String landReformMapspot;
        private String landBuildingArea;
        private String landPriceSell;
        private String landPlotRatio;
        private String landReformCubage;
        private String landIsTwoCircuit;
        private String landName;
        private String landEnterCompany;
        private String landUseinfo;
        private String landLandArea;
        private String landPowerSupply;

        //写字楼
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

        //厂房
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

        //仓库
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

        public String getCarrierid() {
            return carrierid;
        }

        public void setCarrierid(String carrierid) {
            this.carrierid = carrierid;
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

        public String getParkSaleRental() {
            return parkSaleRental;
        }

        public void setParkSaleRental(String parkSaleRental) {
            this.parkSaleRental = parkSaleRental;
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

        public String getLandSaleRental() {
            return landSaleRental;
        }

        public void setLandSaleRental(String landSaleRental) {
            this.landSaleRental = landSaleRental;
        }

        public String getLandDisposeSewage() {
            return landDisposeSewage;
        }

        public void setLandDisposeSewage(String landDisposeSewage) {
            this.landDisposeSewage = landDisposeSewage;
        }

        public String getLandGreenRatio() {
            return landGreenRatio;
        }

        public void setLandGreenRatio(String landGreenRatio) {
            this.landGreenRatio = landGreenRatio;
        }

        public String getLandNetworkDesc() {
            return landNetworkDesc;
        }

        public void setLandNetworkDesc(String landNetworkDesc) {
            this.landNetworkDesc = landNetworkDesc;
        }

        public String getLandInvestment() {
            return landInvestment;
        }

        public void setLandInvestment(String landInvestment) {
            this.landInvestment = landInvestment;
        }

        public String getLandPriceManage() {
            return landPriceManage;
        }

        public void setLandPriceManage(String landPriceManage) {
            this.landPriceManage = landPriceManage;
        }

        public String getLandGasSupply() {
            return landGasSupply;
        }

        public void setLandGasSupply(String landGasSupply) {
            this.landGasSupply = landGasSupply;
        }

        public String getLandBuildingDensity() {
            return landBuildingDensity;
        }

        public void setLandBuildingDensity(String landBuildingDensity) {
            this.landBuildingDensity = landBuildingDensity;
        }

        public String getLandReformMode() {
            return landReformMode;
        }

        public void setLandReformMode(String landReformMode) {
            this.landReformMode = landReformMode;
        }

        public String getLandUsageEnd() {
            return landUsageEnd;
        }

        public void setLandUsageEnd(String landUsageEnd) {
            this.landUsageEnd = landUsageEnd;
        }

        public String getLandWaterSupply() {
            return landWaterSupply;
        }

        public void setLandWaterSupply(String landWaterSupply) {
            this.landWaterSupply = landWaterSupply;
        }

        public String getLandIsNetwork() {
            return landIsNetwork;
        }

        public void setLandIsNetwork(String landIsNetwork) {
            this.landIsNetwork = landIsNetwork;
        }

        public String getLandReformArea() {
            return landReformArea;
        }

        public void setLandReformArea(String landReformArea) {
            this.landReformArea = landReformArea;
        }

        public String getLandUseage() {
            return landUseage;
        }

        public void setLandUseage(String landUseage) {
            this.landUseage = landUseage;
        }

        public String getLandPriceRent() {
            return landPriceRent;
        }

        public void setLandPriceRent(String landPriceRent) {
            this.landPriceRent = landPriceRent;
        }

        public String getLandIsThreeOld() {
            return landIsThreeOld;
        }

        public void setLandIsThreeOld(String landIsThreeOld) {
            this.landIsThreeOld = landIsThreeOld;
        }

        public String getLandUsageStart() {
            return landUsageStart;
        }

        public void setLandUsageStart(String landUsageStart) {
            this.landUsageStart = landUsageStart;
        }

        public String getLandReformMapspot() {
            return landReformMapspot;
        }

        public void setLandReformMapspot(String landReformMapspot) {
            this.landReformMapspot = landReformMapspot;
        }

        public String getLandBuildingArea() {
            return landBuildingArea;
        }

        public void setLandBuildingArea(String landBuildingArea) {
            this.landBuildingArea = landBuildingArea;
        }

        public String getLandPriceSell() {
            return landPriceSell;
        }

        public void setLandPriceSell(String landPriceSell) {
            this.landPriceSell = landPriceSell;
        }

        public String getLandPlotRatio() {
            return landPlotRatio;
        }

        public void setLandPlotRatio(String landPlotRatio) {
            this.landPlotRatio = landPlotRatio;
        }

        public String getLandReformCubage() {
            return landReformCubage;
        }

        public void setLandReformCubage(String landReformCubage) {
            this.landReformCubage = landReformCubage;
        }

        public String getLandIsTwoCircuit() {
            return landIsTwoCircuit;
        }

        public void setLandIsTwoCircuit(String landIsTwoCircuit) {
            this.landIsTwoCircuit = landIsTwoCircuit;
        }

        public String getLandName() {
            return landName;
        }

        public void setLandName(String landName) {
            this.landName = landName;
        }

        public String getLandEnterCompany() {
            return landEnterCompany;
        }

        public void setLandEnterCompany(String landEnterCompany) {
            this.landEnterCompany = landEnterCompany;
        }

        public String getLandUseinfo() {
            return landUseinfo;
        }

        public void setLandUseinfo(String landUseinfo) {
            this.landUseinfo = landUseinfo;
        }

        public String getLandLandArea() {
            return landLandArea;
        }

        public void setLandLandArea(String landLandArea) {
            this.landLandArea = landLandArea;
        }

        public String getLandPowerSupply() {
            return landPowerSupply;
        }

        public void setLandPowerSupply(String landPowerSupply) {
            this.landPowerSupply = landPowerSupply;
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
    }
}
