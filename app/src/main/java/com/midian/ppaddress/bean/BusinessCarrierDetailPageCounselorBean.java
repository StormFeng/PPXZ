package com.midian.ppaddress.bean;

import com.google.gson.JsonSyntaxException;

import java.util.List;

import midian.baselib.app.AppException;
import midian.baselib.bean.NetResult;

/**
 * 8.5物业顾问列表
 */
public class BusinessCarrierDetailPageCounselorBean extends NetResult {
    public static BusinessCarrierDetailPageCounselorBean parse(String json) throws AppException {
        BusinessCarrierDetailPageCounselorBean res = new BusinessCarrierDetailPageCounselorBean();
        try {
            res = gson.fromJson(json, BusinessCarrierDetailPageCounselorBean.class);
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
        private List<CarrierList> list;
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

        public List<CarrierList> getList() {
            return list;
        }

        public void setList(List<CarrierList> list) {
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
    public class CarrierList extends NetResult {
        private String declaration;
        private String goodRate;
        private String portrait;
        private String counts;
        private String counselorid;
        private String subAgencyCompany;
        private String fullname;
        private String agencyCompany;

        public String getDeclaration() {
            return declaration;
        }

        public void setDeclaration(String declaration) {
            this.declaration = declaration;
        }

        public String getGoodRate() {
            return goodRate;
        }

        public void setGoodRate(String goodRate) {
            this.goodRate = goodRate;
        }

        public String getPortrait() {
            return portrait;
        }

        public void setPortrait(String portrait) {
            this.portrait = portrait;
        }

        public String getCounts() {
            return counts;
        }

        public void setCounts(String counts) {
            this.counts = counts;
        }

        public String getCounselorid() {
            return counselorid;
        }

        public void setCounselorid(String counselorid) {
            this.counselorid = counselorid;
        }

        public String getSubAgencyCompany() {
            return subAgencyCompany;
        }

        public void setSubAgencyCompany(String subAgencyCompany) {
            this.subAgencyCompany = subAgencyCompany;
        }

        public String getFullname() {
            return fullname;
        }

        public void setFullname(String fullname) {
            this.fullname = fullname;
        }

        public String getAgencyCompany() {
            return agencyCompany;
        }

        public void setAgencyCompany(String agencyCompany) {
            this.agencyCompany = agencyCompany;
        }
    }
}
