package com.ecommercewebsite.ecommercewebsite.user.model;

import com.ecommercewebsite.ecommercewebsite.role.entity.RoleEntity;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@ToString(onlyExplicitlyIncluded = true)
@RequiredArgsConstructor
public class UserRequest {
    @NotNull
    @NotBlank
    @Pattern(regexp = "[6-9]{1}[0-9]{9}")
    @Size(max = 255)
    @ToString.Include
    private final String mobileNumber;
    @NotNull
    @NotBlank
    @Pattern(regexp = "[A-Z]{1}[a-z]{1,10}")
    private final String firstName;

    @NotNull
    @NotBlank
    @Pattern(regexp = "[A-Z]{1}[a-z]{1,10}")
    private final String lastName;
    @Past
    private final LocalDate dateOfBirth;

    @NotNull
    @NotBlank
    @Email
    private final String emailId;

    @NotNull
    @NotBlank
    @Pattern(regexp = "^(?=.*?[A-Za-z])(?=.*?[A-za-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,19}$")
    private final String password;
    private final RoleEntity roleEntity;

}