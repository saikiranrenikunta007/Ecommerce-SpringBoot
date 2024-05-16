package com.ecommercewebsite.ecommercewebsite.useraddress;

import com.ecommercewebsite.ecommercewebsite.comment.model.CommentRequest;
import com.ecommercewebsite.ecommercewebsite.comment.model.CommentResponse;
import com.ecommercewebsite.ecommercewebsite.config.jwt.service.JwtService;
import com.ecommercewebsite.ecommercewebsite.useraddress.controller.UserAddressController;
import com.ecommercewebsite.ecommercewebsite.useraddress.model.UserAddressRequest;
import com.ecommercewebsite.ecommercewebsite.useraddress.model.UserAddressResponse;
import com.ecommercewebsite.ecommercewebsite.useraddress.service.UserAddressService;
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
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
@WebMvcTest(controllers = UserAddressController.class)
@AutoConfigureMockMvc(addFilters = false)
public class ControllerTest {
    @MockBean
    private JwtService jwtService;
    @MockBean
    private UserAddressService userAddressService;
    @Autowired
    private MockMvc mockMvc;
    private static final String GENERIC_URL = "http://localhost:8080/users/b724c823-efcf-4898-915f-ab4bfad1e7cf/user-addresses";
    private static final String SPECIFIC_URL = "http://localhost:8080/users/b724c823-efcf-4898-915f-ab4bfad1e7cf/user-addresses/078405a6-d69e-4426-b16f-da7eb135acf7";
    @Test
    void adduserAddressForUser() throws Exception
    {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put( "id","26b06929-cfad-4183-8af8-4fd80e85a961");
        jsonObject.put("addressLine1","Near PostOffice");
        jsonObject.put("addressLine2","Elgandal ");
        jsonObject.put( "city","Karimnagar");
        jsonObject.put("country","India");
        jsonObject.put("state","Telangana");
        RequestBuilder requestBuilder = MockMvcRequestBuilders.post(GENERIC_URL).accept(MediaType.APPLICATION_JSON).content(jsonObject.toString()).contentType(MediaType.APPLICATION_JSON);
        UserAddressResponse userAddressResponse = UserAddressResponse
                                                  .builder()
                .id("26b06929-cfad-4183-8af8-4fd80e85a961")
                .addressLine1("Near PostOffice")
                .addressLine2("Elgandal ")
                .state("Telangana")
                .country("India")
                .city("Karimnagar")
                .build();
        when(userAddressService.addAddressforUser(anyString(),any(UserAddressRequest.class))).thenReturn(userAddressResponse);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        Assertions.assertEquals(201,result.getResponse().getStatus());
        JSONAssert.assertEquals(jsonObject.toString(),result.getResponse().getContentAsString(),false);

    }
    @Test
    void retrieveAllUserAddressesOfUser() throws Exception
    {
        JSONObject[] jsonObject = new JSONObject[2];
        jsonObject[0] = new JSONObject();
        jsonObject[0].put( "id","26b06929-cfad-4183-8af8-4fd80e85a961");
        jsonObject[0].put("addressLine1","Near PostOffice");
        jsonObject[0].put("addressLine2","Elgandal ");
        jsonObject[0].put( "city","Karimnagar");
        jsonObject[0].put("state","Telangana");
        jsonObject[0].put("country","India");



        jsonObject[1] = new JSONObject();
        jsonObject[1].put( "id","7b6273a1-6590-48f2-afab-60079f040fde");
        jsonObject[1].put("addressLine1","Near PostOffice");
        jsonObject[1].put("addressLine2","Elgandal ");
        jsonObject[1].put( "city","Karimnagar");
        jsonObject[1].put("state","Telangana");
        jsonObject[1].put("country","India");


        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(GENERIC_URL).accept(MediaType.APPLICATION_JSON);
        UserAddressResponse userAddressResponse1 = UserAddressResponse
                                                  .builder()
                .id("7b6273a1-6590-48f2-afab-60079f040fde")
                .city("Karimnagar")
                .country("India")
                .state("Telangana")
                .addressLine1("Near PostOffice")
                .addressLine2("Elgandal ")
                .build();
        UserAddressResponse userAddressResponse2 = UserAddressResponse
                .builder()
                .id("26b06929-cfad-4183-8af8-4fd80e85a961")
                .city("Karimnagar")
                .country("India")
                .state("Telangana")
                .addressLine1("Near PostOffice")
                .addressLine2("Elgandal ")
                .build();
        when(userAddressService.retrieveAllAddressesOfUser(anyString())).thenReturn(Arrays.asList(userAddressResponse2,userAddressResponse1));
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        Assertions.assertEquals(200,result.getResponse().getStatus());
        JSONAssert.assertEquals(Arrays.toString(jsonObject),result.getResponse().getContentAsString(),false);
    }
    @Test
    void updateUserAddressById() throws Exception
    {
        String userAddressId = "078405a6-d69e-4426-b16f-da7eb135acf7";
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("addressLine1","Near Hostel");
        jsonObject.put("addressLine2","Elgandal ");
        jsonObject.put( "city","Karimnagar");
        jsonObject.put("state","Telangana");
        jsonObject.put("country","India");
        RequestBuilder requestBuilder = MockMvcRequestBuilders.put(SPECIFIC_URL)
                .content(jsonObject.toString()).contentType(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        Assertions.assertEquals(202,result.getResponse().getStatus());
        verify(userAddressService).updateUserAddressById(eq(userAddressId),any(UserAddressRequest.class));
    }
    @Test
    void deleteAUserAddressById() throws Exception
    {
        String userAddressId = "078405a6-d69e-4426-b16f-da7eb135acf7";
        RequestBuilder requestBuilder = MockMvcRequestBuilders.delete(SPECIFIC_URL);
        doNothing().when(userAddressService).deleteUserAddressById(userAddressId);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        Assertions.assertEquals(204,result.getResponse().getStatus());
        verify(userAddressService).deleteUserAddressById(userAddressId);
    }

}
