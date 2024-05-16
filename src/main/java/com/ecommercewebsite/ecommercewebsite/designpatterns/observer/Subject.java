package com.ecommercewebsite.ecommercewebsite.designpatterns.observer;
public interface Subject {
    void registerObserver(Observer o);
    void deregisterObserver(Observer o);
    void notifyUser();
}
