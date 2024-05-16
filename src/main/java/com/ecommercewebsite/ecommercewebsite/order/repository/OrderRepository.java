package com.ecommercewebsite.ecommercewebsite.order.repository;

import com.ecommercewebsite.ecommercewebsite.order.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity,String> {

}
