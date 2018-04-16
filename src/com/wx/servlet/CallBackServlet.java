package com.wx.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wx.util.AuthUtil;

import net.sf.json.JSONObject;

@WebServlet("/WxAuth/callBack")
public class CallBackServlet extends HttpServlet{

	//微信授权登陆 回调 方法 
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		 //通过code换取网页授权access_token
		String code = req.getParameter("code");
		String url  = "https://api.weixin.qq.com/sns/oauth2/access_token?appid="+AuthUtil.APPID
				+ "&secret="+AuthUtil.APPSECRET
				+ "&code="+code
				+ "&grant_type=authorization_code"; 
		JSONObject json = AuthUtil.doGetJson(url);
		//公众号的唯一标识
		String appId = json.getString("appid");
		//网页授权接口调用凭证,注意：此access_token与基础支持的access_token不同
		String accessToken = json.getString("access_token");
		//拉取用户信息(需scope为 snsapi_userinfo)
		String getUserInfoUrl = "https://api.weixin.qq.com/sns/userinfo?access_token="+accessToken
				+ "&openid="+appId
				+ "&lang=zh_CN";
		
		JSONObject userInfoJson = AuthUtil.doGetJson(getUserInfoUrl);
		/**
		 * 
	      {     "openid":" OPENID",
				" nickname": NICKNAME,
				"sex":"1",
				"province":"PROVINCE"
				"city":"CITY",
				"country":"COUNTRY",
				"headimgurl":    "http://thirdwx.qlogo.cn/mmopen/g3MonUZtNHkdmzicIlibx6iaFqAc56vxLSUfpb6n5WKSYVY0ChQKkiaJSgQ1dZuTOgvLLrhJbERQQ4eMsv84eavHiaiceqxibJxCfHe/46",
				"privilege":[ "PRIVILEGE1" "PRIVILEGE2"     ],
				"unionid": "o6_bmasdasdsad6_2sgVt7hMZOPfL"
			}
		 */
		System.out.println("userInfoJson==>"+userInfoJson);
		super.doGet(req, resp);
	}
}
