package com.silva.chetax.demo.spring.source.comments.controller;

import java.util.Date;
import java.util.List;

import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.silva.chetax.demo.spring.source.comments.entity.in.EntityReq;
import com.silva.chetax.demo.spring.source.comments.entity.out.EntityResp;

/**
 * <p>GET：无副作用，幂等，不可带 Request Body
<p>PUT：副作用，幂等，可以带 Request Body
<p>POST：副作用，非幂等，可以带 Request Body
<p>DELETE：副作用，幂等，不可带 Request Body

<p>consumes： 指定处理请求的提交内容类型（Content-Type），例如application/json,text/html; 

<p>produces： 指定返回的内容类型，仅当request请求头中的(Accept)类型中包含该指定类型才返回；
 * @author litinglong
 *
 */
@RestController
@RequestMapping("testController002") // 绑定url
public class TestController002 {

	@GetMapping("getMappingTest")
	public String getMappingTest() {
		return new Date().toString();
	}

	@PostMapping("getMappingTest")
	public String postMappingTest() {
		return new Date().toString();
	}

	@DeleteMapping("deleteMappingTest")
	public String deleteMappingTest() {
		return new Date().toString();
	}

	@PutMapping("putMappingTest")
	public String putMappingTest() {
		return new Date().toString();
	}

	@PatchMapping("patchMappingTest")
	public String patchMappingTest() {
		return new Date().toString();
	}

	@RequestMapping("commonEntityReqWithoutAnnotationTest")
	public String commonEntityReqWithoutAnnotationTest(EntityReq req) {
		return new Date().toString() + ":" + req;
	}
	
	@RequestMapping("pathVariableTest/{text}")
	public String pathVariableTest(@PathVariable("text") String text) {
		return new Date().toString() + ":" + text;
	}

	/**
	 * 绑定参数,将客户端请求中的参数值映射到相应方法的参数上;
	 * @param text
	 * @return
	 */
	@RequestMapping("requestParamTest1")
	public String requestParamTest1(@RequestParam("text") String text) {
		return new Date().toString() + ":" + text;
	}

	/**
	 * 多个同标识符的参数
	 * @param text
	 * @return
	 */
	@RequestMapping("requestParamTest2")
	public String requestParamTest2(@RequestParam("text") String[] text) {
		return new Date().toString() + ":" + text;
	}
	
	/**
	 * 多个同标识符的参数
	 * @param text
	 * @return
	 */
	@RequestMapping("requestParamTest3")
	public String requestParamTest2(@RequestParam("text") List<String> text) {
		return new Date().toString() + ":" + text;
	}

	@RequestMapping("requestBodyTest")
	public String requestBodyTest(@RequestBody EntityReq req) {
		return new Date().toString() + ":" + req;
	}

	@RequestMapping("requestHeaderTest")
	public String requestHeaderTest(@RequestHeader("User-Agent") String userAgent,
			@RequestHeader(value = "Accept") String[] accepts) {
		return new Date().toString() + ":" + userAgent + ":" + accepts.length;
	}
	
	@RequestMapping("cookieValuetest")
	public String cookieValuetest(@CookieValue(value="cookie_key", defaultValue="") String cookie_key) {
		return new Date().toString() + ":" + cookie_key;
	}
	
	@RequestMapping("responseBodyTest")
	@ResponseBody
	public EntityResp responseBodyTest() {
		EntityResp resp = new EntityResp();
		resp.setText(new Date().toString());
		resp.setTime(new Date());
		return resp;
	}
	
	@RequestMapping("entityRespTest")
	public EntityResp entityRespTest() {
		EntityResp resp = new EntityResp();
		resp.setText(new Date().toString());
		resp.setTime(new Date());
		return resp;
	}

}
