package com.midian.ppaddress.ui.personal;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.midian.ppaddress.R;
import com.midian.ppaddress.ui.personal.fragment.CollectionFragment_1;
import com.midian.ppaddress.ui.personal.fragment.CollectionFragment_2;

import java.util.ArrayList;
import java.util.List;

import midian.baselib.base.BaseFragmentActivity;
import midian.baselib.utils.UIHelper;
import midian.baselib.widget.BaseLibTopbarView;

/**
 * 我的收藏
 */
public class MyCollectionActivity extends BaseFragmentActivity implements OnClickListener, OnPageChangeListener {

    private BaseLibTopbarView topbar;
    private View cancelArea;
    private TextView deleteFavor_tv;
    private Button btn_tab1, btn_tab2;
    private ViewPager pager;
    private ArrayList<Fragment> fragments = new ArrayList<Fragment>();
    private CollectionFragment_1 f1;
    private CollectionFragment_2 f2;
    private CheckBox allCheck;
    private boolean isFrist = true;
    private View currentButton;
    private TextView tv_edit;


    private OnClickListener editClickListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            showEdit();
            if (pager.getCurrentItem() == 0) {
                f1.isEditMode(true);
                f2.isEditMode(false);
                cancelArea.setVisibility(View.VISIBLE);
            } else if (pager.getCurrentItem() == 1) {
                f1.isEditMode(false);
                f2.isEditMode(true);
                cancelArea.setVisibility(View.VISIBLE);
            }
        }
    };

    private OnClickListener cancelClickListener = new OnClickListener() {

        @Override
        public void onClick(View v) {
            showNormal();
            if (pager.getCurrentItem() == 0) {
                f1.isEditMode(false);
                f1.allSelect(false);
                cancelArea.setVisibility(View.GONE);
            } else if (pager.getCurrentItem() == 1) {
                f2.isEditMode(false);
                f2.allSelect(false);
                cancelArea.setVisibility(View.GONE);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mycollection);

        initView();
    }

    private void initView() {

        topbar = findView(R.id.topbar);
        topbar.getLine_iv().setVisibility(View.GONE);
        cancelArea = findView(R.id.cancelArea);// 底部选择删除区域
        allCheck = findView(R.id.allCheck);// 全选按钮
        deleteFavor_tv = findView(R.id.delete_favor);// 删除按钮
        btn_tab1 = findView(R.id.btn_1);
        btn_tab2 = findView(R.id.btn_2);
        tv_edit=findView(R.id.tv_edit);
        allCheck.setOnClickListener(this);
        cancelArea.setOnClickListener(this);
        deleteFavor_tv.setOnClickListener(this);
        pager = findView(R.id.pager);
        f1 = new CollectionFragment_1();// 载体
        f2 = new CollectionFragment_2();// 服务机构
        fragments.add(f1);
        fragments.add(f2);
        FragmentAdapter fragmentAdapter = new FragmentAdapter(getSupportFragmentManager(), fragments);
        pager.setAdapter(fragmentAdapter);
        pager.setOnPageChangeListener(this);

        btn_tab1.setOnClickListener(this);
        btn_tab2.setOnClickListener(this);
        btn_tab1.performClick();
        showNormal();
    }

    public void showNormal() {
        topbar.setTitle("我的收藏").setLeftImageButton(R.drawable.icon_back, null)
                .setLeftText("返回", UIHelper.finish(_activity));//.setRightText("编辑", editClickListener)
        tv_edit.setText("编辑");
        tv_edit.setOnClickListener(editClickListener);
        cancelArea.setVisibility(View.GONE);

    }

    private void showEdit() {
        topbar.setTitle("我的收藏");//.setRightText("完成", cancelClickListener)
        tv_edit.setText("完成");
        tv_edit.setOnClickListener(cancelClickListener);
        cancelArea.setVisibility(View.VISIBLE);
        allCheck.setChecked(false);
    }

    private String record_type = "1";// 1是载体收藏，2是服务机构收藏

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_1:
                pager.setCurrentItem(0);
                setButton(v);
                btn_tab1.setTextColor(getResources().getColor(R.color.white));
                btn_tab2.setTextColor(getResources().getColor(R.color.text_bg90));
                try {
                    if(CollectionFragment_1.carrierCollectList.size()<=0){
                        tv_edit.setVisibility(View.GONE);
                    }else{
                        tv_edit.setVisibility(View.VISIBLE);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case R.id.btn_2:
                pager.setCurrentItem(1);
                setButton(v);
                btn_tab2.setTextColor(getResources().getColor(R.color.white));
                btn_tab1.setTextColor(getResources().getColor(R.color.text_bg90));
                if(CollectionFragment_2.agencyList.size()<=0){
                    tv_edit.setVisibility(View.GONE);
                }else{
                    tv_edit.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.allCheck:// 全选
                if (isFrist) {
                    f1.allSelect(true);
                    f2.allSelect(true);
                    isFrist = false;
                } else if (!isFrist) {
                    f1.allSelect(false);
                    f2.allSelect(false);
                    allCheck.setChecked(false);
                    isFrist = true;
                }
                break;
            case R.id.delete_favor:// 删除收藏
                System.out.println("record_type:" + record_type);
                if (record_type == "1") {
                    f1.getDataDoctor(true, "1");
                }
                if (record_type == "2") {
                    f2.getDataNews(true, "2");
                }
                break;
        }
    }

    private void setButton(View arg0) {
        if (currentButton != null && currentButton.getId() != arg0.getId())
            currentButton.setEnabled(true);
        arg0.setEnabled(false);
        currentButton = arg0;
    }

    @Override
    public void onPageScrollStateChanged(int arg0) {
    }

    @Override
    public void onPageScrolled(int arg0, float arg1, int arg2) {
        f1.isEditMode(false);
        f1.allSelect(false);
        f2.isEditMode(false);
        f2.allSelect(false);
        showNormal();
    }

    @Override
    public void onPageSelected(int position) {
        switch (position) {
            case 0:
                record_type = "1";
                setButton(btn_tab1);
                btn_tab1.setTextColor(getResources().getColor(R.color.white));
                btn_tab2.setTextColor(getResources().getColor(R.color.text_bg90));
                try {
                    if(CollectionFragment_1.carrierCollectList.size()<=0){
                        tv_edit.setVisibility(View.GONE);
                    }else{
                        tv_edit.setVisibility(View.VISIBLE);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case 1:
                record_type = "2";
                setButton(btn_tab2);
                btn_tab2.setTextColor(getResources().getColor(R.color.white));
                btn_tab1.setTextColor(getResources().getColor(R.color.text_bg90));
                if(CollectionFragment_2.agencyList.size()<=0){
                    tv_edit.setVisibility(View.GONE);
                }else{
                    tv_edit.setVisibility(View.VISIBLE);
                }
                break;
        }
    }

    public void normal() {
        showNormal();
    }

    private void showEmptyTitle() {
        topbar.setTitle("我的收藏").setLeftImageButton(R.drawable.icon_back, null).setLeftText("返回",
                UIHelper.finish(_activity));
//        topbar.hideRight_tv();
        tv_edit.setVisibility(View.GONE);
        cancelArea.setVisibility(View.GONE);
    }

    public void showEmpty() {
        showEmptyTitle();
    }

    class FragmentAdapter extends FragmentPagerAdapter {

        List<Fragment> fragmentList = new ArrayList<Fragment>();

        public FragmentAdapter(FragmentManager fm, List<Fragment> fragmentList) {
            super(fm);
            this.fragmentList = fragmentList;
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
