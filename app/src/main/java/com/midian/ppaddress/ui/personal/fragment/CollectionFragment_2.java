package com.midian.ppaddress.ui.personal.fragment;

import android.util.Log;
import android.view.View;

import com.midian.ppaddress.R;
import com.midian.ppaddress.bean.MemberFavoritePageAgencyBean.AgencyList;
import com.midian.ppaddress.datasource.MyCollectionTab2DataResource;
import com.midian.ppaddress.itemtpl.MyCollectionTab2tpl;
import com.midian.ppaddress.ui.personal.MyCollectionActivity;
import com.midian.ppaddress.utils.AppUtil;

import java.util.ArrayList;

import midian.baselib.api.ApiCallback;
import midian.baselib.base.BaseListFragment;
import midian.baselib.bean.NetResult;
import midian.baselib.shizhefei.view.listviewhelper.IDataAdapter;
import midian.baselib.shizhefei.view.listviewhelper.IDataSource;
import midian.baselib.utils.UIHelper;

/**
 * 首页
 * Created by chu on 2016/2/15.
 */
public class CollectionFragment_2 extends BaseListFragment<AgencyList> implements ApiCallback {

/*    @Override
    protected int getLayoutId() {
        return R.layout.comman_list_item_notitle;
    }*/
    public static ArrayList<AgencyList> agencyList;
    @Override
    protected IDataSource getDataSource() {
        return new MyCollectionTab2DataResource(_activity);
    }

    @Override
    protected Class getTemplateClass() {
        return MyCollectionTab2tpl.class;
    }


    public void allSelect(boolean isSelect) {
        for (AgencyList item : listViewHelper.getAdapter().getData()) {
            item.setCheck(isSelect);
        }
        listViewHelper.getAdapter().notifyDataSetChanged();
    }

    public void isEditMode(boolean isEditPut) {
        for (AgencyList mItem : listViewHelper.getAdapter().getData()) {
            mItem.setEdit(isEditPut);
        }
        listViewHelper.getAdapter().notifyDataSetChanged();
    }


    public void getDataNews(boolean isCheck, String record_type) {
        StringBuilder card_ids = new StringBuilder();
        for (AgencyList mItem : listViewHelper.getAdapter().getData()) {
            if (mItem.isCheck()) {
                card_ids.append("," + mItem.getFavoriteid());
            }
        }
        if (card_ids.length() > 1) {
            card_ids.deleteCharAt(0);
        } else {
            UIHelper.t(_activity, "至少选择一个要删除的服务机构");
            return;
        }
        AppUtil.getPpApiClient(ac).memberFavoriteBulkCancelAgency(card_ids.toString(), this);// 删除收藏接口
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
            if ("memberFavoriteBulkCancelAgency".equals(tag)) {
                UIHelper.t(_activity, res.message);
                for (int i = listViewHelper.getAdapter().getData().size() - 1; i >= 0; i--) {
                    AgencyList mItems = listViewHelper.getAdapter().getData().get(i);
                    if (listViewHelper.getAdapter().getData().get(i).isCheck()) {
                        listViewHelper.getAdapter().getData().remove(mItems);
                    }
                }
                adapter.notifyDataSetChanged();
                listViewHelper.refresh();
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


    @Override
    public void onEndRefresh(IDataAdapter<ArrayList<AgencyList>> adapter, ArrayList<AgencyList> result) {
        super.onEndRefresh(adapter, result);
        Log.d("onEndRefresh", "onEndRefresh: 服务机构收藏="+result.size());
        agencyList=result;
        if (agencyList.size()<= 0) {
            ((MyCollectionActivity) _activity).showEmpty();
        }
    }

//    @Override
//    public void onResume() {
//        super.onResume();
//        try {
//            if (agencyList.size()<= 0) {
//                ((MyCollectionActivity) _activity).showEmpty();
//                Log.d("wqf","隐藏");
//            }else{
//                ((MyCollectionActivity) _activity).showNormal();
//                Log.d("wqf","执行");
//            }
//        } catch (Exception e) {
//        }
//    }
}
