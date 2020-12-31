package com.silva.gateway.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeRule;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeRuleManager;
/*熔断降级*/
@Component
public class GoodsQueryService {

    private static final String KEY = "queryGoodsInfo2";

    /**
     * 模拟商品查询接口
     *
     * @param spuId
     * @return
     */
    @SentinelResource(value = KEY, blockHandler = "blockHandlerMethod", fallback = "queryGoodsInfoFallback")
    public String queryGoodsInfo(String spuId) {

        // 模拟调用服务出现异常
        if ("0".equals(spuId)) {
            throw new RuntimeException();
        }

        return "query goodsinfo success, " + spuId;
    }

    public String blockHandlerMethod(String spuId, BlockException e) {
        System.out.println("queryGoodsInfo222 blockHandler"+e.toString());
        return "queryGoodsInfo error, blockHandlerMethod res: " + spuId;

    }

    public String queryGoodsInfoFallback(String spuId, Throwable e) {
    	System.out.println("queryGoodsInfo222 fallback"+e.toString());
        return "queryGoodsInfo error, return fallback res: " + spuId;
    }

    @PostConstruct
    public void initDegradeRule() {
        List<DegradeRule> rules = new ArrayList<>();
        DegradeRule rule = new DegradeRule();
        rule.setResource(KEY);
        // 80s内调用接口出现异常次数超过5的时候, 进行熔断
        rule.setCount(5);
        rule.setGrade(RuleConstant.DEGRADE_GRADE_EXCEPTION_COUNT);
        rule.setTimeWindow(80);
        rules.add(rule);
        DegradeRuleManager.loadRules(rules);
    }
}
