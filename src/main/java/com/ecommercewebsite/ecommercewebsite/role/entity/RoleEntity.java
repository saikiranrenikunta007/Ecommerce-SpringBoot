package com.ecommercewebsite.ecommercewebsite.role.entity;

import com.ecommercewebsite.ecommercewebsite.base.AbstractAuditingEntity;
import com.ecommercewebsite.ecommercewebsite.user.entity.UserEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Setter
@Table(name="role")
public class RoleEntity{
   @Id
   private String id;
   @Column(name = "created_by")
   private String createdBy;
   @Column(name = "created_date")
   private Date createdDate;
   @Column(name="last_updated_by")
   private  String lastUpdatedBy;
   @Column(name = "last_updated_date")
   private Date lastUpdatedDate;
   @Column(name="roleType")
   private  String roleType;
   @OneToMany(mappedBy = "roleEntity",cascade = CascadeType.ALL)
   private List<UserEntity>users= new ArrayList<>();
}
