package com.midian.ppaddress.ui.main;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.midian.baidupush.MyPushMessageReceiver;
import com.midian.baidupush.PushMessage;
import com.midian.login.view.LoginActivity;
import com.midian.ppaddress.R;
import com.midian.ppaddress.bean.MemberMembershipInfoShowBean;
import com.midian.ppaddress.bean.MessageCountInnerMessageBean;
import com.midian.ppaddress.bean.PersonalIndexBean;
import com.midian.ppaddress.ui.chooseaddres.ConsultantDetailActivity1;
import com.midian.ppaddress.ui.personal.AskHistoryActivity;
import com.midian.ppaddress.ui.personal.BookedActivity;
import com.midian.ppaddress.ui.personal.BookingActivity;
import com.midian.ppaddress.ui.personal.BookingActivity_Counselor;
import com.midian.ppaddress.ui.personal.CommentToServiceTalentActivity;
import com.midian.ppaddress.ui.personal.ConsultantCommentActivity;
import com.midian.ppaddress.ui.personal.MyBuildingResourceActivity;
import com.midian.ppaddress.ui.personal.MyCollectionActivity;
import com.midian.ppaddress.ui.personal.MyCommentActivity;
import com.midian.ppaddress.ui.personal.MyContrastActivity;
import com.midian.ppaddress.ui.personal.MyOrderActivity;
import com.midian.ppaddress.ui.personal.MyPropertyActivity;
import com.midian.ppaddress.ui.personal.MyRecommendActivity;
import com.midian.ppaddress.ui.personal.MyRewardActivity;
import com.midian.ppaddress.ui.personal.NewsActivity;
import com.midian.ppaddress.ui.personal.PersonInfoActivity;
import com.midian.ppaddress.ui.personal.SettingActivity;
import com.midian.ppaddress.utils.AppUtil;

import midian.baselib.api.ApiCallback;
import midian.baselib.base.BaseFragment;
import midian.baselib.bean.NetResult;
import midian.baselib.utils.FDDataUtils;
import midian.baselib.utils.UIHelper;
import midian.baselib.widget.BaseLibTopbarView;
import midian.baselib.widget.CircleImageView;
import midian.baselib.widget.pulltorefresh.PullToRefreshBase;
import midian.baselib.widget.pulltorefresh.PullToRefreshScrollView;

/**
 * 个人中心
 * 已完成的身份普通用户、服务达人、客商中介、业主、
 * Created by chu on 2016/2/15.
 */
public class PersonalFragment extends BaseFragment implements View.OnClickListener, ApiCallback {
    private BaseLibTopbarView topbar;
    private CircleImageView head_cv;
    private TextView name_tv, phone_tv;
    private View personal_ll, booking_tv, sign_tv, message_tv, collect_tv, myComment_tv, contrast_tv, setting_tv,
            recommend_rl, consulting_tv, property_tv, my_order_tv, disk_source_tv, tv_personalpage, gratuity_tv;
    private TextView type_tv, booking_num_tv, hasMoney_tv, waitMoney_tv, tv_p;
    private TextView tv_book_count, tv_news_count, tv_ask_count, tv_notice;
    private View title_ll, money_ll, booking_top;
    private View ll_book, v_book;
    private String type = "1";
    private int flag = 0;//flag=1时为普通客商
    private int roletype;
    private PullToRefreshScrollView refreshScrollView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_personal, null);
        initView(v);
        return v;
    }

    private void initView(View v) {
        View view = LayoutInflater.from(_activity).inflate(R.layout.item_personal_view, null);
        topbar = (BaseLibTopbarView) v.findViewById(R.id.topbar);
        topbar.setTitle("个人中心");
        topbar.setTitleImage(R.drawable.icon_switch_account);
        title_ll = topbar.getTitleLl();

        head_cv = (CircleImageView) view.findViewById(R.id.head_cv);
        name_tv = (TextView) view.findViewById(R.id.name_tv);
        phone_tv = (TextView) view.findViewById(R.id.phone_tv);
        tv_p = (TextView) view.findViewById(R.id.tv_p);
        personal_ll = view.findViewById(R.id.personal_ll);//头像view
        booking_top = view.findViewById(R.id.booking_top);
        type_tv = (TextView) view.findViewById(R.id.type_tv);//身份标识文字(客商中介/服务达人等等)
        money_ll = view.findViewById(R.id.money_ll);//赏金view
        booking_num_tv = (TextView) view.findViewById(R.id.booking_num_tv);//预约数
        hasMoney_tv = (TextView) view.findViewById(R.id.hasMoney_tv);//已获得赏金
        waitMoney_tv = (TextView) view.findViewById(R.id.waitMoney_tv);//未获得赏金
        tv_book_count = (TextView) view.findViewById(R.id.tv_book_count);//预约消息数
        tv_news_count = (TextView) view.findViewById(R.id.tv_news_count);//系统消息数
        tv_ask_count = (TextView) view.findViewById(R.id.tv_ask_count);//咨询数量
        tv_notice = (TextView) view.findViewById(R.id.tv_notice);//推荐数量

        property_tv = view.findViewById(R.id.property_tv);//我的物业
        my_order_tv = view.findViewById(R.id.my_order_tv);//我的订单
        disk_source_tv = view.findViewById(R.id.disk_source_tv);//盘源
        tv_personalpage = view.findViewById(R.id.tv_personalpage);//我的主页
        ll_book = view.findViewById(R.id.ll_book);
        v_book = view.findViewById(R.id.v_book);

        consulting_tv = view.findViewById(R.id.rl_consulting);//咨询记录
        recommend_rl = view.findViewById(R.id.recommend_rl);//我的推荐
        booking_tv = view.findViewById(R.id.booking);//我的预约
        sign_tv = view.findViewById(R.id.sign);//已签约
        gratuity_tv = view.findViewById(R.id.gratuity_tv);//我的打赏
        message_tv = view.findViewById(R.id.message);//消息
        collect_tv = view.findViewById(R.id.collect);//我的收藏
        myComment_tv = view.findViewById(R.id.comment);//我的评价
        contrast_tv = view.findViewById(R.id.contrast);//我的对比
        setting_tv = view.findViewById(R.id.setting);//设置


        personal_ll.setOnClickListener(this);
        property_tv.setOnClickListener(this);
        my_order_tv.setOnClickListener(this);
        disk_source_tv.setOnClickListener(this);
        tv_personalpage.setOnClickListener(this);
        consulting_tv.setOnClickListener(this);
        recommend_rl.setOnClickListener(this);
        booking_tv.setOnClickListener(this);
        sign_tv.setOnClickListener(this);
        gratuity_tv.setOnClickListener(this);
        message_tv.setOnClickListener(this);
        collect_tv.setOnClickListener(this);
        myComment_tv.setOnClickListener(this);
        contrast_tv.setOnClickListener(this);
        setting_tv.setOnClickListener(this);
        title_ll.setOnClickListener(this);
        if (ac.isUserIdExsit()) {
            ac.setProperty("user_type", getUserType(_activity));
            if ("0".equals(getUserType(_activity))) {
                switchAverageUser();
            } else if ("1".equals(getUserType(_activity))) {
                switchMerchants();
                topbar.setTitle("切换为普通用户");
            } else if ("2".equals(getUserType(_activity))) {
                switchAgent();
                topbar.setTitle("切换为普通用户");
            } else if ("3".equals(getUserType(_activity))) {
                switchOwner();
                Log.d("wqf", "业主身份");
                topbar.setTitle("切换为普通用户");
            } else if ("4".equals(getUserType(_activity))) {
                switchService();
                topbar.setTitle("切换为普通用户");
            }
            AppUtil.getPpApiClient(ac).memberMembershipInfoInfoscreen(ac.user_id, getUserType(_activity), this);
        } else {
            initPersonInfo(ac.getProperty("user_type"));
        }

        refreshScrollView = (PullToRefreshScrollView) v.findViewById(R.id.refreshScrollView);
        refreshScrollView.scrollView.addView(view);

        refreshScrollView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ScrollView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ScrollView> refreshView) {
                loadUserInfo();// 下拉刷新 更新头像
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ScrollView> refreshView) {

            }
        });

        MyPushMessageReceiver.addPushListener(mPushListener);//在此页监听手机状态栏消息推送
    }

    MyPushMessageReceiver.PushListener mPushListener=new MyPushMessageReceiver.PushListener() {
        @Override
        public void updateContent(PushMessage msg) {
            //有消息推送时，可以处理一些消息相关状态等
            //...
            AppUtil.getPpApiClient(ac).memberMembershipInfoInfoscreen(ac.user_id, getUserType(_activity), new ApiCallback() {
                @Override
                public void onApiStart(String tag) {

                }

                @Override
                public void onApiLoading(long count, long current, String tag) {

                }

                @Override
                public void onApiSuccess(NetResult res, String tag) {
                    PersonalIndexBean bean = (PersonalIndexBean) res;
                    Log.d("wqf",bean.getData().getMyMsgs());
                    if (!"0".equals(bean.getData().getMyMsgs()))
                        tv_news_count.setVisibility(View.VISIBLE);
                    else{
                        tv_news_count.setVisibility(View.GONE);
                    }
                    if (!"0".equals(bean.getData().getMyOrders()))
                        tv_book_count.setVisibility(View.VISIBLE);
                    else{
                        tv_book_count.setVisibility(View.GONE);
                    }
                    if (!"0".equals(bean.getData().getConsults()))
                        tv_ask_count.setVisibility(View.VISIBLE);
                    else{
                        tv_ask_count.setVisibility(View.GONE);
                    }
                    if (!"0".equals(bean.getData().getMyRecoms()))
                        tv_notice.setVisibility(View.VISIBLE);
                    else{
                        tv_notice.setVisibility(View.GONE);
                    }
                    if("0".equals(ac.getProperty("user_type")) || "2".equals(ac.getProperty("user_type"))){
                        if(!"0".equals(bean.getData().getMyMsgs()) || !"0".equals(bean.getData().getMyOrders())){
                            MainActivity.tv_news.setVisibility(View.VISIBLE);
                        }else{
                            MainActivity.tv_news.setVisibility(View.GONE);
                        }
                    }else if("4".equals(ac.getProperty("user_type"))){
                        if(!"0".equals(bean.getData().getMyMsgs()) || !"0".equals(bean.getData().getConsults())){
                            MainActivity.tv_news.setVisibility(View.VISIBLE);
                        }else{
                            MainActivity.tv_news.setVisibility(View.GONE);
                        }
                    }else if("1".equals(ac.getProperty("user_type"))){
                        if(!"0".equals(bean.getData().getMyMsgs()) || !"0".equals(bean.getData().getMyRecoms())){
                            MainActivity.tv_news.setVisibility(View.VISIBLE);
                        }else{
                            MainActivity.tv_news.setVisibility(View.GONE);
                        }
                    }else{
                        if(!"0".equals(bean.getData().getMyMsgs())){
                            MainActivity.tv_news.setVisibility(View.VISIBLE);
                        }else{
                            MainActivity.tv_news.setVisibility(View.GONE);
                        }
                    }
                    booking_num_tv.setText(bean.getData().getOrderTotal());
                    if (bean.getData().getHasPay() != null) {
                        hasMoney_tv.setText("¥" + FDDataUtils.addComma(bean.getData().getHasPay()));
                        waitMoney_tv.setText("¥" + FDDataUtils.addComma(bean.getData().getWaitPay()));
                    }
                }

                @Override
                public void onApiFailure(Throwable t, int errorNo, String strMsg, String tag) {

                }

                @Override
                public void onParseError(String tag) {

                }
            });
        }
    };


    @Override
    public void onDestroy() {
        super.onDestroy();
        MyPushMessageReceiver.removePushListener(mPushListener);
    }

    private void initSp(Context context) {
        SharedPreferences sp = context.getSharedPreferences("user_info", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("user_type", "0");
        editor.commit();
    }

    /**
     * 获取保存在SP中的用户类型
     *
     * @param context
     * @return
     */
    private String getUserType(Context context) {
        SharedPreferences sp = context.getSharedPreferences("user_info", Context.MODE_PRIVATE);
        return sp.getString("user_type", "0").toString();

    }

    /**
     * 更新头像
     */
    private void loadUserInfo() {
        AppUtil.getPpApiClient(ac).memberMembershipInfoShow(ac.getProperty("user_type"), this);
    }

    @Override
    public void onResume() {
        super.onResume();
        //未登录禁用下拉
        if (!ac.isUserIdExsit()) {
            refreshScrollView.setPullRefreshEnabled(false);
        } else {
            refreshScrollView.setPullRefreshEnabled(true);
        }
//        initPersonInfo(getUserType(_activity));// TODO: 2016/7/7 0007  mainactivity中resume调用接口
        initPersonInfo(ac.getProperty("user_type"));
        if(ac.isUserIdExsit()) {
            loadUserInfo();
        }
    }

    private void initString(String pNumber, TextView textView, int start, int end) {
        if (!TextUtils.isEmpty(pNumber) && pNumber.length() > 6) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < pNumber.length(); i++) {
                char c = pNumber.charAt(i);
                if (i < start || i >= pNumber.length() - end) {
                    sb.append(c);
                } else {
                    sb.append('*');
                }
            }
            textView.setText(sb.toString());
        }
    }

    private void initPersonInfo(String type) {
        if (ac.isUserIdExsit()) {
            if ("0".equals(getUserType(_activity))) {
                topbar.findViewById(R.id.title_iv).setVisibility(View.GONE);
            } else {
                topbar.findViewById(R.id.title_iv).setVisibility(View.VISIBLE);
            }
            AppUtil.getPpApiClient(ac).memberMembershipInfoInfoscreen(ac.user_id, type, this);
            if (TextUtils.isEmpty(ac.getProperty("head_pic"))) {
                ac.setImage(head_cv, R.drawable.head1);
            } else {
                ac.setImage(head_cv, ac.head_pic);
            }
            name_tv.setText(ac.getProperty("nickname"));
            String str = ac.getProperty("phone");
            tv_p.setVisibility(View.VISIBLE);
            initString(str, phone_tv, 3, 4);
            //账号类型，0为客商，1为客商中介，2为物业顾问，3为业主，4为服务达人
            if ("0".equals(getUserType(_activity))) {
//                type_tv.setVisibility(View.GONE);
                switchAverageUser();
            } else if ("1".equals(ac.getProperty("user_type"))) {
                type_tv.setText("客商中介");
                topbar.setTitle("切换为普通用户");
                switchMerchants();
            } else if ("2".equals(ac.getProperty("user_type"))) {
                type_tv.setText("物业顾问");
                topbar.setTitle("切换为普通用户");
                switchAgent();
            } else if ("3".equals(ac.getProperty("user_type"))) {
                type_tv.setText("业主");
                topbar.setTitle("切换为普通用户");
                switchOwner();
            } else if ("4".equals(ac.getProperty("user_type"))) {
                type_tv.setText("服务达人");
                topbar.setTitle("切换为普通用户");
                switchService();

            }
        } else {
            ac.clearUserInfo();
            switchAverageUser();
            topbar.setTitle("个人中心");
            tv_p.setVisibility(View.GONE);
            topbar.findViewById(R.id.title_iv).setVisibility(View.GONE);
            name_tv.setText("未登录");
            phone_tv.setText("听说登录了的人都是幸运的人");
            type_tv.setVisibility(View.GONE);
            ac.setImage(head_cv, R.drawable.head1);
            tv_book_count.setVisibility(View.GONE);
            tv_news_count.setVisibility(View.GONE);
            tv_ask_count.setVisibility(View.GONE);
            tv_notice.setVisibility(View.GONE);
        }
    }

    @Override
    public void onApiSuccess(NetResult res, String tag) {
        refreshScrollView.onPullDownRefreshComplete();
        refreshScrollView.onPullUpRefreshComplete();
        if ("memberMembershipInfoShow".equals(tag)) {//刷新 头像回调
            MemberMembershipInfoShowBean userBean = (MemberMembershipInfoShowBean) res;
            if (TextUtils.isEmpty(userBean.getData().getMember().getPortrait())) {
                ac.setImage(head_cv, R.drawable.head1);
            } else {
                ac.setProperty("head_pic", userBean.getData().getMember().getPortrait());
                ac.setImage(head_cv, userBean.getData().getMember().getPortrait());
            }
        }
//        //判断是否显示未读消息图标
//        if("messageCountInnerMessage".equals(tag)){
//            if("messageCountInnerMessage".equals(tag)){
//                MessageCountInnerMessageBean bean= (MessageCountInnerMessageBean) res;
//                if("0".equals(ac.getProperty("user_type"))) {
//                    if (!"0".equals(bean.getData().getOrders()) || !"0".equals(bean.getData().getConsults())) {
//                        MainActivity.tv_news.setVisibility(View.VISIBLE);
//                    }else{
//                        MainActivity.tv_news.setVisibility(View.GONE);
//                    }
//                }else if("2".equals(ac.getProperty("user_type"))){
//                    if (!"0".equals(bean.getData().getOrders())) {
//                        MainActivity.tv_news.setVisibility(View.VISIBLE);
//                    }else{
//                        MainActivity.tv_news.setVisibility(View.GONE);
//                    }
//                }
//            }
//        }
        if ("memberMembershipInfoConvertRole".equals(tag)) {
            UIHelper.t(_activity, "切换角色成功!");
            if (flag == 0) {
                switchAverageUser();
                AppUtil.getPpApiClient(ac).memberMembershipInfoInfoscreen(ac.user_id, "0", this);//获取主页信息
                AppUtil.getPpApiClient(ac).messageCountInnerMessage("0", this);//获取普通用户消息
                ac.ischange = true;
                ac.setProperty("user_type", "0");
                if (roletype == 1)
                    topbar.setTitle("切换为客商中介");
                if (roletype == 2)
                    topbar.setTitle("切换为物业顾问");
                if (roletype == 3) {
                    topbar.setTitle("切换为业主");
                    Log.d("wqf", "ac.getProperty(\"user_type\")::" + ac.getProperty("user_type"));
                    Log.d("wqf", "user_type::" + ac.user_type);
                }
                if (roletype == 4)
                    topbar.setTitle("切换为服务达人");
                flag = 1;
            } else if (flag == 1) {
                if (roletype == 1) {
                    switchMerchants();
                }
                if (roletype == 2) {
                    switchAgent();
                    AppUtil.getPpApiClient(ac).messageCountInnerMessage(ac.getProperty("user_type"), this);//获取物业顾问消息
                }
                if (roletype == 3) {
                    switchOwner();
                }
                if (roletype == 4) {
                    switchService();
                }
                ac.ischange = true;
                ac.setProperty("user_type", roletype + "");
                initPersonInfo(ac.getProperty("user_type"));
                topbar.setTitle("切换为普通用户");
                Log.d("wqf", "ac.getProperty(\"user_type\")::" + ac.getProperty("user_type"));
                Log.d("wqf", "user_type::" + ac.user_type);
                flag = 0;
            }
            Log.d("=-=-=-=-=-", "onApiSuccess: 切换 成功flag=" + flag + "roletype=" + roletype);

        }
        if ("memberMembershipInfoInfoscreen".equals(tag)) {
            PersonalIndexBean bean = (PersonalIndexBean) res;
            Log.d("wqf",bean.getData().getMyMsgs());
            if (!"0".equals(bean.getData().getMyMsgs()))
                tv_news_count.setVisibility(View.VISIBLE);
            else{
                tv_news_count.setVisibility(View.GONE);
            }
            if (!"0".equals(bean.getData().getMyOrders()))
                tv_book_count.setVisibility(View.VISIBLE);
            else{
                tv_book_count.setVisibility(View.GONE);
            }
            if (!"0".equals(bean.getData().getConsults()))
                tv_ask_count.setVisibility(View.VISIBLE);
            else{
                tv_ask_count.setVisibility(View.GONE);
            }
            if (!"0".equals(bean.getData().getMyRecoms()))
                tv_notice.setVisibility(View.VISIBLE);
            else{
                tv_notice.setVisibility(View.GONE);
            }
            if("0".equals(ac.getProperty("user_type")) || "2".equals(ac.getProperty("user_type"))){
                if(!"0".equals(bean.getData().getMyMsgs()) || !"0".equals(bean.getData().getMyOrders())){
                    MainActivity.tv_news.setVisibility(View.VISIBLE);
                }else{
                    MainActivity.tv_news.setVisibility(View.GONE);
                }
            }else if("4".equals(ac.getProperty("user_type"))){
                if(!"0".equals(bean.getData().getMyMsgs()) || !"0".equals(bean.getData().getConsults())){
                    MainActivity.tv_news.setVisibility(View.VISIBLE);
                }else{
                    MainActivity.tv_news.setVisibility(View.GONE);
                }
            }else if("1".equals(ac.getProperty("user_type"))){
                if(!"0".equals(bean.getData().getMyMsgs()) || !"0".equals(bean.getData().getMyRecoms())){
                    MainActivity.tv_news.setVisibility(View.VISIBLE);
                }else{
                    MainActivity.tv_news.setVisibility(View.GONE);
                }
            }else{
                if(!"0".equals(bean.getData().getMyMsgs())){
                    MainActivity.tv_news.setVisibility(View.VISIBLE);
                }else{
                    MainActivity.tv_news.setVisibility(View.GONE);
                }
            }
            booking_num_tv.setText(bean.getData().getOrderTotal());
            if (bean.getData().getHasPay() != null) {
                hasMoney_tv.setText("¥" + FDDataUtils.addComma(bean.getData().getHasPay()));
                waitMoney_tv.setText("¥" + FDDataUtils.addComma(bean.getData().getWaitPay()));
            }
        }
    }

    /**
     * 个人中心更新头像后返回此方法更新头像
     */
    public void refreshCity() {
        if (!ac.isUserIdExsit()) {
            return;
        }
        loadUserInfo();// 下拉刷新 更新头像
       /* String fileName = ac.getProperty("user_type_second");
        Log.d("=-=-=-=-", "refreshCity: "+ac.getProperty("user_type_second"));
        Glide.with(_activity).load(fileName).centerCrop().thumbnail(0.1f).placeholder(R.drawable.head1).error(R.drawable.head1).into(head_cv);*/
    }

    @Override
    public void onClick(View v) {
        if (!ac.isUserIdExsit() && v.getId() != R.id.setting) {
            UIHelper.jump(_activity, LoginActivity.class);
            return;
        }
        try {
            roletype = Integer.valueOf(getUserType(_activity)).intValue();
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        Intent intent = new Intent();
        switch (v.getId()) {
            case R.id.title_ll:
                /*
                  1  switchMerchants();//客商中介
                  2  switchAgent();//经纪人
                  3  switchOwner();//业主
                  4  switchService();//服务达人
                  0  switchAverageUser();//普通用户
                 */
                if (roletype == 0) {
                    UIHelper.t(_activity, "当前为普通用户");
                } else {
                    if (flag == 0) {
                        AppUtil.getPpApiClient(ac).memberMembershipInfoConvertRole("0", this);
                    } else if (flag == 1) {
                        AppUtil.getPpApiClient(ac).memberMembershipInfoConvertRole(roletype + "", this);
                    }
                }
                break;
            case R.id.personal_ll://个人中心
                UIHelper.jumpForResult(_activity, PersonInfoActivity.class, 1301);
                break;
            case R.id.property_tv:
                //我的物业
                UIHelper.jump(_activity, MyPropertyActivity.class);
                break;
            case R.id.my_order_tv:
                //我的订单
                UIHelper.jump(_activity, MyOrderActivity.class);
                break;
            case R.id.disk_source_tv:
                //我的盘源
                UIHelper.jump(_activity, MyBuildingResourceActivity.class);
                break;
            case R.id.tv_personalpage:
                //个人主页
                Bundle mBundle = new Bundle();
                mBundle.putString("counselorid", ac.user_id);
                mBundle.putString("tag", "PersonalFragment");
                UIHelper.jump(_activity, ConsultantDetailActivity1.class, mBundle);
                break;
            case R.id.rl_consulting:
                //咨询记录
//                _activity.startActivity(intent);
                UIHelper.jump(_activity, AskHistoryActivity.class);
                break;
            case R.id.recommend_rl:
                UIHelper.jump(_activity, MyRecommendActivity.class);
                break;
            case R.id.booking:
                if ("0".equals(ac.getProperty("user_type"))) {
                    //普通客商——我的预约
                    UIHelper.jump(_activity, BookingActivity.class);
                } else if ("2".equals(ac.getProperty("user_type"))) {
                    //物业顾问——我的预约
                    UIHelper.jump(_activity, BookingActivity_Counselor.class);
                }
                break;
            case R.id.sign:
                //已签约
                UIHelper.jump(_activity, BookedActivity.class);
                break;
            case R.id.message:
                //消息
                UIHelper.jump(_activity, NewsActivity.class);
                break;
            case R.id.gratuity_tv:
                //打赏
                UIHelper.jump(_activity, MyRewardActivity.class);
                break;
            case R.id.collect:
                //我的收藏
                UIHelper.jump(_activity, MyCollectionActivity.class);
                break;
            case R.id.comment: //我的评价
                if ("0".equals(ac.getProperty("user_type"))) {//普通用户的评价
                    UIHelper.jump(_activity, MyCommentActivity.class);
                } else if ("2".equals(ac.getProperty("user_type"))) {
                    //经纪人评价
                    UIHelper.jump(_activity, ConsultantCommentActivity.class);
                } else if ("4".equals(ac.getProperty("user_type"))) {
                    //服务达人
                    UIHelper.jump(_activity, CommentToServiceTalentActivity.class);
                }
                break;
            case R.id.contrast:
                //对比类型
                UIHelper.jump(_activity, MyContrastActivity.class);
                break;
            case R.id.setting:
                //设置
                UIHelper.jump(_activity, SettingActivity.class);
                break;
        }
    }


    /**
     * 普通用户
     */
    private void switchAverageUser() {
        type = "1";
        type_tv.setVisibility(View.GONE);
        money_ll.setVisibility(View.GONE);
        property_tv.setVisibility(View.GONE);
        my_order_tv.setVisibility(View.GONE);
        disk_source_tv.setVisibility(View.GONE);
        tv_personalpage.setVisibility(View.GONE);
        consulting_tv.setVisibility(View.GONE);
        recommend_rl.setVisibility(View.GONE);
        booking_top.setVisibility(View.VISIBLE);
        booking_tv.setVisibility(View.VISIBLE);
        sign_tv.setVisibility(View.VISIBLE);
        gratuity_tv.setVisibility(View.GONE);
        message_tv.setVisibility(View.VISIBLE);
        collect_tv.setVisibility(View.VISIBLE);
        myComment_tv.setVisibility(View.VISIBLE);
        contrast_tv.setVisibility(View.VISIBLE);
        setting_tv.setVisibility(View.VISIBLE);
    }

    /**
     * 客商中介
     */
    private void switchMerchants() {
        type = "2";
        type_tv.setVisibility(View.VISIBLE);
        type_tv.setText("客商中介");
        money_ll.setVisibility(View.VISIBLE);
        property_tv.setVisibility(View.GONE);
        my_order_tv.setVisibility(View.GONE);
        disk_source_tv.setVisibility(View.GONE);
        tv_personalpage.setVisibility(View.GONE);
        consulting_tv.setVisibility(View.GONE);
        recommend_rl.setVisibility(View.VISIBLE);
        booking_top.setVisibility(View.GONE);
        booking_tv.setVisibility(View.GONE);
        sign_tv.setVisibility(View.GONE);
        gratuity_tv.setVisibility(View.GONE);
        message_tv.setVisibility(View.VISIBLE);
        collect_tv.setVisibility(View.GONE);
        myComment_tv.setVisibility(View.GONE);
        contrast_tv.setVisibility(View.GONE);
        setting_tv.setVisibility(View.VISIBLE);
        ll_book.setVisibility(View.GONE);
        v_book.setVisibility(View.GONE);
    }

    /**
     * 经纪人
     */
    private void switchAgent() {
        type = "3";
        type_tv.setVisibility(View.VISIBLE);
        type_tv.setText("物业顾问");
        money_ll.setVisibility(View.VISIBLE);
        property_tv.setVisibility(View.GONE);
        my_order_tv.setVisibility(View.VISIBLE);
        disk_source_tv.setVisibility(View.VISIBLE);
        tv_personalpage.setVisibility(View.VISIBLE);
        consulting_tv.setVisibility(View.GONE);
        recommend_rl.setVisibility(View.GONE);
        booking_top.setVisibility(View.GONE);
        booking_tv.setVisibility(View.VISIBLE);
        sign_tv.setVisibility(View.GONE);
        gratuity_tv.setVisibility(View.GONE);
        message_tv.setVisibility(View.VISIBLE);
        collect_tv.setVisibility(View.GONE);
        myComment_tv.setVisibility(View.VISIBLE);
        contrast_tv.setVisibility(View.GONE);
        setting_tv.setVisibility(View.VISIBLE);
    }

    /**
     * 业主
     */
    private void switchOwner() {
        type = "4";
        type_tv.setVisibility(View.VISIBLE);
        type_tv.setText("业主");
        money_ll.setVisibility(View.GONE);
        property_tv.setVisibility(View.VISIBLE);
        my_order_tv.setVisibility(View.GONE);
        disk_source_tv.setVisibility(View.GONE);
        tv_personalpage.setVisibility(View.GONE);
        consulting_tv.setVisibility(View.GONE);
        recommend_rl.setVisibility(View.GONE);
        booking_top.setVisibility(View.GONE);
        booking_tv.setVisibility(View.GONE);
        sign_tv.setVisibility(View.GONE);
        gratuity_tv.setVisibility(View.VISIBLE);
        message_tv.setVisibility(View.VISIBLE);
        collect_tv.setVisibility(View.GONE);
        myComment_tv.setVisibility(View.GONE);
        contrast_tv.setVisibility(View.GONE);
        setting_tv.setVisibility(View.VISIBLE);
    }

    /**
     * 服务达人
     */
    private void switchService() {
        type = "0";
        type_tv.setVisibility(View.VISIBLE);
        type_tv.setText("服务达人");
        money_ll.setVisibility(View.GONE);
        property_tv.setVisibility(View.GONE);
        my_order_tv.setVisibility(View.GONE);
        disk_source_tv.setVisibility(View.GONE);
        tv_personalpage.setVisibility(View.GONE);
        consulting_tv.setVisibility(View.VISIBLE);
        recommend_rl.setVisibility(View.GONE);
        booking_top.setVisibility(View.GONE);
        booking_tv.setVisibility(View.GONE);
        sign_tv.setVisibility(View.GONE);
        gratuity_tv.setVisibility(View.GONE);
        message_tv.setVisibility(View.VISIBLE);
        collect_tv.setVisibility(View.GONE);
        myComment_tv.setVisibility(View.VISIBLE);
        contrast_tv.setVisibility(View.GONE);
        setting_tv.setVisibility(View.VISIBLE);
    }

    @Override
    public void onApiStart(String tag) {

    }

    @Override
    public void onApiLoading(long count, long current, String tag) {

    }

    @Override
    public void onApiFailure(Throwable t, int errorNo, String strMsg, String tag) {

    }

    @Override
    public void onParseError(String tag) {

    }
}
