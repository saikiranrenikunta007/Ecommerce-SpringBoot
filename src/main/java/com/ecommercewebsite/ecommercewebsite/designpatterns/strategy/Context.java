package com.ecommercewebsite.ecommercewebsite.designpatterns.strategy;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
@AllArgsConstructor
@Setter
public class Context {
    private Strategy strategy;
    public void execute()
    {
        strategy.execute();
    }

}
