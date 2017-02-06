package com.midian.ppaddress.ui.chooseaddres;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.midian.ppaddress.R;

import midian.baselib.base.BaseActivity;
import midian.baselib.utils.UIHelper;
import midian.baselib.widget.BaseLibTopbarView;

/**
 * 委托选址
 * Created by chu on 2016/4/27.
 */
public class ChooseDemandActivity extends BaseActivity {
    private BaseLibTopbarView topbar;
    private TextView phone_tv,confirm_tv;
    private EditText edit_et;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_demand);

        topbar = findView(R.id.topbar);
        topbar.setTitle("投递选址需求").setLeftImageButton(R.drawable.icon_back, UIHelper.finish(_activity));
        phone_tv = findView(R.id.phone_tv);
        confirm_tv = findView(R.id.confirm_tv);
        edit_et = findView(R.id.edit_et);

        confirm_tv.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.confirm_tv:
                UIHelper.t(_activity,"已委托选址");
                finish();
                break;
        }
    }
}
