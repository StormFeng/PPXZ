package com.midian.ppaddress.ui.chooseaddres.detailfragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.midian.ppaddress.R;
import com.midian.ppaddress.bean.BusinessCarrierCommentsPageCounselorsBean;
import com.midian.ppaddress.bean.BusinessCarrierCommentsPageExpertsBean;
import com.midian.ppaddress.utils.AppUtil;

import java.util.ArrayList;
import java.util.List;

import midian.baselib.api.ApiCallback;
import midian.baselib.base.BaseFragment;
import midian.baselib.bean.NetResult;
import midian.baselib.utils.FDDataUtils;
import midian.baselib.utils.UIHelper;
import midian.baselib.widget.CircleImageView;
import midian.baselib.widget.ListViewForScrollView;

/**
 * 专家点评
 * Created by chu on 2016/3/23.
 */
public class Detail6Fragment extends BaseFragment implements ApiCallback {

    private ListViewForScrollView listView6;
    private String carrierid;
    private Detail6Adapter detail6Adapter;
    private List<BusinessCarrierCommentsPageExpertsBean.CommentList> datalist6 = new ArrayList<>();
    private TextView wait_tv, empty_tv;


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_detail6, null);
        listView6 = (ListViewForScrollView) v.findViewById(R.id.listView6);
        wait_tv = (TextView) v.findViewById(R.id.wait_tv);
        empty_tv = (TextView) v.findViewById(R.id.empty_tv);
        detail6Adapter = new Detail6Adapter();
        listView6.setAdapter(detail6Adapter);

        loadData();
        return v;
    }

    private void loadData() {
//        BusinessCarrierCommentsPageExpertsBean bean =
        AppUtil.getPpApiClient(ac).businessCarrierCommentsPageExperts(carrierid, "1", "10", this);
    }

    public String getCarrierid() {
        return carrierid;
    }

    public void setCarrierid(String carrierid) {
        this.carrierid = carrierid;
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
            BusinessCarrierCommentsPageExpertsBean bean = (BusinessCarrierCommentsPageExpertsBean) res;
            datalist6 = bean.getData().getList();
            if (datalist6 != null && datalist6.size() > 0) {
                detail6Adapter.notifyDataSetChanged();
                empty_tv.setVisibility(View.GONE);
            } else {
                empty_tv.setVisibility(View.VISIBLE);
            }

        } else {
            ac.handleErrorCode(_activity, res.errorcode);
        }

    }

    class Detail6Adapter extends BaseAdapter {

        @Override
        public int getCount() {
            if (datalist6 != null && datalist6.size() > 0) {
                if (datalist6.size() > 4) {
                    return 4;
                } else {
                    return datalist6.size();
                }
            } else {
                return 0;
            }
        }

        @Override
        public Object getItem(int i) {
            return datalist6.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(final int i, View view, ViewGroup viewGroup) {
            ViewHolder holder;
            if (view == null) {
                holder = new ViewHolder();
                LayoutInflater inflater = LayoutInflater.from(_activity);
                view = inflater.inflate(R.layout.item_comment_fragment_tpl, null);
                holder.head_cv = (CircleImageView) view.findViewById(R.id.head_cv);
                holder.name_tv = (TextView) view.findViewById(R.id.name_tv);
                holder.time_tv = (TextView) view.findViewById(R.id.time_tv);
                holder.content_tv = (TextView) view.findViewById(R.id.content_tv);
                holder.zan_iv = (ImageView) view.findViewById(R.id.zan_iv);
                holder.zan_count_tv = (TextView) view.findViewById(R.id.zan_count_tv);
                holder.zan_ll = view.findViewById(R.id.zan_ll);
                view.setTag(holder);
            }
            holder = (ViewHolder) view.getTag();
            //渲染数据
            final BusinessCarrierCommentsPageExpertsBean.CommentList bean = datalist6.get(i);

            final String commentid = bean.getId();
            if (bean.getImages() == null) {
                ac.setImage(holder.head_cv, R.drawable.head1);
            } else {
                ac.setImage(holder.head_cv, bean.getImages());
            }
            if (bean.getImages() != null) {
                ac.setImage(holder.head_cv, bean.getImages());
            }
            holder.name_tv.setText(bean.getName());
            holder.time_tv.setText(bean.getCreateTime());
            holder.content_tv.setText(bean.getContent());
           holder. like_count = Integer.parseInt(bean.getLikeCount());
            holder.zan_count_tv.setText(holder.like_count + "");
            if ("0".equals(bean.getIsLike())) {
                ac.setImage(holder.zan_iv, R.drawable.icon_zan_n);
                holder.likeType = "1";
            } else {
                ac.setImage(holder.zan_iv, R.drawable.icon_zan_p);
                holder.likeType = "0";
            }
            final ViewHolder finalHolder = holder;
            holder.zan_ll.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //角色类型--0为客商，1为客商中介，2为物业顾问，3为业主，4为服务达人
                    //只有客商这个角色才能使用该接口，有物业顾问。专家点评列表才有点赞。客商点评列表不能点赞。
                    if ("0".equals(ac.getProperty("user_type"))) {
                        AppUtil.getPpApiClient(ac).businessCarrierCommentsClickLike(commentid, new ApiCallback() {
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
                                if (res.isOK()) {//0：没有点赞 1：已点赞
                                    if (finalHolder.likeType.equals("1")) {
                                        UIHelper.t(_activity,"点赞成功！");
                                        finalHolder.likeType = "0";
                                        bean.setIsLike("1");
                                        finalHolder.like_count = FDDataUtils.getInteger(bean.getLikeCount()) + 1;
                                        ac.setImage(finalHolder.zan_iv, R.drawable.icon_zan_p);
                                        bean.setLikeCount(finalHolder.like_count + "");
                                        detail6Adapter.notifyDataSetChanged();
                                    } else {
                                        UIHelper.t(_activity,"取消点赞成功！");
                                        finalHolder.likeType = "1";
                                        bean.setIsLike("0");
                                        ac.setImage(finalHolder.zan_iv, R.drawable.icon_zan_n);
                                        finalHolder.like_count = FDDataUtils.getInteger(bean.getLikeCount()) - 1;
                                        finalHolder.zan_count_tv.setText(finalHolder.like_count+"");
                                        bean.setLikeCount(finalHolder.like_count + "");
                                        detail6Adapter.notifyDataSetChanged();
                                    }

                                    UIHelper.t(_activity, res.message);

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
                        });
                    } else {
                        UIHelper.t(_activity, "切换到普通用户身份使用点赞功能！");
                    }
                }
            });
            return view;
        }

        public class ViewHolder {
            private TextView name_tv, time_tv, content_tv, zan_count_tv;
            private ImageView zan_iv;
            private CircleImageView head_cv;
            private View zan_ll;
            private String  likeType;
            private  int like_count = 0;
        }
    }

    @Override
    public void onApiFailure(Throwable t, int errorNo, String strMsg, String tag) {

    }

    @Override
    public void onParseError(String tag) {

    }

  /*  @Override
    protected IDataSource<ArrayList> getDataSource() {
        return new ExpertsFragmentDataSource(_activity,carrierid);
    }

    @Override
    protected Class getTemplateClass() {
        return ExpertsFragmentTpl.class;
    }
*/
}
