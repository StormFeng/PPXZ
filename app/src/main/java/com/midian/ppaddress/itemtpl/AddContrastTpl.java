package com.midian.ppaddress.itemtpl;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.midian.ppaddress.R;
import com.midian.ppaddress.bean.MemberCompareListBean.CompareData;

import midian.baselib.view.BaseTpl;

/**
 * 对比页列表条目
 * Created by chu on 2016/4/25.
 */
public class AddContrastTpl extends BaseTpl<CompareData> implements View.OnClickListener {
    private static boolean isEditable;
    private CheckBox check_cb;
    private ImageView image_iv;
    private TextView title_tv;
    private View item_ll;
    private String id,carrierid, carrierType;
    private CompareData bean;

    public AddContrastTpl(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public AddContrastTpl(Context context) {
        super(context);
    }

    @Override
    protected void initView() {
        check_cb = (CheckBox) findView(R.id.check_cb);
        image_iv = (ImageView) findView(R.id.image_iv);
        title_tv = (TextView) findView(R.id.title_tv);
        item_ll = findView(R.id.item_ll);
        item_ll.setOnClickListener(this);
        check_cb.setOnClickListener(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.item_contrast_tpl;
    }

    @Override
    public void setBean(CompareData bean, int position) {
        this.bean = bean;
        if (bean.isChecked()) {
            check_cb.setChecked(true);
        } else {
            check_cb.setChecked(false);
        }
        if (bean.getCover() == null && bean.getCover().equals("")) {
            ac.setImage(image_iv, R.drawable.default_bg);
        } else {
            ac.setImage(image_iv, bean.getCover());
        }
        title_tv.setText(bean.getCarrierName());
        id = bean.getId();
        carrierid = bean.getCarrierid();
        carrierType = bean.getCarrierType();
    }

    public static boolean isEditable() {
        return isEditable;
    }

    public static void setIsEditable(boolean isEditable) {
        AddContrastTpl.isEditable = isEditable;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.check_cb:
                bean.setChecked(!bean.isChecked());
                adapter.notifyDataSetChanged();
                break;
            case R.id.item_ll:
//                if (isEditable) {
                    //编辑状态
                    bean.setChecked(!bean.isChecked());
                    adapter.notifyDataSetChanged();
//                }
                break;
        }
    }
}
