package com.ecommercewebsite.ecommercewebsite.comment;

import com.ecommercewebsite.ecommercewebsite.comment.controller.CommentController;
import com.ecommercewebsite.ecommercewebsite.comment.entity.CommentEntity;
import com.ecommercewebsite.ecommercewebsite.comment.model.CommentRequest;
import com.ecommercewebsite.ecommercewebsite.comment.model.CommentResponse;
import com.ecommercewebsite.ecommercewebsite.comment.service.CommentService;
import com.ecommercewebsite.ecommercewebsite.config.jwt.service.JwtService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;
import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@WebMvcTest(controllers = CommentController.class)
@AutoConfigureMockMvc(addFilters = false)
public class ControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private JwtService jwtService;
    @MockBean
    private CommentService commentService;
    private final ObjectMapper objectMapper = new ObjectMapper();
    private static final String SPECIFIC_URL = "http://localhost:8080/comments/923e2a7c-ce55-4392-8be9-b7be0a037653";
    private static final String GENERIC_URL = "http://localhost:8080/comments";
    @Test
    void retrieveAllComments() throws Exception {
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        JSONObject[] jsonObjects = new JSONObject[1];
        jsonObjects[0] = new JSONObject();
        jsonObjects[0].put("id","62e0267d-f5a7-45ae-8577-74066a96c6d4");
        JsonNode jsonNode = objectMapper.valueToTree(jsonObjects);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(SPECIFIC_URL).accept(MediaType.APPLICATION_JSON);
        CommentResponse commentResponse = CommentResponse
                                         .builder()
                                         .id("62e0267d-f5a7-45ae-8577-74066a96c6d4")
                                         .reviews("GoodProduct")
                                         .build();
        when(commentService.retrieveAllComments(anyString())).thenReturn(Collections.singletonList(commentResponse));
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        Assertions.assertEquals(200,result.getResponse().getStatus());
        JSONAssert.assertEquals(objectMapper.writeValueAsString(jsonNode),result.getResponse().getContentAsString(),false);

    }
    @Test
    void addAComment() throws Exception
    {
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id","62e0267d-f5a7-45ae-8577-74066a96c6d4");
        jsonObject.put("reviews","GoodProduct");
        JsonNode jsonNode = objectMapper.valueToTree(jsonObject);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.post(SPECIFIC_URL).accept(MediaType.APPLICATION_JSON).content(jsonObject.toString()).contentType(MediaType.APPLICATION_JSON);
        CommentResponse commentResponse = CommentResponse
                .builder()
                .id("62e0267d-f5a7-45ae-8577-74066a96c6d4")
                .reviews("GoodProduct")
                .build();
        when(commentService.addComment(any(CommentRequest.class),any())).thenReturn(commentResponse);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        Assertions.assertEquals(201,result.getResponse().getStatus());
        JSONAssert.assertEquals(objectMapper.writeValueAsString(jsonNode),result.getResponse().getContentAsString(),false);

    }
    @Test
    void deleteCommentById() throws Exception
    {
        String commentId = "62e0267d-f5a7-45ae-8577-74066a96c6d4";
        RequestBuilder requestBuilder = MockMvcRequestBuilders.delete(GENERIC_URL+"/"+commentId).accept(MediaType.APPLICATION_JSON);
        CommentResponse commentResponse = CommentResponse
                .builder()
                .id("62e0267d-f5a7-45ae-8577-74066a96c6d4")
                .reviews("GoodProduct")
                .build();
        when(commentService.deleteCommentById(commentId)).thenReturn(commentResponse);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        Assertions.assertEquals(204, result.getResponse().getStatus());
        verify(commentService, times(1)).deleteCommentById(commentId);
    }
    @Test
    void update() throws Exception
    {
        String commentId = "62e0267d-f5a7-45ae-8577-74066a96c6d4";
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id","62e0267d-f5a7-45ae-8577-74066a96c6d4");
        jsonObject.put("reviews","GoodProduct");
        JsonNode jsonNode = objectMapper.valueToTree(jsonObject);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.put(GENERIC_URL+"/"+commentId)
                .content(jsonObject.toString()).contentType(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        Assertions.assertEquals(202,result.getResponse().getStatus());
        verify(commentService).updateCommentById(any(CommentRequest.class),eq(commentId));
    }
}
