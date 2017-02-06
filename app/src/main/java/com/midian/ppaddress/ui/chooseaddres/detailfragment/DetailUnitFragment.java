package com.midian.ppaddress.ui.chooseaddres.detailfragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.midian.ppaddress.R;
import com.midian.ppaddress.bean.BusinessCarrierDetailPageUnitsBean;
import com.midian.ppaddress.ui.chooseaddres.MoreOptionalUnitActivity;
import com.midian.ppaddress.utils.AppUtil;

import java.util.ArrayList;
import java.util.List;

import midian.baselib.api.ApiCallback;
import midian.baselib.base.BaseFragment;
import midian.baselib.bean.NetResult;
import midian.baselib.utils.FDDataUtils;
import midian.baselib.utils.UIHelper;
import midian.baselib.widget.ListViewForScrollView;

/**
 * 详情--可选单元
 * Created by chu on 2016/4/22.
 */
public class DetailUnitFragment extends BaseFragment implements View.OnClickListener, ApiCallback {

    private TextView count_tv, more_tv;
    private String carrierid;
    private String carrier_type;
    private ListViewForScrollView listview;
    private UnitAdapter unitAdapter;
    private List<BusinessCarrierDetailPageUnitsBean.UnitsList> dataList = new ArrayList<>();
    private TextView wait_tv, empty_tv;
    private View item_ll;

    public String getCarrierid() {
        return carrierid;
    }

    public void setCarrierid(String carrierid) {
        this.carrierid = carrierid;
    }

    public String getCarrier_type() {
        return carrier_type;
    }

    public void setCarrier_type(String carrier_type) {
        this.carrier_type = carrier_type;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_optional_unit, null);
        count_tv = (TextView) v.findViewById(R.id.count_tv);
        more_tv = (TextView) v.findViewById(R.id.more_tv);
        listview = (ListViewForScrollView) v.findViewById(R.id.listview);
        wait_tv = (TextView) v.findViewById(R.id.wait_tv);
        empty_tv = (TextView) v.findViewById(R.id.empty_tv);
        item_ll = v.findViewById(R.id.item_ll);
        unitAdapter = new UnitAdapter();
        listview.setAdapter(unitAdapter);
        loadData();
        more_tv.setOnClickListener(this);
        return v;
    }

    private void loadData() {
        AppUtil.getPpApiClient(ac).businessCarrierDetailPageUnits(carrierid, ac.user_type, "1", "10", this);
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
            BusinessCarrierDetailPageUnitsBean bean = (BusinessCarrierDetailPageUnitsBean) res;
            dataList = bean.getData().getList();
            if (dataList != null && dataList.size() > 0) {
                empty_tv.setVisibility(View.GONE);
                item_ll.setVisibility(View.VISIBLE);
                unitAdapter.notifyDataSetChanged();
                count_tv.setText("有" + dataList.size() + "种单元供您选择");
            } else {
                empty_tv.setVisibility(View.VISIBLE);
                item_ll.setVisibility(View.GONE);
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


    class UnitAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            if (dataList != null && dataList.size() > 0) {
                if (dataList.size() > 4) {
                    return 4;
                } else {
                    return dataList.size();
                }
            } else {
                return 0;
            }
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
                convertView = inflater.inflate(R.layout.item_optional_unit_tpl, null);
                mHolder.image_iv = (ImageView) convertView.findViewById(R.id.image_iv);
                mHolder.height_tv = (TextView) convertView.findViewById(R.id.height_tv);
                mHolder.decorate_tv = (TextView) convertView.findViewById(R.id.decorate_tv);
                mHolder.floor_tv = (TextView) convertView.findViewById(R.id.floor_tv);
                mHolder.area_tv = (TextView) convertView.findViewById(R.id.area_tv);
                mHolder.shang_tv = (TextView) convertView.findViewById(R.id.shang_tv);
                mHolder.type_tv = (TextView) convertView.findViewById(R.id.type_tv);
                mHolder.price_tv = (TextView) convertView.findViewById(R.id.price_tv);
                mHolder.shang_ll = convertView.findViewById(R.id.shang_ll);
                convertView.setTag(mHolder);
            }
            mHolder = (ViewHolder) convertView.getTag();
            //渲染数据
            final BusinessCarrierDetailPageUnitsBean.UnitsList bean = dataList.get(position);

            if (bean.getCoverimg() == null && bean.getCoverimg().equals("")) {
                ac.setImage(mHolder.image_iv, R.drawable.default_bg);
            } else {
                ac.setImage(mHolder.image_iv, bean.getCoverimg());
            }
            //载体类型，1为园区，2为综合体，3为土地，4为写字楼，5为写字楼单元，6为厂房，7为厂房单元，8为仓库，9为仓库单元
            //只有写字楼，仓库，厂房三个载体类型才有可选单元
            if (bean.getCarrierType().equals("7") || bean.getCarrierType().equals("9")) { //BeamHeight只有厂房和仓库单元才有含有该字段返
                mHolder.height_tv.setText("层高：" + bean.getBeamHeight()+"米");
            }
            if (bean.getCarrierType().equals("5")) {//只有写字楼单元才有含有该字段返回
                mHolder.height_tv.setText("装修：" + bean.getDecor());
            }
            mHolder.floor_tv.setText("楼层：" + bean.getFloor());
            mHolder.area_tv.setText(FDDataUtils.addComma(bean.getBuildingArea()) + "m²");

            //账号类型，0为客商，1为客商中介，2为物业顾问，3为业主，4为服务达人
            if ("1".equals(ac.getProperty("user_type")) || "2".equals(ac.getProperty("user_type"))) {
                mHolder.shang_ll.setVisibility(View.VISIBLE);//客商中介，物业顾问才展示该字段
                if (!TextUtils.isEmpty(bean.getReward())) {
                    mHolder.shang_tv.setText(bean.getReward());
                }
            } else {
                mHolder.shang_ll.setVisibility(View.GONE);
            }

            if ("0".equals(bean.getSaleRental())) {//0为租售，1为租，2为售
                mHolder.type_tv.setText("租");
                if (bean.getSaleRental().equals("0")) {
                    mHolder.price_tv.setText("面议");
                } else {
                    mHolder.price_tv.setText(FDDataUtils.addComma(bean.getPriceRent()) + "元/m²·月 起");
                }
            } else if ("1".equals(bean.getSaleRental())) {
                mHolder.type_tv.setText("租");
                if (bean.getSaleRental().equals("0")) {
                    mHolder.price_tv.setText("面议");
                } else {
                    mHolder.price_tv.setText(FDDataUtils.addComma(bean.getPriceRent()) + "元/m²·月 起");
                }
            }
            if ("2".equals(bean.getSaleRental())) {
                mHolder.type_tv.setText("售");
                if (bean.getSaleRental().equals("0")) {
                    mHolder.price_tv.setText("面议");
                } else {
                    mHolder.price_tv.setText(FDDataUtils.addComma(bean.getPriceSell()) + "元/m² 起");
                }
            }
            return convertView;
        }

        public class ViewHolder {
            private ImageView image_iv;
            private TextView height_tv, decorate_tv, floor_tv, area_tv, shang_tv, type_tv, price_tv;
            private View shang_ll;
        }
    }


 /*   @Override
    protected int getLayoutId() {
        return R.layout.fragment_optional_unit;
    }

    @Override
    protected IDataSource<ArrayList> getDataSource() {
        return new OptionalUnitDataSource(_activity,carrierid);
    }



    @Override
    protected Class getTemplateClass() {
        return OptionalUnitTpl.class;
    }*/


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.more_tv:
//                UIHelper.t(_activity, "点击查看更多可选单元");
                Bundle mBundle = new Bundle();
                mBundle.putString("carrierid", carrierid);
                UIHelper.jump(_activity, MoreOptionalUnitActivity.class, mBundle);//更多可选单元
                break;
        }
    }

  /*  @Override
    public void onEndRefresh(IDataAdapter adapter, ArrayList result) {
        super.onEndRefresh(adapter, result);
//        count_tv.setText("有"+resultList+"种单元供您选择");
    }*/


}
