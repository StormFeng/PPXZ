package com.midian.ppaddress.ui.personal;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;

import com.midian.ppaddress.R;
import com.midian.ppaddress.ui.personal.fragment.BookFragment_1;
import com.midian.ppaddress.ui.personal.fragment.BookFragment_2;

import java.util.ArrayList;
import java.util.List;

import midian.baselib.base.BaseFragmentActivity;
import midian.baselib.utils.UIHelper;
import midian.baselib.widget.BaseLibTopbarView;

/**
 * 我的预约
 * Created by chu on 2016/2/18.
 */
public class RecentlyBookedActivity extends BaseFragmentActivity implements View.OnClickListener{
    private BaseLibTopbarView topbar;
    private View currentButton;
    private BookFragment_1 tab1;
    private BookFragment_2 tab2;
    private ViewPager mViewPager;
    private List<Fragment> mFragmentList=new ArrayList<Fragment>();
    private Button btn_tab1,btn_tab2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_twobtn_viewpager);
        initView();
    }
    private void initView() {
        topbar= (BaseLibTopbarView) findViewById(R.id.topbar);
        topbar.setTitle("我的预约").setLeftImageButton(R.drawable.icon_back, UIHelper.finish(this));
        btn_tab1=findView(R.id.btn_1);
        btn_tab2=findView(R.id.btn_2);
        mViewPager=findView(R.id.viewpager);
        tab1=new BookFragment_1();
        tab2=new BookFragment_2();
        mFragmentList.add(tab1);
        mFragmentList.add(tab2);
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
