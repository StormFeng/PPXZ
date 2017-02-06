package com.midian.ppaddress.bean;

import com.google.gson.JsonSyntaxException;

import java.util.ArrayList;
import java.util.List;

import midian.baselib.app.AppException;
import midian.baselib.bean.NetResult;

/**
 * 首页1
 * Created by chu on 2016/5/8.
 */
public class AppIndexBean extends NetResult {
    public static AppIndexBean parse(String json) throws AppException {
        AppIndexBean res = new AppIndexBean();
        try {
            res = gson.fromJson(json, AppIndexBean.class);
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
            throw AppException.json(e);
        }
        return res;
    }

    private IndexData data;

    public IndexData getData() {
        return data;
    }

    public void setData(IndexData data) {
        this.data = data;
    }

    public class IndexData extends NetResult {
        private ArrayList<RecommendAgencys> recommendAgencys;//-------推荐的服务机构
        private ArrayList<RecommendCarriers> recommendCarriers;//-------推荐的载体
        private String investEnvironUrl;//http://xxx/f8af”,   -----投资环境URL地址
        private ArrayList<RecommendCounselors> recommendCounselors;//推荐顾问
        private ArrayList<Banners> banners;


        public ArrayList<Banners> getBanners() {
            return banners;
        }

        public void setBanners(ArrayList<Banners> banners) {
            this.banners = banners;
        }

        public ArrayList<RecommendAgencys> getRecommendAgencys() {
            return recommendAgencys;
        }

        public void setRecommendAgencys(ArrayList<RecommendAgencys> recommendAgencys) {
            this.recommendAgencys = recommendAgencys;
        }

        public ArrayList<RecommendCarriers> getRecommendCarriers() {
            return recommendCarriers;
        }

        public void setRecommendCarriers(ArrayList<RecommendCarriers> recommendCarriers) {
            this.recommendCarriers = recommendCarriers;
        }

        public String getInvestEnvironUrl() {
            return investEnvironUrl;
        }

        public void setInvestEnvironUrl(String investEnvironUrl) {
            this.investEnvironUrl = investEnvironUrl;
        }

        public ArrayList<RecommendCounselors> getRecommendCounselors() {
            return recommendCounselors;
        }

        public void setRecommendCounselors(ArrayList<RecommendCounselors> recommendCounselors) {
            this.recommendCounselors = recommendCounselors;
        }
    }


    public class Banners extends NetResult {
        private String image;//": "http://img.thumb.pploc.com/9d26.jpg",
        private String carrierType;//": 2,
        private String sort;//": 1,
        private String follow;//": "",
        private String carrierid;//": 1,
        private String isfollow;//": 0

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getCarrierType() {
            return carrierType;
        }

        public void setCarrierType(String carrierType) {
            this.carrierType = carrierType;
        }

        public String getSort() {
            return sort;
        }

        public void setSort(String sort) {
            this.sort = sort;
        }

        public String getFollow() {
            return follow;
        }

        public void setFollow(String follow) {
            this.follow = follow;
        }

        public String getCarrierid() {
            return carrierid;
        }

        public void setCarrierid(String carrierid) {
            this.carrierid = carrierid;
        }

        public String getIsfollow() {
            return isfollow;
        }

        public void setIsfollow(String isfollow) {
            this.isfollow = isfollow;
        }
    }


    public class RecommendAgencys extends NetResult {
        private String logo;//": "http://xxx/223c.jpg",       -------服务机构logo
        private String sort;//": 1,       -------排序
        private String agencyid;//": 1,机构id
        private String advertid;// 3,       -------服务机构广告id
        private String adverImage;//"http://xxx/06ae.jpg",       -------服务机构宣传背景图
        private String favorite;// 1,       -------会员是否已收藏，1：已收藏，0：未收藏
        private String name;//"facebook",       -------服务机构名称
        private String adwords;// "全球最大的社交网站"       -------服务机构广告语
        private String id;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getAgencyid() {
            return agencyid;
        }

        public void setAgencyid(String agencyid) {
            this.agencyid = agencyid;
        }

        public String getLogo() {
            return logo;
        }

        public void setLogo(String logo) {
            this.logo = logo;
        }

        public String getSort() {
            return sort;
        }

        public void setSort(String sort) {
            this.sort = sort;
        }

        public String getAdvertid() {
            return advertid;
        }

        public void setAdvertid(String advertid) {
            this.advertid = advertid;
        }

        public String getAdverImage() {
            return adverImage;
        }

        public void setAdverImage(String adverImage) {
            this.adverImage = adverImage;
        }

        public String getFavorite() {
            return favorite;
        }

        public void setFavorite(String favorite) {
            this.favorite = favorite;
        }


        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAdwords() {
            return adwords;
        }

        public void setAdwords(String adwords) {
            this.adwords = adwords;
        }
    }

    public class RecommendCarriers extends NetResult {
        private String landArea;// 10, -------土地面积
        private String saleRental;// 1, -------租售方式，0为租售，1为租，2为售
        private String priceRent;// 20, -------出租单价
        private String county;// "天河", -------所在区县
        private String carrierType;// 8,-------载体类型，[1]：园区，[2]：综合体，[3]：土地，[4]：写字楼，[6]：厂房，[8]：仓库
        private List<Images> images;//
        private String carrierName;// "沁园仓库",       -------载体名称
        private String carrierid;// 59,       -------载体id
        private String priceSell;// 30,       -------出售单价
        private String buildingArea;// 20,       -------土地面积
        private String city;// "广州"       -------所在城市

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

        public String getCounty() {
            return county;
        }

        public void setCounty(String county) {
            this.county = county;
        }

        public String getCarrierType() {
            return carrierType;
        }

        public void setCarrierType(String carrierType) {
            this.carrierType = carrierType;
        }

        public List<Images> getImages() {
            return images;
        }

        public void setImages(List<Images> images) {
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

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }
    }

    public class RecommendCounselors extends NetResult {
        private String memberid;// 54,       -------服务达人id
        private String carrierType;// 8,       -------载体类型，[1]：园区，[2]：综合体，[3]：土地，[4]：写字楼，[6]：厂房，[8]：仓库
        private Carrier carrier;
        private String uuid;// "d77bf0ab652e4400b950bf425bd68b27",-------载体uuid
        private String carrierid;// 59,       -------载体id
        private String adwords;//"写字楼推荐",       -------载体广告语
        private String portrait;//"http://xxx/628f.jpg"       -------服务达人头像

        public String getMemberid() {
            return memberid;
        }

        public void setMemberid(String memberid) {
            this.memberid = memberid;
        }

        public String getCarrierType() {
            return carrierType;
        }

        public void setCarrierType(String carrierType) {
            this.carrierType = carrierType;
        }

        public Carrier getCarrier() {
            return carrier;
        }

        public void setCarrier(Carrier carrier) {
            this.carrier = carrier;
        }

        public String getUuid() {
            return uuid;
        }

        public void setUuid(String uuid) {
            this.uuid = uuid;
        }

        public String getCarrierid() {
            return carrierid;
        }

        public void setCarrierid(String carrierid) {
            this.carrierid = carrierid;
        }

        public String getAdwords() {
            return adwords;
        }

        public void setAdwords(String adwords) {
            this.adwords = adwords;
        }

        public String getPortrait() {
            return portrait;
        }

        public void setPortrait(String portrait) {
            this.portrait = portrait;
        }
    }

    public class Images extends NetResult {
        private String id;// 306,       -------图片id
        private String sort;// 0,       -------图片顺序
        private String imageType;// 0,  -------载体图片类型,0为介绍图片，1为简介图片
        private String conver;// "http://xxx/f8af.jpg"       -------载体图片地址

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

    public class Carrier extends NetResult {
        private String saleRental;//1,       -------租售方式，0为租售，1为租，2为售
        private String priceRent;//20,       -------出租单价
        private String carrierType;// 8,       -------载体类型，[1]：园区，[2]：综合体，[3]：土地，[4]：写字楼，[6]：厂房，[8]：仓库
        private String images;//"http://xxx/f74f.jpg",      -------载体图片
        private String carrierName;// "沁园仓库",      -------载体名称
        private String carrierid;// 59,      -------载体id
        private String priceSell;// 30      -------出售单价

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

        public String getCarrierType() {
            return carrierType;
        }

        public void setCarrierType(String carrierType) {
            this.carrierType = carrierType;
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

        public String getPriceSell() {
            return priceSell;
        }

        public void setPriceSell(String priceSell) {
            this.priceSell = priceSell;
        }
    }

}
