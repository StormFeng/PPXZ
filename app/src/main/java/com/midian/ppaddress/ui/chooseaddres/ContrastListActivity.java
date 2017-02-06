package com.midian.ppaddress.ui.chooseaddres;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import com.midian.ppaddress.R;
import com.midian.ppaddress.bean.MemberCompareListBean;
import com.midian.ppaddress.datasource.ContrastDataSource;
import com.midian.ppaddress.itemtpl.ContrastTpl;
import com.midian.ppaddress.utils.AppUtil;

import java.util.ArrayList;

import midian.baselib.base.BaseListActivity;
import midian.baselib.bean.NetResult;
import midian.baselib.shizhefei.view.listviewhelper.IDataAdapter;
import midian.baselib.shizhefei.view.listviewhelper.IDataSource;
import midian.baselib.utils.UIHelper;
import midian.baselib.widget.BaseLibTopbarView;

/**
 * 对比页
 * Created by chu on 2016/4/25.
 */
public class ContrastListActivity extends BaseListActivity<MemberCompareListBean.CompareData> {
    private BaseLibTopbarView topbar;
    private CheckBox add_check_tv;
    private TextView del_contrast_tv;//添加对比、开始对比||全选、删除
    private String carriertype, carrierid;
    private boolean isAdd = false;
    private int count = 0;

    private View.OnClickListener editClickListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            if (resultList.size() > 0) {
                showNormal();// 编辑状态的标题栏
            }
        }
    };
    private View.OnClickListener cancelClickListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            showEdit();// 正常标题栏
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        carrierid = mBundle.getString("carrierid");
        carriertype = mBundle.getString("carriertype");
        initView();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_contrast;
    }

    private void initView() {
        topbar = findView(R.id.topbar);
        add_check_tv = findView(R.id.add_check_tv);
        del_contrast_tv = findView(R.id.del_contrast_tv);
        add_check_tv.setOnClickListener(this);
        del_contrast_tv.setOnClickListener(this);

        add_check_tv.setChecked(false);
        adapter.setRunnable(new Runnable() {
            @Override
            public void run() {//
                boolean isAllChecked = true;
                for (MemberCompareListBean.CompareData compareData : resultList) {
                    if (compareData.isChecked()) {
                        isAllChecked = true;
                    } else {
                        isAllChecked = false;
                    }
                }
                add_check_tv.setChecked(isAllChecked);
            }
        });
        showNormal();
    }

    private void showNormal() {
        topbar.getTitleLl().setVisibility(View.VISIBLE);
        topbar.setTitle("已选择" + count + "/3").setLeftImageButton(R.drawable.icon_back, UIHelper.finish(_activity)).setRightText("编辑", cancelClickListener);
        if (isAdd) {
            add_check_tv.setText("已添加");
            add_check_tv.setEnabled(false);
        } else {
            add_check_tv.setText("添加对比");
            add_check_tv.setEnabled(true);
        }
        del_contrast_tv.setText("开始对比");
        ContrastTpl.setIsEditable(false);
        for (MemberCompareListBean.CompareData compareData : resultList) {
            compareData.setChecked(false);
        }
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onEndRefresh(IDataAdapter<ArrayList<MemberCompareListBean.CompareData>> adapter, ArrayList<MemberCompareListBean.CompareData> result) {
        super.onEndRefresh(adapter, result);

        for (MemberCompareListBean.CompareData compareData : resultList) {
            if (compareData.getCarrierid().equals(carrierid)) {
                isAdd = true;
            }
        }
        showNormal();
        if (resultList.size() > 0) {
            topbar.showRight_tv();
        } else {
            topbar.hideRight_tv();
        }
    }

    private void showEdit() {
        topbar.getTitleLl().setVisibility(View.GONE);
        topbar.setLeftImageButton(R.drawable.icon_back, UIHelper.finish(_activity)).setRightText("完成", editClickListener);
        add_check_tv.setText("全选");
        add_check_tv.setEnabled(true);
        del_contrast_tv.setText("删除");
        ContrastTpl.setIsEditable(true);
        for (MemberCompareListBean.CompareData compareData : resultList) {
            compareData.setChecked(false);
        }
        adapter.notifyDataSetChanged();
    }

    @Override
    protected IDataSource<ArrayList<MemberCompareListBean.CompareData>> getDataSource() {
        return new ContrastDataSource(_activity, mBundle.getString("carriertype"));
    }


    @Override
    protected Class getTemplateClass() {
        return ContrastTpl.class;
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.add_check_tv:

                if (ContrastTpl.isEditable()) {//编辑状态--全选
                    showEdit();
                    //全选
                    for (MemberCompareListBean.CompareData compareData : resultList) {
                        compareData.setChecked(true);
                    }
                    adapter.notifyDataSetChanged();
                } else {//非编辑状态--添加载体
                    showNormal();
                    if ("添加对比".equals(add_check_tv.getText().toString())) {
                        AppUtil.getPpApiClient(ac).memberCompareAdd(carrierid, this);//添加对比接口
                    }
                }
                break;
            case R.id.del_contrast_tv:
                StringBuilder card_ids = new StringBuilder();
                for (int i = 0; i < resultList.size(); i++) {
                    MemberCompareListBean.CompareData compareData = resultList.get(i);
                    if (compareData.isChecked()) {
                        card_ids.append("," + compareData.getId());
                    }
                }

                if (ContrastTpl.isEditable()) {//删除状态
                    if (card_ids.length() > 1) {
                        card_ids.deleteCharAt(0);
                    } else {
                        UIHelper.t(_activity, "至少选择一个要删除的载体");
                        return;
                    }
                    System.out.println("删除掉的对比id=" + card_ids.toString());
                    AppUtil.getPpApiClient(ac).memberCompareClear(card_ids.toString(), this);//9.3批量删除对比
                } else {//跳转对比
                    if (card_ids.length() > 1) {
                        card_ids.deleteCharAt(0);
                    } else {
                        UIHelper.t(_activity, "至少选择两个才能对比吖");
                        return;
                    }
                    mBundle.putString("carriertype", carriertype);
                    mBundle.putString("compareids", card_ids.toString());
                    System.out.println("传递对比参数 carriertype=" + carriertype + "--compareids=" + card_ids);
                    UIHelper.jump(_activity, TableContrastActivity.class, mBundle);//载体对比
                }
                break;
        }
    }


    @Override
    public void onApiSuccess(NetResult res, String tag) {
        super.onApiSuccess(res, tag);
        hideLoadingDlg();
        if (res.isOK()) {
            if ("memberCompareAdd".equals(tag)) {//添加对比
                /*for (MemberCompareListBean.CompareData compareData : resultList) {
                    if (carrierid.equals(compareData.getCarrierid())) {

                    } else {
                        listViewHelper.refresh();
                        add_check_tv.setText("已添加");
                        isAdd = true;
                    }
                }*/
                listViewHelper.refresh();
                add_check_tv.setText("已添加");
                isAdd = true;

            }
            if ("memberCompareClear".equals(tag)) {//删除对比
                listViewHelper.refresh();
                UIHelper.t(_activity, res.message);
            }
        } else {
            ac.handleErrorCode(_activity, res.errorcode);
        }
    }
}
