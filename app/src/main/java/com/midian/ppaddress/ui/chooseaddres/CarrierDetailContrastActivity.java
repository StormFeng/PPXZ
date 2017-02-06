package com.midian.ppaddress.ui.chooseaddres;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.midian.ppaddress.R;
import com.midian.ppaddress.bean.MemberCompareListBean;
import com.midian.ppaddress.datasource.MyContrastChooseDataResource;
import com.midian.ppaddress.itemtpl.MyContrastChooseTpl;
import com.midian.ppaddress.utils.AppUtil;

import java.util.ArrayList;

import midian.baselib.base.BaseListActivity;
import midian.baselib.bean.NetResult;
import midian.baselib.shizhefei.view.listviewhelper.IDataAdapter;
import midian.baselib.shizhefei.view.listviewhelper.IDataSource;
import midian.baselib.utils.UIHelper;
import midian.baselib.widget.BaseLibTopbarView;

/**
 * 载体详情》对比载体列表
 */
public class CarrierDetailContrastActivity extends BaseListActivity<MemberCompareListBean> {
    private BaseLibTopbarView topbar;
    private String carrierType, carrierid;
    private LinearLayout ll_btn;
    private Button btn_delete, btn_selectall;
    private int flag = 0;
    private TextView title_tv;
    private CheckBox cb;
    private StringBuilder card_ids;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }


    @Override
    protected int getLayoutId() {
        return R.layout.activity_carrier_contrast;
    }

    @Override
    protected IDataSource<ArrayList<MemberCompareListBean>> getDataSource() {
        carrierType = mBundle.getString("carriertype");
        carrierid = mBundle.getString("carrierid");
        return new MyContrastChooseDataResource(carrierType, _activity);
    }

    @Override
    protected Class getTemplateClass() {
        return MyContrastChooseTpl.class;
    }

    @Override
    public void onEndRefresh(IDataAdapter<ArrayList<MemberCompareListBean>> adapter, ArrayList<MemberCompareListBean> result) {
        super.onEndRefresh(adapter, result);
        title_tv.setText("已选择" + listView.getCheckedItemCount() + "/" + resultList.size());
        if(resultList.size()<=0){
            btn_selectall.setText("添加对比");
            btn_selectall.setTextColor(getResources().getColor(R.color.blue));
        }
        for (int i = 0; i < resultList.size(); i++) {
            if (carrierid.equals(resultList.get(i).getData().get(i).getCarrierid())) {
                btn_selectall.setText("已添加");
                btn_selectall.setTextColor(getResources().getColor(R.color.dp_gray));
                return;
            } else {
                btn_selectall.setText("添加对比");
                btn_selectall.setTextColor(getResources().getColor(R.color.blue));
            }
        }
//        topbar.getRight_tv().performClick();
    }

    private void initView() {
        findViewById(R.id.ll_btn).setVisibility(View.VISIBLE);
        topbar = findView(R.id.topbar);
        btn_delete = findView(R.id.btn_delete);
        btn_selectall = findView(R.id.btn_selectall);
        btn_delete.setText("开始对比");
        topbar.setLeftImageButton(R.drawable.icon_back, UIHelper.finish(_activity));
        title_tv = topbar.getTitle_tv();
        title_tv.setText("已选择" + listView.getCheckedItemCount() + "/" + (listView.getAdapter().getCount() - 1));
        title_tv.setTextColor(getResources().getColor(R.color.text_bg90));
        btn_delete.setOnClickListener(this);
        btn_selectall.setOnClickListener(this);//全选
        listView.setSelector(R.drawable.listview_selector);
        listView.setDescendantFocusability(ViewGroup.FOCUS_BLOCK_DESCENDANTS);
        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        listView.setOnItemClickListener(this);
        topbar.setRightText("编辑", this);

        btn_selectall.setBackgroundColor(getResources().getColor(R.color.white));
        btn_selectall.setTextColor(getResources().getColor(R.color.blue));
        btn_delete.setBackgroundColor(getResources().getColor(R.color.blue));
        btn_delete.setTextColor(getResources().getColor(R.color.white));

    }

    @Override
    public void onItemClick(AdapterView parent, View view, int position, long id) {
        super.onItemClick(parent, view, position, id);
        title_tv.setText("已选择" + listView.getCheckedItemCount() + "/" + (listView.getAdapter().getCount() - 1));
        if (resultList.get(position).getData().get(position).isChecked()) {
            resultList.get(position).getData().get(position).setChecked(false);
        } else {
            resultList.get(position).getData().get(position).setChecked(true);
        }
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onClick(View arg0) {
        super.onClick(arg0);
        card_ids = new StringBuilder();
        switch (arg0.getId()) {
            case R.id.right_tv:
                if (flag == 0) {
                    flag = 1;
                    topbar.getRight_tv().setText("完成");
                    btn_delete.setText("删除");
                    for (int i = 0; i < listView.getAdapter().getCount() - 1; i++) {
                        if (listView.isItemChecked(i)) {
                            btn_selectall.setText("取消全选");
                            btn_selectall.setTextColor(getResources().getColor(R.color.red));
                        } else {
                            btn_selectall.setText("全选");
                            btn_selectall.setTextColor(getResources().getColor(R.color.blue));
                            return;
                        }
                    }
                } else if (flag == 1) {
                    flag = 0;
                    topbar.getRight_tv().setText("编辑");
                    btn_delete.setText("开始对比");
                    btn_delete.setTextColor(getResources().getColor(R.color.white));
                    btn_selectall.setTextColor(getResources().getColor(R.color.blue));
                    if (resultList.size() == 0) {
                        btn_selectall.setText("添加对比");
                    }
                    for (int i = 0; i < resultList.size(); i++) {
                        if (carrierid.equals(resultList.get(i).getData().get(i).getCarrierid())) {
                            Log.d("wqf", resultList.get(i).getData().get(i).getCarrierid());
                            btn_selectall.setText("已添加");
                            btn_selectall.setTextColor(getResources().getColor(R.color.dp_gray));
                            return;
                        } else {
                            btn_selectall.setText("添加对比");
                            btn_selectall.setTextColor(getResources().getColor(R.color.blue));
                        }
                    }
                }
                break;
            case R.id.btn_selectall:
                if ("已添加".equals(btn_selectall.getText().toString().trim())) {
                    UIHelper.t(_activity, "已添加该载体");
                    return;
                }
                if ("添加对比".equals(btn_selectall.getText().toString().trim())) {
                    AppUtil.getPpApiClient(ac).memberCompareAdd(carrierid, this);
                    return;
                }
                if ("全选".equals(btn_selectall.getText().toString().trim())) {
                    for (int i = 0; i < listView.getAdapter().getCount() - 1; i++) {
                        if (!listView.isItemChecked(i)) {
                            View view = listView.getAdapter().getView(i, null, null);
                            listView.performItemClick(view, i, listView.getItemIdAtPosition(i));
                        }
                    }
                    btn_selectall.setText("取消全选");
                    btn_selectall.setTextColor(getResources().getColor(R.color.red));
                    return;
                }
                if ("取消全选".equals(btn_selectall.getText().toString().trim())) {
                    for (int i = 0; i < listView.getAdapter().getCount() - 1; i++) {
                        if (listView.isItemChecked(i)) {
                            View view = listView.getAdapter().getView(i, null, null);
                            listView.performItemClick(view, i, listView.getItemIdAtPosition(i));
                        }
                    }
                    btn_selectall.setText("全选");
                    btn_selectall.setTextColor(getResources().getColor(R.color.blue));
                }
                break;
            case R.id.btn_delete:
                if ("删除".equals(btn_delete.getText().toString().trim())) {
                    for (int i = 0; i < listView.getAdapter().getCount() - 1; i++) {
                        String id = resultList.get(i).getData().get(i).getId();
                        if (listView.isItemChecked(i)) {
                            card_ids.append("," + id);
                            Log.d("wqf", card_ids.toString());
                        }
                    }
                    if (card_ids.length() > 1) {
                        card_ids.deleteCharAt(0);
                    } else {
                        UIHelper.t(_activity, "至少选择一个要删除的载体");
                        return;
                    }
                    System.out.println("删除掉的对比id=" + card_ids.toString());
                    AppUtil.getPpApiClient(ac).memberCompareClear(card_ids.toString(), this);
                } else {
                    if (listView.getCheckedItemCount() < 2) {
                        UIHelper.t(_activity, "至少选择两个才能对比吖");
                        return;
                    }
                    if (listView.getCheckedItemCount() > 3) {
                        UIHelper.t(_activity, "最多只能选择三个载体");
                        return;
                    }
                    for (int i = 0; i < resultList.size(); i++) {
                        String id = resultList.get(i).getData().get(i).getId();
                        if (resultList.get(i).getData().get(i).isChecked()) {
                            card_ids.append("," + id);
                        }
                    }
                    if (card_ids.length() > 1) {
                        card_ids.deleteCharAt(0);
                    }
                    mBundle.putString("carriertype", carrierType);
                    mBundle.putString("compareids", card_ids.toString());
                    mBundle.putString("carrierid", carrierid);
                    UIHelper.jump(_activity, TableContrastActivity.class, mBundle);//载体对比
                }
                break;
        }
    }

    @Override
    public void onApiSuccess(NetResult res, String tag) {
        super.onApiSuccess(res, tag);
        if (res.isOK()) {
            if ("memberCompareClear".equals(tag)) {
                UIHelper.t(_activity, res.message);
                for (int i = 0; i < resultList.size(); i++) {
                    if (resultList.get(i).getData().get(i).isChecked()) {
                        resultList.get(i).getData().get(i).setChecked(false);
                        listView.setItemChecked(i, false);
                        adapter.notifyDataSetChanged();
                    }
                }
                title_tv.setText("已选择" + listView.getCheckedItemCount() + "/" + resultList.size());
                listViewHelper.refresh();
            }
            if ("memberCompareAdd".equals(tag)) {
                UIHelper.t(_activity, res.message);
                title_tv.setText("已选择" + listView.getCheckedItemCount() + "/" + (listView.getAdapter().getCount() - 1));
                listViewHelper.refresh();
            }
        } else {
            ac.handleErrorCode(_activity, res.message);
        }
    }
}
