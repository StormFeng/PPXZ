package com.midian.ppaddress.ui.personal;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.midian.ppaddress.R;
import com.midian.ppaddress.bean.MemberMembershipInfoShowBean;
import com.midian.ppaddress.utils.AppUtil;

import java.io.File;
import java.io.FileNotFoundException;

import midian.baselib.base.BasePicActivity;
import midian.baselib.bean.NetResult;
import midian.baselib.utils.UIHelper;
import midian.baselib.widget.BaseLibTopbarView;
import midian.baselib.widget.CircleImageView;

/**
 * 个人资料
 *
 * @author chu
 */
public class PersonInfoActivity extends BasePicActivity {

    public static final String TYPE = "type";
    public static final String NAME = "name";

    private BaseLibTopbarView topbar;
    private View head_ll, name_ll, phone_ll, sex_ll, area_ll, adress_ll, personalId_ll, company_ll, store_ll, declaration_ll, payinfo_ll, pay_ll;
    private CircleImageView head_iv;
    private TextView name_tv, phone_tv, sex_tv, area_tv, personalid_tv, company_tv, store_tv, declaration_tv, bankcard_tv, bank_tv;
    private TextView tv_company, tv_store;
    private TextView head_hint;
    private File mHead = null;
    private int sex;
    private String declaration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_info);
        init();
        if ("0".equals(ac.getProperty("user_type")) || "3".equals(ac.getProperty("user_type"))) {
            initMember();
        } else if ("1".equals(ac.getProperty("user_type"))) {
            initMerchants();
        } else if ("4".equals(ac.getProperty("user_type"))) {
            initService();
        }
        loadData();
    }

    private void loadData() {
        AppUtil.getPpApiClient(ac).memberMembershipInfoShow(ac.getProperty("user_type"), this);
    }

    private void init() {
        topbar = (BaseLibTopbarView) findViewById(R.id.topbar);
        topbar.setTitle("个人资料").setLeftImageButton(R.drawable.icon_back, this);
        head_iv = findView(R.id.head_iv);
        name_tv = findView(R.id.user_name);
        phone_tv = findView(R.id.user_phone);
        sex_tv = findView(R.id.sex);
        area_tv = findView(R.id.area);
        personalid_tv = findView(R.id.personalid);
        company_tv = findView(R.id.company);
        store_tv = findView(R.id.store);
        declaration_tv = findView(R.id.declaration);
        bankcard_tv = findView(R.id.bankcard);
        bank_tv = findView(R.id.bank);
        head_hint = findView(R.id.head_hint);

        tv_company = findView(R.id.tv_company);
        tv_store = findView(R.id.tv_store);

        head_ll = findViewById(R.id.head_ll);
        name_ll = findViewById(R.id.name_ll);
        phone_ll = findViewById(R.id.phone_ll);
        sex_ll = findViewById(R.id.sex_ll);
        area_ll = findViewById(R.id.area_ll);

        personalId_ll = findViewById(R.id.personalId_ll);
        company_ll = findViewById(R.id.company_ll);
        store_ll = findViewById(R.id.store_ll);
        declaration_ll = findViewById(R.id.declaration_ll);
        payinfo_ll = findViewById(R.id.payinfo_ll);
        pay_ll = findViewById(R.id.pay_ll);
        phone_tv.setText(ac.phone);

        head_ll.setOnClickListener(this);
        name_ll.setOnClickListener(this);
        declaration_ll.setOnClickListener(this);
        sex_ll.setOnClickListener(this);
        area_ll.setOnClickListener(this);
        payinfo_ll.setOnClickListener(this);
        phone_ll.setOnClickListener(this);
    }

    private void initString(String pNumber, TextView textView, int start, int end) {
        if (!TextUtils.isEmpty(pNumber) && pNumber.length() > 6) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < pNumber.length(); i++) {
                char c = pNumber.charAt(i);
                if (i < start || i >= pNumber.length() - end) {
                    sb.append(c);
                } else {
                    sb.append('*');
                }
            }
            textView.setText(sb.toString());
        }
    }

    /**
     * 普通用户/业主个人资料
     */
    private void initMember() {
        payinfo_ll.setVisibility(View.GONE);
        company_ll.setVisibility(View.GONE);
        store_ll.setVisibility(View.GONE);
        declaration_ll.setVisibility(View.GONE);
        personalId_ll.setVisibility(View.GONE);
        pay_ll.setVisibility(View.GONE);
    }

    /**
     * 客商中介个人资料
     */
    private void initMerchants() {
        company_ll.setVisibility(View.GONE);
        store_ll.setVisibility(View.GONE);
        declaration_ll.setVisibility(View.GONE);
        personalId_ll.setVisibility(View.GONE);
    }

    /**
     * 服务达人个人资料
     */
    private void initService() {
        payinfo_ll.setVisibility(View.GONE);
        tv_company.setText("公司");
        tv_store.setText("职位");
        declaration_ll.setVisibility(View.GONE);
        pay_ll.setVisibility(View.GONE);
    }

    @Override
    public void onClick(View v) {
        Bundle mBundle = new Bundle();
        int memberid = Integer.valueOf(ac.user_id).intValue();
        int roletype = Integer.valueOf(ac.user_type).intValue();
        mBundle.putInt("memberid", memberid);
        mBundle.putInt("sex", sex);
        mBundle.putInt("roletype", roletype);
        mBundle.putString("declaration", declaration);
        super.onClick(v);
        int id = v.getId();
        if (id == R.id.left_ll) {
            setResult(RESULT_OK);
            finish();
        }
        if (id == R.id.head_ll) {
            takePhoto();
        } else if (id == R.id.name_ll) {
            mBundle.putString("title", "姓名");
            mBundle.putString("content", name_tv.getText().toString().trim());
            UIHelper.jumpForResult(_activity, EditPersonInfoActivity.class, mBundle, 1001);
        } else if (id == R.id.declaration_ll) {
            mBundle.putString("title", "个人宣言");
            mBundle.putString("content", declaration_tv.getText().toString().trim());
//            mBundle.putBoolean("isPhone", true);
            UIHelper.jumpForResult(_activity, EditPersonInfoActivity.class, mBundle, 1002);
//            UIHelper.jumpForResult(_activity, EditPhoneActivity.class, 1002);
        } else if (id == R.id.sex_ll) {
            mBundle.putString("title", "性别");
            mBundle.putString("content", sex_tv.getText().toString().trim());
            UIHelper.jumpForResult(_activity, ChooseSexActivity.class, mBundle, 1003);
        } else if (id == R.id.area_ll) {
            String declaration = declaration_tv.getText().toString().trim();
            String position = tv_store.getText().toString().trim();
            mBundle.putString("title", "选择城市");
            mBundle.putString("declaration", declaration);
            mBundle.putString("position", position);
            mBundle.putString("content", area_tv.getText().toString().trim());
            UIHelper.jumpForResult(_activity, ChooseCitysActivity.class, mBundle, 1004);
        } else if (id == R.id.payinfo_ll) {
            UIHelper.jumpForResult(_activity, ChangeBankCardActivity_1.class, mBundle, 1005);
        }else if (id == R.id.phone_ll) {
            UIHelper.jump(_activity,ChangeNumberActivity_1.class);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case 1001:
                    name_tv.setText(data.getExtras().getString("content"));
                    ac.setProperty("nickname", data.getExtras().getString("content"));
                    break;
                case 1002:
                    declaration_tv.setText(data.getExtras().getString("content"));
                    ac.setProperty("declaration", data.getExtras().getString("content"));
                    break;
                case 1003:
                    if ("1".equals(data.getExtras().getString("sex"))) {
                        sex_tv.setText("男");
                    } else {
                        sex_tv.setText("女");
                    }
                    ac.setProperty("sex", data.getExtras().getString("sex"));
                    break;
                case 1004:
                    AppUtil.getPpApiClient(ac).memberMembershipInfoShow(ac.getProperty("user_type"), this);
                    break;
                case 1005:
                    loadData();
                    break;
            }
        }

    }

    Bitmap bitmap;
    String path;
    @Override
    public void outputBitmap(Bitmap bitmap, String path) {
        super.outputBitmap(bitmap, path);
        this.bitmap = bitmap;
        this.path = path;
        int userId=Integer.valueOf(ac.user_id);
        mHead = new File(path);
        try {
            AppUtil.getPpApiClient(ac).memberMembershipInfoUpdate(userId, null, ac.sex, null, null, null, ac.user_type, mHead, null, null, null, this);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onApiStart(String tag) {
        super.onApiStart(tag);
        topbar.showProgressBar();
        showLoadingDlg();

    }

    @Override
    public void onApiSuccess(NetResult res, String tag) {
        super.onApiSuccess(res, tag);
        topbar.hideProgressBar();
        hideLoadingDlg();
        if (res.isOK()) {
            if ("memberMembershipInfoShow".equals(tag)) {
                MemberMembershipInfoShowBean bean = (MemberMembershipInfoShowBean) res;
                head_hint.setVisibility(View.GONE);
                ac.setImage(head_iv, bean.getData().getMember().getPortrait());//头像
                name_tv.setText(bean.getData().getMember().getNickname());//名字
                sex = Integer.valueOf(bean.getData().getMember().getSex()).intValue();//性别
                if (sex == 0) {
                    sex_tv.setText("女");
                } else sex_tv.setText("男");
                area_tv.setText(bean.getData().getMember().getProvince() + " " + bean.getData().getMember().getCity() + " "
                        + bean.getData().getMember().getCounty());//地区
                phone_tv.setText(bean.getData().getMember().getMobilephone());//电话
                if ("1".equals(ac.getProperty("user_type"))) {
                    String str = bean.getData().getAgent().getBankcard();
                    initString(str, bankcard_tv, 4, 2);
//                    bankcard_tv.setText(bean.getData().getAgent().getBankcard());//银行号码
                    bank_tv.setText(bean.getData().getAgent().getBank());//银行
                } else if ("2".equals(ac.getProperty("user_type"))) {
                    String str = bean.getData().getCounselor().getIdcard();
                    initString(str, personalid_tv, 4, 4);
//                    personalid_tv.setText(bean.getData().getCounselor().getIdcard());//身份证
                    str = bean.getData().getCounselor().getBankcard();
                    initString(str, bankcard_tv, 4, 2);
//                    bankcard_tv.setText(bean.getData().getCounselor().getBankcard());//银行号码
                    bank_tv.setText(bean.getData().getCounselor().getBank());//银行
                    company_tv.setText(bean.getData().getCounselor().getAgencyCompany());//中介公司
                    store_tv.setText(bean.getData().getCounselor().getSubAgencyCompany());//分店
                    declaration = bean.getData().getCounselor().getDeclaration();//个人宣言
                    declaration_tv.setText(declaration);
                } else if ("4".equals(ac.getProperty("user_type"))) {
                    String str = bean.getData().getAgency_expert().getIdcard();
                    initString(str, personalid_tv, 4, 4);
//                    personalid_tv.setText(bean.getData().getAgency_expert().getIdcard());
                    company_tv.setText(bean.getData().getAgency_expert().getCompanyName());
                    store_tv.setText(bean.getData().getAgency_expert().getPosition());
                }
            }
            if ("memberMembershipInfoUpdate".equals(tag)){
                UIHelper.t(_activity, "头像修改成功");
                head_iv.setImageBitmap(bitmap);
                ac.setProperty("user_type_second",path);
            }
        } else {
            ac.handleErrorCode(_activity, res.errorcode);
        }
    }

    @Override
    public void onApiFailure(Throwable t, int errorNo, String strMsg, String tag) {
        super.onApiFailure(t, errorNo, strMsg, tag);
        hideLoadingDlg();
        topbar.hideProgressBar();
    }
}
