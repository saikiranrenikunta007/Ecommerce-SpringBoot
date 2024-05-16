package com.ecommercewebsite.ecommercewebsite.order.Service;
import com.ecommercewebsite.ecommercewebsite.order.entity.OrderEntity;
import com.ecommercewebsite.ecommercewebsite.order.mapper.OrderMapper;
import com.ecommercewebsite.ecommercewebsite.order.mapper.OrderPaymentMapper;
import com.ecommercewebsite.ecommercewebsite.order.model.OrderPaymentRequest;
import com.ecommercewebsite.ecommercewebsite.order.model.OrderPaymentResponse;
import com.ecommercewebsite.ecommercewebsite.order.model.OrderRequest;
import com.ecommercewebsite.ecommercewebsite.order.model.OrderResponse;
import com.ecommercewebsite.ecommercewebsite.order.repository.OrderRepository;
import com.ecommercewebsite.ecommercewebsite.payment.entity.PaymentEntity;
import com.ecommercewebsite.ecommercewebsite.payment.model.PaymentProcessingRequest;
import com.ecommercewebsite.ecommercewebsite.payment.repository.PaymentRepository;
import com.ecommercewebsite.ecommercewebsite.product.entity.ProductEntity;
import com.ecommercewebsite.ecommercewebsite.product.mapper.ProductMapper;
import com.ecommercewebsite.ecommercewebsite.product.model.ProductResponse;
import com.ecommercewebsite.ecommercewebsite.product.repository.ProductRepository;
import com.ecommercewebsite.ecommercewebsite.user.entity.UserEntity;
import com.ecommercewebsite.ecommercewebsite.user.repository.UserRepository;
import com.ecommercewebsite.ecommercewebsite.useraddress.entity.UserAddressEntity;
import com.ecommercewebsite.ecommercewebsite.useraddress.mapper.UserAddressMapper;
import com.ecommercewebsite.ecommercewebsite.useraddress.model.UserAddressResponse;
import com.ecommercewebsite.ecommercewebsite.useraddress.repository.UserAddressRepository;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderServiceImpl implements  OrderService{
    private final UserRepository userRepository;
    private final UserAddressRepository userAddressRepository;
    @Autowired
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final OrderMapper orderMapper;
    private final ProductMapper productMapper;
    private final OrderPaymentMapper orderPaymentMapper;
    private final PaymentRepository paymentRepository;
    private final UserAddressMapper userAddressMapper;

    @Override
    public OrderResponse createNewOrderForUser(String userId, OrderRequest orderRequest) {
        Optional<UserEntity> userEntity = userRepository.findById(userId);
        if(userEntity.isPresent())
        {
            OrderEntity orderEntity = orderMapper.create(orderRequest);
            orderEntity.setUserEntity(userEntity.get());
            orderRepository.save(orderEntity);
            return orderMapper.toResponse(orderEntity);
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    @Override
    public List<ProductResponse> retrieveAllProductsForOrder(String orderId) {
        Optional<OrderEntity> orderEntity = orderRepository.findById(orderId);
        if(orderEntity.isPresent())
        {
            List<ProductResponse> productResponses = orderEntity.get().getProductEntities().stream().map(productMapper::toResponse).toList();
            if(productResponses.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
            return productResponses;

        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
    @Override
    public OrderResponse updateOrderById(String orderId, OrderRequest orderRequest) {
        Optional<OrderEntity> order = orderRepository.findById(orderId);
        if(order.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        OrderEntity orderEntity = orderMapper.create(orderRequest);
        orderEntity.setUserEntity(order.get().getUserEntity());
        orderEntity.setId(orderId);
        orderRepository.save(orderEntity);
        return orderMapper.toResponse(orderEntity);
    }

    @Override
    public void deleteOrderById(String orderId) {
        Optional<OrderEntity>orderEntity = orderRepository.findById(orderId);
        if(orderEntity.isEmpty())
        {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        }
        orderRepository.deleteById(orderId);
    }

    @Override
    public OrderPaymentResponse orderInitiation(OrderPaymentRequest orderPaymentRequest) {
        Set<ProductResponse> productResponses = productRepository.findAllById(orderPaymentRequest.getProductIds()).stream().map(productMapper::toResponse).collect(Collectors.toSet());
        Optional<UserAddressResponse> userAddressResponse = userAddressRepository.findById(orderPaymentRequest.getUserAddressId()).stream().map(userAddressMapper::toResponse).findFirst();
        if(userAddressResponse.isEmpty())
        {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"please give a valid address");
        }
        if(productResponses.isEmpty())
        {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"please add some valid products");
        }
        OrderRequest orderRequest = new OrderRequest("INITIATED",productResponses,userAddressResponse.get());
        OrderEntity orderEntity = orderMapper.create(orderRequest);
        Optional<UserAddressEntity> userAddressEntity = userAddressRepository.findById(orderPaymentRequest.getUserAddressId());
        if(userAddressEntity.isPresent())
        {
            UserEntity userEntity = userAddressEntity.get().getUserEntity();
            orderEntity.setUserEntity(userEntity);
        }
        orderRepository.save(orderEntity);
        double totalAmount = productRepository.findAllById(productResponses.stream().map(ProductResponse::getId).toList()).stream().map(ProductEntity::getPrice).reduce(0.0, Double::sum);
        return orderPaymentMapper.toResponse(orderEntity,totalAmount);

    }
    @Override
    public OrderResponse orderProcessing(String userId,PaymentProcessingRequest paymentProcessingRequest)
    {
        Optional<UserEntity> userEntity = userRepository.findById(userId);
        if(userEntity.isEmpty())
        {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Invalid User");
        }
        Optional<PaymentEntity> paymentEntity = paymentRepository.findById(paymentProcessingRequest.getPaymentId());
        if(paymentEntity.isEmpty())
        {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Invalid PaymentId");
        }
        OrderEntity orderEntity = paymentEntity.get().getOrderEntityDetails().get(0);
        orderEntity.setDeliveryStatus("ORDER CONFIRMED - DELIVERED WITH IN 3 DAYS");
        orderRepository.save(orderEntity);
        return orderMapper.toResponse(orderEntity);
    }

    @Override
    public List<OrderResponse> retrieveAllOrders() {
        log.info("retrieve all orders start");
        try{
            log.info("orderEntities::{}",orderRepository.findAll());
            List<OrderEntity> orderEntities = orderRepository.findAll();
            if(orderEntities.isEmpty() || orderEntities==null)
            {
                log.info("Empty Entities");
                throw new ResponseStatusException(HttpStatus.NOT_FOUND,"No orders available");
            }
            log.info("responses::::");
            List<OrderResponse> orderResponses = orderEntities.stream().map(orderMapper::toResponse).toList();
            return orderResponses;
        }
        catch (Exception e)
        {
            log.error("error while retrieving orders{}",e.getMessage());
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);


    }

    @Override
    public int add(int a, int b) {
        return a+b;
    }

}
