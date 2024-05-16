package com.ecommercewebsite.ecommercewebsite.orderEntity;

import com.ecommercewebsite.ecommercewebsite.category.model.CategoryRequest;
import com.ecommercewebsite.ecommercewebsite.config.jwt.service.JwtService;
import com.ecommercewebsite.ecommercewebsite.order.Service.OrderService;
import com.ecommercewebsite.ecommercewebsite.order.controller.OrderController;
import com.ecommercewebsite.ecommercewebsite.order.model.OrderPaymentRequest;
import com.ecommercewebsite.ecommercewebsite.order.model.OrderPaymentResponse;
import com.ecommercewebsite.ecommercewebsite.order.model.OrderRequest;
import com.ecommercewebsite.ecommercewebsite.order.model.OrderResponse;
import com.ecommercewebsite.ecommercewebsite.payment.model.PaymentProcessingRequest;
import com.ecommercewebsite.ecommercewebsite.product.model.ProductRequest;
import com.ecommercewebsite.ecommercewebsite.product.model.ProductResponse;
import com.ecommercewebsite.ecommercewebsite.useraddress.model.UserAddressRequest;
import com.ecommercewebsite.ecommercewebsite.useraddress.model.UserAddressResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import lombok.ToString;
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
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.*;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@WebMvcTest(controllers = {OrderController.class})
@AutoConfigureMockMvc(addFilters = false)
@Slf4j
class ControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private JwtService jwtService;
    @MockBean
    private OrderService orderService;

    private final ObjectMapper objectMapper = new ObjectMapper();
    private static final String GENERIC_URL = "http://localhost:8080/orders";
    @Test
    void retrieveAllorders() throws Exception
    {

        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        JSONObject[] jsonObject = new JSONObject[1];
        jsonObject[0] = new JSONObject();
        jsonObject[0].put( "id","126aa826-1f2c-49f3-8137-e334759c4b74");
        jsonObject[0].put(  "orderRefNo","0c25b13e-5221-48c2-b404-4a35535d6ff3");
        jsonObject[0].put( "deliveryStatus","ORDER CONFIRMED - DELIVERED WITH IN 3 DAYS");

        JSONObject[] products = new JSONObject[1];

        products[0] = new JSONObject();
        products[0].put( "id","7b99bf54-f209-48ee-9900-ce71ff112451");
        products[0].put("name","Woodland");
        products[0].put("description","manufactured by nike and comfortable");
        jsonObject[0].put("products",products);

        JSONObject[] userAddress = new JSONObject[1];
        userAddress[0] = new JSONObject();
        userAddress[0].put( "id","26b06929-cfad-4183-8af8-4fd80e85a961");
        userAddress[0].put("addressLine1","Near PostOffice");
        userAddress[0].put("addressLine2","Elgandal ");
        userAddress[0].put( "city","Karimnagar");
        userAddress[0].put("state","Telangana");
        userAddress[0].put("country","India");
        jsonObject[0].put("userAddress",userAddress);

        Set<ProductResponse> productResponses = new HashSet<ProductResponse>(List.of(new ProductResponse(
                "7b99bf54-f209-48ee-9900-ce71ff112451",
                "Puma",
                "manufactured by nike and comfortable")));

        UserAddressResponse userAddressResponse = UserAddressResponse
                .builder()
                .id("26b06929-cfad-4183-8af8-4fd80e85a961")
                .addressLine1("Near PostOffice")
                .addressLine2("Elgandal ")
                .state("Telangana")
                .country("India")
                .city("Karimnagar")
                .build();
        List<OrderResponse> orderResponses = new ArrayList<>();
        orderResponses.add(
                new OrderResponse("126aa826-1f2c-49f3-8137-e334759c4b74","0c25b13e-5221-48c2-b404-4a35535d6ff3","ORDER CONFIRMED - DELIVERED WITH IN 3 DAYS",productResponses,userAddressResponse)
        );
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(GENERIC_URL).accept(MediaType.APPLICATION_JSON);
        when(orderService.retrieveAllOrders()).thenReturn(orderResponses);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        Assertions.assertEquals(200,result.getResponse().getStatus());
        JSONAssert.assertEquals(objectMapper.writeValueAsString(jsonObject),result.getResponse().getContentAsString(),false);

    }
    @Test
    void updateOrderById() throws Exception
    {

        Set<ProductResponse> productResponses = new HashSet<ProductResponse>(List.of(new ProductResponse(
                "7b99bf54-f209-48ee-9900-ce71ff112451",
                "Puma",
                "manufactured by nike and comfortable")));

        UserAddressResponse userAddressResponse = UserAddressResponse
                .builder()
                .id("26b06929-cfad-4183-8af8-4fd80e85a961")
                .addressLine1("Near PostOffice")
                .addressLine2("Elgandal ")
                .state("Telangana")
                .country("India")
                .city("Karimnagar")
                .build();
        OrderResponse orderResponse =  new OrderResponse("a9445810-8955-4581-90bd-2146710cab81",
                                                 "0c25b13e-5221-48c2-b404-4a35535d6ff3",
                                                "ORDER CONFIRMED - DELIVERED WITH IN 3 DAYS",
                                                             productResponses,
                                                             userAddressResponse);


        RequestBuilder requestBuilder = MockMvcRequestBuilders.put(GENERIC_URL+"/"+orderResponse.getId()).accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(orderResponse));
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        Assertions.assertEquals(202,result.getResponse().getStatus());
        verify(orderService).updateOrderById(eq(orderResponse.getId()),any(OrderRequest.class));
    }
    @Test
    void orderInitiation() throws Exception
    {
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
         JSONObject jsonObject = new JSONObject();
         jsonObject.put( "orderId","a9445810-8955-4581-90bd-2146710cab81");
         jsonObject.put("amount",4500.0);
         jsonObject.put("orderRefNo", "64cd5b57-9430-43a3-b0f4-0af72433b379");

        OrderPaymentResponse orderPaymentResponse = OrderPaymentResponse
                                                    .builder()
                                                    .orderId("a9445810-8955-4581-90bd-2146710cab81")
                                                    .orderRefNo("64cd5b57-9430-43a3-b0f4-0af72433b379")
                                                    .amount(4500.0)
                                                    .build();
        RequestBuilder requestBuilder = MockMvcRequestBuilders.post(GENERIC_URL).accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON).content(jsonObject.toString());
        when(orderService.orderInitiation(any(OrderPaymentRequest.class))).thenReturn(orderPaymentResponse);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        Assertions.assertEquals(201,result.getResponse().getStatus());
        JSONAssert.assertEquals(objectMapper.writeValueAsString(jsonObject),result.getResponse().getContentAsString(),false);
    }
    @Test
    void orderProcessing() throws Exception
    {
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS,false);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("paymentId","0057d0a6-5984-4b4d-95a4-c20c26749b03");
        jsonObject.put("paymentRefNo","388c5cd7-5751-4c99-ad37-63b4da5bed1a");
        RequestBuilder requestBuilder= MockMvcRequestBuilders.post(GENERIC_URL+"/"+"8f9e00dd-2f3c-4f2d-af3b-b507c841d4e3"+"/"+"order-confirmation")
                .accept(MediaType.APPLICATION_JSON).content(jsonObject.toString()).contentType(MediaType.APPLICATION_JSON);
        when(orderService.orderProcessing(anyString(),any(PaymentProcessingRequest.class))).thenReturn(null);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        Assertions.assertEquals(201,result.getResponse().getStatus());
        Assertions.assertTrue(Objects.requireNonNull(result.getResponse().getHeader("location")).contains(GENERIC_URL+"/"+"8f9e00dd-2f3c-4f2d-af3b-b507c841d4e3"+"/"+"order-confirmation"));
    }
    @Test
    void deleteOrderById() throws Exception {
        String orderId = "94511600-e599-4a76-a2ad-864735f5e31b";
        RequestBuilder requestBuilder = MockMvcRequestBuilders.delete(GENERIC_URL + "/" + orderId).accept(MediaType.APPLICATION_JSON);
        OrderResponse orderResponse = OrderResponse
                .builder()
                .id("94511600-e599-4a76-a2ad-864735f5e31b")
                .orderRefNo("449ba710-f86f-4647-99c0-d6815cc58446")
                .deliveryStatus("Available")
                .userAddress(new UserAddressResponse("6ecc1e44-1316-4487-b45c-10dcc170f055","ST2-1006","HNo-45/8/5","8InclineColony","GDK","India"))
                .products(new HashSet<>(Arrays.asList(new ProductResponse("c1c0edf3-cccd-45af-91ac-3e3dfa58c6b5","Samsung","Hard"),
                        new ProductResponse("f9300a80-3e0b-4bc4-b770-cdb25f973b9e","Sony","Sound"))))
                .build();
        doNothing().when(orderService).deleteOrderById(orderId);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        Assertions.assertEquals(204, result.getResponse().getStatus());
        verify(orderService, times(1)).deleteOrderById(orderId);
    }


    @Test
    void test0() {
        System.out.println("hello world");
    }







}


