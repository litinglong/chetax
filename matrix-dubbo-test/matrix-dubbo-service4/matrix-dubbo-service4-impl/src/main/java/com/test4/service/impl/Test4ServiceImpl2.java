package com.test4.service.impl;

import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Service;

import com.test3.service.Test3Service;
import com.test4.service.Test4Service2;

@Service
public class Test4ServiceImpl2 implements Test4Service2 {

	@Reference(version = "1.0.0",group = "test",check = false)
    private Test3Service test3Service;

    public String sayName(String name) {
    	return test3Service.sayName(name);
    }
}
