package com.niit.userKanbanBoardService.controller;

import com.niit.userKanbanBoardService.domain.User;
import com.niit.userKanbanBoardService.domain.Board;
import com.niit.userKanbanBoardService.exception.NotFoundException;
import com.niit.userKanbanBoardService.exception.UserAlreadyExistsException;
import com.niit.userKanbanBoardService.exception.UserNotFoundException;
import com.niit.userKanbanBoardService.service.UserService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Data
@RestController
@RequestMapping("/api/admin/")

public class UserKanbanController {
private UserService userService;
private ResponseEntity<?> responseEntity;
@Autowired
    public UserKanbanController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> saveUser(@RequestBody User user)  throws UserAlreadyExistsException {
        try{
            System.out.println("save user Controller is invoked");
            responseEntity = new ResponseEntity<>(userService.addUser(user), HttpStatus.CREATED);
        }
        catch(UserAlreadyExistsException e){
            throw new UserAlreadyExistsException();
        }
        return responseEntity;

    }
    @PutMapping("/updateImage/{emailId}")
    public ResponseEntity<?> updateImage(@RequestParam("myFile") MultipartFile image, @PathVariable String emailId) throws IOException {
        responseEntity = new ResponseEntity<>(userService.updateImage(emailId, image.getBytes()), HttpStatus.CREATED);
        return responseEntity;

    }

    @PostMapping("/task/updateUser/{emailId}")
    public ResponseEntity<?> updateUserById(@RequestBody User user, @PathVariable String emailId) throws UserNotFoundException {
        try
        {
            responseEntity = new ResponseEntity<>(userService.updateUser(emailId,user), HttpStatus.OK);
        }
        catch (UserNotFoundException e)
        {
            throw new UserNotFoundException();
        }
        return responseEntity;
    }
    @PostMapping("/task/updatePassword/{emailId}")
    public ResponseEntity<?> updatePasswordOfAUser(@RequestBody String password, @PathVariable String emailId) throws UserNotFoundException {

        return new ResponseEntity<>(userService.updatePassword(emailId,password), HttpStatus.OK);
    }
    @GetMapping("/task/getUserById/{emailId}")
    public ResponseEntity<?> getUserById(@PathVariable String emailId) throws UserNotFoundException {
        try
        {
            responseEntity = new ResponseEntity<>(userService.getUserById(emailId), HttpStatus.OK);
        }
        catch (UserNotFoundException e)
        {
            throw new UserNotFoundException();
        }
        return responseEntity;
    }


    @PostMapping("/task/addTask/{emailId}")
    public ResponseEntity<?> saveTaskForUser(@RequestBody Board boardTask, @PathVariable String emailId) throws UserNotFoundException {
        try
        {
            responseEntity = new ResponseEntity<>(userService.addTaskForUser(emailId,boardTask),HttpStatus.CREATED);
        }
        catch (UserNotFoundException | NotFoundException e)
        {
            throw new UserNotFoundException();
        }
        return responseEntity;

    }


    @DeleteMapping("/task/deleteTask/{emailId}/{taskId}")
    public ResponseEntity<?> deleteTaskForUser(@PathVariable String emailId ,@PathVariable int taskId) throws UserNotFoundException {
        try
        {
            responseEntity = new ResponseEntity<>(userService.deleteTaskForUser(emailId,taskId),HttpStatus.OK);
        }
        catch (UserNotFoundException e)
        {
            throw new UserNotFoundException();
        }
        return responseEntity;
    }

    @PutMapping("/task/increaseStatus/{emailId}")
    public ResponseEntity<?> increaseTaskStatusForAUser(@RequestBody Board boardTask,@PathVariable String emailId) throws UserNotFoundException {
        try
        {
            responseEntity = new ResponseEntity<>(userService.increaseTaskStatusForUser(emailId, boardTask),HttpStatus.OK);
        }
        catch (UserNotFoundException e)
        {
            throw new UserNotFoundException();
        }
        return responseEntity;
    }

    @PutMapping("/task/decreaseStatus/{emailId}")
    public ResponseEntity<?> decreaseTaskStatusForAUser(@RequestBody Board boardTask,@PathVariable String emailId) throws UserNotFoundException {
        try
        {
            responseEntity = new ResponseEntity<>(userService.decreaseTaskStatusForUser(emailId, boardTask),HttpStatus.OK);
        }
        catch (UserNotFoundException e)
        {
            throw new UserNotFoundException();
        }
        return responseEntity;
    }

    @GetMapping("/task/getAllTaskForParticularUser/{emailId}")
    public ResponseEntity<?> getAllTaskForAParticularUser(@PathVariable String emailId) throws UserNotFoundException {
        try
        {
            responseEntity = new ResponseEntity<>(userService.getAllTasksForParticularUser(emailId),HttpStatus.OK);
        }
        catch (UserNotFoundException e)
        {
            throw new UserNotFoundException();
        }
        return responseEntity;

    }

    @PutMapping("/task/updateUser/{emailId}/{taskId}")
    public  ResponseEntity<?> updateUser(@PathVariable String emailId, @RequestBody Board boardTask) throws UserNotFoundException {
        try
        {
            responseEntity =  new ResponseEntity<>(userService.updateBoardTaskForUser(emailId, boardTask),HttpStatus.OK);
        }
        catch (UserNotFoundException e)
        {
            throw new UserNotFoundException();
        }
        return responseEntity;
    }

    @PutMapping("/task/priority/{emailId}/{priority}")
    public ResponseEntity<?> changeTaskPriority(@RequestBody Board boardTask,@PathVariable String emailId,String priority) throws UserNotFoundException {
        try
        {
            responseEntity =  new ResponseEntity<>(userService.changeTaskPriorityForUser(emailId, boardTask, priority),HttpStatus.OK);
        }
        catch (UserNotFoundException e)
        {
            throw new UserNotFoundException();
        }
        return responseEntity;
    }

    @PutMapping("/task/changeStatus/{emailId}/{status}")
    public ResponseEntity<?> changeTaskStatusForAUser(@RequestBody Board boardTask,@PathVariable String emailId,@PathVariable int status) throws UserNotFoundException {
        try
        {
            responseEntity =  new ResponseEntity<>(userService.changeTaskStatusForUser(emailId, boardTask,status),HttpStatus.OK);
        }
        catch (UserNotFoundException e)
        {
            throw new UserNotFoundException();
        }
        return responseEntity;
    }

}




