package com.niit.userKanbanBoardService.service;

import com.niit.userKanbanBoardService.domain.Team;
import com.niit.userKanbanBoardService.domain.User;
import com.niit.userKanbanBoardService.exception.TeamAlreadyExistsException;
import com.niit.userKanbanBoardService.exception.UserAlreadyExistsException;
import com.niit.userKanbanBoardService.exception.UserNotFoundException;
import com.niit.userKanbanBoardService.repository.UserRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Data
@Service
public class UserTeamServiceImpl implements UserTeamService{

    private UserRepository userTeamRepository;

    @Autowired
    public UserTeamServiceImpl(UserRepository userTeamRepository) {
        this.userTeamRepository = userTeamRepository;
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
                    return userTeamRepository.save(user);
                }

            }
        }
        return userTeamRepository.save(user);
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

    @Override
    public User updateTeam(String emailId, Team team) throws UserNotFoundException {
        User user = userTeamRepository.findById(emailId).get();
        List<Team> teams = user.getTeamList();
        if(userTeamRepository.findById(emailId).isEmpty()){
            throw new UserNotFoundException();
        }
        else if(user.getTeamList()!=null) {

            for (int i = 0; i < teams.size(); i++) {
                if (teams.get(i).getTeamId().equals(team.getTeamId())) {
                    int j = teams.get(i).getMemberList().size();
                    int k = 0;
                    while (k < team.getMemberList().size()) {
                        teams.get(i).getMemberList().add(j, team.getMemberList().get(k));
                        j++;
                        k++;
                    }


                } else {
                    user.getTeamList().add(team);
                }
                break;

            }
        }

            return userTeamRepository.save(user);
        }

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

        return users;
    }

}
