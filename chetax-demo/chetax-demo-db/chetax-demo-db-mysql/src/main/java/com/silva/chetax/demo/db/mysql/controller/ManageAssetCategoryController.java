package com.silva.chetax.demo.db.mysql.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.silva.chetax.demo.db.mysql.entity.ResultBody;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

@Api(tags = "首页模块")
@RestController
public class ManageAssetCategoryController {

    @ApiImplicitParam(name = "name",value = "姓名",required = true)
    @ApiOperation(value = "向客人问好")
    @GetMapping("/sayHi")
    public ResponseEntity<String> sayHi(@RequestParam(value = "name")String name){
        return ResponseEntity.ok("Hi:"+name);
    }
    
    //TODO rest接口：生成资产目录编码
    @ApiImplicitParam(name = "id",value = "当前目录ID")
    @ApiOperation(value = "生成资产目录编码")
    @GetMapping("/generateManageAssetCategoryNo")
    public ResultBody<String> generateManageAssetCategoryNo(@RequestParam(value = "id")String id){
        return ResultBody.ok().data(id);
    }
    
    //TODO rest接口：新增资产目录
    @ApiImplicitParam(name = "id",value = "类目名称")
    @ApiOperation(value = "新增资产目录")
    @GetMapping("/insertManageAssetCategory")
    public ResultBody<String> insertManageAssetCategory(@RequestParam(value = "categoryName")String categoryName){
        return ResultBody.ok().data(categoryName);
    }
    
    //TODO rest接口：新增资产目录子节点
    @ApiImplicitParam(name = "id",value = "类目名称")
    @ApiOperation(value = "更新资产目录树")
    @GetMapping("/updateManageAssetCategorys")
    public ResultBody<String> updateManageAssetCategorys(@RequestParam(value = "categoryName")String categoryName){
        return ResultBody.ok().data(categoryName);
    }
    
    
    //TODO rest接口：修改资产目录信息（名称）
    
    
    
    
    //TODO rest接口：废弃资产目录
    
    //TODO rest接口：引用资产目录（在关系表中维护关联关系）
    
    //TODO rest接口：解除引用资产目录（在关系表中维护关联关系）
    
}
