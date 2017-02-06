package com.midian.ppaddress.ui.personal;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.midian.ppaddress.R;
import com.midian.ppaddress.datasource.NewsHistoryDataSource;
import com.midian.ppaddress.itemtpl.NewsHistorytpl;
import com.midian.ppaddress.itemtpl.NewsHistorytpl_b;
import com.midian.ppaddress.itemtpl.NewsHistorytpl_c;

import java.util.ArrayList;

import midian.baselib.base.BaseListActivity;
import midian.baselib.base.BaseMultiTypeListActivity;
import midian.baselib.shizhefei.view.listviewhelper.IDataAdapter;
import midian.baselib.shizhefei.view.listviewhelper.IDataSource;
import midian.baselib.utils.UIHelper;
import midian.baselib.widget.BaseLibTopbarView;
import midian.baselib.widget.pulltorefresh.PullToRefreshBase;
//咨询记录
public class AskHistoryActivity extends BaseMultiTypeListActivity{
    private BaseLibTopbarView topbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        topbar = findView(R.id.topbar);
        topbar.setTitle("咨询记录").setLeftImageButton(R.drawable.icon_back, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setResult(RESULT_OK);
                finish();
            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.comman_list_item;
    }

    @Override
    protected IDataSource<ArrayList> getDataSource() {
        return new NewsHistoryDataSource(_activity);
    }

    @Override
    protected ArrayList<Class> getTemplateClasses() {
        ArrayList<Class> tpls=new ArrayList<>();
        tpls.add(NewsHistorytpl.class);
        tpls.add(NewsHistorytpl_c.class);
        tpls.add(NewsHistorytpl_b.class);
        return tpls;
    }
//
//    @Override
//    public void onStartRefresh(IDataAdapter adapter) {
//        super.onStartRefresh(adapter);
//        NewsHistoryDataSource.position=-1;
////        listViewHelper.doPullRefreshing(false,0);
//    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK){
            NewsHistoryDataSource.position=-1;
            listViewHelper.refresh();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        System.out.println("AskHistoryActivity.onResume()");
//        listViewHelper.refresh();
    }
}
