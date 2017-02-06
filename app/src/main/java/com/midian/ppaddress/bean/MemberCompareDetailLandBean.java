package com.midian.ppaddress.bean;

import com.google.gson.JsonSyntaxException;

import java.util.List;

import midian.baselib.app.AppException;
import midian.baselib.bean.NetResult;

/**
 * Created by chu on 2016/5/6.
 */
public class MemberCompareDetailLandBean extends NetResult {
    public static MemberCompareDetailLandBean parse(String json) throws AppException {
        MemberCompareDetailLandBean res = new MemberCompareDetailLandBean();
        try {
            res = gson.fromJson(json, MemberCompareDetailLandBean.class);
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
        private String intention;
        private String traffics;
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

        public String getCarrierid() {
            return carrierid;
        }

        public void setCarrierid(String carrierid) {
            this.carrierid = carrierid;
        }
    }
}
