package com.ecommercewebsite.ecommercewebsite.payment;

import com.ecommercewebsite.ecommercewebsite.config.jwt.service.JwtService;

import com.ecommercewebsite.ecommercewebsite.payment.controller.PaymentController;
import com.ecommercewebsite.ecommercewebsite.payment.model.PaymentRequest;
import com.ecommercewebsite.ecommercewebsite.payment.model.PaymentResponse;
import com.ecommercewebsite.ecommercewebsite.payment.service.PaymentService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
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

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@WebMvcTest(controllers = { PaymentController.class })
@AutoConfigureMockMvc(addFilters = false)
@Slf4j
class ControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private JwtService jwtService;
    @MockBean
    private PaymentService paymentService;
    private final ObjectMapper objectMapper = new ObjectMapper();
    private static final String GENERIC_URL = "http://localhost:8080/payments";

    @Test
    void paymentInitiation() throws Exception
    {
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS,false);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put( "paymentId","6e5111af-e044-487d-8fbd-b098ce9fb5f8");
        jsonObject.put(  "paymentRefNo","4f1c409c-ab8a-42a8-844f-b81e644e71d6");
        jsonObject.put("status","SUCCESSFUL");

        PaymentResponse paymentResponse = new PaymentResponse(
                "6e5111af-e044-487d-8fbd-b098ce9fb5f8",
                "4f1c409c-ab8a-42a8-844f-b81e644e71d6",
                "SUCCESSFUL"
                );
        PaymentRequest paymentRequest = new PaymentRequest("a9445810-8955-4581-90bd-2146710cab81",450,200,4000.0,"upi");
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                                        .post(GENERIC_URL).accept(MediaType.APPLICATION_JSON)
                                        .content(objectMapper.writeValueAsString(paymentRequest))
                                        .contentType(MediaType.APPLICATION_JSON);
        when(paymentService.paymentInitiation(any(PaymentRequest.class))).thenReturn(paymentResponse);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        log.info("result status:: {} response:: {}",result.getResponse().getStatus(),result.getResponse().getContentAsString());
        Assertions.assertEquals(201,result.getResponse().getStatus());
        JSONAssert.assertEquals(jsonObject.toString(),result.getResponse().getContentAsString(),false);
    }
}
