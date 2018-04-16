package com.wx.util;
 
import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import net.sf.json.JSONObject;

public class AuthUtil {
   
	//微信公众平台  AppID
	public static final String APPID = "xxxxxxx";
	//微信公众平台  APPSECRET
	public static final String APPSECRET = "xxxxxxx";
	
	//发送远程请求 返回jsonObject 
	public static JSONObject doGetJson(String url) throws ClientProtocolException, IOException {
		JSONObject jsonObject = null;
		DefaultHttpClient client = new DefaultHttpClient(); 
		HttpGet httpGet = new HttpGet(url);
		HttpResponse response = client.execute(httpGet);
		HttpEntity httpEntity = response.getEntity(); 
		if(httpEntity!=null){
			String result = EntityUtils.toString(httpEntity,"UTF-8");
			jsonObject = JSONObject.fromObject(result);
		} 
		return jsonObject;

	}

}
