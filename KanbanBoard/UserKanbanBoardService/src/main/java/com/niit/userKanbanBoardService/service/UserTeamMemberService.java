package com.niit.userKanbanBoardService.service;



import com.niit.userKanbanBoardService.domain.Member;
import com.niit.userKanbanBoardService.domain.User;
import com.niit.userKanbanBoardService.exception.NotFoundException;
import com.niit.userKanbanBoardService.exception.TeamNotFoundException;

import java.util.List;

public interface UserTeamMemberService
{
    public User addMemberForTeam(String emailId, String teamId, Member member) throws TeamNotFoundException, Exception;
    public List<Member> getAllMembersOfATeam(String emailId, String teamId) throws TeamNotFoundException;
    public User updateMemberForTeam(String emailId,String teamId, Member member) throws TeamNotFoundException, NotFoundException, Exception;
    public User deleteMemberOfTeam(String emailId,String teamId, String memberId) throws TeamNotFoundException, NotFoundException, Exception;

}
