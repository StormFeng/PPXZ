package com.midian.ppaddress.bean;

import com.google.gson.JsonSyntaxException;

import midian.baselib.app.AppException;
import midian.baselib.bean.NetResult;

/**
 * 16.7选址-载体详情-物业顾问详情-发送委托
 * Created by chu on 2016/5/9.
 */
public class MemberOrderMemberSendBeam extends NetResult {
    public static MemberOrderMemberSendBeam parse(String json) throws AppException {
        MemberOrderMemberSendBeam res = new MemberOrderMemberSendBeam();
        try {
            res = gson.fromJson(json, MemberOrderMemberSendBeam.class);
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
            throw AppException.json(e);
        }
        return res;
    }

    private Data data;

    public class Data extends NetResult {
        private String id;//

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }
    }

}
