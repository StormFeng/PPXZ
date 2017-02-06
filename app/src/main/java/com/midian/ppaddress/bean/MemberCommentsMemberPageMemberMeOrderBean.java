package com.midian.ppaddress.bean;

import com.google.gson.JsonSyntaxException;

import java.util.List;

import midian.baselib.app.AppException;
import midian.baselib.bean.NetResult;

/**
 * 15.5个人中心-我（普通客商）的评论-对物业顾问的评论
 * Created by chu on 2016/5/9.
 */
public class MemberCommentsMemberPageMemberMeOrderBean extends NetResult {
    public static MemberCommentsMemberPageMemberMeOrderBean parse(String json) throws AppException {
        MemberCommentsMemberPageMemberMeOrderBean res = new MemberCommentsMemberPageMemberMeOrderBean();
        try {
            res = gson.fromJson(json, MemberCommentsMemberPageMemberMeOrderBean.class);
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
        private String lastPage;//": true,
        private String pageSize;//": 15,
        private String pageNumber;//": 1,
        private List<Lists> list;
        private String firstPage;//": true,
        private String totalRow;//": 1,
        private String totalPage;//": 1

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

        public List<Lists> getList() {
            return list;
        }

        public void setList(List<Lists> list) {
            this.list = list;
        }

        public String getFirstPage() {
            return firstPage;
        }

        public void setFirstPage(String firstPage) {
            this.firstPage = firstPage;
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

    public class Lists extends NetResult {
        private String id;//": 1,
        private String portrait;//": "http://xxx/28f.jpg",
        private String images;//": "http://xxx/ca2.jpeg",
        private String carrierName;//": "金地香山湖写字楼",
        private String username;//": "liyanhong",
        private String isComments;//": 1,
        private String createTime;//": "2016-04-12",
        private String carrierType;//": 4,
        private String counselorid;//": 54,
        private String commentServer;//": "服务态度好!",
        private String carrierid;//": 77,
        private String commentCarrier;//": "绿化面积大，楼间距大，临近地铁，性价比高",
        private String carrierRate;//": 2,
        private String serverRate;//": 2

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getPortrait() {
            return portrait;
        }

        public void setPortrait(String portrait) {
            this.portrait = portrait;
        }

        public String getImages() {
            return images;
        }

        public void setImages(String images) {
            this.images = images;
        }

        public String getCarrierName() {
            return carrierName;
        }

        public void setCarrierName(String carrierName) {
            this.carrierName = carrierName;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getIsComments() {
            return isComments;
        }

        public void setIsComments(String isComments) {
            this.isComments = isComments;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getCarrierType() {
            return carrierType;
        }

        public void setCarrierType(String carrierType) {
            this.carrierType = carrierType;
        }

        public String getCounselorid() {
            return counselorid;
        }

        public void setCounselorid(String counselorid) {
            this.counselorid = counselorid;
        }

        public String getCommentServer() {
            return commentServer;
        }

        public void setCommentServer(String commentServer) {
            this.commentServer = commentServer;
        }

        public String getCarrierid() {
            return carrierid;
        }

        public void setCarrierid(String carrierid) {
            this.carrierid = carrierid;
        }

        public String getCommentCarrier() {
            return commentCarrier;
        }

        public void setCommentCarrier(String commentCarrier) {
            this.commentCarrier = commentCarrier;
        }

        public String getCarrierRate() {
            return carrierRate;
        }

        public void setCarrierRate(String carrierRate) {
            this.carrierRate = carrierRate;
        }

        public String getServerRate() {
            return serverRate;
        }

        public void setServerRate(String serverRate) {
            this.serverRate = serverRate;
        }
    }


}
