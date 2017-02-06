package com.midian.ppaddress.ui.chooseaddres.detailfragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.midian.ppaddress.R;
import com.midian.ppaddress.bean.BusinessCarrierDetailQueryPropertiesBean;
import com.midian.ppaddress.utils.AppUtil;

import java.util.ArrayList;
import java.util.List;

import midian.baselib.api.ApiCallback;
import midian.baselib.base.BaseFragment;
import midian.baselib.bean.NetResult;
import midian.baselib.widget.FlowLayout;
import midian.baselib.widget.ListViewForScrollView;

/**
 * 基本参数fragment
 * Created by chu on 2016/3/22.
 */
public class Detail2Fragment extends BaseFragment implements ApiCallback {

    private ListViewForScrollView listView2;
    private String carrierid;
    private Detail2Adapter adapter;
    private List<BusinessCarrierDetailQueryPropertiesBean.PropertiesData> dataList = new ArrayList<>();
    private TextView wait_tv, empty_tv;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_detail2, null);
        listView2 = (ListViewForScrollView) v.findViewById(R.id.listView2);
        adapter = new Detail2Adapter();
        listView2.setAdapter(adapter);
        wait_tv = (TextView) v.findViewById(R.id.wait_tv);
        empty_tv = (TextView) v.findViewById(R.id.empty_tv);
        loadData();
        return v;
    }

    private void loadData() {
//        BusinessCarrierDetailQueryPropertiesBean bean =
        AppUtil.getPpApiClient(ac).carrierDetailList(carrierid, this);
    }

    @Override
    public void onApiStart(String tag) {

    }

    @Override
    public void onApiLoading(long count, long current, String tag) {

    }

    @Override
    public void onApiSuccess(NetResult res, String tag) {
        _activity.hideLoadingDlg();
        wait_tv.setVisibility(View.GONE);
        if (res.isOK()) {
            BusinessCarrierDetailQueryPropertiesBean bean = (BusinessCarrierDetailQueryPropertiesBean) res;
            dataList = bean.getData();
            if (dataList != null && dataList.size() > 0) {
                empty_tv.setVisibility(View.GONE);
                adapter.notifyDataSetChanged();
            } else {
                empty_tv.setVisibility(View.VISIBLE);
            }
        } else {
            ac.handleErrorCode(_activity, res.errorcode);
        }
    }

    @Override
    public void onApiFailure(Throwable t, int errorNo, String strMsg, String tag) {

    }

    @Override
    public void onParseError(String tag) {

    }


    public class Detail2Adapter extends BaseAdapter {
        @Override
        public int getCount() {
            if (dataList != null && dataList.size() > 0) {
                return dataList.size();
            }
            return 0;
        }

        @Override
        public Object getItem(int i) {
            return dataList.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int position, View view, ViewGroup viewGroup) {
            ViewHolder holder;
            if (view == null) {
                holder = new ViewHolder();
                LayoutInflater inflater = LayoutInflater.from(_activity);
                view = inflater.inflate(R.layout.item_basic_fragment_tpl, null);
                holder.key_tv = (TextView) view.findViewById(R.id.key_tv);
                holder.value_tv = (TextView) view.findViewById(R.id.value_tv);
                holder.liner_list = (FlowLayout) view.findViewById(R.id.liner_list);
                view.setTag(holder);
            }
            holder = (ViewHolder) view.getTag();
            //渲染数据

            final BusinessCarrierDetailQueryPropertiesBean.PropertiesData bean = dataList.get(position);

            holder.key_tv.setText(bean.getFieldKey() + "：");
            holder.value_tv.setText(bean.getFieldValue());

            holder.liner_list.removeAllViews();
            if (bean.getFieldArrayValue() == null) {
                holder.value_tv.setVisibility(View.VISIBLE);
                holder.liner_list.setVisibility(View.GONE);
            } else {
                holder.value_tv.setVisibility(View.GONE);
                holder.liner_list.setVisibility(View.VISIBLE);
                for (int i = 0; i < bean.getFieldArrayValue().size(); i++) {
                    BusinessCarrierDetailQueryPropertiesBean.PropertiesData.FileValueList valueList = bean.getFieldArrayValue().get(i);
//                    addView(valueList);
                    final View v = LayoutInflater.from(_activity).inflate(R.layout.jibencanshu_value, holder.liner_list, false);
                    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                    TextView tv = (TextView) v.findViewById(R.id.value_tv);
                    ImageView iv = (ImageView) v.findViewById(R.id.value_iv);
                    ac.setImage(iv, valueList.getIconurl());
                    tv.setText(valueList.getName());
                    //把TextView加入流式布局
                    holder.liner_list.addView(v);
                }
            }
            return view;
        }

        public class ViewHolder {
            private TextView key_tv, value_tv;
            private FlowLayout liner_list;
        }

    }


    public String getCarrierid() {
        return carrierid;
    }

    public void setCarrierid(String carrierid) {
        this.carrierid = carrierid;
    }
  /*  @Override
    protected IDataSource<ArrayList> getDataSource() {
        return new BasicFragemntDataSource(_activity,carrierid);
    }
    @Override
    protected Class getTemplateClass() {
        return BasicFragemntTpl.class;
    }*/
}
