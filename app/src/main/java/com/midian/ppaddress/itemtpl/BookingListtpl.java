package com.midian.ppaddress.itemtpl;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.text.LoginFilter;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.midian.ppaddress.R;
import com.midian.ppaddress.bean.BookPageBean;
import com.midian.ppaddress.bean.BusinessInfrasListOrderStageBean;
import com.midian.ppaddress.bean.IndexMultiMemberPageNewstBean;
import com.midian.ppaddress.bean.MemberOrderMemberPageBean;
import com.midian.ppaddress.bean.MemberOrderMemberPageNewestBean;
import com.midian.ppaddress.bean.UpdateStageBean;
import com.midian.ppaddress.ui.chooseaddres.MultiDetailActivity;
import com.midian.ppaddress.ui.chooseaddres.ParkDetailActivity;
import com.midian.ppaddress.utils.AppUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import midian.baselib.api.ApiCallback;
import midian.baselib.bean.NetResult;
import midian.baselib.utils.FDDataUtils;
import midian.baselib.utils.UIHelper;
import midian.baselib.view.BaseTpl;
import midian.baselib.widget.basepopup.BasePopupWindow;
import midian.baselib.widget.wheelview.WLQQTimePicker;

public class BookingListtpl extends BaseTpl<IndexMultiMemberPageNewstBean> implements View.OnClickListener{

    private TextView tv_createTime,tv_intention,tv_name,tv_type,tv_price,tv_area,tv_startTime,
        tv_position,tv_fullName,tv_number,tv_saleType;
    private ImageView iv_Image,iv_portrait;
    private Button btn_1,btn_2;//取消预约，已考察 按钮
    private ImageView iv_dot1,iv_dot2,iv_dot3,iv_dot4,iv_line1,iv_line2,iv_line3;
    private String stage;//订单阶段
    private String orderid;//预约id;
    private String time,position;
    private String carriertype,carriertitle,carrierid;
    private MenuPopup menuPopup;
    private int flag;//flag==0,dialog正常显示，flag==1,表示底部空间不足显示dialog，dialog在view上边显示
    private int[] viewLocation;

    private TextView tv_isread;

    public BookingListtpl(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    public BookingListtpl(Context context) {
        super(context);
    }

    @Override
    protected void initView() {
        tv_createTime=findView(R.id.tv_booknumber);
        tv_intention=findView(R.id.tv_intention);
        tv_type=findView(R.id.tv_type);
        tv_price=findView(R.id.tv_price);
        tv_area=findView(R.id.tv_area);
        tv_name=findView(R.id.tv_name);
        tv_startTime=findView(R.id.tv_time);
        tv_position=findView(R.id.tv_position);
        tv_fullName=findView(R.id.tv_fullname);
        tv_number=findView(R.id.tv_number);
        tv_saleType=findView(R.id.saleType);
        iv_Image=findView(R.id.iv_pic);
        iv_portrait=findView(R.id.iv_portrait);
        tv_isread=findView(R.id.tv_isread);
        findView(R.id.ll_statechange).setOnClickListener(this);
        findView(R.id.ll_item).setOnClickListener(this);
        findView(R.id.ll_number).setOnClickListener(this);

        viewLocation=new int[2];
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_book_list;
    }

    @Override
    public void setBean(IndexMultiMemberPageNewstBean bean, int position) {
        if (bean!=null) {
            if (bean.getItemViewType() == 1) {
                if("0".equals(bean.botbeans.getIsread())){
                    tv_isread.setVisibility(VISIBLE);
                }else{
                    tv_isread.setVisibility(GONE);
                }
                tv_createTime.setText(bean.botbeans.getCreateTime());
                if ("1".equals(bean.botbeans.getStage())) {
                    tv_intention.setText("预约");
                    findViewById(R.id.ll_statechange).setVisibility(VISIBLE);
                }
                if ("2".equals(bean.botbeans.getStage())) {
                    tv_intention.setText("已预约");
                    findViewById(R.id.ll_statechange).setVisibility(VISIBLE);
                }
                if ("3".equals(bean.botbeans.getStage())) {
                    tv_intention.setText("已考察");
                    findViewById(R.id.ll_statechange).setVisibility(GONE);
                }
                if ("4".equals(bean.botbeans.getStage())) {
                    tv_intention.setText("意向同意");
                    findViewById(R.id.ll_statechange).setVisibility(GONE);
                }
                if (Integer.valueOf(bean.botbeans.getStage()).intValue()>=5) {
                    tv_intention.setText("已签约");
                    findViewById(R.id.ll_statechange).setVisibility(GONE);
                }

                tv_name.setText(bean.botbeans.getCarrierName());
                if ("1".equals(bean.botbeans.getCarrierType())) {
                    tv_saleType.setVisibility(GONE);
                    tv_type.setText("园区");
                }
                if ("2".equals(bean.botbeans.getCarrierType())) {
                    tv_saleType.setVisibility(GONE);
                    tv_type.setText("综合体");
                }
                if ("3".equals(bean.botbeans.getCarrierType())) {
                    tv_type.setText("土地");
                    tv_saleType.setVisibility(GONE);
                    tv_price.setText(bean.botbeans.getProperty());
                    tv_area.setText("土地面积  "+ FDDataUtils.addComma(bean.botbeans.getLandArea()) + "m²");
                }

                if ("4".equals(bean.botbeans.getCarrierType())) {
                    tv_type.setText("写字楼");
                    tv_saleType.setVisibility(VISIBLE);
                    if ("0".equals(bean.botbeans.getSaleRental())) {
                        tv_saleType.setText("租");
                        if ("0".equals(bean.botbeans.getPriceRent())) {
                            tv_price.setText("面议");
                        } else {
                            tv_price.setText(bean.botbeans.getPriceRent() + "元/m²·月 起");
                        }
                    }
                    if ("1".equals(bean.botbeans.getSaleRental())) {
                        tv_saleType.setText("租");
                        if ("0".equals(bean.botbeans.getPriceRent())) {
                            tv_price.setText("面议");
                        } else {
                            tv_price.setText(bean.botbeans.getPriceRent() + "元/m²·月 起");
                        }
                    }
                    if ("2".equals(bean.botbeans.getSaleRental())) {
                        tv_saleType.setText("售");
                        if ("0".equals(bean.botbeans.getPriceRent())) {
                            tv_price.setText("面议");
                        } else {
                            tv_price.setText(bean.botbeans.getPriceSell() + "元/m² 起");
                        }
                    }
                    tv_area.setText("待租售面积  " + FDDataUtils.addComma(bean.botbeans.getBuildingArea()) + "m²");
                }
                if ("6".equals(bean.botbeans.getCarrierType())) {
                    tv_saleType.setVisibility(GONE);
                    tv_type.setText("厂房");
                    tv_area.setText("待租售面积  " + FDDataUtils.addComma(bean.botbeans.getBuildingArea()) + "m²");
                    tv_price.setText(bean.botbeans.getFloor() + "层");
                }
                if ("8".equals(bean.botbeans.getCarrierType())) {
                    tv_saleType.setVisibility(GONE);
                    tv_type.setText("仓库");
                    tv_area.setText("待租售面积  " + FDDataUtils.addComma(bean.botbeans.getBuildingArea()) + "m²");
                    tv_price.setText(bean.botbeans.getFloor() + "层");
                }
                tv_startTime.setText(bean.botbeans.getStartTime());
                tv_position.setText(bean.botbeans.getContactLocat());
                tv_fullName.setText(bean.botbeans.getFullname());
                tv_number.setText(bean.botbeans.getMobilephone());
                ac.setImage(iv_Image, bean.botbeans.getImages());
                ac.setImage(iv_portrait, bean.botbeans.getPortrait());
                carriertitle=bean.botbeans.getCarrierName();
                carriertype=bean.botbeans.getCarrierType();
                carrierid=bean.botbeans.getCarrierid();
                stage=bean.botbeans.getStage();
                orderid=bean.botbeans.getId();

            }
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.ll_item:
                Bundle bundle=new Bundle();
                bundle.putString("carriertitle",carriertitle);
                bundle.putString("carriertype",carriertype);
                bundle.putString("carrierid",carrierid);
                UIHelper.jump(_activity, ParkDetailActivity.class,bundle);
                break;
            case R.id.ll_statechange:
                _activity.getWindow().findViewById(R.id.view).setVisibility(VISIBLE);
                view.getLocationOnScreen(viewLocation);
                int height=_activity.getWindowManager().getDefaultDisplay().getHeight();
                if(height-viewLocation[1]<view.getHeight()*4){
                    flag=1;
                }else{
                    flag=0;
                }
                menuPopup = new MenuPopup(_activity);
                menuPopup.setOnDismissListener(new BasePopupWindow.OnDismissListener() {
                    @Override
                    public void onDismiss() {
                        _activity.getWindow().findViewById(R.id.view).setVisibility(GONE);
                    }
                });
                menuPopup.showPopupWindow(view);
                break;
            case R.id.ll_number:
                String phoneNumber = tv_number.getText().toString().trim();
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phoneNumber));
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                _activity.startActivity(intent);
                break;
        }
    }

    public class MenuPopup extends BasePopupWindow implements OnClickListener{
        private int[] viewLocation;
        private Button btn_changecontent,btn_changestate;
//        private int flag;
        public MenuPopup(Activity context) {
            super(context, ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
            viewLocation=new int[2];
            btn_changestate = (Button) mPopupView.findViewById(R.id.btn_changestate);
            btn_changecontent = (Button) mPopupView.findViewById(R.id.btn_changecontent);
            btn_changestate.setText("取消预约");
            btn_changestate.setOnClickListener(this);
            btn_changecontent.setOnClickListener(this);
        }
//        public MenuPopup(Activity context,int flag) {
//            super(context, ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
//            this.flag=flag;
//            viewLocation=new int[2];
//            btn_changestate = (Button) mPopupView.findViewById(R.id.btn_changestate);
//            btn_changecontent = (Button) mPopupView.findViewById(R.id.btn_changecontent);
//            btn_changestate.setText("取消预约");
//            btn_changestate.setOnClickListener(this);
//            btn_changecontent.setOnClickListener(this);
//            Log.d("wqf",flag+"::flag");
//        }
        @Override
        public void showPopupWindow(View v) {
            Log.d("wqf",flag+"::showPopupWindow");
            try {
                v.getLocationOnScreen(viewLocation);
                if(flag==0) {
                    mPopupWindow.showAtLocation(v, Gravity.RIGHT | Gravity.TOP, (int) (v.getWidth() * 0.5),
                            viewLocation[1] + ((v.getHeight() * 3 / 4)));
                }else if(flag==1){
                    mPopupWindow.showAtLocation(v, Gravity.RIGHT | Gravity.TOP, (int) (v.getWidth() * 0.5),
                            viewLocation[1]-((v.getHeight()*5/2)));
                }
                if(getShowAnimation() != null && mAnimaView != null){
                    mAnimaView.clearAnimation();
                    mAnimaView.startAnimation(getShowAnimation());
                }
                if (getShowAnimation() == null && getShowAnimator() != null && mAnimaView != null) {
                    getShowAnimator().start();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override
        protected Animation getShowAnimation() {
            AnimationSet set=new AnimationSet(true);
            set.setInterpolator(new DecelerateInterpolator());
            set.addAnimation(getScaleAnimation(0,1,0,1,Animation.RELATIVE_TO_SELF,1,Animation.RELATIVE_TO_SELF,0));
            set.addAnimation(getDefaultAlphaAnimation());
            return set;
        }
        @Override
        protected View getClickToDismissView() {
            return null;
        }

        /**
         * 得到popupwindow的主体，一般是在xml文件写好然后inflate出来并返回
         * @return
         */
        @Override
        public View getPopupView() {
            Log.d("wqf",flag+"::getPopupView");
            int layoutId=0;
            if(flag==0) {
                Log.d("wqf","00000000");
                layoutId=R.layout.dialog_changebook;
            }else if(flag==1) {
                Log.d("wqf","111111111");
                layoutId=R.layout.dialog_changebook_down;
            }
            return getPopupViewById(layoutId);
        }

        /**
         * 得到用于展示动画的view，一般为了做到蒙层效果
         * @return
         */
        @Override
        public View getAnimaView() {
            return null;
        }

        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.btn_changestate:
                    menuPopup.dismiss();
                    if("取消预约".equals(btn_changestate.getText().toString().trim())){
                        AppUtil.getPpApiClient(ac).memberOrderMemberCancel(orderid, new ApiCallback() {
                            @Override
                            public void onApiStart(String tag) {

                            }

                            @Override
                            public void onApiLoading(long count, long current, String tag) {

                            }

                            @Override
                            public void onApiSuccess(NetResult res, String tag) {
                                UIHelper.t(_activity,"取消预约成功");
                                listViewHelper.refresh();
                            }

                            @Override
                            public void onApiFailure(Throwable t, int errorNo, String strMsg, String tag) {

                            }

                            @Override
                            public void onParseError(String tag) {

                            }
                        });
                    }
                    break;
                case R.id.btn_changecontent:
                    menuPopup.dismiss();
                    final Dialog dialog1=new Dialog(_activity,R.style.add_dialog);
                    Window window=dialog1.getWindow();
                    dialog1.requestWindowFeature(Window.FEATURE_NO_TITLE);
                    dialog1.setContentView(R.layout.dialog_choose_time_position);
                    dialog1.show();
                    final TextView tv_time = (TextView) window.findViewById(R.id.tv_time);
                    final EditText et_position = (EditText) window.findViewById(R.id.et_position);
                    tv_time.setOnClickListener(new OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            final Dialog dialog=new Dialog(_activity,R.style.add_dialog);
                            final Window window=dialog.getWindow();
                            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                            dialog.setContentView(R.layout.dialog_timepicker);
                            WLQQTimePicker timePicker= (WLQQTimePicker) window.findViewById(R.id.timepicker);
                            timePicker.setDate(new Date().getTime());
                            dialog.show();
                            window.findViewById(R.id.cancel).setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    dialog.dismiss();
                                }
                            });
                            window.findViewById(R.id.confirm).setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    TextView tv= (TextView) window.findViewById(R.id.picker_title);
                                    dialog.dismiss();
                                    String str=tv.getText().toString().trim();
                                    Date date = null;
                                    try {
                                        date=new SimpleDateFormat("yyyy年MM月dd日 HH:mm").parse(str);
                                    } catch (ParseException e) {
                                        e.printStackTrace();
                                    }
                                    SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm");
                                    String s=sdf.format(date);
//                                        UIHelper.t(_activity,s);
                                    tv_time.setText(s);
                                }
                            });
                        }
                    });
                    window.findViewById(R.id.iv_back).setOnClickListener(new OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            dialog1.dismiss();
                        }
                    });
                    window.findViewById(R.id.btn_ok).setOnClickListener(new OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            String s=tv_time.getText().toString().trim();
                            time=tv_time.getText().toString().trim()+":00";
                            position=et_position.getText().toString().trim();
                            if(TextUtils.isEmpty(s)){
                                UIHelper.t(_activity,"请设置时间");
                                return;
                            }else if(TextUtils.isEmpty(position)){
                                UIHelper.t(_activity,"请设置地点");
                                return;
                            }else{
                                dialog1.dismiss();
                                try {
                                    AppUtil.getPpApiClient(ac).memberOrderMemberUpdate(orderid, position, time, new ApiCallback() {
                                        @Override
                                        public void onApiStart(String tag) {

                                        }

                                        @Override
                                        public void onApiLoading(long count, long current, String tag) {

                                        }

                                        @Override
                                        public void onApiSuccess(NetResult res, String tag) {
                                            UIHelper.t(_activity,"修改预约信息成功");
                                            tv_startTime.setText(time);
                                            tv_position.setText(position);
                                            listViewHelper.refresh();
                                        }

                                        @Override
                                        public void onApiFailure(Throwable t, int errorNo, String strMsg, String tag) {

                                        }

                                        @Override
                                        public void onParseError(String tag) {

                                        }
                                    });
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    });
                    break;
            }
        }
    }
}
