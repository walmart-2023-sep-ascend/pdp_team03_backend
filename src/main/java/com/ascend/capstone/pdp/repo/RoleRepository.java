package com.ascend.capstone.pdp.repo;

import com.ascend.capstone.pdp.entity.ERole;
import com.ascend.capstone.pdp.entity.Role;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface RoleRepository extends MongoRepository<Role, String> {
  Optional<Role> findByName(ERole name);
}
