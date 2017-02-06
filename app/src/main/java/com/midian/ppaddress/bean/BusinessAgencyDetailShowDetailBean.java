package com.midian.ppaddress.bean;

import android.util.Log;

import com.google.gson.JsonSyntaxException;

import java.util.List;

import midian.baselib.app.AppException;
import midian.baselib.bean.NetResult;

/**
 * 首页1
 * Created by chu on 2016/5/8.
 */
public class BusinessAgencyDetailShowDetailBean extends NetResult {
    public static BusinessAgencyDetailShowDetailBean parse(String json) throws AppException {
        BusinessAgencyDetailShowDetailBean res = new BusinessAgencyDetailShowDetailBean();
        try {
            res = gson.fromJson(json, BusinessAgencyDetailShowDetailBean.class);
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
        private Agency agency;
        private List<Agencyexpert> agencyexpert;

        public Agency getAgency() {
            return agency;
        }

        public void setAgency(Agency agency) {
            this.agency = agency;
        }

        public List<Agencyexpert> getAgencyexpert() {
            return agencyexpert;
        }

        public void setAgencyexpert(List<Agencyexpert> agencyexpert) {
            this.agencyexpert = agencyexpert;
        }
    }
    public class Agency extends NetResult{
        private String id;
        private String logo;
        private String goodRate;
        private String labels;
        private String name;
        private String adver;
        private String serviceCount;
        private String favorite;
        private String favoriteid;
        private String intro;

        public String getFavoriteid() {
            return favoriteid;
        }

        public void setFavoriteid(String favoriteid) {
            this.favoriteid = favoriteid;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getLogo() {
            return logo;
        }

        public void setLogo(String logo) {
            this.logo = logo;
        }

        public String getGoodRate() {
            return goodRate;
        }

        public void setGoodRate(String goodRate) {
            this.goodRate = goodRate;
        }

        public String getLabels() {
            return labels;
        }

        public void setLabels(String labels) {
            this.labels = labels;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAdver() {
            return adver;
        }

        public void setAdver(String adver) {
            this.adver = adver;
        }

        public String getServiceCount() {
            return serviceCount;
        }

        public void setServiceCount(String serviceCount) {
            this.serviceCount = serviceCount;
        }

        public String getFavorite() {
            return favorite;
        }

        public void setFavorite(String favorite) {
            this.favorite = favorite;
        }

        public String getIntro() {
            return intro;
        }

        public void setIntro(String intro) {
            this.intro = intro;
        }
    }
    public class Agencyexpert extends NetResult{
        private String id;
        private String portrait;
        private String fullname;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getPortrait() {
            return portrait;
        }

        public void setPortrait(String portrait) {
            this.portrait = portrait;
        }

        public String getFullname() {
            return fullname;
        }

        public void setFullname(String fullname) {
            this.fullname = fullname;
        }
    }
}
