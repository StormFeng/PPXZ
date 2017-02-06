package com.midian.ppaddress.itemtpl;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

import com.midian.ppaddress.R;
import com.midian.ppaddress.bean.AnswerMulBean;
import com.midian.ppaddress.bean.CompareDetailBean;
import com.midian.ppaddress.bean.CompareMultiItem;

import midian.baselib.bean.NetResult;
import midian.baselib.view.BaseTpl;

public class TabHintTpl extends BaseTpl<NetResult>{

    private TextView key_tv;

    public TabHintTpl(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TabHintTpl(Context context) {
        super(context);
    }


    @Override
    protected void initView() {
        key_tv = findView(R.id.key_tv);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.item_title;
    }
    CompareDetailBean mTabBean;
    @Override
    public void setBean(NetResult bean, int position) {

        if (bean instanceof  CompareDetailBean.Propertys) {
            CompareDetailBean.Propertys propertys = (CompareDetailBean.Propertys) bean;
            key_tv.setText(propertys.getFieldKey());//渲染标题
        }

       /* if (bean.getItemViewType() == 1) {
            for (int i = 0; i < bean.compareData.getPropertys().size(); i++) {
                if (i < bean.compareData.getPropertys().size()) {
                    key_tv.setText(bean.compareData.getPropertys().get(i).getFieldKey());//渲染标题
                    System.out.println("对比获取的参数=" + bean.compareData.getPropertys().get(i).getFieldKey());
                } else {
                    key_tv.setText("");
                }
            }
        }*/
    }

}
