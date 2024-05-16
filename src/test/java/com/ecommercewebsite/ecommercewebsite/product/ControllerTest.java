package com.ecommercewebsite.ecommercewebsite.product;

import com.ecommercewebsite.ecommercewebsite.category.model.CategoryRequest;
import com.ecommercewebsite.ecommercewebsite.config.jwt.service.JwtService;
import com.ecommercewebsite.ecommercewebsite.product.controller.ProductController;
import com.ecommercewebsite.ecommercewebsite.product.model.PagingResponse;
import com.ecommercewebsite.ecommercewebsite.product.model.ProductRequest;
import com.ecommercewebsite.ecommercewebsite.product.model.ProductResponse;
import com.ecommercewebsite.ecommercewebsite.product.service.ProductService;
import com.ecommercewebsite.ecommercewebsite.useraddress.model.UserAddressRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import lombok.extern.slf4j.Slf4j;
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
import java.util.List;
import java.util.ArrayList;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@WebMvcTest(controllers = ProductController.class)
@AutoConfigureMockMvc(addFilters = false)
@Slf4j
public class ControllerTest {
    @MockBean
    private JwtService jwtService;
    @MockBean
    private ProductService productService;
    @Autowired
    private MockMvc mockMvc;
    private final ObjectMapper objectMapper = new ObjectMapper();
    private static final String GENERIC_URL ="http://localhost:8080/products";
    @Test
    void retrieveAllProducts() throws Exception {
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("recordCount",2);
        JSONObject[] products = new JSONObject[6];

        products[0] = new JSONObject();
        products[0].put( "id","1e0a3b5d-9192-4fcb-a67e-0be51e27b361");
        products[0].put("name","Puma");
        products[0].put("description","manufactured by nike and comfortable");

        products[1] = new JSONObject();
        products[1].put("id","73b6345e-afd1-4dd1-86a8-34a69c79deba");
        products[1].put(  "name","Nike Shoes");
        products[1].put( "description","manufactured by nike and very comfortable");

        jsonObject.put("response",products);

        List<ProductResponse> productResponses = List.of(new ProductResponse(
                                             "1e0a3b5d-9192-4fcb-a67e-0be51e27b361",
                                           "Puma",
                                       "manufactured by nike and comfortable"),

                                                new ProductResponse(
                                                        "73b6345e-afd1-4dd1-86a8-34a69c79deba",
                                                        "Nike Shoes",
                                                        "manufactured by nike and very comfortable"));

        PagingResponse<List<ProductResponse>> pagingResponse = new PagingResponse<>(2,productResponses);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(GENERIC_URL).accept(MediaType.APPLICATION_JSON);
        when(productService.retrieveAllProducts()).thenReturn(productResponses);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        Assertions.assertEquals(200,result.getResponse().getStatus());
        JSONAssert.assertEquals(objectMapper.writeValueAsString(jsonObject),result.getResponse().getContentAsString(),false);
    }

    @Test
    void retrieveProductById() throws Exception
    {
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id","73b6345e-afd1-4dd1-86a8-34a69c79deba");
        jsonObject.put( "name","Nike Shoes");
        jsonObject.put("description","manufactured by nike and very comfortable");

        ProductResponse productResponse = ProductResponse
                                        .builder()
                                        .id("73b6345e-afd1-4dd1-86a8-34a69c79deba")
                                        .name("Nike Shoes")
                                        .description("manufactured by nike and very comfortable")
                                        .build();
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(GENERIC_URL+"/"+productResponse.getId()).accept(MediaType.APPLICATION_JSON);
        when(productService.retrieveProductById(productResponse.getId())).thenReturn(productResponse);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        Assertions.assertEquals(200,result.getResponse().getStatus());
        JSONAssert.assertEquals(objectMapper.writeValueAsString(jsonObject),result.getResponse().getContentAsString(),false);
    }

    @Test
    void updateProductById() throws Exception
    {
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id","73b6345e-afd1-4dd1-86a8-34a69c79deba");
        jsonObject.put( "name","Nike Shoes");
        jsonObject.put("description","manufactured by nike and very comfortable");

        ProductResponse productResponse = ProductResponse
                .builder()
                .id("73b6345e-afd1-4dd1-86a8-34a69c79deba")
                .name("Nike Shoes")
                .description("manufactured by nike and very comfortable")
                .build();
        RequestBuilder requestBuilder = MockMvcRequestBuilders.put(GENERIC_URL+"/"+productResponse.getId()).accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON).content(jsonObject.toString());
        when(productService.updateProductById(eq(productResponse.getId()),any(ProductRequest.class))).thenReturn(productResponse);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        Assertions.assertEquals(202,result.getResponse().getStatus());
        JSONAssert.assertEquals(objectMapper.writeValueAsString(jsonObject),result.getResponse().getContentAsString(),false);
    }

    @Test
    void addProductIntoOrder() throws Exception
    {
        String orderId = "c5b88974-b2a8-44f4-9e22-ee6f7b2955e4";
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id","73b6345e-afd1-4dd1-86a8-34a69c79deba");
        jsonObject.put( "name","Nike Shoes");
        jsonObject.put("description","manufactured by nike and very comfortable");

        ProductResponse productResponse = ProductResponse
                .builder()
                .id("73b6345e-afd1-4dd1-86a8-34a69c79deba")
                .name("Nike Shoes")
                .description("manufactured by nike and very comfortable")
                .build();

        RequestBuilder requestBuilder = MockMvcRequestBuilders.post(GENERIC_URL+"/orders/"+orderId).accept(MediaType.APPLICATION_JSON).content(jsonObject.toString()).contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        Assertions.assertEquals(201,result.getResponse().getStatus());
        JSONAssert.assertEquals(jsonObject.toString(),result.getResponse().getContentAsString(),false);
    }
}




