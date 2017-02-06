package com.midian.ppaddress.api;

import android.content.res.Resources;
import android.text.TextUtils;
import android.util.Log;

import com.midian.fastdevelop.afinal.http.AjaxParams;
import com.midian.ppaddress.app.Constant;
import com.midian.ppaddress.bean.AddMemberMeAgencyexpertBean;
import com.midian.ppaddress.bean.AddMemberMeOrderBean;
import com.midian.ppaddress.bean.AppBean;
import com.midian.ppaddress.bean.AppIndexBean;
import com.midian.ppaddress.bean.AppInfoBean;
import com.midian.ppaddress.bean.BusinessAgencyActivityShowBean;
import com.midian.ppaddress.bean.BusinessAgencyConsultAskQuestionBean;
import com.midian.ppaddress.bean.BusinessAgencyConsultPageRecordBean;
import com.midian.ppaddress.bean.BusinessAgencyConsultSendRecordBean;
import com.midian.ppaddress.bean.BusinessAgencyConsultShowQuestionBean;
import com.midian.ppaddress.bean.BusinessAgencyDetailListAgencyexpertBean;
import com.midian.ppaddress.bean.BusinessAgencyDetailPageCommentsBean;
import com.midian.ppaddress.bean.BusinessAgencyDetailShowDetailBean;
import com.midian.ppaddress.bean.BusinessAgencyScreenListIndustryBean;
import com.midian.ppaddress.bean.BusinessAgencyScreenPageActivityBean;
import com.midian.ppaddress.bean.BusinessAgencyScreenPageAgencyBean;
import com.midian.ppaddress.bean.BusinessAppsearchMapcarriersBean;
import com.midian.ppaddress.bean.BusinessAppsearchSoMapBean;
import com.midian.ppaddress.bean.BusinessAppsearchSolabelsBean;
import com.midian.ppaddress.bean.BusinessAppsearchSopageBean;
import com.midian.ppaddress.bean.BusinessAppsearchSoscreenBean;
import com.midian.ppaddress.bean.BusinessCarrierCommentsClickLikeBean;
import com.midian.ppaddress.bean.BusinessCarrierCommentsPageCounselorsBean;
import com.midian.ppaddress.bean.BusinessCarrierCommentsPageExpertsBean;
import com.midian.ppaddress.bean.BusinessCarrierDetailGetChildrenCarriersBean;
import com.midian.ppaddress.bean.BusinessCarrierDetailMapTrafficsBean;
import com.midian.ppaddress.bean.BusinessCarrierDetailPageCounselorBean;
import com.midian.ppaddress.bean.BusinessCarrierDetailPageUnitsBean;
import com.midian.ppaddress.bean.BusinessCarrierDetailQueryPropertiesBean;
import com.midian.ppaddress.bean.BusinessCarrierDetailShowBean;
import com.midian.ppaddress.bean.BusinessCarrierIndustryListBean;
import com.midian.ppaddress.bean.BusinessCommentsMembersBean;
import com.midian.ppaddress.bean.BusinessInfrasCarrierTypeBean;
import com.midian.ppaddress.bean.BusinessInfrasGetEezBean;
import com.midian.ppaddress.bean.BusinessInfrasListEezBean;
import com.midian.ppaddress.bean.BusinessInfrasListOrderStageBean;
import com.midian.ppaddress.bean.BusinessLocationCityCountysBean;
import com.midian.ppaddress.bean.BusinessLocationCityReleaseListBean;
import com.midian.ppaddress.bean.BusinessLocationCountyListBean;
import com.midian.ppaddress.bean.BusinessLocationProvinceCitysBean;
import com.midian.ppaddress.bean.BusinessLocationProvincesBean;
import com.midian.ppaddress.bean.ClinchDealListBean;
import com.midian.ppaddress.bean.CommentToServerBean;
import com.midian.ppaddress.bean.CompareDetailBean;
import com.midian.ppaddress.bean.ConsultListBean;
import com.midian.ppaddress.bean.ConsultantDetailBean;
import com.midian.ppaddress.bean.CounselorPageCarrierBean;
import com.midian.ppaddress.bean.EntercarrierApplyBean;
import com.midian.ppaddress.bean.EntercarrierPageEnterBean;
import com.midian.ppaddress.bean.EntercarrierPageOwnerCarrierBean;
import com.midian.ppaddress.bean.EntercarrierPageRentsBean;
import com.midian.ppaddress.bean.EntercarrierShowBean;
import com.midian.ppaddress.bean.FeedBackListBean;
import com.midian.ppaddress.bean.HelpListBean;
import com.midian.ppaddress.bean.MemberAuthorizeAuthBindBean;
import com.midian.ppaddress.bean.MemberCommentsAgencyexpertPageMeBean;
import com.midian.ppaddress.bean.MemberCommentsCounselorPageMeBean;
import com.midian.ppaddress.bean.MemberCommentsMemberPageMemberMeOrderBean;
import com.midian.ppaddress.bean.MemberCompareListBean;
import com.midian.ppaddress.bean.MemberCompareListTypeBean;
import com.midian.ppaddress.bean.MemberFavoriteAgencyBean;
import com.midian.ppaddress.bean.MemberFavoriteCarrierBean;
import com.midian.ppaddress.bean.MemberFavoritePageAgencyBean;
import com.midian.ppaddress.bean.MemberFavoritePageCarrierBean;
import com.midian.ppaddress.bean.MemberMembershipInfoShowBean;
import com.midian.ppaddress.bean.MemberMessagePageOrderMessagesBean;
import com.midian.ppaddress.bean.MemberMessagePageSysMessagesBean;
import com.midian.ppaddress.bean.MemberMessageShowSysMessagesBean;
import com.midian.ppaddress.bean.MemberOrderCounselorListContractBean;
import com.midian.ppaddress.bean.MemberOrderCounselorPageBean;
import com.midian.ppaddress.bean.MemberOrderCounselorPageOrderBean;
import com.midian.ppaddress.bean.MemberOrderMemberPageBean;
import com.midian.ppaddress.bean.MemberOrderMemberPageNewestBean;
import com.midian.ppaddress.bean.MemberOrderMemberPageSignOrderBean;
import com.midian.ppaddress.bean.MemberOrderMemberSendBeam;
import com.midian.ppaddress.bean.MemberOrderMemberShowBean;
import com.midian.ppaddress.bean.MemberOrderMemberSitedetailsBean;
import com.midian.ppaddress.bean.MessageCountInnerMessageBean;
import com.midian.ppaddress.bean.OrderCounselorPageNewestBean;
import com.midian.ppaddress.bean.OrderCounselorUploadContractBean;
import com.midian.ppaddress.bean.PersonalIndexBean;
import com.midian.ppaddress.bean.RewardPageOwnerCommissionBean;
import com.midian.ppaddress.bean.TradingInListBean;
import com.midian.ppaddress.bean.UpdateStageBean;
import com.midian.ppaddress.bean.UpdateUserBean;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

import midian.baselib.api.ApiCallback;
import midian.baselib.api.BaseApiClient;
import midian.baselib.app.AppContext;
import midian.baselib.bean.NetResult;
import midian.baselib.utils.Md5Utils;

/**
 * 网络请求客户端
 *
 * @author moshouguan
 */
public class PpApiClient extends BaseApiClient {

    public PpApiClient(AppContext api) {
        super(api);
    }

    static public void init(AppContext appcontext) {
        if (appcontext == null)
            return;
        appcontext.api.addApiClient(new PpApiClient(appcontext));
    }

    /**
     * 7.1首页
     *
     * @param callback
     */
    public void businessAppIndex(String cityid, ApiCallback callback) {
        AjaxParams params = new AjaxParams();
        params.put("client_key", ac.getClientKey());
        if (!TextUtils.isEmpty(ac.user_id)) {
            params.put("memberid", ac.user_id);
        }
        if (cityid != null) {
            params.put("cityid", cityid);
        }
        get(callback, Constant.BUSINESS_APP_INDEX, params, AppIndexBean.class, getMethodName(Thread.currentThread().getStackTrace()));
    }

    /**
     * 7.1首页
     */
    public AppIndexBean businessAppIndex() throws Exception {
        AjaxParams params = new AjaxParams();
        params.put("client_key", ac.getClientKey());
//        params.put("memberid",memberid+"");
//        params.put("cityid",cityid+"");
        return (AppIndexBean) getSync(Constant.BUSINESS_APP_INDEX, params, AppIndexBean.class);
    }

    /**
     * 7.2地图筛选
     *
     * @param callback
     */
    public void businessAppsearchSoscreen(ApiCallback callback) {
        AjaxParams params = new AjaxParams();
        params.put("client_key", ac.getClientKey());
        get(callback, Constant.BUSINESS_APPSEARCH_SOSCREEN, params, BusinessAppsearchSoscreenBean.class,
                getMethodName(Thread.currentThread().getStackTrace()));
    }

    /**
     * 7.3地图搜索标签
     *
     * @param keyword
     */
    public void soLabelsList(String keyword, ApiCallback callback) {
        AjaxParams params = new AjaxParams();
        params.put("client_key", ac.getClientKey());
        if (keyword != null) {
            params.put("keyword", keyword);
        }
        get(callback, Constant.BUSINESS_APPSEARCH_SOLABELS, params, BusinessAppsearchSolabelsBean.class,
                getMethodName(Thread.currentThread().getStackTrace()));
    }


    /**
     * 8.1选址-载体列表
     *
     * @param carrierType
     * @param labelids
     * @param saleRental
     * @param startPrice
     * @param endPrice
     * @param startArea
     * @param endArea
     * @param cityid
     * @param countyid
     * @param keyword
     * @param pageNo
     * @param callback
     */

    public void businessAppsearchSopage(String carrierType, String labelids, String saleRental, String startPrice,
                                        String endPrice, String startArea, String endArea, String cityid, String countyid,
                                        String keyword, String pageNo, ApiCallback callback) {
        AjaxParams params = new AjaxParams();
        params.put("client_key", ac.getClientKey());
        if (carrierType != null) {
            params.put("carrier.carrierType", carrierType + "");
        }
        if (labelids != null) {
            params.put("carrier.labelids", labelids);
        }
        if (saleRental != null) {
            params.put("carrier.saleRental", saleRental + "");
        }
        if (startPrice != null) {
            params.put("carrier.startPrice", startPrice + "");
        }
        if (endPrice != null) {
            params.put("carrier.endPrice", endPrice + "");
        }
        if (startArea != null) {
            params.put("carrier.startArea", startArea + "");
        }
        if (endArea != null) {
            params.put("carrier.endArea", endArea + "");
        }
        params.put("carrier.cityid", cityid + "");
        if (countyid != null) {
            params.put("carrier.countyid", countyid + "");
        }
        if (keyword != null) {
            params.put("carrier.keyword", keyword);
        }
        params.put("pageNo", pageNo + "");
        get(callback, Constant.BUSINESS_APPSEARCH_SOPAGE, params, BusinessAppsearchSopageBean.class,
                getMethodName(Thread.currentThread().getStackTrace()));
    }

    /**
     * 8.1选址-载体列表
     *
     * @param carrierType
     * @param labelids
     * @param saleRental
     * @param startPrice
     * @param endPrice
     * @param startArea
     * @param endArea
     * @param cityid
     * @param countyid
     * @param keyword
     * @param pageNo
     */

    public BusinessAppsearchSopageBean businessAppsearchSopage(String carrierType, String labelids, String saleRental, String startPrice,
                                                               String endPrice, String startArea, String endArea, String cityid, String countyid,
                                                               String keyword, String pageNo) throws Exception {
        AjaxParams params = new AjaxParams();
        params.put("client_key", ac.getClientKey());
        if (carrierType != null) {
            params.put("carrier.carrierType", carrierType);
        }
        if (labelids != null) {
            params.put("carrier.labelids", labelids);
        }
        if (saleRental != null) {
            params.put("carrier.saleRental", saleRental);
        }
        if (startPrice != null) {
            params.put("carrier.startPrice", startPrice);
        }
        if (endPrice != null) {
            params.put("carrier.endPrice", endPrice);
        }
        if (startArea != null) {
            params.put("carrier.startArea", startArea);
        }
        if (endArea != null) {
            params.put("carrier.endArea", endArea);
        }
        params.put("carrier.cityid", cityid);
        if (countyid != null) {
            params.put("carrier.countyid", countyid);
        }
        if (keyword != null) {
            params.put("carrier.keyword", keyword);
        }
        params.put("pageNo", pageNo);
        return (BusinessAppsearchSopageBean) getSync(Constant.BUSINESS_APPSEARCH_SOPAGE, params, BusinessAppsearchSopageBean.class);
    }

    /**
     * 8.2选址-地图选址
     *
     * @param carrierType
     * @param labelids
     * @param saleRental
     * @param startPrice
     * @param endPrice
     * @param startArea
     * @param endArea
     * @param cityid
     * @param countyid
     * @param keyword
     * @param leftUpLng
     * @param leftUpLat
     * @param rightLowLng
     * @param rightLowLat
     * @param arealevel
     * @param callback
     */

    public void businessAppsearchSomap(String carrierType, String labelids, String saleRental, String startPrice,
                                       String endPrice, String startArea, String endArea, String cityid, String countyid,
                                       String keyword, String leftUpLng, String leftUpLat, String rightLowLng,
                                       String rightLowLat, String arealevel, ApiCallback callback) {
        AjaxParams params = new AjaxParams();
        params.put("client_key", ac.getClientKey());
        if (carrierType != null) {
            params.put("so.carrierType", carrierType);
        }
        if (labelids != null) {
            params.put("so.labelids", labelids);
        }
        if (saleRental != null) {
            params.put("so.saleRental", saleRental);
        }
        if (startPrice != null) {
            params.put("so.startPrice", startPrice);
        }
        if (endPrice != null) {
            params.put("so.endPrice", endPrice);
        }
        if (startArea != null) {
            params.put("so.startArea", startArea);
        }
        if (endArea != null) {
            params.put("so.endArea", endArea);
        }
        params.put("so.cityid", cityid + "");
        if (countyid != null) {
            params.put("so.countyid", countyid);
        }
        if (keyword != null) {
            params.put("so.keyword", keyword);
        }
        if (leftUpLng != null) {
            params.put("so.leftUpLng", leftUpLng);
        }
        if (leftUpLat != null) {
            params.put("so.leftUpLat", leftUpLat);
        }
        if (rightLowLng != null) {
            params.put("so.rightLowLng", rightLowLng);
        }
        if (rightLowLat != null) {
            params.put("so.rightLowLat", rightLowLat);
        }
        params.put("so.arealevel", arealevel + "");
        get(callback, Constant.BUSINESS_APPSEARCH_SOMAP, params, BusinessAppsearchSoMapBean.class,
                getMethodName(Thread.currentThread().getStackTrace()));
    }

    /**
     * 8.3选址-地图选址-覆盖物载体列表
     *
     * @param carrierid
     * @param callback
     */
    public void businessAppsearchMapCarriers(String carrierid, ApiCallback callback) {
        AjaxParams params = new AjaxParams();
        params.put("client_key", ac.getClientKey());
        params.put("carrierid", carrierid);
        get(callback, Constant.business_appsearch_mapcarriers, params, BusinessAppsearchMapcarriersBean.class, getMethodName(Thread.currentThread().getStackTrace()));
    }

    /**
     * 8.4选址-载体详情
     *
     * @param carrierid
     * @param memberid
     * @param roletype
     */
    public BusinessCarrierDetailShowBean businessCarrierDetailShow(String carrierid, String memberid, String roletype) throws Exception {
        AjaxParams params = ac.getCAjaxParams();
        params.put("client_key", ac.getClientKey());
        params.put("carrierid", carrierid);
        if (carrierid != null) {
            params.put("memberid", memberid);
        }
        if (roletype != null) {
            params.put("roletype", roletype);
        }
        return (BusinessCarrierDetailShowBean) getSync(Constant.BUSINESS_CARRIER_DETAIL_SHOW, params, BusinessCarrierDetailShowBean.class);
    }
    /**
     * 8.4选址-载体详情
     *
     * @param carrierid
     * @param memberid
     * @param roletype
     */
    public void getCarrierDetailShow(String carrierid, String memberid, String roletype,ApiCallback callback) {
        AjaxParams params = ac.getCAjaxParams();
        params.put("client_key", ac.getClientKey());
        params.put("carrierid", carrierid);
        if (carrierid != null) {
            params.put("memberid", memberid);
        }
        if (roletype != null) {
            params.put("roletype", roletype);
        }
        get(callback, Constant.BUSINESS_CARRIER_DETAIL_SHOW, params, BusinessCarrierDetailShowBean.class, getMethodName(Thread.currentThread().getStackTrace()));
    }

    /**
     * 8.5选址-载体详情-物业顾问列表
     *
     * @param carrierid
     * @param pageNo
     * @param pageSize
     */
    public BusinessCarrierDetailPageCounselorBean businessCarrierDetailPageCounselor(String carrierid, String pageNo, String pageSize) throws Exception {
        AjaxParams params = ac.getCAjaxParams();
        params.put("client_key", ac.getClientKey());
        params.put("carrierid", carrierid);
        if (pageNo != null) {
            params.put("pageNo", pageNo + "");
        }
        if (pageSize != null) {
            params.put("pageSize", pageSize + "");
        }
        return (BusinessCarrierDetailPageCounselorBean) getSync(Constant.BUSINESS_CARRIER_DETAIL_PAGECOUNSELOR, params, BusinessCarrierDetailPageCounselorBean.class);
    }


    /**
     * 8.6载体详情--用户点评列表
     *
     * @param carrierid
     * @param pageNo
     * @param pageSize
     * @return
     * @throws Exception
     */
    public BusinessCommentsMembersBean businessCarrierCommentsPageMembers(String carrierid, String pageNo, String pageSize) throws Exception {
        AjaxParams params = new AjaxParams();
        params.put("client_key", ac.getClientKey());
        params.put("carrierid", carrierid);
        if (pageNo != null) {
            params.put("pageNo", pageNo);
        }
        if (pageSize != null) {
            params.put("pageSize", pageSize);
        }
        return (BusinessCommentsMembersBean) getSync(Constant.BUSINESS_CARRIER_COMMENTS_PAGEMEMBERS, params, BusinessCommentsMembersBean.class);
    }
    /**
     * 8.6载体详情--用户点评列表
     *
     * @param carrierid
     * @param pageNo
     * @param pageSize
     * @return
     * @throws Exception
     */
    public void businessCarrierCommentsPageMembers(String carrierid, String pageNo, String pageSize,ApiCallback callback)  {
        AjaxParams params = new AjaxParams();
        params.put("client_key", ac.getClientKey());
        params.put("carrierid", carrierid);
        if (pageNo != null) {
            params.put("pageNo", pageNo);
        }
        if (pageSize != null) {
            params.put("pageSize", pageSize);
        }
        get(callback, Constant.BUSINESS_CARRIER_COMMENTS_PAGEMEMBERS, params, BusinessCommentsMembersBean.class, getMethodName(Thread.currentThread().getStackTrace()));
    }

    /**
     * 8.7 选址-载体详情-物业顾问点评列表
     *
     * @param carrierid
     * @param pageNo
     * @param pageSize
     */
    public BusinessCarrierCommentsPageCounselorsBean businessCarrierCommentsPageCounselors(String carrierid, String pageNo, String pageSize) throws Exception {
        AjaxParams params = ac.getCAjaxParams();
        params.put("carrierid", carrierid);
        if (pageNo != null) {
            params.put("pageNo", pageNo);
        }
        if (pageSize != null) {
            params.put("pageSize", pageSize);
        }
        return (BusinessCarrierCommentsPageCounselorsBean) getSync(Constant.BUSINESS_CARRIER_COMMENTS_PAGECOUNSELORS, params, BusinessCarrierCommentsPageCounselorsBean.class);
    }
    /**
     * 8.7 选址-载体详情-物业顾问点评列表
     *
     * @param carrierid
     * @param pageNo
     * @param pageSize
     */
    public void businessCarrierCommentsPageCounselors(String carrierid, String pageNo, String pageSize,ApiCallback callback){
        AjaxParams params = ac.getCAjaxParams();
        params.put("carrierid", carrierid);
        if (pageNo != null) {
            params.put("pageNo", pageNo);
        }
        if (pageSize != null) {
            params.put("pageSize", pageSize);
        }
//        return (BusinessCarrierCommentsPageCounselorsBean) getSync();
        get(callback,  Constant.BUSINESS_CARRIER_COMMENTS_PAGECOUNSELORS,params,BusinessCarrierCommentsPageCounselorsBean.class, getMethodName(Thread.currentThread().getStackTrace()));
    }

    /**
     * 8.8 选址-载体详情-专家点评列表
     *
     * @param carrierid
     * @param pageNo
     * @param pageSize
     */
    public BusinessCarrierCommentsPageExpertsBean businessCarrierCommentsPageExperts(String carrierid, String pageNo, String pageSize) throws Exception {
        AjaxParams params = ac.getCAjaxParams();
        params.put("client_key", ac.getClientKey());
        params.put("carrierid", carrierid);
        if (pageNo != null) {
            params.put("pageNo", pageNo);
        }
        if (pageSize != null) {
            params.put("pageSize", pageSize);
        }
        return (BusinessCarrierCommentsPageExpertsBean) getSync(Constant.BUSINESS_CARRIER_COMMENTS_PAGEEXPERTS, params, BusinessCarrierCommentsPageExpertsBean.class);
    }
    /**
     * 8.8 选址-载体详情-专家点评列表
     *
     * @param carrierid
     * @param pageNo
     * @param pageSize
     */
    public void businessCarrierCommentsPageExperts(String carrierid, String pageNo, String pageSize,ApiCallback callback) {
        AjaxParams params = ac.getCAjaxParams();
        params.put("client_key", ac.getClientKey());
        params.put("carrierid", carrierid);
        if (pageNo != null) {
            params.put("pageNo", pageNo);
        }
        if (pageSize != null) {
            params.put("pageSize", pageSize);
        }
//        return (BusinessCarrierCommentsPageExpertsBean) getSync();
        get(callback, Constant.BUSINESS_CARRIER_COMMENTS_PAGEEXPERTS, params, BusinessCarrierCommentsPageExpertsBean.class, getMethodName(Thread.currentThread().getStackTrace()));
    }

    /**
     * 8.9选址-载体详情-点评列表-点赞功能
     *
     * @param commentid
     * @param callback
     */
    public void businessCarrierCommentsClickLike(String commentid, ApiCallback callback) {
        AjaxParams params = ac.getCAjaxParams();
        params.put("commentid", commentid);
        post(callback, Constant.BUSINESS_CARRIER_COMMENTS_CLICKLIKE, params, BusinessCarrierCommentsClickLikeBean.class,
                getMethodName(Thread.currentThread().getStackTrace()));
    }

    /**
     * 8.10载体-载体详情-可选单元列表
     *
     * @param carrierid
     * @param roletype
     * @param pageNo
     * @param pageSize
     */
    public BusinessCarrierDetailPageUnitsBean businessCarrierDetailPageUnits(String carrierid, String roletype, String pageNo, String pageSize) throws Exception {
        AjaxParams params = ac.getCAjaxParams();
        params.put("client_key", ac.getClientKey());
        params.put("carrierid", carrierid);
        params.put("roletype", roletype);
        if (pageNo != null) {
            params.put("pageNo", pageNo);
        }
        if (pageSize != null) {
            params.put("pageSize", pageSize);
        }
        return (BusinessCarrierDetailPageUnitsBean) getSync(Constant.BUSINESS_CARRIER_DETAIL_PAGEUNITS, params, BusinessCarrierDetailPageUnitsBean.class);
    }

    /**
     * 8.10载体-载体详情-可选单元列表
     *
     * @param carrierid
     * @param roletype
     * @param pageNo
     * @param pageSize
     */
    public void businessCarrierDetailPageUnits(String carrierid, String roletype, String pageNo, String pageSize,ApiCallback callback)  {
        AjaxParams params = ac.getCAjaxParams();
        params.put("client_key", ac.getClientKey());
        params.put("carrierid", carrierid);
        params.put("roletype", roletype);
        if (pageNo != null) {
            params.put("pageNo", pageNo);
        }
        if (pageSize != null) {
            params.put("pageSize", pageSize);
        }
//        return (BusinessCarrierDetailPageUnitsBean) getSync(Constant.BUSINESS_CARRIER_DETAIL_PAGEUNITS, params, BusinessCarrierDetailPageUnitsBean.class);
        get(callback, Constant.BUSINESS_CARRIER_DETAIL_PAGEUNITS, params, BusinessCarrierDetailPageUnitsBean.class, getMethodName(Thread.currentThread().getStackTrace()));
    }

    /**
     * 8.11载体-载体详情-基本参数
     *
     * @param carrierid
     */
    public BusinessCarrierDetailQueryPropertiesBean carrierDetailList(String carrierid) throws Exception {
        AjaxParams params = ac.getCAjaxParams();
        params.put("client_key", ac.getClientKey());
        params.put("carrierid", carrierid + "");
        return (BusinessCarrierDetailQueryPropertiesBean) getSync(Constant.BUSINESS_CARRIER_DETAIL_QUERYPROPERTIES, params, BusinessCarrierDetailQueryPropertiesBean.class);
    }
    /**
     * 8.11载体-载体详情-基本参数
     *
     * @param carrierid
     */
    public void carrierDetailList(String carrierid,ApiCallback callback)  {
        AjaxParams params = ac.getCAjaxParams();
        params.put("client_key", ac.getClientKey());
        params.put("carrierid", carrierid + "");
//        return (BusinessCarrierDetailQueryPropertiesBean) getSync();
        get(callback, Constant.BUSINESS_CARRIER_DETAIL_QUERYPROPERTIES, params, BusinessCarrierDetailQueryPropertiesBean.class, getMethodName(Thread.currentThread().getStackTrace()));
    }


    /**
     * 8.12载体-载体详情-载体选址列表
     *
     * @param carrierid
     * @param pageNo
     * @param pageSize
     */
    public BusinessCarrierDetailGetChildrenCarriersBean chooseCarrierList(String carrierid, String pageNo, String pageSize) throws Exception {
        AjaxParams params = new AjaxParams();
        params.put("client_key", ac.getClientKey());
        params.put("carrierid", carrierid);
        if (pageNo != null) {
            params.put("pageNo", pageNo);
        }
        if (pageSize != null) {
            params.put("pageSize", pageSize);
        }
        return (BusinessCarrierDetailGetChildrenCarriersBean) getSync(Constant.BUSINESS_CARRIER_DETAIL_GETCHILDRENCARRIERS, params, BusinessCarrierDetailGetChildrenCarriersBean.class);
    }

    /**
     * 8.13载体-载体详情-交通区位地图
     *
     * @param carrierid
     * @param callback
     */
    public void businessCarrierDetailMapTraffics(String carrierid, ApiCallback callback) {
        AjaxParams params = new AjaxParams();
        params.put("client_key", ac.getClientKey());
        params.put("carrierid", carrierid);
        get(callback, Constant.BUSINESS_CARRIER_DETAIL_MAPTRAFFICS, params, BusinessCarrierDetailMapTrafficsBean.class,
                getMethodName(Thread.currentThread().getStackTrace()));
    }

    /**
     * 9.1个人中心-我的对比-对比类型
     *
     * @param memberid
     */
    public MemberCompareListTypeBean compareTypeList(int memberid) throws Exception {
        AjaxParams params = ac.getCAjaxParams();
        params.put("client_key", ac.getClientKey());
        params.put("memberid", memberid + "");
        return (MemberCompareListTypeBean) getSync(Constant.MEMBER_COMPARE_LISTTYPE, params, MemberCompareListTypeBean.class);
    }

    /**
     * 9.2个人中心-我的对比-对比列表
     *
     * @param carrierType
     */
    public MemberCompareListBean compareList(String carrierType) throws Exception {
        AjaxParams params = ac.getCAjaxParams();
        params.put("carrierType", carrierType);
        return (MemberCompareListBean) getSync(Constant.MEMBER_COMPARE_LIST, params, MemberCompareListBean.class);
    }

    /**
     * 9.3个人中心-我的对比-批量删除对比
     *
     * @param compareids
     * @param callback
     */
    public void memberCompareClear(String compareids, ApiCallback callback) {
        AjaxParams params = new AjaxParams();
        params.put("client_key", ac.getClientKey());
        params.put("compareids", compareids);
        post(callback, Constant.MEMBER_COMPARE_CLEAR, params, NetResult.class,
                getMethodName(Thread.currentThread().getStackTrace()));
    }

    /**
     * 9.4个人中心-我的对比-添加对比
     *
     * @param carrierid
     * @param callback
     */
    public void memberCompareAdd(String carrierid, ApiCallback callback) {
        AjaxParams params = ac.getCAjaxParams();
        params.put("carrierid", carrierid);
        post(callback, Constant.MEMBER_COMPARE_ADD, params, NetResult.class,
                getMethodName(Thread.currentThread().getStackTrace()));
    }

    /**
     * 9.5个人中心-我的对比-对比详情
     *
     * @param carrierType
     * @param compareids
     * @return
     * @throws Exception
     */
    public CompareDetailBean memberCompareDetailList(String carrierType, String compareids) throws Exception {
        AjaxParams params = ac.getCAjaxParams();
        params.put("carrierType", carrierType);
        params.put("compareids", compareids);
        return (CompareDetailBean) getSync(Constant.MEMBER_COMPARE_DETAIL, params, CompareDetailBean.class);
    }

    /**
     * 9.5个人中心-我的对比-对比详情
     *
     * @param carrierType
     * @param compareids
     * @return
     * @throws Exception
     */
    public void memberCompareDetailList(String carrierType, String compareids, ApiCallback callback) {
        AjaxParams params = ac.getCAjaxParams();
        params.put("carrierType", carrierType);
        params.put("compareids", compareids);
//        return (CompareDetailBean) getSync(Constant.MEMBER_COMPARE_DETAIL, params, CompareDetailBean.class);
        get(callback, Constant.MEMBER_COMPARE_DETAIL, params, CompareDetailBean.class, getMethodName(Thread.currentThread().getStackTrace()));
    }

    /**
     * 10.1服务机构-主屏-机构行业
     *
     * @return
     * @throws Exception
     */
    public BusinessAgencyScreenListIndustryBean getIndustryList() throws Exception {
        AjaxParams params = ac.getCAjaxParams();
        params.put("client_key", ac.getClientKey());
        return (BusinessAgencyScreenListIndustryBean) getSync(Constant.BUSINESS_AGENCY_SCREEN_LISTINDUSTRY, params, BusinessAgencyScreenListIndustryBean.class);
    }

    /**
     * 10.2服务机构-主屏-活动列表
     *
     * @param pageNo
     * @param callback
     */
    public void businessAgencyScreenPageActivity(int pageNo, ApiCallback callback) {
        AjaxParams params = ac.getCAjaxParams();
        params.put("client_key", ac.getClientKey());
        params.put("pageNo", pageNo + "");
        get(callback, Constant.BUSINESS_AGENCY_SCREEN_PAGEACTIVITY, params, BusinessAgencyScreenPageActivityBean.class,
                getMethodName(Thread.currentThread().getStackTrace()));
    }

    /**
     * 10.2 服务机构-主屏-活动列表
     *
     * @param pageNo
     * @return
     * @throws Exception
     */
    public BusinessAgencyScreenPageActivityBean getScreenPageBean(int pageNo) throws Exception {
        AjaxParams params = ac.getCAjaxParams();
        params.put("client_key", ac.getClientKey());
        params.put("pageNo", pageNo + "");
        return (BusinessAgencyScreenPageActivityBean) getSync(Constant.BUSINESS_AGENCY_SCREEN_PAGEACTIVITY, params, BusinessAgencyScreenPageActivityBean.class);
    }

    /**
     * 10.3服务机构-主屏-服务机构列表
     *
     * @param pageNo
     * @param industryid
     * @param callback
     */
    public void businessAgencyScreenPageAgency(int pageNo, int industryid, ApiCallback callback) {
        AjaxParams params = ac.getCAjaxParams();
        params.put("client_key", ac.getClientKey());
        params.put("pageNo", pageNo + "");
        params.put("industryid", industryid + "");
        get(callback, Constant.BUSINESS_AGENCY_SCREEN_PAGEAGENCY, params, BusinessAgencyScreenPageAgencyBean.class,
                getMethodName(Thread.currentThread().getStackTrace()));
    }

    /**
     * 10.3服务机构-主屏-服务机构列表
     *
     * @param pageNo
     * @param industryid
     * @return
     * @throws Exception
     */
    public BusinessAgencyScreenPageAgencyBean getAgencyBean(int pageNo, int industryid) throws Exception {
        AjaxParams params = ac.getCAjaxParams();
        params.put("client_key", ac.getClientKey());
        params.put("pageNo", pageNo + "");
        params.put("industryid", industryid + "");
        return (BusinessAgencyScreenPageAgencyBean) getSync(Constant.BUSINESS_AGENCY_SCREEN_PAGEAGENCY, params, BusinessAgencyScreenPageAgencyBean.class);
    }

    /**
     * 10.4 服务机构-主屏-一键申请
     *
     * @param memberid
     * @param requestcontent
     * @param callback
     */
    public void businessAgencyScreenOnekeyApply(String memberid, String requestcontent, ApiCallback callback) {
        AjaxParams params = ac.getCAjaxParams();
        params.put("client_key", ac.getClientKey());
        params.put("memberid", memberid + "");
        params.put("request.content", requestcontent);
        post(callback, Constant.BUSINESS_AGENCY_SCREEN_ONEKEYAPPLY, params, NetResult.class,
                getMethodName(Thread.currentThread().getStackTrace()));
    }

    /**
     * 10.5 服务机构-详细页-详情
     *
     * @param agencyid
     * @param memberid
     * @param callback
     */
    public void businessAgencyDetailShowDetail(String agencyid, String memberid, ApiCallback callback) {
        AjaxParams params = ac.getCAjaxParams();
        params.put("client_key", ac.getClientKey());
        params.put("agencyid", agencyid);
        params.put("memberid", memberid);
        get(callback, Constant.BUSINESS_AGENCY_DETAIL_SHOWDETAIL, params, BusinessAgencyDetailShowDetailBean.class,
                getMethodName(Thread.currentThread().getStackTrace()));
    }

    /**
     * 10.5 服务机构-详细页-详情
     *
     * @param agencyid
     * @param memberid
     * @return
     * @throws Exception
     */
    public BusinessAgencyDetailShowDetailBean getBusinessDetailPage(String agencyid, String memberid) throws Exception {
        AjaxParams params = ac.getCAjaxParams();
        params.put("client_key", ac.getClientKey());
        params.put("agencyid", agencyid);
        params.put("memberid", memberid);
        return (BusinessAgencyDetailShowDetailBean) getSync(Constant.BUSINESS_AGENCY_DETAIL_SHOWDETAIL, params, BusinessAgencyDetailShowDetailBean.class);
    }


    /**
     * 10.6 服务机构-详细页-评论列表
     *
     * @param pageNo
     * @param agencyid
     * @param callback
     */
    public void businessAgencyDetailPageComments(String pageNo, String agencyid, ApiCallback callback) {
        AjaxParams params = ac.getCAjaxParams();
        params.put("client_key", ac.getClientKey());
        params.put("pageNo", pageNo);
        params.put("agencyid", agencyid);
        get(callback, Constant.BUSINESS_AGENCY_DETAIL_PAGECOMMENTS, params, BusinessAgencyDetailPageCommentsBean.class,
                getMethodName(Thread.currentThread().getStackTrace()));
    }

    /**
     * 10.6 服务机构-详细页-评论列表
     *
     * @param pageNo
     * @param agencyid
     * @return
     * @throws Exception
     */
    public BusinessAgencyDetailPageCommentsBean getCommentBean(String pageNo, String agencyid) throws Exception {
        AjaxParams params = ac.getCAjaxParams();
        params.put("client_key", ac.getClientKey());
        params.put("pageNo", pageNo);
        params.put("agencyid", agencyid);
        return (BusinessAgencyDetailPageCommentsBean) getSync(Constant.BUSINESS_AGENCY_DETAIL_PAGECOMMENTS, params, BusinessAgencyDetailPageCommentsBean.class);
    }

    /**
     * 10.7 服务机构-详细页-服务达人列表
     *
     * @param pageNo
     * @param agencyid
     * @param callback
     */
    public void businessAgencyDetailListAgencyexpert(String pageNo, int agencyid, ApiCallback callback) {
        AjaxParams params = ac.getCAjaxParams();
        params.put("client_key", ac.getClientKey());
        params.put("pageNo", pageNo);
        params.put("request.content", agencyid + "");
        get(callback, Constant.BUSINESS_AGENCY_DETAIL_LISTAGENCYEXPERT, params, BusinessAgencyDetailListAgencyexpertBean.class,
                getMethodName(Thread.currentThread().getStackTrace()));
    }

    public BusinessAgencyDetailListAgencyexpertBean getAgencyexpertList(String pageNo, int agencyid) throws Exception {
        AjaxParams params = ac.getCAjaxParams();
        params.put("client_key", ac.getClientKey());
        params.put("pageNo", pageNo);
        params.put("request.content", agencyid + "");
        return (BusinessAgencyDetailListAgencyexpertBean) getSync(Constant.BUSINESS_AGENCY_DETAIL_LISTAGENCYEXPERT, params, BusinessAgencyDetailListAgencyexpertBean.class);
    }

    /**
     * 10.7 服务机构-详细页-服务达人列表
     *
     * @param pageNo
     * @param agencyid
     */
    public BusinessAgencyDetailListAgencyexpertBean getAgencyexpert(String pageNo, String agencyid) throws Exception {
        AjaxParams params = ac.getCAjaxParams();
        params.put("client_key", ac.getClientKey());
        params.put("pageNo", pageNo);
        params.put("agencyid", agencyid + "");
        return (BusinessAgencyDetailListAgencyexpertBean) getSync(Constant.BUSINESS_AGENCY_DETAIL_LISTAGENCYEXPERT, params, BusinessAgencyDetailListAgencyexpertBean.class);
    }


    /**
     * 10.8 服务机构-咨询-发起问题
     *
     * @param memberid
     * @param agencyexpertid
     * @param question
     * @param callback
     */
    public void businessAgencyConsultAskQuestion(String memberid, String agencyexpertid, String question, ApiCallback callback) {
        AjaxParams params = ac.getCAjaxParams();
        params.put("client_key", ac.getClientKey());
        params.put("memberid", memberid);
        params.put("agencyexpertid", agencyexpertid + "");
        params.put("question.question", question);
        post(callback, Constant.BUSINESS_AGENCY_CONSULT_ASKQUESTION, params, BusinessAgencyConsultAskQuestionBean.class,
                getMethodName(Thread.currentThread().getStackTrace()));
    }

    /**
     * 10.8 服务机构-咨询-发起问题
     *
     * @param memberid
     * @param agencyexpertid
     * @param question
     * @return
     * @throws Exception
     */
    public BusinessAgencyConsultAskQuestionBean getAgencyConsultAskQuestionBean(int memberid, int agencyexpertid, String question) throws Exception {
        AjaxParams params = ac.getCAjaxParams();
        params.put("client_key", ac.getClientKey());
        params.put("memberid", memberid + "");
        params.put("agencyexpertid", agencyexpertid + "");
        params.put("question.question", question);
        return (BusinessAgencyConsultAskQuestionBean) postSync(Constant.BUSINESS_AGENCY_CONSULT_ASKQUESTION, params, BusinessAgencyConsultAskQuestionBean.class);
    }

    /**
     * 10.9 服务机构-咨询-会话-留言内容列表
     *
     * @param memberid
     * @param roletype
     * @param questionid
     * @param pageNo
     * @param callback
     */
    public void businessAgencyConsultPageRecord(String memberid, String roletype, String questionid, int pageNo, ApiCallback callback) {
        AjaxParams params = ac.getCAjaxParams();
        params.put("client_key", ac.getClientKey());
        params.put("memberid", memberid + "");
        params.put("roletype", roletype + "");
        params.put("questionid", questionid + "");
        params.put("pageNo", pageNo + "");
        get(callback, Constant.BUSINESS_AGENCY_CONSULT_PAGERECORD, params, BusinessAgencyConsultPageRecordBean.class,
                getMethodName(Thread.currentThread().getStackTrace()));
    }

    /**
     * 10.9 服务机构-咨询-会话-留言内容列表
     *
     * @param memberid
     * @param roletype
     * @param questionid
     * @param pageNo
     * @return
     * @throws Exception
     */
    public BusinessAgencyConsultPageRecordBean getAgengConsulPage(String memberid, String roletype, String questionid, int pageNo) throws Exception {
        AjaxParams params = ac.getCAjaxParams();
        params.put("client_key", ac.getClientKey());
        params.put("memberid", memberid + "");
        params.put("roletype", roletype + "");
        params.put("questionid", questionid + "");
        params.put("pageNo", pageNo + "");
        return (BusinessAgencyConsultPageRecordBean) getSync(Constant.BUSINESS_AGENCY_CONSULT_PAGERECORD, params, BusinessAgencyConsultPageRecordBean.class);
    }

    /**
     * 10.10 服务机构-咨询-会话-问题
     *
     * @param questionid
     * @param callback
     */
    public void businessAgencyConsultShowQuestion(int questionid, ApiCallback callback) {
        AjaxParams params = ac.getCAjaxParams();
        params.put("client_key", ac.getClientKey());
        params.put("questionid", questionid + "");
        get(callback, Constant.BUSINESS_AGENCY_CONSULT_SHOWQUESTION, params, BusinessAgencyConsultShowQuestionBean.class,
                getMethodName(Thread.currentThread().getStackTrace()));
    }

    /**
     * 10.10 服务机构-咨询-会话-问题
     *
     * @param questionid
     * @return
     * @throws Exception
     */
    public BusinessAgencyConsultShowQuestionBean getAgencyConsultShowQuestionBean(String questionid) throws Exception {
        AjaxParams params = ac.getCAjaxParams();
        params.put("client_key", ac.getClientKey());
        params.put("questionid", questionid + "");
        return (BusinessAgencyConsultShowQuestionBean) getSync(Constant.BUSINESS_AGENCY_CONSULT_SHOWQUESTION, params, BusinessAgencyConsultShowQuestionBean.class);
    }

    /**
     * 10.11 服务机构-咨询-关闭会话
     *
     * @param questionid
     * @param memberid
     * @param roletype
     * @param callback
     */
    public void businessAgencyConsultCloseQuestion(String questionid, String memberid, String roletype, ApiCallback callback) {
        AjaxParams params = ac.getCAjaxParams();
        params.put("client_key", ac.getClientKey());
        params.put("questionid", questionid + "");
        params.put("memberid", memberid + "");
        params.put("roletype", roletype + "");
        post(callback, Constant.BUSINESS_AGENCY_CONSULT_CLOSEQUESTION, params, NetResult.class,
                getMethodName(Thread.currentThread().getStackTrace()));
    }

    /**
     * 10.12 服务机构-咨询-发送记录
     *
     * @param senderid
     * @param roletype
     * @param receiverid
     * @param questionid
     * @param content
     * @param callback
     */
    public void businessAgencyConsultSendRecord(String senderid, String roletype, String receiverid, String questionid,
                                                String content, ApiCallback callback) {
        AjaxParams params = ac.getCAjaxParams();
        params.put("client_key", ac.getClientKey());
        params.put("senderid", senderid + "");
        params.put("roletype", roletype + "");
        params.put("receiverid", receiverid + "");
        params.put("questionid", questionid + "");
        params.put("content", content);
        post(callback, Constant.BUSINESS_AGENCY_CONSULT_SENDRECORD, params, BusinessAgencyConsultSendRecordBean.class,
                getMethodName(Thread.currentThread().getStackTrace()));
    }

    /**
     * 10.13 服务机构-活动页-详情
     *
     * @param activityid
     * @param memberid
     * @param callback
     */
    public void businessAgencyActivityShow(int activityid, String memberid, ApiCallback callback) {
        AjaxParams params = ac.getCAjaxParams();
        params.put("client_key", ac.getClientKey());
        params.put("activityid", activityid + "");
        params.put("memberid", memberid + "");
        get(callback, Constant.BUSINESS_AGENCY_ACTIVITY_SHOW, params, BusinessAgencyActivityShowBean.class,
                getMethodName(Thread.currentThread().getStackTrace()));
    }

    /**
     * 10.14 服务机构-活动页-活动申请
     *
     * @param activityid
     * @param memberid
     * @param fullname
     * @param callback
     */
    public void businessAgencyActivityApply(int activityid, String memberid, String fullname, ApiCallback callback) {
        AjaxParams params = ac.getCAjaxParams();
        params.put("client_key", ac.getClientKey());
        params.put("activityid", activityid + "");
        params.put("memberid", memberid);
        params.put("apply.fullname", fullname);
        post(callback, Constant.BUSINESS_AGENCY_ACTIVITY_APPLY, params, NetResult.class,
                getMethodName(Thread.currentThread().getStackTrace()));
    }

    /**
     * 11.1系统消息列表
     *
     * @param memberid
     * @param pageNo
     * @param pageSize
     * @param callback
     */
    public void memberMessagePageSysMessages(int memberid, int roletype, int pageNo, int pageSize, ApiCallback callback) {
        AjaxParams params = ac.getCAjaxParams();
        params.put("client_key", ac.getClientKey());
        params.put("memberid", memberid + "");
        params.put("roletype", roletype + "");
        params.put("pageNo", pageNo + "");
        params.put("pageSize", pageSize + "");
        get(callback, Constant.MEMBER_MESSAGE_PAGESYSMESSAGES, params, MemberMessagePageSysMessagesBean.class,
                getMethodName(Thread.currentThread().getStackTrace()));
    }

    /**
     * 11.1系统消息列表
     *
     * @param memberid
     * @param pageNo
     * @param pageSize
     * @return
     * @throws Exception
     */
    public MemberMessagePageSysMessagesBean getMemberMessagePageSysMessages(String memberid,String roletype, int pageNo, int pageSize) throws Exception {
        AjaxParams params = ac.getCAjaxParams();
        params.put("client_key", ac.getClientKey());
        params.put("memberid", memberid);
        params.put("roletype", roletype);
        params.put("pageNo", pageNo + "");
        params.put("pageSize", pageSize + "");
        return (MemberMessagePageSysMessagesBean) getSync(Constant.MEMBER_MESSAGE_PAGESYSMESSAGES, params, MemberMessagePageSysMessagesBean.class);
    }

    /**
     * 11.2 系统消息详情
     *
     * @param messageid
     * @param callback
     */
    public void memberMessageShowSysMessages(String messageid, ApiCallback callback) {
        AjaxParams params = new AjaxParams();
        params.put("client_key", ac.getClientKey());
        params.put("messageid", messageid);
        get(callback, Constant.MEMBER_MESSAGE_SHOWSYSMESSAGES, params, MemberMessageShowSysMessagesBean.class,
                getMethodName(Thread.currentThread().getStackTrace()));
    }

    /**
     * 11.3 预约消息列表
     *
     * @param memberid
     * @param roletype
     * @param pageNo
     * @param pageSize
     * @param callback
     */
    public void memberMessagePageOrderMessages(int memberid, int roletype, int pageNo, int pageSize, ApiCallback callback) {
        AjaxParams params = ac.getCAjaxParams();
        params.put("client_key", ac.getClientKey());
        params.put("memberid", memberid + "");
        params.put("roletype", roletype + "");
        params.put("pageNo", pageNo + "");
        params.put("pageSize", pageSize + "");
        get(callback, Constant.MEMBER_MESSAGE_PAGEORDERMESSAGES, params, MemberMessagePageOrderMessagesBean.class,
                getMethodName(Thread.currentThread().getStackTrace()));
    }

    /**
     * 11.3 预约消息列表
     *
     * @param memberid
     * @param roletype
     * @param pageNo
     * @param pageSize
     * @return
     * @throws Exception
     */
    public MemberMessagePageOrderMessagesBean getMemberMessagePageOrderMessages(String memberid, String roletype, int pageNo, int pageSize) throws Exception {
        AjaxParams params = ac.getCAjaxParams();
        params.put("client_key", ac.getClientKey());
        params.put("memberid", memberid + "");
        params.put("roletype", roletype + "");
        params.put("pageNo", pageNo + "");
        params.put("pageSize", pageSize + "");
        return (MemberMessagePageOrderMessagesBean) getSync(Constant.MEMBER_MESSAGE_PAGEORDERMESSAGES, params, MemberMessagePageOrderMessagesBean.class);
    }

    /**
     * 11.4个人中心-消息-消息内的消息数
     * @param roletype
     * @param callback
     */
    public void messageCountInnerMessage(String roletype, ApiCallback callback){
        AjaxParams params = ac.getCAjaxParams();
        params.put("roletype", roletype);
        get(callback, Constant.MEMBER_COUNINNERMESSAGE, params, MessageCountInnerMessageBean.class,
                getMethodName(Thread.currentThread().getStackTrace()));
    }

    /**
     * 12.1 收藏载体
     *
     * @param carrierid
     * @param callback
     */
    public void memberFavoriteCarrier(String carrierid, ApiCallback callback) {
        AjaxParams params = ac.getCAjaxParams();
        params.put("carrierid", carrierid);
        post(callback, Constant.MEMBER_FAVORITE_CARRIER, params, MemberFavoriteCarrierBean.class,
                getMethodName(Thread.currentThread().getStackTrace()));
    }

    /**
     * 12.2 收藏服务机构
     *
     * @param memberid
     * @param agencyid
     * @param callback
     */
    public void memberFavoriteAgency(String memberid, String agencyid, ApiCallback callback) {
        AjaxParams params = ac.getCAjaxParams();
        params.put("client_key", ac.getClientKey());
        params.put("memberid", memberid);
        params.put("agencyid", agencyid + "");
        post(callback, Constant.MEMBER_FAVORITE_AGENCY, params, MemberFavoriteAgencyBean.class,
                getMethodName(Thread.currentThread().getStackTrace()));
    }

    /**
     * 12.3 取消收藏载体
     *
     * @param favoriteid
     * @param callback
     */
    public void cancelCollect(String favoriteid, ApiCallback callback) {
        AjaxParams params = ac.getCAjaxParams();
        params.put("favoriteid", favoriteid + "");
        post(callback, Constant.MEMBER_FAVORITE_CANCELCARRIER, params, NetResult.class,
                getMethodName(Thread.currentThread().getStackTrace()));
    }

    /**
     * 12.4 取消收藏服务机构
     *
     * @param memberid
     * @param favoriteid
     * @param callback
     */
    public void memberFavoriteCancelAgency(String memberid, String favoriteid, ApiCallback callback) {
        AjaxParams params = ac.getCAjaxParams();
        params.put("client_key", ac.getClientKey());
        params.put("memberid", memberid + "");
        params.put("favoriteid", favoriteid + "");
        post(callback, Constant.MEMBER_FAVORITE_CANCELAGENCY, params, NetResult.class,
                getMethodName(Thread.currentThread().getStackTrace()));
    }

    /**
     * 12.5 批量取消收藏载体
     *
     * @param favoriteids
     * @param callback
     */
    public void memberFavoriteBulkCancelCarrier(String favoriteids, ApiCallback callback) {
        AjaxParams params = ac.getCAjaxParams();
        params.put("favoriteids", favoriteids);
        post(callback, Constant.MEMBER_FAVORITE_BULKCANCELCARRIER, params, NetResult.class,
                getMethodName(Thread.currentThread().getStackTrace()));
    }

    /**
     * 12.6 批量取消收藏服务机构
     *
     * @param favoriteids
     * @param callback
     */
    public void memberFavoriteBulkCancelAgency(String favoriteids, ApiCallback callback) {
        AjaxParams params = ac.getCAjaxParams();
        params.put("favoriteids", favoriteids);
        post(callback, Constant.MEMBER_FAVORITE_BULKCANCELAGENCY, params, NetResult.class,
                getMethodName(Thread.currentThread().getStackTrace()));
    }

    /**
     * 12.7 载体收藏列表
     *
     * @param memberid
     * @param pageNo
     * @param pageSize
     * @param callback
     */
    public void memberFavoritePageCarrier(int memberid, int pageNo, int pageSize, ApiCallback callback) {
        AjaxParams params = ac.getCAjaxParams();
        params.put("client_key", ac.getClientKey());
        params.put("memberid", memberid + "");
        params.put("pageNo", pageNo + "");
        params.put("pageSize", pageSize + "");
        get(callback, Constant.MEMBER_FAVORITE_PAGECARRIER, params, MemberFavoritePageCarrierBean.class,
                getMethodName(Thread.currentThread().getStackTrace()));
    }

    /**
     * 12.7 载体收藏列表
     *
     * @param pageNo
     * @param pageSize
     * @return
     * @throws Exception
     */
    public MemberFavoritePageCarrierBean getMemberFavoritePageCarrier(int pageNo, int pageSize) throws Exception {
        AjaxParams params = ac.getCAjaxParams();
        params.put("pageNo", pageNo + "");
        params.put("pageSize", pageSize + "");
        return (MemberFavoritePageCarrierBean) getSync(Constant.MEMBER_FAVORITE_PAGECARRIER, params, MemberFavoritePageCarrierBean.class);
    }

    /**
     * 12.8 服务机构收藏列表
     *
     * @param memberid
     * @param pageNo
     * @param pageSize
     * @param callback
     */
    public void memberFavoritePageAgency(int memberid, int pageNo, int pageSize, ApiCallback callback) {
        AjaxParams params = ac.getCAjaxParams();
        params.put("client_key", ac.getClientKey());
        params.put("memberid", memberid + "");
        params.put("pageNo", pageNo + "");
        params.put("pageSize", pageSize + "");
        get(callback, Constant.MEMBER_FAVORITE_PAGEAGENCY, params, MemberFavoritePageAgencyBean.class,
                getMethodName(Thread.currentThread().getStackTrace()));
    }

    /**
     * 12.8 服务机构收藏列表
     *
     * @param pageNo
     * @param pageSize
     * @return
     * @throws Exception
     */
    public MemberFavoritePageAgencyBean getMemberFavoritePageAgency(int pageNo, int pageSize) throws Exception {
        AjaxParams params = ac.getCAjaxParams();
        params.put("pageNo", pageNo + "");
        params.put("pageSize", pageSize + "");
        return (MemberFavoritePageAgencyBean) getSync(Constant.MEMBER_FAVORITE_PAGEAGENCY, params, MemberFavoritePageAgencyBean.class);
    }

    /**
     * 13.1 获取发布城市列表
     */
    public BusinessLocationCityReleaseListBean getCityList() throws Exception {
        AjaxParams params = new AjaxParams();
        params.put("client_key", ac.getClientKey());
        return (BusinessLocationCityReleaseListBean) getSync(Constant.BUSINESS_LOCATION_CITY_RELEASELIST, params, BusinessLocationCityReleaseListBean.class);
    }

    /**
     * 13.1 获取发布城市列表
     */
    public void getCityList(ApiCallback callback) {
        AjaxParams params = new AjaxParams();
        params.put("client_key", ac.getClientKey());
        get(callback, Constant.BUSINESS_LOCATION_CITY_RELEASELIST, params, BusinessLocationCityReleaseListBean.class, getMethodName(Thread.currentThread().getStackTrace()));
    }


    /**
     * 13.2 根据城市获取地区列表
     *
     * @param id
     * @return
     * @throws Exception
     */
    public BusinessLocationCountyListBean getCountyList(String id) throws Exception {
        AjaxParams params = ac.getCAjaxParams();
        params.put("client_key", ac.getClientKey());
        params.put("cityid", id);
        return (BusinessLocationCountyListBean) getSync(Constant.BUSINESS_LOCATION_COUNTY_LIST, params, BusinessLocationCountyListBean.class);
    }

    /**
     * 13.2 根据城市获取地区列表
     *
     * @param cityid
     * @return
     * @throws Exception
     */
    public void getCountyList(String cityid, ApiCallback callback) {
        AjaxParams params = ac.getCAjaxParams();
        params.put("client_key", ac.getClientKey());
        params.put("cityid", cityid);
        get(callback, Constant.BUSINESS_LOCATION_COUNTY_LIST, params, BusinessLocationCountyListBean.class, getMethodName(Thread.currentThread().getStackTrace()));
    }

    /**
     * 13.3 载体行业列表
     *
     * @return
     * @throws Exception
     */
    public BusinessCarrierIndustryListBean countyList() throws Exception {
        AjaxParams params = ac.getCAjaxParams();
        params.put("client_key", ac.getClientKey());
        return (BusinessCarrierIndustryListBean) getSync(Constant.BUSINESS_CARRIER_INDUSTRY_LIST, params, BusinessCarrierIndustryListBean.class);
    }

    /**
     * 13.6 载体类型列表
     *
     * @return
     * @throws Exception
     */
    public BusinessInfrasCarrierTypeBean carrierTypeList() throws Exception {
        AjaxParams params = ac.getCAjaxParams();
        params.put("client_key", ac.getClientKey());
        return (BusinessInfrasCarrierTypeBean) getSync(Constant.BUSINESS_INFRAS_CARRIERTYPE, params, BusinessInfrasCarrierTypeBean.class);
    }


    /**
     * 13.6 载体类型列表
     *
     * @return
     * @throws Exception
     */
    public void carrierTypeList(ApiCallback callback) {
        AjaxParams params = ac.getCAjaxParams();
        params.put("client_key", ac.getClientKey());
        get(callback, Constant.BUSINESS_INFRAS_CARRIERTYPE, params, BusinessInfrasCarrierTypeBean.class, getMethodName(Thread.currentThread().getStackTrace()));
    }

    /**
     * 13.7 经济专区列表
     *
     * @return
     * @throws Exception
     */
    public BusinessInfrasListEezBean eezList() throws Exception {
        AjaxParams params = ac.getCAjaxParams();
        params.put("client_key", ac.getClientKey());
        return (BusinessInfrasListEezBean) getSync(Constant.BUSINESS_INFRAS_LISTEEZ, params, BusinessInfrasListEezBean.class);
    }

    /**
     * 13.8 经济专区下属城市列表
     *
     * @param eezid
     * @return
     * @throws Exception
     */
    public BusinessInfrasGetEezBean getEezList(int eezid) throws Exception {
        AjaxParams params = ac.getCAjaxParams();
        params.put("client_key", ac.getClientKey());
        params.put("eezid", eezid + "");
        return (BusinessInfrasGetEezBean) getSync(Constant.BUSINESS_INFRAS_GETEEZ, params, BusinessInfrasGetEezBean.class);
    }

    /**
     * 13.9 获取预约阶段列表
     *
     * @return
     * @throws Exception
     */
    public BusinessInfrasListOrderStageBean orderList() throws Exception {
        AjaxParams params = ac.getCAjaxParams();
        params.put("client_key", ac.getClientKey());
        return (BusinessInfrasListOrderStageBean) getSync(Constant.BUSINESS_INFRAS_LISTORDERSTAGE, params, BusinessInfrasListOrderStageBean.class);
    }

    /**
     * 13.9 获取预约阶段列表
     * @param callback
     * @throws Exception
     */
    public void getOrderList(ApiCallback callback) throws Exception {
        AjaxParams params = ac.getCAjaxParams();
        params.put("client_key", ac.getClientKey());
        get(callback,Constant.BUSINESS_INFRAS_LISTORDERSTAGE, params, BusinessInfrasListOrderStageBean.class,getMethodName(Thread.currentThread().getStackTrace()));
    }

    /**
     * 13.11获取 app 初始化信息
     *
     * @param callback
     */
    public void getInfrasShareapp(ApiCallback callback) {
        AjaxParams params = new AjaxParams();
        params.put("client_key", ac.getClientKey());
        get(callback, Constant.INFRAS_SHAREAPP, params, AppInfoBean.class, getMethodName(Thread.currentThread().getStackTrace()));
    }

    /**
     * 13.12获取所有省份列表
     *
     * @param callback
     */
    public void getprovinces(ApiCallback callback) {
        AjaxParams params = new AjaxParams();
        params.put("client_key", ac.getClientKey());
        get(callback, Constant.LOCATION_PROVINCES, params, BusinessLocationProvincesBean.class, getMethodName(Thread.currentThread().getStackTrace()));
    }

    /**
     * 13.12获取所有省份列表
     *
     * @param
     */
    public BusinessLocationProvincesBean getprovincesBean() throws Exception {
        AjaxParams params = new AjaxParams();
        params.put("client_key", ac.getClientKey());
        return (BusinessLocationProvincesBean) getSync(Constant.LOCATION_PROVINCES, params, BusinessLocationProvincesBean.class);
    }

    /**
     * 13.13获取省份下属城市列表
     *
     * @param callback
     */
    public void getprovincecitys(ApiCallback callback) {
        AjaxParams params = new AjaxParams();
        params.put("client_key", ac.getClientKey());
        get(callback, Constant.LOCATION_PROVINCECITYS, params, BusinessLocationProvinceCitysBean.class, getMethodName(Thread.currentThread().getStackTrace()));
    }

    /**
     * 13.13获取省份下属城市列表
     *
     * @param
     */
    public BusinessLocationProvinceCitysBean getprovincecitysBean(String provinceid) throws Exception {
        AjaxParams params = new AjaxParams();
        params.put("client_key", ac.getClientKey());
        params.put("provinceid", provinceid);
        return (BusinessLocationProvinceCitysBean) getSync(Constant.LOCATION_PROVINCECITYS, params, BusinessLocationProvinceCitysBean.class);
    }

    /**
     * 13.14获取城市下属地区列表
     *
     * @param
     */
    public BusinessLocationCityCountysBean getcityCountysBean(String cityid) throws Exception {
        AjaxParams params = new AjaxParams();
        params.put("client_key", ac.getClientKey());
        params.put("cityid", cityid);
        return (BusinessLocationCityCountysBean) getSync(Constant.LOCATION_CITYCOUNTYS, params, BusinessLocationCityCountysBean.class);
    }


    /**
     * 14.5 个人中心-注册-帐号验证
     *
     * @param username
     * @param callback
     * @throws Exception
     */
    public void memberAuthorizeVerifyAccount(String username, ApiCallback callback) throws Exception {
        AjaxParams params = ac.getCAjaxParams();
        params.put("client_key", ac.getClientKey());
        params.put("username", username);
        get(callback, Constant.MEMBER_AUTHORIZE_VERIFYACCOUNT, params, NetResult.class,
                getMethodName(Thread.currentThread().getStackTrace()));
    }

    /**
     * 14.6个人中心-登录-第三方帐号绑定
     *
     * @param username
     * @param code
     * @param channelid
     * @param authid
     * @param nickname
     * @param headurl
     * @param sourceType
     * @param callback
     * @throws Exception
     */
    public void memberAuthorizeAuthBind(String username, String code, String channelid, String authid, String nickname, String headurl,
                                        String sourceType, ApiCallback callback) throws Exception {
        AjaxParams params = ac.getCAjaxParams();
        params.put("client_key", ac.getClientKey());
        params.put("username", username);
        params.put("code", code);
        params.put("channelid", channelid);
        params.put("auth.authid", authid);
        params.put("auth.nickname", nickname);
        params.put("auth.headurl", headurl);
        params.put("auth.sourceType", sourceType);
        post(callback, Constant.MEMBER_AUTHORIZE_AUTHBIND, params, MemberAuthorizeAuthBindBean.class,
                getMethodName(Thread.currentThread().getStackTrace()));
    }

    /**
     * 14.7 个人中心-个人资料-详情（包括：服务达人、物业顾问）
     *
     * @param callback
     */
    public void memberMembershipInfoShow(String roletype, ApiCallback callback) {
        AjaxParams params = ac.getCAjaxParams();
        params.put("client_key", ac.getClientKey());
        params.put("roletype", roletype + "");
        get(callback, Constant.MEMBER_MEMBERSHIP_INFO_SHOW, params, MemberMembershipInfoShowBean.class,
                getMethodName(Thread.currentThread().getStackTrace()));
    }

    /**
     * 14.8个人中心-个人资料-编辑（包括：普通客商、服务达人、物业顾问）
     *
     * @param memberid
     * @param callback
     */
    public void memberMembershipInfoUpdate(int memberid, String nickname, String sex, String provinceid, String cityid, String countyid, String roletype,
                                           File portrait, String declaration, String position, String industryExper, ApiCallback callback) throws FileNotFoundException {
//        AjaxParams params = ac.getCAjaxParams();
        AjaxParams params = new AjaxParams();
//        params.setHasFile(true);
        params.put("client_key", ac.getClientKey());
        params.put("member.id", memberid+"");
        params.put("member.nickname", nickname);
        params.put("member.sex", sex);
        params.put("member.provinceid", provinceid);
        params.put("member.cityid", cityid);
        params.put("member.countyid", countyid);
        params.put("member.roletype", roletype);
//        if (portrait != null) {
//            params.setHasFile(false);
//            params.put("portrait", portrait);
//        }else{
//            params.setHasFile(true);
//        }
        System.out.println("portrait::::::::::"+portrait);

        if(portrait!=null){
            params.setHasFile(true);
            params.put("portrait", portrait);
        }else{
            params.setHasFile(false);
        }
        params.put("counselor.declaration", declaration);
        params.put("agencyexpert.position", position);
        params.put("agencyexpert.industryExper", industryExper);
        post(callback, Constant.MEMBER_MEMBERSHIP_INFO_UPDATE, params, UpdateUserBean.class,
                getMethodName(Thread.currentThread().getStackTrace()));
    }


    /**
     * 14.9个人中心-个人资料-找回密码
     * @param memberid
     * @param callback
     */

    /**
     * 14.10个人中心-个人资料-修改密码
     *
     * @param memberid
     * @param mobilephone
     * @param codesClassify
     * @param code
     * @param password
     */
    public void memberMembershipInfoUpdatePassword(int memberid, String mobilephone, int codesClassify, String code, String password, ApiCallback callback) {
        AjaxParams params = ac.getCAjaxParams();
        params.put("client_key", ac.getClientKey());
        params.put("memberid", memberid + "");
        params.put("mobilephone", mobilephone);
        params.put("codesClassify", codesClassify + "");
        params.put("code", code);
        params.put("password", Md5Utils.md5(password));
        post(callback, Constant.MEMBER_MEMBERSHIP_INFO_UPDATEPASSWORD, params, NetResult.class,
                getMethodName(Thread.currentThread().getStackTrace()));
    }

    /**
     * 14.11个人中心-个人资料-更换手机
     *
     * @param memberid
     * @param callback
     */
    public void updatePhone(int memberid, String oldMobilephone, int codesClassify, String code, String newMobilephone, ApiCallback callback) {
        AjaxParams params = ac.getCAjaxParams();
        params.put("client_key", ac.getClientKey());
        params.put("memberid", memberid + "");
        params.put("oldMobilephone", oldMobilephone);
        params.put("codesClassify", codesClassify + "");
        params.put("code", code);
        params.put("newMobilephone", newMobilephone);
        post(callback, Constant.MEMBER_MEMBERSHIP_INFO_UPDATEPHONE, params, NetResult.class,
                getMethodName(Thread.currentThread().getStackTrace()));
    }


    /**
     * 14.12个人中心-个人资料-更改支付信息
     *
     * @param memberid
     * @param callback
     */
    public void updatePayinfo(int memberid, String mobilephone, int codesClassify, String code, String fullname, String bank, String bankcard, ApiCallback callback) {
        AjaxParams params = ac.getCAjaxParams();
        params.put("client_key", ac.getClientKey());
        params.put("memberid", memberid + "");
        params.put("mobilephone", mobilephone);
        params.put("codesClassify", codesClassify + "");
        params.put("code", code);
        params.put("role.fullname", fullname);
        params.put("role.bank", bank);
        params.put("role.bankcard", bankcard);
        post(callback, Constant.MEMBER_MEMBERSHIP_INFO_UPDATEPAYINFO, params, NetResult.class,
                getMethodName(Thread.currentThread().getStackTrace()));
    }


    /**
     * 14.13个人中心-帐号升级-提交申请
     *
     * @param callback
     */
    public void memberMembershipInfoUpgrade(int memberid, String idcard, String fullname, int roletype, File idcardHand,
                                            File namecard,File idcardFront,File idcardBack,ApiCallback callback) throws FileNotFoundException {
        AjaxParams params = ac.getCAjaxParams();
        params.setHasFile(true);
        params.put("client_key", ac.getClientKey());
        params.put("memberid", memberid + "");
        params.put("idcard", idcard);
        params.put("fullname", fullname);
        params.put("roletype", roletype + "");
        if (idcardFront != null) {
            params.put("idcardFront", idcardFront);
        }
        if (idcardBack != null) {
            params.put("idcardBack", idcardBack);
        }

        if (idcardHand != null) {
            params.put("idcardHand", idcardHand);
        }
        if (namecard != null) {
            params.put("namecard", namecard);
        }
        post(callback, Constant.MEMBER_MEMBERSHIP_INFO_UPGRADE, params, NetResult.class, getMethodName(Thread.currentThread().getStackTrace()));
    }

    /**
     * 14.14个人中心-帮助中心-列表
     *
     * @param callback
     */
    public void getHelpList(int memberid, int pageNo, ApiCallback callback) {
        AjaxParams params = ac.getCAjaxParams();
        params.put("client_key", ac.getClientKey());
        params.put("memberid", memberid + "");
        params.put("pageNo", pageNo + "");
        get(callback, Constant.MEMBER_MEMBERSHIP_HELPCENTER_PAGE, params, HelpListBean.class, getMethodName(Thread.currentThread().getStackTrace()));
    }

    /**
     * 14.14个人中心-帮助中心-列表
     *
     * @param memberid
     * @param pageNo
     * @return
     * @throws Exception
     */
    public HelpListBean getHelpList(String memberid, String pageNo) throws Exception {
        AjaxParams params = ac.getCAjaxParams();
        params.put("client_key", ac.getClientKey());
        params.put("memberid", memberid);
        params.put("pageNo", pageNo);
        return (HelpListBean) getSync(Constant.MEMBER_MEMBERSHIP_HELPCENTER_PAGE, params, HelpListBean.class);
    }

    /**
     * 14.15个人中心-咨询记录-列表（服务达人、普通客商）
     *
     * @param callback
     */
    public void getConsultList(int memberid, int roletype, int pageNo, ApiCallback callback) {
        AjaxParams params = ac.getCAjaxParams();
        params.put("client_key", ac.getClientKey());
        params.put("memberid", memberid + "");
        params.put("roletype", roletype + "");
        params.put("pageNo", pageNo + "");
        get(callback, Constant.MEMBER_MEMBERSHIP_CONSULT_PAGE, params, ConsultListBean.class, getMethodName(Thread.currentThread().getStackTrace()));
    }

    /**
     * 14.15个人中心-咨询记录-列表（服务达人、普通客商）
     *
     * @param memberid
     * @param roletype
     * @param pageNo
     * @return
     * @throws Exception
     */
    public ConsultListBean getConsultList(String memberid, String roletype, int pageNo) throws Exception {
        AjaxParams params = ac.getCAjaxParams();
        params.put("client_key", ac.getClientKey());
        params.put("memberid", memberid + "");
        params.put("roletype", roletype + "");
        params.put("pageNo", pageNo + "");
        return (ConsultListBean) getSync(Constant.MEMBER_MEMBERSHIP_CONSULT_PAGE, params, ConsultListBean.class);
    }

    /**
     * 14.16个人中心-投诉反馈-类型列表
     */
    public FeedBackListBean getFeedbackList() throws Exception {
        AjaxParams params = ac.getCAjaxParams();
        params.put("client_key", ac.getClientKey());
        return (FeedBackListBean) getSync(Constant.MEMBER_MEMBERSHIP_FEEDBACK_LISTCLASSIFY, params, FeedBackListBean.class);
    }

    /**
     * 14.17个人中心-投诉反馈-提交反馈
     *
     * @param callback
     */
    public void CommitFeedback(String memberid, String classifyid, String feedback, String contactway, ApiCallback callback) {
        AjaxParams params = ac.getCAjaxParams();
        params.put("client_key", ac.getClientKey());
        params.put("memberid", memberid + "");
        params.put("classifyid", classifyid + "");
        params.put("feedback", feedback);
        params.put("contactway", contactway);
        post(callback, Constant.MEMBER_MEMBERSHIP_FEEDBACK_SAVE, params, NetResult.class, getMethodName(Thread.currentThread().getStackTrace()));
    }

    /**
     * 14.18个人中心-意见反馈-提交意见
     *
     * @param callback
     */
    public void memberMembershipOpinionsSave(String memberid, String opinions, ApiCallback callback) {
        AjaxParams params = ac.getCAjaxParams();
        params.put("client_key", ac.getClientKey());
        params.put("memberid", memberid);
        params.put("opinions", opinions);
        post(callback, Constant.MEMBER_MEMBERSHIP_OPINIONS_SAVE, params, NetResult.class, getMethodName(Thread.currentThread().getStackTrace()));
    }

    /**
     * 14.19个人中心-个人主页-物业顾问详情
     *
     * @param callback
     */
    public void memberMemberscreenShowCounselor(String memberid, ApiCallback callback) {
        AjaxParams params = new AjaxParams();
        params.put("client_key", ac.getClientKey());
        params.put("memberid", memberid);
        get(callback, Constant.MEMBER_MEMBERSCREEN_SHOWCOUNSELOR, params, ConsultantDetailBean.class, getMethodName(Thread.currentThread().getStackTrace()));
    }

    /**
     * 14.20个人中心-个人主页屏幕
     *
     * @param callback
     */
    public void memberMembershipInfoInfoscreen(String memberid, String roletype, ApiCallback callback) {
        AjaxParams params = ac.getCAjaxParams();
        params.put("client_key", ac.getClientKey());
        params.put("memberid", memberid + "");
        params.put("roletype", roletype + "");
        get(callback, Constant.MEMBER_MEMBERSHIP_INFO_INFOSCREEN, params, PersonalIndexBean.class, getMethodName(Thread.currentThread().getStackTrace()));
    }

    /**
     * 14.21 人中心-个人主页屏幕-切换帐号
     *
     * @param roletype
     * @param callback
     */
    public void memberMembershipInfoConvertRole(String roletype, ApiCallback callback) {
        AjaxParams params = ac.getCAjaxParams();
        params.put("roletype", roletype);
        post(callback, Constant.MEMBER_MEMBERSHIP_INFO_COVERTROLE, params, PersonalIndexBean.class, getMethodName(Thread.currentThread().getStackTrace()));
    }


    /**
     * 15.1个人中心-我的评论-物业顾问对载体
     *
     * @param callback
     */
    public void memberCommentsCounselorPageCarrier(int memberid, int roletype, int pageNo, ApiCallback callback) {
        AjaxParams params = ac.getCAjaxParams();
        params.put("client_key", ac.getClientKey());
        params.put("memberid", memberid + "");
        params.put("roletype", roletype + "");
        params.put("pageNo", pageNo + "");
        get(callback, Constant.MEMBER_COMMENTS_COUNSELOR_PAGECARRIER, params, CounselorPageCarrierBean.class, getMethodName(Thread.currentThread().getStackTrace()));
    }

    /**
     * 15.1个人中心-我的评论-物业顾问对载体
     *
     * @param memberid
     * @param pageNo
     * @return
     * @throws Exception
     */
    public CounselorPageCarrierBean getCounselorPageCarrier(String memberid, String roletype, int pageNo) throws Exception {
        AjaxParams params = ac.getCAjaxParams();
        params.put("client_key", ac.getClientKey());
        params.put("memberid", memberid + "");
        params.put("roletype", roletype + "");
        params.put("pageNo", pageNo + "");
        return (CounselorPageCarrierBean) getSync(Constant.MEMBER_COMMENTS_COUNSELOR_PAGECARRIER, params, CounselorPageCarrierBean.class);
    }




    /**
     * 15.2个人中心-我的评论-对我（物业顾问）的评论
     *
     * @param memberid
     * @param pageNo
     * @return
     * @throws Exception
     */
    public MemberCommentsCounselorPageMeBean getMemberCommentsCounselorPageMe(String memberid, int pageNo) throws Exception {
        AjaxParams params = ac.getCAjaxParams();
        params.put("client_key", ac.getClientKey());
        params.put("memberid", memberid + "");
        params.put("pageNo", pageNo + "");
        return (MemberCommentsCounselorPageMeBean) getSync(Constant.MEMBER_COMMENTS_COUNSELOR_PAGEME, params, MemberCommentsCounselorPageMeBean.class);
    }

    /**
     * 15.2个人中心-我的评论-对我（物业顾问）的评论
     *
     * @param memberid
     * @param pageNo
     * @return
     * @throws Exception
     */
    public void getMemberCommentsCounselorPageMe(String memberid, String pageNo, ApiCallback callback) {
        AjaxParams params = new AjaxParams();
        params.put("client_key", ac.getClientKey());
        params.put("memberid", memberid);
        params.put("pageNo", pageNo);
        get(callback, Constant.MEMBER_COMMENTS_COUNSELOR_PAGEME, params, MemberCommentsCounselorPageMeBean.class, getMethodName(Thread.currentThread().getStackTrace()));
    }

    /**
     * 15.3载体详情-物业顾问点评-提交点评
     *
     * @param callback
     */
    public void memberCommentsCounselorCommentCarrier(String carrierid, String content, ApiCallback callback) {
        AjaxParams params = ac.getCAjaxParams();
        params.put("carrierid", carrierid + "");
        params.put("content", content);
        post(callback, Constant.MEMBER_COMMENTS_COUNSELOR_COMMENTCARRIER, params, NetResult.class, getMethodName(Thread.currentThread().getStackTrace()));
    }

    /**
     * 15.4个人中心-我的评论-对我（服务达人）的评论
     *
     * @param callback
     */
    public void memberCommentsAgencyexpertPageMe(int memberid, int pageNo, ApiCallback callback) {
        AjaxParams params = ac.getCAjaxParams();
        params.put("client_key", ac.getClientKey());
        params.put("memberid", memberid + "");
        params.put("pageNo", pageNo + "");
        get(callback, Constant.MEMBER_COMMENTS_AGENCYEXPERT_PAGEME, params, MemberCommentsAgencyexpertPageMeBean.class, getMethodName(Thread.currentThread().getStackTrace()));
    }

    /**
     * 15.4个人中心-我的评论-对我（服务达人）的评论
     *
     * @param memberid
     * @param pageNo
     * @return
     * @throws Exception
     */
    public MemberCommentsAgencyexpertPageMeBean getMemberCommentsAgencyexpertPageMe(String memberid, int pageNo) throws Exception {
        AjaxParams params = ac.getCAjaxParams();
        params.put("client_key", ac.getClientKey());
        params.put("memberid", memberid + "");
        params.put("pageNo", pageNo + "");
        return (MemberCommentsAgencyexpertPageMeBean) getSync(Constant.MEMBER_COMMENTS_AGENCYEXPERT_PAGEME, params, MemberCommentsAgencyexpertPageMeBean.class);
    }

    /**
     * 15.5个人中心-我（普通客商）的评论-对物业顾问的评论
     *
     * @param callback
     */
    public void memberCommentsMemberPageMemberMeOrder(int memberid, int pageNo, ApiCallback callback) {
        AjaxParams params = ac.getCAjaxParams();
        params.put("client_key", ac.getClientKey());
        params.put("memberid", memberid + "");
        params.put("pageNo", pageNo + "");
        get(callback, Constant.MEMBER_COMMENTS_MEMBER_PAGEMEMBERMEORDER, params, MemberCommentsMemberPageMemberMeOrderBean.class, getMethodName(Thread.currentThread().getStackTrace()));
    }

    /**
     * 15.5个人中心-我（普通客商）的评论-对物业顾问的评论
     *
     * @param memberid
     * @param pageNo
     * @return
     * @throws Exception
     */
    public MemberCommentsMemberPageMemberMeOrderBean getmemberCommentsMemberPageMemberMeOrder(int memberid, int pageNo) throws Exception {
        AjaxParams params = ac.getCAjaxParams();
        params.put("client_key", ac.getClientKey());
        params.put("memberid", memberid + "");
        params.put("pageNo", pageNo + "");
        return (MemberCommentsMemberPageMemberMeOrderBean) getSync(Constant.MEMBER_COMMENTS_MEMBER_PAGEMEMBERMEORDER, params, MemberCommentsMemberPageMemberMeOrderBean.class);
    }

    /**
     * 15.6个人中心-我（普通客商）的评论-对物业顾问的评论-新增评论页
     *
     * @param callback
     */
    public void memberCommentsMemberAddMemberMeOrder(String orderid, ApiCallback callback) {
        AjaxParams params = ac.getCAjaxParams();
        params.put("client_key", ac.getClientKey());
        params.put("orderid", orderid + "");
        get(callback, Constant.MEMBER_COMMENTS_MEMBER_ADDMEMBERMEORDER, params, AddMemberMeOrderBean.class, getMethodName(Thread.currentThread().getStackTrace()));
    }


    /**
     * `15.7个人中心-我（普通客商）的评论-对物业顾问的评论-提交评论
     *
     * @param callback
     */
    public void commentToConsultant(String orderid, String memberid, String serverid, String content, int rate, int carrierid, String carrier_content, int carrier_rate, ApiCallback callback) {
        AjaxParams params = new AjaxParams();
        params.put("client_key", ac.getClientKey());
        params.put("orderid", orderid + "");
        params.put("memberid", memberid + "");
        params.put("counsoler.serverid", serverid + "");
        params.put("counsoler.content", content + "");
        params.put("counsoler.rate", rate + "");
        params.put("carrier.carrierid", carrierid + "");
        params.put("carrier.content", carrier_content + "");
        params.put("carrier.rate", carrier_rate + "");
        post(callback, Constant.MEMBER_COMMENTS_MEMBER_COMMENTMEMBERMEORDER, params, NetResult.class, getMethodName(Thread.currentThread().getStackTrace()));
    }


    /**
     * 15.8个人中心-我（普通客商）的评论-对服务达人的评论
     *
     * @param callback
     */
    public void commentToServer(int memberid, int pageNo, ApiCallback callback) {
        AjaxParams params = new AjaxParams();
        params.put("client_key", ac.getClientKey());
        params.put("memberid", memberid + "");
        params.put("pageNo", pageNo + "");
        get(callback, Constant.MEMBER_COMMENTS_MEMBER_PAGEMEMBERMEAGENCYEXPERT, params, CommentToServerBean.class, getMethodName(Thread.currentThread().getStackTrace()));
    }

    /**
     * 15.8个人中心-我（普通客商）的评论-对服务达人的评论
     *
     * @param memberid
     * @param pageNo
     * @return
     * @throws Exception
     */
    public CommentToServerBean getcommentToServer(int memberid, int pageNo) throws Exception {
        AjaxParams params = new AjaxParams();
        params.put("client_key", ac.getClientKey());
        params.put("memberid", memberid + "");
        params.put("pageNo", pageNo + "");
        return (CommentToServerBean) getSync(Constant.MEMBER_COMMENTS_MEMBER_PAGEMEMBERMEAGENCYEXPERT, params, CommentToServerBean.class);
    }

    /**
     * ` 15.9个人中心-我（普通客商）的评论-对服务达人的评论-新增评论页
     *
     * @param callback
     */
    public void addMemberMeAgencyexpert(String questionid, ApiCallback callback) {
        AjaxParams params = new AjaxParams();
        params.put("client_key", ac.getClientKey());
        params.put("questionid", questionid + "");
        get(callback, Constant.MEMBER_COMMENTS_MEMBER_ADDMEMBERMEAGENCYEXPERT, params, AddMemberMeAgencyexpertBean.class, getMethodName(Thread.currentThread().getStackTrace()));
    }

    /**
     * 15.10个人中心-我（普通客商）的评论-对服务达人的评论-提交评论
     *
     * @param callback
     */
    public void commentMemberMeAgencyexpert(String memberid, String questionid, String serverid, String content, int rate, ApiCallback callback) {
        AjaxParams params = new AjaxParams();
        params.put("client_key", ac.getClientKey());
        params.put("memberid", memberid + "");
        params.put("comments.questionid", questionid + "");
        params.put("comments.serverid", serverid + "");
        params.put("comments.content", content);
        params.put("comments.rate", rate + "");
        post(callback, Constant.MEMBER_COMMENTS_MEMBER_COMMENTMEMBERMEAGENCYEXPERT, params, NetResult.class, getMethodName(Thread.currentThread().getStackTrace()));
    }

    /**
     * 16.1选址-载体详情-一键预约页
     *
     * @param callback
     */
    public void memberOrderMemberShow(String carrierid, ApiCallback callback) {
        AjaxParams params = ac.getCAjaxParams();
        params.put("carrierid", carrierid);
        get(callback, Constant.MEMBER_ORDER_MEMBER_SHOW, params, MemberOrderMemberShowBean.class, getMethodName(Thread.currentThread().getStackTrace()));
    }

    /**
     * 16.2选址-载体详情-一键预约提交
     *
     * @param callback
     */
    public void memberOrderMemberApply(String memberid, String carrierid, String fullname, String counselorid, String contactPhone, String recomid, ApiCallback callback) {
        AjaxParams params = new AjaxParams();
        params.put("client_key", ac.getClientKey());
        params.put("order.memberid", memberid);
        params.put("order.carrierid", carrierid);
        params.put("order.fullname", fullname);
        if (counselorid != null) {
            params.put("counselorid", counselorid);
        }
        params.put("order.contactPhone", contactPhone);
        if (recomid != null) {
            params.put("order.recomid", recomid);
        }

        post(callback, Constant.MEMBER_ORDER_MEMBER_APPLY, params, NetResult.class, getMethodName(Thread.currentThread().getStackTrace()));
    }


    /**
     * 16.3个人中心-我（普通客商）的预约-全部的预约
     *
     * @param callback
     */
    public void memberOrderMemberPage(String memberid, String stage, int pageNo, int pageSize, ApiCallback callback) {
        AjaxParams params = new AjaxParams();
        params.put("client_key", ac.getClientKey());
        params.put("memberid", memberid);
        params.put("stage", stage);
        params.put("pageNo", pageNo + "");
        params.put("pageSize", pageSize + "");
        get(callback, Constant.MEMBER_ORDER_MEMBER_PAGE, params, MemberOrderMemberPageBean.class, getMethodName(Thread.currentThread().getStackTrace()));
    }

    /**
     * 16.3个人中心-我（普通客商）的预约-全部的预约
     *
     * @param memberid
     * @param pageNo
     * @param pageSize
     * @return
     * @throws Exception
     */
    public MemberOrderMemberPageBean getOrderMemberPage(String memberid, String stage, int pageNo, int pageSize) throws Exception {
        AjaxParams params = new AjaxParams();
        params.put("client_key", ac.getClientKey());
        params.put("memberid", memberid);
        params.put("stage", stage);
        params.put("pageNo", pageNo + "");
        params.put("pageSize", pageSize + "");
        return (MemberOrderMemberPageBean) getSync(Constant.MEMBER_ORDER_MEMBER_PAGE, params, MemberOrderMemberPageBean.class);
    }

    /**
     * 16.4个人中心-我（普通客商）的预约-今天的预约
     *
     * @param callback
     */
    public void memberOrderMemberPageNewest(String memberid, String stage, int pageNo, int pageSize, ApiCallback callback) {
        AjaxParams params = new AjaxParams();
        params.put("client_key", ac.getClientKey());
        params.put("memberid", memberid);
        params.put("stage", stage);
        params.put("pageNo", pageNo + "");
        params.put("pageSize", pageSize + "");
        get(callback, Constant.MEMBER_ORDER_MEMBER_PAGENEWEST, params, MemberOrderMemberPageNewestBean.class, getMethodName(Thread.currentThread().getStackTrace()));
    }

    /**
     * 16.4个人中心-我（普通客商）的预约-今天的预约
     *
     * @param memberid
     * @param pageNo
     * @param pageSize
     * @return
     * @throws Exception
     */
    public MemberOrderMemberPageNewestBean getOrderMemberPageNewest(String memberid, String stage, int pageNo, int pageSize) throws Exception {
        AjaxParams params = new AjaxParams();
        params.put("client_key", ac.getClientKey());
        params.put("memberid", memberid);
        params.put("stage", stage);
        params.put("pageNo", pageNo + "");
        params.put("pageSize", pageSize + "");
        return (MemberOrderMemberPageNewestBean) getSync(Constant.MEMBER_ORDER_MEMBER_PAGENEWEST, params, MemberOrderMemberPageNewestBean.class);
    }

    /**
     * 16.5个人中心-我（普通客商）的预约-取消预约
     *
     * @param callback
     */
    public void memberOrderMemberCancel(String orderid, ApiCallback callback) {
        AjaxParams params = new AjaxParams();
        params.put("client_key", ac.getClientKey());
        params.put("orderid", orderid + "");
        post(callback, Constant.MEMBER_ORDER_MEMBER_CANCEL, params, NetResult.class, getMethodName(Thread.currentThread().getStackTrace()));
    }

    /**
     * 16.6选址-载体详情-物业顾问详情-委托选址页
     *
     * @param callback
     */
    public void memberOrderMemberSitedetails(String memberid, String counselorid, ApiCallback callback) {
        AjaxParams params = new AjaxParams();
        params.put("client_key", ac.getClientKey());
        params.put("memberid", memberid + "");
        params.put("counselorid", counselorid + "");
        get(callback, Constant.MEMBER_ORDER_MEMBER_SITEDETAILS, params, MemberOrderMemberSitedetailsBean.class, getMethodName(Thread.currentThread().getStackTrace()));
    }


    /**
     * 16.7选址-载体详情-物业顾问详情-发送委托
     *
     * @param callback
     */
    public void memberOrderMemberSend(String memberid, String counselorid, String roletype, String remark, ApiCallback callback) {
        AjaxParams params = new AjaxParams();
        params.put("client_key", ac.getClientKey());
        params.put("memberid", memberid + "");
        params.put("counselorid", counselorid + "");
        params.put("roletype", roletype + "");
        params.put("remark", remark);
        post(callback, Constant.MEMBER_ORDER_MEMBER_SEND, params, MemberOrderMemberSendBeam.class, getMethodName(Thread.currentThread().getStackTrace()));
    }


    /**
     * 16.8个人中心-我(物业顾问)的订单（已签约、已完成）
     *
     * @param callback
     */
    public void memberOrderCounselorPageOrder(String memberid, String type, String pageNo, String pageSize, ApiCallback callback) {
        AjaxParams params = new AjaxParams();
        params.put("client_key", ac.getClientKey());
        params.put("memberid", memberid);
        params.put("type", type);
        params.put("pageNo", pageNo);
        params.put("pageSize", pageSize);
        get(callback, Constant.MEMBER_ORDER_COUNSELOR_PAGEORDER, params, MemberOrderCounselorPageOrderBean.class, getMethodName(Thread.currentThread().getStackTrace()));
    }

    /**
     * 16.8个人中心-我(物业顾问)的订单（已签约、已完成）
     *
     * @param memberid
     * @param type
     * @param pageNo
     * @param pageSize
     * @return
     * @throws Exception
     */
    public MemberOrderCounselorPageOrderBean getMemberOrderCounselorPageOrder(String memberid, String type, String pageNo, String pageSize) throws Exception {
        AjaxParams params = new AjaxParams();
        params.put("client_key", ac.getClientKey());
        params.put("memberid", memberid);
        params.put("type", type);
        params.put("pageNo", pageNo);
        params.put("pageSize", pageSize);
        return (MemberOrderCounselorPageOrderBean) getSync(Constant.MEMBER_ORDER_COUNSELOR_PAGEORDER, params, MemberOrderCounselorPageOrderBean.class);
    }


    /**
     * 16.9个人中心-我(物业顾问)的订单-查看合同
     *
     * @param callback
     */
    public void memberOrderCounselorListContract(String orderid, ApiCallback callback) {
        AjaxParams params = new AjaxParams();
        params.put("client_key", ac.getClientKey());
        params.put("orderid", orderid);
        get(callback, Constant.MEMBER_ORDER_COUNSELOR_LISTCONTRACT, params, MemberOrderCounselorListContractBean.class, getMethodName(Thread.currentThread().getStackTrace()));
    }

    /**
     * 16.10个人中心_我的订单(物业顾问)_上传合同文件
     *
     * @param callback
     */
    public void memberOrderCounselorUploadContract(String orderid, List<String> selectedPhotos, ApiCallback callback) throws FileNotFoundException {
        AjaxParams params = ac.getCAjaxParams();
        params.setHasFile(true);
        params.put("orderid", orderid);
        System.out.println(selectedPhotos.size());
        for (String s : selectedPhotos) {
            File file = new File(s);
            if (file.exists())
                params.put("contractfiles", file);
            Log.d("====上传合同的文件参数=====", "memberOrderCounselorUploadContract: " + file);
        }
        post(callback, Constant.MEMBER_ORDER_COUNSELOR_UPLOADCONTRACT, params, OrderCounselorUploadContractBean.class, getMethodName(Thread.currentThread().getStackTrace()));
    }


    /**
     * 16.11个人中心-我的订单(普通客商)-已签约
     *
     * @param callback
     */
    public void memberOrderMemberPageSignOrder(int pageNo, String memberid, ApiCallback callback) {
        AjaxParams params = new AjaxParams();
        params.put("client_key", ac.getClientKey());
        params.put("pageNo", pageNo + " ");
        params.put("memberid", memberid);
        get(callback, Constant.MEMBER_ORDER_MEMBER_PAGESIGNORDER, params, MemberOrderMemberPageSignOrderBean.class, getMethodName(Thread.currentThread().getStackTrace()));
    }

    /**
     * 16.11个人中心-我的订单(普通客商)-已签约
     *
     * @param pageNo
     * @param memberid
     * @return
     * @throws Exception
     */
    public MemberOrderMemberPageSignOrderBean getMemberOrderMemberPageSignOrder(int pageNo, String memberid) throws Exception {
        AjaxParams params = new AjaxParams();
        params.put("client_key", ac.getClientKey());
        params.put("pageNo", pageNo + " ");
        params.put("memberid", memberid);
        return (MemberOrderMemberPageSignOrderBean) getSync(Constant.MEMBER_ORDER_MEMBER_PAGESIGNORDER, params, MemberOrderMemberPageSignOrderBean.class);
    }


    /**
     * 16.12个人中心-我（物业顾问）的预约-全部的预约
     *
     * @param callback
     */
    public void memberOrderCounselorPage(String memberid, String stage, int pageNo, int pageSize, ApiCallback callback) {
        AjaxParams params = new AjaxParams();
        params.put("client_key", ac.getClientKey());
        params.put("memberid", memberid);
        params.put("stage", stage + "");
        params.put("pageNo", pageNo + " ");
        params.put("pageSize", pageSize + "");
        get(callback, Constant.MEMBER_ORDER_COUNSELOR_PAGE, params, MemberOrderCounselorPageBean.class, getMethodName(Thread.currentThread().getStackTrace()));
    }

    /**
     * 16.12个人中心-我（物业顾问）的预约-全部的预约
     *
     * @param memberid
     * @param pageNo
     * @param pageSize
     * @return
     * @throws Exception
     */
    public MemberOrderCounselorPageBean getMemberOrderCounselorPage(String memberid, String stage, int pageNo, int pageSize) throws Exception {
        AjaxParams params = new AjaxParams();
        params.put("client_key", ac.getClientKey());
        params.put("memberid", memberid);
        params.put("stage", stage);
        params.put("pageNo", pageNo + " ");
        params.put("pageSize", pageSize + "");
        return (MemberOrderCounselorPageBean) getSync(Constant.MEMBER_ORDER_COUNSELOR_PAGE, params, MemberOrderCounselorPageBean.class);
    }

    /**
     * 16.13个人中心-我（物业顾问）的预约-今天的预约
     *
     * @param callback
     */
    public void memberOrderCounselorPageNewest(String memberid, String stage, int pageNo, int pageSize, ApiCallback callback) {
        AjaxParams params = new AjaxParams();
        params.put("client_key", ac.getClientKey());
        params.put("memberid", memberid);
        params.put("stage", stage + "");
        params.put("pageNo", pageNo + "");
        params.put("pageSize", pageSize + "");
        get(callback, Constant.MEMBER_ORDER_COUNSELOR_PAGENEWEST, params, OrderCounselorPageNewestBean.class, getMethodName(Thread.currentThread().getStackTrace()));
    }

    /**
     * 16.13个人中心-我（物业顾问）的预约-今天的预约
     *
     * @param memberid
     * @param stage
     * @param pageNo
     * @param pageSize
     * @return
     * @throws Exception
     */
    public OrderCounselorPageNewestBean getOrderCounselorPageNewest(String memberid, String stage, int pageNo, int pageSize) throws Exception {
        AjaxParams params = new AjaxParams();
        params.put("client_key", ac.getClientKey());
        params.put("memberid", memberid);
        params.put("stage", stage);
        params.put("pageNo", pageNo + "");
        params.put("pageSize", pageSize + "");
        return (OrderCounselorPageNewestBean) getSync(Constant.MEMBER_ORDER_COUNSELOR_PAGENEWEST, params, OrderCounselorPageNewestBean.class);
    }


    /**
     * 16.14个人中心-我（物业顾问）的预约-修改预约信息
     *
     * @param orderid
     * @param callback
     */
    public void memberOrderCounselorUpdate(String orderid, String locat, String time, ApiCallback callback) {
        AjaxParams params = new AjaxParams();
        params.put("client_key", ac.getClientKey());
        params.put("orderid", orderid + "");
        params.put("locat", locat);
        params.put("time", time);
        post(callback, Constant.MEMBER_ORDER_COUNSELOR_UPDATE, params, NetResult.class, getMethodName(Thread.currentThread().getStackTrace()));
    }


    /**
     * 16.15个人中心-我（物业顾问）的预约-取消预约
     *
     * @param orderid
     * @param callback
     */
    public void memberOrderCounselorCancel(String orderid, ApiCallback callback) {
        AjaxParams params = new AjaxParams();
        params.put("client_key", ac.getClientKey());
        params.put("orderid", orderid);
        post(callback, Constant.MEMBER_ORDER_COUNSELOR_CANCEL, params, NetResult.class, getMethodName(Thread.currentThread().getStackTrace()));
    }


    /**
     * 16.16个人中心-我（物业顾问）的预约-修改预约阶段
     *
     * @param memberid
     * @param orderid
     * @param callback
     */
    public void memberOrderCounselorUpdateStage(String orderid, String memberid, ApiCallback callback) {
        AjaxParams params = new AjaxParams();
        params.put("client_key", ac.getClientKey());
        params.put("orderid", orderid + "");
        params.put("memberid", memberid + "");
        post(callback, Constant.MEMBER_ORDER_COUNSELOR_UPDATESTAGE, params, UpdateStageBean.class, getMethodName(Thread.currentThread().getStackTrace()));
    }


    /**
     * 16.17个人中心_我的订单(物业顾问)_新增订单合同信
     *
     * @param orderid
     * @param callback
     */
    public void memberOrderCounselorAddContracts(String orderid, String filesids, ApiCallback callback) {
        AjaxParams params = ac.getCAjaxParams();
        params.put("orderid", orderid);
        params.put("filesids", filesids);
        post(callback, Constant.MEMBER_ORDER_COUNSELOR_ADDCONTRACTS, params, NetResult.class, getMethodName(Thread.currentThread().getStackTrace()));
    }


    /**
     * 16.18个人中心_我的订单(物业顾问)_编辑订单合同信
     *
     * @param callback
     */
    public void memberOrderCounselorEditContracts(String orderid, String filesids, ApiCallback callback) {
        AjaxParams params = ac.getCAjaxParams();
        params.put("orderid", orderid);
        params.put("filesids", filesids);
        post(callback, Constant.MEMBER_ORDER_COUNSELOR_EDITCONTRACTS, params, NetResult.class, getMethodName(Thread.currentThread().getStackTrace()));
    }

    /**
     * 16.19 个人中心-我（普通客商）的预约-修改预约信息
     *
     * @param orderid
     * @param locat
     * @param time
     * @param callback
     * @throws Exception
     */
    public void memberOrderMemberUpdate(String orderid, String locat, String time, ApiCallback callback) throws Exception {
        AjaxParams params = new AjaxParams();
        params.put("client_key", ac.getClientKey());
        params.put("orderid", orderid);
        params.put("locat", locat);
        params.put("time", time);
        post(callback, Constant.MEMBER_ORDER_MEMBER_UPDATE, params, NetResult.class, getMethodName(Thread.currentThread().getStackTrace()));
    }

    /**
     * 17.1个人中心(物业顾问)-我的盘源
     *
     * @param memberid
     * @param pageNo
     * @param pageSize
     * @param callback
     */
    public void memberEntercarrierPageEnter(String memberid, int pageNo, int pageSize, ApiCallback callback) {
        AjaxParams params = new AjaxParams();
        params.put("client_key", ac.getClientKey());
        params.put("memberid", memberid);
        params.put("pageNo", pageNo + "");
        params.put("pageSize", pageSize + "");
        get(callback, Constant.MEMBER_ENTERCARRIER_PAGEENTER, params, EntercarrierPageEnterBean.class, getMethodName(Thread.currentThread().getStackTrace()));
    }

    /**
     * 17.1个人中心(物业顾问)-我的盘源
     *
     * @param memberid
     * @param pageNo
     * @param pageSize
     * @return
     * @throws Exception
     */
    public EntercarrierPageEnterBean getEntercarrierPageEnter(String memberid, int pageNo, int pageSize) throws Exception {
        AjaxParams params = new AjaxParams();
        params.put("client_key", ac.getClientKey());
        params.put("memberid", memberid);
        if (pageNo + "" != null) {
            params.put("pageNo", pageNo + "");
        }
        if (pageSize + "" != null) {
            params.put("pageSize", pageSize + "");
        }
        return (EntercarrierPageEnterBean) getSync(Constant.MEMBER_ENTERCARRIER_PAGEENTER, params, EntercarrierPageEnterBean.class);
    }

    /**
     * 17.1个人中心(物业顾问)-我的盘源
     *
     * @param memberid
     * @param pageNo
     * @param pageSize
     * @return
     * @throws Exception
     */
    public void getEntercarrierPageEnter(String memberid, String pageNo, String pageSize, ApiCallback callback) {
        AjaxParams params = new AjaxParams();
        params.put("client_key", ac.getClientKey());
        params.put("memberid", memberid);
        if (pageNo != null) {
            params.put("pageNo", pageNo);
        }
        if (pageSize != null) {
            params.put("pageSize", pageSize);
        }
        get(callback, Constant.MEMBER_ENTERCARRIER_PAGEENTER, params, EntercarrierPageEnterBean.class, getMethodName(Thread.currentThread().getStackTrace()));
    }

    /**
     * 17.2个人中心（业主）- 我的物业列表
     *
     * @param memberid
     * @param pageNo
     * @param pageSize
     */
    public EntercarrierPageOwnerCarrierBean memberEntercarrierPageOwnerCarrier(String memberid, int pageNo, int pageSize) throws Exception {
        AjaxParams params = new AjaxParams();
        params.put("client_key", ac.getClientKey());
        params.put("memberid", memberid);
        params.put("pageNo", pageNo + "");
        params.put("pageSize", pageSize + "");
        return (EntercarrierPageOwnerCarrierBean) getSync(Constant.MEMBER_ENTERCARRIER_PAGEOWNERCARRIER, params, EntercarrierPageOwnerCarrierBean.class);
    }


    /**
     * 17.3个人中心（业主）- 我的物业-租凭情况
     *
     * @param carrierid
     * @param pageNo
     * @param pageSize
     * @param callback
     */
    public void memberEntercarrierPageRents(String carrierid, int pageNo, int pageSize, ApiCallback callback) {
        AjaxParams params = new AjaxParams();
        params.put("client_key", ac.getClientKey());
        params.put("carrierid", carrierid);
        params.put("pageNo", pageNo + "");
        params.put("pageSize", pageSize + "");
        get(callback, Constant.MEMBER_ENTERCARRIER_PAGERENTS, params, EntercarrierPageRentsBean.class, getMethodName(Thread.currentThread().getStackTrace()));
    }

    /**
     * 17.3个人中心（业主）- 我的物业-租凭情况
     *
     * @param carrierid
     * @param pageNo
     * @param pageSize
     * @return
     * @throws Exception
     */
    public EntercarrierPageRentsBean getEntercarrierPageRents(String carrierid, int pageNo, int pageSize) throws Exception {
        AjaxParams params = new AjaxParams();
        params.put("client_key", ac.getClientKey());
        params.put("carrierid", carrierid);
        params.put("pageNo", pageNo + "");
        params.put("pageSize", pageSize + "");
        return (EntercarrierPageRentsBean) getSync(Constant.MEMBER_ENTERCARRIER_PAGERENTS, params, EntercarrierPageRentsBean.class);
    }


    /**
     * 17.4选址-写字楼详情-申请入驻
     *
     * @param carrierid
     * @param callback
     */
    public void memberEntercarrierShow(String carrierid, ApiCallback callback) {
        AjaxParams params = ac.getCAjaxParams();
        params.put("carrierid", carrierid);
        get(callback, Constant.MEMBER_ENTERCARRIER_SHOW, params, EntercarrierShowBean.class, getMethodName(Thread.currentThread().getStackTrace()));
    }

    /**
     * 17.5选址-写字楼详情-提交入驻申请
     *
     * @param memberid
     * @param carrierid
     * @param remark
     * @param callback
     */
    public void memberEntercarrierApply(String memberid, String carrierid, String remark, ApiCallback callback) {
        AjaxParams params = new AjaxParams();
        params.put("client_key", ac.getClientKey());
        params.put("enterApply.memberid", memberid);
        params.put("enterApply.carrierid", carrierid);
        if (remark != null) {
            params.put("enterApply.remark", remark);
        }
        post(callback, Constant.MEMBER_ENTERCARRIER_APPLY, params, EntercarrierApplyBean.class, getMethodName(Thread.currentThread().getStackTrace()));
    }


    /**
     * 18.1个人中心-我(客商中介)的推荐（交易中）
     *
     * @param memberid
     * @param type
     * @param pageNo
     * @param pageSize
     * @param callback
     */
    public void recomsharesPageTradingIn(String memberid, int type, int pageNo, int pageSize, ApiCallback callback) {
        AjaxParams params = ac.getCAjaxParams();
        params.put("memberid", memberid);
        params.put("type", type + "");
        params.put("pageNo", pageNo + "");
        params.put("pageSize", pageSize + "");
        get(callback, Constant.MEMBER_RECOMSHARES_PAGE, params, TradingInListBean.class, getMethodName(Thread.currentThread().getStackTrace()));
    }

    /**
     * 18.1个人中心-我(客商中介)的推荐（交易中）
     *
     * @param memberid
     * @param type
     * @param pageNo
     * @param pageSize
     * @return
     * @throws Exception
     */
    public TradingInListBean getRecomsharesPageTradingIn(String memberid, int type, int pageNo, int pageSize) throws Exception {
        AjaxParams params = ac.getCAjaxParams();
        params.put("memberid", memberid);
        params.put("type", type + "");
        params.put("pageNo", pageNo + "");
        params.put("pageSize", pageSize + "");
        return (TradingInListBean) getSync(Constant.MEMBER_RECOMSHARES_PAGE, params, TradingInListBean.class);
    }

    /**
     * 18.1个人中心-我(客商中介)的推荐（已成交）
     *
     * @param memberid
     * @param type
     * @param pageNo
     * @param pageSize
     * @param callback
     */
    public void recomsharesPage(String memberid, int type, int pageNo, int pageSize, ApiCallback callback) {
        AjaxParams params = ac.getCAjaxParams();
        params.put("memberid", memberid);
        params.put("type", type + "");
        params.put("pageNo", pageNo + "");
        params.put("pageSize", pageSize + "");
        get(callback, Constant.MEMBER_RECOMSHARES_PAGE, params, ClinchDealListBean.class, getMethodName(Thread.currentThread().getStackTrace()));
    }

    /**
     * 18.1个人中心-我(客商中介)的推荐（已成交）
     *
     * @param memberid
     * @param type
     * @param pageNo
     * @param pageSize
     * @return
     * @throws Exception
     */
    public ClinchDealListBean getRecomsharesPage(String memberid, int type, int pageNo, int pageSize) throws Exception {
        AjaxParams params = ac.getCAjaxParams();
        params.put("memberid", memberid);
        params.put("type", type + "");
        params.put("pageNo", pageNo + "");
        params.put("pageSize", pageSize + "");
        return (ClinchDealListBean) getSync(Constant.MEMBER_RECOMSHARES_PAGE, params, ClinchDealListBean.class);
    }


    /**
     * 19我的打赏
     *
     * @param memberid
     * @param pageNo
     * @param pageSize
     * @param callback
     */
    public void rewardPageOwnerCommission(String memberid, String roletype, int pageNo, int pageSize, ApiCallback callback) {
        AjaxParams params = new AjaxParams();
        params.put("client_key", ac.getClientKey());
        params.put("memberid", memberid + "");
        params.put("roletype", roletype + "");
        if (TextUtils.isEmpty(pageNo + "")) {
            params.put("pageNo", pageNo + "");
        }
        if (TextUtils.isEmpty(pageSize + "")) {
            params.put("pageSize", pageSize + "");
        }
        get(callback, Constant.MEMBER_REWARD_PAGEOWNERCOMMISSION, params, RewardPageOwnerCommissionBean.class, getMethodName(Thread.currentThread().getStackTrace()));
    }

    /**
     * 19我的打赏
     *
     * @param memberid
     * @param pageNo
     * @param pageSize
     * @return
     * @throws Exception
     */
    public RewardPageOwnerCommissionBean getRewardPageOwnerCommission(String memberid, String roletype, int pageNo, int pageSize) throws Exception {
        AjaxParams params = new AjaxParams();
        params.put("client_key", ac.getClientKey());
        params.put("memberid", memberid + "");
        params.put("roletype", roletype + "");
        params.put("pageNo", pageNo + "");
        params.put("pageSize", pageSize + "");
        return (RewardPageOwnerCommissionBean) getSync(Constant.MEMBER_REWARD_PAGEOWNERCOMMISSION, params, RewardPageOwnerCommissionBean.class);
    }


    public void getApp(ApiCallback callback) {
        AjaxParams params = new AjaxParams();
        get(callback, Constant.APP_URL, params, AppBean.class, getMethodName(Thread.currentThread().getStackTrace()));
    }


}
