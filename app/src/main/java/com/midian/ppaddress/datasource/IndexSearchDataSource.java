package com.midian.ppaddress.datasource;

import android.content.Context;

import com.midian.ppaddress.bean.BusinessAppsearchSopageBean;
import com.midian.ppaddress.utils.AppUtil;

import java.util.ArrayList;

import midian.baselib.base.BaseListDataSource;
import midian.baselib.bean.NetResult;

/**
 * 首页搜索框跳转页面的dataSource
 * Created by chu on 2016/5/23.
 */
public class IndexSearchDataSource extends BaseListDataSource{

    private String carrierType = null;
    private String labelids = null;
    private String saleRental = null;
    private String startPrice = null;
    private String endPrice = null;
    private String startArea = null;
    private String endArea = null;
    private String cityid = null;
    private String countyid = null;
    private String keyword = null;



    public IndexSearchDataSource(Context context) {
        super(context);
    }

    @Override
    protected ArrayList load(int page) throws Exception {
        ArrayList<NetResult> morelist = new ArrayList<NetResult>();
        if (cityid == null) {
            cityid = ac.city_id;
        }
        page = 1;
        this.page = page;
        System.out.println("接口参数keyword="+keyword);
        if (keyword!=null&&!keyword.equals("")) {
            BusinessAppsearchSopageBean bean= AppUtil.getPpApiClient(ac).businessAppsearchSopage(carrierType, labelids, saleRental, startPrice, endPrice, startArea, endArea, cityid, countyid, keyword, this.page+"");
            if (bean != null) {
                if (bean.isOK()) {
                    morelist.addAll(bean.getData().getList());
                    this.page = Integer.parseInt(bean.getData().getPageNumber());
                    if (this.page == 1) {
                        hasMore = false;
                    } else {
                        hasMore = true;
                        this.page++;
                    }
                } else {
                    ac.handleErrorCode(context, bean.errorcode);
                }
            }
        }

        return morelist;
    }


    public String getCarrierType() {
        return carrierType;
    }

    public void setCarrierType(String carrierType) {
        this.carrierType = carrierType;
    }

    public String getLabelids() {
        return labelids;
    }

    public void setLabelids(String labelids) {
        this.labelids = labelids;
    }

    public String getSaleRental() {
        return saleRental;
    }

    public void setSaleRental(String saleRental) {
        this.saleRental = saleRental;
    }

    public String getStartPrice() {
        return startPrice;
    }

    public void setStartPrice(String startPrice) {
        this.startPrice = startPrice;
    }

    public String getEndPrice() {
        return endPrice;
    }

    public void setEndPrice(String endPrice) {
        this.endPrice = endPrice;
    }

    public String getStartArea() {
        return startArea;
    }

    public void setStartArea(String startArea) {
        this.startArea = startArea;
    }

    public String getEndArea() {
        return endArea;
    }

    public void setEndArea(String endArea) {
        this.endArea = endArea;
    }

    public String getCityid() {
        return cityid;
    }

    public void setCityid(String cityid) {
        this.cityid = cityid;
    }

    public String getCountyid() {
        return countyid;
    }

    public void setCountyid(String countyid) {
        this.countyid = countyid;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }
}
