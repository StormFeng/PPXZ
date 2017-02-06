package com.midian.ppaddress.itemtpl;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import com.midian.ppaddress.bean.CityMultiItem;
import com.midian.ppaddress.R;

import midian.baselib.utils.UIHelper;
import midian.baselib.view.BaseTpl;

public class ChooseLocationCitysHeadTpl extends BaseTpl<CityMultiItem> implements View.OnClickListener {
    private TextView loc_city;

	public ChooseLocationCitysHeadTpl(Context context) {
		super(context);
	}

	public ChooseLocationCitysHeadTpl(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	@Override
	protected void initView() {
        loc_city = findView(R.id.loc_city);
        root.setOnClickListener(this);
    }

	@Override
	protected int getLayoutId() {
		return R.layout.item_choose_location_citys_head_tpl;
	}

	@Override
	public void setBean(CityMultiItem bean, int position ) {

        if ("".equals(ac.loc_city)) {
            loc_city.setText("定位失败");
        } else {
            loc_city.setText(ac.loc_city);

        }
    }

    @Override
    public void onClick(View view) {
        if("定位失败".equals(loc_city.getText())) {
            UIHelper.t(_activity, "定位失败");
//            LocationUtil.getInstance(_activity).startOneLocation(new LocationUtil.OneLocationListener() {
//                @Override
//                public void complete(BDLocation location) {
//                    String lon = location.getLongitude() + "";
//                    String lat = location.getLatitude() + "";
//                    ac.loc_city = location.getCity();
//                    AppUtil.getMomaApiClient(ac).getCity(ac.loc_city,(MainActivity)_activity);
//                    ac.saveLocation(lon, lat);
//                    System.out.println("初始化定位城市：：" + ac.loc_city + "::经纬度::" + ac.lon + "---" + ac.lat);
//                    _activity.finish();
//                }
//            });
        }else{
            ac.saveCityName(ac.loc_city, ac.loc_city_id);
            _activity.finish();
        }
    }
}
