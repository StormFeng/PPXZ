package com.midian.ppaddress.ui.personal.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.midian.ppaddress.R;
import com.midian.ppaddress.datasource.ToMyCommentDataSource;
import com.midian.ppaddress.itemtpl.ToMyCommentTpl;

import java.util.ArrayList;

import midian.baselib.base.BaseFragmentActivity;
import midian.baselib.base.BaseListFragment;
import midian.baselib.shizhefei.view.listviewhelper.IDataAdapter;
import midian.baselib.shizhefei.view.listviewhelper.IDataSource;

/**
 * 经纪人评价--对我的评价
 * Created by chu on 2016/5/5.
 */
public class ToMyCommentFragment extends BaseListFragment {
//    private TextView btn_2;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        btn_2 = (TextView) _activity.findViewById(R.id.btn_2);
    }

    @Override
    protected IDataSource<ArrayList> getDataSource() {
        return new ToMyCommentDataSource(_activity);
    }


    @Override
    protected Class getTemplateClass() {
        return ToMyCommentTpl.class;
    }


    @Override
    public void onEndRefresh(IDataAdapter adapter, Object result) {
//        btn_2.setText("对TA的评价(" + resultList.size() + ")");
    }
}
