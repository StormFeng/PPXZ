package com.midian.ppaddress.itemtpl;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.midian.ppaddress.R;
import com.midian.ppaddress.bean.DetailMultiItemBean;
import com.midian.ppaddress.ui.chooseaddres.detailfragment.BasicFragemnt;
import com.midian.ppaddress.ui.chooseaddres.detailfragment.OptionalUnitFragment;
import com.midian.ppaddress.ui.chooseaddres.detailfragment.RecommentFragemnt;

import java.util.ArrayList;

import midian.baselib.adapter.PagerTabAdapter;
import midian.baselib.base.BaseFragmentActivity;
import midian.baselib.view.BaseTpl;
import midian.baselib.widget.BaseLibViewPager;
import midian.baselib.widget.PagerSlidingTabStrip;

/**
 * 详情-推荐理由|基本信息条目|可选单元等fragment
 * 望穿秋水只为你
 * Created by chu on 2016/4/22.
 */
public class DetailBasicInfoTpl extends BaseTpl<DetailMultiItemBean> implements ViewPager.OnPageChangeListener {
    private PagerSlidingTabStrip detal_tabs;
    private BaseLibViewPager detal_pager;
    private PagerTabAdapter adapter;
    private ArrayList<Fragment> fragments;
    private ArrayList<String> titles;
    private RecommentFragemnt recommentFragemnt;
    private BasicFragemnt basicFragemnt;
    private OptionalUnitFragment optionalUnitFragment;
    private String type;
    private TextView title_tv, type_tv;//所属园区的标题、类型
    private ImageView belongs_iv;
    private View belongs_rl;

    public DetailBasicInfoTpl(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public DetailBasicInfoTpl(Context context) {
        super(context);
    }

    @Override
    protected void initView() {
        type = _activity.getIntent().getStringExtra("carriertype");
        detal_tabs = (PagerSlidingTabStrip) findView(R.id.detal_tabs);
        detal_pager = (BaseLibViewPager) findView(R.id.detal_pager);
        belongs_rl = findView(R.id.belongs_rl);
        belongs_iv = findView(R.id.belongs_iv);
        title_tv = findView(R.id.title_tv);
        type_tv = findView(R.id.type_tv);
        recommentFragemnt = new RecommentFragemnt();
        basicFragemnt = new BasicFragemnt();//基本参数
        optionalUnitFragment = new OptionalUnitFragment();//可选单元
        fragments = new ArrayList<Fragment>();
        titles = new ArrayList<String>();
        fragments.add(recommentFragemnt);
        titles.add("推荐理由");
        fragments.add(basicFragemnt);
        titles.add("基本参数");
        //载体类型 ，[1]：园区，[2]：综合体，[3]：土地，[4]：写字楼，[6]：厂房，[8]：仓库
        //只有写字楼，仓库，厂房三个载体类型才有可选单元
      /*  if ("1".equals(type) || "2".equals(type) || "3".equals(type)) {//园区详情  //土地详情
        } else*/
        if ("4".equals(type) || "6".equals(type) || "8".equals(type)) {//写字楼详情  //厂房/仓库详情
            fragments.add(optionalUnitFragment);
            titles.add("可选单元");
        }


        fm = ((BaseFragmentActivity) _activity).getSupportFragmentManager();
        adapter = new PagerTabAdapter(fm, titles, fragments);
        detal_pager.setAdapter(adapter);
        detal_tabs.setViewPager(detal_pager);
        detal_tabs.setOnPageChangeListener(this);
        detal_pager.setCurrentItem(0);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.item_fragment_detail_basic_info_tpl;
    }

    @Override
    public void setBean(DetailMultiItemBean bean, int position) {
//        absListView.setOverScrollMode();
        if (bean.getItemViewType() == 1) {
            recommentFragemnt.setDetailData(bean.detailData);
            basicFragemnt.setCarrierid(bean.detailData.getCarrierid());
            optionalUnitFragment.setCarrierid(bean.detailData.getCarrierid());
            optionalUnitFragment.setCarrier_type(bean.detailData.getCarrierType());

            // //载体类型，1为园区，2为综合体，3为土地，4为写字楼，5为写字楼单元，6为厂房，7为厂房单元，8为仓库，9为仓库单元
            //parent 字段(所属园区或综合体）土地、厂房、仓库、写字楼特有的
            String carrier_type = bean.detailData.getCarrierType();
            if ("3".equals(carrier_type) || "4".equals(carrier_type) || "6".equals(carrier_type) || "8".equals(carrier_type)) {
                belongs_rl.setVisibility(View.VISIBLE);
                if (bean.detailData.getParent() != null) {
                    if (bean.detailData.getParent().getImages() == null || bean.detailData.getParent().getImages().equals("")) {
                        ac.setImage(belongs_iv, R.drawable.icon_bg2);
                    } else {
                        ac.setImage(belongs_iv, bean.detailData.getParent().getImages());
                    }
                    title_tv.setText(bean.detailData.getParent().getCarrierName());
                    //载体类型，1为园区，2为综合体
                    if ("1".equals(bean.detailData.getParent().getCarrierType())) {
                        type_tv.setText("园区");
                    } else if ("2".equals(bean.detailData.getParent().getCarrierType())) {
                        type_tv.setText("综合体");
                    }
                }
            } else {
                belongs_rl.setVisibility(View.GONE);
            }
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {
    }
}
