package com.midian.ppaddress.utils;

import com.midian.ppaddress.api.PpApiClient;
import com.midian.ppaddress.app.MAppContext;

import midian.baselib.app.AppContext;


/**
 * app工具
 * 
 * @author MIDIAN
 * 
 */
public class AppUtil {
	public static MAppContext getMAppContext(AppContext ac) {
		return (MAppContext) ac;
	}
	
	public static PpApiClient getPpApiClient(AppContext ac) {
		return ac.api.getApiClient(PpApiClient.class);
	}
}
