package com.midian.ppaddress.bean;

import com.google.gson.JsonSyntaxException;

import java.util.List;

import midian.baselib.app.AppException;
import midian.baselib.bean.NetResult;

/**
 * 14.12个人中心-投诉反馈-类型列表
 * Created by chu on 2016/5/9.
 */
public class FeedBackListBean extends NetResult {
    public static FeedBackListBean parse(String json) throws AppException {
        FeedBackListBean res = new FeedBackListBean();
        try {
            res = gson.fromJson(json, FeedBackListBean.class);
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
            throw AppException.json(e);
        }
        return res;
    }

    private List<Data> data;

    public List<Data> getData() {
        return data;
    }

    public void setData(List<Data> data) {
        this.data = data;
    }

    public class Data extends NetResult {
        private String id;//": 1,
        private String name;//": "投诉假客商

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
