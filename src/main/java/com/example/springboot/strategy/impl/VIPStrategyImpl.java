package com.example.springboot.strategy.impl;

import com.example.springboot.bean.RebateVo;
import com.example.springboot.strategy.IStrategyDemo;

import java.math.BigDecimal;

/**
 * @author linghongkang
 * @description:
 * @create: 2019-05-20 10:49
 **/
public class VIPStrategyImpl implements IStrategyDemo {
    @Override
    public BigDecimal calculateRebate(RebateVo obj) {
        if(obj.getVipGrade()==1){
            return new BigDecimal("0.05");
        }

        return null;
    }
}
