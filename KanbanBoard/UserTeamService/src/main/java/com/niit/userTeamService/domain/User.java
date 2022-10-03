package com.niit.userTeamService.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document
public class User
{
    @Id
    private String emailId;
    private String password;
    private String fullName;
    private String contact;
    private byte[] image;
    private List<Team> teamList;
    private List<Board> boardTasks;

}
