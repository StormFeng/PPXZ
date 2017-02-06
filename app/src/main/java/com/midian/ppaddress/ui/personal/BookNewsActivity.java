package com.midian.ppaddress.ui.personal;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.midian.ppaddress.R;
import com.midian.ppaddress.datasource.BookNewsDataSource;
import com.midian.ppaddress.itemtpl.BookNewstpl;
import com.midian.ppaddress.utils.AppUtil;

import java.util.ArrayList;

import midian.baselib.base.BaseActivity;
import midian.baselib.base.BaseListActivity;
import midian.baselib.shizhefei.view.listviewhelper.IDataSource;
import midian.baselib.utils.UIHelper;
import midian.baselib.widget.BaseLibTopbarView;

public class BookNewsActivity extends BaseListActivity {
    private BaseLibTopbarView topbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        topbar = findView(R.id.topbar);
        topbar.setTitle("预约消息").setLeftImageButton(R.drawable.icon_back, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setResult(RESULT_OK);
                finish();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==RESULT_OK){
            listViewHelper.refresh();
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.comman_list_item;
    }

    @Override
    protected IDataSource<ArrayList> getDataSource() {
        return  new BookNewsDataSource(_activity);
    }

    @Override
    protected Class getTemplateClass() {
        return BookNewstpl.class;
    }
}
