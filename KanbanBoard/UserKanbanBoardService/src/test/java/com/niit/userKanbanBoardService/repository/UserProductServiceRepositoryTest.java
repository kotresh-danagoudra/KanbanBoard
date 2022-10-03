//package com.niit.UserProductService.repository;
// import com.niit.UserProductService.domain.User;
// import com.niit.UserProductService.domain.Vehicle;
// import com.niit.UserProductService.service.UserVehicleServiceImpl;
// import org.junit.jupiter.api.AfterEach;
// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;
// import org.junit.jupiter.api.extension.ExtendWith;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
// import org.springframework.test.context.junit.jupiter.SpringExtension;
//
// import java.util.Arrays;
// import java.util.List;
//
// import static org.junit.jupiter.api.Assertions.assertEquals;
// import static org.junit.jupiter.api.Assertions.assertNotNull;
//
//
//
//
// @ExtendWith(SpringExtension.class)
// @DataMongoTest
//public class UserProductServiceRepositoryTest
//{
//     @Autowired
//     private UserVehicleRepository customerProductRepository;
//     private UserVehicleServiceImpl customerProductService;
//     private Vehicle product1,product2;
//     private User customer;
//     List<Vehicle> products;
//
//     @BeforeEach
//     public void setUp(){
//
//         product1 = new Vehicle(101,"Book",Arrays.asList("Hard Bind","Light Weight"),true);
//         product2 = new Vehicle(102,"Samsung Galaxy M30",Arrays.asList("8GB Ram","128GB Expandable Memory","6000mAh Battery"),true);
//
//
//         products = Arrays.asList(product1,product1);
//
//         customer = new User(1001,"Lohit Kumar","lohitk@gmail.com","Lom Street Bangalore","abc@123",products);
//     }
//     @AfterEach
//     public void tearDown()
//     {
//         customerProductRepository.deleteAll();
//     }
//
//     @Test
//     public void registerNewCustomerSuccess()
//     {
//         customerProductRepository.insert(customer);
//         User cust = customerProductRepository.findById(customer.getCustomerId()).get();
//         assertNotNull(cust);
//         assertEquals(customer.getCustomerId(),cust.getCustomerId());
//     }
//
//     @Test
//     public void getProductsByCustomerIdSuccess()
//     {
//         customerProductRepository.insert(customer);
//         User cust = customerProductRepository.findById(customer.getCustomerId()).get();
//         List<Vehicle> list = cust.getProductList();
//         assertEquals(2,list.size());
//     }
//     @Test
//     public void deleteProductsCustomerIdSuccess()
//     {
//         customerProductRepository.insert(customer);
//         User cust = customerProductRepository.findById(customer.getCustomerId()).get();
//         List<Vehicle> list = cust.getProductList();
//         list.removeIf(r->r.getProductId()==101);
//         assertEquals(0,list.size());
//     }
//
//}
