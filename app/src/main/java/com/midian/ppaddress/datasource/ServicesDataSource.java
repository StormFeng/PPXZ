package com.midian.ppaddress.datasource;

import android.content.Context;
import android.util.Log;

import com.midian.ppaddress.bean.BusinessAgencyScreenListIndustryBean;
import com.midian.ppaddress.bean.BusinessAgencyScreenPageActivityBean;
import com.midian.ppaddress.bean.IndexMultiItemBean;
import com.midian.ppaddress.utils.AppUtil;

import java.util.ArrayList;

import midian.baselib.base.BaseListDataSource;

public class ServicesDataSource extends BaseListDataSource{
    public ServicesDataSource(Context context) {
        super(context);
    }

    @Override
    protected ArrayList load(int page) throws Exception {
        ArrayList<IndexMultiItemBean> models = new ArrayList<IndexMultiItemBean>();
        this.page=page;
        if (page == 1) {
            BusinessAgencyScreenListIndustryBean topbean=AppUtil.getPpApiClient(ac).getIndustryList();
            IndexMultiItemBean item0=new IndexMultiItemBean();
            item0.setItemViewType(0);
            item0.topBean=topbean.getData();
           /* if(page==1) {
                models.add(item0);
            }*/
            models.add(item0);
        }

        BusinessAgencyScreenPageActivityBean botbean=AppUtil.getPpApiClient(ac).getScreenPageBean(this.page);
        if(botbean!=null){
            if(botbean.isOK()){
                for(BusinessAgencyScreenPageActivityBean.ActivityList bean : botbean.getData().getList()) {
                    IndexMultiItemBean item1 = new IndexMultiItemBean();
                    item1.setItemViewType(1);
//                    item1.botBean = botbean.getData().getList();
                    item1.botBeans = bean;
                    models.add(item1);
                }
                Log.d("wqf"," 参数getList=" + botbean.getData().getList() .size());
                if("false".equals(botbean.getData().getLastPage())){
                    hasMore=true;
                }else{
                    hasMore=false;
                }
            }else{
                ac.handleErrorCode(context,botbean.errorcode);
            }
        }
        return models;
    }
}
