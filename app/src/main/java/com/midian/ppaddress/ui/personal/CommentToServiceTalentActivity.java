package com.midian.ppaddress.ui.personal;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;

import com.midian.ppaddress.R;
import com.midian.ppaddress.datasource.CommentToServieTalentDataResource;
import com.midian.ppaddress.itemtpl.CommentToServiceTalentTpl;
import com.midian.ppaddress.ui.personal.fragment.ToBodyCommentFragment;
import com.midian.ppaddress.ui.personal.fragment.ToMyCommentFragment;

import java.util.ArrayList;
import java.util.List;

import midian.baselib.base.BaseFragmentActivity;
import midian.baselib.base.BaseListActivity;
import midian.baselib.shizhefei.view.listviewhelper.IDataSource;
import midian.baselib.utils.UIHelper;
import midian.baselib.widget.BaseLibTopbarView;
import midian.baselib.widget.BaseLibViewPager;

/**
 * 服务达人评价
 * Created by chu on 2016/5/5.
 */
public class CommentToServiceTalentActivity extends BaseListActivity{
    private BaseLibTopbarView topbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        topbar = findView(R.id.topbar);
        topbar.setTitle("我的评价").setLeftImageButton(R.drawable.icon_back, UIHelper.finish(_activity));
    }

    @Override
    protected int getLayoutId() {
        return R.layout.comman_list_item;
    }

    @Override
    protected IDataSource<ArrayList> getDataSource() {
        return new CommentToServieTalentDataResource(_activity);
    }
    @Override
    protected Class getTemplateClass() {
        return CommentToServiceTalentTpl.class;
    }
}
