package com.midian.ppaddress.ui.chooseaddres;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.midian.login.view.LoginActivity;
import com.midian.ppaddress.R;
import com.midian.ppaddress.bean.BusinessCarrierDetailShowBean;
import com.midian.ppaddress.bean.MemberFavoriteCarrierBean;
import com.midian.ppaddress.ui.chooseaddres.detailfragment.Detail2Fragment;
import com.midian.ppaddress.ui.chooseaddres.detailfragment.Detail4Fragment;
import com.midian.ppaddress.ui.chooseaddres.detailfragment.Detail5Fragment;
import com.midian.ppaddress.ui.chooseaddres.detailfragment.Detail6Fragment;
import com.midian.ppaddress.ui.chooseaddres.detailfragment.DetailRecommentFragment;
import com.midian.ppaddress.ui.chooseaddres.detailfragment.DetailUnitFragment;
import com.midian.ppaddress.utils.AppUtil;
import com.midian.ppaddress.utils.ShareDialogUtil;
import com.midian.ppaddress.widget.Banner;

import java.util.ArrayList;
import java.util.List;

import midian.baselib.base.BaseFragmentActivity;
import midian.baselib.bean.NetResult;
import midian.baselib.utils.FDDataUtils;
import midian.baselib.utils.TDevice;
import midian.baselib.utils.UIHelper;
import midian.baselib.widget.BaseLibTopbarView;
import midian.baselib.widget.CircleImageView;
import midian.baselib.widget.FlowLayout;

/**
 //                            _ooOoo_
 //                           o8888888o
 //                           88" . "88
 //                           (| -_- |)
 //                            O\ = /O
 //                        ____/`---'\____
 //                      .   ' \\| |// `.
 //                       / \\||| : |||// \
 //                     / _||||| -:- |||||- \
 //                       | | \\\ - /// | |
 //                     | \_| ''\---/'' | |
 //                      \ .-\__ `-` ___/-. /
 //                   ___`. .' /--.--\ `. . __
 //                ."" '< `.___\_<|>_/___.' >'"".
 //               | | : `- \`.;`\ _ /`;.`/ - ` : | |
 //                 \ \ `-. \_ __\ /__ _/ .-` / /
 //         ======`-.____`-.___\_____/___.-`____.-'======
 //                            `=---='
 //         .............................................
 //                  佛祖保佑             永无BUG
 ------
 * 园区详情
 * Created by chu on 2016/3/19.
 */
public class ParkDetailActivity extends BaseFragmentActivity implements View.OnClickListener {
    private BaseLibTopbarView topbar;
    private View consultant_ll, bounty_ll;
    private LinearLayout liner_list;//驻守物业顾问
    private Banner bannerView;
    private ImageView share_iv, loc_iv;
    private TextView price_tv, type_tv, mianji_tv, address_tv, look_tv, reward_tv;
    private FlowLayout lable_layout;//标签流布局
    private String shareurl, sharetxt, sharetitle, shareimg;
    private List<BusinessCarrierDetailShowBean.IntroImagesList> banners = new ArrayList<BusinessCarrierDetailShowBean.IntroImagesList>();
    private TextView saleRental_tv,price_unit,unit_tv;
    private TextView choose_tv,coolect_tv;
    private ImageView coolect_iv;
    private boolean isCollect;//是否收藏
    private String returnarrierid;//收藏成功之后返回的carrierid；
    private BusinessCarrierDetailShowBean bean;

    private View belongs_rl, belongs_view;
    private ImageView belongs_iv;
    private TextView belongsTitle_tv, belongs_tv, belongsType_tv;


    private DetailRecommentFragment f1;
    private Detail2Fragment f2;
    private DetailUnitFragment f3;

    private Detail4Fragment f4;
    private Detail5Fragment f5;
    private Detail6Fragment f6;

    private TextView tab1, tab2, tab3, tab4, tab5, tab6;
    private Fragment mFragment;
    private Fragment mFragment1;
    private View more_tv;
    private String title, carrierType, carrierid;
    private int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_park_detail);
        title = mBundle.getString("carriertitle");
        carrierType = mBundle.getString("carriertype");
        Log.d("-=-=-=-", "onCreate: 详情页的type="+carrierType);
        carrierid = mBundle.getString("carrierid");
        topbar = findView(R.id.topbar);
        topbar.setTitle(title).setLeftImageButton(R.drawable.icon_back, UIHelper.finish(_activity)).setRightText("对比", this);

        initTopView();//初始化头部View
        initTabFragmentView();//初始化fragment相关

    }

    private void initTabFragmentView() {
        tab1 = findView(R.id.tab1);
        tab2 = findView(R.id.tab2);
        tab3 = findView(R.id.tab3);
        tab4 = findView(R.id.tab4);
        tab5 = findView(R.id.tab5);
        tab6 = findView(R.id.tab6);
        more_tv = findView(R.id.more);
        more_tv.setOnClickListener(this);

        tab1.setOnClickListener(this);
        tab2.setOnClickListener(this);
        tab3.setOnClickListener(this);
        tab4.setOnClickListener(this);
        tab5.setOnClickListener(this);
        tab6.setOnClickListener(this);
        tab1.setEnabled(false);
        tab2.setEnabled(true);
        tab3.setEnabled(true);
        tab4.setEnabled(false);
        tab5.setEnabled(true);
        tab6.setEnabled(true);
        tab1.isSelected();
        tab4.isSelected();
        tab1.setTextColor(getResources().getColor(R.color.tab_p_bg));
        tab4.setTextColor(getResources().getColor(R.color.blue_tv));
        mFragment = new Fragment();
        mFragment1 = new Fragment();
        f1 = new DetailRecommentFragment();//推荐理由fragment
        f2 = new Detail2Fragment();//基本参数fragment

        if ("4".equals(carrierType) || "6".equals(carrierType) || "8".equals(carrierType)) {//写字楼详情  //厂房/仓库详情
            tab3.setVisibility(View.VISIBLE);
            f3 = new DetailUnitFragment();//可选单元
            f3.setCarrier_type(carrierType);
            f3.setCarrierid(carrierid);
        } else {
            tab3.setVisibility(View.GONE);
        }

        f4 = new Detail4Fragment();//用户点评fragment
        f5 = new Detail5Fragment();//物业顾问点评fragment
        f6 = new Detail6Fragment();//专家点评fragment
        switchFragment(mFragment, f1);
        switchFragment1(mFragment1, f4);
//        f1.setDetailData(bean.getData());
        f1.setCarrierid(carrierid);
        f2.setCarrierid(carrierid);

        f4.setCarrierid(carrierid);
        f5.setCarrierid(carrierid);
        f6.setCarrierid(carrierid);
    }

    /**
     * 初始化头部View
     */
    private void initTopView() {

        consultant_ll = findView(R.id.consultant_ll);
        liner_list = findView(R.id.liner_list);
        bounty_ll = findView(R.id.bounty_ll);//赏金
        bannerView = (Banner) findViewById(R.id.bannerView);
//        bannerView.setBannerStyle(Banner.CIRCLE_INDICATOR);//设置圆形指示器
        bannerView.setBannerStyle(Banner.NUM_INDICATOR);//数字指示器
//        bannerView.setIndicatorGravity(Banner.CENTER);
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
        price_unit = findView(R.id.price_unit);
        unit_tv = findView(R.id.unit_tv);
        belongs_rl = findView(R.id.belongs_rl);
        belongs_view = findView(R.id.belongs_view);
        belongs_iv = findView(R.id.belongs_iv);
        belongsTitle_tv = findView(R.id.belongsTitle_tv);
        belongs_tv = findView(R.id.belongs_tv);
        belongsType_tv = findView(R.id.belongsType_tv);

        findViewById(R.id.collect_ll).setOnClickListener(this);//收藏
        findViewById(R.id.live_ll).setOnClickListener(this);//实景

        choose_tv = (TextView) _activity.findViewById(R.id.choose_tv);//进入选址、申请驻守、一键预约
        coolect_iv = (ImageView) _activity.findViewById(R.id.coolect_iv);
        coolect_tv = (TextView) _activity.findViewById(R.id.coolect_tv);

        choose_tv.setOnClickListener(this);
        share_iv.setOnClickListener(this);
        loc_iv.setOnClickListener(this);
        consultant_ll.setOnClickListener(this);
        loadData();//载体详情接口
    }

    private void loadData() {// 载体详情接口
        AppUtil.getPpApiClient(ac).getCarrierDetailShow(carrierid, ac.user_id, ac.getProperty("user_type"), this);
    }

    @Override
    public void onApiStart(String tag) {
        super.onApiStart(tag);
//        showLoadingDlg();
        showLoadingDlg("",false);
    }

    @Override
    public void onApiSuccess(NetResult res, String tag) {
        super.onApiSuccess(res, tag);
        hideLoadingDlg();
        if (res.isOK()) {
            if ("getCarrierDetailShow".equals(tag)) {//载体详情回调
                BusinessCarrierDetailShowBean bean = (BusinessCarrierDetailShowBean) res;
                this.bean = bean;
                renderTopView();
            }
            if("memberFavoriteCarrier".equals(tag)){
                UIHelper.t(_activity,"收藏成功");
                MemberFavoriteCarrierBean bean = (MemberFavoriteCarrierBean) res;
                returnarrierid = bean.getData().getId();
                coolect_iv.setBackgroundResource(R.drawable.icon_collect_p);
                coolect_tv.setText("已收藏");
                isCollect=true;
            }
            if("cancelCollect".equals(tag)){
                UIHelper.t(_activity,"取消收藏成功");
                coolect_iv.setBackgroundResource(R.drawable.icon_collect_n);
                coolect_tv.setText("收藏");
                isCollect=false;
            }
        } else {
            ac.handleErrorCode(_activity, res.errorcode);
        }
    }

    @Override
    public void onApiFailure(Throwable t, int errorNo, String strMsg, String tag) {
        super.onApiFailure(t, errorNo, strMsg, tag);
        hideLoadingDlg();
    }

    private void renderTopView() {
        ArrayList<String> images = new ArrayList<>();
        for (int i = 0; i < bean.getData().getIntroImages().size(); i++) {
            images.add(bean.getData().getIntroImages().get(i).getConver());
        }
        bannerView.setImages(images.toArray());
        carrierid = bean.getData().getCarrierid();
        carrierType = bean.getData().getCarrierType();
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
        if ("0".equals(bean.getData().getSaleRental()) || "1".equals(bean.getData().getSaleRental())) {
            saleRental_tv.setText("出租");
            unit_tv.setText("元/m²·月");
            price_unit.setVisibility(View.VISIBLE);
            if ("3".equals(carrierType)) {
                price_tv.setText(FDDataUtils.addComma(bean.getData().getPriceRent()));
                mianji_tv.setText("待租售面积：" + FDDataUtils.addComma(bean.getData().getLandArea()) + "m²");
            } else {
                price_tv.setText(FDDataUtils.addComma(bean.getData().getPriceRent()));
                mianji_tv.setText("待租售面积：" + FDDataUtils.addComma(bean.getData().getBuildingArea()) + "m²");
            }
        } else if ("2".equals(bean.getData().getSaleRental())) {//出售
            saleRental_tv.setText("出售");
            unit_tv.setText("元/m²");
            price_unit.setVisibility(View.GONE);
            if ("3".equals(carrierType)) {
                price_tv.setText(FDDataUtils.addComma(bean.getData().getPriceSell()));
                mianji_tv.setText("待出售面积：" + FDDataUtils.addComma(bean.getData().getLandArea()) + "m²");
            } else {
                price_tv.setText(FDDataUtils.addComma(bean.getData().getPriceSell()));
                mianji_tv.setText("待出售面积：" + FDDataUtils.addComma(bean.getData().getBuildingArea()) + "m²");
            }
        }

        address_tv.setText(bean.getData().getAddress());
        look_tv.setText("最近一周有" + bean.getData().getBrowseCount() + "人浏览,有" + bean.getData().getOrderCounts() + "人预约看了这个载体");

        lable_layout.removeAllViews();
        for (int i = 0; i < bean.getData().getLabels().size(); i++) {
            String lable_id = bean.getData().getLabels().get(i).getId();
            String key = bean.getData().getLabels().get(i).getLabel();
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

        isCollect = bean.getData().isCollect();//是否收藏
        System.out.println("判断载体详情是否收藏isCollect=" + isCollect);
        if (isCollect) {
            coolect_iv.setBackgroundResource(R.drawable.icon_collect_p);
            coolect_tv.setText("已收藏");
            returnarrierid = bean.getData().getFavoriteid();
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
                reward_tv.setText(bean.getData().getReward());
            } else {
                bounty_ll.setVisibility(View.GONE);//赏金view
            }
            liner_list.removeAllViews();
            if (bean.getData().getCounselors() == null) {
                consultant_ll.setVisibility(View.GONE);
            } else {
                consultant_ll.setVisibility(View.VISIBLE);//物业顾问列表
                try {
                    for (int i = 0; i < bean.getData().getCounselors().size(); i++) {
                        if (i < 4) {
                            CircleImageView image = (CircleImageView) View.inflate(_activity, R.layout.view_member_add, null);
                            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams((int) TDevice.dpToPixel(30f), (int) TDevice.dpToPixel(30f));
                            params.rightMargin = (int) TDevice.dpToPixel(10f);
                            if (TextUtils.isEmpty(bean.getData().getCounselors().get(i).getPortrait())) {
                                ac.setImage(image, R.drawable.head1);
                            } else {
                                ac.setImage(image, bean.getData().getCounselors().get(i).getPortrait());
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


        // //载体类型，1为园区，2为综合体，3为土地，4为写字楼，5为写字楼单元，6为厂房，7为厂房单元，8为仓库，9为仓库单元
        //parent 字段(所属园区或综合体）土地、厂房、仓库、写字楼特有的
        String carrier_type = bean.getData().getCarrierType();
        if ("3".equals(carrier_type) || "4".equals(carrier_type) || "6".equals(carrier_type) || "8".equals(carrier_type)) {
            belongs_rl.setVisibility(View.VISIBLE);
            if (bean.getData().getParent() != null) {
                if (bean.getData().getParent().getImages() == null || bean.getData().getParent().getImages().equals("")) {
                    ac.setImage(belongs_iv, R.drawable.icon_bg2);
                } else {
                    ac.setImage(belongs_iv, bean.getData().getParent().getImages());
                }
                belongsTitle_tv.setText(bean.getData().getParent().getCarrierName());
                //载体类型，1为园区，2为综合体
                if ("1".equals(bean.getData().getParent().getCarrierType())) {
                    belongsType_tv.setText("园区");
                } else if ("2".equals(bean.getData().getParent().getCarrierType())) {
                    belongsType_tv.setText("综合体");
                }
            }
        } else {
            belongs_rl.setVisibility(View.GONE);
        }

        //分享需求字段
        sharetitle = bean.getData().getSharetitle();
        sharetxt = bean.getData().getSharetxt();
        shareurl = bean.getData().getShareurl();
        shareimg = bean.getData().getShareimg();
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
    public void onClick(View v) {
        Bundle mBundle = new Bundle();
        switch (v.getId()) {
            case R.id.consultant_ll://物业顾问列表
                if (TextUtils.isEmpty(carrierid)) {
                    return;
                }
                mBundle.putString("carrierid", carrierid);
                UIHelper.jump(_activity, ConsultantActivity.class, mBundle);
                break;
            case R.id.share:
                if (TextUtils.isEmpty(shareurl)) {
                    return;
                }
                new ShareDialogUtil(_activity).show(shareurl, sharetitle, sharetxt, shareimg);
                break;
            case R.id.loc_iv://跳转载体交通区位图
                if (TextUtils.isEmpty(carrierid) && TextUtils.isEmpty(title)) {
                    return;
                }
                mBundle.putString("carrierid", carrierid);
                mBundle.putString("title", title);
                UIHelper.jump(_activity, CarrierMapLocationActivity.class, mBundle);
                break;
            case R.id.collect_ll:
                //角色类型--0为客商， 只有普通用户(客商)才开放载体收藏---不能收藏单元类型载体
                //载体类型，5为写字楼单元，7为厂房单元，9为仓库单元
//                isCollect = bean.getData().isCollect();
                if ("0".equals(ac.getProperty("user_type"))) {
//                    if (!isCollect && !"5".equals(carrierType) && !"7".equals(carrierType) && !"9".equals(carrierType)) {
                    if (!isCollect) {
                        AppUtil.getPpApiClient(ac).memberFavoriteCarrier(carrierid, this);//收藏接口
                    } else {
                        AppUtil.getPpApiClient(ac).cancelCollect(returnarrierid, this);//取消收藏
                    }
                }else{
                    UIHelper.t(_activity, "只有客商身份才能操作");
                }
                break;
            case R.id.live_ll:
                if (TextUtils.isEmpty(bean.getData().getPanoramaurl())) {
                    return;
                }
                mBundle.putString("title", bean.getData().getCarrierName());
                mBundle.putString("thirdView", bean.getData().getPanoramaurl());
                UIHelper.jump(_activity, ThirdViewActivity.class, mBundle);
                break;
            case R.id.choose_tv:
                if (!ac.isUserIdExsit()) {
                    UIHelper.jump(_activity, LoginActivity.class);
                    return;
                }
                if (TextUtils.isEmpty(carrierid)) {
                    return;
                }
                 /* 1.进入 综合体和园区，下面按钮都是“进入选址”；
                    2.进入4写字楼，3土地，6厂房，8仓库  详情后：
                    物业顾问-按钮变成“申请入驻”其他角色-按钮变成“一键预约”，
                    但是只有客商身份0登录才可以点击，其他角色点击提示要切换成为客商才可以选址；*/
                if ("进入选址".equals(choose_tv.getText().toString().trim())) {
//                    if (!"0".equals(ac.getProperty("user_type"))) {
//                        UIHelper.t(_activity, "请切换为普通用户进行选址");
//                        return;
//                    }
                    mBundle.putString("carrierid", carrierid);
                    mBundle.putString("title", bean.getData().getCarrierName());
                    UIHelper.jump(_activity, ChooseAddressActivity.class, mBundle);//进入选址
                } else if ("申请驻守".equals(choose_tv.getText().toString().trim())) {
                    mBundle.putString("carrierid", carrierid);
                    UIHelper.jump(_activity, ApplyEnterActivity.class, mBundle);//申请入驻
                } else if ("一键预约".equals(choose_tv.getText().toString().trim())) {
                    if (!"0".equals(ac.getProperty("user_type"))) {
                        UIHelper.t(_activity, "只有客商身份才能操作");
                        return;
                    }
                    mBundle.putString("carrierid", carrierid);
                    UIHelper.jump(_activity, MakeAppointmentActivity.class, mBundle);//载体详情》》》一键预约
                }
                break;


            case R.id.right_tv://对比
                if (!"0".equals(ac.getProperty("user_type"))) {
                    UIHelper.t(_activity, "只有客商身份才能操作");
                    return;
                }
                if (TextUtils.isEmpty(carrierid)) {
                    return;
                }
                mBundle.putString("carriertype", carrierType);
                mBundle.putString("carrierid", carrierid);
//                UIHelper.jump(_activity, ContrastListActivity.class,mBundle);
                UIHelper.jump(_activity, CarrierDetailContrastActivity.class, mBundle);
                break;
            case R.id.tab1:
                switchFragment(mFragment, f1);
                tab1.setEnabled(false);
                tab2.setEnabled(true);
                tab3.setEnabled(true);
                setTabbg1(R.id.tab1);
                break;
            case R.id.tab2:
                switchFragment(mFragment, f2);
                tab1.setEnabled(true);
                tab2.setEnabled(false);
                tab3.setEnabled(true);
                setTabbg1(R.id.tab2);
                break;
            case R.id.tab3:
                switchFragment(mFragment, f3);
                tab1.setEnabled(true);
                tab2.setEnabled(true);
                tab3.setEnabled(false);
                setTabbg1(R.id.tab3);
                break;
            case R.id.tab4:
                switchFragment1(mFragment1, f4);
                tab4.setEnabled(false);
                tab5.setEnabled(true);
                tab6.setEnabled(true);
                position = 0;
                setTabbg2(R.id.tab4);
                break;
            case R.id.tab5:
                switchFragment1(mFragment1, f5);
                tab4.setEnabled(true);
                tab5.setEnabled(false);
                tab6.setEnabled(true);
                position = 1;
                setTabbg2(R.id.tab5);
                break;
            case R.id.tab6:
                switchFragment1(mFragment1, f6);
                tab4.setEnabled(true);
                tab5.setEnabled(true);
                tab6.setEnabled(false);
                position = 2;
                setTabbg2(R.id.tab6);
                break;
            case R.id.more://点击查看更多点评
                mBundle.putInt("position", position);
                mBundle.putString("carrierid", bean.getData().getCarrierid());
                mBundle.putString("carriername", bean.getData().getCarrierName());
                UIHelper.jump(_activity, MoreCommentActivity.class, mBundle);
                break;
        }
    }

    private void setTabbg1(int arg0) {
        tab1.setTextColor(getResources().getColor(R.color.tab_n_bg));
        tab2.setTextColor(getResources().getColor(R.color.tab_n_bg));
        tab3.setTextColor(getResources().getColor(R.color.tab_n_bg));
        TextView tv = findView(arg0);
        tv.setEnabled(false);
        tv.setTextColor(getResources().getColor(R.color.tab_p_bg));
        tv.isSelected();
    }

    private void setTabbg2(int arg0) {
        tab4.setTextColor(getResources().getColor(R.color.text_bgb0));
        tab5.setTextColor(getResources().getColor(R.color.text_bgb0));
        tab6.setTextColor(getResources().getColor(R.color.text_bgb0));
        TextView tv = findView(arg0);
        tv.setEnabled(false);
        tv.setTextColor(getResources().getColor(R.color.blue_tv));
        tv.isSelected();
    }



    public void switchFragment(Fragment from, Fragment to) {
        FragmentTransaction mTransaction = fm.beginTransaction();
        if (mFragment != to) {
            mFragment = to;
            if (to.isAdded()) {
                mTransaction.hide(from).show(to).commit();
            } else {
                mTransaction.hide(from).add(R.id.fl_content, to).commit();
            }
        }
    }

    public void switchFragment1(Fragment from, Fragment to) {
        FragmentTransaction mTransaction = fm.beginTransaction();
        if (mFragment1 != to) {
            mFragment1 = to;
            if (to.isAdded()) {
                mTransaction.hide(from).show(to).commit();
            } else {
                mTransaction.hide(from).add(R.id.f_content, to).commit();
            }
        }
    }





  /*  @Override
    protected IDataSource<ArrayList> getDataSource() {
        return new YuanQuDataSource(_activity);
    }
*/
    /*@Override
    protected ArrayList<Class> getTemplateClasses() {
        ArrayList<Class> tpl = new ArrayList<Class>();
        tpl.add(RecommentReasonTpl.class);
        return null;
    }*/


}
