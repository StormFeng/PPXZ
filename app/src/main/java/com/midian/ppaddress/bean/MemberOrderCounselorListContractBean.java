package com.midian.ppaddress.bean;

import com.google.gson.JsonSyntaxException;

import java.util.ArrayList;

import midian.baselib.app.AppException;
import midian.baselib.bean.NetResult;

/**
 * 16.9个人中心-我(物业顾问)的订单-查看合同
 * Created by chu on 2016/5/9.
 */
public class MemberOrderCounselorListContractBean extends NetResult {
    public static MemberOrderCounselorListContractBean parse(String json) throws AppException {
        MemberOrderCounselorListContractBean res = new MemberOrderCounselorListContractBean();
        try {
            res = gson.fromJson(json, MemberOrderCounselorListContractBean.class);
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
            throw AppException.json(e);
        }
        return res;
    }

    private ArrayList<LookContractData> data;

    public ArrayList<LookContractData> getData() {
        return data;
    }

    public void setData(ArrayList<LookContractData> data) {
        this.data = data;
    }

    public class LookContractData extends NetResult {
        private String id;//": 3,图片id
        private String fileName;//": "Jellyfish2.jpg",图片名称
        private String image;//": "http://xxx/6ae.jpg" 图片地址

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getFileName() {
            return fileName;
        }

        public void setFileName(String fileName) {
            this.fileName = fileName;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }
    }

}
