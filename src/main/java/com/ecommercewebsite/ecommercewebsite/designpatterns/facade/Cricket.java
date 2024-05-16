package com.ecommercewebsite.ecommercewebsite.designpatterns.facade;

import org.springframework.stereotype.Component;

@Component
public class Cricket implements Sports{
    @Override
    public void play()
    {
        System.out.println("cricket");
    }
}
