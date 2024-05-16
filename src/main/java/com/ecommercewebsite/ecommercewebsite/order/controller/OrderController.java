package com.ecommercewebsite.ecommercewebsite.order.controller;
import com.ecommercewebsite.ecommercewebsite.order.Service.OrderService;
import com.ecommercewebsite.ecommercewebsite.order.model.OrderPaymentRequest;
import com.ecommercewebsite.ecommercewebsite.order.model.OrderPaymentResponse;
import com.ecommercewebsite.ecommercewebsite.order.model.OrderRequest;
import com.ecommercewebsite.ecommercewebsite.order.model.OrderResponse;
import com.ecommercewebsite.ecommercewebsite.payment.model.PaymentProcessingRequest;
import com.ecommercewebsite.ecommercewebsite.product.model.ProductResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;


/**
 * This is an OrderController class.
 * This is responsible for all the operations regarding user orders.
 */
@RestController
@RequiredArgsConstructor
@Slf4j
public class OrderController {

    private final OrderService orderService;

    /**
     * This method is responsible for retrieving all orders
     * @return List of orderResponse payload
     */
    @GetMapping("/orders")
    public ResponseEntity<List<OrderResponse>> retrieveAllOrders()
    {
        List<OrderResponse> orderResponses = orderService.retrieveAllOrders();
        return ResponseEntity.ok(orderResponses);
    }
    /**
     * This method is responsible for create new order for user
     * @param userId
     * @param orderRequest
     * @return OrderResponse Payload
     */
    @PostMapping("/orders/{userId}")
    public ResponseEntity<OrderResponse> createNewOrderForUser(@PathVariable String userId, @Valid  @RequestBody OrderRequest orderRequest)
    {
        OrderResponse orderResponse =  orderService.createNewOrderForUser(userId,orderRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(orderResponse);
    }

    /**
     * This method is responsible for retrieving all products in an order.
     * @param orderId
     * @return ProductResponse payload
     */
    @GetMapping("/orders/{orderId}")
    public ResponseEntity<List<ProductResponse>> retrieveAllProducts(@PathVariable String orderId)
    {
        List<ProductResponse> productResponses = orderService.retrieveAllProductsForOrder(orderId);
        return ResponseEntity.ok(productResponses);
    }

    /**
     * This method is responsible for update an order.
     * @param orderId
     * @param orderRequest
     * @return OrderResponse Payload.
     */
    @PutMapping("/orders/{orderId}")
    public ResponseEntity<OrderResponse> updateOrderById(@PathVariable String orderId,@Valid @RequestBody OrderRequest orderRequest)
    {
        OrderResponse orderResponse = orderService.updateOrderById(orderId,orderRequest);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(orderResponse);
    }

    /**
     * This
     * @param orderId
     * @return ResponseObject with status 204
     */
    @DeleteMapping("/orders/{orderId}")
    public ResponseEntity<Object> deleteOrderById(@PathVariable String orderId)
    {
        orderService.deleteOrderById(orderId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    /**
     * This method is responsible for initiation of the order.
     * @param orderPaymentRequest
     * @return orderPaymentResponse Payload
     */
    @PostMapping("/orders")
    public ResponseEntity<OrderPaymentResponse> orderInitiation(@Valid @RequestBody OrderPaymentRequest orderPaymentRequest)
    {
        OrderPaymentResponse orderPaymentResponse = orderService.orderInitiation(orderPaymentRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(orderPaymentResponse);
    }

    /**
     * This method is responsible for orderProcessing
     * @param userId
     * @param paymentProcessingRequest
     * @return OrderResponse Payload
     */
    @PostMapping("/orders/{userId}/order-confirmation")
    public ResponseEntity<OrderResponse> orderProcessing(@PathVariable String userId,@Valid @RequestBody PaymentProcessingRequest paymentProcessingRequest)
    {
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().buildAndExpand().toUri();
        OrderResponse orderResponse = orderService.orderProcessing(userId,paymentProcessingRequest);
        return ResponseEntity.created(location).body(orderResponse);
    }


}
