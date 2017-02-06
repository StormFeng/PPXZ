package com.midian.ppaddress.bean;

import com.google.gson.JsonSyntaxException;

import java.util.List;

import midian.baselib.app.AppException;
import midian.baselib.bean.NetResult;

/**
 * 8.3选址，地图选址，覆盖物列表
 * Created by chu on 2016/5/6.
 */
public class BusinessAppsearchMapcarriersBean extends NetResult {
    public static BusinessAppsearchMapcarriersBean parse(String json) throws AppException {
        BusinessAppsearchMapcarriersBean res = new BusinessAppsearchMapcarriersBean();
        try {
            res = gson.fromJson(json, BusinessAppsearchMapcarriersBean.class);
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
            throw AppException.json(e);
        }
        return res;
    }

    private MapListData data;

    public MapListData getData() {
        return data;
    }

    public void setData(MapListData data) {
        this.data = data;
    }

    public class MapListData extends NetResult {
        private Carrier carrier;
        private List<CarrierList> list;

        public Carrier getCarrier() {
            return carrier;
        }

        public void setCarrier(Carrier carrier) {
            this.carrier = carrier;
        }

        public List<CarrierList> getList() {
            return list;
        }

        public void setList(List<CarrierList> list) {
            this.list = list;
        }

    }

    public class Carrier extends NetResult {
        private String labels;//孵化器,充裕卸货平台
        private String carrierType;//1
        private String carrierName;//碧桂园凤凰城园区
        private String carrierid;//49
        private String buildingArea;//200

        public String getLabels() {
            return labels;
        }

        public void setLabels(String labels) {
            this.labels = labels;
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

        public String getCarrierid() {
            return carrierid;
        }

        public void setCarrierid(String carrierid) {
            this.carrierid = carrierid;
        }

        public String getBuildingArea() {
            return buildingArea;
        }

        public void setBuildingArea(String buildingArea) {
            this.buildingArea = buildingArea;
        }
    }

    public class CarrierList extends NetResult {
        private String carrieruuid;//8865840124f94790a67f62371d47740d
        private String landArea;//10
        private String labels;//靠近公交站,环境清新
        private String carrierType;//3
        private String carrierName;//龙湖首开•天宸原著
        private String property;//住宅用地
        private String images;//http://xxx/01.jpeg
        private String carrierid;//50
        private String floor;//
        private String buildingArea;
        private String saleRental;//": 0,
        private String priceRent;//": 200,
        private String priceSell;//": 300,

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

        public String getCarrieruuid() {
            return carrieruuid;
        }

        public void setCarrieruuid(String carrieruuid) {
            this.carrieruuid = carrieruuid;
        }

        public String getLandArea() {
            return landArea;
        }

        public void setLandArea(String landArea) {
            this.landArea = landArea;
        }

        public String getLabels() {
            return labels;
        }

        public void setLabels(String labels) {
            this.labels = labels;
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

        public String getProperty() {
            return property;
        }

        public void setProperty(String property) {
            this.property = property;
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
    }
}
