package com.midian.ppaddress.itemtpl;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.midian.ppaddress.R;
import com.midian.ppaddress.bean.BusinessCarrierCommentsPageExpertsBean.CommentList;
import com.midian.ppaddress.ui.chooseaddres.MoreCommentActivity;
import com.midian.ppaddress.utils.AppUtil;

import midian.baselib.api.ApiCallback;
import midian.baselib.bean.NetResult;
import midian.baselib.utils.FDDataUtils;
import midian.baselib.utils.UIHelper;
import midian.baselib.view.BaseTpl;
import midian.baselib.widget.CircleImageView;

/**
 * Created by chu on 2016/4/21.
 */
public class MoreExpertsCommentTpl extends BaseTpl<CommentList> implements View.OnClickListener, ApiCallback {

    private TextView name_tv, time_tv, content_tv, zan_count_tv;
    private ImageView zan_iv;
    private CircleImageView head_cv;
    private View zan_ll;
    private String commentid, likeType;
    private int like_count;
    private CommentList bean;


    public MoreExpertsCommentTpl(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MoreExpertsCommentTpl(Context context) {
        super(context);
    }

    @Override
    protected void initView() {
        head_cv = (CircleImageView) findViewById(R.id.head_cv);
        name_tv = (TextView) findViewById(R.id.name_tv);
        time_tv = (TextView) findViewById(R.id.time_tv);
        content_tv = (TextView) findViewById(R.id.content_tv);
        zan_iv = (ImageView) findViewById(R.id.zan_iv);
        zan_count_tv = findView(R.id.zan_count_tv);
        zan_ll = findView(R.id.zan_ll);
        zan_ll.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        //角色类型--0为客商，1为客商中介，2为物业顾问，3为业主，4为服务达人
        //只有客商这个角色才能使用该接口，有物业顾问。专家点评列表才有点赞。客商点评列表不能点赞。
        if ("0".equals(ac.getProperty("user_type"))) {
            AppUtil.getPpApiClient(ac).businessCarrierCommentsClickLike(commentid, this);
        }else {
            UIHelper.t(_activity, "切换到普通用户身份使用点赞功能！");
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.item_more_experts_comment_tpl;
    }

    @Override
    public void setBean(CommentList bean, int position) {
        this.bean = bean;
        commentid = bean.getId();
        if (bean.getImages() == null) {
            ac.setImage(head_cv, R.drawable.head1);
        } else {
            ac.setImage(head_cv, bean.getImages());
        }
        if (bean.getImages() != null) {
            ac.setImage(head_cv, bean.getImages());
        }
        name_tv.setText(bean.getName());
        time_tv.setText(bean.getCreateTime());
        content_tv.setText(bean.getContent());
        like_count = Integer.parseInt(bean.getLikeCount());
        zan_count_tv.setText(like_count + "");
//        islike = bean.getIsLike();//0：没有点赞 1：已点赞
        if ("0".equals(bean.getIsLike())) {
            ac.setImage(zan_iv, R.drawable.icon_zan_n);
            likeType = "1";
        } else {
            ac.setImage(zan_iv, R.drawable.icon_zan_p);
            likeType = "0";
        }
    }


    @Override
    public void onApiStart(String tag) {
        ((MoreCommentActivity) _activity).showLoadingDlg();
    }

    @Override
    public void onApiLoading(long count, long current, String tag) {

    }

    @Override
    public void onApiSuccess(NetResult res, String tag) {
        ((MoreCommentActivity) _activity).hideLoadingDlg();
        if (res.isOK()) {//0：没有点赞 1：已点赞
            if (likeType.equals("1")) {
                UIHelper.t(_activity,"点赞成功！");
                likeType = "0";
                bean.setIsLike("1");
                ac.setImage(zan_iv, R.drawable.icon_zan_p);
                like_count = FDDataUtils.getInteger(bean.getLikeCount()) + 1;
                adapter.notifyDataSetChanged();
            } else {
                UIHelper.t(_activity,"取消点赞成功！");
                likeType = "1";
                bean.setIsLike("0");
                ac.setImage(zan_iv, R.drawable.icon_zan_n);
                like_count = FDDataUtils.getInteger(bean.getLikeCount()) - 1;
                zan_count_tv.setText(like_count + "");
                adapter.notifyDataSetChanged();
            }
            bean.setLikeCount(like_count + "");
        } else {
            ac.handleErrorCode(_activity, res.errorcode);
        }
    }

    @Override
    public void onApiFailure(Throwable t, int errorNo, String strMsg, String tag) {
        ((MoreCommentActivity) _activity).hideLoadingDlg();
    }

    @Override
    public void onParseError(String tag) {
        ((MoreCommentActivity) _activity).hideLoadingDlg();
    }
}
