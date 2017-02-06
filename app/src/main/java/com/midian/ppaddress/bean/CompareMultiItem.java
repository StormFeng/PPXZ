package com.midian.ppaddress.bean;


import com.midian.ppaddress.bean.AppIndexBean.RecommendCarriers;
import com.midian.ppaddress.bean.AppIndexBean.RecommendCounselors;

import java.util.ArrayList;
import java.util.List;

import midian.baselib.bean.NetResult;

/**
 * Created by chu on 2015.12.4.004.
 */
public class CompareMultiItem extends NetResult {
    public CompareDetailBean.CompareData compareData;
    public List<CompareDetailBean.FieldArrayValue> fieldArrayValue;
}
