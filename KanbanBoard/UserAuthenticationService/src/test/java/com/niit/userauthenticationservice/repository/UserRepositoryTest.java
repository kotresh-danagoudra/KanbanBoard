// package com.niit.userauthenticationservice.repository;
//
// import com.niit.userauthenticationservice.domain.User;
// import org.junit.jupiter.api.AfterEach;
// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;
// import org.junit.jupiter.api.extension.ExtendWith;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
// import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
// import org.springframework.test.context.junit.jupiter.SpringExtension;
//
//
//
// import static org.junit.jupiter.api.Assertions.assertEquals;
// @ExtendWith(SpringExtension.class)
// @DataJpaTest
// @AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
// public class UserRepositoryTest {
//
//     @Autowired
//     private UserRepository customerRepository;
//
//     private User customer;
//
//     @BeforeEach
//     public void setUp() throws Exception {
//         customer = new User(1001, "abc@123");
//
//     }
//
//     @AfterEach
//     public void tearDown() throws Exception {
//         customerRepository.deleteAll();
//     }
//
//
//     @Test
//     public void testSaveUserSuccess() {
//         customerRepository.save(customer);
//         User object = customerRepository.findById(customer.getCustomerId()).get();
//         assertEquals(customer.getCustomerId(), object.getCustomerId());
//     }
//
//     @Test
//     public void testLoginUserSuccess() {
//         customerRepository.save(customer);
//         User object = customerRepository.findById(customer.getCustomerId()).get();
//         assertEquals(customer.getCustomerId(), object.getCustomerId());
//     }
// }
