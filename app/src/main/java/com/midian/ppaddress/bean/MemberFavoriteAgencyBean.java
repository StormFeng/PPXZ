package com.midian.ppaddress.bean;

import com.google.gson.JsonSyntaxException;

import midian.baselib.app.AppException;
import midian.baselib.bean.NetResult;

/**
 * 首页1
 * Created by chu on 2016/5/8.
 */
public class MemberFavoriteAgencyBean extends NetResult {
    public static MemberFavoriteAgencyBean parse(String json) throws AppException {
        MemberFavoriteAgencyBean res = new MemberFavoriteAgencyBean();
        try {
            res = gson.fromJson(json, MemberFavoriteAgencyBean.class);
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
