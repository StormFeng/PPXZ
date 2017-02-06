package com.midian.ppaddress.bean;

import com.google.gson.JsonSyntaxException;

import java.util.List;

import midian.baselib.app.AppException;
import midian.baselib.bean.NetResult;

/**
 *8.9选址-载体详情-点评列表-点赞功能
 * Created by chu on 2016/5/8.
 */
public class BusinessCarrierCommentsClickLikeBean extends NetResult {
    public static BusinessCarrierCommentsClickLikeBean parse(String json) throws AppException {
        BusinessCarrierCommentsClickLikeBean res = new BusinessCarrierCommentsClickLikeBean();
        try {
            res = gson.fromJson(json, BusinessCarrierCommentsClickLikeBean.class);
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
        private String id;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }
    }
}
