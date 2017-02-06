package com.midian.ppaddress.itemtpl;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.midian.login.view.LoginActivity;
import com.midian.ppaddress.R;
import com.midian.ppaddress.bean.BusinessCarrierDetailShowBean.IntroImagesList;
import com.midian.ppaddress.bean.DetailMultiItemBean;
import com.midian.ppaddress.bean.MemberFavoriteCarrierBean;
import com.midian.ppaddress.ui.chooseaddres.ApplyEnterActivity;
import com.midian.ppaddress.ui.chooseaddres.CarrierMapLocationActivity;
import com.midian.ppaddress.ui.chooseaddres.ChooseAddressActivity;
import com.midian.ppaddress.ui.chooseaddres.ConsultantActivity;
import com.midian.ppaddress.ui.chooseaddres.MakeAppointmentActivity;
import com.midian.ppaddress.ui.chooseaddres.MultiDetailActivity;
import com.midian.ppaddress.ui.chooseaddres.ThirdViewActivity;
import com.midian.ppaddress.utils.AppUtil;
import com.midian.ppaddress.utils.ShareDialogUtil;
import com.midian.ppaddress.widget.Banner;
import java.util.ArrayList;
import java.util.List;
import midian.baselib.api.ApiCallback;
import midian.baselib.bean.NetResult;
import midian.baselib.utils.FDDataUtils;
import midian.baselib.utils.TDevice;
import midian.baselib.utils.UIHelper;
import midian.baselib.view.BaseTpl;
import midian.baselib.widget.CircleImageView;
import midian.baselib.widget.FlowLayout;

/**
 * Created by chu on 2016/4/22.
 */
public class DetailHeadTpl extends BaseTpl<DetailMultiItemBean> implements View.OnClickListener, ApiCallback {
    private View consultant_ll, bounty_ll;
    private LinearLayout liner_list;//驻守物业顾问
    private Banner bannerView;//顶部轮播图
    private ImageView share_iv, loc_iv;
    private TextView price_tv, type_tv, mianji_tv, address_tv, look_tv, reward_tv;
    private FlowLayout lable_layout;//标签流布局
    private String shareurl, sharetxt, sharetitle, shareimg;
    private List<IntroImagesList> banners = new ArrayList<IntroImagesList>();
    private String carrierid, carrierType;
    private TextView saleRental_tv;
    private TextView choose_tv;
    private ImageView coolect_iv;
    private TextView coolect_tv;
    private boolean isCollect;//是否收藏
    private DetailMultiItemBean bean;
    private String returnarrierid;//收藏成功之后返回的carrierid；


    public DetailHeadTpl(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public DetailHeadTpl(Context context) {
        super(context);
    }

    @Override
    protected void initView() {
        consultant_ll = findView(R.id.consultant_ll);
        liner_list = findView(R.id.liner_list);
        bounty_ll = findView(R.id.bounty_ll);//赏金
        bannerView= (Banner) findViewById(R.id.bannerView);
        bannerView.setBannerStyle(Banner.NUM_INDICATOR);//数字指示器
        bannerView.isAutoPlay(true);
        bannerView.setDelayTime(5000);//设置轮播间隔时间
        share_iv = findView(R.id.share);
        price_tv = findView(R.id.price_tv);
        type_tv = findView(R.id.type_tv);
        mianji_tv = findView(R.id.mianji_tv);
        address_tv = findView(R.id.address_tv);
        look_tv = findView(R.id.look_tv);//最近浏览人数
        reward_tv = findView(R.id.reward_tv);
        loc_iv = findView(R.id.loc_iv);
        lable_layout = findView(R.id.lable_layout);
        saleRental_tv = findView(R.id.saleRental_tv);

        _activity.findViewById(R.id.collect_ll).setOnClickListener(this);//收藏
        _activity.findViewById(R.id.live_ll).setOnClickListener(this);//实景

        choose_tv = (TextView) _activity.findViewById(R.id.choose_tv);//进入选址、申请驻守、一键预约
        coolect_iv = (ImageView) _activity.findViewById(R.id.coolect_iv);
        coolect_tv = (TextView) _activity.findViewById(R.id.coolect_tv);

        choose_tv.setOnClickListener(this);
        share_iv.setOnClickListener(this);
        loc_iv.setOnClickListener(this);
        consultant_ll.setOnClickListener(this);

    }

    @Override
    protected int getLayoutId() {
        return R.layout.item_multi_detail_tpl;
    }

    @Override
    public void setBean(DetailMultiItemBean bean, int position) {
        this.bean = bean;
        if (bean.getItemViewType() == 0) {
            ArrayList<String> images=new ArrayList<>();
            for(int i=0;i<bean.detailData.getIntroImages().size();i++){
                images.add(bean.detailData.getIntroImages().get(i).getConver());
            }
            bannerView.setImages(images.toArray());
            carrierid = bean.detailData.getCarrierid();
            carrierType = bean.detailData.getCarrierType();
            if ("1".equals(carrierType)) {
                type_tv.setText("园区");
            } else if ("2".equals(carrierType)) {
                type_tv.setText("综合体");
            } else if ("3".equals(carrierType)) {
                type_tv.setText("土地");
            } else if ("4".equals(carrierType)) {
                type_tv.setText("写字楼");
            } else if ("5".equals(carrierType)) {
                type_tv.setText("写字楼单元");
            } else if ("6".equals(carrierType)) {
                type_tv.setText("厂房");
            } else if ("8".equals(carrierType)) {
                type_tv.setText("仓库");
            }

            //租售方式，0为租售，1为租，2为售
            if ("0".equals(bean.detailData.getSaleRental()) || "1".equals(bean.detailData.getSaleRental())) {
                saleRental_tv.setText("出租");
                if ("3".equals(carrierType)) {
                    price_tv.setText(FDDataUtils.addComma(bean.detailData.getPriceRent()) + "元/m²·月 起");
                    mianji_tv.setText("待租售面积：" + FDDataUtils.addComma(bean.detailData.getLandArea()) + "m²");
                } else {
                    price_tv.setText(FDDataUtils.addComma(bean.detailData.getPriceRent()) + "元/m²·月 起");
                    mianji_tv.setText("待租售面积：" + FDDataUtils.addComma(bean.detailData.getBuildingArea()) + "m²");
                }
            } else if ("2".equals(bean.detailData.getSaleRental())) {//出售
                saleRental_tv.setText("出售");
                if ("3".equals(carrierType)) {
                    price_tv.setText(FDDataUtils.addComma(bean.detailData.getPriceSell()) + "元/m²");
                    mianji_tv.setText("待出售面积：" + FDDataUtils.addComma(bean.detailData.getLandArea()) + "m²");
                } else {
                    price_tv.setText(FDDataUtils.addComma(bean.detailData.getPriceSell()) + "元/m²");
                    mianji_tv.setText("待出售面积：" + FDDataUtils.addComma(bean.detailData.getBuildingArea()) + "m²");
                }
            }

            look_tv.setText("最近一周有" + bean.detailData.getBrowseCount() + "人浏览,有" + bean.detailData.getOrderCounts() + "人预约看了这个载体");

            lable_layout.removeAllViews();
            for (int i = 0; i < bean.detailData.getLabels().size(); i++) {
                String lable_id = bean.detailData.getLabels().get(i).getId();
                String key = bean.detailData.getLabels().get(i).getLabel();
                addTextView(key);
            }

            //载体类型，1为园区，2为综合体，3为土地，4为写字楼，5为写字楼单元，6为厂房，7为厂房单元，8为仓库，9为仓库单元
            /* 1.进入 综合体和园区，下面按钮都是“进入选址”；
               2.进入4写字楼，3土地，6厂房，8仓库  详情后：
               物业顾问-按钮变成“申请入驻”其他角色-按钮变成“一键预约”，
               但是只有客商身份0登录才可以点击，其他角色点击提示要切换成为客商才可以选址；*/
            if ("1".equals(carrierType) || "2".equals(carrierType)) {
                choose_tv.setText("进入选址");
            } else if ("3".equals(carrierType) || "4".equals(carrierType) || "6".equals(carrierType) || "8".equals(carrierType)) {
                if ("2".equals(ac.getProperty("user_type"))) {
                    choose_tv.setText("申请驻守");
                } else {
                    choose_tv.setText("一键预约");
                }
            }

            isCollect = bean.detailData.isCollect();//是否收藏
            System.out.println("判断载体详情是否收藏isCollect=" + isCollect);
            if (isCollect) {
                coolect_iv.setBackgroundResource(R.drawable.icon_collect_p);
                coolect_tv.setText("已收藏");
                returnarrierid = bean.detailData.getFavoriteid();
            } else {
                coolect_iv.setBackgroundResource(R.drawable.icon_collect_n);
                coolect_tv.setText("收藏");
            }


            //角色类型--0为客商，1为客商中介，2为物业顾问，3为业主，4为服务达人
            System.out.println("时入载体详情时ac.user_type=" + ac.getProperty("user_type"));
//            String carrier_type = bean.detailData.getCarrierType();//载体类型
            //（reward, counselor , parent）土地3、写字楼4、厂房6、仓库8、特有的
            if ("3".equals(carrierType) || "4".equals(carrierType) || "6".equals(carrierType) || "8".equals(carrierType)) {
                if ("1".equals(ac.getProperty("user_type")) || "2".equals(ac.getProperty("user_type"))) {//只有客商中介和物业顾问才能看到赏金，对其他角色隐藏该字段
                    bounty_ll.setVisibility(View.VISIBLE);//赏金view
                    reward_tv.setText(bean.detailData.getReward());
                } else {
                    bounty_ll.setVisibility(View.GONE);//赏金view
                }
                liner_list.removeAllViews();
                if (bean.detailData.getCounselors() == null) {
                    consultant_ll.setVisibility(View.GONE);
                } else {
                    consultant_ll.setVisibility(View.VISIBLE);//物业顾问列表
                    try {
                        for (int i = 0; i < bean.detailData.getCounselors().size(); i++) {
                            if (i < 4) {
                                CircleImageView image = (CircleImageView) View.inflate(_activity, R.layout.view_member_add, null);
                                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams((int) TDevice.dpToPixel(30f), (int) TDevice.dpToPixel(30f));
                                params.rightMargin = (int) TDevice.dpToPixel(10f);
                                if (TextUtils.isEmpty(bean.detailData.getCounselors().get(i).getPortrait())) {
                                    ac.setImage(image, R.drawable.head1);
                                } else {
                                    ac.setImage(image, bean.detailData.getCounselors().get(i).getPortrait());
                                }
                                liner_list.addView(image, params);
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            } else {
                consultant_ll.setVisibility(View.GONE);
                bounty_ll.setVisibility(View.GONE);
            }

            //分享需求字段
            sharetitle = bean.detailData.getSharetitle();
            sharetxt = bean.detailData.getSharetxt();
            shareurl = bean.detailData.getShareurl();
            shareimg = bean.detailData.getShareimg();
        }

    }

    /**
     * 把textview加入到流布局
     */
    private void addTextView(final String keyword) {
        final TextView tv = (TextView) LayoutInflater.from(_activity).inflate(R.layout.tv, lable_layout, false);
        tv.setText(keyword);
        lable_layout.addView(tv);
    }

    @Override
    public void onClick(View view) {
        Bundle mBundle = new Bundle();
        switch (view.getId()) {
            case R.id.consultant_ll://物业顾问列表
                mBundle.putString("carrierid", carrierid);
                UIHelper.jump(_activity, ConsultantActivity.class, mBundle);
                break;
            case R.id.share:
                new ShareDialogUtil(_activity).show(shareurl, sharetitle, sharetxt, shareimg);
                break;
            case R.id.loc_iv://跳转载体交通区位图
                mBundle.putString("carrierid", carrierid);
                mBundle.putString("title", bean.detailData.getCarrierName());
                UIHelper.jump(_activity, CarrierMapLocationActivity.class, mBundle);
                break;
            case R.id.collect_ll:
                //角色类型--0为客商， 只有普通用户(客商)才开放载体收藏---不能收藏单元类型载体
                //载体类型，5为写字楼单元，7为厂房单元，9为仓库单元
                isCollect = bean.detailData.isCollect();
                if ("0".equals(ac.getProperty("user_type"))) {
                    if (!isCollect && !"5".equals(carrierType) && !"7".equals(carrierType) && !"9".equals(carrierType)) {
                        AppUtil.getPpApiClient(ac).memberFavoriteCarrier(carrierid, this);//收藏接口
                    } else {
                        AppUtil.getPpApiClient(ac).cancelCollect(returnarrierid, this);//取消收藏
                    }
                }
                break;
            case R.id.live_ll:
                mBundle.putString("title", bean.detailData.getCarrierName());
                mBundle.putString("thirdView", bean.detailData.getPanoramaurl());
                UIHelper.jump(_activity, ThirdViewActivity.class, mBundle);
                break;
            case R.id.choose_tv:
                if (!ac.isUserIdExsit()) {
                    UIHelper.jump(_activity, LoginActivity.class);
                    return;
                }
                 /* 1.进入 综合体和园区，下面按钮都是“进入选址”；
                    2.进入4写字楼，3土地，6厂房，8仓库  详情后：
                    物业顾问-按钮变成“申请入驻”其他角色-按钮变成“一键预约”，
                    但是只有客商身份0登录才可以点击，其他角色点击提示要切换成为客商才可以选址；*/
                if ("进入选址".equals(choose_tv.getText().toString().trim())) {
                    if (!"0".equals(ac.getProperty("user_type"))) {
                        UIHelper.t(_activity, "请切换为普通用户进行选址");
                        return;
                    }
                    mBundle.putString("carrierid", carrierid);
                    mBundle.putString("title", bean.detailData.getCarrierName());
                    UIHelper.jump(_activity, ChooseAddressActivity.class, mBundle);//进入选址
                } else if ("申请驻守".equals(choose_tv.getText().toString().trim())) {
                    mBundle.putString("carrierid", carrierid);
                    UIHelper.jump(_activity, ApplyEnterActivity.class, mBundle);//申请入驻
                } else if ("一键预约".equals(choose_tv.getText().toString().trim())) {
                    if (!"0".equals(ac.getProperty("user_type"))) {
                        UIHelper.t(_activity, "只有客商身份才能操作");
                        return;
                    } else {
                        if (carrierid == null && carrierid.equals("")) {
                            UIHelper.t(_activity, "载体id为空");
                            return;
                        } else {
                            System.out.println("一键预约跳转carrierid为空" + carrierid);
                            mBundle.putString("carrierid", carrierid);
                            UIHelper.jump(_activity, MakeAppointmentActivity.class, mBundle);//载体详情》》》一键预约
                        }
                    }
                }
                break;
        }
    }

    @Override
    public void onApiStart(String tag) {
        ((MultiDetailActivity) _activity).showLoadingDlg();
    }

    @Override
    public void onApiLoading(long count, long current, String tag) {

    }

    @Override
    public void onApiSuccess(NetResult res, String tag) {
        ((MultiDetailActivity) _activity).hideLoadingDlg();
        if (res.isOK()) {
            if ("memberFavoriteCarrier".equals(tag)) {//收藏成功
                MemberFavoriteCarrierBean b = (MemberFavoriteCarrierBean) res;
                UIHelper.t(_activity, res.message);
                bean.detailData.setCollect(true);
                coolect_iv.setBackgroundResource(R.drawable.icon_collect_p);
                coolect_tv.setText("已收藏");
                returnarrierid = b.getData().getId();
            }
            if ("cancelCollect".equals(tag)) {//取消收藏
                UIHelper.t(_activity, res.message);
                bean.detailData.setCollect(false);
                coolect_iv.setBackgroundResource(R.drawable.icon_collect_n);
                coolect_tv.setText("收藏");
            }
        } else {
            ac.handleErrorCode(_activity, res.errorcode);
        }
    }


    @Override
    public void onApiFailure(Throwable t, int errorNo, String strMsg, String tag) {
        ((MultiDetailActivity) _activity).hideLoadingDlg();
    }

    @Override
    public void onParseError(String tag) {
    }

}
