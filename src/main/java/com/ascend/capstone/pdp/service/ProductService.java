package com.ascend.capstone.pdp.service;

import com.ascend.capstone.pdp.entity.Product;

import java.util.List;

public interface ProductService {

    public List<Product> getProducts();

    public Product addProduct(Product product);

    public Product deleteProduct(int id );

    public Product updateProduct(int id , Product product);

    public Product getProductById(int id);
}