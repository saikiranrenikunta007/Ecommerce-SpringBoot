package com.ecommercewebsite.ecommercewebsite.designpatterns.factory;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FactoryRunner implements CommandLineRunner {

    private final Factory factory;

    @Override
    public void run(String... args) throws Exception {

        factory.createNotification("SMS").notifyUser();
    }
}
