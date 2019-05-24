package com.example.springboot.strategy.impl;

import com.example.springboot.bean.RebateVo;
import com.example.springboot.strategy.IStrategyDemo;

import java.math.BigDecimal;

/**
 * @author linghongkang
 * @description:
 * @create: 2019-05-20 11:07
 **/
public class ActivityStrategyImpl implements IStrategyDemo {
    @Override
    public BigDecimal calculateRebate(RebateVo obj) {
        if("10214".equals(obj.getActivityId())){
            return new BigDecimal("0.03");
        }
        return null;
    }
}
