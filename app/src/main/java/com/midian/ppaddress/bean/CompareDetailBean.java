package com.midian.ppaddress.bean;

import com.google.gson.JsonSyntaxException;

import java.util.ArrayList;
import java.util.List;

import midian.baselib.app.AppException;
import midian.baselib.bean.NetResult;

/**
 * 载体对比-表格对比页
 * Created by chu on 2016/5/6.
 */
public class CompareDetailBean extends NetResult {
    public static CompareDetailBean parse(String json) throws AppException {
        CompareDetailBean res = new CompareDetailBean();
        try {
            res = gson.fromJson(json, CompareDetailBean.class);
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
            throw AppException.json(e);
        }
        return res;
    }

    private CompareData data;

    public CompareData getData() {
        return data;
    }

    public void setData(CompareData data) {
        this.data = data;
    }

    public List<NetResult> adapterSameDate = new ArrayList<>();//列值相同的数据
    public List<NetResult> allDate = new ArrayList<>();//所有数据

    public class CompareData extends NetResult {
        private List<Carriers> carriers;
        private List<Propertys> propertys;

        public List<Carriers> getCarriers() {
            return carriers;
        }

        public void setCarriers(List<Carriers> carriers) {
            this.carriers = carriers;
        }

        public List<Propertys> getPropertys() {
            return propertys;
        }

        public void setPropertys(List<Propertys> propertys) {
            this.propertys = propertys;
        }
    }

    public class Carriers extends NetResult {
       private String  carrierName;//": "嘉福工业区",
        private String compareid;//": 95,
        private String carrierid;//": 1

        public String getCarrierName() {
            return carrierName;
        }

        public void setCarrierName(String carrierName) {
            this.carrierName = carrierName;
        }

        public String getCompareid() {
            return compareid;
        }

        public void setCompareid(String compareid) {
            this.compareid = compareid;
        }

        public String getCarrierid() {
            return carrierid;
        }

        public void setCarrierid(String carrierid) {
            this.carrierid = carrierid;
        }
    }

    public class Propertys extends NetResult {
        private List<FieldArrayValue> fieldArrayValue;

        private String fieldKey;//": "基本参数"
        public List<FieldArrayValue> getFieldArrayValue() {
            return fieldArrayValue;
        }

        public void setFieldArrayValue(List<FieldArrayValue> fieldArrayValue) {
            this.fieldArrayValue = fieldArrayValue;
        }

        public String getFieldKey() {
            return fieldKey;
        }

        public void setFieldKey(String fieldKey) {
            this.fieldKey = fieldKey;
        }
    }


    public class FieldArrayValue extends NetResult {
        private String key;//"通讯网络"
        private List<String> values;//"中国电信
        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public List<String> getValues() {
            return values;
        }

        public void setValues(List<String> values) {
            this.values = values;
        }
    }

    public class Values extends NetResult {

    }

}
