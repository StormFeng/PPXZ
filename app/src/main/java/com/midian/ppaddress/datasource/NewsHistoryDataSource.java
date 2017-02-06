package com.midian.ppaddress.datasource;

import android.content.Context;
import android.util.Log;

import com.midian.ppaddress.bean.ConsultListBean;
import com.midian.ppaddress.utils.AppUtil;

import java.util.ArrayList;
import java.util.List;

import midian.baselib.base.BaseListDataSource;
import midian.baselib.bean.NetResult;
import midian.baselib.widget.pulltorefresh.PullToRefreshBase;

public class NewsHistoryDataSource extends BaseListDataSource {

    public NewsHistoryDataSource(Context context) {
        super(context);
    }
    public static int position = -1;


    @Override
    protected ArrayList load(int page) throws Exception {
        this.page=page;
        if(page==1){
            position = -1;
        }
        ArrayList<NetResult> models = new ArrayList<>();
        ConsultListBean bean= AppUtil.getPpApiClient(ac).getConsultList(ac.user_id,ac.getProperty("user_type"),page);
        List<ConsultListBean.Lists> topList=new ArrayList<>();
        List<ConsultListBean.Lists> botList=new ArrayList<>();
        List<ConsultListBean.Lists> cenList=new ArrayList<>();
        if(bean.isOK()){
            for(int i=0;i<bean.getData().getList().size();i++){
                if("0".equals(bean.getData().getList().get(i).getClose())){
                    ConsultListBean.Lists lists = bean.getData().getList().get(i);
                    lists.setItemViewType(0);
                    topList.add(lists);
                }else{
                    ConsultListBean.Lists lists = bean.getData().getList().get(i);
                    lists.setItemViewType(2);
                    botList.add(lists);
                }
            }
            if(position!=-1){
                Log.d("wqf","BBB:::"+position);
                if(topList.size()>0) {
                    models.addAll(position, topList);
                }
                position += topList.size();
            }else{
                models.addAll(topList);
                Log.d("wqf","AAA:::"+position);
                if(botList.size()>0){
                    position=topList.size();
                    ConsultListBean.Lists lists = new ConsultListBean.Lists();
                    lists.setItemViewType(1);
                    cenList.add(lists);
                    models.addAll(cenList);
                    Log.d("wqf","CCC:::"+position);
                }
            }
//            if(position==-1){
//                Log.d("wqf","AAA:::"+position);
//                models.addAll(topList);
//                if(botList.size()>0) {
//                    position = models.size();
//                    ConsultListBean.Lists lists = new ConsultListBean.Lists();
//                    lists.setItemViewType(1);
//                    cenList.add(lists);
//                    models.addAll(cenList);
//                }
//            }else if(position!=-1){
//                Log.d("wqf","BBB:::"+position);
//               try {
//                   models.addAll(position, topList);
//               } catch (Exception e) {
//                   e.printStackTrace();
//               }
//               position += topList.size();
//            }
            models.addAll(botList);
            if(bean.getData().getList().size()<10){
                hasMore=false;
            }else{
                hasMore=true;
            }
        }
        return models;
    }
}
