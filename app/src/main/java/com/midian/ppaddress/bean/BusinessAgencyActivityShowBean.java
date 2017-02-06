package com.midian.ppaddress.bean;

import com.google.gson.JsonSyntaxException;

import java.util.List;

import midian.baselib.app.AppException;
import midian.baselib.bean.NetResult;

/**
 * 首页1
 * Created by chu on 2016/5/8.
 */
public class BusinessAgencyActivityShowBean extends NetResult {
    public static BusinessAgencyActivityShowBean parse(String json) throws AppException {
        BusinessAgencyActivityShowBean res = new BusinessAgencyActivityShowBean();
        try {
            res = gson.fromJson(json, BusinessAgencyActivityShowBean.class);
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
        private String title;
        private String applied;
        private String url;
        private String isapply;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getApplied() {
            return applied;
        }

        public void setApplied(String applied) {
            this.applied = applied;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getIsapply() {
            return isapply;
        }

        public void setIsapply(String isapply) {
            this.isapply = isapply;
        }
    }
}
