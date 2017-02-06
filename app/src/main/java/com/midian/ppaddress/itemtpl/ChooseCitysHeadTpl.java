package com.midian.ppaddress.itemtpl;

import android.content.Context;
import android.util.AttributeSet;

import com.midian.ppaddress.bean.CityMultiItem;
import com.midian.ppaddress.R;

import midian.baselib.view.BaseTpl;

public class ChooseCitysHeadTpl extends BaseTpl<CityMultiItem> {

	public ChooseCitysHeadTpl(Context context) {
		super(context);
	}

	public ChooseCitysHeadTpl(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	@Override
	protected void initView() {
	}

	@Override
	protected int getLayoutId() {
		return R.layout.item_choose_citys_head_tpl;
	}

	@Override
	public void setBean(CityMultiItem bean, int position ) {

	}

}
