package com.midian.ppaddress.bean;

import com.google.gson.JsonSyntaxException;

import midian.baselib.app.AppException;
import midian.baselib.bean.NetResult;

/**
 * 16.16个人中心-我（物业顾问）的预约-修改预约阶段
 * Created by chu on 2016/5/9.
 */
public class UpdateStageBean extends NetResult {
    public static UpdateStageBean parse(String json) throws AppException {
        UpdateStageBean res = new UpdateStageBean();
        try {
            res = gson.fromJson(json, UpdateStageBean.class);
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
        private String orderid;//": 4,
        private String stage;//": 3

        public String getOrderid() {
            return orderid;
        }

        public void setOrderid(String orderid) {
            this.orderid = orderid;
        }

        public String getStage() {
            return stage;
        }

        public void setStage(String stage) {
            this.stage = stage;
        }
    }
}
