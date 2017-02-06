package com.midian.ppaddress.ui.personal;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.midian.baidupush.MyPushMessageReceiver;
import com.midian.baidupush.PushMessage;
import com.midian.ppaddress.R;
import com.midian.ppaddress.bean.BusinessInfrasListOrderStageBean;
import com.midian.ppaddress.bean.MessageCountInnerMessageBean;
import com.midian.ppaddress.datasource.BookingDataResource;
import com.midian.ppaddress.itemtpl.BookingListtpl;
import com.midian.ppaddress.itemtpl.BookingToptpl;
import com.midian.ppaddress.utils.AppUtil;

import java.util.ArrayList;

import midian.baselib.base.BaseMultiTypeListActivity;
import midian.baselib.bean.NetResult;
import midian.baselib.shizhefei.view.listviewhelper.IDataAdapter;
import midian.baselib.shizhefei.view.listviewhelper.IDataSource;
import midian.baselib.utils.UIHelper;
import midian.baselib.widget.BaseLibTopbarView;
import midian.baselib.widget.DimedView;
import midian.baselib.widget.SelectFilterView;

/**
 * 普通 用户预约
 */
public class BookingActivity extends BaseMultiTypeListActivity implements SelectFilterView.onTabChangeListener, DimedView.OpenCloseListener {
    private BaseLibTopbarView topbar;
    private SelectFilterView select;//选择过滤视图
    private DimedView dimedView;
    private String stage=null;//阶段id，2为已预约，3为已考察，4为意向同意
    private BookingDataResource data;
    private ArrayList<String> stageList=new ArrayList<>();
    private ArrayList<String> idList=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        topbar=findView(R.id.topbar);
        topbar.setTitle("我的预约").setLeftImageButton(R.drawable.icon_back, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setResult(RESULT_OK);
                finish();
            }
        });
        select=findView(R.id.select);
        select.setOnTabChangeListener(this);
        dimedView = (DimedView) findViewById(R.id.dimeView);
        dimedView.setOpenCloseListener(this);
        String[] tab={"筛选"};
        select.initTab(tab);
        loadData();
        dimedView.getListView().setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                dimedView.close();
                data.setStage(idList.get(i));
                listViewHelper.refresh();
            }
        });
        //注册监听推送接收
        MyPushMessageReceiver.addPushListener(mPushListener);

    }

    private void loadData() {
        try {
            AppUtil.getPpApiClient(ac).getOrderList(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onApiSuccess(NetResult res, String tag) {
        super.onApiSuccess(res, tag);
        if(res.isOK()){
            if("getOrderList".equals(tag)) {
                BusinessInfrasListOrderStageBean bean = (BusinessInfrasListOrderStageBean) res;
                stageList.add("全部");
                idList.add("");
                for (int i = 0; i < bean.getData().size(); i++) {
                    stageList.add(bean.getData().get(i).getName());
                    idList.add(bean.getData().get(i).getId());
                }

                dimedView.setAdapter(new BaseAdapter() {
                    @Override
                    public int getCount() {
                        return stageList.size();
                    }

                    @Override
                    public Object getItem(int i) {
                        return i;
                    }

                    @Override
                    public long getItemId(int i) {
                        return i;
                    }

                    @Override
                    public View getView(int i, View view, ViewGroup viewGroup) {
                        TextView textView = new TextView(getApplicationContext());
                        textView.setTextSize(15);
                        textView.setTextColor(getResources().getColor(R.color.text_bg60));
                        textView.setPadding(60, 40, 0, 40);
                        textView.setText(stageList.get(i));
                        return textView;
                    }
                });
            }
//            if("messageCountInnerMessage".equals(tag)){
//                MessageCountInnerMessageBean bean= (MessageCountInnerMessageBean) res;
//                if("0".equals(bean.getData().getOrders())){
//                    BookingToptpl.tv_book_count.setVisibility(View.GONE);
//                }else{
//                    BookingToptpl.tv_book_count.setVisibility(View.VISIBLE);
//                }
//            }
        }else{
            ac.handleErrorCode(_activity, res.errorcode);
        }
    }

    @Override
    public void onEndRefresh(IDataAdapter adapter, ArrayList result) {
        super.onEndRefresh(adapter, result);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    MyPushMessageReceiver.PushListener mPushListener=new MyPushMessageReceiver.PushListener() {
        @Override
        public void updateContent(PushMessage msg) {
            //有消息推送时，可以处理一些消息相关状态等
            listViewHelper.refresh();
        }
    };
    @Override
    public void onDestroy() {
        super.onDestroy();
        MyPushMessageReceiver.removePushListener(mPushListener);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.comman_list_select;
    }

    @Override
    protected IDataSource<ArrayList> getDataSource() {
        data=new BookingDataResource(_activity);
        return data;
    }

    @Override
    protected ArrayList<Class> getTemplateClasses() {
        ArrayList<Class> tpls=new ArrayList<Class>();
        tpls.add(BookingToptpl.class);
        tpls.add(BookingListtpl.class);
        return tpls;
    }

    @Override
    public void open() {

    }

    @Override
    public void close() {
        select.setchangeState(0, false);
    }

    @Override
    public void onTabChange(int index, boolean isSelect) {
        if (isSelect) {
            dimedView.show();
        } else {
            dimedView.close();
        }
    }
}
