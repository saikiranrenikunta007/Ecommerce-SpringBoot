package com.ecommercewebsite.ecommercewebsite.userEntity;

import com.ecommercewebsite.ecommercewebsite.config.jwt.service.JwtService;
import com.ecommercewebsite.ecommercewebsite.order.Service.OrderService;
import com.ecommercewebsite.ecommercewebsite.order.model.OrderResponse;
import com.ecommercewebsite.ecommercewebsite.product.model.ProductResponse;
import com.ecommercewebsite.ecommercewebsite.role.mapper.RoleMapper;
import com.ecommercewebsite.ecommercewebsite.role.model.RoleRequest;
import com.ecommercewebsite.ecommercewebsite.role.model.RoleResponse;
import com.ecommercewebsite.ecommercewebsite.user.controller.UserController;
import com.ecommercewebsite.ecommercewebsite.user.mapper.UserMapper;
import com.ecommercewebsite.ecommercewebsite.user.model.UserRequest;
import com.ecommercewebsite.ecommercewebsite.user.model.UserResponse;
import com.ecommercewebsite.ecommercewebsite.user.repository.UserRepository;
import com.ecommercewebsite.ecommercewebsite.user.service.UserService;
import com.ecommercewebsite.ecommercewebsite.useraddress.model.UserAddressResponse;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
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

import java.time.LocalDate;
import java.util.*;

import static org.mockito.Mockito.*;

@WebMvcTest(controllers = UserController.class)
@AutoConfigureMockMvc(addFilters = false)
@Slf4j
public class UserResourceUT {
    @MockBean
    private UserService userService;
    @MockBean
    private JwtService jwtService;
    @MockBean
    private UserMapper userMapper;
    @MockBean
    private UserRepository userRepository;
    @MockBean
    private OrderService orderService;
    @MockBean
    private RoleMapper roleMapper;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private MockMvc mockMvc;
    private static final String SPECIFIC_URL = "http://localhost:8080/users/07e21001-ac97-4ebc-aa02-9ae5af355ed8";
    private static final String GENERIC_URL = "http://localhost:8080/users";
    @Test
    void retrieveUsersDetailsById() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(SPECIFIC_URL).accept(MediaType.APPLICATION_JSON);
        String userId = "07e21001-ac97-4ebc-aa02-9ae5af355ed8";
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
        NodeBeanUser nodeBeanUser = new NodeBeanUser(userId,"Sagar","Kumar",
                       "9121750480","sl@gmail.com"
                                  ,new RoleResponse("Admin"));
        JsonNode node = objectMapper.valueToTree(nodeBeanUser);
        UserResponse userResponse = UserResponse
                .builder()
                .id("07e21001-ac97-4ebc-aa02-9ae5af355ed8")
                .emailId("sl@gmail.com")
                .firstName("Sagar")
                .dateOfBirth(LocalDate.of(2000, 11, 25))
                .mobileNumber("9121750480")
                .roleResponse(new RoleResponse("Admin"))
                .lastName("Kumar").build();
        when(userService.retrieveUserDetailsById(userId)).thenReturn(userResponse);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        System.out.println(userResponse.toString());
        Assertions.assertEquals(200, result.getResponse().getStatus());
        JSONAssert.assertEquals( objectMapper.writeValueAsString(node), result.getResponse().getContentAsString(), false);
    }

    @Test
    void retrieveAllUsers() throws Exception {

        objectMapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
        List<NodeBeanUser> nodeBeanUsers = new ArrayList<>();
        NodeBeanUser nodeBeanUser = new NodeBeanUser("07e21001-ac97-4ebc-aa02-9ae5af355ed8",
                          "Sagar", "Kumar","9121750480",
                           "sl@gmail.com",new RoleResponse("Admin"));
        nodeBeanUsers.add(nodeBeanUser);
        nodeBeanUser = new NodeBeanUser("895077bc-4d14-435d-b389-1c71b8c0338d", "Saikiran", "Renikunta","9121750480","ashokbandi2504@gmail.com",new RoleResponse("Admin"));
        nodeBeanUsers.add(nodeBeanUser);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(GENERIC_URL).accept(MediaType.APPLICATION_JSON);
        List<UserResponse> userResponses = new ArrayList<>();
        UserResponse userResponse = UserResponse
                .builder()
                .roleResponse(new RoleResponse("Admin"))
                .id("07e21001-ac97-4ebc-aa02-9ae5af355ed8")
                .firstName("Sagar")
                .lastName("Kumar")
                .emailId("sl@gmail.com")
                .mobileNumber("9121750480")
                .dateOfBirth(LocalDate.of(2000,11,25))
                .build();
        userResponses.add(userResponse);
                 userResponse = UserResponse
                .builder()
                .roleResponse(new RoleResponse("Admin"))
                .id("895077bc-4d14-435d-b389-1c71b8c0338d")
                .firstName("Saikiran")
                .lastName("Renikunta")
                .emailId("ashokbandi2504@gmail.com")
                .mobileNumber("9121750480")
                .dateOfBirth(LocalDate.of(2000,11,25))
                .build();
        userResponses.add(userResponse);
        when(userService.retrieveAllUsers()).thenReturn(userResponses);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        Assertions.assertEquals(200, result.getResponse().getStatus());
        JSONAssert.assertEquals(objectMapper.writeValueAsString(nodeBeanUsers), result.getResponse().getContentAsString(), false);
    }
    @Test
    void retrieveAllOrdersForUser() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(SPECIFIC_URL+"/orders").accept(MediaType.APPLICATION_JSON);
        String userId = "07e21001-ac97-4ebc-aa02-9ae5af355ed8";
        objectMapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
        List<NodeBeanOrder> nodeBeanOrders = new ArrayList<>();
        NodeBeanOrder nodeBeanOrder = new NodeBeanOrder("c5b88974-b2a8-44f4-9e22-ee6f7b2955e4",
                                                        "8f1bc539-a2bb-4440-9084-e9d9e6984010",
                                                        "INITIATED",
                                                        new HashSet<>(new HashSet<>(List.of(new ProductResponse("7b99bf54-f209-48ee-9900-ce71ff112451",
                                                                "Woodland", "manufactured by nike and comfortable")))),
                                                        new UserAddressResponse("3a903731-1240-4c13-9bb3-a701b2faa4be","Near PostOffice", "Elgandal","Karimnagar","Telangana","India")

        );
        nodeBeanOrders.add(nodeBeanOrder);
        nodeBeanOrder = new NodeBeanOrder("e77d89f6-6181-4286-b9b5-9a605a240009",
                "158fbf15-1a29-44d8-8388-79e2805a53cc",
                "ORDER CONFIRMED - DELIVERED WITH IN 3 DAYS",
                new HashSet<>(new HashSet<>(List.of(new ProductResponse("7b99bf54-f209-48ee-9900-ce71ff112451",
                        "Woodland", "manufactured by nike and comfortable")))),
                new UserAddressResponse("3a903731-1240-4c13-9bb3-a701b2faa4be","Near PostOffice", "Elgandal","Karimnagar","Telangana","India"));

        nodeBeanOrders.add(nodeBeanOrder);
        List<OrderResponse> orderResponses = new ArrayList<>();
        ProductResponse productResponse =   ProductResponse
                                            .builder()
                                            .description("manufactured by nike and comfortable")
                                            .name("Woodland")
                                            .id("7b99bf54-f209-48ee-9900-ce71ff112451")
                                            .build();
        UserAddressResponse userAddressResponse = UserAddressResponse
                                                  .builder()
                .id("3a903731-1240-4c13-9bb3-a701b2faa4be")
                .city("Karimnagar")
                .country("India")
                .state("Telangana")
                .addressLine1("Near PostOffice")
                .addressLine2("Elgandal")
                .build();
        OrderResponse orderResponse =   OrderResponse
                                        .builder()
                                        .id("c5b88974-b2a8-44f4-9e22-ee6f7b2955e4")
                                        .orderRefNo("8f1bc539-a2bb-4440-9084-e9d9e6984010")
                                        .products(new HashSet<>(Collections.singletonList(productResponse)))
                                        .userAddress(userAddressResponse)
                                        .deliveryStatus("INITIATED")
                                        .build();
        orderResponses.add(orderResponse);
       orderResponse =   OrderResponse
                .builder()
                .id("e77d89f6-6181-4286-b9b5-9a605a240009")
                .orderRefNo("158fbf15-1a29-44d8-8388-79e2805a53cc")
                .products(new HashSet<>(Arrays.asList(productResponse)))
                .userAddress(userAddressResponse)
                .deliveryStatus("ORDER CONFIRMED - DELIVERED WITH IN 3 DAYS")
                .build();
        orderResponses.add(orderResponse);
        System.out.println("OrderResponses::"+orderResponses.isEmpty());
        when(userService.retrieveAllOrders(userId)).thenReturn(orderResponses);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        System.out.println("Result::"+result.getResponse().getContentAsString());
        System.out.println("Mapper::"+objectMapper.writeValueAsString(nodeBeanOrders));

        Assertions.assertEquals(200, result.getResponse().getStatus());
        JSONAssert.assertEquals( objectMapper.writeValueAsString(nodeBeanOrders), result.getResponse().getContentAsString(), false);


    }
    @Test
    void deleteUserById() throws Exception {
        String userId = "07e21001-ac97-4ebc-aa02-9ae5af355ed8";
        RequestBuilder requestBuilder = MockMvcRequestBuilders.delete(SPECIFIC_URL).accept(MediaType.APPLICATION_JSON);

        doNothing().when(userService).deleteUser(userId);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        Assertions.assertEquals(204, result.getResponse().getStatus());
        verify(userService, times(1)).deleteUser(userId);
    }
   @Test
    void updateUserById() throws Exception
    {
        log.info("update method called");
        String userId = "07e21001-ac97-4ebc-aa02-9ae5af355ed8";
        UserRequest userRequest = new UserRequest("9121750480", "Sagar",
                "Kumar", null, "sl@gmail.com", "Sl@123456",
                roleMapper.create(new RoleRequest("Admin", "1001")));
        JsonNode node = objectMapper.valueToTree(userRequest);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.put(SPECIFIC_URL)
                                        .accept(MediaType.APPLICATION_JSON)
                                        .content(objectMapper.writeValueAsString(node))
                                        .contentType(MediaType.APPLICATION_JSON);
        log.info("RequestBuilder:: {}",requestBuilder);
        when(userService.updateUser(userRequest,userId)).thenReturn(null);
        log.info("when then completed");
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        log.info("Result:: {}",result.getResponse().getContentAsString());
        log.info("Result::{}",result.getResponse().getStatus());
        Assertions.assertEquals(202, result.getResponse().getStatus());
        log.info("lastStep");
        verify(userService).updateUser(any(UserRequest.class),eq(userId));
        log.info("verified");




    }

}
