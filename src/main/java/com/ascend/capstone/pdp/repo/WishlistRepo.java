package com.ascend.capstone.pdp.repo;

import com.ascend.capstone.pdp.entity.Wishlist;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;

public interface WishlistRepo extends MongoRepository<Wishlist,Integer> {
    @Query("{user:?0}")
    List<Wishlist> findbyuser(String user);
    
    @Query("{id:?0,user:?1}")
    Optional<Wishlist> findByuId(Integer id,String user);

}
