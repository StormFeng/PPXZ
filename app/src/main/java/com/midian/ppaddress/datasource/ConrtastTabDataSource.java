package com.midian.ppaddress.datasource;

import android.content.Context;

import com.midian.ppaddress.bean.AnswerMulBean;
import com.midian.ppaddress.bean.BusinessAgencyConsultPageRecordBean;
import com.midian.ppaddress.bean.CompareDetailBean;
import com.midian.ppaddress.bean.CompareMultiItem;
import com.midian.ppaddress.bean.MemberCompareDetailBean;
import com.midian.ppaddress.bean.MemberCompareDetailBean.CompareData;
import com.midian.ppaddress.bean.TabBean;
import com.midian.ppaddress.utils.AppUtil;

import java.util.ArrayList;
import java.util.List;

import midian.baselib.base.BaseListDataSource;
import midian.baselib.bean.NetResult;

/**
 * 载体对比-表格对比页
 * Created by chu on 2016/2/16.
 */
public class ConrtastTabDataSource extends BaseListDataSource {

    private String compareids;
    private String carriertype;

    public ConrtastTabDataSource(Context context, String compareids, String carriertype) {
        super(context);
        this.compareids = compareids;
        this.carriertype = carriertype;
    }

    @Override
    protected ArrayList load(int page) throws Exception {
        ArrayList<NetResult> models = new ArrayList<NetResult>();
        System.out.println("对比列表请求参数 carriertype=" + carriertype + "--compareids=" + compareids);
        CompareDetailBean bean = AppUtil.getPpApiClient(ac).memberCompareDetailList(carriertype, compareids);//
       if (bean != null) {
            if (bean.isOK()) {
                List<CompareDetailBean.Propertys> propertysList = bean.getData().getPropertys();
                bean.setItemViewType(0);
                models.add(bean);
                int indexOut = propertysList.size();//行数
                int lineCount = 0;//列数
                if(indexOut>0){//如果列数大于0
                    lineCount = propertysList.get(0).getFieldArrayValue().get(0).getValues().size();
                }
//                System.out.println("lineCount::::"+lineCount);
                for (int i=0;i<indexOut;i++) {
                    CompareDetailBean.Propertys propertys = propertysList.get(i);
                    propertys.setItemViewType(1);
                    models.add(propertys);
                    int indexInside =  propertys.getFieldArrayValue().size();
                    for (int j= 0;j<indexInside;j++) {
                        CompareDetailBean.FieldArrayValue arrayValue = propertys.getFieldArrayValue().get(j);
                        arrayValue.setItemViewType(2);
                        if(lineCount==2){//判断 第一列与第二列相同数据
                            if(arrayValue.getValues().get(0).equals(arrayValue.getValues().get(1))){
                                bean.adapterSameDate.add(arrayValue);//列值相同的数据
                            }
                        }else if(lineCount == 3){//判断第二列与第三列数据是否相同
                            if(arrayValue.getValues().get(0).equals(arrayValue.getValues().get(1))&&arrayValue.getValues().get(0).equals(arrayValue.getValues().get(2))){
                                bean.adapterSameDate.add(arrayValue);
                            }
                        }
                        models.add(arrayValue);
                    }
                }
            }
        }

        hasMore = false;
        bean.allDate.addAll(models);//所有数据
        return models;
    }

    public String getCompareids() {
        return compareids;
    }

    public void setCompareids(String compareids) {
        this.compareids = compareids;
    }

    public String getCarriertype() {
        return carriertype;
    }

    public void setCarriertype(String carriertype) {
        this.carriertype = carriertype;
    }
}
