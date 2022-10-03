package com.niit.userTeamService.rabbitmq;


import com.niit.userTeamService.domain.User;
import com.niit.userTeamService.exception.UserAlreadyExistsException;
import com.niit.userTeamService.exception.UserNotFoundException;
import com.niit.userTeamService.repository.UserTeamRepository;
import com.niit.userTeamService.service.UserTeamService;
import lombok.Data;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
@Data
@Component
public class Consumer {
    @Autowired
    private UserTeamService userService;
    @Autowired
    private UserTeamRepository userTeamRepository;
    @RabbitListener(queues = "user_queue")
    public void sendDataFromRabbitmq(UserDTO userDTO) throws UserAlreadyExistsException, UserNotFoundException {
        User user=new User();
        user.setEmailId(userDTO.getEmailId());
        user.setFullName(userDTO.getFullName());
        user.setPassword(userDTO.getPassword());
        user.setContact(userDTO.getContact());
        user.setImage(userDTO.getImage());
        user.setBoardTasks(userDTO.getBoardTasks());
        user.setTeamList(userDTO.getTeamList());
        if (userTeamRepository.findById(userDTO.getEmailId()).isEmpty())
        {
            userService.addUser(user);
        }
//        userService.getUserById(userDTO.getEmailId());
//        userService.addUser(user);


    }
}
