// package com.niit.userauthenticationservice.service;
//
// import com.niit.userauthenticationservice.domain.User;
// import com.niit.userauthenticationservice.exception.UserAlreadyExistsException;
// import com.niit.userauthenticationservice.repository.UserRepository;
// import org.junit.jupiter.api.AfterEach;
// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;
// import org.junit.jupiter.api.extension.ExtendWith;
// import org.mockito.InjectMocks;
// import org.mockito.Mock;
// import org.mockito.junit.jupiter.MockitoExtension;
//
// import java.util.Optional;
//
// import static org.junit.jupiter.api.Assertions.assertEquals;
// import static org.junit.jupiter.api.Assertions.assertThrows;
// import static org.mockito.ArgumentMatchers.any;
// import static org.mockito.Mockito.*;
// @ExtendWith(MockitoExtension.class)
// public class UserServiceTest {
//
//     @Mock
//     private UserRepository customerRepository;
//     @InjectMocks
//     private UserServiceImpl customerService;
//
//     private User customer;
//     @BeforeEach
//     public void setUp() {
//         customer = new User(1001, "abc@123");
//     }
//
//     @AfterEach
//     public void tearDown() {
//     customer = null;
//     }
//     @Test
//     public void givenCustomerToSaveReturnSavedCustomerSuccess() throws UserAlreadyExistsException {
//         when(customerRepository.findById(any())).thenReturn(Optional.ofNullable(null));
//         when(customerRepository.save(any())).thenReturn(customer);
//         assertEquals(customer,customerService.saveCustomer(customer));
//         verify(customerRepository,times(1)).findById(any());
//         verify(customerRepository,times(1)).save(any());
//     }
//     @Test
//     public void givenCustomerToSaveReturnSavedCustomerFailure() throws UserAlreadyExistsException {
//         when(customerRepository.findById(any())).thenReturn(Optional.ofNullable(customer));
//         assertThrows(UserAlreadyExistsException.class,()->customerService.saveCustomer(customer));
//         verify(customerRepository,times(1)).findById(any());
//         verify(customerRepository,times(0)).save(any());
//     }
//     @Test
//     public void givenCustomerLoginSuccessReturnRetrievedCustomer()
//     {
//         when(customerRepository.findByCustomerIdAndPassword(any(),any())).thenReturn(customer);
//         System.out.println(customer);
//         assertEquals(customer,customerRepository.findByCustomerIdAndPassword(customer.getCustomerId(),customer.getPassword()));
//         verify(customerRepository,times(1)).findByCustomerIdAndPassword(any(),any());
//     }
// }
