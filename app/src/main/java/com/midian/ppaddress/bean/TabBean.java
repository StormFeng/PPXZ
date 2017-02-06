package com.midian.ppaddress.bean;

import java.util.ArrayList;
import java.util.List;

import midian.baselib.bean.NetResult;

/**
 * Created by chu on 2016/5/27.
 */
public class TabBean extends NetResult {
    public TabBean(){
        super();
        list=new ArrayList<String>();
    }


    String key;
    List<String> list;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public List<String> getList() {
        return list;
    }

    public void setList(List<String> list) {
        this.list = list;
    }
}
