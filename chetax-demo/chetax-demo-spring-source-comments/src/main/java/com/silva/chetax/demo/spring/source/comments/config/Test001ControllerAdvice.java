package com.silva.chetax.demo.spring.source.comments.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;

import com.silva.chetax.demo.spring.source.comments.controller.TestController001;

/**
 * https://www.cnblogs.com/lenve/p/10748453.html
 * @see TestController001#test001ControllerAdvice
 * @author litinglong
 *
 */
@ControllerAdvice
public class Test001ControllerAdvice {
	
    /**
     * test001ControllerAdvice
     * 全局异常处理, 如果在controller方法遇到异常，就会调用含有此注解的方法。
     * @param e
     * @return
     */
    @ExceptionHandler(Exception.class)
    public ModelAndView customException(Exception e) {
        ModelAndView mv = new ModelAndView();
        mv.addObject("message", e.getMessage());
        mv.setViewName("myerror");
        return mv;
    }
	
    /**
     * test001ControllerAdvice
     * 全局数据绑定
     * @return
     */
    @ModelAttribute(name = "md")
    public Map<String,Object> mydata() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("age", 99);
        map.put("gender", "男");
        return map;
    }
    
    /**
     * test002ControllerAdvice
     * 全局数据预处理,注解表示该方法用来处理被@ModelAttribute("b")注解相关的参数,在方法中,给参数添加一个 b 前缀,即请求参数要有b前缀.
     * @param binder
     */
    @InitBinder("b")
    public void b(WebDataBinder binder) {
    	// WebDataBinder除了增加属性名称前缀外，还可以做其他处理，比如可以将所以form 讲所有传递进来的string 进行html编码，防止xss攻击，比如可以将字符串类型的日期转换成date类型
        binder.setFieldDefaultPrefix("b.");
        
    }
}
