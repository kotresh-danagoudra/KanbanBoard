package com.niit.userTeamService.service;

import com.niit.userTeamService.domain.Team;
import com.niit.userTeamService.domain.User;
import com.niit.userTeamService.exception.TeamAlreadyExistsException;
import com.niit.userTeamService.exception.UserAlreadyExistsException;
import com.niit.userTeamService.exception.UserNotFoundException;

import java.util.List;

public interface UserTeamService
{
    public User getUserById(String emailId) throws UserNotFoundException;
    public User addUser(User user) throws UserAlreadyExistsException;
    public User addTeam(String emailId, Team team) throws UserNotFoundException, Exception;
    public List<Team> getATeamOfUser(String emailId) throws UserNotFoundException;
//    public User updateTeam(String emailId, Team team) throws UserNotFoundException;
    public User deleteTeam(String emailId, String teamId) throws UserNotFoundException, Exception;
}
