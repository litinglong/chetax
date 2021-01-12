package com.silva.chetax.demo.db.mysql.mapper;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.silva.chetax.demo.db.mysql.entity.ManageAssetCategory;

@SpringBootTest
public class ManageAssetCategoryMapperTest {

    @Autowired
    private ManageAssetCategoryMapper manageAssetCategoryMapper;

    @Test
    public void testSelect() {
        System.out.println(("----- selectAll method test ------"));
        List<ManageAssetCategory> userList = manageAssetCategoryMapper.selectList(null);
        userList.forEach(System.out::println);
        userList.stream().filter(predicate->predicate.getParentCategoryId().equals("0")).collect(Collectors.toList());
    }

}
