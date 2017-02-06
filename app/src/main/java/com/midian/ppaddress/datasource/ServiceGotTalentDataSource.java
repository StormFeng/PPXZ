package com.midian.ppaddress.datasource;

import android.content.Context;

import com.midian.ppaddress.bean.CommentToServerBean;
import com.midian.ppaddress.utils.AppUtil;

import java.util.ArrayList;

import midian.baselib.base.BaseListDataSource;
import midian.baselib.bean.NetResult;

/**
 * 个人中心-我的评价-对服务达人的评价
 * Created by chu on 2016/5/3.
 */
public class ServiceGotTalentDataSource extends BaseListDataSource{

    private int memberid;
    public ServiceGotTalentDataSource(Context context) {
        super(context);
    }

    @Override
    protected ArrayList load(int page) throws Exception {
        this.page=page;
        memberid=Integer.valueOf(ac.user_id).intValue();
        ArrayList<NetResult> morelist = new ArrayList<>();
        CommentToServerBean bean= AppUtil.getPpApiClient(ac).getcommentToServer(memberid,page);
        if(bean.isOK()){
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
