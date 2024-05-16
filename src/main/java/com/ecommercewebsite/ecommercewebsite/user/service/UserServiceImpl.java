package com.ecommercewebsite.ecommercewebsite.user.service;

import com.ecommercewebsite.ecommercewebsite.order.mapper.OrderMapper;
import com.ecommercewebsite.ecommercewebsite.order.model.OrderResponse;
import com.ecommercewebsite.ecommercewebsite.role.entity.RoleEntity;
import com.ecommercewebsite.ecommercewebsite.role.mapper.RoleMapper;
import com.ecommercewebsite.ecommercewebsite.role.model.RoleRequest;
import com.ecommercewebsite.ecommercewebsite.role.model.RoleResponse;
import com.ecommercewebsite.ecommercewebsite.user.entity.UserEntity;
import com.ecommercewebsite.ecommercewebsite.user.mapper.UserMapper;
import com.ecommercewebsite.ecommercewebsite.user.model.UserRequest;
import com.ecommercewebsite.ecommercewebsite.user.model.UserResponse;
import com.ecommercewebsite.ecommercewebsite.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;
import java.util.Optional;
@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {
    private  final UserRepository userRepository;
    private  final UserMapper userMapper;
    private final RoleMapper roleMapper;
    private final OrderMapper orderMapper;
    @Override
    public List<UserResponse> retrieveAllUsers() {
       List<UserResponse> userResponses= userRepository.findAll().stream().map(userMapper::toResponse).toList();
       if(userResponses.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
       return userResponses;
    }
    @Override
    public UserResponse retrieveUserDetailsById(String userId) {
        Optional<UserEntity> userEntity= userRepository.findById(userId);
        log.info("retrieveUserDetailsById started");
        if(userEntity.isPresent())
        {
            log.info("UserService::retrieveUserDetailsById {} userEntity {}",userId,userEntity);
            return userMapper.toResponse(userEntity.get());
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
   @Override
    public void deleteUser(String userId) {
        if(userRepository.findById(userId).isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        userRepository.deleteById(userId);
    }
    @Override
    public UserResponse updateUser(UserRequest userRequest,String userId) {
          Optional<UserEntity> user = userRepository.findById(userId);
          if(user.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
          UserEntity userEntity  = userMapper.create(userRequest);
          userEntity.setId(userId);
          userEntity.setRoleEntity(user.get().getRoleEntity());
          userEntity.setUserAddressEntities(user.get().getUserAddressEntities());
          userEntity.setOrderEntities(user.get().getOrderEntities());
          userRepository.save(userEntity);
          return userMapper.toResponse(userEntity);
    }
    @Override
    public List<OrderResponse> retrieveAllOrders(String userId) {
        Optional<UserEntity> userEntity = userRepository.findById(userId);
        if(userEntity.isPresent())
        {
           List<OrderResponse> orderResponses = userEntity.get().getOrderEntities().stream().map(orderMapper :: toResponse).toList();
           if(orderResponses.isEmpty())  throw new ResponseStatusException(HttpStatus.NOT_FOUND);
           return orderResponses;
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
}