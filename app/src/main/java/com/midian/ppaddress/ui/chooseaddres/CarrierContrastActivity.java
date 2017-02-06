package com.midian.ppaddress.ui.chooseaddres;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;

import com.midian.ppaddress.R;
import com.midian.ppaddress.bean.MemberCompareListBean.CompareData;
import com.midian.ppaddress.datasource.CarrierDataSource;
import com.midian.ppaddress.itemtpl.CarrierTpl;
import com.midian.ppaddress.utils.AppUtil;

import java.util.ArrayList;

import midian.baselib.app.AppManager;
import midian.baselib.base.BaseListActivity;
import midian.baselib.bean.NetResult;
import midian.baselib.shizhefei.view.listviewhelper.IDataAdapter;
import midian.baselib.shizhefei.view.listviewhelper.IDataSource;
import midian.baselib.utils.UIHelper;
import midian.baselib.widget.BaseLibTopbarView;

/**
 * 对比页添加截体对比的列表
 * Created by chu on 2016/4/25.
 */
public class CarrierContrastActivity extends BaseListActivity<CompareData> {

    private BaseLibTopbarView topbar;
    private ArrayList<String> ids;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ids = mBundle.getStringArrayList("ids");
        initView();
    }

    private void initView() {
        topbar = findView(R.id.topbar);
        topbar.setTitle("载体对比").setLeftImageButton(R.drawable.icon_back, UIHelper.finish(_activity));
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_carrier_contrat;
    }

    String cid;

    @Override
    protected IDataSource<ArrayList<CompareData>> getDataSource() {
        return new CarrierDataSource(_activity, mBundle.getString("carriertype"));
    }

    @Override
    protected Class getTemplateClass() {
        return CarrierTpl.class;
    }


    @Override
    public void onEndRefresh(IDataAdapter<ArrayList<CompareData>> adapter, ArrayList<CompareData> result) {
        super.onEndRefresh(adapter, result);
        for (int i = 0; i < ids.size(); i++) {
            cid = ids.get(i);
            Log.d("cid", "activity中已对比载体id=" + cid);
            for (int j = 0; j < resultList.size(); j++) {
                if (cid.equals(resultList.get(j).getId())) {
                    resultList.remove(j);
//                    adapter.notifyDataSetChanged();
                    listViewHelper.getAdapter().notifyDataSetChanged();
                }
            }
        }

    }

    @Override
    public void onItemClick(AdapterView parent, View view, int position, long id) {
        this.position = position;
        try {
            AppUtil.getPpApiClient(ac).memberCompareDetailList(resultList.get(position).getCarrierType(), resultList.get(position).getId(),this);//
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    int position;

    @Override
    public void onApiStart(String tag) {
        super.onApiStart(tag);
        showLoadingDlg("", true);
    }

    @Override
    public void onApiSuccess(NetResult res, String tag) {
        super.onApiSuccess(res, tag);
        hideLoadingDlg();
        if (res.isOK()) {
            AppManager.getAppManager().finishActivity(TableContrastActivity.class);
          /*  Bundle mBundle = new Bundle();
            mBundle.putString("id", resultList.get(position).getId());
            Log.d("-=-=-=-=-=-=-", "添加返回的id: onItemClick" + resultList.get(position).getId());
            CompareDetailBean dataBean = (CompareDetailBean) res;
            mBundle.putSerializable("dataBean",dataBean);*/
            ids.add(resultList.get(position).getId());
            StringBuilder comdis = new StringBuilder();
            for (int i = 0; i < ids.size(); i++) {
                comdis.append("," + ids.get(i));
            }
            if (comdis.length() > 1) {
                comdis.deleteCharAt(0);
            }
            TableContrastActivity.gotoActivity(_activity, resultList.get(position).getCarrierType(),comdis.toString());
           /* Bundle bundle=new Bundle();
            bundle.putString("compareids",comdis.toString());
            bundle.putString("carriertype",resultList.get(position).getCarrierType());
            TableContrastActivity.flag++;
            setResult(RESULT_OK, bundle);*/
            finish();
        } else {
            ac.handleErrorCode(_activity, res.errorcode);
        }
    }
}
