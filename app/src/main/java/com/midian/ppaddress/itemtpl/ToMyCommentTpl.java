package com.midian.ppaddress.itemtpl;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.TextView;

import com.midian.ppaddress.R;
import com.midian.ppaddress.bean.MemberCommentsCounselorPageMeBean;

import midian.baselib.bean.NetResult;
import midian.baselib.view.BaseTpl;

/**
 * Created by chu on 2016/5/5.
 */
public class ToMyCommentTpl extends BaseTpl<MemberCommentsCounselorPageMeBean.Lists>{

    private ImageView head_cv;
    private TextView tv_name,tv_rate,tv_content,tv_time;
    public ToMyCommentTpl(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ToMyCommentTpl(Context context) {
        super(context);
    }

    @Override
    protected void initView() {
        head_cv=findView(R.id.head_cv);
        tv_name=findView(R.id.tv_name);
        tv_rate=findView(R.id.tv_rate);
        tv_content=findView(R.id.tv_content);
        tv_time=findView(R.id.tv_time);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.item_to_my_comment_tpl;
    }

    @Override
    public void setBean(MemberCommentsCounselorPageMeBean.Lists bean, int position) {
        if(bean!=null){
//            MemberCommentsCounselorPageMeBean.Lists textList = bean.getData().getList().get(position);
            ac.setImage(head_cv,bean.getPortrait());
            tv_name.setText(bean.getMember());
            tv_content.setText(bean.getContent());
                tv_time.setText(bean.getCreateTime());
            if("0".equals(bean.getRate())) {
                tv_rate.setText("无");
                tv_rate.setBackgroundColor(getResources().getColor(R.color.dp_gray));
            }
            if("1".equals(bean.getRate())){
                tv_rate.setText("好评");
                tv_rate.setBackgroundColor(getResources().getColor(R.color.red));
            }
            if("2".equals(bean.getRate())){
                tv_rate.setText("中评");
                tv_rate.setBackgroundColor(getResources().getColor(R.color.orange_button));
            }
            if("3".equals(bean.getRate())){
                tv_rate.setText("差评");
                tv_rate.setBackgroundColor(getResources().getColor(R.color.dp_gray));
            }
        }
    }

}
