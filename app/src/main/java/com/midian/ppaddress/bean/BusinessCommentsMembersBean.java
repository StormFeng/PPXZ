package com.midian.ppaddress.bean;

import com.google.gson.JsonSyntaxException;

import java.util.List;

import midian.baselib.app.AppException;
import midian.baselib.bean.NetResult;

/**
 *8.6载体详情--用户点评列表
 */
public class BusinessCommentsMembersBean extends NetResult {
    public static BusinessCommentsMembersBean parse(String json) throws AppException {
        BusinessCommentsMembersBean res = new BusinessCommentsMembersBean();
        try {
            res = gson.fromJson(json, BusinessCommentsMembersBean.class);
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
            throw AppException.json(e);
        }
        return res;
    }


    private CommentData data;

    public CommentData getData() {
        return data;
    }

    public void setData(CommentData data) {
        this.data = data;
    }

    public class CommentData extends NetResult {
        private String lastPage;//": true,
        private String pageSize;//": 15,
        private String pageNumber;//": 1,
        private List<CommentMembersList> list;//"
        private String firstPage;//": true,
        private String totalRow;//": 1,
        private String totalPage;//

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

        public List<CommentMembersList> getList() {
            return list;
        }

        public void setList(List<CommentMembersList> list) {
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

    public class CommentMembersList extends NetResult {
        private String content;//": "环境优美，停车方便",
        private String id;//": 6,
        private String createTime;//": "22 天前",
        private String rate;//": 1, -----评论等级，0无，1为好评，2为中评，3为差评
        private String likeCount;//": 12,
        private String name;//": "海春",
        private String images;//": "http://xxx/ f.jpg

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

        public String getRate() {
            return rate;
        }

        public void setRate(String rate) {
            this.rate = rate;
        }

        public String getLikeCount() {
            return likeCount;
        }

        public void setLikeCount(String likeCount) {
            this.likeCount = likeCount;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getImages() {
            return images;
        }

        public void setImages(String images) {
            this.images = images;
        }
    }


}
