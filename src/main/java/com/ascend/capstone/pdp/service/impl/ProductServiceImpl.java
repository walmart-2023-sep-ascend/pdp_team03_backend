package com.ascend.capstone.pdp.service.impl;

import com.ascend.capstone.pdp.entity.Product;
import com.ascend.capstone.pdp.repo.ProductRepo;
import com.ascend.capstone.pdp.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepo productRepo;
    @Override
    public List<Product> getProducts() {
        return productRepo.findAll();
    }

    @Override
    public Product addProduct(Product product) {
        return productRepo.save(product);
    }

    @Override
    public Product deleteProduct(int id) {
        Product product = productRepo.findById(id).get();
        productRepo.delete(product);
        return product;
    }

    @Override
    public Product updateProduct(int id, Product product) {
        Product productVar = productRepo.findById(id).get();
        productVar.setId(product.getId());
        productVar.setTitle(product.getTitle());
        productVar.setRetailPrice(product.getRetailPrice());
        productVar.setProductName(product.getProductName());
        productVar.setProductCategory(product.getProductCategory());
        productVar.setBrand(product.getBrand());
        productVar.setAvailableQuantity(product.getAvailableQuantity());
        productVar.setDiscount(product.getDiscount());
        productVar.setElegibileForPromotion(product.getElegibileForPromotion());
        productVar.setGender(product.getGender());
        productVar.setHeight(product.getHeight());
        productVar.setIconUrl(product.getIconUrl());
        productVar.setInventryStatus(product.getInventryStatus());
        productVar.setIsHazardous(product.getIsHazardous());
        productVar.setWidth(product.getWidth());
        productVar.setWeight(product.getWeight());
        productVar.setWarrantyDuration(product.getWarrantyDuration());
        productVar.setSpecification(product.getSpecification());
        productVar.setShortDescription(product.getShortDescription());
        productVar.setReturnDates(product.getReturnDates());
        productVar.setPurchasable(product.getPurchasable());
        productVar.setOrderLimit(product.getOrderLimit());
        productVar.setModel(product.getModel());
        productVar.setMinQuantity(product.getMinQuantity());
        productVar.setLongDescription(product.getLongDescription());
        productVar.setLength(product.getLength());
        productVar.setIsReturnable(product.getIsReturnable());
        productVar.setIconUrl(product.getIconUrl());
        productVar.setSearchTags(product.getSearchTags());
        productVar.setImageUrls(product.getImageUrls());
        productVar.setRatings(product.getRatings());

        productRepo.save(productVar);
        return productVar;


    }

    @Override
    public Product getProductById(int id) {
        Product productById = productRepo.findById(id).get();
        return productById;
    }

}
