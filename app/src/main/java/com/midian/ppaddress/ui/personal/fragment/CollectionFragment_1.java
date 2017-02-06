package com.midian.ppaddress.ui.personal.fragment;

import android.util.Log;

import com.midian.ppaddress.bean.MemberFavoritePageCarrierBean.CarrierCollectList;
import com.midian.ppaddress.datasource.MyCollectionTab1DataResource;
import com.midian.ppaddress.itemtpl.MyCollectionTab1tpl;
import com.midian.ppaddress.ui.personal.MyCollectionActivity;
import com.midian.ppaddress.utils.AppUtil;

import java.util.ArrayList;

import midian.baselib.api.ApiCallback;
import midian.baselib.base.BaseListFragment;
import midian.baselib.bean.NetResult;
import midian.baselib.shizhefei.view.listviewhelper.IDataAdapter;
import midian.baselib.shizhefei.view.listviewhelper.IDataSource;
import midian.baselib.utils.UIHelper;

public class CollectionFragment_1 extends BaseListFragment<CarrierCollectList> implements ApiCallback {
 /*   @Override
    protected int getLayoutId() {
        return R.layout.comman_list_item_notitle;
    }*/
    public static ArrayList<CarrierCollectList> carrierCollectList;
    @Override
    protected IDataSource getDataSource() {
        return new MyCollectionTab1DataResource(_activity);
    }

    @Override
    protected Class getTemplateClass() {
        return MyCollectionTab1tpl.class;
    }


    public void allSelect(boolean isSelect) {
        for (CarrierCollectList item : listViewHelper.getAdapter().getData()) {
            item.setCheck(isSelect);
        }
        listViewHelper.getAdapter().notifyDataSetChanged();
    }

    public void isEditMode(boolean isEditPut) {
        for (CarrierCollectList mItem : listViewHelper.getAdapter().getData()) {
            mItem.setEdit(isEditPut);
        }
        listViewHelper.getAdapter().notifyDataSetChanged();
    }

    public void getDataDoctor(boolean isCheck, String record_type) {
        StringBuilder card_ids = new StringBuilder();
        for (CarrierCollectList mItem : listViewHelper.getAdapter().getData()) {
            if (mItem.isCheck()) {
                card_ids.append("," + mItem.getFavoriteid());
            }
        }
        if (card_ids.length() > 1) {
            card_ids.deleteCharAt(0);
        } else {
            UIHelper.t(_activity, "至少选择一个要删除的载体");
            return;
        }
        AppUtil.getPpApiClient(ac).memberFavoriteBulkCancelCarrier(card_ids.toString(), this);// 删除收藏接口
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
            UIHelper.t(_activity, res.message);
            for (int i = listViewHelper.getAdapter().getData().size() - 1; i >= 0; i--) {
                CarrierCollectList mItems = listViewHelper.getAdapter().getData().get(i);
                if (listViewHelper.getAdapter().getData().get(i).isCheck()) {
                    listViewHelper.getAdapter().getData().remove(mItems);
                }
            }
            adapter.notifyDataSetChanged();
            listViewHelper.refresh();
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

    /**
     * 数据刷新加载结束时
     */
    @Override
    public void onEndRefresh(IDataAdapter<ArrayList<CarrierCollectList>> adapter, ArrayList<CarrierCollectList> result) {
        super.onEndRefresh(adapter, result);
        carrierCollectList=result;
        if (carrierCollectList.size()<= 0) {
            ((MyCollectionActivity) _activity).showEmpty();
        }
    }
}
