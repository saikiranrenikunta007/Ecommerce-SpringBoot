package com.ecommercewebsite.ecommercewebsite.designpatterns.observer;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ObserverRunner implements CommandLineRunner {

    private final Subject s;
    @Override
    public void run(String... args) throws Exception {
        s.registerObserver(new ObserverImpl());
        s.registerObserver(new ObserverImpl());
        s.notifyUser();
    }
}
