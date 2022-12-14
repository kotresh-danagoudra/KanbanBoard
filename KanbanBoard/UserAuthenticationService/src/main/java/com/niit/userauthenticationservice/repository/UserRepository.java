package com.niit.userauthenticationservice.repository;


import com.niit.userauthenticationservice.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,String> {
User findByEmailIdAndPassword(String emailId, String password);
}
