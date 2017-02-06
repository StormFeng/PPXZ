package com.midian.ppaddress.ui.personal;
import android.content.Intent;
import android.os.Bundle;
import com.midian.login.R;
import com.midian.ppaddress.datasource.ChooseCitysDataSource;
import com.midian.ppaddress.itemtpl.ChooseCitysViewTpl;
import java.util.ArrayList;
import midian.baselib.base.BaseListActivity;
import midian.baselib.shizhefei.view.listviewhelper.IDataSource;
import midian.baselib.utils.UIHelper;
import midian.baselib.widget.BaseLibTopbarView;

/**
 * 选择城市
 *
 * @author chu
 */
public class ChooseCitysActivity extends BaseListActivity{

    private BaseLibTopbarView topbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK){
            setResult(RESULT_OK);
            finish();
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.chooseposition_list_item;
    }

    @Override
    protected IDataSource<ArrayList> getDataSource() {
        return new ChooseCitysDataSource(_activity);
    }

    @Override
    protected Class getTemplateClass() {
        return ChooseCitysViewTpl.class;
    }


    private void initView() {
        topbar = (BaseLibTopbarView) findViewById(R.id.topbar);
        topbar.setTitle("地区");
        topbar.setLeftImageButton(R.drawable.icon_back, null).setLeftText("返回", UIHelper.finish(_activity));
    }
}
