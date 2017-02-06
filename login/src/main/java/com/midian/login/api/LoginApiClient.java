package com.midian.login.api;

import com.midian.configlib.LoginConstant;
import com.midian.fastdevelop.afinal.http.AjaxParams;
import com.midian.login.bean.AuthLoginBean;
import com.midian.login.bean.AuthBindBean;
import com.midian.login.bean.AuthRegisterBean;
import com.midian.login.bean.LoginBean;
import com.midian.login.bean.MemberAuthorizeAuthBindBean;
import com.midian.login.bean.RegisterBean;

import java.io.File;
import java.io.FileNotFoundException;

import midian.baselib.api.ApiCallback;
import midian.baselib.api.BaseApiClient;
import midian.baselib.app.AppContext;
import midian.baselib.bean.NetResult;
import midian.baselib.utils.Md5Utils;

/**
 * 网络请求客户端基类
 *
 * @author moshouguan
 */
public class LoginApiClient extends BaseApiClient {

    public LoginApiClient(AppContext api) {
        super(api);
    }

    /**
     * 1.1.注册
     */
    public void register(String phone, String password, String code, String nickname, String channelid, int sex,
                         File portrait, ApiCallback callback) throws FileNotFoundException {

        AjaxParams params = new AjaxParams();
        params.setHasFile(true);
        params.put("client_key", ac.getClientKey());
        params.put("username", phone);
        params.put("password", Md5Utils.md5(password));
        params.put("code", code);
        params.put("nickname", nickname);
        params.put("channelid", ac.device_token);
        params.put("sex", sex + "");//0是女，1是男
        params.put("portrait", portrait);

        post(callback, LoginConstant.MEMBER_AUTHORIZE_REGISTER, params, RegisterBean.class,
                getMethodName(Thread.currentThread().getStackTrace()));
    }

    /**
     * 1.2登录
     */
    public void login(String username, String password, String channelid,
                      ApiCallback callback) {
        AjaxParams params = new AjaxParams();
        params.put("client_key", ac.getClientKey());
        params.put("username", username);
        params.put("password", Md5Utils.md5(password));
        params.put("channelid", channelid);
        post(callback, LoginConstant.MEMBER_AUTHORIZE_LOGIN, params, LoginBean.class,
                getMethodName(Thread.currentThread().getStackTrace()));
    }


    /**
     * 1.3发送验证码
     *
     * @param callback
     */
    public void sendCode(String mobilephone, int codesClassify, ApiCallback callback) {
        AjaxParams params = new AjaxParams();
        params.put("client_key", ac.getClientKey());
        params.put("mobilephone", mobilephone);
        params.put("codesClassify", codesClassify + "");
        post(callback, LoginConstant.member_platform_codes_send, params, NetResult.class,
                getMethodName(Thread.currentThread().getStackTrace()));
    }


    /**
     * 1.5验证验证码
     *
     * @param callback
     */
    public void validateCode(String mobilephone, int codesClassify, String code, ApiCallback callback) {
        AjaxParams params = new AjaxParams();
        params.put("client_key", ac.getClientKey());
        params.put("mobilephone", mobilephone);
        params.put("codesClassify", codesClassify + "");
        params.put("code", code);
        post(callback, LoginConstant.member_platform_codes_verify, params, NetResult.class,
                getMethodName(Thread.currentThread().getStackTrace()));
    }

    /**
     * 找回密码
     *
     * @param mobilephone
     * @param codesClassify
     * @param code
     * @param password
     * @param callback
     */
    public void getFindPwd(String mobilephone, int codesClassify, String code, String password, ApiCallback callback) {
        AjaxParams params = new AjaxParams();
        params.put("client_key", ac.getClientKey());
        params.put("mobilephone", mobilephone);
        params.put("codesClassify", codesClassify + "");
        params.put("code", code);
        params.put("password", Md5Utils.md5(password));
        post(callback, LoginConstant.member_membership_info_findPassword, params, NetResult.class,
                getMethodName(Thread.currentThread().getStackTrace()));
    }

    /**
     * 14.3第三方账号注册
     * @param phone
     * @param password
     * @param code
     * @param channelid
     * @param authid
     * @param nickname
     * @param headurl
     * @param sourceType
     * @param callback
     */
    public void authorizeAuthRegister(String phone, String password, String code, String channelid, String authid, String nickname, String headurl, String sourceType, ApiCallback callback) {
        AjaxParams params = new AjaxParams();
        params.put("client_key", ac.getClientKey());
        params.put("username", phone);
        params.put("password", password);
        params.put("code", code);
        params.put("channelid", channelid);
        params.put("auth.authid", authid);
        params.put("auth.nickname", nickname);
        params.put("auth.headurl", headurl);
        params.put("auth.sourceType", sourceType);
        post(callback, LoginConstant.AUTHORIZE_AUTHREGISTER, params, AuthRegisterBean.class, getMethodName(Thread.currentThread().getStackTrace()));
    }

    /**
     * 14.6第三方账号绑定
     *
     * @param phone
     * @param code
     * @param channelid
     * @param authid
     * @param nickname
     * @param headurl
     * @param sourceType
     * @param callback
     */
    public void authorizeAuthBind(String phone, String code, String channelid, String authid, String nickname, String headurl, String sourceType, ApiCallback callback) {
        AjaxParams params = new AjaxParams();
        params.put("client_key", ac.getClientKey());
        params.put("username", phone);
        params.put("code", code);
        params.put("channelid", channelid);
        params.put("auth.authid", authid);
        params.put("auth.nickname", nickname);
        params.put("auth.headurl", headurl);
        params.put("auth.sourceType", sourceType);
        post(callback, LoginConstant.AUTHORIZEAUTHBIND, params, AuthBindBean.class, getMethodName(Thread.currentThread().getStackTrace()));
    }

    /**
     * 14.4第三方登录
     *
     * @param channelid
     * @param authid
     * @param nickname
     * @param headurl
     * @param sourceType
     * @param callback
     */
    public void authLogin(String channelid, String authid, String nickname, String headurl, String sourceType, ApiCallback callback) {
        AjaxParams params = new AjaxParams();
        params.put("client_key", ac.getClientKey());
        params.put("channelid", channelid);
        params.put("auth.authid", authid);
        params.put("auth.nickname", nickname);
        params.put("auth.headurl", headurl);
        params.put("auth.sourceType", sourceType);
        post(callback, LoginConstant.AUTHORIZE_AUTHLOGIN, params, LoginBean.class, getMethodName(Thread.currentThread().getStackTrace()));
    }

    /**
     * 14.5账号验证(第三方绑定前)
     *
     * @param phone
     * @param callback
     */
    public void authorizeVerifyAccount(String phone, ApiCallback callback) {
        AjaxParams params = new AjaxParams();
        params.put("client_key", ac.getClientKey());
        params.put("username", phone);
        get(callback, LoginConstant.AUTHORIZE_VERIFYACCOUNT, params, NetResult.class, getMethodName(Thread.currentThread().getStackTrace()));
    }


    /**
     * 14.6个人中心-登录-第三方帐号绑定
     *
     * @param phone
     * @param code
     * @param channelid
     * @param authid
     * @param nickname
     * @param headurl
     * @param sourceType
     * @param callback
     * @throws Exception
     */
    public void memberAuthorizeAuthBind(String phone, String code, String channelid, String authid, String nickname, String headurl,
                                        String sourceType, ApiCallback callback) throws Exception {
        AjaxParams params = new AjaxParams();
        params.put("client_key", ac.getClientKey());
        params.put("username", phone);
        params.put("code", code);
        params.put("channelid", channelid);
        params.put("auth.authid", authid);
        params.put("auth.nickname", nickname);
        params.put("auth.headurl", headurl);
        params.put("auth.sourceType", sourceType);
        post(callback, LoginConstant.MEMBER_AUTHORIZE_AUTHBIND, params, MemberAuthorizeAuthBindBean.class,
                getMethodName(Thread.currentThread().getStackTrace()));
    }


    static public void init(AppContext appcontext) {
        if (appcontext == null)
            return;
        appcontext.api.addApiClient(new LoginApiClient(appcontext));
    }
}
