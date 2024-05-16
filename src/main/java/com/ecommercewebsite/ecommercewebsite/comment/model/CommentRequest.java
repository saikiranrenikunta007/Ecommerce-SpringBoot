package com.ecommercewebsite.ecommercewebsite.comment.model;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CommentRequest {
    @Pattern(regexp = "[a-zA-Z]{10,100}")
    private String reviews;
}
