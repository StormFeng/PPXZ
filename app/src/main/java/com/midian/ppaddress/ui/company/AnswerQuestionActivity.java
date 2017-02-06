package com.midian.ppaddress.ui.company;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.midian.ppaddress.R;
import com.midian.ppaddress.bean.BusinessAgencyConsultSendRecordBean;
import com.midian.ppaddress.bean.BusinessAgencyConsultShowQuestionBean;
import com.midian.ppaddress.datasource.AnswerDataSource;
import com.midian.ppaddress.itemtpl.AnswerBTpl;
import com.midian.ppaddress.itemtpl.AnswerLeftTpl;
import com.midian.ppaddress.itemtpl.AnswerRightTpl;
import com.midian.ppaddress.itemtpl.AnswerTTpl;
import com.midian.ppaddress.utils.AppUtil;

import java.util.ArrayList;

import midian.baselib.api.ApiCallback;
import midian.baselib.base.BaseMultiTypeListActivity;
import midian.baselib.bean.NetResult;
import midian.baselib.shizhefei.view.listviewhelper.IDataAdapter;
import midian.baselib.shizhefei.view.listviewhelper.IDataSource;
import midian.baselib.utils.UIHelper;
import midian.baselib.widget.BaseLibTopbarView;
import midian.baselib.widget.pulltorefresh.PullToRefreshListView;

/**
 * 咨询记录页
 * Created by Administrator on 2016/4/26.
 */
public class AnswerQuestionActivity extends BaseMultiTypeListActivity implements ApiCallback{

    private BaseLibTopbarView topbar;
    private String questionid,agencyexpertId,isclose;
    private EditText et_sendMessage;
    private ImageView iv_refresh;
    private TextView tv_closeTime;
    private PullToRefreshListView listView;

    public static String receiverId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Intent intent=getIntent();
        questionid = intent.getStringExtra("questionId");
//        agencyexpertId=intent.getStringExtra("agencyexpertId");
        isclose=intent.getStringExtra("isclose");
        super.onCreate(savedInstanceState);
        initView();
    }

    @Override
    public void onApiSuccess(NetResult res, String tag) {
        super.onApiSuccess(res, tag);
        if(res.isOK()){
            if("businessAgencyConsultSendRecord".equals(tag)) {
                UIHelper.t(_activity,res.message);
                et_sendMessage.setText("");
                listViewHelper.refresh();
            }
        }else{
            ac.handleErrorCode(_activity,res.message);
        }
    }


    private void initView() {
        topbar = findView(R.id.topbar);
        iv_refresh=findView(R.id.iv_refresh);
        tv_closeTime=findView(R.id.tv_closeTime);
        listView= findView(R.id.pullToRefreshListView);
        topbar.setTitle("咨询会话");
        topbar.setLeftImageButton(R.drawable.icon_back, UIHelper.finish(_activity));
        topbar.setRightText("结束", null);
        topbar.getRight_tv().setOnClickListener(this);
        findViewById(R.id.btn_sendMessage).setOnClickListener(this);
        et_sendMessage=findView(R.id.et_sendMessage);
        iv_refresh.setOnClickListener(this);
        if("1".equals(isclose)){
            findViewById(R.id.ll_btn).setVisibility(View.GONE);
            findViewById(R.id.iv_refresh).setVisibility(View.GONE);
            findViewById(R.id.topbar).findViewById(R.id.right_tv).setVisibility(View.GONE);
        }
    }

    @Override
    public void onClick(View arg0) {
        super.onClick(arg0);
        switch (arg0.getId()){
            case R.id.btn_sendMessage://发送消息
                String message=et_sendMessage.getText().toString();
                if(TextUtils.isEmpty(message)){
                    UIHelper.t(_activity,"请输入要发送的消息");
                }else {
                    AppUtil.getPpApiClient(ac).businessAgencyConsultSendRecord(ac.user_id, ac.getProperty("user_type"), receiverId, questionid, message, this);
                }
                break;
            case R.id.iv_refresh://刷新
                listViewHelper.refresh();
                break;
            case R.id.right_tv://结束会话
                new AlertDialog.Builder(_activity).setTitle("提示").setMessage("您是否要结束对话?").setNegativeButton("否",null).setPositiveButton("是", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        AppUtil.getPpApiClient(ac).businessAgencyConsultCloseQuestion(questionid, ac.user_id, ac.getProperty("user_type"), new ApiCallback() {
                            @Override
                            public void onApiStart(String tag) {

                            }

                            @Override
                            public void onApiLoading(long count, long current, String tag) {

                            }

                            @Override
                            public void onApiSuccess(NetResult res, String tag) {
                                if(res.isOK()){
                                    UIHelper.t(_activity, res.message);
                                    if(!"4".equals(ac.getProperty("user_type"))) {
                                        Bundle bundle = new Bundle();
                                        bundle.putString("questionid", questionid);
                                        bundle.putString("agencyexpertId", receiverId);
                                        UIHelper.jumpForResult(_activity, EvaluateActivity.class, bundle, 1001);
                                    }else{
                                        listViewHelper.refresh();
                                        listViewHelper.getListView().setSelection(0);
                                    }
                                }else{
                                    ac.handleErrorCode(_activity,res.message);
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
                }).show();
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK){
            setResult(RESULT_OK);
            finish();
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_answer_question;
    }

    @Override
    protected IDataSource<ArrayList> getDataSource() {
        return new AnswerDataSource(questionid,_activity);
    }

    @Override
    protected ArrayList<Class> getTemplateClasses() {
        ArrayList<Class> tpls=new ArrayList<Class>();
        tpls.add(AnswerTTpl.class);
        tpls.add(AnswerLeftTpl.class);
        tpls.add(AnswerRightTpl.class);
        tpls.add(AnswerBTpl.class);
        return tpls;
    }

    @Override
    protected void onResume() {
        super.onResume();
        listViewHelper.refresh();
    }
}
