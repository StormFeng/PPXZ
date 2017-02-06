package com.midian.ppaddress.bean;

import com.google.gson.JsonSyntaxException;

import java.util.List;

import midian.baselib.app.AppException;
import midian.baselib.bean.NetResult;

/**
 * 17.1个人中心(物业顾问)-我的盘源
 * Created by chu on 2016/5/9.
 */
public class EntercarrierPageEnterBean extends NetResult {
    public static EntercarrierPageEnterBean parse(String json) throws AppException {
        EntercarrierPageEnterBean res = new EntercarrierPageEnterBean();
        try {
            res = gson.fromJson(json, EntercarrierPageEnterBean.class);
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
        private String lastPage;//": true,
        private String pageSize;//": 1,
        private String pageNumber;//": 1,
        private String firstPage;//": true,
        private String totalRow;//": 7,
        private String totalPage;//": 1
        private List<Lists> list;

        public String getLastPage() {
            return lastPage;
        }

        public void setLastPage(String lastPage) {
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

        public List<Lists> getList() {
            return list;
        }

        public void setList(List<Lists> lists) {
            this.list = lists;
        }
    }

    public class Lists extends NetResult {
        private String floor;//": "30",//仓库楼层(厂房、仓库共有字段)
        private String coverimgid;//": 169,
        private String carrierType;//": 8,
        private String property;//": "住宅用地",//土地用地性质
        private String landArea;//": 1, //土地面积
        private String saleRental;//": 1,//写字楼租售类型: 0为租售，1为租，2为售
        private String priceRent;//": 20, //写字楼租单价
        private List<Labels> labels;
        private String images;//": "http:// XXX/7d706b155.jpg",
        private String carrierName;//": "金沙丽水仓库",
        private String carrierid;//": 53,
        private String carrierCode;//": "W-20160304",
        private String priceSell;//": 30, //园区售单价
        private String buildingArea;//": 20  //仓库待租售面积(厂房、仓库共有字段)

        public String getFloor() {
            return floor;
        }

        public void setFloor(String floor) {
            this.floor = floor;
        }

        public String getCoverimgid() {
            return coverimgid;
        }

        public void setCoverimgid(String coverimgid) {
            this.coverimgid = coverimgid;
        }

        public String getCarrierType() {
            return carrierType;
        }

        public void setCarrierType(String carrierType) {
            this.carrierType = carrierType;
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

        public List<Labels> getLabels() {
            return labels;
        }

        public void setLabels(List<Labels> labels) {
            this.labels = labels;
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

        public String getCarrierid() {
            return carrierid;
        }

        public void setCarrierid(String carrierid) {
            this.carrierid = carrierid;
        }

        public String getCarrierCode() {
            return carrierCode;
        }

        public void setCarrierCode(String carrierCode) {
            this.carrierCode = carrierCode;
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

    public class Labels extends NetResult {
        private String id;
        private String label;//靠近公园

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getLabel() {
            return label;
        }

        public void setLabel(String label) {
            this.label = label;
        }
    }

}
