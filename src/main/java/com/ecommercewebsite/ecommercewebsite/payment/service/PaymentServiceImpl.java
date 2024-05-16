package com.ecommercewebsite.ecommercewebsite.payment.service;

import com.ecommercewebsite.ecommercewebsite.order.entity.OrderEntity;
import com.ecommercewebsite.ecommercewebsite.order.repository.OrderRepository;
import com.ecommercewebsite.ecommercewebsite.payment.entity.PaymentEntity;
import com.ecommercewebsite.ecommercewebsite.payment.mapper.PaymentMapper;
import com.ecommercewebsite.ecommercewebsite.payment.model.PaymentRequest;
import com.ecommercewebsite.ecommercewebsite.payment.model.PaymentResponse;
import com.ecommercewebsite.ecommercewebsite.payment.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.server.ResponseStatusException;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService{
    private final OrderRepository orderRepository;
    private final PaymentMapper paymentMapper;
    private final PaymentRepository paymentRepository;
    @Override
    public PaymentResponse paymentInitiation(@RequestBody PaymentRequest paymentRequest)
    {
            Optional<OrderEntity> orderEntity = orderRepository.findById(paymentRequest.getOrderId());
            if (orderEntity.isEmpty()) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND,"provide valid order to initiate payment");
            }
            PaymentEntity paymentEntity = paymentMapper.create(paymentRequest);
            paymentEntity.setOrderEntityDetails(List.of(orderEntity.get()));
            paymentEntity.setStatus("SUCCESSFUL");
            paymentRepository.save(paymentEntity);
            orderEntity.get().setPaymentEntity(paymentEntity);
            orderEntity.get().setDeliveryStatus("PROCESSING");
            orderRepository.save(orderEntity.get());
            return paymentMapper.toResponse(paymentEntity);
    }


}
