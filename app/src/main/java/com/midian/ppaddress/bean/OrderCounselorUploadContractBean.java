package com.midian.ppaddress.bean;

import com.google.gson.JsonSyntaxException;

import java.util.List;

import midian.baselib.app.AppException;
import midian.baselib.bean.NetResult;

/**
 * 16.10个人中心_我的订单(物业顾问)_上传/编辑合同
 * Created by chu on 2016/5/9.
 */
public class OrderCounselorUploadContractBean extends NetResult {
    public static OrderCounselorUploadContractBean parse(String json) throws AppException {
        OrderCounselorUploadContractBean res = new OrderCounselorUploadContractBean();
        try {
            res = gson.fromJson(json, OrderCounselorUploadContractBean.class);
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
            throw AppException.json(e);
        }
        return res;
    }

    private List<ContractData> data;

    public List<ContractData> getData() {
        return data;
    }

    public void setData(List<ContractData> data) {
        this.data = data;
    }

    public class ContractData extends NetResult {
        private String id;//": 318,
        private String fileName;//": "签约合同0216.png",
        private String image;//": "http://img /conc3c.png"

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
