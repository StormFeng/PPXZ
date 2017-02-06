package com.midian.ppaddress.bean;

import com.google.gson.JsonSyntaxException;

import midian.baselib.app.AppException;
import midian.baselib.bean.NetResult;

/**
 * 16.6选址-载体详情-物业顾问详情-委托选址页
 * Created by chu on 2016/5/9.
 */
public class MemberOrderMemberSitedetailsBean extends NetResult {
    public static MemberOrderMemberSitedetailsBean parse(String json) throws AppException {
        MemberOrderMemberSitedetailsBean res = new MemberOrderMemberSitedetailsBean();
        try {
            res = gson.fromJson(json, MemberOrderMemberSitedetailsBean.class);
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
       private String  mobilephone;//": "13827864920",
        private String memberid;//": 53,
        private String counselorid;//": 73

        public String getMobilephone() {
            return mobilephone;
        }

        public void setMobilephone(String mobilephone) {
            this.mobilephone = mobilephone;
        }

        public String getMemberid() {
            return memberid;
        }

        public void setMemberid(String memberid) {
            this.memberid = memberid;
        }

        public String getCounselorid() {
            return counselorid;
        }

        public void setCounselorid(String counselorid) {
            this.counselorid = counselorid;
        }
    }
}
