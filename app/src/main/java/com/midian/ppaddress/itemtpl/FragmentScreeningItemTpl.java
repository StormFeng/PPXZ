package com.midian.ppaddress.itemtpl;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

import com.midian.ppaddress.R;
import com.midian.ppaddress.ui.main.ChooseAddressFragment.ScreeningItem;

import midian.baselib.view.BaseTpl;

/**
 * 选址筛选条目
 * Created by chu on 2016/5/21.
 */
public class FragmentScreeningItemTpl extends BaseTpl<ScreeningItem>{
    private TextView item_name;
    public FragmentScreeningItemTpl(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public FragmentScreeningItemTpl(Context context) {
        super(context);
    }

    @Override
    protected void initView() {
        item_name = findView(R.id.item_name);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.item_screening_tpl;
    }

    @Override
    public void setBean(ScreeningItem bean, int position) {
        item_name.setText(bean.getName());
    }
}
