package com.midian.ppaddress.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.midian.ppaddress.R;
import com.midian.ppaddress.datasource.ServicesDataSource;
import com.midian.ppaddress.itemtpl.ServicesContentTpl;
import com.midian.ppaddress.itemtpl.ServicesHeadTpl;
import com.midian.ppaddress.ui.company.CompanyApplyActivity;
import com.midian.ppaddress.ui.company.OneKeyAppActivity;
import com.midian.ppaddress.utils.AppUtil;

import java.util.ArrayList;

import midian.baselib.api.ApiCallback;
import midian.baselib.base.BaseListFragment;
import midian.baselib.base.BaseMultiTypeListFragment;
import midian.baselib.bean.NetResult;
import midian.baselib.mining.app.zxing.view.ViewfinderView;
import midian.baselib.shizhefei.view.listviewhelper.IDataAdapter;
import midian.baselib.shizhefei.view.listviewhelper.IDataSource;
import midian.baselib.utils.UIHelper;
import midian.baselib.widget.BaseLibTopbarView;

/**
 * 服务（服务机构）dff
 * Created by chu on 2016/2/15.
 */
public class ServicesFragment extends BaseMultiTypeListFragment implements View.OnClickListener {

    private BaseLibTopbarView topbar;
    private ImageButton btn_apply;

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
    }


    private void initView(View v) {
        topbar = (BaseLibTopbarView) v.findViewById(R.id.topbar);
        topbar.setTitle("服务机构");
        btn_apply= (ImageButton) v.findViewById(R.id.btn_apply);
        btn_apply.setOnClickListener(this);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    public void refreshCity(String user_type) {
//        View view = LayoutInflater.from(_activity).inflate(R.layout.fragment_service, null);
//        if(!"0".equals(user_type)){
//            view.findViewById(R.id.btn_apply).setVisibility(View.GONE);
//        }else{
//            view.findViewById(R.id.btn_apply).setVisibility(View.VISIBLE);
//        }
}

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_service;
    }

    @Override
    protected IDataSource<ArrayList> getDataSource() {
        return new ServicesDataSource(_activity);
    }

    @Override
    protected ArrayList<Class> getTemplateClasses() {
        ArrayList<Class> tpls = new ArrayList<Class>();
        tpls.add(ServicesHeadTpl.class);
        tpls.add(ServicesContentTpl.class);
        return tpls;
    }

    @Override
    public void onClick(View view) {
        if (!"0".equals(ac.getProperty("user_type"))) {
            UIHelper.t(_activity, "只有客商身份才能操作");
            return;
        }
        Bundle bundle=new Bundle();
        bundle.putString("flag","ServicesFragment");
        UIHelper.jump(_activity, OneKeyAppActivity.class,bundle);
    }

//    @Override
//    protected Class getTemplateClass() {
//        return ServicesContentTpl.class;
//    }
}
