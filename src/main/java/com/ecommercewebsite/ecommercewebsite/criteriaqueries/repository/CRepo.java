package com.ecommercewebsite.ecommercewebsite.criteriaqueries.repository;

import com.ecommercewebsite.ecommercewebsite.criteriaqueries.Entity.CriteriaQueries;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Repository
public interface CRepo extends JpaRepository<CriteriaQueries,String> {
}
