package com.midian.ppaddress.ui.personal;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.midian.baidupush.MyPushMessageReceiver;
import com.midian.baidupush.PushMessage;
import com.midian.ppaddress.R;
import com.midian.ppaddress.bean.MessageCountInnerMessageBean;
import com.midian.ppaddress.datasource.NewsActivityDataResource;
import com.midian.ppaddress.itemtpl.NewsActivityBotTpl;
import com.midian.ppaddress.utils.AppUtil;

import java.util.ArrayList;

import midian.baselib.base.BaseListActivity;
import midian.baselib.bean.NetResult;
import midian.baselib.shizhefei.view.listviewhelper.IDataAdapter;
import midian.baselib.shizhefei.view.listviewhelper.IDataSource;
import midian.baselib.utils.UIHelper;
import midian.baselib.widget.BaseLibTopbarView;

public class NewsActivity extends BaseListActivity implements View.OnClickListener {
    private BaseLibTopbarView topbar;
    private TextView tv_ask, tv_booknews;
    private TextView tv_ask_count,tv_book_count;

    private int flag;//用于标识是否第一次进入，避免二次刷新listview


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        topbar = findView(R.id.topbar);
        tv_ask = findView(R.id.tv_ask);
        tv_booknews = findView(R.id.tv_booknews);
        tv_ask_count=findView(R.id.tv_ask_count);
        tv_book_count=findView(R.id.tv_book_count);
        topbar.setTitle("消息").setLeftImageButton(R.drawable.icon_back, UIHelper.finish(_activity)).setRightText("约看规则", this);
        if("0".equals(ac.getProperty("user_type")) || "2".equals(ac.getProperty("user_type"))){
            AppUtil.getPpApiClient(ac).messageCountInnerMessage(ac.getProperty("user_type"),this);
        }
        tv_ask.setOnClickListener(this);
        tv_booknews.setOnClickListener(this);
        //隐藏/显示咨询记录
        if ("0".equals(ac.getProperty("user_type"))) {
            tv_ask.setVisibility(View.VISIBLE);
        } else {
            tv_ask.setVisibility(View.GONE);
        }
        //隐藏/显示预约消息
        if("0".equals(ac.getProperty("user_type")) || "2".equals(ac.getProperty("user_type"))){
            tv_booknews.setVisibility(View.VISIBLE);
        }else{
            tv_booknews.setVisibility(View.GONE);
        }

        //注册监听推送接收
        MyPushMessageReceiver.addPushListener(mPushListener);
        listViewHelper.loadViewFactory.config(false, "暂无系统消息");
        flag=0;
    }

    @Override
    public void onApiSuccess(NetResult res, String tag) {
        super.onApiSuccess(res, tag);
        if(res.isOK()){
            MessageCountInnerMessageBean bean= (MessageCountInnerMessageBean) res;
            if("0".equals(bean.getData().getOrders())){
                tv_book_count.setVisibility(View.GONE);
            }else{
                tv_book_count.setVisibility(View.VISIBLE);
            }
            if("0".equals(ac.getProperty("user_type"))){
                if("0".equals(bean.getData().getConsults())){
                    tv_ask_count.setVisibility(View.GONE);
                }else{
                    tv_ask_count.setVisibility(View.VISIBLE);
                }
            }
        }else{
            ac.handleErrorCode(_activity,res.errorcode);
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.comman_list_item_news;
    }

    @Override
    protected IDataSource<ArrayList> getDataSource() {
        return new NewsActivityDataResource(_activity);
    }

    @Override
    protected Class getTemplateClass() {
        return NewsActivityBotTpl.class;
    }


    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.right_tv:
                UIHelper.jump(_activity, LookRuleActivity.class);
                break;
            case R.id.tv_ask:
                UIHelper.jumpForResult(_activity, AskHistoryActivity.class,1010);
                break;
            case R.id.tv_booknews:
                UIHelper.jumpForResult(_activity, BookNewsActivity.class,1020);
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(flag!=0) {
            listViewHelper.refresh();
        }
        flag++;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK){
            if("0".equals(ac.getProperty("user_type")) || "2".equals(ac.getProperty("user_type"))){
                AppUtil.getPpApiClient(ac).messageCountInnerMessage(ac.getProperty("user_type"),this);
            }
        }
    }

    @Override
    public void onEndRefresh(IDataAdapter adapter, Object result) {
        listViewHelper.loadViewFactory.config(false, "暂无系统消息");
    }

    @Override
    public void onEndLoadMore(IDataAdapter adapter, Object result) {

    }

    MyPushMessageReceiver.PushListener mPushListener=new MyPushMessageReceiver.PushListener() {
        @Override
        public void updateContent(PushMessage msg) {
            //有消息推送时，可以处理一些消息相关状态等
            listViewHelper.refresh();
        }
    };
    @Override
    public void onDestroy() {
        super.onDestroy();
        MyPushMessageReceiver.removePushListener(mPushListener);
    }
}


