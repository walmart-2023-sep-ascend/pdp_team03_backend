package com.ascend.capstone.pdp.repo;

import com.ascend.capstone.pdp.entity.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Optional;

public interface ProductRepo extends MongoRepository<Product,Integer> {
    @Query("{id:?0}")
    Optional<Product> findById(Integer id);
}
