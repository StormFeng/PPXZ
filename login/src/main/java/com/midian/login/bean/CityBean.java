package com.midian.login.bean;

import com.google.gson.JsonSyntaxException;

import midian.baselib.app.AppException;
import midian.baselib.bean.NetResult;

/**
 * 10.2获取城市列表
 * Created by Administrator on 2015/12/16 0016.
 */
public class CityBean extends NetResult {
    public static CityBean parse(String json) throws AppException {
        CityBean res = new CityBean();
        try {
            res = gson.fromJson(json, CityBean.class);
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
            throw AppException.json(e);
        }
        // System.out.println(res);
        return res;
    }

    private CityContent content;

    public CityContent getContent() {
        return content;
    }

    public void setContent(CityContent content) {
        this.content = content;
    }

    public class CityContent {
        private String city_id;//城市id
        private String city_name;//城市名称

        public String getCity_id() {
            return city_id;
        }

        public void setCity_id(String city_id) {
            this.city_id = city_id;
        }

        public String getCity_name() {
            return city_name;
        }

        public void setCity_name(String city_name) {
            this.city_name = city_name;
        }
    }
}
