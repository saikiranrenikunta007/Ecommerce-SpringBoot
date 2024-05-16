package com.ecommercewebsite.ecommercewebsite.base;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@MappedSuperclass
public class AbstractAuditingEntity {
    @Id
    private String id= UUID.randomUUID().toString();
    @Column(name = "created_by")
    private String createdBy = "SYSTEM USER";
    @Column(name = "created_date")
    private Date createdDate = new Date();
    @Column(name="last_updated_by")
    private  String lastUpdatedBy = "SYSTEM USER";
    @Column(name = "last_updated_date")
    private Date lastUpdatedDate = new Date();
    @Column(name="last_logged_in")
    private Date lastLoggedIn = new Date();
}
