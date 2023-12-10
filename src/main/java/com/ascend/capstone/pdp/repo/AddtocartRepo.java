package com.ascend.capstone.pdp.repo;

import com.ascend.capstone.pdp.entity.Addtocart;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Optional;


public interface AddtocartRepo extends MongoRepository<Addtocart,Integer> {
    @Query("{email:?0}")
    Addtocart findbyuser(String email);
    
    @Query("{id:?0,email:?1}")
    Optional<Addtocart> findByuId(Integer id,String email);
    
	@Aggregation(pipeline = { "{$group: { _id: '', total: {$max: $cartId }}}" })
	public Integer max();

	@Aggregation(pipeline = { "{$group: { _id: '', total: {$min: $cartId }}}" })
	public Integer min();


}