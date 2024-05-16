package com.ecommercewebsite.ecommercewebsite.designpatterns.facade;

import org.springframework.stereotype.Component;

@Component
public class FootBall implements  Sports{
    @Override
    public void play()
    {
        System.out.println("Football");
    }
}
