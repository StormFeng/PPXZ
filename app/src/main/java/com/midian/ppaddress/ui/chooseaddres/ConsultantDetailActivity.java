package com.midian.ppaddress.ui.chooseaddres;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.midian.ppaddress.R;
import com.midian.ppaddress.bean.ConsultantDetailBean;
import com.midian.ppaddress.ui.company.OneKeyAppActivity;
import com.midian.ppaddress.ui.personal.fragment.ToMyCommentFragment;
import com.midian.ppaddress.utils.AppUtil;

import java.util.ArrayList;
import java.util.List;

import midian.baselib.adapter.PagerTabAdapter;
import midian.baselib.base.BaseActivity;
import midian.baselib.base.BaseFragmentActivity;
import midian.baselib.bean.NetResult;
import midian.baselib.shizhefei.view.listviewhelper.IDataAdapter;
import midian.baselib.shizhefei.view.listviewhelper.IDataSource;
import midian.baselib.utils.UIHelper;
import midian.baselib.widget.BaseLibTopbarView;
import midian.baselib.widget.BaseLibViewPager;
import midian.baselib.widget.PagerSlidingTabStrip;
import midian.baselib.widget.ScrollViewWidthListener;

/**
 * 物业顾问详情【暂时未使用页面】
 * Created by chu on 2016/4/27.
 */
public class ConsultantDetailActivity extends BaseFragmentActivity implements View.OnClickListener {
    private List<Fragment> fragments = new ArrayList<Fragment>();
    private BaseLibTopbarView topbar;
    private ScrollViewWidthListener mScrollViewWidthListener;
    private ConsultantAgentFragment agentFragment;
    //private ToConsultantCommentFragment commentFragment;
    private ToMyCommentFragment myCommentFragment;//选址_写字楼详情_经纪人详情 对应 个人中心_我的评价_对我的评价
    private ImageView head_cv, gender_iv;
    private TextView name_tv, type_tv, company_tv, comment_tv, sign_tv;
    private Button btn_tab1, btn_tab2, btn_tab3, btn_tab4;
    private View tab1, tab2,hide_view;
    private ViewPager mViewPager;
    private String counselorid;//物业顾问Id

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultant_detail);
        initView();
        loadData();
    }

    private void loadData() {
        AppUtil.getPpApiClient(ac).memberMemberscreenShowCounselor(ac.user_id, this);
    }


    private void initView() {
        topbar = findView(R.id.topbar);
        topbar.setLeftImageButton(R.drawable.icon_back, UIHelper.finish(_activity)).setTitle("");
//        topbar.setBackgroundColor(getResources().getColor(R.color.alpha));
        topbar.setMode(BaseLibTopbarView.MODE_HIDE_TOPBAR);
        head_cv = findView(R.id.head_cv);
        gender_iv = findView(R.id.gender_iv);
        name_tv = findView(R.id.name_tv);
        type_tv = findView(R.id.type_tv);
        company_tv = findView(R.id.company_tv);
        comment_tv = findView(R.id.comment_tv);
        sign_tv = findView(R.id.sign_tv);
        tab1 = findView(R.id.tab1);
        tab2 = findView(R.id.tab2);
        hide_view = findView(R.id.hide_view);
        btn_tab1 = findView(R.id.btn_1);
        btn_tab2 = findView(R.id.btn_2);
        btn_tab3 = findView(R.id.btn_3);
        btn_tab4 = findView(R.id.btn_4);
        mViewPager = findView(R.id.viewpager);


        mScrollViewWidthListener = findView(R.id.scroll);
        mScrollViewWidthListener.setOnChildViewVisibilityChangedListener(new ScrollViewWidthListener.onChildViewVisibilityChangedListener() {
            @Override
            public void onChildViewVisibilityChanged(int index, View v, boolean becameVisible) {
                System.out.println("index::" + index + "---" + "v.getId()::" + v.getId() + "---" + "becameVisible::"
                        + becameVisible + "---" + R.id.hide_view);
                if (becameVisible) {
                    if (index == 2) {
                        topbar.setMode(BaseLibTopbarView.MODE_HIDE_TOPBAR);
                        tab1.setVisibility(View.INVISIBLE);
                    }
                } else {
                    if (index == 2) {
                        topbar.setMode(BaseLibTopbarView.MODE_1);
                        ObjectAnimator.ofFloat(topbar.getTitle_tv(), "alpha", 0, 1).setDuration(1000).start();
                        tab1.setVisibility(View.VISIBLE);
                    }
                }
            }
        });
        agentFragment = new ConsultantAgentFragment();
//        myCommentFragment = new ToMyCommentFragment();
        fragments.add(agentFragment);
//        fragments.add(myCommentFragment);
        FragmentAdapter fragmentAdapter = new FragmentAdapter(getSupportFragmentManager(), fragments);
        mViewPager.setAdapter(fragmentAdapter);
        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        setButton(R.id.btn_1);
                        setButton(R.id.btn_3);
                        break;
                    case 1:
                        setButton(R.id.btn_2);
                        setButton(R.id.btn_4);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
        btn_tab1.setOnClickListener(this);
        btn_tab2.setOnClickListener(this);
        btn_tab3.setOnClickListener(this);
        btn_tab4.setOnClickListener(this);
        findViewById(R.id.btn_chooseAdr).setOnClickListener(this);
        btn_tab1.performClick();
//        btn_tab1.isSelected();
    }

    class FragmentAdapter extends FragmentPagerAdapter {
        private List<Fragment> fragments;

        public FragmentAdapter(FragmentManager fm, List<Fragment> fragments) {
            super(fm);
            this.fragments = fragments;
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_1:
            case R.id.btn_3:
                setButton(R.id.btn_1);
                setButton(R.id.btn_3);
                mViewPager.setCurrentItem(0);
                break;
            case R.id.btn_2:
            case R.id.btn_4:
                setButton(R.id.btn_2);
                setButton(R.id.btn_4);
                mViewPager.setCurrentItem(1);
                break;
            case R.id.btn_chooseAdr:
                Bundle bundle = new Bundle();
                bundle.putString("flag", "ConsultantDetailActivity");
                bundle.putString("counselorid", counselorid);
                UIHelper.jump(_activity, OneKeyAppActivity.class, bundle);
                break;
        }
    }

    private void setButton(int id) {
        btn_tab1.setTextColor(getResources().getColor(R.color.text_bg90));
        btn_tab2.setTextColor(getResources().getColor(R.color.text_bg90));
        btn_tab1.setClickable(true);
        btn_tab2.setClickable(true);
        Button btn = (Button) findViewById(id);
        btn.setTextColor(getResources().getColor(R.color.blue));
        btn.setClickable(false);
    }



    @Override
    public void onApiSuccess(NetResult res, String tag) {
        super.onApiSuccess(res, tag);
        if ("memberMemberscreenShowCounselor".equals(tag)) {
            ConsultantDetailBean bean = (ConsultantDetailBean) res;
            ac.setImage(head_cv, bean.getData().getImages());
            if ("0".equals(bean.getData().getSex())) {
                gender_iv.setImageResource(R.drawable.icon_women);
            } else {
                gender_iv.setImageResource(R.drawable.icon_man);
            }
            name_tv.setText(bean.getData().getFullname());
            company_tv.setText(bean.getData().getAgencyCompany());
            comment_tv.setText("好评率 " + bean.getData().getGoodRate() + "%");
            sign_tv.setText(bean.getData().getDeclaration());
            counselorid = bean.getData().getMemberid();
        }
    }

}
