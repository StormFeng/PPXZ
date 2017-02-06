package com.midian.ppaddress.bean;

import com.google.gson.JsonSyntaxException;

import midian.baselib.app.AppException;
import midian.baselib.bean.NetResult;


public class ConsultantDetailBean extends NetResult {
    public static ConsultantDetailBean parse(String json) throws AppException {
        ConsultantDetailBean res = new ConsultantDetailBean();
        try {
            res = gson.fromJson(json, ConsultantDetailBean.class);
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
        private String sex;//": 0,
        private String declaration;//”: “提供国内顶级信息化招商服务”,
        private String memberid;//”: 54,
        private String agencyCompany;//”: “优利普斯”,
        private String goodRate;//”: 98,
        private String images;//”: “http://XXX/0f435f3972628f.jpg”,
        private String fullname;//”: “姚总”,
        private String adverimage;//”: “http://XXX/0f435f3972628f.jpg

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public String getDeclaration() {
            return declaration;
        }

        public void setDeclaration(String declaration) {
            this.declaration = declaration;
        }

        public String getMemberid() {
            return memberid;
        }

        public void setMemberid(String memberid) {
            this.memberid = memberid;
        }

        public String getAgencyCompany() {
            return agencyCompany;
        }

        public void setAgencyCompany(String agencyCompany) {
            this.agencyCompany = agencyCompany;
        }

        public String getGoodRate() {
            return goodRate;
        }

        public void setGoodRate(String goodRate) {
            this.goodRate = goodRate;
        }

        public String getImages() {
            return images;
        }

        public void setImages(String images) {
            this.images = images;
        }

        public String getFullname() {
            return fullname;
        }

        public void setFullname(String fullname) {
            this.fullname = fullname;
        }

        public String getAdverimage() {
            return adverimage;
        }

        public void setAdverimage(String adverimage) {
            this.adverimage = adverimage;
        }
    }
}
