package com.midian.ppaddress.itemtpl;

import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.midian.ppaddress.R;
import com.midian.ppaddress.bean.AppIndexBean.RecommendCarriers;
import com.midian.ppaddress.bean.IndexMultiItem;
import com.midian.ppaddress.ui.homepage.ChooseAddressTypeActivity1;
import com.midian.ppaddress.ui.homepage.IndexSearchActivity;
import com.midian.ppaddress.ui.homepage.InvestmentActivity;
import com.midian.ppaddress.ui.homepage.MapChooseAddressActivity;

import java.util.ArrayList;
import java.util.List;

import midian.baselib.utils.TDevice;
import midian.baselib.utils.UIHelper;
import midian.baselib.view.BaseTpl;
import midian.baselib.widget.FlowLayout;
import midian.baselib.widget.HorizontialListView;

/**
 * 首页上部分【暂时不用】
 * Created by chu on 2016/2/23.
 */
public class HomeUpperPortionTpl extends BaseTpl<IndexMultiItem> implements View.OnClickListener, View.OnFocusChangeListener {
    private TextView city_btn;
    private EditText input_et;
    private ImageView search;
    private View synthes_park;
    private View building;
    private View workshop;
    private View land;
    private View map_choose_address_ll, investment_ll;//地图、投资环境
    private LinearLayout liner_list;
    private HorizontialListView h_list;
    private ListAdapter listAdapter;
    private String investEnvironUrl = null;
    private List<RecommendCarriers> dataList;
    private String city;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public HomeUpperPortionTpl(Context context) {
        super(context);
    }

    public HomeUpperPortionTpl(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void initView() {
        city_btn = findView(R.id.city_btn);
        input_et = (EditText) findView(R.id.edit_et);
        search = (ImageView) findView(R.id.search);
        synthes_park = findView(R.id.synthes_park);
        building = findView(R.id.building);
        workshop = findView(R.id.workshop);
        land = findView(R.id.land);
        map_choose_address_ll = findView(R.id.map_choose_address_ll);
        investment_ll = findView(R.id.investment_ll);
//        h_list = findView(R.id.h_list);
        liner_list = findView(R.id.liner_list);


        input_et.setOnFocusChangeListener(this);
        city_btn.setOnClickListener(this);
        search.setOnClickListener(this);
        synthes_park.setOnClickListener(this);
        building.setOnClickListener(this);
        workshop.setOnClickListener(this);
        land.setOnClickListener(this);
        map_choose_address_ll.setOnClickListener(this);
        investment_ll.setOnClickListener(this);
        dataList = new ArrayList<RecommendCarriers>();
        listAdapter = new ListAdapter();
//        h_list.setAdapter(listAdapter);

        if (ac.city_name.equals("") || ac.city_name == null) {
            city_btn.setText("定位失败");
        } else {
            city_btn.setText(ac.city_name);
        }
    }

    public void refresh() {
        city_btn.setText(ac.city_name);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.item_home_upper_portion_tpl;
    }

    @Override
    public void setBean(IndexMultiItem bean, int position) {
        if (bean.getItemViewType() == 0) {
            dataList = bean.carrierses;
            listAdapter.notifyDataSetChanged();
            //推荐载体条目
           /* for (AppIndexBean.RecommendCarriers topitem : bean.carrierses) {
                ItemIndexTpl iit = new ItemIndexTpl(_activity);
                liner_list.addView(iit);
                iit.setBean(topitem,0);
            }*/
            investEnvironUrl = bean.investEnvironUrl;
            System.out.println("投资环境url=" + investEnvironUrl);

        }
    }

    @Override
    public void onFocusChange(View view, boolean b) {
        if (b) {
            UIHelper.jump(_activity, IndexSearchActivity.class);//首页搜索跳转
        } else {
            input_et.clearFocus();
        }
    }


    public class ListAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return dataList.size();
        }

        @Override
        public Object getItem(int position) {
            return dataList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View view, ViewGroup viewGroup) {
            ViewHolder holder = null;
            if (view == null) {
                LayoutInflater inflater = LayoutInflater.from(_activity);
                view = inflater.inflate(R.layout.item_view, null);
                holder = new ViewHolder();
                holder.title_tv = (TextView) view.findViewById(R.id.title_tv);
                holder.sale_tv = (TextView) view.findViewById(R.id.sale_tv);
                holder.type_tv = (TextView) view.findViewById(R.id.type_tv);
                holder.price_tv = (TextView) view.findViewById(R.id.price_tv);
                holder.loc_tv = (TextView) view.findViewById(R.id.loc_tv);
                holder.liner_list = (LinearLayout) view.findViewById(R.id.liner_list);
                view.setTag(holder);
            } else {
                holder = (ViewHolder) view.getTag();
            }
            //渲染数据
          final  RecommendCarriers bean = dataList.get(position);
            holder.title_tv.setText(bean.getCarrierName());
            if ("1".equals(bean.getSaleRental())) {//租售方式，0为租售，1为租，2为售
                holder.sale_tv.setText("出租");
                holder.price_tv.setText(bean.getPriceRent());//出租单价
            } else if ("2".equals(bean.getSaleRental())) {
                holder.sale_tv.setText("出售");
                holder.price_tv.setText(bean.getPriceSell());//出售单价
            } else if ("0".equals(bean.getSaleRental())) {
                holder.sale_tv.setText("租售");
                holder.price_tv.setText(bean.getPriceSell());//出售单价
            }
            //载体类型 ，[1]：园区，[2]：综合体，[3]：土地，[4]：写字楼，[6]：厂房，[8]：仓库
            if ("1".equals(bean.getCarrierType())) {
                holder.type_tv.setText("园区");
            } else if ("2".equals(bean.getCarrierType())) {
                holder.type_tv.setText("综合体");
            } else if ("3".equals(bean.getCarrierType())) {
                holder.type_tv.setText("土地");
            } else if ("4".equals(bean.getCarrierType())) {
                holder.type_tv.setText("写字楼");
            } else if ("6".equals(bean.getCarrierType())) {
                holder.type_tv.setText("厂房");
            } else if ("8".equals(bean.getCarrierType())) {
                holder.type_tv.setText("仓库");
            }
            holder.price_tv.setText(bean.getPriceSell());//出售单价
            holder.loc_tv.setText(bean.getCity() + "   " + bean.getCounty() + "    园区总面积 " + bean.getLandArea() + "m²");
            if (bean.getImages() != null) {
                holder.liner_list.removeAllViews();
                for (int j = 0; j < 4; j++) {
                    ImageView image = (ImageView) View.inflate(_activity, R.layout.card_view_item, null);
                    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams((int) TDevice.dpToPixel(60f), (int) TDevice.dpToPixel(60f));
                    params.rightMargin = (int) TDevice.dpToPixel(15f);
                    LinearLayout tl = new LinearLayout(_activity);
                    tl.setOrientation(HORIZONTAL);
                    tl.setGravity(Gravity.CENTER_HORIZONTAL);
                    tl.addView(image,params);
                    ac.setImage(image, bean.getImages().get(j).getConver());
                    holder.liner_list.addView(tl);

                   /* holder.type_list.removeAllViews();
                    List<AppIndexBean.Images> imageBean = bean.getImages();
                    for (int j = 0; j < imageBean.size(); j++) {
                        final ImageView image = (ImageView) View.inflate(_activity, R.layout.card_view_item, null);
                        ac.setImage(image, imageBean.get(j).getConver());
                        LinearLayout tl = new LinearLayout(_activity);
                        tl.setOrientation(HORIZONTAL);
//                        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                        tl.addView(image);
                        holder.type_list.addView(tl);
                    }*/
                }
            }
            return view;
        }

        final class ViewHolder {
            private TextView title_tv, sale_tv, type_tv, price_tv, loc_tv;
            private LinearLayout liner_list;
            private FlowLayout type_list;
        }
    }


    @Override
    public void onClick(View v) {
        Bundle mBundle = new Bundle();
        String type = null;
        switch (v.getId()) {
            case R.id.city_btn:
                break;
            case R.id.synthes_park://园区  载体类型 ，[1]：园区，[2]：综合体，[3]：土地，[4]：写字楼，[6]：厂房，[8]：仓库
                type = "1";
                mBundle.putString("type", type);
                UIHelper.jump(_activity, ChooseAddressTypeActivity1.class, mBundle);
                break;
            case R.id.building://写字楼
                type = "4";
                mBundle.putString("type", type);
                UIHelper.jump(_activity, ChooseAddressTypeActivity1.class, mBundle);
                break;
            case R.id.workshop://仓库/厂房
                type = "6";
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
                break;
            case R.id.investment_ll://投资环境
                if (investEnvironUrl == null) {
                    return;
                } else {
                    mBundle.putString("url", investEnvironUrl);
                    UIHelper.jump(_activity, InvestmentActivity.class, mBundle);
                }
                break;
        }
    }

}
