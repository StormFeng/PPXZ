package com.midian.ppaddress.bean;

import com.google.gson.JsonSyntaxException;

import java.util.List;

import midian.baselib.app.AppException;
import midian.baselib.bean.NetResult;

/**
 * 17.3个人中心（业主）- 我的物业-租凭情况
 * Created by chu on 2016/5/9.
 */
public class EntercarrierPageRentsBean extends NetResult {
    public static EntercarrierPageRentsBean parse(String json) throws AppException {
        EntercarrierPageRentsBean res = new EntercarrierPageRentsBean();
        try {
            res = gson.fromJson(json, EntercarrierPageRentsBean.class);
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
        private String code;//": "BU-20160321",
        private Rents rents;//"

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public Rents getRents() {
            return rents;
        }

        public void setRents(Rents rents) {
            this.rents = rents;
        }
    }

    public class Rents extends NetResult {
        private String lastPage;//": true,
        private String pageSize;//": 1,
        private String pageNumber;//": 1,
        private String firstPage;//": true,
        private List<RentsList> list;//

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

        public List<RentsList> getList() {
            return list;
        }

        public void setList(List<RentsList> list) {
            this.list = list;
        }
    }

    public class RentsList extends NetResult {
        private String createTime;//": "2016-04-09 16:44:21",
        private String memberid;//": "xiaoma",
        private String nickname;//": "马云",
        private String counselorImage;//": "http://img/xxx.jpg",
        private String memberImage;//": "http://img/xxx.jpg",
        private String counselorid;//": "liyanhong",
        private String stage;//": 5,
        private String fullname;//": "姚总

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getMemberid() {
            return memberid;
        }

        public void setMemberid(String memberid) {
            this.memberid = memberid;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getCounselorImage() {
            return counselorImage;
        }

        public void setCounselorImage(String counselorImage) {
            this.counselorImage = counselorImage;
        }

        public String getMemberImage() {
            return memberImage;
        }

        public void setMemberImage(String memberImage) {
            this.memberImage = memberImage;
        }

        public String getCounselorid() {
            return counselorid;
        }

        public void setCounselorid(String counselorid) {
            this.counselorid = counselorid;
        }

        public String getStage() {
            return stage;
        }

        public void setStage(String stage) {
            this.stage = stage;
        }

        public String getFullname() {
            return fullname;
        }

        public void setFullname(String fullname) {
            this.fullname = fullname;
        }
    }


}
