package com.ecommercewebsite.ecommercewebsite.designpatterns.facade;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class Facade {

    private Sports cricket;
    private Sports footBall;
    void playCricket()
    {
        cricket.play();
    }
    void playFootBall()
    {
        footBall.play();
    }

}
