package com.midian.ppaddress.itemtpl;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.midian.ppaddress.R;
import com.midian.ppaddress.bean.IndexMultiMemberPageNewstBean;
import com.midian.ppaddress.bean.MemberOrderCounselorPageBean;
import com.midian.ppaddress.bean.MemberOrderMemberPageNewestBean;
import com.midian.ppaddress.ui.personal.AllBookingActivityCounselor;
import com.midian.ppaddress.ui.personal.AllBookingActivityMember;
import com.midian.ppaddress.utils.AppUtil;

import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

import midian.baselib.api.ApiCallback;
import midian.baselib.bean.NetResult;
import midian.baselib.utils.UIHelper;
import midian.baselib.view.BaseTpl;

public class BookingToptpl extends BaseTpl<IndexMultiMemberPageNewstBean> implements View.OnClickListener {

    private ImageView iv_pic1,iv_pic2,iv_pic3,iv_pic4,iv_pic5;

    private TextView tv_date,tv_month,tv_week;
    private int mMonth,mDay,mWeek;
    private TextView tv_Count;
    public static TextView tv_book_count;


    public BookingToptpl(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public BookingToptpl(Context context) {
        super(context);
    }

    @Override
    protected void initView() {
        iv_pic1=findView(R.id.iv_pic1);
        iv_pic2=findView(R.id.iv_pic2);
        iv_pic3=findView(R.id.iv_pic3);
        iv_pic4=findView(R.id.iv_pic4);
        iv_pic5=findView(R.id.iv_pic5);
        tv_date=findView(R.id.tv_date);
        tv_month=findView(R.id.tv_month);
        tv_week=findView(R.id.tv_week);
        tv_Count=findView(R.id.tv_count);
        tv_book_count=findView(R.id.tv_book_count);
        Calendar c=Calendar.getInstance();
        c.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
        mMonth=c.get(Calendar.MONTH)+1;
        mDay=c.get(Calendar.DAY_OF_MONTH);
        mWeek=c.get(Calendar.DAY_OF_WEEK);
        tv_month.setText(mMonth+"月");
        tv_date.setText(mDay+"");
        if(mWeek==1){
            tv_week.setText("周日");
        }else if(mWeek==2){
            tv_week.setText("周一");
        }else if(mWeek==3){
            tv_week.setText("周二");
        }else if(mWeek==4){
            tv_week.setText("周三");
        }else if(mWeek==5){
            tv_week.setText("周四");
        }else if(mWeek==6){
            tv_week.setText("周五");
        }else if(mWeek==7){
            tv_week.setText("周六");
        }
        findView(R.id.recentlyBooked_ll).setOnClickListener(this);
//        AppUtil.getPpApiClient(ac).memberOrderMemberPageNewest(ac.user_id,null,1,10,this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_book_top;
    }

    @Override
    public void setBean(IndexMultiMemberPageNewstBean bean, int position) {
        if(bean!=null){
            if (bean.getItemViewType() == 0) {
                List<MemberOrderMemberPageNewestBean.Images> list = bean.topbean.getData().getImages();
                String count = bean.topbean.getData().getQueueCount();
                tv_Count.setText(count);
                if("0".equals(count)){
                    tv_book_count.setVisibility(View.GONE);
                }else{
                    tv_book_count.setVisibility(View.VISIBLE);
                }
                try {
                    ac.setImage(iv_pic1, list.get(0).getUrl());
                    ac.setImage(iv_pic2, list.get(1).getUrl());
                    ac.setImage(iv_pic3, list.get(2).getUrl());
                    ac.setImage(iv_pic4, list.get(3).getUrl());
                    ac.setImage(iv_pic5, list.get(4).getUrl());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (iv_pic1 == null) {
                    iv_pic1.setVisibility(GONE);
                }
                if (iv_pic2 == null) {
                    iv_pic2.setVisibility(GONE);
                }
                if (iv_pic3 == null) {
                    iv_pic3.setVisibility(GONE);
                }
                if (iv_pic4 == null) {
                    iv_pic4.setVisibility(GONE);
                }
                if (iv_pic5 == null) {
                    iv_pic5.setVisibility(GONE);
                }
            }
        }
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.recentlyBooked_ll:
                UIHelper.jumpForResult(_activity, AllBookingActivityMember.class,1001);
//                UIHelper.jump(_activity, RecentlyBookedActivity.class);
                break;
        }
    }
}
