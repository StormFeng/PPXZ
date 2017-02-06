package com.midian.ppaddress.bean;

import com.google.gson.JsonSyntaxException;

import java.util.ArrayList;
import java.util.List;

import midian.baselib.app.AppException;
import midian.baselib.bean.NetResult;

public class BusinessAgencyScreenPageActivityBean extends NetResult {
    public static BusinessAgencyScreenPageActivityBean parse(String json) throws AppException {
        BusinessAgencyScreenPageActivityBean res = new BusinessAgencyScreenPageActivityBean();
        try {
            res = gson.fromJson(json, BusinessAgencyScreenPageActivityBean.class);
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
        private ArrayList<ActivityList> list;
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

        public ArrayList<ActivityList> getList() {
            return list;
        }

        public void setList(ArrayList<ActivityList> list) {
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
    public class ActivityList extends NetResult{
        private String id;
        private String coverImage;
        private String title;
        private String slogan;
        private String subslogan;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getCoverImage() {
            return coverImage;
        }

        public void setCoverImage(String coverImage) {
            this.coverImage = coverImage;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getSlogan() {
            return slogan;
        }

        public void setSlogan(String slogan) {
            this.slogan = slogan;
        }

        public String getSubslogan() {
            return subslogan;
        }

        public void setSubslogan(String subslogan) {
            this.subslogan = subslogan;
        }
    }
}
