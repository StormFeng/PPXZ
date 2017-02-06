package com.midian.ppaddress.itemtpl;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.TextView;

import com.midian.ppaddress.R;
import com.midian.ppaddress.bean.MemberCommentsAgencyexpertPageMeBean;

import midian.baselib.bean.NetResult;
import midian.baselib.view.BaseTpl;

/**
 * 详情-评论条目
 * Created by chu on 2016/4/25.
 */
public class CommentToServiceTalentTpl extends BaseTpl<MemberCommentsAgencyexpertPageMeBean.Lists>{
    private ImageView head_cv;
    private TextView tv_name,tv_rate,tv_content,tv_time,tv_question;
    public CommentToServiceTalentTpl(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CommentToServiceTalentTpl(Context context) {
        super(context);
    }

    @Override
    protected void initView() {
        head_cv=findView(R.id.head_cv);
        tv_name=findView(R.id.tv_name);
        tv_rate=findView(R.id.tv_rate);
        tv_content=findView(R.id.tv_content);
        tv_time=findView(R.id.tv_time);
        tv_question=findView(R.id.tv_question);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.item_comment_to_servicetalent;
    }

    @Override
    public void setBean(MemberCommentsAgencyexpertPageMeBean.Lists bean, int position) {
        if(bean!=null){
//            MemberCommentsAgencyexpertPageMeBean.Lists textList = bean.getData().getList().get(position);
            ac.setImage(head_cv,bean.getPortrait());
            tv_name.setText(bean.getMember());
            tv_content.setText(bean.getContent());
            tv_time.setText(bean.getCreateTime());
            tv_question.setText("问题："+bean.getQuestion());
            if("0".equals(bean.getRate())) {
                tv_rate.setText("无");
                tv_rate.setBackgroundColor(getResources().getColor(R.color.dp_gray));
            }
            if("1".equals(bean.getRate())) {
                tv_rate.setText("好评");
                tv_rate.setBackgroundColor(getResources().getColor(R.color.red));
            }
            if("2".equals(bean.getRate())) {
                tv_rate.setText("中评");
                tv_rate.setBackgroundColor(getResources().getColor(R.color.orange_button));
            }
            if("3".equals(bean.getRate())) {
                tv_rate.setText("差评");
                tv_rate.setBackgroundColor(getResources().getColor(R.color.dp_gray));
            }
        }
    }
}
