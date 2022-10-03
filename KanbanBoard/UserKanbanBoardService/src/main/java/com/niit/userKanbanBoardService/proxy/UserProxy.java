package com.niit.userKanbanBoardService.proxy;

import com.niit.userKanbanBoardService.domain.User;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name="user-authentication-service",url = "localhost:8085")
public interface UserProxy
{
    @PostMapping("/api/user/registration")
    public ResponseEntity<?> saveUser(@RequestBody User user) ;


    @PutMapping("/api/user/updateUser/{emailId}")
    public ResponseEntity<?> updatePasswordOfAUser(@RequestBody String password, @PathVariable String emailId) ;

}
