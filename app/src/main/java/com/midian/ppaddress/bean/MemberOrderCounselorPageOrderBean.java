package com.midian.ppaddress.bean;

import com.google.gson.JsonSyntaxException;

import java.util.List;

import midian.baselib.app.AppException;
import midian.baselib.bean.NetResult;

/**
 * 16.8个人中心-我(物业顾问)的订单（已签约、已完成）
 * Created by chu on 2016/5/9.
 */
public class MemberOrderCounselorPageOrderBean extends NetResult {
    public static MemberOrderCounselorPageOrderBean parse(String json) throws AppException {
        MemberOrderCounselorPageOrderBean res = new MemberOrderCounselorPageOrderBean();
        try {
            res = gson.fromJson(json, MemberOrderCounselorPageOrderBean.class);
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
        private String contactLocat;//": "广州市天河区天府路100号",
        private String carrierType;//": 6,
        private String coverimg;//": "http://xxx/701.jpeg",
        private String number;//": "abcd",
        private String counselorName;//": "李彦宏",
        private String code;//": "C-20160329",
        private String stage;//": 5,
        private String id;//": 1,
        private String startTime;//": "2016-04-05 17:00:59",
        private String mobilephone;//": "13827864920",
        private String carrierName;//": "南国国际公馆厂房",
        private String images;//": "http://xxx/7e0.jpg",
        private String memberName;//": "马云",
        private String carrierid;//": 85,
        private String money;//": 2000.00,
        private String portrait;//": "http:// XXX/3.jpg

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

        public String getCoverimg() {
            return coverimg;
        }

        public void setCoverimg(String coverimg) {
            this.coverimg = coverimg;
        }

        public String getNumber() {
            return number;
        }

        public void setNumber(String number) {
            this.number = number;
        }

        public String getCounselorName() {
            return counselorName;
        }

        public void setCounselorName(String counselorName) {
            this.counselorName = counselorName;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getStage() {
            return stage;
        }

        public void setStage(String stage) {
            this.stage = stage;
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

        public String getMemberName() {
            return memberName;
        }

        public void setMemberName(String memberName) {
            this.memberName = memberName;
        }

        public String getCarrierid() {
            return carrierid;
        }

        public void setCarrierid(String carrierid) {
            this.carrierid = carrierid;
        }

        public String getMoney() {
            return money;
        }

        public void setMoney(String money) {
            this.money = money;
        }

        public String getPortrait() {
            return portrait;
        }

        public void setPortrait(String portrait) {
            this.portrait = portrait;
        }
    }

}
