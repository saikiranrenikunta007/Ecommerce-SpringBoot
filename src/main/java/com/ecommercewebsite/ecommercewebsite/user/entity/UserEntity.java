package com.ecommercewebsite.ecommercewebsite.user.entity;

import com.ecommercewebsite.ecommercewebsite.base.AbstractAuditingEntity;
import com.ecommercewebsite.ecommercewebsite.order.entity.OrderEntity;
import com.ecommercewebsite.ecommercewebsite.role.entity.RoleEntity;
import com.ecommercewebsite.ecommercewebsite.useraddress.entity.UserAddressEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.access.vote.RoleVoter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Builder
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="users")
public class UserEntity extends AbstractAuditingEntity implements UserDetails  {

    @Column(name="first_name")
    private String firstName;
    @Column(name="last_name")
    private String lastName;
    @Column(name="date_of_birth")
    private LocalDate dateOfBirth;
    @Column(name="mobile_number")
    private String mobileNumber;
    @Column(name="email_id")
    private String emailId;
    @Column(name="password")
    private String password;
    @JsonIgnore
    @OneToMany(mappedBy = "userEntity",cascade = CascadeType.ALL)
    private List<UserAddressEntity> userAddressEntities =new ArrayList<>();
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="roleId")
    private RoleEntity roleEntity;
    @JsonIgnore
    @OneToMany(mappedBy = "userEntity",cascade = CascadeType.ALL)
    private List<OrderEntity> orderEntities =new ArrayList<>();

    public UserEntity(String sagar, String kumar, LocalDate of, String s, String s1, String s2,String id) {
        super();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> sample = List.of(new SimpleGrantedAuthority(roleEntity.getRoleType()));
        sample.forEach(System.out::println);
        return sample;

    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return emailId;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
