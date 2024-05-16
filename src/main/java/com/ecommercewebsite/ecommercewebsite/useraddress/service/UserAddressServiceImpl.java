package com.ecommercewebsite.ecommercewebsite.useraddress.service;

import com.ecommercewebsite.ecommercewebsite.user.entity.UserEntity;
import com.ecommercewebsite.ecommercewebsite.user.repository.UserRepository;
import com.ecommercewebsite.ecommercewebsite.useraddress.entity.UserAddressEntity;
import com.ecommercewebsite.ecommercewebsite.useraddress.mapper.UserAddressMapper;
import com.ecommercewebsite.ecommercewebsite.useraddress.model.UserAddressRequest;
import com.ecommercewebsite.ecommercewebsite.useraddress.model.UserAddressResponse;
import com.ecommercewebsite.ecommercewebsite.useraddress.repository.UserAddressRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
@RequiredArgsConstructor
public class UserAddressServiceImpl implements UserAddressService {

    private final UserAddressRepository userAddressRepository;
    private final UserAddressMapper userAddressMapper;
    private  final UserRepository userRepository;

    @Override
    public UserAddressResponse addAddressforUser(String userId, UserAddressRequest userAddressRequest) {
        Optional<UserEntity> userEntity = userRepository.findById(userId);
        if(userEntity.isPresent())
        {
            UserAddressEntity userAddressEntity = userAddressMapper.create(userAddressRequest);
            userAddressEntity.setUserEntity(userEntity.get());
            userAddressRepository.save(userAddressEntity);
            return userAddressMapper.toResponse(userAddressEntity);

        }
       throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    @Override
    public List<UserAddressResponse> retrieveAllAddressesOfUser(String userId) {
        Optional<UserEntity> userEntity = userRepository.findById(userId);
        if(userEntity.isPresent())
        {
           return  userEntity.get().getUserAddressEntities().stream().map(userAddressMapper::toResponse).toList();
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    @Override
    public UserAddressResponse updateUserAddressById(String userAddressId, UserAddressRequest userAddressRequest) {
        Optional<UserAddressEntity> userAddress = userAddressRepository.findById(userAddressId);
        if(userAddress.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        UserAddressEntity userAddressEntity = userAddressMapper.create(userAddressRequest);
        userAddressEntity.setId(userAddressId);
        userAddressEntity.setUserEntity(userAddress.get().getUserEntity());
        userAddressRepository.save(userAddressEntity);
        return userAddressMapper.toResponse(userAddressEntity);
    }

    @Override
    public void deleteUserAddressById(String userAddressId) {
        if(userAddressRepository.findById(userAddressId).isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        userAddressRepository.deleteById(userAddressId);
    }
}
