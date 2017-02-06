package com.midian.ppaddress.itemtpl;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.midian.ppaddress.R;
import com.midian.ppaddress.bean.CompareDetailBean;
import com.midian.ppaddress.bean.CompareMultiItem;
import com.midian.ppaddress.bean.MemberCompareDetailBean;
import com.midian.ppaddress.bean.MemberCompareListBean;
import com.midian.ppaddress.ui.chooseaddres.AddContrastActivity;
import com.midian.ppaddress.ui.chooseaddres.CarrierContrastActivity;
import com.midian.ppaddress.ui.chooseaddres.TableContrastActivity;

import java.util.ArrayList;
import java.util.List;

import midian.baselib.bean.NetResult;
import midian.baselib.utils.UIHelper;
import midian.baselib.view.BaseTpl;

public class TabTitleTpl extends BaseTpl<NetResult> implements View.OnClickListener {

    public TabTitleTpl(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TabTitleTpl(Context context) {
        super(context);
    }

    private TextView tv_1, tv_2, tv_3, tv_4;
    private ImageView del_iv2, del_iv3, del_iv4;
    private List<TextView> titleList;
    private List<ImageView> imgList;
    private String compareids;
    private String carriertype;
    ArrayList<String> ids;

    @Override
    protected void initView() {
        Intent intent = _activity.getIntent();
        carriertype = intent.getStringExtra("carriertype");
        compareids = intent.getStringExtra("compareids");
        tv_1 = findView(R.id.tv_1);
        tv_2 = findView(R.id.tv_2);
        tv_3 = findView(R.id.tv_3);
        tv_4 = findView(R.id.tv_4);
        del_iv2 = findView(R.id.del_iv2);
        del_iv3 = findView(R.id.del_iv3);
        del_iv4 = findView(R.id.del_iv4);
        tv_2.setOnClickListener(this);
        tv_3.setOnClickListener(this);
        tv_4.setOnClickListener(this);
        del_iv2.setOnClickListener(this);
        del_iv3.setOnClickListener(this);
        del_iv4.setOnClickListener(this);
        titleList = new ArrayList<TextView>();
        titleList.add(tv_2);
        titleList.add(tv_3);
        titleList.add(tv_4);

        imgList = new ArrayList<ImageView>();
        imgList.add(del_iv2);
        imgList.add(del_iv3);
        imgList.add(del_iv4);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.item_tab;
    }


    @Override
    public void setBean(NetResult bean, int position) {
        if (bean instanceof CompareDetailBean) {
            CompareDetailBean detailBean = (CompareDetailBean) bean;
            tv_1.setText("");
            ids = new ArrayList<String>();
            for (int i = 0; i < titleList.size(); i++) {
                if (i < detailBean.getData().getCarriers().size()) {
                    titleList.get(i).setText(detailBean.getData().getCarriers().get(i).getCarrierName());//渲染标题
                    ids.add(i, detailBean.getData().getCarriers().get(i).getCompareid());
                    if (i == 0) {
                        ((TableContrastActivity) _activity).setId1(detailBean.getData().getCarriers().get(0).getCarrierid());
                        ((TableContrastActivity) _activity).setTitle1(detailBean.getData().getCarriers().get(0).getCarrierName());
                    } else if (i == 1) {
                        ((TableContrastActivity) _activity).setId2(detailBean.getData().getCarriers().get(1).getCarrierid());
                        ((TableContrastActivity) _activity).setTitle2(detailBean.getData().getCarriers().get(1).getCarrierName());
                    } else if (i == 2) {
                        ((TableContrastActivity) _activity).setId3(detailBean.getData().getCarriers().get(2).getCarrierid());
                        ((TableContrastActivity) _activity).setTitle3(detailBean.getData().getCarriers().get(2).getCarrierName());
                    }
                    imgList.get(i).setVisibility(View.VISIBLE);
                } else {
                    titleList.get(i).setText("");
                    titleList.get(i).setBackgroundResource(R.drawable.icon_add_compare);
//                    titleList.get(i).setBackgroundResource(R.drawable.icon_image_add);
                    imgList.get(i).setVisibility(View.GONE);
                }
            }
        }
    }


    /**
     * 添加对比载体
     */
    private void add() {
        Bundle mBundle = new Bundle();
        mBundle.putString("carriertype", carriertype);
        mBundle.putStringArrayList("ids", ids);
//        UIHelper.jumpForResult(_activity, AddContrastActivity.class, mBundle, 1005);//在对比表格页添加载体
        UIHelper.jumpForResult(_activity, CarrierContrastActivity.class, mBundle, 1005);//在对比表格页添加载体
    }

    /**
     * 删除对比载体
     * @param index
     */
    private void delete(int index) {
        ((TableContrastActivity)_activity).addSame();
        listViewHelper.getAdapter().notifyDataSetChanged();
        for (NetResult item : data) {
            if (item.getItemViewType() == 0) {
                CompareDetailBean carrierBean = (CompareDetailBean) item;
                if (index < carrierBean.getData().getCarriers().size()) {
                    carrierBean.getData().getCarriers().remove(index);
                }
                ids.remove(index);
                //改变隐藏显示状态
                hideRight(carrierBean);//如果有删除操作就恢复初始状态
            }
            if (item.getItemViewType() == 2) {
                CompareDetailBean.FieldArrayValue arrayValue = (CompareDetailBean.FieldArrayValue) item;
                if (index < arrayValue.getValues().size()) {
                    arrayValue.getValues().remove(index);
                }
            }
        }
        //删除对比载体后，重新添加数据
        ((TableContrastActivity)_activity).addSame();
//        List<NetResult> resultList = (List<NetResult>) listViewHelper.getDataSource().getResultList();
//        CompareDetailBean bean = (CompareDetailBean) resultList.get(0);
//        resultList.clear();
//        resultList.addAll(bean.allDate);
        adapter.notifyDataSetChanged();
    }


    /**
     * 改变隐藏显示状态
     * @param mTabBean
     */
    private void hideRight(CompareDetailBean mTabBean) {
        ((TableContrastActivity) _activity).topbar.setRightText("隐藏相同项",((TableContrastActivity) _activity)).showRight_tv();
        ((TableContrastActivity) _activity).topbar.getRight_tv().setTextColor(getResources().getColor(R.color.contrast_bg));
        ((TableContrastActivity) _activity).ishow="1";
        if (mTabBean.getData().getCarriers().size() <= 1) {
            ((TableContrastActivity) _activity).topbar.getRight_tv().setVisibility(GONE);
        } else {
            ((TableContrastActivity) _activity).topbar.getRight_tv().setVisibility(VISIBLE);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.del_iv2:
                if (TextUtils.isEmpty(tv_2.getText().toString().trim())) {

                } else {
                    delete(0);
                }
                break;
            case R.id.del_iv3:
                if (TextUtils.isEmpty(tv_3.getText().toString().trim())) {

                } else {
                    delete(1);
                }
                break;
            case R.id.del_iv4:
                if (TextUtils.isEmpty(tv_4.getText().toString().trim())) {

                } else {
                    delete(2);
                }
                break;
            case R.id.tv_2:
                if (TextUtils.isEmpty(tv_2.getText().toString().trim())) {
                    add();
                }
                break;
            case R.id.tv_3:
                if (TextUtils.isEmpty(tv_3.getText().toString().trim())) {
                    add();
                }
                break;
            case R.id.tv_4:
                if (TextUtils.isEmpty(tv_4.getText().toString().trim())) {
                    add();
                }
                break;
        }
    }
}
