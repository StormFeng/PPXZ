package com.midian.ppaddress.ui.company;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AbsListView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.midian.ppaddress.R;
import com.midian.ppaddress.bean.BusinessAgencyDetailListAgencyexpertBean;
import com.midian.ppaddress.bean.BusinessAgencyDetailPageCommentsBean;
import com.midian.ppaddress.bean.CompanyDetailBean;
import com.midian.ppaddress.bean.MemberFavoriteAgencyBean;
import com.midian.ppaddress.datasource.CompanyDeatilDataResource;
import com.midian.ppaddress.itemtpl.CompanyDetialTopTpl;
import com.midian.ppaddress.itemtpl.CompanyDetialBotTpl;
import com.midian.ppaddress.utils.AppUtil;

import java.util.ArrayList;

import midian.baselib.base.BaseMultiTypeListActivity;
import midian.baselib.bean.NetResult;
import midian.baselib.shizhefei.view.listviewhelper.IDataAdapter;
import midian.baselib.shizhefei.view.listviewhelper.IDataSource;
import midian.baselib.utils.UIHelper;
import midian.baselib.widget.BaseLibTopbarView;
import midian.baselib.widget.pulltorefresh.PullToRefreshListView;

/**
 * 服务机构详情
 */
public class CompanyDetialActivity extends BaseMultiTypeListActivity implements AbsListView.OnScrollListener {
    public String agencyid;
    public static BaseLibTopbarView topbar;
    private int position=0;
    private int top=0;//listview滑动的距离
    private View v=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        topbar = findView(R.id.topbar);
        topbar.setTitle("").setLeftImageButton(R.drawable.icon_back, UIHelper.finish(_activity));
        topbar.setBackgroundColor(Color.parseColor("#00ffffff"));
        topbar.getLine_iv().setVisibility(View.GONE);

        if(!ac.isUserIdExsit()){
            findViewById(R.id.ll_ask).setVisibility(View.GONE);
            topbar.hideRight_ib();
        }
        //对非普通用户隐藏咨询按钮和收藏按钮
        if(!"0".equals(ac.getProperty("user_type"))){
            findViewById(R.id.ll_ask).setVisibility(View.GONE);
        }
        if (!"0".equals(ac.getProperty("user_type"))) {
            topbar.hideRight_ib();
            Log.d("wqf","DETAIL");
        }
        findViewById(R.id.ll_ask).setOnClickListener(this);
        listView.setOnScrollListener(this);
        findView(R.id.right_ib).setOnClickListener(this);//收藏按钮监听事件
    }


    @Override
    public void onClick(View arg0) {
        super.onClick(arg0);
        switch (arg0.getId()){
            case R.id.ll_ask:
                String id = mBundle.getString("agencyid");
                Log.d("wqf",id);
                Intent intent=new Intent();
                intent.setClass(_activity,ChooseWaiterActivity.class);
                intent.putExtra("agencyId",id);
                startActivity(intent);
                break;
            case R.id.right_ib:
                if ("0".equals(CompanyDetialTopTpl.isCollect)) {
                    AppUtil.getPpApiClient(ac).memberFavoriteAgency(ac.user_id, CompanyDetialTopTpl.agencyId, this);
                }
                if ("1".equals(CompanyDetialTopTpl.isCollect)) {
                    AppUtil.getPpApiClient(ac).memberFavoriteCancelAgency(ac.user_id, CompanyDetialTopTpl.favoriteid, this);
                }
                break;
        }
    }

    @Override
    public void onApiSuccess(NetResult res, String tag) {
        super.onApiSuccess(res, tag);
        if (res.isOK()) {
            if ("memberFavoriteAgency".equals(tag)) {
                CompanyDetailBean.topBean.getData().getAgency().setFavorite("1");
                CompanyDetialTopTpl.isCollect = "1";
                UIHelper.t(_activity, "收藏成功");
                MemberFavoriteAgencyBean bean = (MemberFavoriteAgencyBean) res;
                CompanyDetailBean.topBean.getData().getAgency().setFavoriteid(bean.getData().getId());
                CompanyDetialTopTpl.favoriteid = bean.getData().getId();
                ac.setProperty(CompanyDetialTopTpl.agencyId, CompanyDetialTopTpl.favoriteid);
                topbar.setRightImageButton(R.drawable.icon_collect_yes);
            }
            if ("memberFavoriteCancelAgency".equals(tag)) {
                CompanyDetailBean.topBean.getData().getAgency().setFavorite("0");
                CompanyDetialTopTpl.isCollect = "0";
                UIHelper.t(_activity, "取消成功");
                if(position==0 && top>-282) {
                    topbar.setRightImageButton(R.drawable.icon_collect_no);
                }else{
                    topbar.setRightImageButton(R.drawable.icon_collect_no_black);
                }
            }
        } else {
            ac.handleErrorCode(_activity, res.message);
        }
    }


    @Override
    protected int getLayoutId() {
        return R.layout.activity_company_detail;
    }

    @Override
    protected IDataSource<ArrayList> getDataSource() {
        agencyid = mBundle.getString("agencyid");
        return new CompanyDeatilDataResource(_activity,agencyid);
    }
    @Override
    protected ArrayList<Class> getTemplateClasses() {
        ArrayList<Class> tpls = new ArrayList<>();
        tpls.add(CompanyDetialTopTpl.class);
        tpls.add(CompanyDetialBotTpl.class);
        return tpls;
    }

    @Override
    public void onScrollStateChanged(AbsListView absListView, int i) {
        //当屏幕停止滚动时为0；当屏幕滚动且用户使用的触碰或手指还在屏幕上时为1；
        //由于用户的操作，屏幕产生惯性滑动时为2
    }

    @Override
    public void onScroll(AbsListView absListView, int i, int i1, int i2) {
        v = listView.getChildAt(0);
        if(v == null){
//            ll=0;
        }
        try {
            position=listView.getFirstVisiblePosition();
            top=v.getTop();
//            ll=-top + position * v.getHeight();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(position==0 && top>-282){
//            topbar.setTitle("原来的LOGO");
            topbar.setTitle("").setLeftImageButton(R.drawable.icon_back_white, UIHelper.finish(_activity));
            topbar.setBackgroundColor(Color.parseColor("#00ffffff"));
            topbar.getLine_iv().setVisibility(View.GONE);
            if (!"0".equals(ac.getProperty("user_type"))) {
                topbar.hideRight_ib();
                Log.d("wqf","SCROLL");
            }else {
                if ("0".equals(CompanyDetialTopTpl.isCollect)) {
                    topbar.setRightImageButton(R.drawable.icon_collect_no);
                } else if ("1".equals(CompanyDetialTopTpl.isCollect)) {
                    topbar.setRightImageButton(R.drawable.icon_collect_yes);
                }
            }
        }else{
//            topbar.setTitle("改变的LOGO");
            topbar.setTitle(CompanyDetialTopTpl.name).setLeftImageButton(R.drawable.icon_back, UIHelper.finish(_activity));
            topbar.setBackgroundColor(Color.parseColor("#ffffff"));
            topbar.getLine_iv().setVisibility(View.VISIBLE);
            if (!"0".equals(ac.getProperty("user_type"))) {
                topbar.hideRight_ib();
                Log.d("wqf","SCROLL——NPO");
            }else {
                if ("0".equals(CompanyDetialTopTpl.isCollect)) {
                    topbar.setRightImageButton(R.drawable.icon_collect_no_black);
                } else if ("1".equals(CompanyDetialTopTpl.isCollect)) {
                    topbar.setRightImageButton(R.drawable.icon_collect_yes);
                }
            }
        }
//        Log.d("wqf","position::"+position+">>top::"+top);
    }
}
