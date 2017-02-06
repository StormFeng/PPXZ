package com.midian.ppaddress.bean;

import com.google.gson.JsonSyntaxException;

import java.util.List;

import midian.baselib.app.AppException;
import midian.baselib.bean.NetResult;

/**
 * 8.11载体-载体详情-基本参数
 */
public class BusinessCarrierDetailQueryPropertiesBean extends NetResult {
    public static BusinessCarrierDetailQueryPropertiesBean parse(String json) throws AppException {
        BusinessCarrierDetailQueryPropertiesBean res = new BusinessCarrierDetailQueryPropertiesBean();
        try {
            res = gson.fromJson(json, BusinessCarrierDetailQueryPropertiesBean.class);
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
            throw AppException.json(e);
        }
        return res;
    }

    private List<PropertiesData> data;

    public List<PropertiesData> getData() {
        return data;
    }

    public void setData(List<PropertiesData> data) {
        this.data = data;
    }

    public class PropertiesData extends NetResult {
        private String fieldValue;
        private String fieldKey;
        private List<FileValueList> fieldArrayValue;

        public List<FileValueList> getFieldArrayValue() {
            return fieldArrayValue;
        }

        public void setFieldArrayValue(List<FileValueList> fieldArrayValue) {
            this.fieldArrayValue = fieldArrayValue;
        }

        public String getFieldValue() {
            return fieldValue;
        }

        public void setFieldValue(String fieldValue) {
            this.fieldValue = fieldValue;
        }

        public String getFieldKey() {
            return fieldKey;
        }

        public void setFieldKey(String fieldKey) {
            this.fieldKey = fieldKey;
        }

        public class FileValueList extends NetResult{
            private String name;
            private String iconurl;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getIconurl() {
                return iconurl;
            }

            public void setIconurl(String iconurl) {
                this.iconurl = iconurl;
            }
        }
    }
}
