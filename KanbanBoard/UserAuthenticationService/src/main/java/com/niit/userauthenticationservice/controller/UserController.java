package com.niit.userauthenticationservice.controller;


import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.niit.userauthenticationservice.domain.User;
import com.niit.userauthenticationservice.exception.InvalidCredentialsException;
import com.niit.userauthenticationservice.exception.UserAlreadyExistsException;
import com.niit.userauthenticationservice.exception.UserNotFoundException;
import com.niit.userauthenticationservice.security.SecurityTokenGenerator;
import com.niit.userauthenticationservice.service.UserService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Data
@RestController
@RequestMapping("/api/user")
public class UserController {

    private UserService userService;
    private SecurityTokenGenerator securityTokenGenerator;

    @Autowired
    public UserController(UserService userService, SecurityTokenGenerator securityTokenGenerator) {
        this.userService = userService;
        this.securityTokenGenerator = securityTokenGenerator;
    }
    @PostMapping("/registration")
    public ResponseEntity<?> saveUser(@RequestBody User user) throws UserAlreadyExistsException {
        return new ResponseEntity<>(userService.saveUser(user),HttpStatus.CREATED);
    }
    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody User user) throws InvalidCredentialsException
    {
        User retrievedUser = userService.findByUserIdAndPassword(user.getEmailId(),user.getPassword());

        if(retrievedUser==null)
        {
            throw new InvalidCredentialsException();
        }
        Map<String,String> map = securityTokenGenerator.generateToken(user);
        return new ResponseEntity<>(map,HttpStatus.OK);
    }
    @GetMapping("/forgotPassword/{emailId}")
    public ResponseEntity<?> forgotPassword(@PathVariable String emailId){
        return new ResponseEntity<>(userService.forgotPassword(emailId),HttpStatus.OK);
    }
    @PutMapping("/updateUser/{emailId}")
    public ResponseEntity<?> updatePasswordOfAUser(@RequestBody String password, @PathVariable String emailId) throws UserNotFoundException {

        return new ResponseEntity<>(userService.updatePassword(emailId,password), HttpStatus.OK);
    }

}
