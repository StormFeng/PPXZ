package com.midian.ppaddress.bean;

import com.google.gson.JsonSyntaxException;

import midian.baselib.app.AppException;
import midian.baselib.bean.NetResult;

/**
 * 12.1 收藏载体
 * 除去巫山不是云
 * Created by chu on 2016/5/8.
 */
public class MemberFavoriteCarrierBean extends NetResult {
    public static MemberFavoriteCarrierBean parse(String json) throws AppException {
        MemberFavoriteCarrierBean res = new MemberFavoriteCarrierBean();
        try {
            res = gson.fromJson(json, MemberFavoriteCarrierBean.class);
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
