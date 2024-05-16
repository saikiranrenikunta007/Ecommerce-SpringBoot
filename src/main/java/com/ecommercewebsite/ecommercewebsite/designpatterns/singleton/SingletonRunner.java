package com.ecommercewebsite.ecommercewebsite.designpatterns.singleton;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class SingletonRunner implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {
        Singleton.getInstance();
        Singleton.getInstance();
    }
}
