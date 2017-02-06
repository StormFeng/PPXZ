package com.midian.ppaddress.ui.chooseaddres;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.midian.ppaddress.R;
import com.midian.ppaddress.bean.MemberCommentsCounselorPageMeBean;
import com.midian.ppaddress.utils.AppUtil;

import java.util.ArrayList;
import java.util.List;

import midian.baselib.api.ApiCallback;
import midian.baselib.base.BaseFragment;
import midian.baselib.bean.NetResult;
import midian.baselib.widget.CircleImageView;
import midian.baselib.widget.ListViewForScrollView;

/**
 * Created by chu on 2016/4/28.
 */
public class ToConsultantCommentFragment extends BaseFragment implements ApiCallback {
    private TextView f2_tv,f4_tv;
    private ListView list;
    private ListViewForScrollView listViewForScrollView;
    private ToCommentAdapter toCommentAdapter;
    private List<MemberCommentsCounselorPageMeBean.Lists> dataBean = new ArrayList<MemberCommentsCounselorPageMeBean.Lists>();
    private String counselorid;

    public String getCounselorid() {
        return counselorid;
    }

    public void setCounselorid(String counselorid) {
        this.counselorid = counselorid;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_consultant_comments, null);
        f4_tv = (TextView) _activity.findViewById(R.id.f4_tv);
        f2_tv = (TextView) _activity.findViewById(R.id.f2_tv);
        listViewForScrollView = (ListViewForScrollView) v.findViewById(R.id.listview);

        loadData();
        return v;
    }

    private void loadData() {
        AppUtil.getPpApiClient(ac).getMemberCommentsCounselorPageMe(counselorid, "1", this);
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
            MemberCommentsCounselorPageMeBean bean = (MemberCommentsCounselorPageMeBean) res;
            dataBean = bean.getData().getList();
            toCommentAdapter = new ToCommentAdapter();
            listViewForScrollView.setAdapter(toCommentAdapter);
//        measureHeight(listViewForScrollView);
//            f2_tv.setText(" 对TA的评价(" + dataBean.size() + ")");
//            f4_tv.setText(" 对TA的评价(" + dataBean.size() + ")");
            toCommentAdapter.notifyDataSetChanged();
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


    class ToCommentAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return dataBean.size();
        }

        @Override
        public Object getItem(int position) {
            return dataBean.get(position);
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
                convertView = inflater.inflate(R.layout.item_consultant_comment_tpl, null);
                mHolder.head_cv = (CircleImageView) convertView.findViewById(R.id.head_cv);
                mHolder.name_tv = (TextView) convertView.findViewById(R.id.name_tv);
                mHolder.comment_type_tv = (TextView) convertView.findViewById(R.id.rate_tv);
                mHolder.time_tv = (TextView) convertView.findViewById(R.id.time_tv);
                mHolder.content_tv = (TextView) convertView.findViewById(R.id.content_tv);
                convertView.setTag(mHolder);
            }
            mHolder = (ViewHolder) convertView.getTag();

            if (dataBean == null) {
                ac.setImage(mHolder.head_cv, R.drawable.icon_head);
            } else {
                ac.setImage(mHolder.head_cv, dataBean.get(position).getPortrait());
            }
            mHolder.name_tv.setText(dataBean.get(position).getMember());
            String rate = dataBean.get(position).getRate();
            if ("0".equals(rate)) {
//                mHolder.comment_type_tv.setVisibility(View.GONE);
                mHolder.comment_type_tv.setText("无");
                mHolder.comment_type_tv.setBackgroundColor(getResources().getColor(R.color.dp_gray));
            } else if ("1".equals(rate)) {
                mHolder.comment_type_tv.setText("好评");
                mHolder.comment_type_tv.setBackgroundColor(getResources().getColor(R.color.red));
            }else if ("2".equals(rate)) {
                mHolder.comment_type_tv.setText("中评");
                mHolder.comment_type_tv.setBackgroundColor(getResources().getColor(R.color.orange_button));
            }else if ("3".equals(rate)) {
                mHolder.comment_type_tv.setText("差评");
                mHolder.comment_type_tv.setBackgroundColor(getResources().getColor(R.color.dp_gray));
            }
            mHolder.time_tv.setText(dataBean.get(position).getCreateTime());
            mHolder.content_tv.setText(dataBean.get(position).getContent());

            return convertView;
        }

        public class ViewHolder {
            CircleImageView head_cv;
            TextView name_tv, comment_type_tv, time_tv, content_tv;
        }
    }

   /* @Override
    protected IDataSource<ArrayList> getDataSource() {
        return new ConsultantCommentDataSource(_activity);
    }
    @Override
    protected Class getTemplateClass() {
        return ConsultantCommentTpl.class;
    }
*/


}
