package com.midian.ppaddress.bean;

import com.google.gson.JsonSyntaxException;

import java.util.List;

import midian.baselib.app.AppException;
import midian.baselib.bean.NetResult;

/**
 * 7.2地图筛选
 * Created by chu on 2016/5/6.
 */
public class BusinessAppsearchSoscreenBean extends NetResult {
    public static BusinessAppsearchSoscreenBean parse(String json) throws AppException {
        BusinessAppsearchSoscreenBean res = new BusinessAppsearchSoscreenBean();
        try {
            res = gson.fromJson(json, BusinessAppsearchSoscreenBean.class);
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
        private List<Econzones> econzones;//筛选页经济区筛选列列表
        private List<Hotcitys> hotcitys;//-筛选页热门城市列列表
        private List<CarrierType> carrierType;//载体类型

        public List<Econzones> getEconzones() {
            return econzones;
        }

        public void setEconzones(List<Econzones> econzones) {
            this.econzones = econzones;
        }

        public List<Hotcitys> getHotcitys() {
            return hotcitys;
        }

        public void setHotcitys(List<Hotcitys> hotcitys) {
            this.hotcitys = hotcitys;
        }

        public List<CarrierType> getCarrierType() {
            return carrierType;
        }

        public void setCarrierType(List<CarrierType> carrierType) {
            this.carrierType = carrierType;
        }
    }

    public class Econzones extends NetResult {
        private List<Citys> citys;
        private String name;//珠三角",  ---经济区名称
        private String eezid;// 1   -----经济区ID

        public List<Citys> getCitys() {
            return citys;
        }

        public void setCitys(List<Citys> citys) {
            this.citys = citys;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getEezid() {
            return eezid;
        }

        public void setEezid(String eezid) {
            this.eezid = eezid;
        }
    }

    public class Hotcitys extends NetResult {
        private String id;// 200,  ------热门城市ID
        private String name;//广州"    ------热门城市名称

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public class CarrierType extends NetResult {
        private String id;// 2,------热门城市ID
        private String name;//高新技术"------热门城市名称

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }


    public class Citys extends NetResult {
        private String id;// 200,     ---经济区下所属城市ID
        private String name;//广州"  ---经济区下所属城市名称

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }


}
