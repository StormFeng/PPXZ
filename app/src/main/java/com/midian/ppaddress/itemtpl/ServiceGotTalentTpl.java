package com.midian.ppaddress.itemtpl;

import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.midian.ppaddress.R;
import com.midian.ppaddress.bean.CommentToServerBean;
import com.midian.ppaddress.ui.personal.PersonalCommentActivity;

import midian.baselib.bean.NetResult;
import midian.baselib.utils.UIHelper;
import midian.baselib.view.BaseTpl;

/**
 * Created by chu on 2016/5/3.
 */
public class ServiceGotTalentTpl extends BaseTpl<CommentToServerBean.Lists> implements View.OnClickListener {
    private TextView to_comment_tv,name_tv,time_tv,question_tv,rate_tv,content_tv;
    private ImageView head_cv;
    private String flag="ServiceGotTalentTpl";
    private String questionid;
    private String isComments;//是否已评论

    public ServiceGotTalentTpl(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    public ServiceGotTalentTpl(Context context) {
        super(context);
    }

    @Override
    protected void initView() {
        to_comment_tv =findView(R.id.to_comment_tv);
        to_comment_tv.setOnClickListener(this);
        head_cv=findView(R.id.head_cv);
        name_tv=findView(R.id.name_tv);
        time_tv=findView(R.id.time_tv);
        question_tv= findView(R.id.question_tv);
        rate_tv=findView(R.id.rate_tv);
        content_tv=findView(R.id.content_tv);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.item_service_got_talent_tpl;
    }

    @Override
    public void setBean(CommentToServerBean.Lists bean, int position) {
        if(bean!=null){
            ac.setImage(head_cv,bean.getPortrait());
            name_tv.setText(bean.getMember());
            time_tv.setText(bean.getCreateTime());
            question_tv.setText("问题："+bean.getQuestion());
            rate_tv.setVisibility(VISIBLE);
            if("0".equals(bean.getRate())){
                rate_tv.setText("无");
                rate_tv.setBackgroundColor(getResources().getColor(R.color.dp_gray));
            }
            else if("1".equals(bean.getRate())){
                rate_tv.setText("好评");
                rate_tv.setBackgroundColor(getResources().getColor(R.color.red));
            }
            else if("2".equals(bean.getRate())) {
                rate_tv.setText("中评");
                rate_tv.setBackgroundColor(getResources().getColor(R.color.orange_button));
            }
            else if("3".equals(bean.getRate())) {
                rate_tv.setText("差评");
                rate_tv.setBackgroundColor(getResources().getColor(R.color.dp_gray));
            }
            content_tv.setText(bean.getContent());
            questionid=bean.getQuestionid();
            isComments=bean.getIsComments();
            if("0".equals(isComments)){
                findView(R.id.ll_comment).setVisibility(GONE);
                findView(R.id.to_comment_tv).setVisibility(VISIBLE);
            }else{
                findView(R.id.ll_comment).setVisibility(VISIBLE);
                findView(R.id.to_comment_tv).setVisibility(GONE);
            }
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.to_comment_tv:
                Intent intent=new Intent();
                intent.setClass(_activity,PersonalCommentActivity.class);
                intent.putExtra("flag",flag);
                intent.putExtra("questionid",questionid);
                _activity.startActivity(intent);
//                UIHelper.jump(_activity, PersonalCommentActivity.class);
                break;
        }
    }
}
