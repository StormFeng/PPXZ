package com.midian.ppaddress.ui.personal;

import android.os.Bundle;

import com.midian.ppaddress.bean.CityMultiItem;
import com.midian.login.bean.ProvincesBean.City;
import com.midian.ppaddress.datasource.ChooseAreaDataSource;
import com.midian.ppaddress.itemtpl.ChooseAreaTpl;

import java.util.ArrayList;
import java.util.List;

import midian.baselib.base.BaseListActivity;
import midian.baselib.shizhefei.view.listviewhelper.IDataSource;
import midian.baselib.utils.UIHelper;
import midian.baselib.widget.BaseLibTopbarView;

/**
 * 选择地区
 *
 * @author chu
 *
 */
public class ChooseAreaActivity extends BaseListActivity<CityMultiItem> {

	private BaseLibTopbarView topbar;
    private List<City> city = new ArrayList<City>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        city = (List<City>) mBundle.getSerializable("citys");
		initView();
	}

	private void initView() {
		topbar = (BaseLibTopbarView) findViewById(com.midian.login.R.id.topbar);
		topbar.setTitle("选择地区");
		topbar.setLeftImageButton(com.midian.login.R.drawable.icon_back, UIHelper.finish(this));

    }

	@Override
	protected IDataSource<ArrayList<CityMultiItem>> getDataSource() {
		return new ChooseAreaDataSource(_activity,(List<City>) mBundle.getSerializable("citys"));
    }

	@Override
	protected Class getTemplateClass() {

		return ChooseAreaTpl.class;
	}

}
