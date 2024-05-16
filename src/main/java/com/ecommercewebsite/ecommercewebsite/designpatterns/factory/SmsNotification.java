package com.ecommercewebsite.ecommercewebsite.designpatterns.factory;

import org.springframework.stereotype.Component;

@Component
public class SmsNotification implements NotificationService {
    @Override
    public void notifyUser()
    {
        System.out.println("Sms Service");
    }
}
