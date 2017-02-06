package com.midian.ppaddress.bean;

import com.google.gson.JsonSyntaxException;

import java.util.List;

import midian.baselib.app.AppException;
import midian.baselib.bean.NetResult;

/**
 * 18.1个人中心-我(客商中介)的推荐（已成交）
 * Created by chu on 2016/5/8.
 */
public class ClinchDealListBean extends NetResult {
    public static ClinchDealListBean parse(String json) throws AppException {
        ClinchDealListBean res = new ClinchDealListBean();
        try {
            res = gson.fromJson(json, ClinchDealListBean.class);
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

        private String lastPage;//: true,
        private String pageSize;//: 15,
        private String pageNumber;//: 1,
        private List<ClinchDealList>list;//已成交
        private String   firstPage;//: true,
        private String  totalRow;//: 5,
        private String  totalPage;//: 1

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

        public List<ClinchDealList> getList() {
            return list;
        }

        public void setList(List<ClinchDealList> list) {
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

    /**
     * 已成交
     */
    public class ClinchDealList extends NetResult {
        private String updateTime;//": "2016-04-11 18:29:54",
        private String sharerid;//": 59,
        private String money;//": "",
        private String portrait;//": "http:// XXX/4847e0.jpg",
        private String carrierName;//": "南国国际公馆厂房",
        private String number;//": "0",
        private String memberName;//": "马云",
        private String dealarea;//": 5000,
        private String stage;//": 6

        public String getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(String updateTime) {
            this.updateTime = updateTime;
        }

        public String getSharerid() {
            return sharerid;
        }

        public void setSharerid(String sharerid) {
            this.sharerid = sharerid;
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

        public String getCarrierName() {
            return carrierName;
        }

        public void setCarrierName(String carrierName) {
            this.carrierName = carrierName;
        }

        public String getNumber() {
            return number;
        }

        public void setNumber(String number) {
            this.number = number;
        }

        public String getMemberName() {
            return memberName;
        }

        public void setMemberName(String memberName) {
            this.memberName = memberName;
        }

        public String getDealarea() {
            return dealarea;
        }

        public void setDealarea(String dealarea) {
            this.dealarea = dealarea;
        }

        public String getStage() {
            return stage;
        }

        public void setStage(String stage) {
            this.stage = stage;
        }
    }

}
