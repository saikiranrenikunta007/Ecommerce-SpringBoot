package com.ecommercewebsite.ecommercewebsite.designpatterns.observer;

public class ObserverImpl implements Observer{
    @Override
    public void update()
    {
        System.out.println("Observer notified");
    }
}
