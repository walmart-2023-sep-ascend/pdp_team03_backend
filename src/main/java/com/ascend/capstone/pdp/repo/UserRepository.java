package com.ascend.capstone.pdp.repo;

import com.ascend.capstone.pdp.entity.User;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {
  Boolean findByUsername(String username);

  Optional<User> findByEmail(String email);

  Boolean existsByUsername(String username);

  Boolean existsByEmail(String email);

  Boolean existsByPhone(String phone);


//  User findMax();
  @Aggregation(pipeline = {"{$group: { _id: '', total: {$max: $userId }}}"})
  public Integer max();

}
