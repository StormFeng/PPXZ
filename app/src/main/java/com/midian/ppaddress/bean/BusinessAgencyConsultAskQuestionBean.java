package com.midian.ppaddress.bean;

import com.google.gson.JsonSyntaxException;

import java.util.List;

import midian.baselib.app.AppException;
import midian.baselib.bean.NetResult;

/**
 * Created by chu on 2016/5/6.
 */
public class BusinessAgencyConsultAskQuestionBean extends NetResult {
    public static BusinessAgencyConsultAskQuestionBean parse(String json) throws AppException {
        BusinessAgencyConsultAskQuestionBean res = new BusinessAgencyConsultAskQuestionBean();
        try {
            res = gson.fromJson(json, BusinessAgencyConsultAskQuestionBean.class);
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
        private String question;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getQuestion() {
            return question;
        }

        public void setQuestion(String question) {
            this.question = question;
        }
    }
}
