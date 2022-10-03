package com.niit.userTeamService.rabbitmq;


import com.niit.userTeamService.domain.Team;
import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TeamDTO {
    private String emailId;
    private List<Team> teamList;
}
