package com.midian.ppaddress.bean;


import com.midian.ppaddress.bean.AppIndexBean.RecommendCarriers;
import com.midian.ppaddress.bean.AppIndexBean.RecommendCounselors;

import java.lang.reflect.Array;
import java.util.ArrayList;

import midian.baselib.bean.NetResult;

public class IndexMultiMemberPageNewstBean extends NetResult {
    public MemberOrderMemberPageNewestBean topbean;
    public ArrayList<MemberOrderMemberPageNewestBean.Lists> botbean;
    public MemberOrderMemberPageNewestBean.Lists botbeans;
}
