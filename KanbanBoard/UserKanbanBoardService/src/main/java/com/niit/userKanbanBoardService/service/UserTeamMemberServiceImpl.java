package com.niit.userKanbanBoardService.service;


import com.niit.userKanbanBoardService.domain.Member;
import com.niit.userKanbanBoardService.domain.Team;
import com.niit.userKanbanBoardService.domain.User;
import com.niit.userKanbanBoardService.exception.NotFoundException;
import com.niit.userKanbanBoardService.exception.TeamNotFoundException;
import com.niit.userKanbanBoardService.repository.UserRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Data
@Service
public class UserTeamMemberServiceImpl implements UserTeamMemberService
{
    private UserRepository userRepository;
    @Autowired
    public UserTeamMemberServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Override
    public User addMemberForTeam(String emailId, String teamId, Member member) throws TeamNotFoundException, Exception {
        if(userRepository.findById(emailId).isEmpty())
        {
            throw new TeamNotFoundException();
        }
        User user = userRepository.findById(emailId).get();
        List<Team> teams = user.getTeamList();
        for (int i=0; i<teams.size(); i++)
        {
            if(teams.get(i).getTeamId().equals(teamId)){
                List<Member> members = teams.get(i).getMemberList();
                for(int j=0; j<members.size(); j++){
                    if(members.get(j).getMemberId().equals(member.getMemberId())){
                        throw new Exception("Already Exists");
                    }
                    else{
                        members.add(member);
                        teams.get(i).setMemberList(members);
                    }
                }
            }
            else if (teams.get(i).getMemberList()==null){
                teams.get(i).setMemberList(Arrays.asList(member));
            }
        }
        user.setTeamList(teams);
        User users = userRepository.save(user);
        return users;

    }
    @Override
    public List<Member> getAllMembersOfATeam(String emailId, String teamId) throws TeamNotFoundException {

        if (userRepository.findById(emailId).isPresent()) {
            User user = userRepository.findById(emailId).get();
            List<Team> teams = user.getTeamList();
            for (int i = 0; i < teams.size(); i++) {
                if (teams.get(i).getTeamId().equals(teamId)) {
                    return teams.get(i).getMemberList();
                }
            }
        }
            throw new TeamNotFoundException();

    }

    @Override
    public User updateMemberForTeam(String emailId,String teamId, Member member) throws TeamNotFoundException, NotFoundException, Exception {
        if(userRepository.findById(emailId).isEmpty())
        {
            throw new TeamNotFoundException();
        }
        User user = userRepository.findById(emailId).get();
        List<Team> teams = user.getTeamList();
        if(teams.size()!=0){
            for (int i=0; i<teams.size(); i++)
            {
                if(teams.get(i).getTeamId().equals(teamId)){
                    List<Member> members = teams.get(i).getMemberList();
                    for(int j=0; j<members.size(); j++){
                        if(members.get(j).getMemberId().equals(member.getMemberId())){
                            members.get(j).setEmailId(member.getEmailId());
                            members.get(j).setContact(member.getContact());
                            members.get(j).setName(member.getName());
//                            members.get(j).setBoardTasks(member.getBoardTasks());
                            teams.get(i).setMemberList(members);
                        }
                        else{
                            members.add(member);
                            teams.get(i).setMemberList(members);
                        }
                    }
                }
                else if (teams.get(i).getMemberList()==null){
                    teams.get(i).setMemberList(Arrays.asList(member));
                }
            }
        }else{
            throw new NotFoundException();
        }
        user.setTeamList(teams);
        User users = userRepository.save(user);
        return users;

    }
    @Override
    public User deleteMemberOfTeam(String emailId,String teamId, String memberId) throws TeamNotFoundException, NotFoundException, Exception {
        if(userRepository.findById(emailId).isEmpty())
        {
            throw new TeamNotFoundException();
        }
        User user = userRepository.findById(emailId).get();
        List<Team> teams = user.getTeamList();
        if(teams.size()!=0){
            for (int i=0; i<teams.size(); i++)
            {
                if(teams.get(i).getTeamId().equals(teamId)){
                    List<Member> members = teams.get(i).getMemberList();
                    for(int j=0; j<members.size(); j++){
                        if(members.get(j).getMemberId().equals(memberId)){
                            members.remove(j);
                            teams.get(i).setMemberList(members);
                        }
                    }
                }
            }
        }else{
            throw new NotFoundException();
        }
        user.setTeamList(teams);
        User users = userRepository.save(user);
        return users;


    }

}
