package com.ecommercewebsite.ecommercewebsite.category;

import com.ecommercewebsite.ecommercewebsite.category.controller.CategoryController;
import com.ecommercewebsite.ecommercewebsite.category.model.CategoryRequest;
import com.ecommercewebsite.ecommercewebsite.category.model.CategoryResponse;
import com.ecommercewebsite.ecommercewebsite.category.service.CategoryService;
import com.ecommercewebsite.ecommercewebsite.config.jwt.service.JwtService;
import com.ecommercewebsite.ecommercewebsite.product.model.ProductResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.mysql.cj.xdevapi.JsonArray;
import jdk.jfr.Category;
import org.h2.command.dml.MergeUsing;
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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@WebMvcTest(controllers = CategoryController.class)
@AutoConfigureMockMvc(addFilters = false)
public class ControllerTest {
    @MockBean
    private JwtService jwtService;
    @MockBean
    private CategoryService categoryService;
    @Autowired
    private MockMvc mockMvc;
    private static final String GENERIC_URL = "http://localhost:8080/categories";
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void retrieveAllCategories() throws Exception {
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        JSONObject[] jsonObjects = new JSONObject[2];
        jsonObjects[0] = new JSONObject();
        jsonObjects[0].put("id", "468d1f9d-aef9-495e-8b14-3fa155c2fd7a");
        jsonObjects[0].put("name", "shoes");
        jsonObjects[0].put("description", "All new Branded Shoes");
        jsonObjects[0].put("products", List.of());

        jsonObjects[1] = new JSONObject();
        jsonObjects[1].put("id", "56416291-de58-43c1-bc41-5695f0d78c74");
        jsonObjects[1].put("name", "shoes");
        jsonObjects[1].put("description", "All new Branded Shoes");


        JSONObject[] productJson = new JSONObject[1];
        productJson[0] = new JSONObject();
        productJson[0].put("id", "f171c0ff-aa14-4fce-92f1-7698417923f9");
        productJson[0].put("name", "Sony");
        productJson[0].put("description", "manufactured by saikiran");
        jsonObjects[1].put("products",Arrays.asList(productJson));
        System.out.println("Expected::");
        System.out.println(objectMapper.writeValueAsString(jsonObjects));

        List<CategoryResponse> categoryResponses = new ArrayList<>();
        CategoryResponse categoryResponse = CategoryResponse
                .builder()
                .id("468d1f9d-aef9-495e-8b14-3fa155c2fd7a")
                .name("shoes")
                .description("All new Branded Shoes")
                .products(List.of())
                .build();
        categoryResponses.add(categoryResponse);
        categoryResponse = CategoryResponse
                .builder()
                .id("56416291-de58-43c1-bc41-5695f0d78c74")
                .name("shoes")
                .description("All new Branded Shoes")
                .products(List.of(new ProductResponse("f171c0ff-aa14-4fce-92f1-7698417923f9","Sony","manufactured by saikiran")))
                .build();
        categoryResponses.add(categoryResponse);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(GENERIC_URL).accept(MediaType.APPLICATION_JSON);
        when(categoryService.retrieveAllCategories()).thenReturn(categoryResponses);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        Assertions.assertEquals(200,result.getResponse().getStatus());
        JSONAssert.assertEquals(objectMapper.writeValueAsString(jsonObjects),result.getResponse().getContentAsString(),false);

    }

    @Test
    void addCategory() throws Exception
    {
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS,false);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id","30db2430-3d6f-43ab-98a9-f7c1e813bad6");
        jsonObject.put("name", "Shoes");
        jsonObject.put("description","All about Shoes");
        jsonObject.put("products","[]");

        CategoryResponse categoryResponse = CategoryResponse.builder()
                .id("30db2430-3d6f-43ab-98a9-f7c1e813bad6")
                .name("Shoes")
                .description("All about Shoes")
                .products(new ArrayList<ProductResponse>())
                .build();
        RequestBuilder requestBuilder = MockMvcRequestBuilders.post(GENERIC_URL).accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON).content(jsonObject.toString());
        when(categoryService.addCategory(any(CategoryRequest.class))).thenReturn(categoryResponse);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        Assertions.assertEquals(201,result.getResponse().getStatus());
        JSONAssert.assertEquals(objectMapper.writeValueAsString(jsonObject),result.getResponse().getContentAsString(),false);
    }

    @Test
    void deleteAllCategories() throws Exception
    {
        RequestBuilder requestBuilder = MockMvcRequestBuilders.delete(GENERIC_URL).accept(MediaType.APPLICATION_JSON);
        doNothing().when(categoryService).deleteAllCategories();
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        Assertions.assertEquals(204,result.getResponse().getStatus());
        verify(categoryService).deleteAllCategories();
    }

    @Test
    void updateCategoryById() throws Exception
    {
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS,false);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id","30db2430-3d6f-43ab-98a9-f7c1e813bad6");
        jsonObject.put("name", "Shoes");
        jsonObject.put("description","All about Shoes");
        jsonObject.put("products","[]");


        CategoryResponse categoryResponse = CategoryResponse.builder()
                .id("30db2430-3d6f-43ab-98a9-f7c1e813bad6")
                .name("Shoes")
                .description("All about Shoes")
                .products(new ArrayList<ProductResponse>())
                .build();
        RequestBuilder requestBuilder = MockMvcRequestBuilders.put(GENERIC_URL+"/"+"30db2430-3d6f-43ab-98a9-f7c1e813bad6").accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON).content(jsonObject.toString());
        when(categoryService.updateCategoryById(anyString(),any(CategoryRequest.class))).thenReturn(categoryResponse);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        Assertions.assertEquals(202,result.getResponse().getStatus());
        JSONAssert.assertEquals(objectMapper.writeValueAsString(jsonObject),result.getResponse().getContentAsString(),false);
    }

}


