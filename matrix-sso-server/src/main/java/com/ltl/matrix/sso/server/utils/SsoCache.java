package com.ltl.matrix.sso.server.utils;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SsoCache {
	public static ConcurrentHashMap<String, HashSet<String>> tokenMapx = new ConcurrentHashMap<String, HashSet<String>>();
	public static Map<String, String> sessionTokenMap = new HashMap<>();
	
	@SuppressWarnings("serial")
	public static Map<String, String> userMap = new HashMap<String, String>(){{put("litinglong", "123456");}};
    // 用来验证token是否存在，key为token，value随便（不考虑并发的话可以使用HashSet）
    public static ConcurrentHashMap<String, String> tokenMap = new ConcurrentHashMap<>();
    // 用来存放sessionId和token的对应关系（这样可以在直接调用sso-server退出时，能够通过sessionId拿到token，从而让子系统也退出）
    // 用来进行系统退出，key为token，value为子系统退出url的集合
    public static ConcurrentHashMap<String, List<String>> tokenSystemUrlMap = new ConcurrentHashMap<String, List<String>>();

}
