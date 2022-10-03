//package com.niit.userKanbanBoardService.rabbitmq;
//
//import com.niit.userKanbanBoardService.domain.User;
//import com.niit.userKanbanBoardService.exception.UserAlreadyExistsException;
//import com.niit.userKanbanBoardService.exception.UserNotFoundException;
//import com.niit.userKanbanBoardService.service.UserService;
//import lombok.Data;
//import org.springframework.amqp.rabbit.annotation.RabbitListener;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//@Component
//@Data
//public class Consumer {
//    @Autowired
//    private UserService userService;
//    @RabbitListener(queues = "return_queue")
//    public void sendDataFromRabbitmq(TeamDTO teamDTO) throws UserAlreadyExistsException, UserNotFoundException {
//       User user = userService.getUserById(teamDTO.getEmailId());
//       user.setTeamList(teamDTO.getTeamList());
//       userService.updateTeam(user.getEmailId(), user.getTeamList());
//    }
//}
