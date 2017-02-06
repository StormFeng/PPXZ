package com.midian.ppaddress.bean;

import com.google.gson.JsonSyntaxException;

import midian.baselib.app.AppException;
import midian.baselib.bean.NetResult;

/**
 * 首页1
 * Created by chu on 2016/5/8.
 */
public class MessageCountInnerMessageBean extends NetResult {
    public static MessageCountInnerMessageBean parse(String json) throws AppException {
        MessageCountInnerMessageBean res = new MessageCountInnerMessageBean();
        try {
            res = gson.fromJson(json, MessageCountInnerMessageBean.class);
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
        private String consults;
        private String orders;

        public String getConsults() {
            return consults;
        }

        public void setConsults(String consults) {
            this.consults = consults;
        }

        public String getOrders() {
            return orders;
        }

        public void setOrders(String orders) {
            this.orders = orders;
        }
    }
}
