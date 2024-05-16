package com.ecommercewebsite.ecommercewebsite.comment.controller;

import com.ecommercewebsite.ecommercewebsite.comment.model.CommentRequest;
import com.ecommercewebsite.ecommercewebsite.comment.model.CommentResponse;
import com.ecommercewebsite.ecommercewebsite.comment.service.CommentService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
/**
 * This is CommentController class
 * This class is responsible for comment operations.
 */
public class CommentController {

    private final CommentService commentService;

    /**
     * This method is responsible for retrieving all comments for a product.
     *
     * @param productId
     * @return CommentResponse payload
     */
    @GetMapping("/comments/{productId}")
    public ResponseEntity<List<CommentResponse>> retrieveAllComments(@PathVariable String productId) {
        List<CommentResponse> commentResponses = commentService.retrieveAllComments(productId);
        return ResponseEntity.ok(commentResponses);
    }

    /**
     * This method is used to add a comment for a product.
     *
     * @param productId
     * @param commentRequest
     * @return
     */
    @PostMapping("/comments/{productId}")
    public ResponseEntity<CommentResponse> addComment(@PathVariable String productId, @Valid @RequestBody CommentRequest commentRequest) {
        CommentResponse commentResponse = commentService.addComment(commentRequest, productId);
        return ResponseEntity.status(HttpStatus.CREATED).body(commentResponse);
    }

    /**
     * This method is responsible for update a comment for a product
     *
     * @param commentId
     * @param commentRequest
     * @ return CommentResponse Payload
     */
    @PutMapping("/comments/{commentId}")
    public ResponseEntity<CommentResponse> updateCommentById(@PathVariable String commentId, @Valid @RequestBody CommentRequest commentRequest) {
        CommentResponse commentResponse = commentService.updateCommentById(commentRequest, commentId);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(commentResponse);
    }

    /**
     * This method is responsible for deletion of comment for a product.
     *
     * @param commentId
     * @return COmmentResponse payload
     */
    @DeleteMapping("/comments/{commentId}")
    public ResponseEntity<CommentResponse> deleteCommentById(@PathVariable String commentId) {
        CommentResponse commentResponse = commentService.deleteCommentById(commentId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(commentResponse);
    }

    /**
     * This method is responsible for deleting all comments of a product.
     *
     * @param productId
     * @return List of CommentResponses
     */
    @DeleteMapping("/comments/products/{productId}")
    public ResponseEntity<List<CommentResponse>> deleteAllCommentOfProduct(@PathVariable String productId) {
        List<CommentResponse> commentResponses = commentService.deleteAllCommentOfProduct(productId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(commentResponses);
    }


}
