package com.midian.ppaddress.itemtpl;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.midian.ppaddress.R;
import com.midian.ppaddress.bean.EntercarrierPageRentsBean;
import com.midian.ppaddress.ui.personal.LeaseStateActivity;

import midian.baselib.bean.NetResult;
import midian.baselib.utils.UIHelper;
import midian.baselib.view.BaseTpl;

/**
 * 详情-评论条目
 * Created by chu on 2016/4/25.
 */
public class LeaseStateTpl extends BaseTpl<EntercarrierPageRentsBean> implements View.OnClickListener{

    private ImageView iv_portrait_1,iv_portrait_2;
    private TextView tv_name_1,tv_name_2,tv_id_1,tv_id_2,tv_stage,tv_time;

    public LeaseStateTpl(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public LeaseStateTpl(Context context) {
        super(context);
    }

    @Override
    protected void initView() {
        iv_portrait_1=findView(R.id.iv_portrait_1);
        iv_portrait_2=findView(R.id.iv_portrait_2);
        tv_name_1=findView(R.id.tv_name_1);
        tv_name_2=findView(R.id.tv_name_2);
        tv_id_2=findView(R.id.tv_id_2);
        tv_id_1=findView(R.id.tv_id_1);
        tv_stage=findView(R.id.tv_stage);
        tv_time=findView(R.id.tv_time);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.item_leasestate;
    }

    @Override
    public void setBean(EntercarrierPageRentsBean bean, int position) {
        if(bean.isOK()){
            EntercarrierPageRentsBean.RentsList list = bean.getData().getRents().getList().get(position);
            ac.setImage(iv_portrait_1,list.getCounselorImage());
            ac.setImage(iv_portrait_2,list.getMemberImage());
            tv_name_1.setText("经纪："+list.getFullname());
            tv_name_2.setText("客商："+list.getNickname());
            tv_id_1.setText("ID:  "+list.getCounselorid());
            tv_id_2.setText("ID:  "+list.getMemberid());
            tv_time.setText(list.getCreateTime());
            if("0".equals(list.getStage()))
                tv_stage.setText("取消预约");
            if("1".equals(list.getStage()))
                tv_stage.setText("预约");
            if("2".equals(list.getStage()))
                tv_stage.setText("已预约");
            if("3".equals(list.getStage()))
                tv_stage.setText("已考察");
            if("4".equals(list.getStage()))
                tv_stage.setText("意向同意");
            if("5".equals(list.getStage()))
                tv_stage.setText("已签约");
            if("6".equals(list.getStage()))
                tv_stage.setText("已上传合同");
            if("7".equals(list.getStage()))
                tv_stage.setText("待审核合同");
            if("8".equals(list.getStage()))
                tv_stage.setText("审核不通过合同");
            if("9".equals(list.getStage()))
                tv_stage.setText("审核通过合同/待付款到平台");
            if("10".equals(list.getStage()))
                tv_stage.setText("付款到平台/待打赏");
            if("11".equals(list.getStage()))
                tv_stage.setText("已打赏");
        }
    }


    @Override
    public void onClick(View view){
        switch (view.getId()){
        }
    }
}
