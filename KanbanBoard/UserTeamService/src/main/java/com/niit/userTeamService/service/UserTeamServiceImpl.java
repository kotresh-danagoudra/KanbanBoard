package com.niit.userTeamService.service;

import com.niit.userTeamService.domain.Team;
import com.niit.userTeamService.domain.User;
import com.niit.userTeamService.exception.TeamAlreadyExistsException;
import com.niit.userTeamService.exception.UserAlreadyExistsException;
import com.niit.userTeamService.exception.UserNotFoundException;
import com.niit.userTeamService.rabbitmq.Producer;
import com.niit.userTeamService.rabbitmq.TeamDTO;
import com.niit.userTeamService.repository.UserTeamRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Data
@Service
public class UserTeamServiceImpl implements UserTeamService{

    private UserTeamRepository userTeamRepository;
    private Producer producer;
    @Autowired
//    public UserTeamServiceImpl(UserTeamRepository userTeamRepository) {
//        this.userTeamRepository = userTeamRepository;
//    }
    public UserTeamServiceImpl(UserTeamRepository userTeamRepository, Producer producer) {
        this.userTeamRepository = userTeamRepository;
        this.producer = producer;
    }

    @Override
    public User addUser(User user) throws UserAlreadyExistsException {
        if(userTeamRepository.findById(user.getEmailId()).isPresent())
        {
            System.out.println("got error");
            throw new UserAlreadyExistsException();
        }
        return userTeamRepository.save(user);
    }
    @Override
    public User getUserById(String emailId) throws UserNotFoundException {
        if(userTeamRepository.findById(emailId).isEmpty())
        {
            throw new UserNotFoundException();
        }
        return userTeamRepository.findById(emailId).get();
    }
    @Override
    public User addTeam(String emailId, Team team) throws UserNotFoundException, Exception {
        if(userTeamRepository.findById(emailId).isEmpty())
        {
            throw new UserNotFoundException();
        }
        User user = userTeamRepository.findById(emailId).get();
        if(user.getTeamList()== null)
        {
            user.setTeamList(Arrays.asList(team));
        }
        else {
            List<Team> tasks = user.getTeamList();
            for(int i=0; i< tasks.size(); i++){
                if(tasks.get(i).getTeamId().equals(team.getTeamId())){
                    throw new TeamAlreadyExistsException();
                }
                else {
                    tasks.add(team);
                    user.setTeamList(tasks);
                }

            }
        }
        User users = userTeamRepository.save(user);
        sendTeam(users);

        return users;
    }

    @Override
    public List<Team> getATeamOfUser(String emailId) throws UserNotFoundException {
        if (userTeamRepository.findById(emailId).isEmpty())
         {
             throw new UserNotFoundException();
        }
        User user = userTeamRepository.findById(emailId).get();
        return user.getTeamList();
    }

//    @Override
//    public User updateTeam(String emailId, Team team) throws UserNotFoundException {
//        List<Team> teams = getATeamOfUser(emailId);
//        User user = userTeamRepository.findById(emailId).get();
//        if(userTeamRepository.findById(emailId).isEmpty()){
//            throw new UserNotFoundException();
//        }
//        else {
////            user.getTeamList().removeIf(x->x.getTeamId()== team.getTeamId());
////            user.getTeamList().add(team);
//
//            return userTeamRepository.save(user);
//        }
//    }

    @Override
    public User deleteTeam(String emailId, String teamId) throws UserNotFoundException, Exception {
        boolean isPresent = false;
        if(userTeamRepository.findById(emailId).isEmpty())
        {
            throw new UserNotFoundException();
        }
        User user = userTeamRepository.findById(emailId).get();
        List<Team> teams = user.getTeamList();
        isPresent = teams.removeIf(x->x.getTeamId().equals(teamId));
        if(!isPresent){
            throw new UserNotFoundException();
        }
        user.setTeamList(teams);
        User users = userTeamRepository.save(user);
        sendTeam(users);

        return users;
    }

    public void sendTeam(User user) throws Exception{
        TeamDTO teamDTO = new TeamDTO();
        teamDTO.setEmailId(user.getEmailId());
        teamDTO.setTeamList(user.getTeamList());
        producer.sendMessageToRabbitMq(teamDTO);
    }

}
