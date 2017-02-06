package com.midian.ppaddress.ui.chooseaddres.detailfragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.midian.ppaddress.R;
import com.midian.ppaddress.bean.BusinessCarrierDetailShowBean;
import com.midian.ppaddress.utils.AppUtil;

import midian.baselib.api.ApiCallback;
import midian.baselib.base.BaseFragment;
import midian.baselib.bean.NetResult;

/**
 * 园区-详情页-推荐理由fragment
 * Created by chu on 2016/3/22.
 */
public class DetailRecommentFragment extends BaseFragment implements ApiCallback {
    private TextView summary_tv;
    private ImageView iv1,iv2,iv3,iv4,iv5, iv6;
//    private CarrierDetailData detailData;
    private String carrierid;

    public String getCarrierid() {
        return carrierid;
    }

    public void setCarrierid(String carrierid) {
        this.carrierid = carrierid;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_recomment, null);
        summary_tv = (TextView) v.findViewById(R.id.summary_tv);
        iv1 = (ImageView) v.findViewById(R.id.iv1);
        iv2 = (ImageView) v.findViewById(R.id.iv2);
        iv3 = (ImageView) v.findViewById(R.id.iv3);
        iv4 = (ImageView) v.findViewById(R.id.iv4);
        iv5 = (ImageView) v.findViewById(R.id.iv5);
        iv6 = (ImageView) v.findViewById(R.id.iv6);
//        setData();
        loadData();
        return v;
    }

    private void loadData() {
        AppUtil.getPpApiClient(ac).getCarrierDetailShow(carrierid, ac.user_id, ac.getProperty("user_type"), this);
    }

    private void setData() {
       /* summary_tv.setText(detailData.getSummary());
//        HashMap<ImageView, String> map = new HashMap<ImageView, String>();
        ac.setImage(iv1, detailData.getImages().get(0).getConver());
        ac.setImage(iv2, detailData.getImages().get(1).getConver());
        ac.setImage(iv3, detailData.getImages().get(2).getConver());
        ac.setImage(iv4, detailData.getImages().get(3).getConver());
        ac.setImage(iv5, detailData.getImages().get(4).getConver());
        ac.setImage(iv6, detailData.getImages().get(5).getConver());*/
    }

  /*  public CarrierDetailData getDetailData() {
        return detailData;
    }

    public void setDetailData(CarrierDetailData detailData) {
        this.detailData = detailData;
    }*/

    @Override
    public void onApiStart(String tag) {

    }

    @Override
    public void onApiLoading(long count, long current, String tag) {

    }

    @Override
    public void onApiSuccess(NetResult res, String tag) {
        if (res.isOK()) {
            BusinessCarrierDetailShowBean bean = (BusinessCarrierDetailShowBean) res;
            ac.setImage(iv1, bean.getData().getImages().get(0).getConver());
            ac.setImage(iv2, bean.getData().getImages().get(1).getConver());
            ac.setImage(iv3, bean.getData().getImages().get(2).getConver());
            ac.setImage(iv4, bean.getData().getImages().get(3).getConver());
            ac.setImage(iv5, bean.getData().getImages().get(4).getConver());
            ac.setImage(iv6, bean.getData().getImages().get(5).getConver());
            summary_tv.setText(bean.getData().getSummary());
        } else {
            ac.handleErrorCode(_activity, res.errorcode);
        }
    }

    @Override
    public void onApiFailure(Throwable t, int errorNo, String strMsg, String tag) {

    }

    @Override
    public void onParseError(String tag) {

    }
}
