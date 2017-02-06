package com.midian.ppaddress.ui.chooseaddres.detailfragment;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.midian.ppaddress.R;
import com.midian.ppaddress.datasource.OptionalUnitDataSource;
import com.midian.ppaddress.itemtpl.OptionalUnitTpl;
import com.midian.ppaddress.ui.chooseaddres.MoreOptionalUnitActivity;

import java.util.ArrayList;

import midian.baselib.base.BaseListFragment;
import midian.baselib.shizhefei.view.listviewhelper.IDataAdapter;
import midian.baselib.shizhefei.view.listviewhelper.IDataSource;
import midian.baselib.utils.UIHelper;

/**
 * 详情--可选单元
 * Created by chu on 2016/4/22.
 */
public class OptionalUnitFragment extends BaseListFragment implements View.OnClickListener {

    private TextView count_tv,more_tv;
    private String carrierid;
    private String carrier_type;

    public String getCarrierid() {
        return carrierid;
    }

    public void setCarrierid(String carrierid) {
        this.carrierid = carrierid;
    }

    public String getCarrier_type() {
        return carrier_type;
    }

    public void setCarrier_type(String carrier_type) {
        this.carrier_type = carrier_type;
    }

    @Override
    public void onViewCreated(View view,  Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        count_tv = (TextView) view.findViewById(R.id.count_tv);
        more_tv = (TextView) view.findViewById(R.id.more_tv);
        more_tv.setOnClickListener(this);

    }


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_optional_unit;
    }

    @Override
    protected IDataSource<ArrayList> getDataSource() {
        return new OptionalUnitDataSource(_activity,carrierid);
    }



    @Override
    protected Class getTemplateClass() {
        return OptionalUnitTpl.class;
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.more_tv:
//                UIHelper.t(_activity, "点击查看更多可选单元");
                Bundle mBundle = new Bundle();
                mBundle.putString("carrierid", carrierid);
                UIHelper.jump(_activity,MoreOptionalUnitActivity.class,mBundle);
                break;
        }
    }

    @Override
    public void onEndRefresh(IDataAdapter adapter, ArrayList result) {
        super.onEndRefresh(adapter, result);
//        count_tv.setText("有"+resultList+"种单元供您选择");
    }

    /*   @Override
    public void onEndRefresh(IDataAdapter<ArrayList> adapter, ArrayList result) {
        super.onEndRefresh(adapter, result);
        ArrayList<UnitsData> item = result;
        for (int i = 0; i < item.size(); i++) {

        }
    }*/
}
