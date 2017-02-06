package com.midian.ppaddress.bean;

import com.google.gson.JsonSyntaxException;

import midian.baselib.app.AppException;
import midian.baselib.bean.NetResult;

/**
 * Created by chu on 2016/6/14.
 */
public class AppInfoBean extends NetResult {
    public static AppInfoBean parse(String json) throws AppException {
        AppInfoBean res = new AppInfoBean();
        try {
            res = gson.fromJson(json, AppInfoBean.class);
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
            throw AppException.json(e);
        }
        return res;
    }


    private AppData data;


    public AppData getData() {
        return data;
    }

    public void setData(AppData data) {
        this.data = data;
    }

    public class AppData extends NetResult {
        private String shareurl;//": "http://www.pploc.com",
        private String shareimg;//": "http://www.pploc.com/resources/images/favicon.ico",
        private String sharetxt;//": "PP优选，选址招商两不误，值得你信赖的平台！",
        private String sharetitle;//": "PP优选，选址招商两不误！

        public String getShareurl() {
            return shareurl;
        }

        public void setShareurl(String shareurl) {
            this.shareurl = shareurl;
        }

        public String getShareimg() {
            return shareimg;
        }

        public void setShareimg(String shareimg) {
            this.shareimg = shareimg;
        }

        public String getSharetxt() {
            return sharetxt;
        }

        public void setSharetxt(String sharetxt) {
            this.sharetxt = sharetxt;
        }

        public String getSharetitle() {
            return sharetitle;
        }

        public void setSharetitle(String sharetitle) {
            this.sharetitle = sharetitle;
        }
    }

}
