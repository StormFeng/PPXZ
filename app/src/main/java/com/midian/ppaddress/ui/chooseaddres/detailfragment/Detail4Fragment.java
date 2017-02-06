package com.midian.ppaddress.ui.chooseaddres.detailfragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.midian.ppaddress.R;
import com.midian.ppaddress.bean.BusinessCommentsMembersBean;
import com.midian.ppaddress.utils.AppUtil;

import java.util.ArrayList;
import java.util.List;

import midian.baselib.api.ApiCallback;
import midian.baselib.base.BaseFragment;
import midian.baselib.bean.NetResult;
import midian.baselib.widget.CircleImageView;
import midian.baselib.widget.ListViewForScrollView;

/**
 * 载体详情-- 用户点评fragment
 * Created by chu on 2016/3/23.
 */
public class Detail4Fragment extends BaseFragment implements ApiCallback {
    private ListViewForScrollView listView4;
    private String carrierid;
    private Detail4Adapter detail4Adapter;
    private List<BusinessCommentsMembersBean.CommentMembersList> dataList4 = new ArrayList<BusinessCommentsMembersBean.CommentMembersList>();
    private TextView wait_tv, empty_tv;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_detail4, null);
        listView4 = (ListViewForScrollView) v.findViewById(R.id.listView4);
        wait_tv = (TextView) v.findViewById(R.id.wait_tv);
        empty_tv = (TextView) v.findViewById(R.id.empty_tv);
        detail4Adapter = new Detail4Adapter();
        listView4.setAdapter(detail4Adapter);
        loadData();
        return v;
    }

    private void loadData() {
        AppUtil.getPpApiClient(ac).businessCarrierCommentsPageMembers(carrierid, "1", "10", this);
    }

    @Override
    public void onApiStart(String tag) {

    }

    @Override
    public void onApiLoading(long count, long current, String tag) {

    }

    @Override
    public void onApiSuccess(NetResult res, String tag) {
        wait_tv.setVisibility(View.GONE);
        _activity.hideLoadingDlg();
        if (res.isOK()) {
            BusinessCommentsMembersBean bean = (BusinessCommentsMembersBean) res;
            dataList4 = bean.getData().getList();
            if (dataList4 != null && dataList4.size() > 0) {
                detail4Adapter.notifyDataSetChanged();
                empty_tv.setVisibility(View.GONE);
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


    class Detail4Adapter extends BaseAdapter {

        @Override
        public int getCount() {
            if (dataList4 != null && dataList4.size() > 0) {
                if (dataList4.size() > 4) {
                    return 4;
                } else {
                    return dataList4.size();
                }
            } else {
                return 0;
            }
        }

        @Override
        public Object getItem(int position) {
            return dataList4.get(position);
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
                convertView = inflater.inflate(R.layout.item_comment_tpl, null);
                mHolder.head_cv = (CircleImageView) convertView.findViewById(R.id.head_cv);
                mHolder.name_tv = (TextView) convertView.findViewById(R.id.name_tv);
                mHolder.time_tv = (TextView) convertView.findViewById(R.id.time_tv);
                mHolder.content_tv = (TextView) convertView.findViewById(R.id.content_tv);
                mHolder.state_tv = (TextView) convertView.findViewById(R.id.state_tv);
                convertView.setTag(mHolder);
            }
            mHolder = (ViewHolder) convertView.getTag();
            //渲染数据

            if (dataList4.get(position).getImages() == null) {
                ac.setImage(mHolder.head_cv, R.drawable.head1);
            } else {
                ac.setImage(mHolder.head_cv, dataList4.get(position).getImages());
            }
            mHolder.name_tv.setText(dataList4.get(position).getName());
            mHolder.time_tv.setText(dataList4.get(position).getCreateTime());
            mHolder.content_tv.setText(dataList4.get(position).getContent());
            String rate = dataList4.get(position).getRate();//评论等级，0无，1为好评，2为中评，3为差评
            if ("0".equals(rate)) {
                mHolder.state_tv.setVisibility(View.GONE);
            } else if ("1".equals(rate)) {
                mHolder.state_tv.setText("好评");
                mHolder.state_tv.setBackgroundResource(R.color.my_appraise);
            } else if ("2".equals(rate)) {
                mHolder.state_tv.setText("中评");
                mHolder.state_tv.setBackgroundResource(R.color.comment_bg2);
            } else if ("3".equals(rate)) {
                mHolder.state_tv.setText("差评");
                mHolder.state_tv.setBackgroundResource(R.color.comment_bg3);
            }
            return convertView;
        }

        public class ViewHolder {
            private TextView name_tv, time_tv, content_tv, state_tv;
            private CircleImageView head_cv;
        }
    }


    public String getCarrierid() {
        return carrierid;
    }

    public void setCarrierid(String carrierid) {
        this.carrierid = carrierid;
    }
    /*   @Override
    protected int getLayoutId() {
        return R.layout.fragment_more_comment;
    }*/

   /* @Override
    protected IDataSource<ArrayList> getDataSource() {
        return new MoreCommentDataSource(_activity, carrierid);
    }

    @Override
    protected Class getTemplateClass() {
        return MoreCommentFragmentTpl.class;
    }*/


}
