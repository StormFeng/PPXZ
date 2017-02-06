package com.midian.ppaddress.itemtpl;

import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.midian.ppaddress.R;
import com.midian.ppaddress.bean.CompareDetailBean;
import com.midian.ppaddress.ui.chooseaddres.CarrierMapLocationActivity;
import com.midian.ppaddress.ui.chooseaddres.TableContrastActivity;

import java.util.ArrayList;
import java.util.List;

import midian.baselib.bean.NetResult;
import midian.baselib.utils.UIHelper;
import midian.baselib.view.BaseTpl;

public class TabListTpl extends BaseTpl<NetResult>  {


    public TabListTpl(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TabListTpl(Context context) {
        super(context);
    }

    TextView tv_1, tv2, tv3, tv4;
    List<TextView> textList;

    @Override
    protected int getLayoutId() {
        return R.layout.item_tab2;
    }

    @Override
    protected void initView() {
        tv_1 = findView(R.id.tv_1);
        tv2 = findView(R.id.tv2);
        tv3 = findView(R.id.tv3);
        tv4 = findView(R.id.tv4);
        textList = new ArrayList<TextView>();
        textList.add(tv2);
        textList.add(tv3);
        textList.add(tv4);
    }

    @Override
    public void setBean(NetResult bean,  final int position) {
        if (bean instanceof CompareDetailBean.FieldArrayValue) {
            CompareDetailBean.FieldArrayValue item = (CompareDetailBean.FieldArrayValue) bean;
            tv_1.setText(item.getKey());
            for (int i = 0; i < textList.size(); i++) {
                if (i < item.getValues().size()) {
                    textList.get(i).setText(item.getValues().get(i));//渲染对比的value
//                    if (position == 2) {
                    if ("地理位置".equals(item.getKey())) {
                        textList.get(i).setEnabled(true);
                        textList.get(i).setTextColor(getResources().getColor(R.color.contrast_bg));
                        final int finalI = i;
                        textList.get(i).setOnClickListener(new OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Bundle bundle = new Bundle();
                                if (finalI == 0) {
                                    bundle.putString("carrierid", ((TableContrastActivity) _activity).getId1());
                                    bundle.putString("title", ((TableContrastActivity) _activity).getTitle1());
                                } else if (finalI == 1) {
                                    bundle.putString("carrierid", ((TableContrastActivity) _activity).getId1());
                                    bundle.putString("title", ((TableContrastActivity) _activity).getTitle2());
                                } else if (finalI == 2) {
                                    bundle.putString("carrierid", ((TableContrastActivity) _activity).getId2());
                                    bundle.putString("title", ((TableContrastActivity) _activity).getTitle3());
                                }
                                UIHelper.jump(_activity, CarrierMapLocationActivity.class, bundle);
                            }
                        });
                    } else {
                        textList.get(i).setEnabled(false);
                        textList.get(i).setTextColor(getResources().getColor(R.color.text_bg60));
                    }
                } else {
                    textList.get(i).setText("");
                }
            }
        }

    }
}
