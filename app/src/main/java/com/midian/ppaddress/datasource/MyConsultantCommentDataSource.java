package com.midian.ppaddress.datasource;

import android.content.Context;

import com.midian.ppaddress.bean.MemberCommentsMemberPageMemberMeOrderBean;
import com.midian.ppaddress.utils.AppUtil;

import java.util.ArrayList;

import midian.baselib.base.BaseListDataSource;
import midian.baselib.bean.NetResult;

/**
 * 个人中心-我的评价-对物业顾问的评价
 * Created by chu on 2016/5/3.
 */
public class MyConsultantCommentDataSource extends BaseListDataSource{

    private int memberid,pageNo=1;

    public MyConsultantCommentDataSource(Context context) {
        super(context);
    }

    @Override
    protected ArrayList load(int page) throws Exception {
        this.page=page;
        memberid=Integer.valueOf(ac.user_id).intValue();
        ArrayList<NetResult> morelist = new ArrayList<>();
        MemberCommentsMemberPageMemberMeOrderBean bean= AppUtil.getPpApiClient(ac).getmemberCommentsMemberPageMemberMeOrder(memberid,page);
        if(bean.isOK()) {
            morelist.addAll(bean.getData().getList());
            if(bean.getData().getList().size()<10){
                hasMore=false;
            }else{
                hasMore=true;
            }
        }else{
            ac.handleErrorCode(context,bean.errorcode);
        }
        return morelist;
    }
}
