package com.midian.ppaddress.itemtpl;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import com.midian.ppaddress.R;

import midian.baselib.bean.NetResult;
import midian.baselib.view.BaseTpl;
import midian.baselib.widget.CircleImageView;

/**13559196902
 * Created by chu on 2016/4/27.
 */
public class ConsultantDetailHeadTpl extends BaseTpl{

    private View bg;
    private CircleImageView head_cv;
    private TextView name_tv,type_tv,compangy_tv,comment_tv,sign_tv;


    public ConsultantDetailHeadTpl(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ConsultantDetailHeadTpl(Context context) {
        super(context);
    }

    @Override
    protected void initView() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.item_consultant_detail_head_tpl;
    }

    @Override
    public void setBean(NetResult bean, int position) {

    }
}
