package com.silva.chetax.demo.db.mysql.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.silva.chetax.demo.db.mysql.entity.ResultBody;
import com.silva.chetax.demo.db.mysql.service.Test001Service;

@RestController
@RequestMapping("test")
public class TestCtl {
	@Autowired
	private Test001Service test001Service;
    //TODO rest接口：生成资产目录编码
    @GetMapping("/test1")
    public ResultBody<String> generateManageAssetCategoryNo(@RequestParam(value = "id")String id){
    	test001Service.test();
        return ResultBody.ok().data(id);
    }
    
    
}
