package com.niit.userTeamService.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT,reason = "Team already exits")
public class TeamAlreadyExistsException extends Exception
{

}

