package com.example.springboot.strategy;

import com.example.springboot.bean.RebateVo;
import com.example.springboot.strategy.impl.RebateStrategyFactory;

import java.math.BigDecimal;

/**
 * @author linghongkang
 * @description:
 * @create: 2019-05-20 10:52
 **/
public class StrategyContent {
    private IStrategyDemo strategyDemo;

    public StrategyContent() {
    }

    public StrategyContent(IStrategyDemo strategyDemo){
        this.strategyDemo=strategyDemo;
    }

    public BigDecimal countRebate(RebateVo obj){
        return strategyDemo.calculateRebate(obj);
    }

    public static void main(String[] args) {
        // 策略调用顺序 VIP > Activity
        StrategyContent strategyContent = new StrategyContent(RebateStrategyFactory.createInstant("VIP"));
        RebateVo rebateVo = new RebateVo();
        rebateVo.setVipGrade(1);
        rebateVo.setActivityId("10214");
        BigDecimal re = strategyContent.countRebate(rebateVo);
        System.out.println(re);
    }
}
