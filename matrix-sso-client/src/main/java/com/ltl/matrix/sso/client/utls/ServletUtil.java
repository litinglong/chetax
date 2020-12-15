package com.ltl.matrix.sso.client.utls;

import javax.servlet.http.HttpServletRequest;

public class ServletUtil {
	/**
	 * 获取服务的url基本地址
	 * @param request
	 * @return
	 */
	public static String getServerPath(HttpServletRequest request){
		String path = request.getContextPath();
		String basePath = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+ path + "/";
		
		return basePath;
	}
	/**
	 * 获取带目录的url地址
	 * @param request
	 * @param directory
	 * @return
	 */
	public static String getServerPath(HttpServletRequest request,String directory){
		String path = request.getContextPath();
		String basePath = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+ path + "/";
		return basePath+directory+"/";
	}
	/**
	 * 获取服务器的根路径
	 * @param request
	 * @param directory
	 * @return
	 */
	public static String getServerContextPath(HttpServletRequest request){
		String path = request.getContextPath();
		return path;
	}
}
