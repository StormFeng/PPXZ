package com.midian.ppaddress.itemtpl;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import com.midian.ppaddress.R;
import com.midian.ppaddress.bean.DetailMultiItemBean;
import com.midian.ppaddress.ui.chooseaddres.MoreCommentActivity;
import com.midian.ppaddress.ui.chooseaddres.detailfragment.DetailCommentFragment;
import com.midian.ppaddress.ui.chooseaddres.detailfragment.expertsCommentFragment;
import com.midian.ppaddress.ui.chooseaddres.detailfragment.DetailWuyeCommentFragment;

import java.util.ArrayList;

import midian.baselib.adapter.PagerTabAdapter;
import midian.baselib.base.BaseFragmentActivity;
import midian.baselib.utils.UIHelper;
import midian.baselib.view.BaseTpl;
import midian.baselib.widget.BaseLibViewPager;
import midian.baselib.widget.PagerSlidingTabStrip;

/**
 * 详情-评论条目的三个fragment
 * Created by chu on 2016/4/25.
 */
public class CommentTpl extends BaseTpl<DetailMultiItemBean> implements ViewPager.OnPageChangeListener, View.OnClickListener {
    private PagerSlidingTabStrip comment_tabs;
    private BaseLibViewPager comment_pager;
    private PagerTabAdapter adapter;
    private ArrayList<Fragment> fragments;
    private ArrayList<String> titles;
    private DetailCommentFragment commentFragment;
    private DetailWuyeCommentFragment wuyeCommentFragment;
    private expertsCommentFragment expertsCommentFragment;
    private LinearLayout more_ll;
    private DetailMultiItemBean bean;



    public CommentTpl(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CommentTpl(Context context) {
        super(context);
    }

    @Override
    protected void initView() {
        comment_tabs = (PagerSlidingTabStrip) findView(R.id.comment_tabs);
        comment_pager = (BaseLibViewPager) findView(R.id.comment_pager);
        more_ll = findView(R.id.more_ll);
        commentFragment = new DetailCommentFragment();//用户点评fragment
        wuyeCommentFragment = new DetailWuyeCommentFragment();
        expertsCommentFragment = new expertsCommentFragment();
//        adviserFragment = new MoreWuYeCommentFragment();//物业顾问点评fragment
//        expertFragment = new MoreExpertCommentFragment();//专家点评fragment

        fragments = new ArrayList<Fragment>();
        titles = new ArrayList<String>();
        fragments.add(commentFragment);
        fragments.add(wuyeCommentFragment);
        fragments.add(expertsCommentFragment);
        titles.add("用户点评");
        titles.add("物业顾问点评");
        titles.add("专家点评");
        fm = ((BaseFragmentActivity) _activity).getSupportFragmentManager();
        adapter = new PagerTabAdapter(fm, titles, fragments);
        comment_pager.setAdapter(adapter);
        comment_tabs.setViewPager(comment_pager);
        comment_tabs.setOnPageChangeListener(this);
        comment_pager.setCurrentItem(0);

        more_ll.setOnClickListener(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.item_detail_comment_tpl;
    }

    @Override
    public void setBean(DetailMultiItemBean bean, int position) {
        this.bean = bean;
        if (bean.getItemViewType() == 2) {
            commentFragment.setCarrierid(bean.detailData.getCarrierid());
            wuyeCommentFragment.setCarrierid(bean.detailData.getCarrierid());
            expertsCommentFragment.setCarrierid(bean.detailData.getCarrierid());

        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.more_ll://物业顾问更多评论
                Bundle mBundle = new Bundle();
                mBundle.putString("carrierid", bean.detailData.getCarrierid());
                mBundle.putString("carriername", bean.detailData.getCarrierName());
                UIHelper.jump(_activity, MoreCommentActivity.class,mBundle);
                break;
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
