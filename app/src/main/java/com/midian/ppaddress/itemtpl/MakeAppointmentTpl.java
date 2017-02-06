package com.midian.ppaddress.itemtpl;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.CheckBox;
import android.widget.TextView;

import com.midian.ppaddress.R;

import midian.baselib.bean.NetResult;
import midian.baselib.view.BaseTpl;
import midian.baselib.widget.CircleImageView;

/**
 * 预约选择物业顾问 的条目
 * Created by chu on 2016/4/27.
 */
public class MakeAppointmentTpl extends BaseTpl {
    private CircleImageView head_cv;
    private TextView name_tv;
    private CheckBox check_cb;

    public MakeAppointmentTpl(Context context) {
        super(context);
    }

    public MakeAppointmentTpl(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void initView() {
        head_cv = (CircleImageView) findView(R.id.head_cv);
        name_tv = (TextView) findView(R.id.name_tv);
        check_cb = (CheckBox) findView(R.id.check_cb);


    }

    @Override
    protected int getLayoutId() {
        return R.layout.item_make_appointment;
    }

    @Override
    public void setBean(NetResult bean, int position) {

    }


}
