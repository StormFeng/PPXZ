package com.midian.ppaddress.itemtpl;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.midian.ppaddress.R;
import com.midian.ppaddress.bean.MemberCompareListBean;
import com.midian.ppaddress.ui.personal.MyContrastChooseActivity;

import midian.baselib.bean.NetResult;
import midian.baselib.utils.UIHelper;
import midian.baselib.view.BaseTpl;

public class MyContrastChooseTpl extends BaseTpl<MemberCompareListBean>{

    private View item_ll;
    private ImageView image_iv;
    private TextView title_tv;
    private CheckBox checkBox;
    private int flag=0;


    public MyContrastChooseTpl(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyContrastChooseTpl(Context context) {
        super(context);
    }

    @Override
    protected void init(Context context) {
        super.init(context);
    }

    @Override
    protected void initView() {
        image_iv=findView(R.id.image_iv);
        title_tv=findView(R.id.title_tv);
        checkBox=findView(R.id.check_cb);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.item_contrast_tpl;
    }

    @Override
    public void setBean(MemberCompareListBean bean, int position) {
        if(bean.isOK()){
            title_tv.setText(bean.getData().get(position).getCarrierName());
            ac.setImage(image_iv,bean.getData().get(position).getCover());
            checkBox.setChecked(bean.getData().get(position).isChecked());
        }
    }

}
