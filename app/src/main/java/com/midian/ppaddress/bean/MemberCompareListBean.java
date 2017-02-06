package com.midian.ppaddress.bean;

import com.google.gson.JsonSyntaxException;

import java.util.ArrayList;
import java.util.List;

import midian.baselib.app.AppException;
import midian.baselib.bean.NetResult;

/**
 *  9.2个人中心-我的对比-对比列表
 * Created by chu on 2016/5/6.
 */
public class MemberCompareListBean extends NetResult {
    public static MemberCompareListBean parse(String json) throws AppException {
        MemberCompareListBean res = new MemberCompareListBean();
        try {
            res = gson.fromJson(json, MemberCompareListBean.class);
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
            throw AppException.json(e);
        }
        return res;
    }

    private ArrayList<CompareData> data;

    public ArrayList<CompareData> getData() {
        return data;
    }

    public void setData(ArrayList<CompareData> data) {
        this.data = data;
    }

    public class CompareData extends NetResult {
        private String id;
        private String cover;
        private String carrierid;
        private String carrierType;
        private String carrierName;
        private boolean isChecked;

        public boolean isChecked() {
            return isChecked;
        }

        public void setChecked(boolean checked) {
            isChecked = checked;
        }

        public String getCarrierName() {
            return carrierName;
        }

        public void setCarrierName(String carrierName) {
            this.carrierName = carrierName;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getCover() {
            return cover;
        }

        public void setCover(String cover) {
            this.cover = cover;
        }

        public String getCarrierid() {
            return carrierid;
        }

        public void setCarrierid(String carrierid) {
            this.carrierid = carrierid;
        }

        public String getCarrierType() {
            return carrierType;
        }

        public void setCarrierType(String carrierType) {
            this.carrierType = carrierType;
        }
    }
}
