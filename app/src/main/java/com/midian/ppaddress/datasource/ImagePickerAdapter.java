package com.midian.ppaddress.datasource;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.bean.ImageItem;
import com.midian.ppaddress.R;
import com.midian.ppaddress.api.PpApiClient;
import com.midian.ppaddress.ui.personal.UploadContractActivity1;
import com.midian.ppaddress.utils.AppUtil;

import java.util.ArrayList;
import java.util.List;

import midian.baselib.api.ApiCallback;
import midian.baselib.app.AppContext;
import midian.baselib.bean.NetResult;
import midian.baselib.utils.UIHelper;

/**
 * ================================================
 * 作    者：ikkong （ikkong@163.com），修改 jeasonlzy（廖子尧）
 * 版    本：1.0
 * 创建日期：2016/5/19
 * 描    述：
 * 修订历史：微信图片选择的Adapter, 感谢 ikkong 的提交
 * ================================================
 */
public class ImagePickerAdapter extends RecyclerView.Adapter<ImagePickerAdapter.SelectedPicViewHolder> {
    private int maxImgCount;
    private Context mContext;
    private List<ImageItem> mData;
    private LayoutInflater mInflater;
    private OnRecyclerViewItemClickListener listener;
    private boolean isAdded;   //是否额外添加了最后一个图片
    private String type,orderid;
    AppContext ac;

    public interface OnRecyclerViewItemClickListener {
        void onItemClick(View view, int position);
    }
    public void setOnItemClickListener(OnRecyclerViewItemClickListener listener) {
        this.listener = listener;
    }

    public void setImages(List<ImageItem> data) {
        mData = new ArrayList<>(data);
        if (type.equals("look")) {
            maxImgCount = mData.size();
            Log.d("==adapter===", ": maxImgCount=." + maxImgCount);
        }
        if (getItemCount() < maxImgCount) {
            mData.add(new ImageItem());
            isAdded = true;
        } else {
            isAdded = false;
        }
        notifyDataSetChanged();
    }

    public List<ImageItem> getImages() {
        //由于图片未选满时，最后一张显示添加图片，因此这个方法返回真正的已选图片
        if (isAdded) return new ArrayList<>(mData.subList(0, mData.size() - 1));
        else return mData;
    }

    public ImagePickerAdapter(Context mContext, List<ImageItem> data, int maxImgCount, String type,String orderid) {
        this.mContext = mContext;
        this.maxImgCount = maxImgCount;
        this.mInflater = LayoutInflater.from(mContext);
        this.type = type;
        this.orderid = orderid;
        ac = AppContext.context();
        setImages(data);
    }

    @Override
    public SelectedPicViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new SelectedPicViewHolder(mInflater.inflate(R.layout.list_item_image, parent, false));
    }

    @Override
    public void onBindViewHolder(final SelectedPicViewHolder holder, final int position) {
        holder.bind(holder, position);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class SelectedPicViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ImageView iv_img;
        private ImageView v_selected;
        private int clickPosition;

        public SelectedPicViewHolder(View itemView) {
            super(itemView);
            iv_img = (ImageView) itemView.findViewById(R.id.iv_img);
            v_selected = (ImageView) itemView.findViewById(R.id.v_selected);
        }

        public void bind(final SelectedPicViewHolder holder,final int position) {
            //设置条目的点击事件
            itemView.setOnClickListener(this);
            //删除
            v_selected.setOnClickListener(this);
            //根据条目位置设置图片
           final ImageItem item = mData.get(position);
            if (isAdded && position == getItemCount() - 1) {
                v_selected.setVisibility(View.GONE);
                iv_img.setImageResource(R.drawable.icon_image_add);
                clickPosition = UploadContractActivity1.IMAGE_ITEM_ADD;
            } else {
                if (type.equals("look")) {
                    v_selected.setVisibility(View.GONE);
                } else {
                    v_selected.setVisibility(View.VISIBLE);
                }
                Glide.with(mContext)                      //配置上下文
                        .load(item.path)                  //设置图片路径
                        .error(com.lzy.imagepicker.R.mipmap.default_image)           //设置错误图片
                        .placeholder(com.lzy.imagepicker.R.mipmap.default_image)     //设置占位图片
                        .diskCacheStrategy(DiskCacheStrategy.ALL)//缓存全尺寸
                        .into(iv_img);
                clickPosition = position;
            }
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.v_selected://删除
                    if (clickPosition != -1) {
//                        clickPosition = UploadContractActivity1.IMAGE_ITEM_DEL;
                        if (listener != null) listener.onItemClick(v, clickPosition);
                      /*  PpApiClient apiClient = new PpApiClient(ac);
                        Log.d("-=-=-", "onClick: 删除合同的position"+clickPosition+"position引用的图片id="+mData.get(clickPosition).img_id);
                        apiClient.memberOrderCounselorEditContracts(orderid, mData.get(clickPosition).img_id, new ApiCallback() {
                            @Override
                            public void onApiStart(String tag) {
                                ((UploadContractActivity1)mContext).showLoadingDlg();
                            }

                            @Override
                            public void onApiLoading(long count, long current, String tag) {

                            }

                            @Override
                            public void onApiSuccess(NetResult res, String tag) {
                                ((UploadContractActivity1)mContext).hideLoadingDlg();
                                if (res.isOK()) {
                                    mData.remove(mData.get(clickPosition));
                                    notifyDataSetChanged();
                                } else {
                                    ac.handleErrorCode(mContext, res.errorcode);
                                }
                            }

                            @Override
                            public void onApiFailure(Throwable t, int errorNo, String strMsg, String tag) {
                                ((UploadContractActivity1)mContext).hideLoadingDlg();
                            }

                            @Override
                            public void onParseError(String tag) {
                                ((UploadContractActivity1)mContext).hideLoadingDlg();
                            }
                        });*/
                    }

                    break;
                default:
                    if (listener != null) listener.onItemClick(v, clickPosition);
                    if (clickPosition!=-1) {
                        Log.d("-=-=-", "onClick: 查看图片的position"+clickPosition+"position引用的图片id="+mData.get(clickPosition).img_id);
                    }
                    break;
            }

        }
    }
}