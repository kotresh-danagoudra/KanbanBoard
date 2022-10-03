//package com.niit.UserProductService.service;
//
//import com.niit.UserProductService.domain.User;
//import com.niit.UserProductService.domain.Vehicle;
//import com.niit.UserProductService.exception.UserAlreadyExistsException;
//import com.niit.UserProductService.repository.UserVehicleRepository;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import java.util.Arrays;
//import java.util.List;
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertThrows;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.*;
//
//@ExtendWith(MockitoExtension.class)
//public class UserProductServiceServiceTest
//{
//    @Mock
//    private UserVehicleRepository customerProductRepository;
//    @InjectMocks
//    private UserVehicleServiceImpl customerProductService;
//    private Vehicle product1,product2;
//    private User customer;
//    List<Vehicle> products;
//
//    @BeforeEach
//    public void setUp(){
//
//        product1 = new Vehicle(101,"Book", Arrays.asList("Hard Bind","Light Weight"),true);
//        product2 = new Vehicle(102,"Samsung Galaxy M30",Arrays.asList("8GB Ram","128GB Expandable Memory","6000mAh Battery"),true);
//
//
//        products = Arrays.asList(product1,product1);
//
//        customer = new User(1001,"Lohit Kumar","lohitk@gmail.com","Lom Street Bangalore","abc@123",products);
//    }
//    @AfterEach
//    public void tearDown()
//    {
//        customer=null;
//    }
//    @Test
//    public void givenCustomerToSaveReturnSavedCustomerSuccess() throws UserAlreadyExistsException {
//        when(customerProductRepository.findById(any())).thenReturn(Optional.ofNullable(null));
//        when(customerProductRepository.save(any())).thenReturn(customer);
//        assertEquals(customer, customerProductService.registerNewCustomer(customer));
//        verify(customerProductRepository, times(1)).findById(any());
//        verify(customerProductRepository, times(1)).save(any());
//    }
//
////    @Test
////    public void givenCustomerToSaveReturnSavedCustomerFailure() throws CustomerAlreadyExistsException
////    {
////        when(customerProductRepository.findById(any())).thenReturn(Optional.ofNullable(null));
////        assertThrows(CustomerAlreadyExistsException.class,()->customerProductService.registerNewCustomer(customer));
////        verify(customerProductRepository, times(1)).findById(any());
////        verify(customerProductRepository, times(1)).save(any());
////    }
//}
