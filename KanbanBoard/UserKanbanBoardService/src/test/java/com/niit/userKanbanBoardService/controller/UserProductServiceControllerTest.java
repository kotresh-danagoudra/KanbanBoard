//package com.niit.UserProductService.controller;
//
// import com.fasterxml.jackson.databind.ObjectMapper;
// import com.niit.UserProductService.domain.User;
// import com.niit.UserProductService.domain.Vehicle;
// import com.niit.UserProductService.exception.UserAlreadyExistsException;
// import com.niit.UserProductService.exception.UserNotFoundException;
// import com.niit.UserProductService.exception.VehicleNotFoundException;
// import com.niit.UserProductService.service.UserVehicleServiceImpl;
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
// import java.util.Arrays;
// import java.util.List;
//
// import static org.mockito.ArgumentMatchers.any;
// import static org.mockito.Mockito.when;
// import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
// import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
// import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//
//@ExtendWith(MockitoExtension.class)
//public class UserProductServiceControllerTest
//{
//
// @Autowired
//     private MockMvc mockMvc;
//
// @Mock
//    private UserVehicleServiceImpl customerProductService;
//    private Vehicle product1,product2;
//    private User customer;
//    List<Vehicle> products;
//
// @InjectMocks
//     private UserVehicleController userProductController;
//
//     @BeforeEach
//     public void setUp(){
//
//         mockMvc = MockMvcBuilders.standaloneSetup(userProductController).build();
//         product1 = new Vehicle(101,"Book",Arrays.asList("Hard Bind","Light Weight"),true);
//         product2 = new Vehicle(102,"Samsung Galaxy M30",Arrays.asList("8GB Ram","128GB Expandable Memory","6000mAh Battery"),true);
//
//
//         products = Arrays.asList(product1,product1);
//
//         customer = new User(1001,"Lohit Kumar","lohitk@gmail.com","Lom Street Bangalore","abc@123",products);
//
//     }
//
//     @AfterEach
//     public void tearDown()
//     {
//         product1 = null;
//         product2 = null;
//         products = null;
//     }
//     @Test
//     public void registerCustomerSuccess() throws Exception {
//         when(customerProductService.registerNewCustomer(any())).thenReturn(customer);
//         mockMvc.perform(post("/api/v2/register")
//                 .contentType(MediaType.APPLICATION_JSON)
//                 .content(asJSONString(customer)))
//                 .andExpect(status().isCreated())
//                 .andDo(MockMvcResultHandlers.print());
//     }
//
//     @Test
//     public void registerCustomerFailure() throws Exception {
//         when(customerProductService.registerNewCustomer(customer)).thenThrow(UserAlreadyExistsException.class);
//         mockMvc.perform(post("/api/v2/register")
//                 .contentType(MediaType.APPLICATION_JSON)
//                 .content(asJSONString(customer)))
//                 .andExpect(status().isConflict())
//                 .andDo(MockMvcResultHandlers.print());
//     }
//
//     @Test
//     public void saveProductSuccess() throws Exception, UserNotFoundException {
//         when(customerProductService.saveCustomerProduct(any(),any())).thenReturn(customer);
//         mockMvc.perform(post("/api/v2/user/product/1001")
//                 .contentType(MediaType.APPLICATION_JSON)
//                 .content(asJSONString(customer)))
//                 .andExpect(status().isCreated())
//                 .andDo(MockMvcResultHandlers.print());
//     }
//
////     @Test
////     public void saveProductFailure() throws Exception, CustomerNotFoundException {
////         when(customerProductService.saveCustomerProduct(any(),any())).thenThrow(CustomerNotFoundException.class);
////         mockMvc.perform(post("/api/v2/user/product/1001")
////                 .contentType(MediaType.APPLICATION_JSON)
////                 .content(asJSONString(customer)))
////                 .andExpect(status().isNotFound())
////                 .andDo(MockMvcResultHandlers.print());
////     }
//
//     @Test
//     public void getAllProductTest() throws Exception, UserNotFoundException {
//         when(customerProductService.getAllProductsOfACustomer(1001)).thenReturn(products);
//         mockMvc.perform(get("/api/v2/user/product/1001")
//                 .contentType(MediaType.APPLICATION_JSON)
//                 .content(asJSONString(products)))
//                 .andExpect(status().isOk()).andDo(MockMvcResultHandlers.print());
//     }
//
//     @Test
//     public void givenProductCodeDeleteProductSuccess() throws Exception, VehicleNotFoundException, UserNotFoundException {
//         when(customerProductService.deleteProductOfACustomer(any(),any())).thenReturn(customer);
//         mockMvc.perform(delete("/api/v2/user/1001/101")
//                 .contentType(MediaType.APPLICATION_JSON))
//                 .andExpect(status().isOk())
//                 .andDo(MockMvcResultHandlers.print());
//
//     }
//     private static String asJSONString(Object user) {
//         try {
//             return new ObjectMapper().writeValueAsString(user);
//         } catch (Exception e) {
//             throw new RuntimeException(e);
//         }
//     }
// }
//
