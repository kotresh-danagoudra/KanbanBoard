package com.niit.userTeamService.service;

import com.niit.userTeamService.domain.Member;
import com.niit.userTeamService.domain.Team;
import com.niit.userTeamService.domain.User;
import com.niit.userTeamService.exception.NotFoundException;
import com.niit.userTeamService.exception.TeamNotFoundException;
import com.niit.userTeamService.exception.UserNotFoundException;

import java.util.List;

public interface UserTeamMemberService
{
    public User addMemberForTeam(String emailId, String teamId, Member member) throws TeamNotFoundException, Exception;
    public List<Member> getAllMembersOfATeam(String emailId, String teamId) throws TeamNotFoundException;
    public User updateMemberForTeam(String emailId,String teamId, Member member) throws TeamNotFoundException, NotFoundException, Exception;
    public User deleteMemberOfTeam(String emailId,String teamId, String memberId) throws TeamNotFoundException, NotFoundException, Exception;

}
