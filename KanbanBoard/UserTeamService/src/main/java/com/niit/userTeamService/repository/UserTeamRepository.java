package com.niit.userTeamService.repository;

import com.niit.userTeamService.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserTeamRepository extends MongoRepository<User,String>
{
}
