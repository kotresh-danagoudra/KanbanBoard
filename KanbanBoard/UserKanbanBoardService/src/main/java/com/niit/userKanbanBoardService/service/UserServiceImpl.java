package com.niit.userKanbanBoardService.service;

import com.niit.userKanbanBoardService.domain.Team;
import com.niit.userKanbanBoardService.domain.User;
import com.niit.userKanbanBoardService.domain.Board;
import com.niit.userKanbanBoardService.exception.NotFoundException;
import com.niit.userKanbanBoardService.exception.TeamAlreadyExistsException;
import com.niit.userKanbanBoardService.exception.UserAlreadyExistsException;
import com.niit.userKanbanBoardService.exception.UserNotFoundException;
import com.niit.userKanbanBoardService.proxy.UserProxy;
import com.niit.userKanbanBoardService.repository.UserRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Data
@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    private UserProxy userProxy;
    @Autowired
    public UserServiceImpl(UserRepository userRepository, UserProxy userProxy) {
        this.userRepository = userRepository;
        this.userProxy=userProxy;
    }

    @Override
    public User addUser(User user) throws UserAlreadyExistsException {

        if(userRepository.findById(user.getEmailId()).isPresent())
        {
            System.out.println("got error");
            throw new UserAlreadyExistsException();
        }
        ResponseEntity<?> response=userProxy.saveUser(user);
        System.out.println("Data saved on user authentication is \n"+response.getBody());
        return userRepository.save(user);
    }

    @Override
    public User updateUser(String emailId, User user) throws UserNotFoundException {
        System.out.println(emailId);
       if(userRepository.findById(emailId).isEmpty())
        {
            System.out.println("User Not Found");
            throw new UserNotFoundException();
        }
        User users = userRepository.findById(emailId).get();
        users.setFullName(user.getFullName());
        users.setContact(user.getContact());
        return userRepository.save(users);
    }

    @Override
    public User getUserById(String emailId) throws UserNotFoundException {
        if(userRepository.findById(emailId).isEmpty())
        {
            throw new UserNotFoundException();
        }
        return userRepository.findById(emailId).get();
    }
    @Override
    public User updatePassword(String emailId, String password) throws UserNotFoundException {
        System.out.println(emailId);
        if(userRepository.findById(emailId).isEmpty())
        {
            System.out.println("User Not Found");
            throw new UserNotFoundException();
        }
        User user = userRepository.findById(emailId).get();
        user.setPassword(password);
        ResponseEntity<?> response = userProxy.updatePasswordOfAUser(password,emailId);
        System.out.println(user);
        return userRepository.save(user);
    }
    @Override
    public User updateTeam(String emailId, List<Team> team) throws UserNotFoundException {
        System.out.println(emailId);
        if(userRepository.findById(emailId).isEmpty())
        {
            System.out.println("User Not Found");
            throw new UserNotFoundException();
        }
        User user = userRepository.findById(emailId).get();
        user.setTeamList(team);
//        ResponseEntity<?> response = userProxy.updatePasswordOfAUser(password,emailId);
        System.out.println(user);
        return userRepository.save(user);
    }


    @Override
    public User updateImage(String emailId, byte[] url) {

        if(userRepository.findById(emailId).isPresent())
        {
            User user = userRepository.findById(emailId).get();
            user.setImage(url);
            return  userRepository.save(user);
        }
        return null;
    }

    @Override
    public User addTaskForUser(String emailId, Board board) throws UserNotFoundException, NotFoundException {
//        if (userRepository.findById(emailId).isPresent()) {
//            User user = userRepository.findById(emailId).get();
//            List<BoardTask> boardTasks = getAllTasksForParticularUser(emailId);
//            int lastTask=boardTasks.size()-1;
//            //manually increasing taskId
//            if(boardTasks.size()==0) {
//                boardTask.setTaskId(0);
//            }
//            else{
//                BoardTask boardTaskLast=boardTasks.get(lastTask);
//                boardTask.setTaskId(boardTaskLast.getTaskId()+1);
//            }
//            user.getBoardTasks().add(boardTask);
//            return userRepository.save(user);
//        } else {
//            throw new UserNotFoundException();
//        }
        if(userRepository.findById(emailId).isEmpty())
        {
            throw new UserNotFoundException();
        }
        User user = userRepository.findById(emailId).get();
        if(user.getBoardTasks() == null)
        {
            user.setBoardTasks(Arrays.asList(board));
        }
        else {
            List<Board> tasks = user.getBoardTasks();
            for(int i=0; i< tasks.size(); i++){
                if(tasks.get(i).getTaskId()==(board.getTaskId())){
                    throw new NotFoundException();
                }
                else {
                    tasks.add(board);
                    user.setBoardTasks(tasks);
                    return userRepository.save(user);
                }

            }
        }
        return userRepository.save(user);

    }

    @Override
    public List<Board> deleteTaskForUser(String emailId, Integer taskId) throws UserNotFoundException {
        List<Board> boardTasks = getAllTasksForParticularUser(emailId);
        User user = userRepository.findById(emailId).get();
        for (int i = 0; i < boardTasks.size(); i++) {
            if (boardTasks.get(i).getTaskId() == taskId) {
                Board status = boardTasks.get(i);
                user.getBoardTasks().remove(i);
                userRepository.save(user);
                return user.getBoardTasks();
            }
        }

        return null;
    }

    @Override
    public User increaseTaskStatusForUser(String emailId, Board board) throws UserNotFoundException {

        List<Board> boardTasks = getAllTasksForParticularUser(emailId);
        User user = userRepository.findById(emailId).get();
        for (int i = 0; i < boardTasks.size(); i++) {
            if (boardTasks.get(i).getTaskId() == board.getTaskId()) {
                Board status = boardTasks.get(i);
                user.getBoardTasks().get(i).setTaskStatus(status.getTaskStatus() + 1);
                userRepository.save(user);
                return user;
            }
        }
        return null;
    }

    @Override
    public User decreaseTaskStatusForUser(String emailId, Board boardTask) throws UserNotFoundException {
        List<Board> boardTasks = getAllTasksForParticularUser(emailId);
        User user = userRepository.findById(emailId).get();
        for (int i = 0; i < boardTasks.size(); i++) {
            if (boardTasks.get(i).getTaskId() == boardTask.getTaskId()) {
                Board status = boardTasks.get(i);
                user.getBoardTasks().get(i).setTaskStatus(status.getTaskStatus() - 1);
                userRepository.save(user);
                return user;
            }
        }

        return null;
    }

    @Override
    public List<Board> getAllTasksForParticularUser(String emailId) throws UserNotFoundException {

        if (userRepository.findById(emailId).isPresent()) {
            User user = userRepository.findById(emailId).get();
            return user.getBoardTasks();
        } else {
            throw new UserNotFoundException();
        }


    }

    @Override
    public User updateBoardTaskForUser(String emailId, Board boardTask) throws UserNotFoundException {

        List<Board> boardTasks = getAllTasksForParticularUser(emailId);
        User result = userRepository.findById(emailId).get();
//        user.getBoardTasks().get(i).setTaskStatus(status.getTaskStatus() + 1);
        for (int i = 0; i < boardTasks.size(); i++) {
            if (boardTasks.get(i).getTaskId() == boardTask.getTaskId()) {
                Board status = boardTasks.get(i);
                result.getBoardTasks().get(i).setToDo(boardTask.getToDo());
                result.getBoardTasks().get(i).setPriority(boardTask.getPriority());
                // result.getBoardTasks().get(i).setDateAssign(boardTask.getDateAssign());
                result.getBoardTasks().get(i).setDueDate(boardTask.getDueDate());
                //  result.getBoardTasks().get(i).setTaskStatus(boardTask.getTaskStatus());

                userRepository.save(result);
                return result;
            }
        }
        return null;
    }

    @Override
    public Board changeTaskPriorityForUser(String emailId, Board boardTask, String priority) throws UserNotFoundException {
        List<Board> boardTasks = getAllTasksForParticularUser(emailId);
        User user = userRepository.findById(emailId).get();
        for (int i = 0; i < boardTasks.size(); i++) {
            if (boardTasks.get(i).getTaskId() == boardTask.getTaskId()) {
                Board status = boardTasks.get(i);
                user.getBoardTasks().get(i).setPriority(priority);
                userRepository.save(user);
                return user.getBoardTasks().get(i);
            }
        }
        return null;
    }

    @Override
    public User changeTaskStatusForUser(String emailId, Board boardTask, int taskStatus) throws UserNotFoundException {

        List<Board> boardTasks = getAllTasksForParticularUser(emailId);
        User user = userRepository.findById(emailId).get();
        for (int i = 0; i < boardTasks.size(); i++) {
            if (boardTasks.get(i).getTaskId() == boardTask.getTaskId()) {
                Board status = boardTasks.get(i);
                user.getBoardTasks().get(i).setTaskStatus(taskStatus);
                userRepository.save(user);
                return user;
            }
        }

        return null;
    }





}
