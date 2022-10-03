package com.niit.userauthenticationservice.service;


import com.niit.userauthenticationservice.domain.User;
import com.niit.userauthenticationservice.exception.InvalidCredentialsException;
import com.niit.userauthenticationservice.exception.UserAlreadyExistsException;
import com.niit.userauthenticationservice.exception.UserNotFoundException;
import com.niit.userauthenticationservice.repository.UserRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Data
@Service
public class UserServiceImpl implements UserService {

private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Override
    public User saveUser(User user) throws UserAlreadyExistsException {
        if(userRepository.findById(user.getEmailId()).isPresent())
        {
            throw new UserAlreadyExistsException();
        }
        System.out.println(user);
        return userRepository.save(user);
    }

    @Override
    public User findByUserIdAndPassword(String emailId, String password) throws InvalidCredentialsException {
        System.out.println("emailId"+emailId);
        System.out.println("password"+password);
        User loggedInCustomer = userRepository.findByEmailIdAndPassword(emailId,password);
        System.out.println(loggedInCustomer);
        if(loggedInCustomer == null)
        {
            throw new InvalidCredentialsException();
        }

        return loggedInCustomer;
    }

    public User forgotPassword(String emailId){
        if(userRepository.findById(emailId).isPresent()){
            return userRepository.findById(emailId).get();
        }
        else {
            return null;
        }
    }

    @Override
    public User updatePassword(String emailId, String password) throws UserNotFoundException {
        System.out.println(emailId);
        if(userRepository.findById(emailId).isEmpty())
        {
            System.out.println("User Not Found");
            throw new UserNotFoundException();
        }
        User user = userRepository.findById(emailId).get();
        user.setPassword(password);
        return userRepository.save(user);
    }
}
