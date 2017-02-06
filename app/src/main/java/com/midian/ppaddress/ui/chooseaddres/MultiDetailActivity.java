package com.midian.ppaddress.ui.chooseaddres;

import android.os.Bundle;
import android.view.View;

import com.midian.ppaddress.R;
import com.midian.ppaddress.datasource.MultiDetailDataSource;
import com.midian.ppaddress.itemtpl.CommentTpl;
import com.midian.ppaddress.itemtpl.DetailBasicInfoTpl;
import com.midian.ppaddress.itemtpl.DetailHeadTpl;

import java.util.ArrayList;

import midian.baselib.base.BaseMultiTypeListFragmentActivity;
import midian.baselib.shizhefei.view.listviewhelper.IDataAdapter;
import midian.baselib.shizhefei.view.listviewhelper.IDataSource;
import midian.baselib.utils.UIHelper;
import midian.baselib.widget.BaseLibTopbarView;

/**
 * 载体详情页
 * Created by chu on 2016/4/22.
 */
public class MultiDetailActivity extends BaseMultiTypeListFragmentActivity implements View.OnClickListener {
    private BaseLibTopbarView topbar;
    private String title, carrierType, carrierid;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    private void initView() {
        topbar = findView(R.id.topbar);
        title = mBundle.getString("carriertitle");
        carrierType = mBundle.getString("carriertype");
        carrierid = mBundle.getString("carrierid");
        topbar.setTitle(title).setLeftText(" ",null).setLeftImageButton(R.drawable.icon_back, UIHelper.finish(_activity)).setRightText("对比", this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_multi_detail;
    }

    @Override
    protected IDataSource<ArrayList> getDataSource() {
        return new MultiDetailDataSource(_activity, mBundle.getString("carrierid"));
    }

    @Override
    protected ArrayList<Class> getTemplateClasses() {
        ArrayList<Class> tpls = new ArrayList<Class>();
        tpls.add(DetailHeadTpl.class);
        tpls.add(DetailBasicInfoTpl.class);
        tpls.add(CommentTpl.class);
        return tpls;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.right_tv://对比
                if (!"0".equals(ac.getProperty("user_type"))) {
                    UIHelper.t(_activity,"只有客商身份才能操作");
                    return;
                }
                mBundle.putString("carriertype", carrierType);
                mBundle.putString("carrierid",carrierid);
//                UIHelper.jump(_activity, ContrastListActivity.class,mBundle);
                UIHelper.jump(_activity,CarrierDetailContrastActivity.class,mBundle);
                break;
        }
    }

    @Override
    public void onEndRefresh(IDataAdapter adapter, Object result) {
    }

    @Override
    public void onEndLoadMore(IDataAdapter adapter, Object result) {

    }
}
