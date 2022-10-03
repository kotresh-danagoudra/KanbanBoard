package com.niit.userKanbanBoardService.service;

import com.niit.userKanbanBoardService.domain.Team;
import com.niit.userKanbanBoardService.domain.User;
import com.niit.userKanbanBoardService.domain.Board;
import com.niit.userKanbanBoardService.exception.NotFoundException;
import com.niit.userKanbanBoardService.exception.UserAlreadyExistsException;
import com.niit.userKanbanBoardService.exception.UserNotFoundException;


import java.util.List;

public interface UserService {


    public User addUser(User user) throws UserAlreadyExistsException;

    public User updateUser(String emailId, User user) throws UserNotFoundException;
    public User updatePassword(String emailId,String password) throws UserNotFoundException;

    public User getUserById(String emailId) throws UserNotFoundException;

    public User updateImage(String emailId, byte[] url);

    public User addTaskForUser(String emailId, Board board) throws UserNotFoundException, NotFoundException;

    public List<Board> deleteTaskForUser(String emailId, Integer taskId) throws UserNotFoundException;

    public User increaseTaskStatusForUser(String emailId, Board board) throws UserNotFoundException;

    public User decreaseTaskStatusForUser(String emailId, Board board) throws UserNotFoundException;

    public List<Board> getAllTasksForParticularUser(String emailId) throws UserNotFoundException;

    public User updateBoardTaskForUser(String emailId, Board board) throws UserNotFoundException;

    public Board changeTaskPriorityForUser(String emailId, Board board, String priority) throws UserNotFoundException;

    public User changeTaskStatusForUser(String emailId, Board board, int taskStatus) throws UserNotFoundException;
    public User updateTeam(String emailId, List<Team> team) throws UserNotFoundException;

}