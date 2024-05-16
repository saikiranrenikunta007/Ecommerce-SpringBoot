package com.ecommercewebsite.ecommercewebsite.api;

import com.ecommercewebsite.ecommercewebsite.apiintegration.ApiIntegrationController;
import com.ecommercewebsite.ecommercewebsite.apiintegration.ApiIntegrationRequest;
import com.ecommercewebsite.ecommercewebsite.apiintegration.ApiIntegrationService;
import com.ecommercewebsite.ecommercewebsite.config.jwt.service.JwtService;
import com.ecommercewebsite.ecommercewebsite.user.model.UserRequest;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@Slf4j
@WebMvcTest(controllers = ApiIntegrationController.class)
@AutoConfigureMockMvc(addFilters = false)
public class ApiIntegrationUnitTest {
    @MockBean
    private ApiIntegrationService apiIntegrationService;

    @MockBean
    private JwtService jwtService;
    @Autowired
    private MockMvc mockMvc;

    private final ObjectMapper objectMapper = new ObjectMapper();

    private final String GENERIC_URL = "http://localhost:8080/api-integration/users";
    @Test
    void getAllusers() throws Exception
    {
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(GENERIC_URL).accept(MediaType.APPLICATION_JSON);
        JSONObject[] jsonObject = new JSONObject[3];
        jsonObject[0] = new JSONObject();
        jsonObject[0].put("id",1);
        jsonObject[0].put("name","Leanne Graham");
        jsonObject[1] = new JSONObject();
        jsonObject[1].put("id","2");
        jsonObject[1].put("name","Ervin Howell");
        jsonObject[2] = new JSONObject();
        jsonObject[2].put("id",3);
        jsonObject[2].put("name","Clementine Bauch");
        JsonNode node = objectMapper.valueToTree(jsonObject);
        when(apiIntegrationService.getUsers()).thenReturn(objectMapper.writeValueAsString(node));
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        Assertions.assertEquals(200,result.getResponse().getStatus());
        JSONAssert.assertEquals(objectMapper.writeValueAsString(node),result.getResponse().getContentAsString(),false);
    }

    @Test
    void addUser() throws Exception
    {

        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name","saikiran");
        jsonObject.put("username","saikiran2511");
        jsonObject.put("email","saikiranrenikunta@gmail.com");
        jsonObject.put("id",11);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.post(GENERIC_URL).accept(MediaType.APPLICATION_JSON).
                content(jsonObject.toString()).contentType(MediaType.APPLICATION_JSON);
        when(apiIntegrationService.addUser(any())).thenReturn(null);
        MvcResult result=mockMvc.perform(requestBuilder).andReturn();
        Assertions.assertEquals(201,result.getResponse().getStatus());
        assertTrue(Objects.requireNonNull(result.getResponse().getHeader("location")).contains("api-integration/users"));
    }

    @Test
    void deleteUser() throws Exception
    {
        String userId = "1";
        RequestBuilder requestBuilder = MockMvcRequestBuilders.delete(GENERIC_URL+"/"+userId).accept(MediaType.APPLICATION_JSON);

        doNothing().when(apiIntegrationService).deleteUser(userId);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        Assertions.assertEquals(204, result.getResponse().getStatus());
        verify(apiIntegrationService, times(1)).deleteUser(userId);
    }

    @Test
    void updateUser() throws Exception
    {
        String userId = "1";
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name","saikiran");
        jsonObject.put("username","saikiran2511");
        jsonObject.put("email","saikiranrenikunta@gmail.com");
        jsonObject.put("id",11);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.put(GENERIC_URL+"/"+userId)
                .content(jsonObject.toString()).contentType(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        Assertions.assertEquals(202,result.getResponse().getStatus());
        verify(apiIntegrationService).updateUser(userId);
    }
}
