package com.midian.ppaddress.bean;

import com.google.gson.JsonSyntaxException;

import java.util.List;

import midian.baselib.app.AppException;
import midian.baselib.bean.NetResult;

/**
 * 16.11个人中心-我的订单(普通客商)-已签约
 * Created by chu on 2016/5/9.
 */
public class MemberOrderMemberPageSignOrderBean extends NetResult {
    public static MemberOrderMemberPageSignOrderBean parse(String json) throws AppException {
        MemberOrderMemberPageSignOrderBean res = new MemberOrderMemberPageSignOrderBean();
        try {
            res = gson.fromJson(json, MemberOrderMemberPageSignOrderBean.class);
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
        private String pageSize;//": 15,
        private String pageNumber;//": 1,
        private List<Lists> list;
        private String firstPage;//": true,
        private String totalRow;//": 1,
        private String totalPage;//": 1

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

        public List<Lists> getList() {
            return list;
        }

        public void setList(List<Lists> list) {
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

    public class Lists extends NetResult {
        private String id;//": 4,
        private String mobilephone;//": "13660538268",
        private String createTime;//": "2016-05-02 15:33:28",
        private String landArea;//": 30000,
        private String carrierType;//": 3,
        private String carrierName;//": "花都花东镇杨二村地块",
        private String images;//": "http://xxx/87.jpg",
        private String property;//": "商业用地",
        private String carrierid;//": 15,
        private String stage;//": 11,
        private String portrait;//": "http://xxx/a0.jpg",
        private String fullname;//": "杨海春
        private String buildingArea;
        private String floor;
        private String saleRental;
        private String priceRent;
        private String priceSell;

        public String getPriceSell() {
            return priceSell;
        }

        public void setPriceSell(String priceSell) {
            this.priceSell = priceSell;
        }

        public String getPriceRent() {
            return priceRent;
        }

        public void setPriceRent(String priceRent) {
            this.priceRent = priceRent;
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

        public String getBuildingArea() {
            return buildingArea;
        }

        public void setBuildingArea(String buildingArea) {
            this.buildingArea = buildingArea;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getMobilephone() {
            return mobilephone;
        }

        public void setMobilephone(String mobilephone) {
            this.mobilephone = mobilephone;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getLandArea() {
            return landArea;
        }

        public void setLandArea(String landArea) {
            this.landArea = landArea;
        }

        public String getCarrierType() {
            return carrierType;
        }

        public void setCarrierType(String carrierType) {
            this.carrierType = carrierType;
        }

        public String getCarrierName() {
            return carrierName;
        }

        public void setCarrierName(String carrierName) {
            this.carrierName = carrierName;
        }

        public String getImages() {
            return images;
        }

        public void setImages(String images) {
            this.images = images;
        }

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

        public String getStage() {
            return stage;
        }

        public void setStage(String stage) {
            this.stage = stage;
        }

        public String getPortrait() {
            return portrait;
        }

        public void setPortrait(String portrait) {
            this.portrait = portrait;
        }

        public String getFullname() {
            return fullname;
        }

        public void setFullname(String fullname) {
            this.fullname = fullname;
        }
    }

}
