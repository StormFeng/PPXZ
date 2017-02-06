package com.midian.ppaddress.itemtpl;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.TextView;

import com.midian.ppaddress.R;
import com.midian.ppaddress.bean.AnswerMulBean;
import com.midian.ppaddress.bean.BusinessAgencyConsultPageRecordBean;

import midian.baselib.view.BaseTpl;

/**
 * 详情-评论条目
 * Created by chu on 2016/4/25.
 */
public class AnswerRightTpl extends BaseTpl<AnswerMulBean>{

    private ImageView portrait;
    private TextView question,createTime;

    public AnswerRightTpl(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public AnswerRightTpl(Context context) {
        super(context);
    }

    @Override
    protected void initView() {
        portrait= (ImageView) findViewById(R.id.iv_port);
        question= (TextView) findViewById(R.id.tv_cont);
        createTime= (TextView) findViewById(R.id.tv_time);
        question.setTextColor(getResources().getColor(R.color.white));
        createTime.setTextColor(getResources().getColor(R.color.white));
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_answer_right_tpl;
    }

    @Override
    public void setBean(AnswerMulBean bean, int position) {
        if(bean.getItemViewType()==2){
            if (bean != null) {
                ac.setImage(portrait, bean.botBean.getPortrait());
                question.setText(bean.botBean.getContent());
                createTime.setText(bean.botBean.getCreateTime());
            }
        }
    }
}
