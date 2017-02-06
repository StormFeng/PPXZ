package com.midian.ppaddress.bean;

import com.google.gson.JsonSyntaxException;

import midian.baselib.app.AppException;
import midian.baselib.bean.NetResult;

/**
 * 14.16个人中心-个人主页屏幕
 * Created by chu on 2016/5/9.
 */
public class PersonalIndexBean extends NetResult {
    public static PersonalIndexBean parse(String json) throws AppException {
        PersonalIndexBean res = new PersonalIndexBean();
        try {
            res = gson.fromJson(json, PersonalIndexBean.class);
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
        private String myRecoms;//": 0,
        private String orderTotal;//": 1,
        private String consults;
        private String myMsgs;//": 8,
        private String waitPay;//": 500,
        private String hasPay;//": 860,
        private String myOrders;//": 0

        public String getConsults() {
            return consults;
        }

        public void setConsults(String consults) {
            this.consults = consults;
        }

        public String getMyRecoms() {
            return myRecoms;
        }

        public void setMyRecoms(String myRecoms) {
            this.myRecoms = myRecoms;
        }

        public String getOrderTotal() {
            return orderTotal;
        }

        public void setOrderTotal(String orderTotal) {
            this.orderTotal = orderTotal;
        }

        public String getMyMsgs() {
            return myMsgs;
        }

        public void setMyMsgs(String myMsgs) {
            this.myMsgs = myMsgs;
        }

        public String getWaitPay() {
            return waitPay;
        }

        public void setWaitPay(String waitPay) {
            this.waitPay = waitPay;
        }

        public String getHasPay() {
            return hasPay;
        }

        public void setHasPay(String hasPay) {
            this.hasPay = hasPay;
        }

        public String getMyOrders() {
            return myOrders;
        }

        public void setMyOrders(String myOrders) {
            this.myOrders = myOrders;
        }
    }
}
