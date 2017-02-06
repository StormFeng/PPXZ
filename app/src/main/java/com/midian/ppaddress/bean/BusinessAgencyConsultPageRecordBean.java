package com.midian.ppaddress.bean;

import com.google.gson.JsonSyntaxException;

import java.util.List;

import midian.baselib.app.AppException;
import midian.baselib.bean.NetResult;

/**
 * 首页1
 * Created by chu on 2016/5/8.
 */
public class BusinessAgencyConsultPageRecordBean extends NetResult {
    public static BusinessAgencyConsultPageRecordBean parse(String json) throws AppException {
        BusinessAgencyConsultPageRecordBean res = new BusinessAgencyConsultPageRecordBean();
        try {
            res = gson.fromJson(json, BusinessAgencyConsultPageRecordBean.class);
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
        private List<RecordList> list;
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

        public List<RecordList> getList() {
            return list;
        }

        public void setList(List<RecordList> list) {
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
    public class RecordList extends NetResult {
        private String position;
        private String senderid;
        private String content;
        private String id;
        private String createTime;
        private String portrait;
        private String receiverid;

        public String getPosition() {
            return position;
        }

        public void setPosition(String position) {
            this.position = position;
        }

        public String getSenderid() {
            return senderid;
        }

        public void setSenderid(String senderid) {
            this.senderid = senderid;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getPortrait() {
            return portrait;
        }

        public void setPortrait(String portrait) {
            this.portrait = portrait;
        }

        public String getReceiverid() {
            return receiverid;
        }

        public void setReceiverid(String receiverid) {
            this.receiverid = receiverid;
        }
    }
}
