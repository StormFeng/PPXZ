package com.midian.ppaddress.itemtpl;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.midian.ppaddress.R;
import com.midian.ppaddress.bean.TradingInListBean;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import midian.baselib.view.BaseTpl;

public class MyRecommendF_1Tpl extends BaseTpl<TradingInListBean> implements View.OnClickListener{
    private int flag=0;
    private View ll_Look;
    private ImageView iv_Arrow;
    private Animation mAnimEnter,mAnimExit;
    private ImageView iv_title;
    private TextView tv_name,tv_booknumber,tv_intention;
    private ImageView iv_line1,iv_line2,iv_line3,iv_dot1,iv_dot2,iv_dot3,iv_dot4;
    private TextView tv_1,tv_2,tv_3,tv_4;
    private TextView tv_time1,tv_time2,tv_time3,tv_time4;

    public MyRecommendF_1Tpl(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyRecommendF_1Tpl(Context context) {
        super(context);
    }

    @Override
    protected void initView() {
        ll_Look=findView(R.id.ll_look);
        iv_title=findView(R.id.iv_title);
        tv_name=findView(R.id.tv_name);
        tv_booknumber=findView(R.id.tv_booknumber);
        tv_intention=findView(R.id.tv_state);
        iv_Arrow= (ImageView) findViewById(R.id.iv_arrow);
        iv_line1=findView(R.id.iv_line1);
        iv_line2=findView(R.id.iv_line2);
        iv_line3=findView(R.id.iv_line3);
        iv_dot1=findView(R.id.icon_dot1);
        iv_dot2=findView(R.id.icon_dot2);
        iv_dot3=findView(R.id.icon_dot3);
        iv_dot4=findView(R.id.icon_dot4);
        tv_1=findView(R.id.tv_1);
        tv_2=findView(R.id.tv_2);
        tv_3=findView(R.id.tv_3);
        tv_4=findView(R.id.tv_4);
        tv_time1=findView(R.id.tv_time1);
        tv_time2=findView(R.id.tv_time2);
        tv_time3=findView(R.id.tv_time3);
        tv_time4=findView(R.id.tv_time4);
        findView(R.id.tv_money).setVisibility(GONE);
        findView(R.id.tv_moneynumber).setVisibility(GONE);
        findView(R.id.tv_dec).setVisibility(GONE);
        findView(R.id.tv_time).setVisibility(GONE);
        ll_Look.setVisibility(VISIBLE);
        mAnimEnter= AnimationUtils.loadAnimation(_activity,R.anim.anim_enter);
        mAnimEnter.setDuration(1000);
        mAnimExit=AnimationUtils.loadAnimation(_activity,R.anim.anim_exit);
        mAnimExit.setDuration(1000);
        findView(R.id.ll_item).setOnClickListener(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.item_myrecommend;
    }

    @Override
    public void setBean(TradingInListBean bean, int position) {
        if(bean.isOK()){
            TradingInListBean.TradingInList list = bean.getData().getList().get(position);
            ac.setImage(iv_title, list.getPortrait());
            tv_name.setText(list.getMemberName());
            tv_booknumber.setText("订单号："+ list.getNumber());
            if ("1".equals(list.getStage())) {
                tv_intention.setText("已预约");
                tv_2.setTextColor(getResources().getColor(R.color.dp_gray));
                tv_3.setTextColor(getResources().getColor(R.color.dp_gray));
                tv_4.setTextColor(getResources().getColor(R.color.dp_gray));
                iv_dot2.setImageDrawable(getResources().getDrawable(R.drawable.icon_dot_gray));
                iv_dot3.setImageDrawable(getResources().getDrawable(R.drawable.icon_dot_gray));
                iv_dot4.setImageDrawable(getResources().getDrawable(R.drawable.icon_dot_gray));
                iv_line1.setImageResource(R.color.dp_gray);
                iv_line2.setImageResource(R.color.dp_gray);
                iv_line3.setImageResource(R.color.dp_gray);
                initTime(list);
            }
            if ("2".equals(list.getStage())) {
                tv_intention.setText("已预约");
                tv_2.setTextColor(getResources().getColor(R.color.dp_gray));
                tv_3.setTextColor(getResources().getColor(R.color.dp_gray));
                tv_4.setTextColor(getResources().getColor(R.color.dp_gray));
                iv_dot2.setImageDrawable(getResources().getDrawable(R.drawable.icon_dot_gray));
                iv_dot3.setImageDrawable(getResources().getDrawable(R.drawable.icon_dot_gray));
                iv_dot4.setImageDrawable(getResources().getDrawable(R.drawable.icon_dot_gray));
                iv_line1.setImageResource(R.color.dp_gray);
                iv_line2.setImageResource(R.color.dp_gray);
                iv_line3.setImageResource(R.color.dp_gray);
                initTime(list);
            }
            if ("3".equals(list.getStage())) {
                tv_intention.setText("已考察");
                tv_3.setTextColor(getResources().getColor(R.color.dp_gray));
                tv_4.setTextColor(getResources().getColor(R.color.dp_gray));
                iv_dot3.setImageDrawable(getResources().getDrawable(R.drawable.icon_dot_gray));
                iv_dot4.setImageDrawable(getResources().getDrawable(R.drawable.icon_dot_gray));
                iv_line2.setImageResource(R.color.dp_gray);
                iv_line3.setImageResource(R.color.dp_gray);
                initTime(list);
            }
            if ("4".equals(list.getStage())) {
                tv_intention.setText("意向同意");
                tv_4.setTextColor(getResources().getColor(R.color.dp_gray));
                iv_dot4.setImageDrawable(getResources().getDrawable(R.drawable.icon_dot_gray));
                iv_line3.setImageResource(R.color.dp_gray);
                initTime(list);
            }
            if (Integer.valueOf(list.getStage()).intValue()>=5) {
                tv_intention.setText("已签约");
                initTime(list);
            }
        }
    }

    private Date toDate(String strDate) throws Exception{
        DateFormat date=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return date.parse(strDate);
    }
    private String toStrDate(Date date) throws Exception{
        SimpleDateFormat formatter = new SimpleDateFormat("MM-dd HH:mm");
        String dateString = formatter.format(date);
        return dateString;
    }
    private void initTime(TradingInListBean.TradingInList list) {
        int max=0;
        for(TradingInListBean.StageList item : list.getStageList()){
            try {
                String returnDate=toStrDate(toDate(item.getUpdateTime()));
                if("1".equals(item.getStage())){
                    tv_time1.setText(returnDate);
                }
                if("2".equals(item.getStage())){
                    tv_time1.setText(returnDate);
                }
                if("3".equals(item.getStage())){
                    tv_time2.setText(returnDate);
                }
                if("4".equals(item.getStage())){
                    tv_time3.setText(returnDate);
                }
                if(Integer.valueOf(item.getStage()).intValue()>=5) {
                    if (max < Integer.valueOf(item.getStage()).intValue()) {
                        max = Integer.valueOf(item.getStage()).intValue();
                        tv_time4.setText(returnDate);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.ll_item:
                if(flag==0){
                    findView(R.id.ll_state).setVisibility(VISIBLE);
                    iv_Arrow.setImageDrawable(getResources().getDrawable(R.drawable.icon_arrow_up));
                    flag=1;
                }else {
                    findView(R.id.ll_state).setVisibility(GONE);
                    iv_Arrow.setImageDrawable(getResources().getDrawable(R.drawable.icon_arrow_down));
                    flag = 0;
                }
        }
    }
}
