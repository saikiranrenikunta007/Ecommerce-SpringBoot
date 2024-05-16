package com.ecommercewebsite.ecommercewebsite.criteriaqueries.service;

import com.ecommercewebsite.ecommercewebsite.criteriaqueries.Entity.CriteriaQueries;
import com.ecommercewebsite.ecommercewebsite.criteriaqueries.mapper.CriteriaQueriesMapper;
import com.ecommercewebsite.ecommercewebsite.criteriaqueries.model.CriteriaRequest;
import com.ecommercewebsite.ecommercewebsite.criteriaqueries.model.CriteriaResponse;
import com.ecommercewebsite.ecommercewebsite.criteriaqueries.repository.CRepo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.PersistenceUnit;
import jakarta.persistence.criteria.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.List;

@Slf4j
@Getter
@Setter
@RequiredArgsConstructor
@Service
public class CriteriaServiceImpl implements CriteriaService {
    private final EntityManager em;
    private final CriteriaQueriesMapper criteriaQueriesMapper;

    private final CRepo cRepo;
    @Override
    public List<CriteriaResponse> getAllItems()
    {


        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<CriteriaQueries> cr = cb.createQuery(CriteriaQueries.class);
        Root<CriteriaQueries> root = cr.from(CriteriaQueries.class);
        cr.select(root);
        Query<CriteriaQueries> query = (Query<CriteriaQueries>) em.createQuery(cr);
        List<CriteriaQueries> results = query.getResultList();
        return results.stream().map(criteriaQueriesMapper::toResponse).toList();

    }

    @Override
    public CriteriaResponse addItem(CriteriaRequest criteriaRequest) {
        CriteriaQueries criteriaQueries = criteriaQueriesMapper.create(criteriaRequest);
        cRepo.save(criteriaQueries);
        return criteriaQueriesMapper.toResponse(criteriaQueries);
    }
    @Override
    public List<CriteriaResponse> getItems()
    {
        Predicate[] predicates = new Predicate[2];
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<CriteriaQueries> cr = cb.createQuery(CriteriaQueries.class);
        Root<CriteriaQueries> root = cr.from(CriteriaQueries.class);
        predicates[0] = cb.between(root.get("itemPrice"),1500,2500);
        predicates[1] = cb.like(root.get("itemName"), "P%");
        cr.select(root).where(predicates);
        Query<CriteriaQueries> criteriaQueries = (Query<CriteriaQueries>) em.createQuery(cr);
        return criteriaQueries.getResultList().stream().map(criteriaQueriesMapper::toResponse).toList();
    }

    @Override
    public List<CriteriaResponse> sortItems() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<CriteriaQueries> cr = cb.createQuery(CriteriaQueries.class);
        Root<CriteriaQueries> root = cr.from(CriteriaQueries.class);
        cr.orderBy(cb.asc(root.get("itemPrice")), cb.desc(root.get("itemName")));
        Query<CriteriaQueries> criteriaQueries = (Query<CriteriaQueries>) em.createQuery(cr);
        return criteriaQueries.getResultList().stream().map(criteriaQueriesMapper::toResponse).toList();

    }
    @Override
    public List<Double> getAvg()
    {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Double> cr = cb.createQuery(Double.class);
        Root<CriteriaQueries> root = cr.from(CriteriaQueries.class);
        cr.select(cb.avg(root.get("itemPrice")));
        Query<Double> criteriaQueries = (Query<Double>) em.createQuery(cr);
        return criteriaQueries.getResultList();



    }

    @Override
    @Transactional
    public void updateItem() {
        log.info("CriteriaService :: updateItem() started");
        CriteriaBuilder cb = em.getCriteriaBuilder();
        log.info("CriteriaBuilder {}",cb);
        CriteriaUpdate<CriteriaQueries> criteriaUpdate = cb.createCriteriaUpdate(CriteriaQueries.class);
        log.info("CriteriaUpdate {}",criteriaUpdate);
        Root<CriteriaQueries> root = criteriaUpdate.from(CriteriaQueries.class);
        criteriaUpdate.set("itemPrice", 5000);
        criteriaUpdate.where(cb.equal(root.get("itemPrice"),2000));
        em.createQuery(criteriaUpdate).executeUpdate();

    }
}

