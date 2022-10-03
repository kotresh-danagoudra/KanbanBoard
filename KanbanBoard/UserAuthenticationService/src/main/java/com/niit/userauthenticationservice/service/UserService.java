package com.niit.userauthenticationservice.service;


import com.niit.userauthenticationservice.domain.User;
import com.niit.userauthenticationservice.exception.InvalidCredentialsException;
import com.niit.userauthenticationservice.exception.UserAlreadyExistsException;
import com.niit.userauthenticationservice.exception.UserNotFoundException;

public interface UserService {

    User saveUser(User user) throws UserAlreadyExistsException;
    //user name and pwd is in db ot not, if not save
    User findByUserIdAndPassword(String userId, String password) throws InvalidCredentialsException;

    User forgotPassword(String emailId);

    User updatePassword(String emailId,String password) throws UserNotFoundException;



}
