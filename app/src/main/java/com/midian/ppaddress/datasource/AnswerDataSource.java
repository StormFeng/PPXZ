package com.midian.ppaddress.datasource;

import android.content.Context;

import com.midian.ppaddress.bean.AnswerMulBean;
import com.midian.ppaddress.bean.BusinessAgencyConsultPageRecordBean;
import com.midian.ppaddress.utils.AppUtil;

import java.util.ArrayList;

import midian.baselib.base.BaseListDataSource;
import midian.baselib.bean.NetResult;

public class AnswerDataSource extends BaseListDataSource {

    private int pageNo=1;
    private String questionid;

    public AnswerDataSource(Context context) {
        super(context);
    }

    public AnswerDataSource(String questionid,Context context) {
        super(context);
        this.questionid=questionid;
    }

    @Override
    protected ArrayList load(int page) throws Exception {
        ArrayList<AnswerMulBean> models=new ArrayList<>();
        AnswerMulBean item0=new AnswerMulBean();
        item0.setItemViewType(0);
        item0.topBean=AppUtil.getPpApiClient(ac).getAgencyConsultShowQuestionBean(questionid);
        models.add(item0);
        BusinessAgencyConsultPageRecordBean itemBean=AppUtil.getPpApiClient(ac).getAgengConsulPage(ac.user_id,ac.getProperty("user_type"),questionid,page);
        int j=itemBean.getData().getList().size();
        for(int i=0;i<j;i++){
            if("left".equals(itemBean.getData().getList().get(j-i-1).getPosition())){
                AnswerMulBean leftItem=new AnswerMulBean();
                leftItem.botBean=itemBean.getData().getList().get(j-i-1);
                leftItem.setItemViewType(1);
                models.add(leftItem);
            }else if("right".equals(itemBean.getData().getList().get(j-i-1).getPosition())){
                AnswerMulBean rightItem=new AnswerMulBean();
                rightItem.botBean=itemBean.getData().getList().get(j-i-1);
                rightItem.setItemViewType(2);
                models.add(rightItem);
            }
        }
        if("1".equals(item0.topBean.getData().getClose())){
            AnswerMulBean item1=new AnswerMulBean();
            item1.setItemViewType(3);
            models.add(item1);
        }
        hasMore = false;
        return models;
    }
}
