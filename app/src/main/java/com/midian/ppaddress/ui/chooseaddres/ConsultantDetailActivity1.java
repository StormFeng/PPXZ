package com.midian.ppaddress.ui.chooseaddres;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.midian.login.view.LoginActivity;
import com.midian.ppaddress.R;
import com.midian.ppaddress.bean.ConsultantDetailBean;
import com.midian.ppaddress.bean.MemberCommentsCounselorPageMeBean;
import com.midian.ppaddress.ui.company.OneKeyAppActivity;
import com.midian.ppaddress.utils.AppUtil;
import com.nostra13.universalimageloader.core.ImageLoader;

import midian.baselib.base.BaseFragmentActivity;
import midian.baselib.bean.NetResult;
import midian.baselib.utils.UIHelper;
import midian.baselib.widget.BaseLibTopbarView;
import midian.baselib.widget.CircleImageView;
import midian.baselib.widget.ScrollViewWidthListener;

/**
 * 纪经人详情
 * Created by chu on 2016/4/28.
 */
public class ConsultantDetailActivity1 extends BaseFragmentActivity implements View.OnClickListener {
    private BaseLibTopbarView topbar;
    private ScrollViewWidthListener mScrollViewWidthListener;
    private View entrust_ll, tab1_ll, tab2_ll;
    private ImageView bg_ll;
    private CircleImageView head_cv;
    private ImageView gender_iv;
    private TextView name_tv, type_tv, company_tv, comment_tv, sign_tv;
    private TextView f1_tv, f2_tv, f3_tv, f4_tv;
    public Fragment mFragment;
    private ConsultantAgentFragment agentFragment;
    private ToConsultantCommentFragment commentFragment;
    private Animation showAnim = null;
    private Animation hideAnim = null;
    private String counselorid;//物业顾问Id
    private String tag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultant_detail1);
        counselorid = mBundle.getString("counselorid");
        tag=mBundle.getString("tag");
        initView();
        loadData();
    }

    private void loadData() {
        AppUtil.getPpApiClient(ac).memberMemberscreenShowCounselor(counselorid, this);
        AppUtil.getPpApiClient(ac).getMemberCommentsCounselorPageMe(counselorid,"1",this);//15.2个人中心-我的评论-对我（物业顾问）的评论
    }

    private void initView() {
        topbar = findView(R.id.topbar);
        topbar.setTitle("").setLeftImageButton(R.drawable.icon_back, UIHelper.finish(_activity)).setMode(BaseLibTopbarView.MODE_2);
        bg_ll = findView(R.id.bg_ll);
        entrust_ll = findView(R.id.entrust_ll);
        if("PersonalFragment".equals(tag)){
            entrust_ll.setVisibility(View.GONE);
        }
        tab1_ll = findView(R.id.tab1_ll);
        tab2_ll = findView(R.id.tab2_ll);
        head_cv = findView(R.id.head_cv);
        name_tv = findView(R.id.name_tv);
        type_tv = findView(R.id.type_tv);
        gender_iv = findView(R.id.gender_iv);
        company_tv = findView(R.id.company_tv);
        comment_tv = findView(R.id.comment_tv);
        sign_tv = findView(R.id.sign_tv);
        f1_tv = findView(R.id.f1_tv);
        f2_tv = findView(R.id.f2_tv);
        f3_tv = findView(R.id.f3_tv);
        f4_tv = findView(R.id.f4_tv);
        f1_tv.setOnClickListener(this);
        f2_tv.setOnClickListener(this);
        f3_tv.setOnClickListener(this);
        f4_tv.setOnClickListener(this);
        f1_tv.setEnabled(false);
        f2_tv.setEnabled(true);
        f3_tv.setEnabled(false);
        f4_tv.setEnabled(true);
        entrust_ll.setOnClickListener(this);
        //加载动画
        showAnim = AnimationUtils.loadAnimation(_activity, R.anim.show_anim);
        hideAnim = AnimationUtils.loadAnimation(_activity, R.anim.hide_anim);
        mScrollViewWidthListener = findView(R.id.scroll);
        mScrollViewWidthListener.setOnChildViewVisibilityChangedListener(new ScrollViewWidthListener.onChildViewVisibilityChangedListener() {
            @Override
            public void onChildViewVisibilityChanged(int index, View v, boolean becameVisible) {
                if (becameVisible) {
                    if (index == 0) {
                        topbar.startAnimation(showAnim);
                        topbar.setMode(BaseLibTopbarView.MODE_HIDE_TOPBAR);
                        tab1_ll.setVisibility(View.GONE);
                    }
                } else {
                    if (index == 0) {
                        topbar.startAnimation(hideAnim);
                        topbar.setMode(BaseLibTopbarView.MODE_2);
                        tab1_ll.setVisibility(View.VISIBLE);
                    }
                }
            }
        });
        mFragment = new Fragment();
        agentFragment = new ConsultantAgentFragment();
        commentFragment = new ToConsultantCommentFragment();
        agentFragment.setCounselorid(counselorid);
        commentFragment.setCounselorid(counselorid);
        switchFragment(mFragment, agentFragment);
        f1_tv.isSelected();
        f1_tv.setTextColor(getResources().getColor(R.color.blue_tv));
        f3_tv.setTextColor(getResources().getColor(R.color.blue_tv));
        if (!"0".equals(ac.getProperty("user_type"))) {
            entrust_ll.setVisibility(View.GONE);
        }
    }

    public void switchFragment(Fragment from, Fragment to) {
        FragmentTransaction mTransaction = fm.beginTransaction();
        if (mFragment != to) {
            mFragment = to;
            if (to.isAdded()) {
                mTransaction.hide(from).show(to).commit();
            } else {
                mTransaction.hide(from).add(R.id.fl_content, to).commit();
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.f1_tv:
            case R.id.f3_tv:
                switchFragment(mFragment, agentFragment);
                f1_tv.setEnabled(false);
                f2_tv.setEnabled(true);
                f3_tv.setEnabled(false);
                f4_tv.setEnabled(true);
                f1_tv.setTextColor(getResources().getColor(R.color.blue_tv));
                f2_tv.setTextColor(getResources().getColor(R.color.text_bg90));
                f3_tv.setTextColor(getResources().getColor(R.color.blue_tv));
                f4_tv.setTextColor(getResources().getColor(R.color.text_bg90));
                break;
            case R.id.f2_tv:
            case R.id.f4_tv:
                switchFragment(mFragment, commentFragment);
                f1_tv.setEnabled(true);
                f2_tv.setEnabled(false);
                f3_tv.setEnabled(true);
                f4_tv.setEnabled(false);
                f1_tv.setTextColor(getResources().getColor(R.color.text_bg90));
                f2_tv.setTextColor(getResources().getColor(R.color.blue_tv));
                f3_tv.setTextColor(getResources().getColor(R.color.text_bg90));
                f4_tv.setTextColor(getResources().getColor(R.color.blue_tv));
                break;
            case R.id.entrust_ll://委托选址
                if (!ac.isUserIdExsit()) {
                    UIHelper.jump(_activity, LoginActivity.class);
                    return;
                }
                Bundle bundle = new Bundle();
                bundle.putString("flag", "ConsultantDetailActivity");
                bundle.putString("counselorid", counselorid);
                UIHelper.jump(_activity, OneKeyAppActivity.class, bundle);
                break;
        }

    }

    @Override
    public void onApiSuccess(NetResult res, String tag) {
        super.onApiSuccess(res, tag);
        if ("memberMemberscreenShowCounselor".equals(tag)) {
            if (res.isOK()) {
                ConsultantDetailBean bean = (ConsultantDetailBean) res;
                if (bean.getData().getAdverimage() == null && bean.getData().getAdverimage().equals("")) {
                    ac.setImage(bg_ll, R.drawable.icon_bg1);
                } else {
                    ac.setImage(bg_ll, bean.getData().getAdverimage());

                }
                if (bean.getData().getImages() == null && bean.getData().getImages().equals("")) {
                    ac.setImage(head_cv, R.drawable.default_bg);
                } else {
                    ac.setImage(head_cv, bean.getData().getImages());
                }
                if ("0".equals(bean.getData().getSex())) {
                    gender_iv.setImageResource(R.drawable.icon_women);
                } else {
                    gender_iv.setImageResource(R.drawable.icon_man);
                }
                name_tv.setText(bean.getData().getFullname());
                type_tv.setText("物业顾问");
                company_tv.setText(bean.getData().getAgencyCompany());
                comment_tv.setText("好评率 " + bean.getData().getGoodRate() + "%");
                sign_tv.setText(bean.getData().getDeclaration());
                counselorid = bean.getData().getMemberid();
            }
        }
        if("getMemberCommentsCounselorPageMe".equals(tag)){
            f2_tv.setText(" 对TA的评价(" + ((MemberCommentsCounselorPageMeBean) res).getData().getTotalRow() + ")");
            f4_tv.setText(" 对TA的评价(" + ((MemberCommentsCounselorPageMeBean) res).getData().getTotalRow() + ")");
        }
    }
}
