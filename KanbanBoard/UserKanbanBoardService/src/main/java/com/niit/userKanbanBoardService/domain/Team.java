package com.niit.userKanbanBoardService.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Team {
    @Id
    private String teamId;
    private List<Member> memberList;
}
