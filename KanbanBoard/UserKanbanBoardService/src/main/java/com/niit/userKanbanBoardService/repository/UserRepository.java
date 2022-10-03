package com.niit.userKanbanBoardService.repository;

import com.niit.userKanbanBoardService.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User,String>
{
//    @Query("{'vehicleList.vehicleId':{$in:[?0]}}")
//    public User findUserByVehicleId(Integer vehicleId);

}
