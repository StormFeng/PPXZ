package com.midian.ppaddress.ui.chooseaddres;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import com.midian.ppaddress.R;
import com.midian.ppaddress.ui.chooseaddres.detailfragment.MoreWuYeCommentFragment;
import com.midian.ppaddress.ui.chooseaddres.detailfragment.MoreCommentFragment;
import com.midian.ppaddress.ui.chooseaddres.detailfragment.MoreExpertCommentFragment;
import com.midian.ppaddress.utils.AppUtil;

import java.util.ArrayList;

import midian.baselib.adapter.PagerTabAdapter;
import midian.baselib.base.BaseFragmentActivity;
import midian.baselib.bean.NetResult;
import midian.baselib.shizhefei.view.listviewhelper.ListViewHelper;
import midian.baselib.utils.UIHelper;
import midian.baselib.widget.BaseLibTopbarView;
import midian.baselib.widget.BaseLibViewPager;
import midian.baselib.widget.PagerSlidingTabStrip;

/**
 * 园区详情--(用户/物业顾问/专家)更多点评
 * Created by chu on 2016/4/20.
 */
public class MoreCommentActivity extends BaseFragmentActivity {
    private BaseLibTopbarView topbar;
    private PagerSlidingTabStrip tabs;
    private BaseLibViewPager pager;
    private PagerTabAdapter adapter;
    private ArrayList<Fragment> fragments = new ArrayList<Fragment>();
    private ArrayList<String> titles = new ArrayList<String>();
    private String carrierid, carriername;


    private MoreCommentFragment moreCommentFragment;
    private MoreWuYeCommentFragment moreAdviserFragment;
    private MoreExpertCommentFragment moreExpertFragment;
    private int position;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_detail_comment);
        position = mBundle.getInt("position");
        carrierid = mBundle.getString("carrierid");
        carriername = mBundle.getString("carriername");
        topbar = findView(R.id.topbar);
        topbar.setTitle(carriername).setLeftText(" ",null).setLeftImageButton(R.drawable.icon_back, UIHelper.finish(_activity));
        tabs = findView(R.id.tabs);
        pager = findView(R.id.pager);
        tabs.setShowRigthIcon(false);
        moreCommentFragment = new MoreCommentFragment();//用户点评fragment
        moreAdviserFragment = new MoreWuYeCommentFragment();//物业顾问点评fragment
        moreExpertFragment = new MoreExpertCommentFragment();//专家点评fragment

        fragments.add(moreCommentFragment);
        fragments.add(moreAdviserFragment);
        fragments.add(moreExpertFragment);
        titles.add("用户点评");
        titles.add("物业顾问点评");
        titles.add("专家点评");
        adapter = new PagerTabAdapter(fm, titles, fragments);
        pager.setAdapter(adapter);
        tabs.setViewPager(pager);
        pager.setCurrentItem(position);


        moreCommentFragment.setCarrierid(carrierid);
        moreAdviserFragment.setCarrierid(carrierid);
        moreExpertFragment.setCarrierid(carrierid);

    }

   /* *//**
     * 监听Viewpager的滑动隐藏软键盘
     * @param view
     * @param motionEvent
     * @return
     *//*
    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        return imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
    }*/
}
