package com.ecommercewebsite.ecommercewebsite.designpatterns.factory;

public class EmailNotification implements NotificationService{
    @Override
    public void notifyUser()
    {
        System.out.println("Email Service");
    }
}
