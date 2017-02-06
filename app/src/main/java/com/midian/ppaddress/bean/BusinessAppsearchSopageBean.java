package com.midian.ppaddress.bean;

import com.google.gson.JsonSyntaxException;

import java.util.List;

import midian.baselib.app.AppException;
import midian.baselib.bean.NetResult;

/**
 * Created by chu on 2016/5/6.
 */
public class BusinessAppsearchSopageBean extends NetResult {
    public static BusinessAppsearchSopageBean parse(String json) throws AppException {
        BusinessAppsearchSopageBean res = new BusinessAppsearchSopageBean();
        try {
            res = gson.fromJson(json, BusinessAppsearchSopageBean.class);
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
        private boolean lasePage;//true
        private String paseSize;//15
        private String pageNumber;//1
        private String firstPage;//true
        private List<CarrierList> list;
        private String totalRow;//1
        private String totalPage;//1

        public boolean isLasePage() {
            return lasePage;
        }

        public void setLasePage(boolean lasePage) {
            this.lasePage = lasePage;
        }

        public String getPaseSize() {
            return paseSize;
        }

        public void setPaseSize(String paseSize) {
            this.paseSize = paseSize;
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

        public List<CarrierList> getList() {
            return list;
        }

        public void setList(List<CarrierList> list) {
            this.list = list;
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
     public class CarrierList extends NetResult{
         private String carrierid;//57
         private String carrieruuid;// "67a0fe3f9f0d4fa58436ecc0a5107f69"
         private String carrierType;//6
         private String showName;//侨建御溪谷厂房
         private String images;//http://xxx/310.jpeg
         private String saleRental;//0
         private String priceRent;//20
         private String priceSell;//30
         private String county;//增城市
         private String memberid;//54
         private String sex;//0
         private String fullname;//姚总
         private String portrait;//http://xxx/28f.jpg
         private String hasEnter;//1
         private List<String> introImages;

         public List<String> getIntroImages() {
             return introImages;
         }

         public void setIntroImages(List<String> introImages) {
             this.introImages = introImages;
         }

         public String getCarrierid() {
             return carrierid;
         }

         public void setCarrierid(String carrierid) {
             this.carrierid = carrierid;
         }

         public String getCarrieruuid() {
             return carrieruuid;
         }

         public void setCarrieruuid(String carrieruuid) {
             this.carrieruuid = carrieruuid;
         }

         public String getCarrierType() {
             return carrierType;
         }

         public void setCarrierType(String carrierType) {
             this.carrierType = carrierType;
         }

         public String getShowName() {
             return showName;
         }

         public void setShowName(String showName) {
             this.showName = showName;
         }

         public String getImages() {
             return images;
         }

         public void setImages(String images) {
             this.images = images;
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

         public String getCounty() {
             return county;
         }

         public void setCounty(String county) {
             this.county = county;
         }

         public String getMemberid() {
             return memberid;
         }

         public void setMemberid(String memberid) {
             this.memberid = memberid;
         }

         public String getSex() {
             return sex;
         }

         public void setSex(String sex) {
             this.sex = sex;
         }

         public String getFullname() {
             return fullname;
         }

         public void setFullname(String fullname) {
             this.fullname = fullname;
         }

         public String getPortrait() {
             return portrait;
         }

         public void setPortrait(String portrait) {
             this.portrait = portrait;
         }

         public String getHasEnter() {
             return hasEnter;
         }

         public void setHasEnter(String hasEnter) {
             this.hasEnter = hasEnter;
         }
     }



}
