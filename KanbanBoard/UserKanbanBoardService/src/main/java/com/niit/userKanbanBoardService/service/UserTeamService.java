package com.niit.userKanbanBoardService.service;



import com.niit.userKanbanBoardService.domain.Team;
import com.niit.userKanbanBoardService.domain.User;
import com.niit.userKanbanBoardService.exception.UserAlreadyExistsException;
import com.niit.userKanbanBoardService.exception.UserNotFoundException;

import java.util.List;

public interface UserTeamService
{
    public User getUserById(String emailId) throws UserNotFoundException;
    public User addUser(User user) throws UserAlreadyExistsException;
    public User addTeam(String emailId, Team team) throws UserNotFoundException, Exception;
    public List<Team> getATeamOfUser(String emailId) throws UserNotFoundException;
    public User updateTeam(String emailId, Team team) throws UserNotFoundException;
    public User deleteTeam(String emailId, String teamId) throws UserNotFoundException, Exception;
}
