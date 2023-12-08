package com.ascend.capstone.pdp.service.impl;

import com.ascend.capstone.pdp.entity.Product;
import com.ascend.capstone.pdp.repo.SearchRepository;
import com.mongodb.client.AggregateIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.convert.MongoConverter;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
public class SearchRepositoryImpl implements SearchRepository {

	@Autowired
	MongoClient client;

	@Autowired
	MongoConverter converter;

	@Override
	public List<Product> findByText(String text)
	{
		final List<Product> products = new ArrayList<>();

		MongoDatabase database = client.getDatabase("PDP");
		MongoCollection<Document> collection = database.getCollection("products");

		AggregateIterable<Document> result = collection.aggregate(Arrays.asList(new Document("$search",
				new Document("index", "default")
						.append("text",
								new Document("query", text)

										.append("path", Arrays.asList("productType", "productName", "shortDescription", "title", "searchTags")))),
				new Document("$limit", 3L)));
		result.forEach( doc -> products.add(converter.read(Product.class, doc)));

		return products;
	}
}
