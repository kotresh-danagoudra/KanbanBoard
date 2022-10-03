// package com.niit.userauthenticationservice.controller;
//
// import com.fasterxml.jackson.databind.ObjectMapper;
// import com.niit.userauthenticationservice.domain.User;
// import com.niit.userauthenticationservice.exception.UserAlreadyExistsException;
// import com.niit.userauthenticationservice.service.UserServiceImpl;
// import org.junit.jupiter.api.AfterEach;
// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;
// import org.junit.jupiter.api.extension.ExtendWith;
// import org.mockito.InjectMocks;
// import org.mockito.Mock;
// import org.mockito.junit.jupiter.MockitoExtension;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.MediaType;
// import org.springframework.test.web.servlet.MockMvc;
// import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
// import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//
// import static org.mockito.ArgumentMatchers.any;
// import static org.mockito.Mockito.when;
// import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
// import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
// @ExtendWith(MockitoExtension.class)
// public class UserControllerTest {
//     @Autowired
//     private MockMvc mockMvc;
//
//     @Mock
//     private UserServiceImpl customerService;
//     private User customer;
//
//     @InjectMocks
//     UserController customerController;
//
//     @BeforeEach
//     void setUp()
//     {
//         customer = new User(1001,"abc@123");
//         mockMvc = MockMvcBuilders.standaloneSetup(customerController).build();
//     }
//     @AfterEach
//     public void tearDown() {
//     customer = null;
//     }
//
//     @Test
//     public void givenCustomerToSaveReturnCustomerSuccess() throws Exception, UserAlreadyExistsException {
//         when(customerService.saveCustomer(any())).thenReturn(customer);
//         mockMvc.perform(post("/api/v1/customer")
//                 .contentType(MediaType.APPLICATION_JSON)
//                 .content(asJSONString(customer)))
//                 .andExpect(status().isCreated())
//                 .andDo(MockMvcResultHandlers.print());
//     }
//     @Test
//     public void givenUserToSaveReturnUserFailure() throws Exception, UserAlreadyExistsException {
//         when(customerService.saveCustomer(any())).thenThrow(UserAlreadyExistsException.class);
//         mockMvc.perform(post("/api/v1/customer")
//                 .contentType(MediaType.APPLICATION_JSON)
//                 .content(asJSONString(customer)))
//                 .andExpect(status().isConflict())
//                 .andDo(MockMvcResultHandlers.print());
//     }
//
//
////     @Test
////     public void testLoginUser() throws Exception, InvalidCredentialsException {
////         int customerId = 1001;
////         String password = "abc@123";
////         when(customerService.findByCustomerIdAndPassword(customerId,password)).thenReturn(customer);
////         mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/login")
////                 .contentType(MediaType.APPLICATION_JSON).content(asJSONString(customer)))
////                 .andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print());
////     }
//
//
//     private static String asJSONString(Object user) {
//         try {
//             return new ObjectMapper().writeValueAsString(user);
//         } catch (Exception e) {
//             throw new RuntimeException(e);
//         }
//     }
// }
