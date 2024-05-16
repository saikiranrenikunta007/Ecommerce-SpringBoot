package com.ecommercewebsite.ecommercewebsite.designpatterns.singleton;

import org.springframework.stereotype.Component;

@Component
public class Singleton {
    private static volatile Singleton obj = null;
    private Singleton(){}

    public static Singleton getInstance()
    {
        if(obj == null)
        {
            System.out.println("hie");
            synchronized(Singleton.class)
            {
                 if(obj == null)
                    obj = new Singleton();
            }
        }
        return obj;
    }

}
