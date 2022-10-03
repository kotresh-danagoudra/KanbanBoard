package com.niit.userTeamService.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Member
{
    @Id
    private String memberId;
    private String emailId;
    private String name;
    private String contact;
    private List<Board> boardTasks;
}
