package com.midian.ppaddress.ui.chooseaddres;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;

import com.midian.ppaddress.R;
import com.midian.ppaddress.bean.CompareDetailBean;
import com.midian.ppaddress.bean.CompareDetailBean.CompareData;
import com.midian.ppaddress.datasource.ConrtastTabDataSource;
import com.midian.ppaddress.itemtpl.TabTitleTpl;
import com.midian.ppaddress.itemtpl.TabListTpl;
import com.midian.ppaddress.itemtpl.TabHintTpl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import midian.baselib.base.BaseMultiTypeListActivity;
import midian.baselib.bean.NetResult;
import midian.baselib.shizhefei.view.listviewhelper.IDataAdapter;
import midian.baselib.shizhefei.view.listviewhelper.IDataSource;
import midian.baselib.utils.UIHelper;
import midian.baselib.widget.BaseLibTopbarView;

/**
 * 载体对比-表格对比详情页
 * Created by chu on 2016/4/26.
 */
public class TableContrastActivity extends BaseMultiTypeListActivity<NetResult> {
    public BaseLibTopbarView topbar;
    private String compareids, carriertype, carrierid;
    String id1,id2,id3;
    String title1,title2, title3;
//    private TextView right_tv;
    public String ishow="1";
    public static int flag=1;



    public static void gotoActivity(Activity mContext, String carriertype, String id) {
        Bundle bundle = new Bundle();
        bundle.putString("compareids", id);
        bundle.putString("carriertype", carriertype);
        UIHelper.jump(mContext, TableContrastActivity.class, bundle);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_table_contrast);
        compareids = mBundle.getString("compareids");
        carriertype = mBundle.getString("carriertype");
        carrierid = mBundle.getString("carrierid");

        topbar = findView(R.id.topbar);
        topbar.setTitle("载体对比").setLeftImageButton(R.drawable.icon_back, UIHelper.finish(_activity));
        topbar.setRightText("隐藏相同项", this).showRight_tv();
        topbar.getRight_tv().setTextColor(getResources().getColor(R.color.contrast_bg));
        refreshListView.setPullRefreshEnabled(false);
    }


    @Override
    protected IDataSource<ArrayList<NetResult>> getDataSource() {
        /*if(flag==1) {
            Log.d("wqf","getString(\"compareids\")::::"+mBundle.getString("compareids"));
            return new ConrtastTabDataSource(_activity, mBundle.getString("compareids"), mBundle.getString("carriertype"));
        }else {
            Log.d("wqf","compareids::::"+compareids);
            return new ConrtastTabDataSource(_activity, compareids, carriertype);
        }*/
        return new ConrtastTabDataSource(_activity, mBundle.getString("compareids"), mBundle.getString("carriertype"));
    }

    @Override
    protected ArrayList<Class> getTemplateClasses() {
        ArrayList<Class> list = new ArrayList<Class>();
        list.add(TabTitleTpl.class);//对比的标题
        list.add(TabHintTpl.class);//基本参数
        list.add(TabListTpl.class);//对比数据列表
        return list;
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        Log.d("=-=-点击事件=-=-", "onClick: isshow="+ishow);
        switch (v.getId()) {
            case R.id.right_tv:
                if ("1".equals(ishow)) {
                    topbar.setRightText("显示相同项", this).showRight_tv();
                    ishow = "2";
                    removeSame();
                    listViewHelper.getAdapter().notifyDataSetChanged();
                } else {
                    topbar.setRightText("隐藏相同项", this).showRight_tv();
                    ishow = "1";
                    addSame();
                    listViewHelper.getAdapter().notifyDataSetChanged();
                }
                break;
            case R.id.add_ll:
                UIHelper.jump(_activity, CarrierContrastActivity.class);//对比页添加截体对比的列表
                break;
        }
    }


    /**
     * 隐藏相同项
     */
    public void removeSame(){
        List<NetResult> resultList = dataSource.getResultList();
        CompareDetailBean bean = (CompareDetailBean) resultList.get(0);
        resultList.removeAll(bean.adapterSameDate);
    }

    /**
     * 重新添加数据
     */
    public void addSame(){
        List<NetResult> resultList = dataSource.getResultList();
        CompareDetailBean bean = (CompareDetailBean) resultList.get(0);
        resultList.clear();
        resultList.addAll(bean.allDate);
    }



    public String getId1() {
        return id1;
    }

    public void setId1(String id1) {
        this.id1 = id1;
    }

    public String getId2() {
        return id2;
    }

    public void setId2(String id2) {
        this.id2 = id2;
    }

    public String getId3() {
        return id3;
    }

    public void setId3(String id3) {
        this.id3 = id3;
    }

    public String getTitle1() {
        return title1;
    }

    public void setTitle1(String title1) {
        this.title1 = title1;
    }

    public String getTitle2() {
        return title2;
    }

    public void setTitle2(String title2) {
        this.title2 = title2;
    }

    public String getTitle3() {
        return title3;
    }

    public void setTitle3(String title3) {
        this.title3 = title3;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            switch (requestCode) {
                case 1005:
//                    String id = data.getStringExtra("id");
//                    System.out.println("添加 对比后返回的对比 id="+id);
//                    CompareDetailBean dataBean = (CompareDetailBean) data.getSerializableExtra("dataBean");
//                    resultList.add(dataBean.getData());
//                    dataBean.setItemViewType(0);
//                    resultList.add(dataBean);
//                    listViewHelper.getAdapter().notifyDataSetChanged();
//                    for (CompareDetailBean.Propertys propertys : dataBean.getData().getPropertys()) {
//                        propertys.setItemViewType(1);
//                        resultList.add(propertys);
//                        listViewHelper.getAdapter().notifyDataSetChanged();
//                    }
//                    for (CompareDetailBean.Propertys propertys : dataBean.getData().getPropertys()) {
//                        for (CompareDetailBean.FieldArrayValue arrayValue : propertys.getFieldArrayValue()) {
//                            arrayValue.setItemViewType(2);
//                            resultList.add(arrayValue);
//                            listViewHelper.getAdapter().notifyDataSetChanged();
//                        }
//                    }
//                    listViewHelper.refresh();



                   /* compareids = data.getStringExtra("compareids");
                    carriertype = data.getStringExtra("carriertype");
                    Log.d("wqf","返回的compareids：："+compareids);*/
                    break;
            }
        }

    }
}
