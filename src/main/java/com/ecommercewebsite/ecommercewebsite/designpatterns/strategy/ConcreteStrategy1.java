package com.ecommercewebsite.ecommercewebsite.designpatterns.strategy;

import org.springframework.stereotype.Component;

public class ConcreteStrategy1 implements Strategy{
    @Override
    public void execute()
    {
        System.out.println("ConcreteStrategy1");
    }
}
