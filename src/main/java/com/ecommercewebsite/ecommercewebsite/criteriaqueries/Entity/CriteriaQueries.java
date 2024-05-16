package com.ecommercewebsite.ecommercewebsite.criteriaqueries.Entity;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Slf4j
@Builder
public class CriteriaQueries{
        @Id
        private  String itemId= UUID.randomUUID().toString();
        private  String  itemName;
        private  String itemDescription;
        private  int itemPrice;
}