package com.midian.ppaddress.bean;

import com.google.gson.JsonSyntaxException;

import java.util.List;

import midian.baselib.app.AppException;
import midian.baselib.bean.NetResult;

/**
 * 服务机构收藏Bean列表
 * Created by chu on 2016/5/8.
 */
public class MemberFavoritePageAgencyBean extends NetResult {
    public static MemberFavoritePageAgencyBean parse(String json) throws AppException {
        MemberFavoritePageAgencyBean res = new MemberFavoritePageAgencyBean();
        try {
            res = gson.fromJson(json, MemberFavoritePageAgencyBean.class);
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
        private String logo;
        private String agencyid;
        private String goodRate;
        private String labels;
        private String name;
        private String serviceCount;
        private String favoriteid;
        private boolean isCheck;
        public boolean isEdit;

        public boolean isCheck() {
            return isCheck;
        }

        public void setCheck(boolean check) {
            isCheck = check;
        }

        public boolean isEdit() {
            return isEdit;
        }

        public void setEdit(boolean edit) {
            isEdit = edit;
        }

        public String getLogo() {
            return logo;
        }

        public void setLogo(String logo) {
            this.logo = logo;
        }

        public String getAgencyid() {
            return agencyid;
        }

        public void setAgencyid(String agencyid) {
            this.agencyid = agencyid;
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

        public String getFavoriteid() {
            return favoriteid;
        }

        public void setFavoriteid(String favoriteid) {
            this.favoriteid = favoriteid;
        }
    }
}
