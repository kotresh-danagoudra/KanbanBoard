package com.niit.userTeamService.rabbitmq;

import com.niit.userTeamService.domain.Board;
import com.niit.userTeamService.domain.Team;
import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserDTO {
    private String emailId;
    private String password;
    private String fullName;
    private String contact;
    private byte[] image;
    private List<Team> teamList;
    private List<Board> boardTasks;
}
