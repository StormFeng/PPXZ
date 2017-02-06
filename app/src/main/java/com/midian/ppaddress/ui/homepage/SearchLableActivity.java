package com.midian.ppaddress.ui.homepage;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.midian.ppaddress.R;
import com.midian.ppaddress.bean.BusinessAppsearchSolabelsBean;
import com.midian.ppaddress.utils.AppUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

import com.midian.ppaddress.bean.BusinessAppsearchSolabelsBean.Data;

import midian.baselib.base.BaseActivity;
import midian.baselib.base.BaseListActivity;
import midian.baselib.bean.NetResult;
import midian.baselib.shizhefei.view.listviewhelper.IDataAdapter;
import midian.baselib.shizhefei.view.listviewhelper.IDataSource;
import midian.baselib.utils.UIHelper;
import midian.baselib.widget.BaseLibTopbarView;
import midian.baselib.widget.CircleImageView;

/**
 * 标签搜索页
 * Created by chu on 2016/5/18.
 */
public class SearchLableActivity extends BaseActivity implements View.OnKeyListener, AdapterView.OnItemClickListener {
    private BaseLibTopbarView topbar;
    private EditText input_et;
    private List<Data> dataList = new ArrayList<Data>();
    private ListView listview;
    private MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_lable);
        initView();
    }


    private void initView() {
        topbar = findView(R.id.topbar);
        topbar.setMode(BaseLibTopbarView.MODE_WITH_INPUT).setLeftImageButton(R.drawable.icon_back, UIHelper.finish(_activity));
        topbar.getInput_et().setHint("例如：近飞机场");
        listview = findView(R.id.listview);
        input_et = topbar.getInput_et();
        input_et.setImeOptions(EditorInfo.IME_ACTION_SEARCH);
        input_et.setOnKeyListener(this);

        adapter = new MyAdapter();
        listview.setAdapter(adapter);
        listview.setOnItemClickListener(this);

    }

    /**
     * 搜索框的键盘搜索键点击回调 // 输入完后按键盘上的搜索键
     *
     * @param view
     * @param keyCode
     * @param keyEvent
     * @return
     */
    @Override
    public boolean onKey(View view, int keyCode, KeyEvent keyEvent) {
        if (keyCode == KeyEvent.KEYCODE_ENTER && keyEvent.getAction() == KeyEvent.ACTION_DOWN) {// 修改回车键功能
            // 先隐藏键盘
            ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(
                    getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
            // 按完搜索键后将当前查询的关键字保存起来,如果该关键字已经存在就不执行保存
            String keyword = input_et.getText().toString().trim();
            if (keyword == null || keyword.length() <= 0 || keyword.equals("")) {
                UIHelper.t(_activity, "搜索内容不能为空");
                return false;
            }
            AppUtil.getPpApiClient(ac).soLabelsList(keyword, this);
        }
        return false;
    }

    @Override
    public void onApiStart(String tag) {
        super.onApiStart(tag);
        showLoadingDlg();
    }

    @Override
    public void onApiSuccess(NetResult res, String tag) {
        super.onApiSuccess(res, tag);
        hideLoadingDlg();
        if (res.isOK()) {
            BusinessAppsearchSolabelsBean bean = (BusinessAppsearchSolabelsBean) res;
            dataList.addAll(bean.getData());
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id ) {
        Bundle mBundle = new Bundle();
        mBundle.putString("lable_id", dataList.get(position).getId());
        mBundle.putString("lable_name", dataList.get(position).getLabel());
        setResult(RESULT_OK, mBundle);
        finish();
    }


    public class MyAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return dataList.size();
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int i, View view, ViewGroup viewGroup) {
            ViewHolder mHolder;
            if (view == null) {
                mHolder = new ViewHolder();
                LayoutInflater inflater = LayoutInflater.from(_activity);
                view = inflater.inflate(R.layout.item_search_lable, null);
                mHolder.lable_tv = (TextView) view.findViewById(R.id.lable_tv);
                mHolder.item_ll = view.findViewById(R.id.item_ll);
                view.setTag(mHolder);
            }
            mHolder = (ViewHolder) view.getTag();
            if (dataList!=null) {
                mHolder.lable_tv.setText(dataList.get(i).getLabel());
            }
            return view;
        }
        public class ViewHolder {
            TextView lable_tv;
            View item_ll;
        }
    }
}
