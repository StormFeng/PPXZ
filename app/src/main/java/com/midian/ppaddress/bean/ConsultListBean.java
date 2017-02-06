package com.midian.ppaddress.bean;

import com.google.gson.JsonSyntaxException;

import java.util.List;

import midian.baselib.app.AppException;
import midian.baselib.bean.NetResult;

/**
 * 14.11个人中心-咨询记录-列表（服务达人、普通客商）
 * Created by chu on 2016/5/9.
 */
public class ConsultListBean extends NetResult{
    public static ConsultListBean parse(String json) throws AppException {
        ConsultListBean res = new ConsultListBean();
        try {
            res = gson.fromJson(json, ConsultListBean.class);
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

    public static class Lists extends NetResult {
        private String member;//": "Ccc", -------咨询会员名称
        private String content;//": "好的，谢谢！", -------最新一条咨询留言
        private String id;//": 6,-------咨询问题id
        private String closeTime;//": "",---问题结束时间
        private String memberid;//": 3,-------咨询会员ID
        private String isread;//": "",-------接收者是否已读，0为未读，1为已读
        private String agencyexpertid;//": 2,-------服务达人ID
        private String question;//": "我是米点联系?",-------咨询问题
        private String portrait;//": "http://img/1e.jpg",-------咨询会员头像
        private String agencyexpert;//": "石泳豪",-------服务达人名称
        private String close;//": 0-------问题是否已关闭，0为进行中，1为结束会话

        private String position;

        public String getPosition() {
            return position;
        }

        public void setPosition(String position) {
            this.position = position;
        }

        public String getMember() {
            return member;
        }

        public void setMember(String member) {
            this.member = member;
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

        public String getCloseTime() {
            return closeTime;
        }

        public void setCloseTime(String closeTime) {
            this.closeTime = closeTime;
        }

        public String getMemberid() {
            return memberid;
        }

        public void setMemberid(String memberid) {
            this.memberid = memberid;
        }

        public String getIsread() {
            return isread;
        }

        public void setIsread(String isread) {
            this.isread = isread;
        }

        public String getAgencyexpertid() {
            return agencyexpertid;
        }

        public void setAgencyexpertid(String agencyexpertid) {
            this.agencyexpertid = agencyexpertid;
        }

        public String getQuestion() {
            return question;
        }

        public void setQuestion(String question) {
            this.question = question;
        }

        public String getPortrait() {
            return portrait;
        }

        public void setPortrait(String portrait) {
            this.portrait = portrait;
        }

        public String getAgencyexpert() {
            return agencyexpert;
        }

        public void setAgencyexpert(String agencyexpert) {
            this.agencyexpert = agencyexpert;
        }

        public String getClose() {
            return close;
        }

        public void setClose(String close) {
            this.close = close;
        }
    }
}
