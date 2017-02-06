package com.midian.ppaddress.bean;

import com.google.gson.JsonSyntaxException;

import java.util.List;

import midian.baselib.app.AppException;
import midian.baselib.bean.NetResult;

/**
 * Created by chu on 2016/5/6.
 */
public class BusinessCarrierDetailShowBean extends NetResult {
    public static BusinessCarrierDetailShowBean parse(String json) throws AppException {
        BusinessCarrierDetailShowBean res = new BusinessCarrierDetailShowBean();
        try {
            res = gson.fromJson(json, BusinessCarrierDetailShowBean.class);
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
            throw AppException.json(e);
        }
        return res;
    }

    private CarrierDetailData data;


    public CarrierDetailData getData() {
        return data;
    }

    public void setData(CarrierDetailData data) {
        this.data = data;
    }

    public class CarrierDetailData extends NetResult {
        private String summary;//简介
        private String reward;//赏金
        private String saleRental;//租售方式
        private String priceRent;//租单价
        private String coverimgid;
        private List<LabelsList> labels;//标签
        private String carrierType;//载体类型
        private Parent parent;//(所属园区或综合体)
        private List<CounselorsList> counselors;
        private List<IntroImagesList> introImages;//（介绍图）页面头部轮播
        private boolean isCollect;//是否收藏
        private String favoriteid;//载体收藏id
        private String orderCounts;//预约数
        private String buildingArea;//待租售面积
        private String landArea;
        private String browseCount;//浏览数
        private String address;//地址
        private List<ImagesList> images;//(简介图) 页面中部 “推荐理由“6张
        private String carrierName;//载体名称
        private String panoramaurl;//3D实景
        private String shareurl;//": "http://www.pploc.com/show?carrierid=29&agentid=1",
        private String sharetxt;//": "我在【PP优选】发现了一个好载体",
        private String sharetitle;//": "我在【PP优选】发现了一个好载体，快来看看吧！",
        private String  shareimg;//": "http://www.pploc.com/1.jpg"
        private String carrierid;//载体id
        private String priceSell;//售单价

        public String getLandArea() {
            return landArea;
        }

        public void setLandArea(String landArea) {
            this.landArea = landArea;
        }

        public String getFavoriteid() {
            return favoriteid;
        }

        public void setFavoriteid(String favoriteid) {
            this.favoriteid = favoriteid;
        }

        public String getShareurl() {
            return shareurl;
        }

        public void setShareurl(String shareurl) {
            this.shareurl = shareurl;
        }

        public String getSharetxt() {
            return sharetxt;
        }

        public void setSharetxt(String sharetxt) {
            this.sharetxt = sharetxt;
        }

        public String getSharetitle() {
            return sharetitle;
        }

        public void setSharetitle(String sharetitle) {
            this.sharetitle = sharetitle;
        }

        public String getShareimg() {
            return shareimg;
        }

        public void setShareimg(String shareimg) {
            this.shareimg = shareimg;
        }

        public String getSummary() {
            return summary;
        }

        public void setSummary(String summary) {
            this.summary = summary;
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

        public String getPriceRent() {
            return priceRent;
        }

        public void setPriceRent(String priceRent) {
            this.priceRent = priceRent;
        }

        public String getCoverimgid() {
            return coverimgid;
        }

        public void setCoverimgid(String coverimgid) {
            this.coverimgid = coverimgid;
        }

        public List<LabelsList> getLabels() {
            return labels;
        }

        public void setLabels(List<LabelsList> labels) {
            this.labels = labels;
        }

        public String getCarrierType() {
            return carrierType;
        }

        public void setCarrierType(String carrierType) {
            this.carrierType = carrierType;
        }

        public Parent getParent() {
            return parent;
        }

        public void setParent(Parent parent) {
            this.parent = parent;
        }

        public List<CounselorsList> getCounselors() {
            return counselors;
        }

        public void setCounselors(List<CounselorsList> counselors) {
            this.counselors = counselors;
        }

        public List<IntroImagesList> getIntroImages() {
            return introImages;
        }

        public void setIntroImages(List<IntroImagesList> introImages) {
            this.introImages = introImages;
        }


        public boolean isCollect() {
            return isCollect;
        }

        public void setCollect(boolean collect) {
            isCollect = collect;
        }

        public String getOrderCounts() {
            return orderCounts;
        }

        public void setOrderCounts(String orderCounts) {
            this.orderCounts = orderCounts;
        }

        public String getBuildingArea() {
            return buildingArea;
        }

        public void setBuildingArea(String buildingArea) {
            this.buildingArea = buildingArea;
        }

        public String getBrowseCount() {
            return browseCount;
        }

        public void setBrowseCount(String browseCount) {
            this.browseCount = browseCount;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public List<ImagesList> getImages() {
            return images;
        }

        public void setImages(List<ImagesList> images) {
            this.images = images;
        }

        public String getCarrierName() {
            return carrierName;
        }

        public void setCarrierName(String carrierName) {
            this.carrierName = carrierName;
        }

        public String getPanoramaurl() {
            return panoramaurl;
        }

        public void setPanoramaurl(String panoramaurl) {
            this.panoramaurl = panoramaurl;
        }

        public String getCarrierid() {
            return carrierid;
        }

        public void setCarrierid(String carrierid) {
            this.carrierid = carrierid;
        }

        public String getPriceSell() {
            return priceSell;
        }

        public void setPriceSell(String priceSell) {
            this.priceSell = priceSell;
        }
    }
    public class LabelsList extends NetResult{
        private String id;
        private String label;

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
    public class Parent extends NetResult{
        private String carrierType;
        private String carrierName;
        private String images;
        private String carrierid;

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

        public String getCarrierid() {
            return carrierid;
        }

        public void setCarrierid(String carrierid) {
            this.carrierid = carrierid;
        }
    }
    public class CounselorsList extends NetResult{
        private String portrait;
        private String counselorid;
        private String fullname;

        public String getPortrait() {
            return portrait;
        }

        public void setPortrait(String portrait) {
            this.portrait = portrait;
        }

        public String getCounselorid() {
            return counselorid;
        }

        public void setCounselorid(String counselorid) {
            this.counselorid = counselorid;
        }

        public String getFullname() {
            return fullname;
        }

        public void setFullname(String fullname) {
            this.fullname = fullname;
        }
    }
    public class IntroImagesList extends NetResult{
        private String id;
        private String sort;
        private String imageType;
        private String conver;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getSort() {
            return sort;
        }

        public void setSort(String sort) {
            this.sort = sort;
        }

        public String getImageType() {
            return imageType;
        }

        public void setImageType(String imageType) {
            this.imageType = imageType;
        }

        public String getConver() {
            return conver;
        }

        public void setConver(String conver) {
            this.conver = conver;
        }
    }
    public class ImagesList extends NetResult{
        private String id;
        private String sort;
        private String imageType;
        private String conver;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getSort() {
            return sort;
        }

        public void setSort(String sort) {
            this.sort = sort;
        }

        public String getImageType() {
            return imageType;
        }

        public void setImageType(String imageType) {
            this.imageType = imageType;
        }

        public String getConver() {
            return conver;
        }

        public void setConver(String conver) {
            this.conver = conver;
        }
    }
}
