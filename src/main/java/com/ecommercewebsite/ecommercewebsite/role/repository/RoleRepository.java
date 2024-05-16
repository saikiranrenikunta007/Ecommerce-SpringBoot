package com.ecommercewebsite.ecommercewebsite.role.repository;

import com.ecommercewebsite.ecommercewebsite.role.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface  RoleRepository extends JpaRepository<RoleEntity,String>{

}
