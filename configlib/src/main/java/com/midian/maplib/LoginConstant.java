package com.midian.configlib;

import com.midian.maplib.ServerConstant;

/**
 * 注册登录url
 *
 * @author MIDIAN
 */
public class LoginConstant {
    /*切换域名实现如下：
   APP会员中心接口地址：http://121.40.133.108:8999/member/views/helpcenter?helpid=1
   指定域名后变更为如：http://app.member.pploc.com/views/aboutus

   APP业务中心接口地址：http://121.40.133.108:8999/business/views/aboutus
   指定域名后变更为如：http://app.business.pploc.com/views/aboutus*/


    public static final String MBASEURL = ServerConstant.MHOST;// PP选址
    public static final String BBASEURL = ServerConstant.BHOST;// PP选址
    /**
     * 14.1注册
     */
    public static final String MEMBER_AUTHORIZE_REGISTER = MBASEURL + "authorize/register";
    /**
     * 14.2登录
     */
    public static final String MEMBER_AUTHORIZE_LOGIN = MBASEURL + "authorize/login";
    /**
     * 13.4发送验证码
     */
    public static final String member_platform_codes_send = MBASEURL + "platform/codes/send";
    /**
     * 13.5验证验证码
     */
    public static final String member_platform_codes_verify = MBASEURL + "platform/codes/verify";
    /**
     * 14.5找回密码
     */
    public static final String member_membership_info_findPassword = MBASEURL + "membership/info/findPassword";
    /**
     * 修改密码
     */
    public static final String member_membership_info_updatePassword = MBASEURL + "membership/info/updatePassword";

    /**
     * 14.3第三方注册
     */
    public static final String AUTHORIZE_AUTHREGISTER = MBASEURL + "authorize/authRegister";

    /**
     * 14.6第三方账号绑定
     */
    public static final String AUTHORIZEAUTHBIND = MBASEURL + "authorize/authBind";
    /**
     * 14.4第三方登录
     */
    public static final String AUTHORIZE_AUTHLOGIN = MBASEURL + "authorize/authLogin";
    /**
     * 14.5第三方账号验证是否存在该账号
     */
    public static final String AUTHORIZE_VERIFYACCOUNT = MBASEURL + "authorize/verifyAccount";

    /**
     * 14.6个人中心-登录-第三方帐号绑定
     */
    public static final String MEMBER_AUTHORIZE_AUTHBIND = MBASEURL + "authorize/authBind";

}
