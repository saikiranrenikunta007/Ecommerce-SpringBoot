package com.ecommercewebsite.ecommercewebsite.useraddress.repository;

import com.ecommercewebsite.ecommercewebsite.useraddress.entity.UserAddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserAddressRepository extends JpaRepository<UserAddressEntity,String> {
}
