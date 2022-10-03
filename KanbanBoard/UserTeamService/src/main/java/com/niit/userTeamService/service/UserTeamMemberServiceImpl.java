package com.niit.userTeamService.service;

import com.niit.userTeamService.domain.Member;
import com.niit.userTeamService.domain.Team;
import com.niit.userTeamService.domain.User;
import com.niit.userTeamService.exception.NotFoundException;
import com.niit.userTeamService.exception.TeamNotFoundException;
import com.niit.userTeamService.exception.UserNotFoundException;
import com.niit.userTeamService.rabbitmq.Producer;
import com.niit.userTeamService.rabbitmq.TeamDTO;
import com.niit.userTeamService.repository.UserTeamRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Data
@Service
public class UserTeamMemberServiceImpl implements UserTeamMemberService
{
    private UserTeamRepository userRepository;
    private Producer producer;
    @Autowired
//    public UserTeamMemberServiceImpl(UserTeamRepository userRepository) {
//        this.userRepository = userRepository;
//    }
    public UserTeamMemberServiceImpl(UserTeamRepository userRepository,Producer producer) {
        this.userRepository = userRepository;
        this.producer = producer;
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
        sendTeam(users);
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
                            members.get(j).setBoardTasks(member.getBoardTasks());
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
        sendTeam(users);
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
        sendTeam(users);
        return users;


    }

    public void sendTeam(User user) throws Exception{
        TeamDTO teamDTO = new TeamDTO();
        teamDTO.setEmailId(user.getEmailId());
        teamDTO.setTeamList(user.getTeamList());
        producer.sendMessageToRabbitMq(teamDTO);
    }
}
