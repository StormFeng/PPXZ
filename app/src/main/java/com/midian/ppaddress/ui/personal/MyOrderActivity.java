package com.midian.ppaddress.ui.personal;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;

import com.midian.ppaddress.R;
import com.midian.ppaddress.ui.personal.fragment.MyOrderFragment_1;
import com.midian.ppaddress.ui.personal.fragment.MyOrderFragment_2;

import java.util.ArrayList;
import java.util.List;

import midian.baselib.base.BaseActivity;
import midian.baselib.base.BaseFragmentActivity;
import midian.baselib.utils.UIHelper;
import midian.baselib.widget.BaseLibTopbarView;

/**
 * 我的订单
 */
public class MyOrderActivity extends BaseFragmentActivity implements View.OnClickListener{
    private BaseLibTopbarView topbar;
    private Button btn_tab1,btn_tab2;
    private ViewPager mViewPager;
    private View currentButton;
    private MyOrderFragment_1 myOrderFragment_1;
    private MyOrderFragment_2 myOrderFragment_2;
    private List<Fragment> mFragmentList=new ArrayList<Fragment>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_twobtn_viewpager);
        initView();
    }

    private void initView() {
        btn_tab1=findView(R.id.btn_1);
        btn_tab2=findView(R.id.btn_2);
        btn_tab1.setText("已签约");
        btn_tab2.setText("已完成");
        topbar = findView(R.id.topbar);
        topbar.setTitle("我的订单").setLeftImageButton(R.drawable.icon_back, UIHelper.finish(this));
        topbar.getLine_iv().setVisibility(View.GONE);
        mViewPager=findView(R.id.viewpager);
        myOrderFragment_1=new MyOrderFragment_1();
        myOrderFragment_2=new MyOrderFragment_2();
        mFragmentList.add(myOrderFragment_1);
        mFragmentList.add(myOrderFragment_2);
        FragmentAdapter fragmentAdapter=new FragmentAdapter(getSupportFragmentManager(),mFragmentList);
        mViewPager.setAdapter(fragmentAdapter);
        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position){
                    case 0:
                        setButton(btn_tab1);
                        btn_tab1.setTextColor(getResources().getColor(R.color.white));
                        btn_tab2.setTextColor(getResources().getColor(R.color.text_bg90));
                        break;
                    case 1:
                        setButton(btn_tab2);
                        btn_tab2.setTextColor(getResources().getColor(R.color.white));
                        btn_tab1.setTextColor(getResources().getColor(R.color.text_bg90));
                        break;
                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        btn_tab1.setOnClickListener(this);
        btn_tab2.setOnClickListener(this);
        btn_tab1.performClick();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_1:
                mViewPager.setCurrentItem(0);
                setButton(view);
                btn_tab1.setTextColor(getResources().getColor(R.color.white));
                btn_tab2.setTextColor(getResources().getColor(R.color.text_bg90));
                break;
            case R.id.btn_2:
                mViewPager.setCurrentItem(1);
                setButton(view);
                btn_tab2.setTextColor(getResources().getColor(R.color.white));
                btn_tab1.setTextColor(getResources().getColor(R.color.text_bg90));
                break;
        }
    }

    class FragmentAdapter extends FragmentPagerAdapter {
        List<Fragment> fragmentList=new ArrayList<Fragment>();
        public FragmentAdapter(FragmentManager fm, List<Fragment> fragmentList) {
            super(fm);
            this.fragmentList=fragmentList;
        }
        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }
        @Override
        public int getCount() {
            return fragmentList.size();
        }
    }

    private void setButton(View arg0) {
        if(currentButton!=null&&currentButton.getId()!=arg0.getId())
            currentButton.setEnabled(true);
        arg0.setEnabled(false);
        currentButton=arg0;
    }
}
