package com.ecommercewebsite.ecommercewebsite.designpatterns.facade;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FacadeRunner implements CommandLineRunner {

    private final Facade facade;
    @Override
    public void run(String... args) throws Exception {
        facade.playCricket();
        facade.playFootBall();
    }
}
