package com.midian.ppaddress.bean;

import com.google.gson.JsonSyntaxException;

import java.util.List;

import midian.baselib.app.AppException;
import midian.baselib.bean.NetResult;

/**
 * 首页1
 * Created by chu on 2016/5/8.
 */
public class MemberMembershipInfoShowBean extends NetResult {
    public static MemberMembershipInfoShowBean parse(String json) throws AppException {
        MemberMembershipInfoShowBean res = new MemberMembershipInfoShowBean();
        try {
            res = gson.fromJson(json, MemberMembershipInfoShowBean.class);
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
        private Member member;
        private Agent agent;
        private Counselor counselor;
        private Agency_expert agency_expert;

        public Member getMember() {
            return member;
        }

        public void setMember(Member member) {
            this.member = member;
        }

        public Agent getAgent() {
            return agent;
        }

        public void setAgent(Agent agent) {
            this.agent = agent;
        }

        public Counselor getCounselor() {
            return counselor;
        }

        public void setCounselor(Counselor counselor) {
            this.counselor = counselor;
        }

        public Agency_expert getAgency_expert() {
            return agency_expert;
        }

        public void setAgency_expert(Agency_expert agency_expert) {
            this.agency_expert = agency_expert;
        }

    }
    public class Member extends NetResult{
        private String mobilephone;
        private String sex;
        private String memberid;
        private String county;
        private String nickname;
        private String province;
        private String portrait;
        private String city;
        private String roletype;

        public String getMobilephone() {
            return mobilephone;
        }

        public void setMobilephone(String mobilephone) {
            this.mobilephone = mobilephone;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public String getMemberid() {
            return memberid;
        }

        public void setMemberid(String memberid) {
            this.memberid = memberid;
        }

        public String getCounty() {
            return county;
        }

        public void setCounty(String county) {
            this.county = county;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getProvince() {
            return province;
        }

        public void setProvince(String province) {
            this.province = province;
        }

        public String getPortrait() {
            return portrait;
        }

        public void setPortrait(String portrait) {
            this.portrait = portrait;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getRoletype() {
            return roletype;
        }

        public void setRoletype(String roletype) {
            this.roletype = roletype;
        }
    }
    public class Agent extends NetResult{
        private String idcard;
        private String bank;
        private String bankcard;

        public String getIdcard() {
            return idcard;
        }

        public void setIdcard(String idcard) {
            this.idcard = idcard;
        }

        public String getBank() {
            return bank;
        }

        public void setBank(String bank) {
            this.bank = bank;
        }

        public String getBankcard() {
            return bankcard;
        }

        public void setBankcard(String bankcard) {
            this.bankcard = bankcard;
        }
    }
    public class Counselor extends NetResult{
        private String declaration;
        private String idcard;
        private String subAgencyCompany;
        private String agencyCompany;
        private String bank;
        private String bankcard;

        public String getDeclaration() {
            return declaration;
        }

        public void setDeclaration(String declaration) {
            this.declaration = declaration;
        }

        public String getIdcard() {
            return idcard;
        }

        public void setIdcard(String idcard) {
            this.idcard = idcard;
        }

        public String getSubAgencyCompany() {
            return subAgencyCompany;
        }

        public void setSubAgencyCompany(String subAgencyCompany) {
            this.subAgencyCompany = subAgencyCompany;
        }

        public String getAgencyCompany() {
            return agencyCompany;
        }

        public void setAgencyCompany(String agencyCompany) {
            this.agencyCompany = agencyCompany;
        }

        public String getBank() {
            return bank;
        }

        public void setBank(String bank) {
            this.bank = bank;
        }

        public String getBankcard() {
            return bankcard;
        }

        public void setBankcard(String bankcard) {
            this.bankcard = bankcard;
        }
    }
    public class Agency_expert extends NetResult{
        private String idcard;
        private String companyName;
        private String position;
        private String industryExper;
        private String bank;
        private String bankcard;

        public String getIdcard() {
            return idcard;
        }

        public void setIdcard(String idcard) {
            this.idcard = idcard;
        }

        public String getCompanyName() {
            return companyName;
        }

        public void setCompanyName(String companyName) {
            this.companyName = companyName;
        }

        public String getPosition() {
            return position;
        }

        public void setPosition(String position) {
            this.position = position;
        }

        public String getIndustryExper() {
            return industryExper;
        }

        public void setIndustryExper(String industryExper) {
            this.industryExper = industryExper;
        }

        public String getBank() {
            return bank;
        }

        public void setBank(String bank) {
            this.bank = bank;
        }

        public String getBankcard() {
            return bankcard;
        }

        public void setBankcard(String bankcard) {
            this.bankcard = bankcard;
        }
    }
}
