package com.ecommercewebsite.ecommercewebsite.designpatterns.strategy;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StrategyRunner implements CommandLineRunner {
    private final Context context;
    @Override
    public void run(String... args) throws Exception {
       context.setStrategy(new ConcreteStrategy1());
       context.execute();
       context.setStrategy(new ConcreteStrategy2());
       context.execute();
    }
}
