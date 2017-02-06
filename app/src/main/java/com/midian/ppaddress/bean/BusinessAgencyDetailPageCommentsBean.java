package com.midian.ppaddress.bean;

import com.google.gson.JsonSyntaxException;

import java.util.List;

import midian.baselib.app.AppException;
import midian.baselib.bean.NetResult;

/**
 * 评论列表
 * Created by chu on 2016/5/8.
 */
public class BusinessAgencyDetailPageCommentsBean extends NetResult {
    public static BusinessAgencyDetailPageCommentsBean parse(String json) throws AppException {
        BusinessAgencyDetailPageCommentsBean res = new BusinessAgencyDetailPageCommentsBean();
        try {
            res = gson.fromJson(json, BusinessAgencyDetailPageCommentsBean.class);
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
        private List<CommentList> list;
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

        public List<CommentList> getList() {
            return list;
        }

        public void setList(List<CommentList> list) {
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
    public class CommentList extends NetResult{
        private String commentid;
        private String content;
        private String questionid;
        private String question;
        private String member;
        private String agencyexpert;
        private String createTime;
        private String rate;

        public String getQuestion() {
            return question;
        }

        public void setQuestion(String question) {
            this.question = question;
        }

        public String getCommentid() {
            return commentid;
        }

        public void setCommentid(String commentid) {
            this.commentid = commentid;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getQuestionid() {
            return questionid;
        }

        public void setQuestionid(String questionid) {
            this.questionid = questionid;
        }

        public String getMember() {
            return member;
        }

        public void setMember(String member) {
            this.member = member;
        }

        public String getAgencyexpert() {
            return agencyexpert;
        }

        public void setAgencyexpert(String agencyexpert) {
            this.agencyexpert = agencyexpert;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getRate() {
            return rate;
        }

        public void setRate(String rate) {
            this.rate = rate;
        }
    }
}
