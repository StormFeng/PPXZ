package com.midian.ppaddress.datasource;

import android.content.Context;

import com.midian.ppaddress.bean.MemberCommentsAgencyexpertPageMeBean;
import com.midian.ppaddress.utils.AppUtil;

import java.util.ArrayList;

import midian.baselib.base.BaseListDataSource;
import midian.baselib.bean.NetResult;

/**
 * 15.4 个人中心-我的评论-对我（服务达人）的评论
 */
public class CommentToServieTalentDataResource extends BaseListDataSource{
    private int pageNo=1;
    public CommentToServieTalentDataResource(Context context) {
        super(context);
    }

    @Override
    protected ArrayList load(int page) throws Exception {
        this.page=page;
        ArrayList<NetResult> models = new ArrayList<>();
        MemberCommentsAgencyexpertPageMeBean bean= AppUtil.getPpApiClient(ac).getMemberCommentsAgencyexpertPageMe(ac.user_id,page);
        if(bean.isOK()){
            models.addAll(bean.getData().getList());
            if(bean.getData().getList().size()<10){
                hasMore=false;
            }else{
                hasMore=true;
            }
        }else{
            ac.handleErrorCode(context,bean.errorcode);
        }
        return models;
    }
}
