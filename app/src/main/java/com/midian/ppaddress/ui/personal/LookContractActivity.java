package com.midian.ppaddress.ui.personal;

import android.annotation.TargetApi;
import android.app.Dialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;

import com.midian.ppaddress.R;
import com.midian.ppaddress.bean.MemberOrderCounselorListContractBean;
import com.midian.ppaddress.utils.AppUtil;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import midian.baselib.base.BaseActivity;
import midian.baselib.bean.NetResult;
import midian.baselib.utils.UIHelper;
import midian.baselib.widget.BaseLibTopbarView;

/**
 * 查看合同
 */
public class LookContractActivity extends BaseActivity {
    private BaseLibTopbarView topbar;
    private ImageView iv_pic1,iv_pic2,iv_pic3,iv_pic4,iv_pic5,iv_pic6,iv_pic7,iv_pic8,iv_pic9;
    private String orderid;
    private boolean num=false;
    private float scaleWidth;
    private float scaleHeight;
    private Bitmap bp1;
    private int width,height;
    private Dialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lookcontrast);
        topbar = findView(R.id.topbar);
        topbar.setTitle("合同").setLeftImageButton(R.drawable.icon_back, UIHelper.finish(_activity));
        iv_pic1=findView(R.id.iv_pic1);
        iv_pic2=findView(R.id.iv_pic2);
        iv_pic3=findView(R.id.iv_pic3);
        iv_pic4=findView(R.id.iv_pic4);
        iv_pic5=findView(R.id.iv_pic5);
        iv_pic6=findView(R.id.iv_pic6);
        iv_pic7=findView(R.id.iv_pic7);
        iv_pic8=findView(R.id.iv_pic8);
        iv_pic9=findView(R.id.iv_pic9);
        iv_pic1.setOnClickListener(this);
        iv_pic2.setOnClickListener(this);
        iv_pic3.setOnClickListener(this);
        iv_pic4.setOnClickListener(this);
        iv_pic5.setOnClickListener(this);
        iv_pic6.setOnClickListener(this);
        iv_pic7.setOnClickListener(this);
        iv_pic8.setOnClickListener(this);
        iv_pic9.setOnClickListener(this);
        orderid=mBundle.getString("orderid");
        AppUtil.getPpApiClient(ac).memberOrderCounselorListContract(orderid,this);

    }

    @Override
    public void onClick(View arg0) {
        super.onClick(arg0);
        dialog=new Dialog(_activity,R.style.add_dialog);
        switch(arg0.getId()){
            case R.id.iv_pic1:
                initPic(iv_pic1);
                break;
            case R.id.iv_pic2:
                initPic(iv_pic2);
                break;
            case R.id.iv_pic3:
                initPic(iv_pic3);
                break;
            case R.id.iv_pic4:
                initPic(iv_pic4);
                break;
            case R.id.iv_pic5:
                initPic(iv_pic5);
                break;
            case R.id.iv_pic6:
                initPic(iv_pic6);
                break;
            case R.id.iv_pic7:
                initPic(iv_pic7);
                break;
            case R.id.iv_pic8:
                initPic(iv_pic8);
                break;
            case R.id.iv_pic9:
                initPic(iv_pic9);
                break;
        }
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    private void initPic(ImageView pic) {
        Drawable drawable = pic.getDrawable();
        Window window=dialog.getWindow();
        dialog.setContentView(R.layout.dialog_lookpic);
        ImageView iv_pic = (ImageView) window.findViewById(R.id.iv_image);
        iv_pic.setImageDrawable(drawable);
//        iv_pic.setBackground(drawable);
        dialog.show();
    }

    @Override
    public void onApiSuccess(NetResult res, String tag) {
        super.onApiSuccess(res, tag);
        MemberOrderCounselorListContractBean bean= (MemberOrderCounselorListContractBean) res;
        try {
            ac.setImage(iv_pic1,bean.getData().get(0).getImage());
            ac.setImage(iv_pic2,bean.getData().get(1).getImage());
            ac.setImage(iv_pic3,bean.getData().get(2).getImage());
            ac.setImage(iv_pic4,bean.getData().get(3).getImage());
            ac.setImage(iv_pic5,bean.getData().get(4).getImage());
            ac.setImage(iv_pic6,bean.getData().get(5).getImage());
            ac.setImage(iv_pic7,bean.getData().get(6).getImage());
            ac.setImage(iv_pic8,bean.getData().get(7).getImage());
            ac.setImage(iv_pic9,bean.getData().get(8).getImage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(iv_pic1.getDrawable()==null){
            iv_pic1.setVisibility(View.GONE);
        }
        if(iv_pic2.getDrawable()==null){
            iv_pic2.setVisibility(View.GONE);
        }
        if(iv_pic3.getDrawable()==null){
            iv_pic3.setVisibility(View.GONE);
        }
        if(iv_pic4.getDrawable()==null){
            iv_pic4.setVisibility(View.GONE);
        }
        if(iv_pic5.getDrawable()==null){
            iv_pic5.setVisibility(View.GONE);
        }
        if(iv_pic6.getDrawable()==null){
            iv_pic6.setVisibility(View.GONE);
        }
        if(iv_pic7.getDrawable()==null){
            iv_pic7.setVisibility(View.GONE);
        }
        if(iv_pic8.getDrawable()==null){
            iv_pic8.setVisibility(View.GONE);
        }
        if(iv_pic9.getDrawable()==null){
            iv_pic9.setVisibility(View.GONE);
        }
    }
    public Bitmap getBitmap(String urlpath){
        Bitmap bitmap=null;
        try {
            URL url=new URL(urlpath);
            URLConnection conn=url.openConnection();
            InputStream in = conn.getInputStream();
            bitmap =BitmapFactory.decodeStream(in);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bitmap;
    }
}
