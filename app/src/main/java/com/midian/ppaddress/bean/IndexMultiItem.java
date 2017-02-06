package com.midian.ppaddress.bean;


import java.util.ArrayList;

import midian.baselib.bean.NetResult;

import com.midian.ppaddress.bean.AppIndexBean.RecommendCarriers;
import com.midian.ppaddress.bean.AppIndexBean.RecommendCounselors;

/**
 * Created by chu on 2015.12.4.004.
 */
public class IndexMultiItem extends NetResult {
    public ArrayList<RecommendCarriers> carrierses;//载体推荐
    public RecommendCounselors counselorses;//顾问推荐
    public String  investEnvironUrl;
}
