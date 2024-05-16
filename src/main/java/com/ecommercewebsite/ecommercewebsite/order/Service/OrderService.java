package com.ecommercewebsite.ecommercewebsite.order.Service;

import com.ecommercewebsite.ecommercewebsite.order.model.OrderPaymentRequest;
import com.ecommercewebsite.ecommercewebsite.order.model.OrderPaymentResponse;
import com.ecommercewebsite.ecommercewebsite.order.model.OrderRequest;
import com.ecommercewebsite.ecommercewebsite.order.model.OrderResponse;
import com.ecommercewebsite.ecommercewebsite.payment.model.PaymentProcessingRequest;
import com.ecommercewebsite.ecommercewebsite.product.model.ProductResponse;

import java.util.List;

public interface OrderService {
    /**
     * This method is responsible for create new order for user
     *
     * @param userId
     * @param orderRequest
     * @return OrderResponse Payload
     */
    public OrderResponse createNewOrderForUser(String userId, OrderRequest orderRequest);

    /**
     * This method is responsible for retrieving all products in an order.
     *
     * @param orderId
     * @return ProductResponse payload
     */
    public List<ProductResponse> retrieveAllProductsForOrder(String orderId);

    /**
     * This method is responsible for update an order.
     *
     * @param orderId
     * @param orderRequest
     * @return OrderResponse Payload.
     */

    public OrderResponse updateOrderById(String orderId, OrderRequest orderRequest);

    /**
     * This
     *
     * @param orderId
     * @return ResponseObject with status 204
     */
    public void deleteOrderById(String orderId);

    /**
     * This method is responsible for initiation of the order.
     *
     * @param orderPaymentRequest
     * @return orderPaymentResponse Payload
     */
    public OrderPaymentResponse orderInitiation(OrderPaymentRequest orderPaymentRequest);

    /**
     * This method is responsible for orderProcessing
     *
     * @param userId
     * @param paymentProcessingRequest
     * @return OrderResponse Payload
     */
    OrderResponse orderProcessing(String userId, PaymentProcessingRequest paymentProcessingRequest);

    /**
     * This method is responsible for retrieving all orders
     *
     * @return List of orderResponse payload
     */

    public List<OrderResponse> retrieveAllOrders();

    public int add(int a, int b);
}
