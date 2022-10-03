package com.niit.userKanbanBoardService.controller;

import com.niit.userKanbanBoardService.domain.Team;
import com.niit.userKanbanBoardService.exception.TeamAlreadyExistsException;
import com.niit.userKanbanBoardService.exception.UserNotFoundException;
import com.niit.userKanbanBoardService.service.UserTeamService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Data
@RestController
@RequestMapping("/api/admin/")
public class UserTeamController {
    private UserTeamService userTeamService;
    private ResponseEntity<?> responseEntity;
    @Autowired
    public UserTeamController(UserTeamService userTeamService) {
        this.userTeamService = userTeamService;
    }

    @PostMapping("/team/addTeam/{emailId}")
    public ResponseEntity<?> saveTeamMember(@RequestBody Team team, @PathVariable String emailId) throws UserNotFoundException, TeamAlreadyExistsException {
        try
        {
            responseEntity = new ResponseEntity<>(userTeamService.addTeam(emailId,team), HttpStatus.CREATED);
        }
        catch (UserNotFoundException | Exception e)
        {
            throw new UserNotFoundException();
        }
        return responseEntity;
    }
    @DeleteMapping("/team/deleteTeam/{emailId}/{teamId}")
    public ResponseEntity<?> deleteTeamForUser(@PathVariable String emailId ,@PathVariable String teamId) throws UserNotFoundException {
        try
        {
            responseEntity = new ResponseEntity<>(userTeamService.deleteTeam(emailId,teamId),HttpStatus.OK);
        }
        catch (UserNotFoundException | Exception e)
        {
            throw new UserNotFoundException();
        }
        return responseEntity;
    }
    @GetMapping("/team/getAllTeam/{emailId}")
    public ResponseEntity<?> getAllTeams(@PathVariable String emailId) throws UserNotFoundException {
        try {
             responseEntity = new ResponseEntity<>(userTeamService.getATeamOfUser(emailId), HttpStatus.OK);
        } catch (UserNotFoundException e) {
            throw new UserNotFoundException();
        }
        return responseEntity;
    }
    @PutMapping("/team/updateTeam/{emailId}/{teamId}")
    public  ResponseEntity<?> updateTeam(@PathVariable String emailId, @RequestBody Team team) throws UserNotFoundException {
        try
        {
             responseEntity =  new ResponseEntity<>(userTeamService.updateTeam(emailId, team),HttpStatus.OK);
        }
        catch (UserNotFoundException e)
        {
            throw new UserNotFoundException();
        }
        return responseEntity;
    }

}
