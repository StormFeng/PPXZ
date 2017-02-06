package com.midian.ppaddress.bean;

import com.google.gson.JsonSyntaxException;

import java.util.List;

import midian.baselib.app.AppException;
import midian.baselib.bean.NetResult;

/**
 * 8.10载体-载体详情-可选单元列表
 */
public class BusinessCarrierDetailPageUnitsBean extends NetResult {
    public static BusinessCarrierDetailPageUnitsBean parse(String json) throws AppException {
        BusinessCarrierDetailPageUnitsBean res = new BusinessCarrierDetailPageUnitsBean();
        try {
            res = gson.fromJson(json, BusinessCarrierDetailPageUnitsBean.class);
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
            throw AppException.json(e);
        }
        return res;
    }

    private UnitsData data;

    public UnitsData getData() {
        return data;
    }

    public void setData(UnitsData data) {
        this.data = data;
    }

    public class UnitsData extends NetResult {

        private boolean lastPage;
        private String pageSize;
        private String pageNumber;
        private String firstPage;
        private List<UnitsList> list;
        private String totalRow;
        private String totalPage;

        public boolean isLastPage() {
            return lastPage;
        }

        public void setLastPage(boolean lastPage) {
            this.lastPage = lastPage;
        }

        public String getPageSize() {
            return pageSize;
        }

        public void setPageSize(String pageSize) {
            this.pageSize = pageSize;
        }

        public String getPageNumber() {
            return pageNumber;
        }

        public void setPageNumber(String pageNumber) {
            this.pageNumber = pageNumber;
        }

        public List<UnitsList> getList() {
            return list;
        }

        public void setList(List<UnitsList> list) {
            this.list = list;
        }

        public String getFirstPage() {
            return firstPage;
        }

        public void setFirstPage(String firstPage) {
            this.firstPage = firstPage;
        }

        public String getTotalRow() {
            return totalRow;
        }

        public void setTotalRow(String totalRow) {
            this.totalRow = totalRow;
        }

        public String getTotalPage() {
            return totalPage;
        }

        public void setTotalPage(String totalPage) {
            this.totalPage = totalPage;
        }
    }
    public class UnitsList extends NetResult{
        private String decor;
        private String id;
        private String reward;
        private String saleRental;
        private String floor;
        private String priceRent;
        private String name;
        private String coverimg;
        private String beamHeight;
        private String priceSell;
        private String buildingArea;
        private String carrierType;

        public String getCarrierType() {
            return carrierType;
        }

        public void setCarrierType(String carrierType) {
            this.carrierType = carrierType;
        }

        public String getDecor() {
            return decor;
        }

        public void setDecor(String decor) {
            this.decor = decor;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getReward() {
            return reward;
        }

        public void setReward(String reward) {
            this.reward = reward;
        }

        public String getSaleRental() {
            return saleRental;
        }

        public void setSaleRental(String saleRental) {
            this.saleRental = saleRental;
        }

        public String getFloor() {
            return floor;
        }

        public void setFloor(String floor) {
            this.floor = floor;
        }

        public String getPriceRent() {
            return priceRent;
        }

        public void setPriceRent(String priceRent) {
            this.priceRent = priceRent;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getCoverimg() {
            return coverimg;
        }

        public void setCoverimg(String coverimg) {
            this.coverimg = coverimg;
        }

        public String getBeamHeight() {
            return beamHeight;
        }

        public void setBeamHeight(String beamHeight) {
            this.beamHeight = beamHeight;
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
