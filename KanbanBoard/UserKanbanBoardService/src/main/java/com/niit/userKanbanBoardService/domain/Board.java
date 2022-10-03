package com.niit.userKanbanBoardService.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Board
{
    @Id
    private int taskId;
    private String toDo;
    private String priority;
    private String dateAssign;
    private String dueDate;
    private int taskStatus;

}
