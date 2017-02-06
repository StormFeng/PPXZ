package com.midian.ppaddress.itemtpl;

import android.content.Context;
import android.text.Html;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.midian.ppaddress.R;
import com.midian.ppaddress.bean.ClinchDealListBean;
import com.midian.ppaddress.ui.personal.UploadContractActivity;

import midian.baselib.bean.NetResult;
import midian.baselib.utils.UIHelper;
import midian.baselib.view.BaseTpl;

/**
 * 详情-评论条目
 * Created by chu on 2016/4/25.
 */
public class MyRecommendF_2Tpl extends BaseTpl<ClinchDealListBean> implements View.OnClickListener {

    private ImageView iv_title;
    private TextView tv_name, tv_booknumber, tv_time, tv_dec, tv_money;

    public MyRecommendF_2Tpl(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyRecommendF_2Tpl(Context context) {
        super(context);
    }

    @Override
    protected void initView() {
        iv_title = (ImageView) findView(R.id.iv_title);
        tv_name = (TextView) findView(R.id.tv_name);
        tv_booknumber = (TextView) findView(R.id.tv_booknumber);
        tv_time = (TextView) findView(R.id.tv_time);
        tv_dec = (TextView) findView(R.id.tv_dec);
        tv_money = (TextView) findView(R.id.tv_moneynumber);
        findView(R.id.iv_dot).setVisibility(GONE);
        findView(R.id.tv_state).setVisibility(GONE);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.item_myrecommend;
    }

    @Override
    public void setBean(ClinchDealListBean bean, int position) {
        if (bean.isOK()) {
            ac.setImage(iv_title, bean.getData().getList().get(position).getPortrait());
            tv_name.setText(bean.getData().getList().get(position).getMemberName());
            tv_booknumber.setText("订单号：" + bean.getData().getList().get(position).getNumber());
            if (TextUtils.isEmpty(bean.getData().getList().get(position).getMoney())) {
                tv_money.setText("¥" + "0");
            } else {
                tv_money.setText("¥" + bean.getData().getList().get(position).getMoney());
            }
            String html = "您推荐的客商已成功签约 " + "<font color='#0076FF'>" + bean.getData().getList().get(position).getCarrierName() + "</font>" + ", 成交面积" + bean.getData().getList().get(position).getDealarea() + "㎡";
            tv_dec.setText(Html.fromHtml(html));
            tv_time.setText(bean.getData().getList().get(position).getUpdateTime());
        }
    }

    @Override
    public void onClick(View view) {

    }
}
