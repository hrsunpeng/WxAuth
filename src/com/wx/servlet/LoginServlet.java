package com.wx.servlet;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wx.util.AuthUtil;

@WebServlet("/wxLogin")
public class LoginServlet extends HttpServlet {
	
	/**
	 * 微信公众号  调起授权登陆 
	 */ 
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//回 调地址 必须外网可以访问的路径
		String backUrl = "/WxAuth/callBack";
		//用户同意授权，获取code  拉取用户信息(需scope为 snsapi_userinfo)
		String url = "https://open.weixin.qq.com/connect/oauth2/authorize?appid="+AuthUtil.APPID
				+ "&redirect_uri="+URLEncoder.encode(backUrl)
				+ "&response_type=code"
				+ "&scope=snsapi_userinfo"
				+ "&state=STATE#wechat_redirect";
		resp.sendRedirect(url); 
	}

}
