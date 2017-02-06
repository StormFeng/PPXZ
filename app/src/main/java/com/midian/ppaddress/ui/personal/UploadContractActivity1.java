package com.midian.ppaddress.ui.personal;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.bean.ImageItem;
import com.lzy.imagepicker.ui.ImageGridActivity;
import com.lzy.imagepicker.ui.ImagePreviewDelActivity;
import com.midian.ppaddress.R;
import com.midian.ppaddress.bean.MemberOrderCounselorListContractBean;
import com.midian.ppaddress.bean.OrderCounselorUploadContractBean;
import com.midian.ppaddress.datasource.ImagePickerAdapter;
import com.midian.ppaddress.utils.AppUtil;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import midian.baselib.app.AppContext;
import midian.baselib.base.BaseActivity;
import midian.baselib.bean.NetResult;
import midian.baselib.utils.UIHelper;
import midian.baselib.widget.BaseLibTopbarView;

/**
 * 上传合同
 */
public class UploadContractActivity1 extends BaseActivity implements ImagePickerAdapter.OnRecyclerViewItemClickListener {
    private BaseLibTopbarView topbar;
    private TextView submit_tv;
    private String type, orderid;
    public static final int IMAGE_ITEM_ADD = -1;
    public static final int IMAGE_ITEM_DEL = -2;
    public static final int REQUEST_CODE_SELECT = 100;
    public static final int REQUEST_CODE_PREVIEW = 101;

//    private ImagePickerAdapter adapter;
    private ImageAdapter imageAdapter;
    private ArrayList<ImageItem> selImageList; //当前选择的所有图片
    private int maxImgCount = 20;               //允许选择图片最大数
    private List<String>  upLoadPath = new ArrayList<String>();;  //添加的list
    ArrayList<ImageItem> images;//添加到activity中的图片集合
    int position;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contract1);

        type = mBundle.getString("type");
        orderid = mBundle.getString("orderid");
        initWidget();
    }


    private void initWidget() {
        topbar = findView(R.id.topbar);
        topbar.setTitle("合同").setLeftImageButton(R.drawable.icon_back, UIHelper.finish(_activity));

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        selImageList = new ArrayList<>();
//        adapter = new ImagePickerAdapter(this, selImageList, maxImgCount, type, orderid);
        imageAdapter = new ImageAdapter(this, selImageList, maxImgCount, type, orderid);
//        adapter.setOnItemClickListener(this);

        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(imageAdapter);

        submit_tv = findView(R.id.submit_tv);
        submit_tv.setVisibility(View.VISIBLE);
        if (type.equals("look")) {
            submit_tv.setVisibility(View.GONE);
            loadData();
        } else if (type.equals("upload")) {
            submit_tv.setText("上传合同");
        } else if (type.equals("normal") || type.equals("again")) {
            submit_tv.setText("编辑合同");
            loadData();
        }
        submit_tv.setOnClickListener(this);
    }


    private void loadData() {
        Log.d("wqf","loadData::"+orderid);
        AppUtil.getPpApiClient(ac).memberOrderCounselorListContract(orderid, this);//查看合同
    }

    @Override
    public void onItemClick(View view, int position) {
        switch (position) {
            case IMAGE_ITEM_DEL:
                UIHelper.t(_activity, "删除" + selImageList.get(position).img_id);
                break;
            case IMAGE_ITEM_ADD:
                //打开选择,本次允许选择的数量
                ImagePicker.getInstance().setSelectLimit(maxImgCount - selImageList.size());
                ImagePicker.getInstance().setMultiMode(false);//设置选择图片的模式
                Intent intent = new Intent(this, ImageGridActivity.class);
                startActivityForResult(intent, REQUEST_CODE_SELECT);
                break;
            default:
                //打开预览
                Intent intentPreview = new Intent(this, ImagePreviewDelActivity.class);
//                intentPreview.putExtra(ImagePicker.EXTRA_IMAGE_ITEMS, (ArrayList<ImageItem>) adapter.getImages());
                intentPreview.putExtra(ImagePicker.EXTRA_SELECTED_IMAGE_POSITION, position);
                startActivityForResult(intentPreview, REQUEST_CODE_PREVIEW);
                break;
        }
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.submit_tv:
              /*  for (ImageItem path : selImageList) {
                    upLoadFile.add(path.path);
                }*/
                StringBuilder card_ids = new StringBuilder();
                if(selImageList.size()!=0) {
                    for (int i = 0; i < selImageList.size(); i++) {
                        ImageItem compareData = selImageList.get(i);
                        card_ids.append("," + compareData.img_id);
                    }
                    card_ids.deleteCharAt(0);//格式化ids，去除第一个逗号
                }
//                if (card_ids.length() > 1) {
//                    card_ids.deleteCharAt(0);
//                } else {
//                    UIHelper.t(_activity, "没有合同文件内容吖^_^,添加了再来");
//                    return;
//                }
                Log.d("upLoadPath", "onClick:上传参数图片id =" + card_ids.toString());
                if (type.equals("upload")) {
                    //16.17 上传合同文件
                    Log.d("wqf","upload::"+orderid);
                    AppUtil.getPpApiClient(ac).memberOrderCounselorAddContracts(orderid, card_ids.toString(), this);
                } else if (type.equals("normal") || type.equals("again")) {//编辑合同与重新上传合同
                    //16.18 编辑合同文件
                    Log.d("wqf","normal::"+orderid);
                    AppUtil.getPpApiClient(ac).memberOrderCounselorEditContracts(orderid, card_ids.toString(), this);
                }
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == ImagePicker.RESULT_CODE_ITEMS) {
            //添加图片返回
            if (data != null && requestCode == REQUEST_CODE_SELECT) {
                images = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_RESULT_ITEMS);
                /*selImageList.addAll(images);
                adapter.setImages(selImageList);*/
                upLoadPath.clear();
                for (ImageItem path : images) {
                    upLoadPath.add(path.path);
                }
                if (upLoadPath == null || upLoadPath.size() <= 0) {

                } else {
                    try {
                        //添加图片后，先调16.10 上传合同文件，返回图片id，用于上传合同、编辑参数
                        AppUtil.getPpApiClient(ac).memberOrderCounselorUploadContract(orderid, upLoadPath, this);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            }
        } else if (resultCode == ImagePicker.RESULT_CODE_BACK) {
            //预览图片返回
            if (data != null && requestCode == REQUEST_CODE_PREVIEW) {
                ArrayList<ImageItem> images = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_IMAGE_ITEMS);
                selImageList.clear();
                selImageList.addAll(images);
                imageAdapter.setImages(selImageList);
            }
        }
    }

    @Override
    public void onApiStart(String tag) {
        super.onApiStart(tag);
        showLoadingDlg();
    }

//    List<MemberOrderCounselorListContractBean.LookContractData> lookPics = new ArrayList<MemberOrderCounselorListContractBean.LookContractData>();
//    List<OrderCounselorUploadContractBean.ContractData> contractDatas = new ArrayList<OrderCounselorUploadContractBean.ContractData>();


    @Override
    public void onApiSuccess(NetResult res, String tag) {
        super.onApiSuccess(res, tag);
        hideLoadingDlg();
        if (res.isOK()) {
            //查看合同接口回调
            if ("memberOrderCounselorListContract".equals(tag)) {
                MemberOrderCounselorListContractBean lookBean = (MemberOrderCounselorListContractBean) res;
                selImageList.clear();

                for (MemberOrderCounselorListContractBean.LookContractData lookContractData : lookBean.getData()) {
                    ImageItem imageItem = new ImageItem();
                    imageItem.name = lookContractData.getFileName();
                    imageItem.path = lookContractData.getImage();
                    imageItem.img_id = lookContractData.getId();
                    selImageList.add(imageItem);//所有图片
                }

                Log.d("=-=合同图片列表接口---=-=-", "onApiSuccess:" + selImageList.size());
                imageAdapter.setImages(selImageList);
            }

            //16.10上传合同文件返回每个添加的图片id 用于上传合同、编辑合同参数
            else if ("memberOrderCounselorUploadContract".equals(tag)) {
                OrderCounselorUploadContractBean uploadData = (OrderCounselorUploadContractBean) res;

                for (OrderCounselorUploadContractBean.ContractData data :  uploadData.getData()) {
                    ImageItem imageItem = new ImageItem();
                    imageItem.name = data.getFileName();
                    imageItem.path = data.getImage();
                    imageItem.img_id = data.getId();
                    selImageList.add(imageItem);//所有图片
                }
                imageAdapter.setImages(selImageList);
            } else if ("memberOrderCounselorAddContracts".equals(tag)) {
                //16.17上传合同接口
                if (selImageList != null && selImageList.size() > 0) {
                    submit_tv.setText("编辑合同");
                    type = "normal";
                }
            } else if ("memberOrderCounselorEditContracts".equals(tag)) {
                //16.18编辑合同接口
                UIHelper.t(_activity, res.message);
            }

        } else {
            ac.handleErrorCode(_activity, res.errorcode);
        }
    }

    @Override
    public void onApiFailure(Throwable t, int errorNo, String strMsg, String tag) {
        super.onApiFailure(t, errorNo, strMsg, tag);
        hideLoadingDlg();
    }


    public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.SelectedPicViewHolder> {
        private List<ImageItem> mData;
        private LayoutInflater mInflater;
        private boolean isAdded;   //是否额外添加了最后一个图片
        private Context mContext;
        private int maxImgCount;
        private String type, orderid;

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

        public ImageAdapter() {

        }

        public ImageAdapter(Context mContext, List<ImageItem> data, int maxImgCount, String type, String orderid) {
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
            View itemView = mInflater.inflate(R.layout.list_item_image, parent, false);
            return new SelectedPicViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(final SelectedPicViewHolder holder, final int position) {
            //根据条目位置设置图片
            if (isAdded && position == getItemCount() - 1) {
                holder.v_selected.setVisibility(View.GONE);
                holder.iv_img.setImageResource(R.drawable.icon_image_add);
                holder.clickPosition = IMAGE_ITEM_ADD;
            } else {
                if (type.equals("look")) {
                    holder.v_selected.setVisibility(View.GONE);
                } else {
                    holder.v_selected.setVisibility(View.VISIBLE);
                }
                Glide.with(mContext)                      //配置上下文
                        .load(mData.get(position).path)                  //设置图片路径
                        .error(com.lzy.imagepicker.R.mipmap.default_image)           //设置错误图片
                        .placeholder(com.lzy.imagepicker.R.mipmap.default_image)     //设置占位图片
                        .diskCacheStrategy(DiskCacheStrategy.ALL)//缓存全尺寸
                        .into(holder.iv_img);
                holder.clickPosition = position;

            }

            holder.iv_img.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (isAdded && position == getItemCount() - 1) {
                        //添加图片
                        //打开选择,本次允许选择的数量
                        ImagePicker.getInstance().setSelectLimit(maxImgCount - selImageList.size());
                        ImagePicker.getInstance().setMultiMode(false);//设置选择图片的模式
                        Intent intent = new Intent(mContext, ImageGridActivity.class);
                        startActivityForResult(intent, REQUEST_CODE_SELECT);
                    } else {
                        //预览图片
                        //打开预览
                        Intent intentPreview = new Intent(mContext, ImagePreviewDelActivity.class);
                        intentPreview.putExtra(ImagePicker.EXTRA_IMAGE_ITEMS, (ArrayList<ImageItem>) imageAdapter.getImages());
                        intentPreview.putExtra(ImagePicker.EXTRA_SELECTED_IMAGE_POSITION, position);
                        startActivityForResult(intentPreview, REQUEST_CODE_PREVIEW);
                    }
                }
            });

            holder.v_selected.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //删除图片
                    mData.remove(holder.clickPosition);
                    selImageList.remove(holder.clickPosition);
                    notifyDataSetChanged();
                }
            });


        }

        @Override
        public int getItemCount() {
            return mData.size();
        }

        public class SelectedPicViewHolder extends RecyclerView.ViewHolder {

            private ImageView iv_img;
            private ImageView v_selected;
            private int clickPosition;

            public SelectedPicViewHolder(View itemView) {
                super(itemView);
                iv_img = (ImageView) itemView.findViewById(R.id.iv_img);
                v_selected = (ImageView) itemView.findViewById(R.id.v_selected);
            }
        }
    }

}
