package com.ecommercewebsite.ecommercewebsite.comment.service;

import com.ecommercewebsite.ecommercewebsite.comment.model.CommentRequest;
import com.ecommercewebsite.ecommercewebsite.comment.model.CommentResponse;

import java.util.List;


public interface CommentService {
    /**
     * This method is used to add a comment for a product.
     *
     * @param productId
     * @param commentRequest
     * @return
     */
    public CommentResponse addComment(CommentRequest commentRequest, String productId);

    /**
     * This method is responsible for retrieving all comments for a product.
     *
     * @param productId
     * @return CommentResponse payload
     */

    public List<CommentResponse> retrieveAllComments(String productId);

    /**
     * This method is responsible for update a comment for a product
     *
     * @param commentId
     * @param commentRequest
     * @ return CommentResponse Payload
     */


    public CommentResponse updateCommentById(CommentRequest commentRequest, String commentId);

    /**
     * This method is responsible for deletion of comment for a product.
     *
     * @param commentId
     * @return COmmentResponse payload
     */

    public CommentResponse deleteCommentById(String commentId);

    /**
     * This method is responsible for deleting all comments of a product.
     *
     * @param productId
     * @return List of CommentResponses
     */

    public List<CommentResponse> deleteAllCommentOfProduct(String productId);
}
