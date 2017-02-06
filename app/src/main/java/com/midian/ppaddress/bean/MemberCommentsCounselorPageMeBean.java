package com.midian.ppaddress.bean;

import com.google.gson.JsonSyntaxException;

import java.util.List;

import midian.baselib.app.AppException;
import midian.baselib.bean.NetResult;

/**
 * 15.2个人中心-我的评论-对我（物业顾问）的评论
 * Created by chu on 2016/5/9.
 */
public class MemberCommentsCounselorPageMeBean extends NetResult{
    public static MemberCommentsCounselorPageMeBean parse(String json) throws AppException {
        MemberCommentsCounselorPageMeBean res = new MemberCommentsCounselorPageMeBean();
        try {
            res = gson.fromJson(json, MemberCommentsCounselorPageMeBean.class);
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
        private String member;//": "xiaohi",
        private String content;//": "服务态度好!",
        private String id;//": 1,
        private String createTime;//": "29 天前",
        private String sort;//": 2,
        private String rate;//": 2,
        private String memberid;//": 64,
        private String portrait;//": "http://xxx/13c867.jpg"

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

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getSort() {
            return sort;
        }

        public void setSort(String sort) {
            this.sort = sort;
        }

        public String getRate() {
            return rate;
        }

        public void setRate(String rate) {
            this.rate = rate;
        }

        public String getMemberid() {
            return memberid;
        }

        public void setMemberid(String memberid) {
            this.memberid = memberid;
        }

        public String getPortrait() {
            return portrait;
        }

        public void setPortrait(String portrait) {
            this.portrait = portrait;
        }
    }


}
