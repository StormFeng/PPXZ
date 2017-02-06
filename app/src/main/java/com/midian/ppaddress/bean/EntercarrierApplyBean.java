package com.midian.ppaddress.bean;

import com.google.gson.JsonSyntaxException;

import midian.baselib.app.AppException;
import midian.baselib.bean.NetResult;

/**
 * 17.5选址-写字楼详情-提交入驻申请
 * Created by chu on 2016/5/9.
 */
public class EntercarrierApplyBean extends NetResult {
    public static EntercarrierApplyBean parse(String json) throws AppException {
        EntercarrierApplyBean res = new EntercarrierApplyBean();
        try {
            res = gson.fromJson(json, EntercarrierApplyBean.class);
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

    public class Data {
        private String id;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }
    }

}
