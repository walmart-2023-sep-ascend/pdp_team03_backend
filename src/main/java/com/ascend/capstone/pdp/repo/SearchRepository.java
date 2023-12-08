package com.ascend.capstone.pdp.repo;

import com.ascend.capstone.pdp.entity.Product;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SearchRepository {
	List<Product> findByText(String text);

}
