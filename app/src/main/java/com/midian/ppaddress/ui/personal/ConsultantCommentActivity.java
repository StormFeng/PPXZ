package com.midian.ppaddress.ui.personal;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;

import com.midian.ppaddress.R;
import com.midian.ppaddress.ui.personal.fragment.MyConsultantCommentFragment;
import com.midian.ppaddress.ui.personal.fragment.ServiceGotTalentFragment;
import com.midian.ppaddress.ui.personal.fragment.ToBodyCommentFragment;
import com.midian.ppaddress.ui.personal.fragment.ToMyCommentFragment;

import java.util.ArrayList;
import java.util.List;

import midian.baselib.base.BaseFragmentActivity;
import midian.baselib.utils.UIHelper;
import midian.baselib.widget.BaseLibTopbarView;
import midian.baselib.widget.BaseLibViewPager;

/**
 * 经纪人评价
 * Created by chu on 2016/5/5.
 */
public class ConsultantCommentActivity extends BaseFragmentActivity implements View.OnClickListener  {
    private BaseLibTopbarView topbar;
    private View currentButton;
    private Button tab1, tab2;
    private ToMyCommentFragment myCommentFragment;//对我的评价
    private ToBodyCommentFragment bodyCommentFragment;//对载体的评价
    private BaseLibViewPager pager;
    private ArrayList<Fragment> fragments = new ArrayList<Fragment>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultant_comment);

        topbar = findView(R.id.topbar);
        topbar.setTitle("我的评价").setLeftImageButton(R.drawable.icon_back, UIHelper.finish(_activity));
        topbar.getLine_iv().setVisibility(View.GONE);
        pager = findView(R.id.pager);
        myCommentFragment = new ToMyCommentFragment();
        bodyCommentFragment = new ToBodyCommentFragment();
        tab1 = (Button) findViewById(R.id.btn_1);
        tab2 = (Button) findViewById(R.id.btn_2);
        fragments.add(myCommentFragment);
        fragments.add(bodyCommentFragment);
        FragmentAdapter adapter = new FragmentAdapter(getSupportFragmentManager(),fragments);
        pager.setAdapter(adapter);
        pager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position){
                    case 0:
                        setButton(tab1);
                        tab1.setTextColor(getResources().getColor(R.color.white));
                        tab2.setTextColor(getResources().getColor(R.color.text_bg90));
                        break;
                    case 1:
                        setButton(tab2);
                        tab2.setTextColor(getResources().getColor(R.color.white));
                        tab1.setTextColor(getResources().getColor(R.color.text_bg90));
                        break;
                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        tab1.setOnClickListener(this);
        tab2.setOnClickListener(this);
        tab1.performClick();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_1:
                pager.setCurrentItem(0);
                setButton(view);
                tab1.setTextColor(getResources().getColor(R.color.white));
                tab2.setTextColor(getResources().getColor(R.color.text_bg90));
                break;
            case R.id.btn_2:
                pager.setCurrentItem(1);
                setButton(view);
                tab2.setTextColor(getResources().getColor(R.color.white));
                tab1.setTextColor(getResources().getColor(R.color.text_bg90));
                break;
        }
    }

    private void setButton(View arg0) {
        if (currentButton != null && currentButton.getId() != arg0.getId())
            currentButton.setEnabled(true);
        arg0.setEnabled(false);
        currentButton = arg0;
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
}
