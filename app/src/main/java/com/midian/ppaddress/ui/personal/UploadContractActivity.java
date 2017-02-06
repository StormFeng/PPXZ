package com.midian.ppaddress.ui.personal;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.midian.ppaddress.R;
import com.midian.ppaddress.bean.MemberOrderCounselorListContractBean;
import com.midian.ppaddress.bean.MemberOrderCounselorListContractBean.LookContractData;
import com.midian.ppaddress.utils.AppUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import midian.baselib.base.BaseActivity;
import midian.baselib.bean.NetResult;
import midian.baselib.utils.ScreenUtils;
import midian.baselib.utils.UIHelper;
import midian.baselib.widget.BaseLibTopbarView;

/**
 * 上传合同【旧页面，暂时不用】
 */
public class UploadContractActivity extends BaseActivity {
    private BaseLibTopbarView topbar;
    private RecyclerView recyclerView;
    private RelativeLayout add_iv;
    private TextView submit_tv;
    private String type,orderid;
    ArrayList<String> selectedPhotos = new ArrayList<>();
    public final static int REQUEST_CODE = 1;
    public final static int PHOTO_REQUEST_CODE = 2;
    private List<String> photos = null;
    private  HashMap<String, String> netPath = new HashMap<>();
    private List<String> upLoadPath;

    // TODO: 2016/6/14 【旧页面，暂时不用】
    // TODO: 2016/6/14 【旧页面，暂时不用】
    // TODO: 2016/6/14 【旧页面，暂时不用】
    // TODO: 2016/6/14 【旧页面，暂时不用】
    // TODO: 2016/6/14 【旧页面，暂时不用】
    // TODO: 2016/6/14 【旧页面，暂时不用】
    // TODO: 2016/6/14 【旧页面，暂时不用】



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contract);
        type = mBundle.getString("type");
        orderid = mBundle.getString("orderid");

        initView();
    }

    private void initView() {
        topbar = findView(R.id.topbar);
        topbar.setLeftImageButton(R.drawable.icon_back, UIHelper.finish(_activity));
        recyclerView = findView(R.id.recyclerView);
        submit_tv = findView(R.id.submit_tv);
        submit_tv.setVisibility(View.VISIBLE);
        if (type.equals("look")) {
            topbar.setTitle("查看合同");
            submit_tv.setVisibility(View.GONE);
            loadData();
        } else if (type.equals("upload")) {
            topbar.setTitle("上传合同");
            submit_tv.setText("上传合同");
        } else if (type.equals("normal")) {
            topbar.setTitle("编辑合同");
            submit_tv.setText("编辑完成");
        } else if (type.equals("again")) {
            topbar.setTitle("重新上传合同");
            submit_tv.setText("重新上传合同");
        }
        submit_tv.setOnClickListener(this);

//        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(3, OrientationHelper.VERTICAL));
        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        recyclerView.setHasFixedSize(true);

       /* if (photos != null) {
            recyclerView.setVisibility(View.VISIBLE);
        } else {
            recyclerView.setVisibility(View.GONE);
        }*/


    }
    private void loadData() {
        AppUtil.getPpApiClient(ac).memberOrderCounselorListContract(orderid, this);//查看合同
    }


    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
           /* case R.id.add_iv:
                if (selectedPhotos.size() < 10) {
                    PhotoPickerIntent intent = new PhotoPickerIntent(UploadContractActivity.this);
                    intent.setPhotoCount(10 - selectedPhotos.size());
                    intent.setShowCamera(true);
                    startActivityForResult(intent, PHOTO_REQUEST_CODE);
                } else {
                    UIHelper.t(_activity, "最多只能上传10张合同图片");
                }
                break;*/
            case R.id.submit_tv:
                if (photos == null) {
                    UIHelper.t(_activity, "请上传合同图片");
                    return;
                }
                upLoadPath = new ArrayList<String>();
                for (String path : selectedPhotos) {
                    if (netPath.containsKey(path)) {
                    } else {
                        upLoadPath.add(path);
                    }
                }
                UIHelper.t(_activity, "正在上传合同资料，请稍后...");
//                AppUtil.getMAppContext(ac).upLoad(orderid, upLoadPath,  UploadContractActivity.this);
        }
    }

    public void previewPhoto(Intent intent) {
        startActivityForResult(intent, REQUEST_CODE);
    }




    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && requestCode == REQUEST_CODE) {
            if (data != null) {
//                photos = data.getStringArrayListExtra(PhotoPickerActivity.KEY_SELECTED_PHOTOS);
            }
            selectedPhotos.clear();
            if (photos != null) {
                recyclerView.setVisibility(View.VISIBLE);
                selectedPhotos.addAll(photos);
                System.out.println("返回的照片数据：：：" + photos);
            } else {
                recyclerView.setVisibility(View.GONE);
            }
            resizePhotoView();
        } else if (resultCode == RESULT_OK && requestCode == PHOTO_REQUEST_CODE) {
            if (data != null) {
//                photos = data.getStringArrayListExtra(PhotoPickerActivity.KEY_SELECTED_PHOTOS);
            }
            if (photos != null) {
                recyclerView.setVisibility(View.VISIBLE);
                selectedPhotos.addAll(photos);
                System.out.println("返回的照片数据：：：" + photos);
            } else {
                recyclerView.setVisibility(View.GONE);
            }
            resizePhotoView();

        }
    }

    List<LookContractData> pics = new ArrayList<LookContractData>();
    @Override
    public void onApiSuccess(NetResult res, String tag) {
        super.onApiSuccess(res, tag);
        hideLoadingDlg();
        if (res.isOK()) {
            //查看合同回调
            if ("memberOrderCounselorListContract".equals(tag)) {
                MemberOrderCounselorListContractBean contractBean = (MemberOrderCounselorListContractBean) res;
                pics = contractBean.getData();
//                recyclerView.setVisibility(View.VISIBLE);
                selectedPhotos.clear();
                for (LookContractData pic : pics) {
                    System.out.println("资料详情返回id为::" + pic.getId() + "图片name:::" + pic.getFileName()+ "图片地址:::" + pic.getImage());
                    String p = ac.getFileUrl(pic.getImage());
                    netPath.put(p, pic.getId());
                    selectedPhotos.add(p);
                }
//                resizePhotoView();
            }

            //上传广告资料接口
            else if ("memberOrderCounselorUploadContract".equals(tag)) {
                UIHelper.t(_activity, "上传成功,请等待管理员审核!");
//                finish();
            }
            /*else if ("deleteMaterialPic".equals(tag)) {
                UIHelper.t(_activity, "删除图片成功");
            }*/
        } else {
            ac.handleErrorCode(_activity, res.errorcode);
        }
    }


    public void resizePhotoView() {
        LinearLayout.LayoutParams p = (LinearLayout.LayoutParams) recyclerView.getLayoutParams();
        int itemHeight = (ScreenUtils.GetScreenWidthPx(_activity) - recyclerView.getPaddingLeft() * 2) / 4;
        int low = selectedPhotos.size() / 4;
        if (selectedPhotos.size() % 4 > 0)
            low++;
        p.height = itemHeight * low + recyclerView.getPaddingTop() * 2;
        if (selectedPhotos.size() > 0) {
            recyclerView.setVisibility(View.VISIBLE);
        } else {
            recyclerView.setVisibility(View.GONE);
        }

    }




}
