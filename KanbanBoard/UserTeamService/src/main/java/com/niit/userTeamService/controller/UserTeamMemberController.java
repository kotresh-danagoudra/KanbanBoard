package com.niit.userTeamService.controller;

import com.niit.userTeamService.domain.Member;
import com.niit.userTeamService.domain.Team;
import com.niit.userTeamService.exception.NotFoundException;
import com.niit.userTeamService.exception.TeamAlreadyExistsException;
import com.niit.userTeamService.exception.TeamNotFoundException;
import com.niit.userTeamService.exception.UserNotFoundException;
import com.niit.userTeamService.service.UserTeamMemberService;
import com.niit.userTeamService.service.UserTeamService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Data
@RestController
@RequestMapping("/api/admin/team/member/")
public class UserTeamMemberController {
    private UserTeamMemberService userTeamMemberService;
    private ResponseEntity<?> responseEntity;
    @Autowired
    public UserTeamMemberController(UserTeamMemberService userTeamMemberService) {
        this.userTeamMemberService = userTeamMemberService;
    }
    @PostMapping("/addTeamMember/{emailId}/{teamId}")
    public ResponseEntity<?> saveTeamMember(@RequestBody Member member, @PathVariable String emailId, @PathVariable String teamId) throws UserNotFoundException, TeamAlreadyExistsException {
        try
        {
            responseEntity = new ResponseEntity<>(userTeamMemberService.addMemberForTeam(emailId,teamId,member), HttpStatus.CREATED);
        } catch (TeamNotFoundException | Exception e) {
            throw new TeamAlreadyExistsException();
        }
        return responseEntity;
    }
    @DeleteMapping("/deleteTeamMember/{emailId}/{teamId}/{memberId}")
    public ResponseEntity<?> deleteMemberFromTeam(@PathVariable String emailId ,@PathVariable String teamId,@PathVariable String memberId) throws UserNotFoundException {
        try
        {
            responseEntity = new ResponseEntity<>(userTeamMemberService.deleteMemberOfTeam(emailId,teamId,memberId),HttpStatus.OK);
        }
        catch (TeamNotFoundException | NotFoundException | Exception e)
        {
            throw new UserNotFoundException();
        }
        return responseEntity;
    }
    @GetMapping("/getAllTeamMember/{emailId}//{teamId}")
    public ResponseEntity<?> getAllTeamsMember(@PathVariable String emailId,@PathVariable String teamId) throws UserNotFoundException {
        try {
            responseEntity = new ResponseEntity<>(userTeamMemberService.getAllMembersOfATeam(emailId,teamId), HttpStatus.OK);
        } catch (TeamNotFoundException e) {
            throw new UserNotFoundException();
        }
        return responseEntity;
    }
    @PutMapping("/updateTeamMember/{emailId}/{taskId}")
    public  ResponseEntity<?> updateTeam(@RequestBody Member member, @PathVariable String emailId, @PathVariable String teamId) throws UserNotFoundException {
        try
        {
             responseEntity =  new ResponseEntity<>(userTeamMemberService.updateMemberForTeam(emailId,teamId,member),HttpStatus.OK);
        }
        catch (TeamNotFoundException | NotFoundException | Exception e)
        {
            throw new UserNotFoundException();
        }
        return responseEntity;
    }
}
