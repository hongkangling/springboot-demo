package com.example.springboot.strategy;

import com.example.springboot.bean.RebateVo;

import java.math.BigDecimal;

/**
 * @author linghongkang
 * @description:
 * @create: 2019-05-20 10:43
 **/
public interface IStrategyDemo {

    /**
     * 计算返利比例
     * @param obj
     * @return
     */
    BigDecimal calculateRebate(RebateVo obj);
}
