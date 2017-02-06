package com.midian.ppaddress.app;

import com.midian.maplib.ServerConstant;

public class Constant {
    /*切换域名实现如下：
  APP会员中心接口地址：http://121.40.133.108:8999/member/views/helpcenter?helpid=1
  指定域名后变更为如：http://app.member.pploc.com/views/aboutus

  APP业务中心接口地址：http://121.40.133.108:8999/business/views/aboutus
  指定域名后变更为如：http://app.business.pploc.com/views/aboutus*/
    public static final String SIZE = "10";
    public static final String MBASEURL = ServerConstant.MHOST;// PP选址
    public static final String BBASEURL = ServerConstant.BHOST;// PP选址

    /**
     * 7.1首页
     */
    public static final String BUSINESS_APP_INDEX = BBASEURL + "app/index";
    /**
     * 7.2地图筛选
     */
    public static final String BUSINESS_APPSEARCH_SOSCREEN = BBASEURL + "appsearch/soscreen";

    /**
     * 7.3地图搜索标签
     */
    public static final String BUSINESS_APPSEARCH_SOLABELS = BBASEURL + "appsearch/solabels";

    /**
     * 8.1选址--载体列表
     */
    public static final String BUSINESS_APPSEARCH_SOPAGE = BBASEURL + "appsearch/sopage";

    /**
     * 8.2选址--地图选址
     */
    public static final String BUSINESS_APPSEARCH_SOMAP = BBASEURL + "appsearch/somap";
    /**
     * 8.3选址-地图选址--覆盖物载体列表
     */
    public static final String business_appsearch_mapcarriers = BBASEURL + "appsearch/mapCarriers";

    /**
     * 8.4选址--载体详情
     */
    public static final String BUSINESS_CARRIER_DETAIL_SHOW = BBASEURL + "carrier/detail/show";
    /**
     * 8.5选址-载体详情--物业顾问列表
     */
    public static final String BUSINESS_CARRIER_DETAIL_PAGECOUNSELOR = BBASEURL + "carrier/detail/pageCounselor";
    /**
     * 8.6选址-载体详情-客商点评列表
     */
    public static final String BUSINESS_CARRIER_COMMENTS_PAGEMEMBERS = BBASEURL + "carrier/comments/pageMembers";
    /**
     * 8.7选址-载体详情--物业顾问点评列表
     */
    public static final String BUSINESS_CARRIER_COMMENTS_PAGECOUNSELORS = BBASEURL + "carrier/comments/pageCounselors";
    /**
     * 8.8选址--载体详情-专家点评列表
     */
    public static final String BUSINESS_CARRIER_COMMENTS_PAGEEXPERTS = BBASEURL + "carrier/comments/pageExperts";
    /**
     * 8.9选址-载体详情-点评列表-点赞功能
     */
    public static final String BUSINESS_CARRIER_COMMENTS_CLICKLIKE = BBASEURL + "carrier/comments/clickLike";
    /**
     * 8.10载体-载体详情-可选 单元 列表
     */
    public static final String BUSINESS_CARRIER_DETAIL_PAGEUNITS = BBASEURL + "carrier/detail/pageUnits";
    /**
     * 8.11载体-载体详情-基本参数
     */
    public static final String BUSINESS_CARRIER_DETAIL_QUERYPROPERTIES = BBASEURL + "carrier/detail/queryProperties";
    /**
     * 8.12载体-载体详情-载体选址列表
     */
    public static final String BUSINESS_CARRIER_DETAIL_GETCHILDRENCARRIERS = BBASEURL + "carrier/detail/getChildrenCarriers";
    /**
     * 8.13载体-载体详情-交通区位地图
     */
    public static final String BUSINESS_CARRIER_DETAIL_MAPTRAFFICS = BBASEURL + "carrier/detail/mapTraffics";

    /**
     * 9.1个人中心-我的对比--对比类型
     */
    public static final String MEMBER_COMPARE_LISTTYPE = MBASEURL + "compare/listType";
    /**
     * 9.2个人中心-我的对比-对比列表
     */
    public static final String MEMBER_COMPARE_LIST = MBASEURL + "compare/list";
    /**
     * 9.3个人中心-我的对比-批量删除对比
     */
    public static final String MEMBER_COMPARE_CLEAR = MBASEURL + "compare/clear";
    /**
     * 9.4个人中心-我的对比-添加对比
     */
    public static final String MEMBER_COMPARE_ADD = MBASEURL + "compare/add";
    /**
     * 9.5个人中心-我的对比-对比详情
     */
    public static final String MEMBER_COMPARE_DETAIL = MBASEURL + "compare/detail";

    /**
     * 10.1服务机构-主屏-机构行业
     */
    public static final String BUSINESS_AGENCY_SCREEN_LISTINDUSTRY = BBASEURL + "agency/screen/listIndustry";
    /**
     * 10.2服务机构-主屏-活动列表
     */
    public static final String BUSINESS_AGENCY_SCREEN_PAGEACTIVITY = BBASEURL + "agency/screen/pageActivity";
    /**
     * 10.3服务机构-主屏-服务机构列表
     */
    public static final String BUSINESS_AGENCY_SCREEN_PAGEAGENCY = BBASEURL + "agency/screen/pageAgency";
    /**
     * 10.4服务机构-主屏-一键申请
     */
    public static final String BUSINESS_AGENCY_SCREEN_ONEKEYAPPLY = BBASEURL + "agency/screen/onekeyApply";
    /**
     * 10.5服务机构-详细页-详情
     */
    public static final String BUSINESS_AGENCY_DETAIL_SHOWDETAIL = BBASEURL + "agency/detail/showDetail";
    /**
     * 10.6服务机构-详细页-评论列表
     */
    public static final String BUSINESS_AGENCY_DETAIL_PAGECOMMENTS = BBASEURL + "agency/detail/pageComments";
    /**
     * 10.7服务机构-详细页-服务达人列表
     */
    public static final String BUSINESS_AGENCY_DETAIL_LISTAGENCYEXPERT = BBASEURL + "agency/detail/listAgencyexpert";
    /**
     * 10.8服务机构-咨询-发起问题
     */
    public static final String BUSINESS_AGENCY_CONSULT_ASKQUESTION = BBASEURL + "agency/consult/askQuestion";

    /**
     * 10.9服务机构-咨询-会话-留言内容列表
     */
    public static final String BUSINESS_AGENCY_CONSULT_PAGERECORD = BBASEURL + "agency/consult/pageRecord";
    /**
     * 10.10服务机构-咨询-会话-问题
     */
    public static final String BUSINESS_AGENCY_CONSULT_SHOWQUESTION = BBASEURL + "agency/consult/showQuestion";

    /**
     * 10.11服务机构-咨询-关闭会话
     */
    public static final String BUSINESS_AGENCY_CONSULT_CLOSEQUESTION = BBASEURL + "agency/consult/closeQuestion";

    /**
     * 10.12服务机构-咨询-发送记录
     */
    public static final String BUSINESS_AGENCY_CONSULT_SENDRECORD = BBASEURL + "agency/consult/sendRecord";

    /**
     * 10.13服务机构-活动页-详情
     */
    public static final String BUSINESS_AGENCY_ACTIVITY_SHOW = BBASEURL + "agency/activity/show";

    /**
     * 10.14服务机构-活动页-活动申请
     */
    public static final String BUSINESS_AGENCY_ACTIVITY_APPLY = BBASEURL + "agency/activity/apply";

    /**
     * 11.1系统消息列表
     */
    public static final String MEMBER_MESSAGE_PAGESYSMESSAGES = MBASEURL + "message/pageSysMessages";
    /**
     * 11.2系统消息详情
     */
    public static final String MEMBER_MESSAGE_SHOWSYSMESSAGES = MBASEURL + "message/showSysMessages";
    /**
     * 11.3预约消息列表
     */
    public static final String MEMBER_MESSAGE_PAGEORDERMESSAGES = MBASEURL + "message/pageOrderMessages";
    /**
     * 11.4个人中心-消息-消息内的消息数
     */
    public static final String MEMBER_COUNINNERMESSAGE = MBASEURL + "message/countInnerMessage";
    /**
     * 12.1收藏载体
     */
    public static final String MEMBER_FAVORITE_CARRIER = MBASEURL + "favorite/carrier";
    /**
     * 12.2收藏服务机构
     */
    public static final String MEMBER_FAVORITE_AGENCY = MBASEURL + "favorite/agency";
    /**
     * 12.3取消收藏载体
     */
    public static final String MEMBER_FAVORITE_CANCELCARRIER = MBASEURL + "favorite/cancelCarrier";
    /**
     * 12.4取消收藏服务机构
     */
    public static final String MEMBER_FAVORITE_CANCELAGENCY = MBASEURL + "favorite/cancelAgency";
    /**
     * 12.5批量取消收藏载体
     */
    public static final String MEMBER_FAVORITE_BULKCANCELCARRIER = MBASEURL + "favorite/bulkCancelCarrier";
    /**
     * 12.6批量取消收藏服务机构
     */
    public static final String MEMBER_FAVORITE_BULKCANCELAGENCY = MBASEURL + "favorite/bulkCancelAgency";
    /**
     * 12.7载体收藏列表
     */
    public static final String MEMBER_FAVORITE_PAGECARRIER = MBASEURL + "favorite/pageCarrier";
    /**
     * 12.8服务机构收藏列表
     */
    public static final String MEMBER_FAVORITE_PAGEAGENCY = MBASEURL + "favorite/pageAgency";
    /**
     * 13.1获取发布城市列表
     */
    public static final String BUSINESS_LOCATION_CITY_RELEASELIST = BBASEURL + "location/city/releaseList";
    /**
     * 13.2根据城市获取地区列表
     */
    public static final String BUSINESS_LOCATION_COUNTY_LIST = BBASEURL + "location/county/list";
    /**
     * 13.3载体行业列表
     */
    public static final String BUSINESS_CARRIER_INDUSTRY_LIST = BBASEURL + "carrier/industry/list";

    /**
     * 13.4发送经验证码
     * 13.5验证验证码
     */
    /**
     * 13.6载体类型列表
     */
    public static final String BUSINESS_INFRAS_CARRIERTYPE = BBASEURL + "infras/carrierType";
    /**
     * 13.7经济专区列表
     */
    public static final String BUSINESS_INFRAS_LISTEEZ = BBASEURL + "infras/listEez";
    /**
     * 13.8经济专区下属城市列表
     */
    public static final String BUSINESS_INFRAS_GETEEZ = BBASEURL + "infras/listEez";
    /**
     * 13.9获取预约阶段列表
     */
    public static final String BUSINESS_INFRAS_LISTORDERSTAGE = BBASEURL + "infras/listOrderStage";
    /**
     * 13.11获取APP初始化信息
     */
    public static final String INFRAS_SHAREAPP = BBASEURL + "infras/shareapp";

    /**
     * 13.12取所有省份列表
     */
    public static final String LOCATION_PROVINCES = BBASEURL + "location/provinces";

    /**
     * 13.13获取省份下属城市列表
     */
    public static final String LOCATION_PROVINCECITYS = BBASEURL + "location/provinceCitys";

    /**
     * 13.14获取城市下属地区列表
     */
    public static final String LOCATION_CITYCOUNTYS = BBASEURL + "location/cityCountys";
    /**
     * 14.1注册
     * 14.2登陆
     */

    /**
     * 14.3个人中心-注册-第三方
     */
    public static final String MEMBER_AUTHORIZE_AUTHREGISTER = MBASEURL + "authorize/authRegister";

    /**
     * 14.4个人中心-登录-第三方
     */
    public static final String MEMBER_AUTHORIZE_AUTHLOGIN = MBASEURL + "authorize/authLogin";

    /**
     * 14.5个人中心-注册-帐号验证
     */
    public static final String MEMBER_AUTHORIZE_VERIFYACCOUNT = MBASEURL + "authorize/verifyAccount";

    /**
     * 14.6个人中心-登录-第三方帐号绑定
     */
    public static final String MEMBER_AUTHORIZE_AUTHBIND = MBASEURL + "authorize/authBind";

    /**
     * 14.7个人中心-个人资料-详情（包括：服务达人、物业顾问）
     */
    public static final String MEMBER_MEMBERSHIP_INFO_SHOW = MBASEURL + "membership/info/show";
    /**
     * 14.8个人中心-个人资料-编辑（包括：普通客商、服务达人、物业顾问）
     */
    public static final String MEMBER_MEMBERSHIP_INFO_UPDATE = MBASEURL + "membership/info/update";

    /**
     * 14.9找回密码
     */

    /**
     * 14.10修改密码
     */
    public static final String MEMBER_MEMBERSHIP_INFO_UPDATEPASSWORD = MBASEURL + "membership/info/updatePassword";
    /**
     * 14.11个人中心-个人资料-更换手机
     */
    public static final String MEMBER_MEMBERSHIP_INFO_UPDATEPHONE = MBASEURL + "membership/info/updatePhone";
    /**
     * 14.12个人中心-个人资料-更改支付信息
     */
    public static final String MEMBER_MEMBERSHIP_INFO_UPDATEPAYINFO = MBASEURL + "membership/info/updatePayinfo";
    /**
     * 14.13个人中心-账号升级-提交申请
     */
    public static final String MEMBER_MEMBERSHIP_INFO_UPGRADE = MBASEURL + "membership/info/upgrade";
    /**
     * 14.14个人中心-帮助中心-列表
     */
    public static final String MEMBER_MEMBERSHIP_HELPCENTER_PAGE = MBASEURL + "membership/helpcenter/page";
    /**
     * 14.15个人中心-咨询记录-列表（服务达人、普通客商）
     */
    public static final String MEMBER_MEMBERSHIP_CONSULT_PAGE = MBASEURL + "membership/consult/page";
    /**
     * 14.16个人中心-投诉反馈-类型列表
     */
    public static final String MEMBER_MEMBERSHIP_FEEDBACK_LISTCLASSIFY = MBASEURL + "membership/feedback/listClassify";
    /**
     * 14.17个人中心-投诉反馈-提交反馈
     */
    public static final String MEMBER_MEMBERSHIP_FEEDBACK_SAVE = MBASEURL + "membership/feedback/save";
    /**
     * 14.18个人中心-意见反馈-提交意见
     */
    public static final String MEMBER_MEMBERSHIP_OPINIONS_SAVE = MBASEURL + "membership/opinions/save";
    /**
     * 14.19个人中心-个人主页-物业顾问详情
     */
    public static final String MEMBER_MEMBERSCREEN_SHOWCOUNSELOR = MBASEURL + "memberscreen/showCounselor";
    /**
     * 14.20个人中心-个人主页屏幕
     */
    public static final String MEMBER_MEMBERSHIP_INFO_INFOSCREEN = MBASEURL + "membership/info/infoscreen";

    /**
     * 14.21个人中心-个人主页屏幕-切换帐号
     */
    public static final String MEMBER_MEMBERSHIP_INFO_COVERTROLE = MBASEURL + "membership/info/convertRole";
    /**
     * 15.1个人中心-我的评论-物业顾问对载体
     */
    public static final String MEMBER_COMMENTS_COUNSELOR_PAGECARRIER = MBASEURL + "comments/counselor/pageCarrier";
    /**
     * 15.2个人中心-我的评论-对我（物业顾问）的评论
     */
    public static final String MEMBER_COMMENTS_COUNSELOR_PAGEME = MBASEURL + "comments/counselor/pageMe";
    /**
     * 15.3载体详情-物业顾问点评-提交点评
     */
    public static final String MEMBER_COMMENTS_COUNSELOR_COMMENTCARRIER = MBASEURL + "comments/counselor/commentCarrier";
    /**
     * 15.4个人中心-我的评论-对我（服务达人）的评论
     */
    public static final String MEMBER_COMMENTS_AGENCYEXPERT_PAGEME = MBASEURL + "comments/agencyexpert/pageMe";
    /**
     * 15.5个人中心-我（普通客商）的评论-对物业顾问的评论
     */
    public static final String MEMBER_COMMENTS_MEMBER_PAGEMEMBERMEORDER = MBASEURL + "comments/member/pageMemberMeOrder";
    /**
     * 15.6个人中心-我（普通客商）的评论-对物业顾问的评论-新增评论页
     */
    public static final String MEMBER_COMMENTS_MEMBER_ADDMEMBERMEORDER = MBASEURL + "comments/member/addMemberMeOrder";
    /**
     * 15.7个人中心-我（普通客商）的评论-对物业顾问的评论-提交评论
     */
    public static final String MEMBER_COMMENTS_MEMBER_COMMENTMEMBERMEORDER = MBASEURL + "comments/member/commentMemberMeOrder";
    /**
     * 15.8个人中心-我（普通客商）的评论-对服务达人的评论
     */
    public static final String MEMBER_COMMENTS_MEMBER_PAGEMEMBERMEAGENCYEXPERT = MBASEURL + "comments/member/pageMemberMeAgencyexpert";
    /**
     * 15.9个人中心-我（普通客商）的评论-对服务达人的评论-新增评论页
     */
    public static final String MEMBER_COMMENTS_MEMBER_ADDMEMBERMEAGENCYEXPERT = MBASEURL + "comments/member/addMemberMeAgencyexpert";
    /**
     * 15.10个人中心-我（普通客商）的评论-对服务达人的评论-提交评论
     */
    public static final String MEMBER_COMMENTS_MEMBER_COMMENTMEMBERMEAGENCYEXPERT = MBASEURL + "comments/member/commentMemberMeAgencyexpert";
    /**
     * 16.1选址-载体详情-一键预约页
     */
    public static final String MEMBER_ORDER_MEMBER_SHOW = MBASEURL + "order/member/show";
    /**
     * 16.2选址-载体详情-一键预约提交
     */
    public static final String MEMBER_ORDER_MEMBER_APPLY = MBASEURL + "order/member/apply";
    /**
     * 16.3个人中心-我（普通客商）的预约-全部的预约
     */
    public static final String MEMBER_ORDER_MEMBER_PAGE = MBASEURL + "order/member/page";
    /**
     * 16.4个人中心-我（普通客商）的预约-今天的预约
     */
    public static final String MEMBER_ORDER_MEMBER_PAGENEWEST = MBASEURL + "order/member/pageNewest";

    /**
     * 16.5个人中心-我（普通客商）的预约-取消预约
     */
    public static final String MEMBER_ORDER_MEMBER_CANCEL = MBASEURL + "order/member/cancel";
    /**
     * 16.6选址-载体详情-物业顾问详情-委托选址页
     */
    public static final String MEMBER_ORDER_MEMBER_SITEDETAILS = MBASEURL + "order/member/sitedetails";
    /**
     * 16.7选址-载体详情-物业顾问详情-发送委托
     */
    public static final String MEMBER_ORDER_MEMBER_SEND = MBASEURL + "order/member/send";
    /**
     * 16.8个人中心-我(物业顾问)的订单（已签约、已完成）
     */
    public static final String MEMBER_ORDER_COUNSELOR_PAGEORDER = MBASEURL + "order/counselor/pageOrder";
    /**
     * 16.9个人中心-我(物业顾问)的订单-查看合同
     */
    public static final String MEMBER_ORDER_COUNSELOR_LISTCONTRACT = MBASEURL + "order/counselor/listContract";
    /**
     * 16.10个人中心-我的订单(物业顾问)_上传/编辑合同
     */
    public static final String MEMBER_ORDER_COUNSELOR_UPLOADCONTRACT = MBASEURL + "order/counselor/uploadfilesContract";
    /**
     * 16.11个人中心-我的订单(普通客商)-已签约
     */
    public static final String MEMBER_ORDER_MEMBER_PAGESIGNORDER = MBASEURL + "order/member/pageSignOrder";
    /**
     * 16.12个人中心-我（物业顾问）的预约-全部的预约
     */
    public static final String MEMBER_ORDER_COUNSELOR_PAGE = MBASEURL + "order/counselor/page";
    /**
     * 16.13个人中心-我（物业顾问）的预约-今天的预约
     */
    public static final String MEMBER_ORDER_COUNSELOR_PAGENEWEST = MBASEURL + "order/counselor/pageNewest";
    /**
     * 16.14个人中心-我（物业顾问）的预约-修改预约信息
     */
    public static final String MEMBER_ORDER_COUNSELOR_UPDATE = MBASEURL + "order/counselor/update";
    /**
     * 16.15个人中心-我（物业顾问）的预约-取消预约
     */
    public static final String MEMBER_ORDER_COUNSELOR_CANCEL = MBASEURL + "order/counselor/cancel";

    /**
     * 16.16个人中心-我（物业顾问）的预约-修改预约阶段
     */
    public static final String MEMBER_ORDER_COUNSELOR_UPDATESTAGE = MBASEURL + "order/counselor/updateStage";

    /**
     * 16.17个人中心_我的订单(物业顾问)_新增订单合同信息
     */
    public static final String MEMBER_ORDER_COUNSELOR_ADDCONTRACTS = MBASEURL + "order/counselor/addContracts";

    /**
     * 16.18个人中心_我的订单(物业顾问)_编辑订单合同信
     */
    public static final String MEMBER_ORDER_COUNSELOR_EDITCONTRACTS = MBASEURL + "order/counselor/editContracts";

    /**
     * 16.19个人中心-我（普通客商）的预约-修改预约信息
     */
    public static final String MEMBER_ORDER_MEMBER_UPDATE = MBASEURL + "order/member/update";
    /**
     * 17.1个人中心(物业顾问)-我的盘源
     */
    public static final String MEMBER_ENTERCARRIER_PAGEENTER = MBASEURL + "entercarrier/pageEnter";
    /**
     * 17.2个人中心（业主）- 我的物业列表
     */
    public static final String MEMBER_ENTERCARRIER_PAGEOWNERCARRIER = MBASEURL + "entercarrier/pageOwnerCarrier";
    /**
     * 17.3个人中心（业主）- 17.3我的物业-租凭情况
     */
    public static final String MEMBER_ENTERCARRIER_PAGERENTS = MBASEURL + "entercarrier/pageRents";

    /**
     * 17.4选址-写字楼详情-申请入驻
     */
    public static final String MEMBER_ENTERCARRIER_SHOW = MBASEURL + "entercarrier/show";
    /**
     * 17.5选址-写字楼详情-提交入驻申请
     */
    public static final String MEMBER_ENTERCARRIER_APPLY = MBASEURL + "entercarrier/apply";
    /**
     * 18.1个人中心-我(客商中介)的推荐
     */
    public static final String MEMBER_RECOMSHARES_PAGE = MBASEURL + "recomshares/page";
    /**
     * 19.1个人中心（业主）- 我的打赏
     */
    public static final String MEMBER_REWARD_PAGEOWNERCOMMISSION = MBASEURL + "reward/pageOwnerCommission";

    public static final String APP_URL = "http://121.40.97.52:8080/ControlProject/web/projectcontrolInfo";//http://121.40.97.52:8080/ControlProject/web/projectcontrolInfo?version=android1.0&pname=ppxz

}
