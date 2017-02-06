package com.midian.ppaddress.itemtpl;

import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.midian.ppaddress.R;
import com.midian.ppaddress.bean.MemberCommentsMemberPageMemberMeOrderBean;
import com.midian.ppaddress.ui.personal.PersonalCommentActivity;
import com.umeng.socialize.view.CommentActivity;

import midian.baselib.bean.NetResult;
import midian.baselib.utils.FDDataUtils;
import midian.baselib.utils.UIHelper;
import midian.baselib.view.BaseTpl;
import midian.baselib.widget.CircleImageView;

/**
 * Created by chu on 2016/5/3.
 */
public class MyConsultantCommentListTpl extends BaseTpl<MemberCommentsMemberPageMemberMeOrderBean.Lists> implements View.OnClickListener {
    private ImageView image_iv;
    private CircleImageView head_cv;
    private TextView name_tv, to_comment_tv, title_tv, time_tv,rate_tv_1,rate_tv_2,content_tv_1,content_tv_2;
    private String orderid;
    private String flag="MyConsultantCommentListTpl";

    public MyConsultantCommentListTpl(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyConsultantCommentListTpl(Context context) {
        super(context);
    }

    @Override
    protected void initView() {
        to_comment_tv =findView(R.id.to_comment_tv);
        to_comment_tv.setOnClickListener(this);
        image_iv=findView(R.id.image_iv);
        head_cv=findView(R.id.head_cv);
        name_tv=findView(R.id.name_tv);
        title_tv=findView(R.id.title_tv);
        time_tv=findView(R.id.time_tv);
        rate_tv_1=findView(R.id.rate_tv_1);
        rate_tv_2=findView(R.id.rate_tv_2);
        content_tv_1=findView(R.id.content_tv_1);
        content_tv_2=findView(R.id.content_tv_2);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.item_my_consulatant_comment_list_tpl;
    }

    @Override
    public void setBean(MemberCommentsMemberPageMemberMeOrderBean.Lists bean, int position) {
        if(bean!=null){
//            MemberCommentsMemberPageMemberMeOrderBean.Lists item = bean.getData().getList().get(position);
            try {
                ac.setImage(image_iv,bean.getImages());
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                ac.setImage(head_cv,bean.getPortrait());
            } catch (Exception e) {
                e.printStackTrace();
            }
            name_tv.setText(bean.getUsername());
            title_tv.setText(bean.getCarrierName());
            time_tv.setText(FDDataUtils.getDateToYMD(bean.getCreateTime()));
            content_tv_1.setText("对顾问："+bean.getCommentServer());
            content_tv_2.setText("对载体："+bean.getCommentCarrier());


            if("0".equals(bean.getServerRate())){
                rate_tv_1.setText("无");
                rate_tv_1.setBackgroundColor(getResources().getColor(R.color.dp_gray));
            }
            else if("1".equals(bean.getServerRate())){
                rate_tv_1.setText("好评");
                rate_tv_1.setBackgroundColor(getResources().getColor(R.color.red));
            }
            else if("2".equals(bean.getServerRate())){
                rate_tv_1.setText("中评");
                rate_tv_1.setBackgroundColor(getResources().getColor(R.color.orange_button));
            }
            else if("3".equals(bean.getServerRate())){
                rate_tv_1.setText("差评");
                rate_tv_1.setBackgroundColor(getResources().getColor(R.color.dp_gray));
            }

            if("0".equals(bean.getCarrierRate())){
                rate_tv_2.setText("无");
                rate_tv_2.setBackgroundColor(getResources().getColor(R.color.dp_gray));
            }
            else if("1".equals(bean.getCarrierRate())){
                rate_tv_2.setText("好评");
                rate_tv_2.setBackgroundColor(getResources().getColor(R.color.red));
            }
            else if("2".equals(bean.getCarrierRate())){
                rate_tv_2.setText("中评");
                rate_tv_2.setBackgroundColor(getResources().getColor(R.color.orange_button));
            }
            else if("3".equals(bean.getCarrierRate())){
                rate_tv_2.setText("差评");
                rate_tv_2.setBackgroundColor(getResources().getColor(R.color.dp_gray));
            }
            if("0".equals(bean.getIsComments())){
                findView(R.id.ll_comment).setVisibility(GONE);
                findView(R.id.to_comment_tv).setVisibility(VISIBLE);
            }else{
                findView(R.id.ll_comment).setVisibility(VISIBLE);
                findView(R.id.to_comment_tv).setVisibility(GONE);
            }

            orderid=bean.getId();
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.to_comment_tv:
                Intent intent=new Intent();
                intent.setClass(_activity, PersonalCommentActivity.class);
                intent.putExtra("flag",flag);
                intent.putExtra("orderid",orderid);
                _activity.startActivity(intent);
//                UIHelper.jump(_activity, PersonalCommentActivity.class);
                break;
        }
    }
}
