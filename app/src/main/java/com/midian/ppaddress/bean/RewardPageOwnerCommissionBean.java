package com.midian.ppaddress.bean;

import com.google.gson.JsonSyntaxException;

import java.util.List;

import midian.baselib.app.AppException;
import midian.baselib.bean.NetResult;

/**
 * 19.1我的打赏
 * Created by chu on 2016/5/8.
 */
public class RewardPageOwnerCommissionBean extends NetResult {
    public static RewardPageOwnerCommissionBean parse(String json) throws AppException {
        RewardPageOwnerCommissionBean res = new RewardPageOwnerCommissionBean();
        try {
            res = gson.fromJson(json, RewardPageOwnerCommissionBean.class);
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
        private Commissions commissions;
        private String hasPay;// 24201000,-------已支付金额
        private String totalMoney;// 29401000-----打赏总金额

        public String getTotalMoney() {
            return totalMoney;
        }

        public void setTotalMoney(String totalMoney) {
            this.totalMoney = totalMoney;
        }

        public String getHasPay() {
            return hasPay;
        }

        public void setHasPay(String hasPay) {
            this.hasPay = hasPay;
        }

        public Commissions getCommissions() {
            return commissions;
        }

        public void setCommissions(Commissions commissions) {
            this.commissions = commissions;
        }

    }


    public class Commissions extends NetResult {
        private String lastPage;// true,
        private String pageSize;//15,
        private String pageNumber;//1,
        private List<MoneyList> list;//
        private String firstPage;// true,
        private String totalRow;//7,
        private String totalPage;//: 1


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

        public List<MoneyList> getList() {
            return list;
        }

        public void setList(List<MoneyList> list) {
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

    public class MoneyList extends NetResult {
        private String createTime;//": "2016-05-03 10:39:41",
        private String money;//": 1000,
        private String carrierName;//": "花都花东镇杨二村地块",
        private String carrierCode;//": "L-2016042615",
        private String carrierid;//": 15,
        private String stage;//: 11

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getMoney() {
            return money;
        }

        public void setMoney(String money) {
            this.money = money;
        }

        public String getCarrierName() {
            return carrierName;
        }

        public void setCarrierName(String carrierName) {
            this.carrierName = carrierName;
        }

        public String getCarrierCode() {
            return carrierCode;
        }

        public void setCarrierCode(String carrierCode) {
            this.carrierCode = carrierCode;
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
    }

}
