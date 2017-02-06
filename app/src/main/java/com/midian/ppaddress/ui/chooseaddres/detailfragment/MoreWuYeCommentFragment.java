package com.midian.ppaddress.ui.chooseaddres.detailfragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import com.midian.ppaddress.R;
import com.midian.ppaddress.datasource.MoreWuYeCommentDataSource;
import com.midian.ppaddress.itemtpl.MoreWuyeCommentTpl;
import com.midian.ppaddress.utils.AppUtil;

import java.util.ArrayList;

import midian.baselib.api.ApiCallback;
import midian.baselib.base.BaseListFragment;
import midian.baselib.bean.NetResult;
import midian.baselib.shizhefei.view.listviewhelper.IDataAdapter;
import midian.baselib.shizhefei.view.listviewhelper.IDataSource;
import midian.baselib.utils.UIHelper;

/**
 * 更多物业顾问点评页
 * Created by chu on 2016/3/24.
 */
public class MoreWuYeCommentFragment extends BaseListFragment implements View.OnClickListener, ApiCallback {
    private String carrierid;

    private EditText content_et;
    private TextView comment_tv;
    private View ll_comment;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        content_et = (EditText) view.findViewById(R.id.content_et);
        comment_tv = (TextView) view.findViewById(R.id.comment_tv);
        ll_comment = view.findViewById(R.id.ll_comment);
        comment_tv.setOnClickListener(this);
        //角色类型--0为客商，1为客商中介，2为物业顾问，3为业主，4为服务达人
        //此处评论只为物业顾问开放
        if (!"2".equals(ac.getProperty("user_type"))) {
            ll_comment.setVisibility(View.GONE);
        } else {
            ll_comment.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_more_wuye;
    }

    @Override
    protected IDataSource<ArrayList> getDataSource() {
        return new MoreWuYeCommentDataSource(_activity, carrierid);
    }

    @Override
    protected Class getTemplateClass() {
        return MoreWuyeCommentTpl.class;
    }

    @Override
    public void onEndRefresh(IDataAdapter adapter, Object result) {
    }

    @Override
    public void onEndLoadMore(IDataAdapter adapter, Object result) {
    }

    public String getCarrierid() {
        return carrierid;
    }

    public void setCarrierid(String carrierid) {
        this.carrierid = carrierid;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.comment_tv:
                String content = content_et.getText().toString().trim();
                if (TextUtils.isEmpty(content)) {
                    UIHelper.t(_activity, "评论内容不能为空");
                    return;
                }
                AppUtil.getPpApiClient(ac).memberCommentsCounselorCommentCarrier(carrierid, content, this);
//                    UIHelper.t(_activity, "非物业顾问不能进行评论");
//                    //隐藏软键盘
//                    ((InputMethodManager) _activity.getSystemService(_activity.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(_activity.getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                break;
        }
    }

    @Override
    public void onApiStart(String tag) {
        _activity.showLoadingDlg();
    }

    @Override
    public void onApiLoading(long count, long current, String tag) {

    }

    @Override
    public void onApiSuccess(NetResult res, String tag) {
        _activity.hideLoadingDlg();
        if (res.isOK()) {
            UIHelper.t(_activity, res.message);
            content_et.setText("");
            //隐藏软键盘
            ((InputMethodManager) _activity.getSystemService(_activity.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(_activity.getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
            listViewHelper.refresh();
        } else {
            ac.handleErrorCode(_activity, res.message);
        }
    }

    @Override
    public void onApiFailure(Throwable t, int errorNo, String strMsg, String tag) {

    }

    @Override
    public void onParseError(String tag) {

    }
}
