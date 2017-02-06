package com.midian.ppaddress.datasource;

import android.content.Context;

import com.midian.ppaddress.bean.IndexMultiCounselorPageNewstBean;
import com.midian.ppaddress.bean.OrderCounselorPageNewestBean;
import com.midian.ppaddress.utils.AppUtil;

import java.util.ArrayList;

import midian.baselib.base.BaseListDataSource;
import midian.baselib.bean.NetResult;

public class BookingCounselorDataResource extends BaseListDataSource {
    private String memberid;
    private int pageNo=1,pageSize=10;
    private String stage;
    public BookingCounselorDataResource(Context context) {
        super(context);
    }

    public String getStage() {
        return stage;
    }

    public void setStage(String stage) {
        this.stage = stage;
    }

    @Override
    protected ArrayList load(int page) throws Exception {
        memberid=ac.user_id;
        this.page=page;
        ArrayList<NetResult> models = new ArrayList<>();
        OrderCounselorPageNewestBean topbean=AppUtil.getPpApiClient(ac).getOrderCounselorPageNewest(memberid,stage,page,pageSize);
        if(topbean.isOK()){
            if(page==1){
                IndexMultiCounselorPageNewstBean item0=new IndexMultiCounselorPageNewstBean();
                item0.setItemViewType(0);
                item0.topbean = topbean;
                models.add(item0);
            }
            for(OrderCounselorPageNewestBean.Lists item : topbean.getData().getOrders().getList()){
                IndexMultiCounselorPageNewstBean item1 = new IndexMultiCounselorPageNewstBean();
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
