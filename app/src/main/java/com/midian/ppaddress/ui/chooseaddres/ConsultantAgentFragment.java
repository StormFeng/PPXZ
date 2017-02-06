package com.midian.ppaddress.ui.chooseaddres;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.midian.ppaddress.R;
import com.midian.ppaddress.bean.EntercarrierPageEnterBean;
import com.midian.ppaddress.utils.AppUtil;

import java.util.ArrayList;
import java.util.List;

import midian.baselib.api.ApiCallback;
import midian.baselib.base.BaseFragment;
import midian.baselib.bean.NetResult;
import midian.baselib.utils.FDDataUtils;
import midian.baselib.utils.UIHelper;
import midian.baselib.widget.FlowLayout;
import midian.baselib.widget.ListViewForScrollView;

/**
 * 经纪人详情--驻守物业顾问fragment
 * Created by chu on 2016/4/28.
 */
public class ConsultantAgentFragment extends BaseFragment implements ApiCallback, AdapterView.OnItemClickListener {
    private TextView f1_tv, f3_tv;
    private ListViewForScrollView listView;
    private ListAdapter listAdapter;
    private List<EntercarrierPageEnterBean.Lists> dataList = new ArrayList<EntercarrierPageEnterBean.Lists>();
    private String counselorid;

    public String getCounselorid() {
        return counselorid;
    }

    public void setCounselorid(String counselorid) {
        this.counselorid = counselorid;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_user_comments, null);
        f1_tv = (TextView) _activity.findViewById(R.id.f1_tv);
        f3_tv = (TextView) _activity.findViewById(R.id.f3_tv);
        listView = (ListViewForScrollView) v.findViewById(R.id.listview);
        listView.setOnItemClickListener(this);
        loadDatas();
        return v;
    }

    private void loadDatas() {
        AppUtil.getPpApiClient(ac).getEntercarrierPageEnter(counselorid, "1", "15", this);
    }

    @Override
    public void onApiStart(String tag) {
        _activity.showLoadingDlg();
    }


    @Override
    public void onApiLoading(long count, long current, String tag) {

    }

    @Override
    public void onApiSuccess(NetResult res, String tag) {
        _activity.hideLoadingDlg();
        if (res.isOK()) {
            EntercarrierPageEnterBean bean = (EntercarrierPageEnterBean) res;
            dataList = bean.getData().getList();
            listAdapter = new ListAdapter();
            listView.setAdapter(listAdapter);
            f1_tv.setText("驻守的物业(" + dataList.size() + ")");
            f3_tv.setText("驻守的物业(" + dataList.size() + ")");

//        measureHeight(listView);
            listAdapter.notifyDataSetChanged();
        } else {
            ac.handleErrorCode(_activity, res.errorcode);
        }
    }

    @Override
    public void onApiFailure(Throwable t, int errorNo, String strMsg, String tag) {
        _activity.hideLoadingDlg();
    }

    @Override
    public void onParseError(String tag) {

    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Bundle mBundle = new Bundle();
        mBundle.putString("carriertitle", dataList.get(i).getCarrierName());
        mBundle.putString("carriertype", dataList.get(i).getCarrierType());
        mBundle.putString("carrierid", dataList.get(i).getCarrierid());
        UIHelper.jump(_activity, ParkDetailActivity.class, mBundle);
    }


    class ListAdapter extends BaseAdapter {

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
        public View getView(final int position, View convertView, ViewGroup parent) {
            ViewHolder mHolder;
            if (convertView == null) {
                mHolder = new ViewHolder();
                LayoutInflater inflater = LayoutInflater.from(_activity);
                convertView = inflater.inflate(R.layout.item_consultant_agent_tpl, null);
                mHolder.flow_layout = (FlowLayout) convertView.findViewById(R.id.flow_layout);
                mHolder.iv_image = (ImageView) convertView.findViewById(R.id.image_iv);
                mHolder.tv_name = (TextView) convertView.findViewById(R.id.title_tv);
                mHolder.tv_type = (TextView) convertView.findViewById(R.id.type_tv);
                mHolder.tv_label1 = (TextView) convertView.findViewById(R.id.tv_tabel1);
                mHolder.tv_label2 = (TextView) convertView.findViewById(R.id.tv_tabel2);
                mHolder.tv_label3 = (TextView) convertView.findViewById(R.id.tv_tabel3);
                mHolder.tv_saleRental = (TextView) convertView.findViewById(R.id.rent_tv);
                mHolder.tv_price = (TextView) convertView.findViewById(R.id.price_tv);
                mHolder.tv_area = (TextView) convertView.findViewById(R.id.area_tv);
                convertView.setTag(mHolder);
            }
            mHolder = (ViewHolder) convertView.getTag();

            final EntercarrierPageEnterBean.Lists bean = dataList.get(position);
            mHolder.tv_name.setText(bean.getCarrierName());
            mHolder.tv_name.setText(bean.getCarrierName());
            if (bean.getImages() == null && bean.getImages().equals("")) {
                ac.setImage(mHolder.iv_image, R.drawable.default_bg);
            } else {
                ac.setImage(mHolder.iv_image, bean.getImages());
            }

            //载体类型，1为园区，2为综合体，3为土地，4为写字楼，5为写字楼单元，6为厂房，7为厂房单元，8为仓库，9为仓库单元
            String carrier_type = bean.getCarrierType();
            if ("1".equals(carrier_type)) {
                mHolder.tv_type.setText("园区");
            } else if ("2".equals(carrier_type)) {
                mHolder.tv_type.setText("综合体");
            } else if ("3".equals(carrier_type)) {
                mHolder.tv_type.setText("土地");
            } else if ("4".equals(carrier_type)) {
                mHolder.tv_type.setText("写字楼");
            } else if ("5".equals(carrier_type)) {
                mHolder.tv_type.setText("写字楼单元");
            } else if ("6".equals(carrier_type)) {
                mHolder.tv_type.setText("厂房");
            } else if ("8".equals(carrier_type)) {
                mHolder.tv_type.setText("仓库");
            }

            if ("1".equals(carrier_type) || "2".equals(carrier_type) || "4".equals(carrier_type)) {//写字楼特有的
                mHolder.tv_saleRental.setVisibility(View.VISIBLE);
                String saleRental = bean.getSaleRental();//0为租售，1为租，2为售
                if ("1".equals(saleRental) || "0".equals(saleRental)) {
                    mHolder.tv_saleRental.setText("租");
                    mHolder.tv_price.setText(bean.getPriceRent() + "元/m²·月 起");
                } else if ("2".equals(saleRental)) {
                    mHolder.tv_saleRental.setText("售");
                    mHolder.tv_price.setText(bean.getPriceSell() + "元/m²");//售价
                }
                if("1".equals(carrier_type)){
                    mHolder.tv_area.setText("园区总面积 " + FDDataUtils.addComma(bean.getBuildingArea()) + "m²");
                }else if ("2".equals(carrier_type)) {
                    mHolder.tv_area.setText("综合体面积 " + FDDataUtils.addComma(bean.getBuildingArea()) + "m²");
                } else {
                    mHolder.tv_area.setText("待租售面积 " + FDDataUtils.addComma(bean.getBuildingArea()) + "m²");
                }
            } else {
                mHolder.tv_saleRental.setVisibility(View.GONE);
            }
            if ("6".equals(carrier_type) || "8".equals(carrier_type)) {//仓库、厂房共有字段
                mHolder.tv_price.setText("待租售面积" + FDDataUtils.addComma(bean.getBuildingArea()) + "m²");
                mHolder.tv_area.setText( bean.getFloor()+"层");
            } else if ("3".equals(carrier_type)) {//土地特有的
                mHolder.tv_price.setText("土地面积" + FDDataUtils.addComma(bean.getLandArea()) + "m²");//"用土性质："
                mHolder.tv_area.setText(bean.getProperty());
            }


            mHolder.flow_layout.removeAllViews();
            for (int i = 0; i < bean.getLabels().size(); i++) {
                if (i < 3) {
                    String key = bean.getLabels().get(i).getLabel();
                    final TextView tv = (TextView) LayoutInflater.from(_activity).inflate(R.layout.tv, mHolder.flow_layout, false);
                    tv.setText(key);
                    mHolder.flow_layout.addView(tv);
                }
            }
            return convertView;
        }


        public class ViewHolder {
            public FlowLayout flow_layout;
            public ImageView iv_image;
            public TextView tv_name, tv_type, tv_label1, tv_label2, tv_label3, tv_saleRental, tv_price, tv_area;
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
            params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
        }
        // calculate ListView height
        params.height = totalHeight + (mListView.getDividerHeight() * (adapter.getCount() - 1));
        mListView.setLayoutParams(params);
        return params.height;
    }


     /*   @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        btn_1 = (TextView) _activity.findViewById(R.id.btn_1);
    }*/

   /* @Override
    protected IDataSource<ArrayList> getDataSource() {
//        return new ConsultantAgentDataSource(_activity);
        return new MyBuildingResourceDataResource(_activity);
        *//*有两个地方调用此接口:
        1.	物业顾问详情页的”驻守的物业列表”(包含标签，不包含载体编号)
        2.	物业顾问主页的我的盘源(不包含标签，包含载体编号)*//*
    }

    @Override
    protected Class getTemplateClass() {
        return ConsultantAgentTpl.class;
    }

    @Override
    public void onEndRefresh(IDataAdapter adapter, Object result) {
        System.out.println("驻守的物业数量="+resultList.size());
        btn_1.setText("驻守的物业(" + resultList.size() + ")");
//        measureHeight(listView);// TODO: 2016/5/31 列表高度问题

    }

    @Override
    public void onEndLoadMore(IDataAdapter adapter, Object result) {

    }*/
}
