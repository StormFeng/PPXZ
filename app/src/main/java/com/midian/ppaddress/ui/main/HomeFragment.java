package com.midian.ppaddress.ui.main;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.midian.ppaddress.R;
import com.midian.ppaddress.bean.AppIndexBean;
import com.midian.ppaddress.ui.chooseaddres.ParkDetailActivity;
import com.midian.ppaddress.ui.chooseaddres.detailfragment.BasicFragemnt;
import com.midian.ppaddress.ui.company.CompanyDetialActivity;
import com.midian.ppaddress.ui.homepage.ChooseAddressTypeActivity1;
import com.midian.ppaddress.ui.homepage.CitysReleaseListActivity;
import com.midian.ppaddress.ui.homepage.IndexSearchActivity;
import com.midian.ppaddress.ui.homepage.InvestmentActivity;
import com.midian.ppaddress.ui.homepage.MapActivity;
import com.midian.ppaddress.ui.homepage.MapChooseAddressActivity;
import com.midian.ppaddress.ui.homepage.RecommendCarriersFragment;
import com.midian.ppaddress.utils.AppUtil;
import com.midian.ppaddress.widget.ClipViewPager;
import com.midian.ppaddress.widget.RecyclingPagerAdapter;
import com.midian.ppaddress.widget.ScalePageTransformer;

import java.util.ArrayList;
import java.util.List;

import midian.baselib.api.ApiCallback;
import midian.baselib.bean.NetResult;
import midian.baselib.common.WebViewActivity;
import midian.baselib.utils.FDDataUtils;
import midian.baselib.utils.Func;
import midian.baselib.utils.UIHelper;
import midian.baselib.widget.BannersView;
import midian.baselib.widget.BaseLibTopbarView;
import midian.baselib.widget.CircleImageView;
import midian.baselib.widget.ScrollViewWidthListener;
import midian.baselib.widget.ScrollViewWidthListener.onChildViewVisibilityChangedListener;
import midian.baselib.widget.pulltorefresh.PullToRefreshBase;
import midian.baselib.widget.pulltorefresh.PullToRefreshScrollView;

/**
 * 首页
 * 花落谁人知......
 * Created by chu on 2016/2/15.
 */
public class HomeFragment extends BasicFragemnt implements View.OnClickListener, ApiCallback, AdapterView.OnItemSelectedListener, ViewPager.OnPageChangeListener, BannersView.OnItemClickListener {
    private BaseLibTopbarView topbar;
    private ScrollViewWidthListener mScrollViewWidthListener;
    private PullToRefreshScrollView pulltoScrollView;
    private ListView listview;
    private MyAdapter myAdapter;//载体列表adapter
    private ViewPager h_pager;
    private ViewPagerAdapter pagerAdapter;
    private TextView city_name_tv;
    private BannersView bannerView;
    private TextView city_btn;
    private TextView search_tv;
    private View synthes_park;
    private View building;
    private View workshop;
    private View land;
    private View map_choose_address_ll, investment_ll;//地图、投资环境
    private String investEnvironUrl = null;
    private List<AppIndexBean.RecommendCarriers> carriersList = new ArrayList<AppIndexBean.RecommendCarriers>();
    private List<AppIndexBean.RecommendCounselors> counselorsList = new ArrayList<AppIndexBean.RecommendCounselors>();
    private List<AppIndexBean.RecommendAgencys> agencyses = new ArrayList<AppIndexBean.RecommendAgencys>();
    private List<AppIndexBean.Banners> bannersList = new ArrayList<>();
    private Animation showAnim = null;
    private Animation hideAnim = null;

    //首页服务机构展示页
    private ArrayList<Fragment> fragments;
    private ClipViewPager mViewPager;//服务机构Logo
    private TubatuAdapter mPagerAdapter;
    private ViewPager nViewPager;//背景
    private MyViewPagerAdapter nPageAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_home, null);
        initView(v);
        return v;
    }

    private void initView(View v) {
        View view = LayoutInflater.from(_activity).inflate(R.layout.item_home_upper_portion_tpl, null);
        topbar = (BaseLibTopbarView) v.findViewById(R.id.topbar);
        topbar.setTitle("PP投服").setLeftText("广州", this).setRightImageButton(R.drawable.icon_all_search, this);
        topbar.getLeft_tv().setCompoundDrawablePadding(Func.Dp2Px(_activity, 6));
        topbar.getLeft_tv().setCompoundDrawablesWithIntrinsicBounds(null, null, getResources().getDrawable(R.drawable.icon_address_pull_down), null);
        listview = (ListView) view.findViewById(R.id.listview);//推荐载体，
        search_tv = (TextView) view.findViewById(R.id.search_tv);
        city_btn = (TextView) view.findViewById(R.id.city_btn);
        bannerView = (BannersView) view.findViewById(R.id.bannerView);
        synthes_park = view.findViewById(R.id.synthes_park);
        building = view.findViewById(R.id.building);
        workshop = view.findViewById(R.id.workshop);
        city_name_tv = (TextView) view.findViewById(R.id.city_name_tv);
        land = view.findViewById(R.id.land);
        map_choose_address_ll = view.findViewById(R.id.map_choose_address_ll);
        investment_ll = view.findViewById(R.id.investment_ll);
        h_pager = (ViewPager) view.findViewById(R.id.h_pager);
        h_pager.setPageMargin(1);
        mViewPager = (ClipViewPager) view.findViewById(R.id.viewpager);
        nViewPager = (ViewPager) view.findViewById(R.id.comViewpager);
        mViewPager.setPageTransformer(true, new ScalePageTransformer());
        nViewPager.setPageTransformer(true, new ScalePageTransformer());
        mViewPager.setPageMargin(80);
        mViewPager.setOnPageChangeListener(this);
        nViewPager.setOnPageChangeListener(this);
        view.findViewById(R.id.page_container).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return mViewPager.dispatchTouchEvent(event);
            }
        });

        fragments = new ArrayList<Fragment>();
        pagerAdapter = new ViewPagerAdapter(_activity.getSupportFragmentManager(), fragments);


        bannerView.setOnItemClickListener(this);
        city_btn.setOnClickListener(this);
        search_tv.setOnClickListener(this);
        synthes_park.setOnClickListener(this);
        building.setOnClickListener(this);
        workshop.setOnClickListener(this);
        land.setOnClickListener(this);
        map_choose_address_ll.setOnClickListener(this);
        investment_ll.setOnClickListener(this);

        myAdapter = new MyAdapter();
        listview.setAdapter(myAdapter);

        mPagerAdapter = new TubatuAdapter(_activity);
        nPageAdapter = new MyViewPagerAdapter(_activity);

        //加载首页标题动画
        showAnim = AnimationUtils.loadAnimation(_activity, R.anim.show_anim);
        hideAnim = AnimationUtils.loadAnimation(_activity, R.anim.hide_anim);

        pulltoScrollView = (PullToRefreshScrollView) v.findViewById(R.id.refreshScrollView);
        pulltoScrollView.setPullLoadEnabled(false);
        pulltoScrollView.scrollView.addView(view);
        pulltoScrollView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ScrollView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ScrollView> refreshView) {
                loadData();
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ScrollView> refreshView) {

            }
        });
        mScrollViewWidthListener = pulltoScrollView.scrollView;
        //显示隐藏topbar
//        mScrollViewWidthListener = (ScrollViewWidthListener) v.findViewById(R.id.scroll);
        mScrollViewWidthListener.setOnChildViewVisibilityChangedListener(new onChildViewVisibilityChangedListener() {
            @Override
            public void onChildViewVisibilityChanged(int index, View v, boolean becameVisible) {
                System.out.println("index::" + index + "---" + "v.getId()::" + v.getId() + "---" + "becameVisible::" + becameVisible);
                int[] location=new int[2];
                //以city_btn为参照物，获取city_btn在当前屏幕中的位置
                city_btn.getLocationOnScreen(location);
//                Log.d("wqf",">>X::"+location[0]+">>Y::"+location[1]);
                if(location[1]<61){
//                    topbar.startAnimation(showAnim);// TODO: 2016/7/6 0006 添加动画会导致每次移动屏幕时都加载一次淡入淡出的效果
                    topbar.setVisibility(View.VISIBLE);
                }else{
                    topbar.setVisibility(View.GONE);
                }
            }
        });
        loadData();
    }

    public void refreshCity() {
        topbar.setLeftText(ac.city_name, this);
        city_btn.setText(ac.city_name);
        city_name_tv.setText(ac.city_name + "投资环境");
        loadData();
        pulltoScrollView.doPullRefreshing(false, 0);
    }


    private void loadData() {
        AppUtil.getPpApiClient(ac).businessAppIndex(ac.city_id, this);
    }


    @Override
    public void onApiSuccess(NetResult res, String tag) {
        _activity.hideLoadingDlg();
        if (res.isOK()) {
            pulltoScrollView.onPullDownRefreshComplete();
            pulltoScrollView.onPullUpRefreshComplete();
            if ("businessAppIndex".equals(tag)) {
                carriersList.clear();
                counselorsList.clear();
                agencyses.clear();
                bannersList.clear();
                h_pager.removeAllViews();
                AppIndexBean indexBean = (AppIndexBean) res;
                carriersList.addAll(indexBean.getData().getRecommendCarriers());//-----推荐的载体
                counselorsList.addAll(indexBean.getData().getRecommendCounselors());
                investEnvironUrl = indexBean.getData().getInvestEnvironUrl();
                agencyses = indexBean.getData().getRecommendAgencys();//服务机构

                bannersList = indexBean.getData().getBanners();
                if (bannersList != null) {
                   /* List<BannerView.Item> pics = new ArrayList<BannerView.Item>();
                    for (AppIndexBean.Banners banner : bannersList) {
                        pics.add(new BannerView.Item(banner.getImage(), banner.getFollow()));
                    }
                    bannerView.setPics(pics);*/
                    ArrayList<String> picUrls = new ArrayList<>();
                    for (int i = 0; i < bannersList.size(); i++) {
                        picUrls.add(bannersList.get(i).getImage());
                    }
                    bannerView.config(750, 400, picUrls);
                }
                h_pager.setAdapter(pagerAdapter);
                pagerAdapter.notifyDataSetChanged();//首页推荐载体
                mViewPager.setAdapter(mPagerAdapter);
                mViewPager.setOffscreenPageLimit(agencyses.size());
                nViewPager.setAdapter(nPageAdapter);
                if (agencyses.size() >= 3) {
//                    mViewPager.setCurrentItem(1);
                    nViewPager.setCurrentItem(1);
                }
                mPagerAdapter.notifyDataSetChanged();
                nPageAdapter.notifyDataSetChanged();
                initFragment();
                measureHeight(listview);
                myAdapter.notifyDataSetChanged();//载体列表
            }
            if ("memberFavoriteAgency".equals(tag)) {//收藏 服务机构回调
                UIHelper.t(_activity, res.message);
                listViewHelper.refresh();
            }
        } else {
            ac.handleErrorCode(_activity, res.errorcode);
        }
    }

    /**
     * bannerView监听
     */
    @Override
    public void onItemClick(View v, int position) {
        Bundle bundle = new Bundle();
        String follow = bannersList.get(position).getIsfollow();//值为0载体详细，值为1是打开一个静态网页
        if ("0".equals(follow)) {
            bundle.putString("carriertitle", "载体详情");
            bundle.putString("carriertype", bannersList.get(position).getCarrierType());
            bundle.putString("carrierid", bannersList.get(position).getCarrierid());
            UIHelper.jump(_activity, ParkDetailActivity.class, bundle);
        } else {
            WebViewActivity.gotoActivity(_activity, "详情", bannersList.get(position).getFollow());
        }
    }

    @Override
    public void onClick(View v) {
        Bundle mBundle = new Bundle();
        String type = null;
        switch (v.getId()) {
            case R.id.left_ll://首页获取发布城市列表
            case R.id.city_btn:
                UIHelper.jumpForResult(_activity, CitysReleaseListActivity.class, 10001);
                break;
            // 载体类型 ，[1]：园区，[2]：综合体，[3]：土地，[4]：写字楼，[6]：厂房，[8]：仓库
            case R.id.synthes_park://园区  综合体，
                type = "1,2";
                mBundle.putString("type", type);
                UIHelper.jump(_activity, ChooseAddressTypeActivity1.class, mBundle);
                break;
            case R.id.building://写字楼
                type = "4";
                mBundle.putString("type", type);
                UIHelper.jump(_activity, ChooseAddressTypeActivity1.class, mBundle);
                break;
            case R.id.workshop://仓库/厂房
                type = "6,8";
                mBundle.putString("type", type);
                UIHelper.jump(_activity, ChooseAddressTypeActivity1.class, mBundle);
                break;
            case R.id.land://土地
                type = "3";
                mBundle.putString("type", type);
                UIHelper.jump(_activity, ChooseAddressTypeActivity1.class, mBundle);
                break;
            case R.id.map_choose_address_ll://地图选址
                UIHelper.jump(_activity, MapChooseAddressActivity.class);
//                UIHelper.jump(_activity, MapActivity.class);
                break;
            case R.id.investment_ll://投资环境
                if (investEnvironUrl == null) {
                    return;
                } else {
                    mBundle.putString("url", investEnvironUrl);
                    UIHelper.jump(_activity, InvestmentActivity.class, mBundle);
//                    WebViewActivity.gotoActivity(_activity, "投资环境", investEnvironUrl);//投资环境webView页
                }
                break;
            case R.id.right_ib:
            case R.id.search_tv:
                UIHelper.jump(_activity, IndexSearchActivity.class);//首页搜索跳转
                break;
//            case R.id.comViewpager:

        }
    }

    @Override
    public void onApiStart(String tag) {
        _activity.showLoadingDlg();
    }

    @Override
    public void onApiLoading(long count, long current, String tag) {

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        mViewPager.setCurrentItem(position);
        nViewPager.setCurrentItem(position);
//        if(position==0 || position==agencyses.size()){
//            mViewPager.setScrollble(false);
//        }else{
//            mViewPager.setScrollble(true);
//        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }


    public class MyViewPagerAdapter extends RecyclingPagerAdapter {
        private List<View> views;
        private Context context;


        public MyViewPagerAdapter(Context context) {
            views = new ArrayList<>();
            this.context = context;
        }

        public void addAll(List<View> list) {
            views.addAll(list);
            notifyDataSetChanged();
        }

        @Override
        public int getCount() {
            return agencyses.size();
        }


        @Override
        public View getView(int position, View convertView, ViewGroup container) {
            ViewHolder viewHolder = null;
            if (convertView == null) {
                viewHolder = new ViewHolder();
                convertView = LayoutInflater.from(context).inflate(R.layout.viewpager_item_layout, null);
                viewHolder.tv_name = (TextView) convertView.findViewById(R.id.tv_name);
                viewHolder.tv_des = (TextView) convertView.findViewById(R.id.tv_des);
                viewHolder.iv_bg = (ImageView) convertView.findViewById(R.id.iv_bg);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            final AppIndexBean.RecommendAgencys bean = agencyses.get(position);
            viewHolder.tv_name.setText(bean.getName());
            viewHolder.tv_des.setText(bean.getAdwords());
            ac.setImage(viewHolder.iv_bg, bean.getAdverImage());
            convertView.findViewById(R.id.ll_item).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Bundle bundle = new Bundle();
                    bundle.putString("agencyid", bean.getId());
                    UIHelper.jump(_activity, CompanyDetialActivity.class, bundle);
                }
            });
            return convertView;
        }

        public class ViewHolder {
            TextView tv_name;
            TextView tv_des;
            ImageView iv_bg;
        }
    }

    public class TubatuAdapter extends RecyclingPagerAdapter {

        private final List<View> mList;
        private final Context mContext;

        public TubatuAdapter(Context context) {
            mList = new ArrayList<>();
            mContext = context;
        }

        public void addAll(List<View> list) {
            mList.addAll(list);
            notifyDataSetChanged();
        }


        @Override
        public View getView(final int position, View convertView, ViewGroup container) {
            ViewHolder viewHolder = null;
            if (convertView == null) {
                viewHolder = new ViewHolder();
                convertView = LayoutInflater.from(mContext).inflate(R.layout.top_item_layout, null);
                viewHolder.iv_logo = (ImageView) convertView.findViewById(R.id.iv_logo);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            final AppIndexBean.RecommendAgencys bean = agencyses.get(position);
            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (position == ClipViewPager.index) {
                        Bundle bundle = new Bundle();
                        bundle.putString("agencyid", bean.getId());
                        UIHelper.jump(_activity, CompanyDetialActivity.class, bundle);
                    }
                }
            });
            ac.setImage(viewHolder.iv_logo, agencyses.get(position).getLogo());
            return convertView;
        }

        @Override
        public int getCount() {
            return agencyses.size();
        }

        public class ViewHolder {
            ImageView iv_logo;
        }
    }

    /**
     * 横向载体列表adapter
     */
    private class ViewPagerAdapter extends FragmentStatePagerAdapter {
        ArrayList<Fragment> fragments = new ArrayList<Fragment>();

        public ViewPagerAdapter(FragmentManager fm, ArrayList<Fragment> fragments) {
            super(fm);
            this.fragments = fragments;
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }
    }


    /**
     * 初始化横向载体列表
     */
    private void initFragment() {
        fragments.clear();
        for (int i = 0; i < carriersList.size(); i++) {
            RecommendCarriersFragment carriersFragment = new RecommendCarriersFragment();
            carriersFragment.setRecommendCarriers(carriersList.get(i));
            /*carriersFragment.setArguments(new Bundle());
            carriersFragment.getArguments().putSerializable("carrierData", carriersList.get(i));*/
            fragments.add(carriersFragment);
            pagerAdapter.notifyDataSetChanged();
        }
    }

    private int measureHeight(ListView mListView) {
        mListView.setFocusable(false);
        mListView.setFocusableInTouchMode(false);
        // get ListView adapter
        android.widget.ListAdapter adapter = mListView.getAdapter();
        if (null == adapter) {
            return 0;
        }
        int totalHeight = 0;
        for (int i = 0, len = adapter.getCount(); i < len; i++) {
            View item = adapter.getView(i, null, mListView);
            if (null == item) continue;
            // measure each item width and height
            item.measure(0, 0);
            // calculate all height
            totalHeight += item.getMeasuredHeight();
        }
        ViewGroup.LayoutParams params = mListView.getLayoutParams();
        if (null == params) {
            params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        }
        // calculate ListView height
        params.height = totalHeight + (mListView.getDividerHeight() * (adapter.getCount() - 1));
        mListView.setLayoutParams(params);
        return params.height;
    }


    @Override
    public void onApiFailure(Throwable t, int errorNo, String strMsg, String tag) {
        _activity.hideLoadingDlg();
    }

    @Override
    public void onParseError(String tag) {
        _activity.hideLoadingDlg();
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        Bundle mBundle = new Bundle();
        mBundle.putString("carriertitle", carriersList.get(i).getCarrierName());
        mBundle.putString("carriertype", carriersList.get(i).getCarrierType());
        mBundle.putString("carrierid", carriersList.get(i).getCarrierid());//载体id
        UIHelper.jump(_activity, ParkDetailActivity.class, mBundle);//载体详情
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    /*
    @Override
    protected IDataSource<ArrayList> getDataSource() {
        return new HomeDataSource(_activity);
    }
    @Override
    protected ArrayList<Class> getTemplateClasses() {
        ArrayList<Class> tpls = new ArrayList<Class>();
        tpls.add(HomeUpperPortionTpl.class);
        tpls.add(HomeRecommendListTpl.class);
        return tpls;
    }*/


    /**
     * 首页顾问推荐载体列表
     */
    public class MyAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return counselorsList.size();
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View view, ViewGroup viewGroup) {
            ViewHolder mHolder;
            if (view == null) {
                mHolder = new ViewHolder();
                LayoutInflater inflater = LayoutInflater.from(_activity);
                view = inflater.inflate(R.layout.item_home_recommend_list_tpl, null);
                mHolder.img_iv = (ImageView) view.findViewById(R.id.img_iv);
                mHolder.title_tv = (TextView) view.findViewById(R.id.title_tv);
                mHolder.price_tv = (TextView) view.findViewById(R.id.price_tv);
                mHolder.head_cv = (CircleImageView) view.findViewById(R.id.head_cv);
                mHolder.content_tv = (TextView) view.findViewById(R.id.content_tv);
                mHolder.consultant_ll = view.findViewById(R.id.consultant_ll);
                view.setTag(mHolder);
            }
            mHolder = (ViewHolder) view.getTag();
            //渲染数据
            final AppIndexBean.RecommendCounselors bean = counselorsList.get(position);
            String carrierid = bean.getCarrierid();//载体id
            mHolder.title_tv.setText(bean.getCarrier().getCarrierName());
            if (bean.getCarrier().getImages() == null || bean.getCarrier().getImages().equals("")) {
                ac.setImage(mHolder.img_iv, R.drawable.default_bg);
            } else {
                ac.setImage(mHolder.img_iv, bean.getCarrier().getImages());
            }

            String saleRental = bean.getCarrier().getSaleRental();//租售方式，0为租售，1为租，2为售
            if ("1".equals(saleRental)) {
                mHolder.price_tv.setText(FDDataUtils.addComma(bean.getCarrier().getPriceRent()) + "元/m²·月 起");//出租价格
            } else if ("2".equals(saleRental)) {
                mHolder.price_tv.setText(FDDataUtils.addComma(bean.getCarrier().getPriceSell()) + "元/m² 起");//出售价格
            } else if ("0".equals(saleRental)) {
                mHolder.price_tv.setText(FDDataUtils.addComma(bean.getCarrier().getPriceRent()) + "元/m²·月 起");//租售（）
            }

            if (bean.getPortrait() == null) {
                ac.setImage(mHolder.head_cv, R.drawable.head1);
            } else {
                ac.setImage(mHolder.head_cv, bean.getPortrait());
            }
            mHolder.content_tv.setText(bean.getAdwords());

            mHolder.consultant_ll.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Bundle mBundle = new Bundle();
                    mBundle.putString("carriertitle", bean.getCarrier().getCarrierName());
                    mBundle.putString("carriertype", bean.getCarrier().getCarrierType());
                    mBundle.putString("carrierid", bean.getCarrier().getCarrierid());//载体id
                    UIHelper.jump(_activity, ParkDetailActivity.class, mBundle);
                }
            });


            return view;
        }

        public class ViewHolder {
            private ImageView img_iv;
            private TextView title_tv, price_tv;
            private CircleImageView head_cv;
            private TextView content_tv;
            private View consultant_ll;
        }
    }
}