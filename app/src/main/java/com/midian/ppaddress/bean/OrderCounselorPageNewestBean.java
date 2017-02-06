package com.midian.ppaddress.bean;

import com.google.gson.JsonSyntaxException;

import java.security.PrivateKey;
import java.util.List;

import midian.baselib.app.AppException;
import midian.baselib.bean.NetResult;

/**
 * 16.13个人中心-我（物业顾问）的预约-今天的预约
 * Created by chu on 2016/5/9.
 */
public class OrderCounselorPageNewestBean extends NetResult {
    public static OrderCounselorPageNewestBean parse(String json) throws AppException {
        OrderCounselorPageNewestBean res = new OrderCounselorPageNewestBean();
        try {
            res = gson.fromJson(json, OrderCounselorPageNewestBean.class);
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
        private String queueCount;//": 2,
        private List<Images> images;
        private Orders orders;

        public String getQueueCount() {
            return queueCount;
        }

        public void setQueueCount(String queueCount) {
            this.queueCount = queueCount;
        }

        public List<Images> getImages() {
            return images;
        }

        public void setImages(List<Images> images) {
            this.images = images;
        }

        public Orders getOrders() {
            return orders;
        }

        public void setOrders(Orders orders) {
            this.orders = orders;
        }
    }

    public class Images extends NetResult {
        private String url;//": "http://xxx/2ea.jpg"

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }

    public class Orders extends NetResult {
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
        private String contactLocat;//": "罗岗万达观察后门5号门口",
        private String carrierType;//": 8,
        private String number;//": "48420160505140251",
        private String stage;//": 3,
        private String buildingArea;//": 6000,
        private String portrait;//": "http://xxx/653.jpg",
        private String id;//": 15,
        private String startTime;//": "12:53(1天前)",
        private String mobilephone;//": "15920370107",
        private String floor;//": 1,
        private String carrierName;//": "佛山顺德产业转移工业园仓库",
        private String images;//": "http://xxx/d02.jpg",
        private String carrierid;//": 3,
        private String fullname;//": "石小
        private String landArea;
        private String property;
        private String saleRental;
        private String priceRent;
        private String priceSell;
        private String isread;

        public String getIsread() {
            return isread;
        }

        public void setIsread(String isread) {
            this.isread = isread;
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

        public String getPriceSell() {
            return priceSell;
        }

        public void setPriceSell(String priceSell) {
            this.priceSell = priceSell;
        }

        public String getLandArea() {
            return landArea;
        }

        public void setLandArea(String landArea) {
            this.landArea = landArea;
        }

        public String getProperty() {
            return property;
        }

        public void setProperty(String property) {
            this.property = property;
        }

        public String getContactLocat() {
            return contactLocat;
        }

        public void setContactLocat(String contactLocat) {
            this.contactLocat = contactLocat;
        }

        public String getCarrierType() {
            return carrierType;
        }

        public void setCarrierType(String carrierType) {
            this.carrierType = carrierType;
        }

        public String getNumber() {
            return number;
        }

        public void setNumber(String number) {
            this.number = number;
        }

        public String getStage() {
            return stage;
        }

        public void setStage(String stage) {
            this.stage = stage;
        }

        public String getBuildingArea() {
            return buildingArea;
        }

        public void setBuildingArea(String buildingArea) {
            this.buildingArea = buildingArea;
        }

        public String getPortrait() {
            return portrait;
        }

        public void setPortrait(String portrait) {
            this.portrait = portrait;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getStartTime() {
            return startTime;
        }

        public void setStartTime(String startTime) {
            this.startTime = startTime;
        }

        public String getMobilephone() {
            return mobilephone;
        }

        public void setMobilephone(String mobilephone) {
            this.mobilephone = mobilephone;
        }

        public String getFloor() {
            return floor;
        }

        public void setFloor(String floor) {
            this.floor = floor;
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

        public String getCarrierid() {
            return carrierid;
        }

        public void setCarrierid(String carrierid) {
            this.carrierid = carrierid;
        }

        public String getFullname() {
            return fullname;
        }

        public void setFullname(String fullname) {
            this.fullname = fullname;
        }
    }


}
