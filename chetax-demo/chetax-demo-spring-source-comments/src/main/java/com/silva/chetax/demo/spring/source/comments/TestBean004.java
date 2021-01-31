package com.silva.chetax.demo.spring.source.comments;

import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import lombok.Data;

@Data
public class TestBean004 {
    private String name401;
    private String name402;
    private Map<String, String> map;
    private Set<String> set;
    private List<String> list;
    private Properties props;
    
}
