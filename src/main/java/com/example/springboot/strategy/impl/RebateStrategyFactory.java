package com.example.springboot.strategy.impl;

import com.example.springboot.strategy.IStrategyDemo;

/**
 * @author linghongkang
 * @description:
 * @create: 2019-05-20 11:13
 **/
public class RebateStrategyFactory {

    public static IStrategyDemo createInstant(String type) {
        switch (type) {
            case "VIP":
                return new VIPStrategyImpl();
            case "ACT":
                return new ActivityStrategyImpl();
            default:
                return null;
        }
    }
}
