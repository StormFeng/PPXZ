package com.midian.ppaddress.datasource;

import android.content.Context;

import com.midian.ppaddress.bean.IndexMultiMemberPageNewstBean;
import com.midian.ppaddress.bean.MemberOrderMemberPageNewestBean;
import com.midian.ppaddress.utils.AppUtil;

import java.util.ArrayList;

import midian.baselib.base.BaseListDataSource;
import midian.baselib.bean.NetResult;

/**
 * Created by chu on 2016/2/23.
 */
public class BookingDataResource extends BaseListDataSource {
    private String memberid;
    private int pageSize=10;
    private String stage;

    public String getStage() {
        return stage;
    }

    public void setStage(String stage) {
        this.stage = stage;
    }

    public BookingDataResource(Context context) {
        super(context);
    }
    public BookingDataResource(Context context,String stage) {
        super(context);
        this.stage=stage;
    }

    @Override
    protected ArrayList load(int page) throws Exception {
        ArrayList<NetResult> models = new ArrayList<>();
        memberid=ac.user_id;
        this.page=page;
        MemberOrderMemberPageNewestBean topbean = AppUtil.getPpApiClient(ac).getOrderMemberPageNewest(memberid, stage, page, pageSize);
        if(topbean.isOK()) {
            if (page == 1) {
                IndexMultiMemberPageNewstBean item0 = new IndexMultiMemberPageNewstBean();
                item0.setItemViewType(0);
                item0.topbean = topbean;
                models.add(item0);
            }

            for (MemberOrderMemberPageNewestBean.Lists item : topbean.getData().getOrders().getList()) {
                IndexMultiMemberPageNewstBean item1 = new IndexMultiMemberPageNewstBean();
                item1.setItemViewType(1);
                item1.botbeans = item;
                models.add(item1);
            }
            if (topbean.getData().getOrders().getList().size() < 10) {
                hasMore = false;
            } else {
                hasMore = true;
            }
        }else{
            ac.handleErrorCode(context,topbean.errorcode);
        }
        return models;
    }
}
