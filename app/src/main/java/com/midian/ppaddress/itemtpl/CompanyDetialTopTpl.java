package com.midian.ppaddress.itemtpl;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.midian.ppaddress.R;
import com.midian.ppaddress.bean.BusinessAgencyDetailPageCommentsBean;
import com.midian.ppaddress.bean.BusinessAgencyDetailShowDetailBean;
import com.midian.ppaddress.bean.BusinessAgencyScreenListIndustryBean;
import com.midian.ppaddress.bean.CompanyDetailBean;
import com.midian.ppaddress.bean.MemberFavoriteAgencyBean;
import com.midian.ppaddress.ui.company.ChooseWaiterActivity;
import com.midian.ppaddress.ui.company.CompanyDetialActivity;
import com.midian.ppaddress.ui.company.CompanyListActivity;
import com.midian.ppaddress.utils.AppUtil;

import java.util.ArrayList;
import java.util.List;

import midian.baselib.api.ApiCallback;
import midian.baselib.bean.NetResult;
import midian.baselib.utils.UIHelper;
import midian.baselib.view.BaseTpl;
import midian.baselib.widget.BaseLibTopbarView;

public class CompanyDetialTopTpl extends BaseTpl<CompanyDetailBean> implements View.OnClickListener, ApiCallback {

    private ImageView iv_logo, iv_adver, iv_portrait1, iv_portrait2, iv_portrait3, iv_portrait4;
    private TextView tv_goodRate, tv_label1, tv_label2, tv_label3, tv_name, tv_serviceCount, tv_favorite, tv_intro, tv_fullname, tv_rateCount;
    private View service_ll;
    private ImageButton iv_collect;

    public static String name;
    public static String isCollect;
    public static String favoriteid;
    public static String agencyId;

    private TextView tv_count;
    private BaseLibTopbarView topbar;
    private List<BusinessAgencyDetailShowDetailBean.Agencyexpert> agencyexpertList;
    public CompanyDetialTopTpl(Context context) {
        super(context);
    }
    public CompanyDetialTopTpl(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void initView() {
        iv_logo = findView(R.id.iv_logo);
        iv_adver = findView(R.id.iv_adver);
        iv_portrait1 = findView(R.id.iv_portrait1);
        iv_portrait2 = findView(R.id.iv_portrait2);
        iv_portrait3 = findView(R.id.iv_portrait3);
        iv_portrait4 = findView(R.id.iv_portrait4);
        tv_goodRate = findView(R.id.tv_goodRate);
        tv_label1 = findView(R.id.tv_label1);
        tv_label2 = findView(R.id.tv_label2);
        tv_label3 = findView(R.id.tv_label3);
        tv_name = findView(R.id.tv_name);
        tv_serviceCount = findView(R.id.tv_serviceCount);
        tv_rateCount = findView(R.id.rateCount);
        tv_intro = findView(R.id.tv_intro);
        findView(R.id.ll_Agency).setOnClickListener(this);
        service_ll = findView(R.id.service_ll);
//        findView(R.id.right_ib).setOnClickListener(this);//收藏按钮
        tv_count = findView(R.id.rateCount);

        //非普通客商隐藏服务达人列表
       /* if (!"0".equals(ac.getProperty("user_type"))) {
            service_ll.setVisibility(View.GONE);
        } else {
            service_ll.setVisibility(View.VISIBLE);
        }*/

    }

    @Override
    protected int getLayoutId() {
        return R.layout.item_company_detail_top;
    }

    @Override
    public void setBean(CompanyDetailBean bean, int position) {
        if (bean.getItemViewType() == 0) {
            ac.setImage(iv_logo, bean.topBean.getData().getAgency().getLogo());
            ac.setImage(iv_adver, bean.topBean.getData().getAgency().getAdver());
            tv_goodRate.setText(bean.topBean.getData().getAgency().getGoodRate() + "%");
            name=bean.topBean.getData().getAgency().getName();
            tv_name.setText(bean.topBean.getData().getAgency().getName());
            tv_serviceCount.setText(bean.topBean.getData().getAgency().getServiceCount());
            tv_intro.setText(bean.topBean.getData().getAgency().getIntro());
            String[] str = bean.topBean.getData().getAgency().getLabels().split(",");
            try {
                tv_label1.setText(str[0]);
                tv_label2.setText(str[1]);
                tv_label3.setText(str[2]);
            } catch (Exception e) {
                e.printStackTrace();
            }
            if ("".equals(tv_label1.getText().toString().trim())) {
                tv_label1.setVisibility(GONE);
            }
            if ("".equals(tv_label2.getText().toString().trim())) {
                tv_label2.setVisibility(GONE);
            }
            if ("".equals(tv_label3.getText().toString().trim())) {
                tv_label3.setVisibility(GONE);
            }
            agencyexpertList = bean.topBean.getData().getAgencyexpert();
            favoriteid = bean.topBean.getData().getAgency().getFavoriteid();
            isCollect = bean.topBean.getData().getAgency().getFavorite();
            if (!"0".equals(ac.getProperty("user_type"))) {
                CompanyDetialActivity.topbar.hideRight_ib();
                Log.d("wqf","TOPTPL");
            }else {
                Log.d("wqf","TYPE::"+ac.getProperty("user_type"));
                if ("0".equals(CompanyDetialTopTpl.isCollect)) {
                    CompanyDetialActivity.topbar.setRightImageButton(R.drawable.icon_collect_no);
                } else if ("1".equals(CompanyDetialTopTpl.isCollect)) {
                    CompanyDetialActivity.topbar.setRightImageButton(R.drawable.icon_collect_yes);
                }
            }
            agencyId = bean.topBean.getData().getAgency().getId();
            try {
//                tv_rateCount.setText("用户评价("+bean.botBean.size()+")");
                ac.setImage(iv_portrait1, agencyexpertList.get(0).getPortrait());
                ac.setImage(iv_portrait2, agencyexpertList.get(1).getPortrait());
                ac.setImage(iv_portrait3, agencyexpertList.get(2).getPortrait());
                ac.setImage(iv_portrait4, agencyexpertList.get(3).getPortrait());
            } catch (Exception e) {
                e.printStackTrace();
            }
            AppUtil.getPpApiClient(ac).businessAgencyDetailPageComments(null, agencyId, this);
        }
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_Agency:
                Intent intent = new Intent();
                intent.setClass(_activity, ChooseWaiterActivity.class);
                intent.putExtra("agencyId", agencyId);
                _activity.startActivity(intent);
                break;
            case R.id.right_ib:
                if ("0".equals(isCollect)) {
                    AppUtil.getPpApiClient(ac).memberFavoriteAgency(ac.user_id, agencyId, this);
                }
                if ("1".equals(isCollect)) {
                    AppUtil.getPpApiClient(ac).memberFavoriteCancelAgency(ac.user_id, favoriteid, this);
                }
                break;
        }
    }

    @Override
    public void onApiSuccess(NetResult res, String tag) {
        if (res.isOK()) {
            if ("memberFavoriteAgency".equals(tag)) {
                isCollect = "1";
                UIHelper.t(_activity, "收藏成功");
                MemberFavoriteAgencyBean bean = (MemberFavoriteAgencyBean) res;
                favoriteid = bean.getData().getId();
//                ac.setProperty(agencyId, favoriteid);
//                topbar.setRightImageButton(R.drawable.icon_collect_yes);
            }
            if ("businessAgencyDetailPageComments".equals(tag)) {
                BusinessAgencyDetailPageCommentsBean bean = (BusinessAgencyDetailPageCommentsBean) res;
                String size = bean.getData().getTotalRow();
                tv_rateCount.setText("用户评价(" + size + ")");
            }
            if ("memberFavoriteCancelAgency".equals(tag)) {
                isCollect = "0";
                UIHelper.t(_activity, res.message);
//                topbar.setRightImageButton(R.drawable.icon_collect_no);
            }
        } else {
            ac.handleErrorCode(_activity, res.message);
        }
//        if("memberFavoriteCancelAgency".equals(tag)){
//            isCollect="0";
//            UIHelper.t(_activity,"取消收藏");
//            topbar.setRightImageButton(R.drawable.icon_collect_no);
//        }
    }

    @Override
    public void onApiStart(String tag) {

    }

    @Override
    public void onApiLoading(long count, long current, String tag) {

    }

    @Override
    public void onApiFailure(Throwable t, int errorNo, String strMsg, String tag) {

    }

    @Override
    public void onParseError(String tag) {

    }
}
