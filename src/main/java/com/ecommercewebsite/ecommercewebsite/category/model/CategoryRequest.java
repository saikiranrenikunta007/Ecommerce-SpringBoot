package com.ecommercewebsite.ecommercewebsite.category.model;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;


@Getter
@Setter
@RequiredArgsConstructor
public class CategoryRequest {
    @NotNull
    @NotBlank
    @Pattern(regexp ="[a-z A-Z]+")
    private final String name;
    @NotBlank
    @NotNull
    private final String description;
}
