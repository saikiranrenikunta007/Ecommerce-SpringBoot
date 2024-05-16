package com.ecommercewebsite.ecommercewebsite.designpatterns.observer;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
@Component
public class SubjectImpl implements Subject{
    private ArrayList<Observer> observers = new ArrayList<>();
    @Override
    public void registerObserver(Observer o)
    {
        observers.add(o);
    }
    @Override
    public void deregisterObserver(Observer o)
    {
        observers.remove(o);
    }
    @Override
    public void notifyUser()
    {
        observers.forEach(Observer::update);
    }

}
