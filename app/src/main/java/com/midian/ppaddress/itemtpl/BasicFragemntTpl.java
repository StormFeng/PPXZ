package com.midian.ppaddress.itemtpl;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.midian.ppaddress.R;
import com.midian.ppaddress.bean.BusinessCarrierDetailQueryPropertiesBean;
import com.midian.ppaddress.bean.BusinessCarrierDetailQueryPropertiesBean.PropertiesData;

import java.util.List;

import midian.baselib.utils.TDevice;
import midian.baselib.view.BaseTpl;
import midian.baselib.widget.FlowLayout;

/**
 * Created by chu on 2016/5/26.
 */
public class BasicFragemntTpl extends BaseTpl<PropertiesData> {
    private TextView key_tv, value_tv;
    private FlowLayout liner_list;

    public BasicFragemntTpl(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public BasicFragemntTpl(Context context) {
        super(context);
    }

    @Override
    protected void initView() {
        key_tv = findView(R.id.key_tv);
        value_tv = findView(R.id.value_tv);
        liner_list = findView(R.id.liner_list);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.item_basic_fragment_tpl;
    }

    @Override
    public void setBean(PropertiesData bean, int position) {
        key_tv.setText(bean.getFieldKey()+"：");
        value_tv.setText(bean.getFieldValue());

        liner_list.removeAllViews();
        if (bean.getFieldArrayValue() == null) {
            value_tv.setVisibility(View.VISIBLE);
            liner_list.setVisibility(View.GONE);
        } else {
            value_tv.setVisibility(View.GONE);
            liner_list.setVisibility(View.VISIBLE);
            for (int i = 0; i < bean.getFieldArrayValue().size(); i++) {
                PropertiesData.FileValueList valueList = bean.getFieldArrayValue().get(i);
                addView(valueList);
            }
        }

    }

    /**
     * 把textview加入到流布局
     * @param valueList
     */
    private void addView(PropertiesData.FileValueList valueList) {
        final View view =  LayoutInflater.from(_activity).inflate(R.layout.jibencanshu_value, liner_list, false);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        TextView tv = (TextView) view.findViewById(R.id.value_tv);
        ImageView iv = (ImageView) view.findViewById(R.id.value_iv);
        ac.setImage(iv, valueList.getIconurl());
        tv.setText(valueList.getName());
        //把TextView加入流式布局
        liner_list.addView(view);
    }
}
