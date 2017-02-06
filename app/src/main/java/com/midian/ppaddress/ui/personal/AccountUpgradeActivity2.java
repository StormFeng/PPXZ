package com.midian.ppaddress.ui.personal;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Parcelable;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.midian.fastdevelop.afinal.utils.FieldUtils;
import com.midian.login.view.AgreementActivity;
import com.midian.ppaddress.R;
import com.midian.ppaddress.app.Constant;
import com.midian.ppaddress.utils.AppUtil;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import midian.baselib.base.BaseActivity;
import midian.baselib.base.BasePicActivity;
import midian.baselib.bean.NetResult;
import midian.baselib.utils.ImageUtils;
import midian.baselib.utils.UIHelper;
import midian.baselib.widget.BaseLibTopbarView;
import midian.baselib.widget.dialog.PicChooserDialog;

/**
 * 账号升级2
 * Created by chu on 2016/5/3.
 */
public class AccountUpgradeActivity2 extends BaseActivity{
    private BaseLibTopbarView topbar;
    private String type,name,personalId;
    private Button submit_btn;
    private RelativeLayout rl_pic1,rl_pic2,rl_pic3,rl_pic4;
    private ImageView iv_pic1,iv_pic2,iv_pic3,iv_pic4;
    private CheckBox select;
    private File f_pic1,f_pic2,f_pic3,f_pic4;
    private int memberid,roletype;
    private TextView tv_notice;
    private String str;
    private String url;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity2_account_upgrade);

        topbar = findView(R.id.topbar);
        topbar.setLeftImageButton(R.drawable.icon_back, UIHelper.finish(_activity));
        type = mBundle.getString("type");
        if ("1".equals(type)) {
            topbar.setTitle("客商中介");
            str="《客商中介合作者协议》";
            url= Constant.BBASEURL+"views/agreementAgent";
        } else if ("2".equals(type)) {
            topbar.setTitle("物业顾问");
            str="《物业顾问合作协议》";
            url= Constant.BBASEURL+"views/agreementCounselor";
        }else if ("4".equals(type)) {
            topbar.setTitle("服务达人");
            str="《服务达人合作协议》";
            url= Constant.BBASEURL+"views/agreementAgencyexpert";
        }
        name=mBundle.getString("fullname");
        personalId=mBundle.getString("idcard");
        tv_notice=findView(R.id.notice);
        tv_notice.setText(str);
        tv_notice.setOnClickListener(this);
        findView(R.id.rl_pic1).setOnClickListener(this);
        findView(R.id.rl_pic2).setOnClickListener(this);
        findView(R.id.rl_pic3).setOnClickListener(this);
        findView(R.id.rl_pic4).setOnClickListener(this);
        iv_pic1=findView(R.id.iv_pic1);
        iv_pic2=findView(R.id.iv_pic2);
        iv_pic3=findView(R.id.iv_pic3);
        iv_pic4=findView(R.id.iv_pic4);
        select=findView(R.id.select);
        submit_btn = findView(R.id.submit_btn);
        submit_btn.setOnClickListener(this);

    }

    @Override
    public void onApiSuccess(NetResult res, String tag) {
        super.onApiSuccess(res, tag);
        hideLoadingDlg();
        UIHelper.t(_activity, res.getMessage());
        if("SUCCESS".equals(res.getStatus())) {
            finish();
        }
    }

    @Override
    public void onApiFailure(Throwable t, int errorNo, String strMsg, String tag) {
        super.onApiFailure(t, errorNo, strMsg, tag);
        hideLoadingDlg();
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        memberid=Integer.valueOf(ac.user_id).intValue();
        roletype=Integer.valueOf(type).intValue();
        switch (v.getId()) {
            case R.id.submit_btn:
                if(!select.isChecked()){
                    UIHelper.t(_activity,"请阅读并同意《客商推荐者合作协议》");
                }else{
                    if(iv_pic1==null||iv_pic2==null||iv_pic3==null||iv_pic4==null){
                        UIHelper.t(_activity,"资料不齐全");
                    }else{
                        try {
                            if(f_pic1==null){
                                Log.d("wqf","F_PIC1为空");
                            }
                            showLoadingDlg();
                            AppUtil.getPpApiClient(ac).memberMembershipInfoUpgrade(memberid,personalId,name,roletype,f_pic1,f_pic2,f_pic3,f_pic4,this);
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }
                    }
                }
                break;
            case R.id.rl_pic1:
                ToastDialog(R.id.iv_pic1);
                break;
            case R.id.rl_pic2:
                ToastDialog(R.id.iv_pic2);
                break;
            case R.id.rl_pic3:
                ToastDialog(R.id.iv_pic3);
                break;
            case R.id.rl_pic4:
                ToastDialog(R.id.iv_pic4);
                break;
            case R.id.notice:
                Bundle bundle=new Bundle();
                bundle.putString("url",url);
                bundle.putString("str",str);
                UIHelper.jump(_activity, AgreementActivity.class,bundle);
                break;
        }
    }

    private void ToastDialog(final int id) {
        final PicChooserDialog dialog = new PicChooserDialog(this,
                com.midian.baselib.R.style.bottom_dialog);
        dialog.show();
        Window window=dialog.getWindow();
        window.findViewById(R.id.fromCamera).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                UIHelper.t(_activity,"拍照");
                takePtoto(id);
                dialog.dismiss();
            }
        });
        window.findViewById(R.id.fromGallery).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                UIHelper.t(_activity,"从系统相册中选择");
                selectPicture(id);
                dialog.dismiss();
            }
        });
        window.findViewById(R.id.cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
    }
    private void takePtoto(int id) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, id);
    }
    private void selectPicture(int id) {
        Intent intent=new Intent();
        intent.setAction(Intent.ACTION_PICK);//Pick an item from the data
        intent.setType("image/*");//从所有图片中进行选择
        startActivityForResult(intent, id+1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case R.id.iv_pic1:
                f_pic1=setImageFromCamera(R.id.iv_pic1,R.id.tv_pic1,f_pic1,data);
                break;
            case R.id.iv_pic2:
                f_pic2=setImageFromCamera(R.id.iv_pic2,R.id.tv_pic2,f_pic2,data);
                break;
            case R.id.iv_pic3:
                f_pic3=setImageFromCamera(R.id.iv_pic3,R.id.tv_pic3,f_pic3,data);
                break;
            case R.id.iv_pic4:
                f_pic4=setImageFromCamera(R.id.iv_pic4,R.id.tv_pic4,f_pic4,data);
                break;

            case R.id.iv_pic1+1:
                f_pic1=setImageFromGallery(R.id.iv_pic1,R.id.tv_pic1,f_pic1,data);
                break;
            case R.id.iv_pic2+1:
                f_pic2=setImageFromGallery(R.id.iv_pic2,R.id.tv_pic2,f_pic2,data);
                break;
            case R.id.iv_pic3+1:
                f_pic3=setImageFromGallery(R.id.iv_pic3,R.id.tv_pic3,f_pic3,data);
                break;
            case R.id.iv_pic4+1:
                f_pic4=setImageFromGallery(R.id.iv_pic4,R.id.tv_pic4,f_pic4,data);
                break;
        }
    }

    private File setImageFromGallery(int iv_id,int tv_id,File file,Intent data) {
        ImageView pic=findView(iv_id);
        try {
            Uri selectedImage = data.getData(); //获取系统返回的照片的Uri
            String[] filePathColumn = {MediaStore.Images.Media.DATA};
            Cursor cursor = getContentResolver().query(selectedImage,
                    filePathColumn, null, null, null);//从系统表中查询指定Uri对应的照片
            cursor.moveToFirst();
            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);  //获取照片路径
            file=new File(picturePath);
            cursor.close();
            Bitmap bitmap = BitmapFactory.decodeFile(picturePath);
            findView(tv_id).setVisibility(View.GONE);
            pic.setImageBitmap(bitmap);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return file;
    }

    private File setImageFromCamera(int iv_id,int tv_id,File file,Intent data){
        ImageView pic= (ImageView) findViewById(iv_id);
        String absolutePath = getApplicationContext().getFilesDir().getAbsolutePath();
        file=new File(absolutePath+"/"+"temp.jpg");

        if (data != null) {
            //取得返回的Uri,基本上选择照片的时候返回的是以Uri形式，但是在拍照中有得机子呢Uri是空的，所以要特别注意
            Uri mImageCaptureUri = data.getData();
            //返回的Uri不为空时，那么图片信息数据都会在Uri中获得。如果为空，那么我们就进行下面的方式获取
            if (mImageCaptureUri != null) {
                Bitmap image;
                try {
                    //这个方法是根据Uri获取Bitmap图片的静态方法
                    image = MediaStore.Images.Media.getBitmap(this.getContentResolver(), mImageCaptureUri);
                    if (image != null) {
                        pic.setImageBitmap(image);
//                        file=new File(String.valueOf(mImageCaptureUri));
                        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file));
                        image.compress(Bitmap.CompressFormat.JPEG, 100, bos);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                Bundle extras = data.getExtras();
                if (extras != null) {
                    //这里是有些拍照后的图片是直接存放到Bundle中的所以我们可以从这里面获取Bitmap图片
                    Bitmap image = extras.getParcelable("data");
                    if (image != null) {
                        pic.setImageBitmap(image);
                        BufferedOutputStream bos = null;
                        try {
                            bos = new BufferedOutputStream(new FileOutputStream(file));
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }
                        image.compress(Bitmap.CompressFormat.JPEG, 100, bos);
                    }
                }
            }
            findView(tv_id).setVisibility(View.GONE);
        }
        return file;
    }
}
