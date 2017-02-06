package com.midian.ppaddress.ui.homepage;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.midian.ppaddress.R;
import com.midian.ppaddress.bean.BusinessAppsearchSopageBean;
import com.midian.ppaddress.ui.chooseaddres.ChooseAddressActivity;
import com.midian.ppaddress.ui.chooseaddres.ConsultantActivity;
import com.midian.ppaddress.ui.chooseaddres.MultiDetailActivity;
import com.midian.ppaddress.ui.chooseaddres.ParkDetailActivity;
import com.midian.ppaddress.utils.AppUtil;

import java.util.ArrayList;
import java.util.List;

import midian.baselib.base.BaseActivity;
import midian.baselib.bean.NetResult;
import midian.baselib.utils.FDDataUtils;
import midian.baselib.utils.TDevice;
import midian.baselib.utils.UIHelper;
import midian.baselib.widget.BaseLibTopbarView;
import midian.baselib.widget.CircleImageView;
import midian.baselib.widget.RoundCornerImageView;

/**
 * 首页搜索框跳转页面
 * Created by chu on 2016/5/23.
 */
public class IndexSearchActivity extends BaseActivity implements AdapterView.OnItemClickListener {
    private EditText input_et;
    private View left_ll;
    private ImageView left_ib;
    private ListView listview;
    private MyAdapter myadapter;
    private TextView empty_tv;

    private String carriertype = null;
    private String labelids = null;
    private String saleRental = null;
    private String startPrice = null;
    private String endPrice = null;
    private String startArea = null;
    private String endArea = null;
    private String cityid = null;
    private String countyid = null;
    private String keyword = null;
    private int page = 1;

    private List<BusinessAppsearchSopageBean.CarrierList> dataList = new ArrayList<BusinessAppsearchSopageBean.CarrierList>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index_search);
        initView();
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.left_ib:
               finish();
                break;
        }
    }

    private void initView() {
        left_ll = findView(R.id.left_ll);
        left_ib = findView(R.id.left_ib);
        input_et = findView(R.id.input_et);
        left_ib.setOnClickListener(this);
        listview = findView(R.id.listview);
        empty_tv = findView(R.id.empty_tv);
        input_et.setHint("大家都在搜:办公楼");

        input_et.setFocusable(true);
        input_et.setFocusableInTouchMode(true);
        input_et.requestFocus();
        input_et.setImeOptions(EditorInfo.IME_ACTION_SEARCH);
        input_et.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int keyCode, KeyEvent keyEvent) {
                if (keyCode == KeyEvent.KEYCODE_ENTER && keyEvent.getAction() == KeyEvent.ACTION_DOWN) {// 修改回车键功能
                    // 先隐藏键盘
                    ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(
                            getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                    // 按完搜索键后将当前查询的关键字保存起来,如果该关键字已经存在就不执行保存
                    keyword = input_et.getText().toString().trim();
                    if (keyword == null || keyword.length() <= 0 || keyword.equals("")) {
                        UIHelper.t(_activity, "搜索内容不能为空");
                        return false;
                    }
                    loadData();
                }
                return false;
            }
        });
//        input_et.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//
//            }
//
//            @Override
//            public void afterTextChanged(Editable editable) {
//
//                keyword = input_et.getText().toString();
//                if (keyword!=null&&!keyword.equals("")) {
//                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
//                    imm.showSoftInput(input_et,InputMethodManager.SHOW_FORCED);
//                    loadData();
//                }
//            }
//        });
        listview.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
        Bundle mBundle = new Bundle();
        mBundle.putString("carriertitle", dataList.get(position).getShowName());//标题
        mBundle.putString("carriertype", dataList.get(position).getCarrierType());//载体类型
        mBundle.putString("carrierid", dataList.get(position).getCarrierid());//载体id
        UIHelper.jump(_activity, ParkDetailActivity.class, mBundle);

    }

    private void loadData() {
        if (cityid == null) {
            cityid = ac.city_id;
        }
        AppUtil.getPpApiClient(ac).businessAppsearchSopage(carriertype, labelids, saleRental, startPrice, endPrice, startArea, endArea, cityid, countyid, keyword, page + "", this);
    }

    @Override
    public void onApiStart(String tag) {
        super.onApiStart(tag);
        showLoadingDlg();
    }

    @Override
    public void onApiSuccess(NetResult res, String tag) {
        super.onApiSuccess(res, tag);
        hideLoadingDlg();
        if ("businessAppsearchSopage".equals(tag)) {
            BusinessAppsearchSopageBean carrierListbean = (BusinessAppsearchSopageBean) res;
            myadapter = new MyAdapter();
            listview.setAdapter(myadapter);
            dataList.clear();
            if (carrierListbean.getData().getList() == null || carrierListbean.getData().getList().size() == 0) {
                empty_tv.setVisibility(View.VISIBLE);
            } else {
                empty_tv.setVisibility(View.GONE);
                dataList.addAll(carrierListbean.getData().getList());
            }
            myadapter.notifyDataSetChanged();

        }
    }

    @Override
    public void onApiFailure(Throwable t, int errorNo, String strMsg, String tag) {
        super.onApiFailure(t, errorNo, strMsg, tag);
        hideLoadingDlg();
    }

    /*@Override
    protected int getLayoutId() {
        return R.layout.activity_index_search;
    }

    @Override
    protected IDataSource<ArrayList> getDataSource() {
        return searchDataSource;
    }

    @Override
    protected Class getTemplateClass() {
        return IndexSearchTpl.class;
    }*/


    public class MyAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return dataList.size();
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
                view = inflater.inflate(R.layout.item_types_adress, null);
                mHolder.image_iv = (ImageView) view.findViewById(R.id.image_iv);
                mHolder.has_enter_ll = view.findViewById(R.id.has_enter_ll);
                mHolder.has_enter = view.findViewById(R.id.has_enter);
                mHolder.has_list = (LinearLayout) view.findViewById(R.id.has_list);
                mHolder.status_tv = (TextView) view.findViewById(R.id.status);//租售类型
                mHolder.title_tv = (TextView) view.findViewById(R.id.title);
                mHolder.price_tv = (TextView) view.findViewById(R.id.price);
                mHolder.addresss_tv = (TextView) view.findViewById(R.id.address);
                mHolder.type_tv = (TextView) view.findViewById(R.id.type);//载体类型
                mHolder.name_tv = (TextView) view.findViewById(R.id.name);
                mHolder.booking_tv = (TextView) view.findViewById(R.id.booking);
                mHolder.head = (CircleImageView) view.findViewById(R.id.head);
                view.setTag(mHolder);
            }
            mHolder = (ViewHolder) view.getTag();
            final BusinessAppsearchSopageBean.CarrierList bean = dataList.get(position);
            final String carrierid = bean.getCarrierid();//载体id
            mHolder.carrier_uuid = bean.getCarrieruuid();//载体uuid
            if (bean.getImages() == null) {
                ac.setImage(mHolder.image_iv, R.drawable.default_bg);
            } else {
                ac.setImage(mHolder.image_iv, bean.getImages());
            }
            mHolder.title_tv.setText(bean.getShowName());
            String saleRental = bean.getSaleRental();//租售方式，0为租售，1为租，2为售
           if ("1".equals(saleRental)||"0".equals(saleRental)) {
                mHolder.status_tv.setText("租");
                mHolder.price_tv.setText(bean.getPriceRent()+ "元/m²·月 起");
            } else if ("2".equals(saleRental)) {
                mHolder.status_tv.setText("售");
                mHolder.price_tv.setText(FDDataUtils.addComma(bean.getPriceSell()) + "元/m²");
            }
            String county = bean.getCounty();//区县名称
            if (county != null) {
                mHolder.addresss_tv.setText(county);
            }
            final String carrier_type = bean.getCarrierType();//类型，1为园区，2为综合体，3为土地，4为写字楼，6为厂房，8为仓库

            if ("1".equals(carrier_type)) {
                mHolder.type_tv.setText("园区");
            } else if ("2".equals(carrier_type)) {
                mHolder.type_tv.setText("综合体");
            } else if ("3".equals(carrier_type)) {
                mHolder.type_tv.setText("土地");
            } else if ("4".equals(carrier_type)) {
                mHolder.type_tv.setText("写字楼");
            } else if ("6".equals(carrier_type)) {
                mHolder.type_tv.setText("厂房");
            } else if ("8".equals(carrier_type)) {
                mHolder.type_tv.setText("仓库");
            }
            String hasEnter = bean.getHasEnter();//0是没顾问驻守，1是有顾问驻守
            mHolder.has_list.removeAllViews();

            if ("1".equals(hasEnter)) {
                mHolder.has_enter.setVisibility(View.VISIBLE);
                if (carrier_type.equals("1") || carrier_type.equals("2")) {
                    mHolder.has_list.setVisibility(View.VISIBLE);
                    mHolder.has_enter_ll.setVisibility(View.GONE);
                    mHolder.booking_tv.setText("进入选址");
                    for (int i = 0; i < bean.getIntroImages().size(); i++) {
                        RoundCornerImageView image = (RoundCornerImageView) View.inflate(_activity, R.layout.view_carriers_bg, null);
                        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams((int) TDevice.dpToPixel(30f), (int) TDevice.dpToPixel(30f));
                        params.rightMargin = (int) TDevice.dpToPixel(10f);
                        if (bean.getIntroImages().get(i) == null || bean.getIntroImages().get(i).equals("")) {
                            ac.setImage(image, R.drawable.head1);
                        } else {
                            ac.setImage(image, bean.getIntroImages().get(i));
                        }
                        mHolder.has_list.addView(image, params);
                    }
                } else {
                    mHolder.has_list.setVisibility(View.GONE);
                    mHolder.has_enter_ll.setVisibility(View.VISIBLE);
                    if (bean.getPortrait() == null || bean.getPortrait().equals("")) {
                        ac.setImage(mHolder.head, R.drawable.head1);
                    } else {
                        ac.setImage(mHolder.head, bean.getPortrait());
                    }
                    if (bean.getSex().equals("0")) {//0是女，1是男
                        mHolder.sex = "女";
                    } else {
                        mHolder.sex = "男";
                    }
                    mHolder.name_tv.setText(bean.getFullname() + " | " + mHolder.sex + " | 物业顾问");
                    mHolder.enter_memberid = bean.getMemberid();
                }
            } else {
             mHolder.has_enter.setVisibility(View.GONE);
            }

            mHolder.booking_tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Bundle mBundle = new Bundle();
                    if (carrier_type.equals("1") || carrier_type.equals("2")) {
                        mBundle.putString("carrierid", carrierid);
                        mBundle.putString("title", bean.getShowName());
                        UIHelper.jump(_activity, ChooseAddressActivity.class, mBundle);//进入选址
                    } else {
                        mBundle.putString("carrierid", carrierid);//载体id`
                        UIHelper.jump(_activity, ConsultantActivity.class, mBundle);//物业顾问列表
                    }
                }
            });

            return view;
        }

        public class ViewHolder {
            TextView status_tv, title_tv, price_tv, addresss_tv, type_tv, name_tv, booking_tv;
            CircleImageView head;
            ImageView image_iv;
            View has_enter_ll,has_enter;
            LinearLayout has_list;
            String carrierid, carrier_uuid, sex, enter_memberid;
        }
    }
}
