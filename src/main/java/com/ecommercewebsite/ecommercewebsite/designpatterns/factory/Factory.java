package com.ecommercewebsite.ecommercewebsite.designpatterns.factory;

import lombok.*;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
public class Factory {


    public NotificationService createNotification(String channel)
    {
        if(channel == null || channel.isEmpty())
        {
            return null;
        }
        return switch (channel) {
            case "SMS" -> new SmsNotification();
            case "Email" -> new EmailNotification();
            default -> throw new IllegalArgumentException();
        };
    }

}
