package com.midian.ppaddress.ui.homepage;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import com.midian.login.R;
import com.midian.ppaddress.bean.CityMultiItem;
import com.midian.ppaddress.datasource.CitysReleaseDataSource;
import com.midian.ppaddress.itemtpl.ChooseCitysHeadTpl;
import com.midian.ppaddress.itemtpl.ChooseCitysViewTpl;
import com.midian.ppaddress.itemtpl.HomeChooseCitysViewTpl;

import java.util.ArrayList;

import midian.baselib.base.BaseMultiTypeListActivity;
import midian.baselib.shizhefei.view.listviewhelper.IDataSource;
import midian.baselib.utils.UIHelper;
import midian.baselib.widget.BaseLibTopbarView;

/**
 * 首页获取发布城市列表
 *
 * @author chu
 */
public class CitysReleaseListActivity extends BaseMultiTypeListActivity<CityMultiItem> {

    private BaseLibTopbarView topbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    private void initView() {
        topbar = (BaseLibTopbarView) findViewById(R.id.topbar);
        topbar.setTitle("地区");
        topbar.setLeftImageButton(R.drawable.icon_back, null).setLeftText("返回", UIHelper.finish(_activity));
        listView.setOnItemClickListener(this);
    }


    @Override
    protected IDataSource<ArrayList<CityMultiItem>> getDataSource() {
        return new CitysReleaseDataSource(_activity);
    }

    @Override
    protected ArrayList<Class> getTemplateClasses() {

        ArrayList<Class> tpls = new ArrayList<Class>();
//        tpls.add(ChooseLocationCitysHeadTpl.class);//定位条目
        tpls.add(ChooseCitysHeadTpl.class);//选择城市
        tpls.add(HomeChooseCitysViewTpl.class);//城市列表
        return tpls;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        super.onItemClick(parent, view, position, id);
        Bundle mBundle = new Bundle();
        mBundle.putString("city_id",dataSource.getResultList().get(position).data.getId());
        mBundle.putString("city_name",dataSource.getResultList().get(position).data.getCity());
        mBundle.putString("lng",dataSource.getResultList().get(position).data.getLng());
        mBundle.putString("lat",dataSource.getResultList().get(position).data.getLat());
        setResult(RESULT_OK, mBundle);
        finish();
    }


}
