package com.midian.ppaddress.bean;

import com.google.gson.JsonSyntaxException;

import java.util.List;

import midian.baselib.app.AppException;
import midian.baselib.bean.NetResult;

public class BusinessAgencyScreenPageAgencyBean extends NetResult {
    public static BusinessAgencyScreenPageAgencyBean parse(String json) throws AppException {
        BusinessAgencyScreenPageAgencyBean res = new BusinessAgencyScreenPageAgencyBean();
        try {
            res = gson.fromJson(json, BusinessAgencyScreenPageAgencyBean.class);
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
        private String lastPage;
        private String pageSize;
        private String pageNumber;
        private String firstPage;
        private List<AgencyList> list;
        private String totalRow;
        private String totalPage;


        public String getLastPage() {
            return lastPage;
        }

        public void setLastPage(String lastPage) {
            this.lastPage = lastPage;
        }

        public String getPageSize() {
            return pageSize;
        }

        public void setPageSize(String pageSize) {
            this.pageSize = pageSize;
        }

        public String getPageNumber() {
            return pageNumber;
        }

        public void setPageNumber(String pageNumber) {
            this.pageNumber = pageNumber;
        }

        public String getFirstPage() {
            return firstPage;
        }

        public void setFirstPage(String firstPage) {
            this.firstPage = firstPage;
        }

        public List<AgencyList> getList() {
            return list;
        }

        public void setList(List<AgencyList> list) {
            this.list = list;
        }

        public String getTotalRow() {
            return totalRow;
        }

        public void setTotalRow(String totalRow) {
            this.totalRow = totalRow;
        }

        public String getTotalPage() {
            return totalPage;
        }

        public void setTotalPage(String totalPage) {
            this.totalPage = totalPage;
        }


    }
    public class AgencyList extends NetResult{
        private String id;
        private String logo;
        private String goodRate;
        private String labels;
        private String name;
        private String serviceCount;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getLogo() {
            return logo;
        }

        public void setLogo(String logo) {
            this.logo = logo;
        }

        public String getGoodRate() {
            return goodRate;
        }

        public void setGoodRate(String goodRate) {
            this.goodRate = goodRate;
        }

        public String getLabels() {
            return labels;
        }

        public void setLabels(String labels) {
            this.labels = labels;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getServiceCount() {
            return serviceCount;
        }

        public void setServiceCount(String serviceCount) {
            this.serviceCount = serviceCount;
        }
    }
}
