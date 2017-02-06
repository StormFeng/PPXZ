package com.midian.ppaddress.bean;

import com.google.gson.JsonSyntaxException;

import java.util.List;

import midian.baselib.app.AppException;
import midian.baselib.bean.NetResult;

/**
 * 15.9个人中心-我（普通客商）的评论-对服务达人的评论-新增评论页
 * Created by chu on 2016/5/9.
 */
public class AddMemberMeAgencyexpertBean extends NetResult{
    public static AddMemberMeAgencyexpertBean parse(String json) throws AppException {
        AddMemberMeAgencyexpertBean res = new AddMemberMeAgencyexpertBean();
        try {
            res = gson.fromJson(json, AddMemberMeAgencyexpertBean.class);
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

        private String questionid;//": 7,
        private String question;//": "你好，我有个问题想问一下。",
        private String createTime;//": "2016-04-13 13:56:07",
        private String memberid;//": 55,
        private String agencyexpert;//": "朱杰"
        private String position;//": "客户经理",
        private String agencyName;//": "阿里巴巴",
        private String portrait;//": "http://xxx/ad8c.jpg

        public String getQuestionid() {
            return questionid;
        }

        public void setQuestionid(String questionid) {
            this.questionid = questionid;
        }

        public String getQuestion() {
            return question;
        }

        public void setQuestion(String question) {
            this.question = question;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getMemberid() {
            return memberid;
        }

        public void setMemberid(String memberid) {
            this.memberid = memberid;
        }

        public String getAgencyexpert() {
            return agencyexpert;
        }

        public void setAgencyexpert(String agencyexpert) {
            this.agencyexpert = agencyexpert;
        }

        public String getPosition() {
            return position;
        }

        public void setPosition(String position) {
            this.position = position;
        }

        public String getAgencyName() {
            return agencyName;
        }

        public void setAgencyName(String agencyName) {
            this.agencyName = agencyName;
        }

        public String getPortrait() {
            return portrait;
        }

        public void setPortrait(String portrait) {
            this.portrait = portrait;
        }
    }

}
