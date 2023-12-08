package com.ascend.capstone.pdp.service;

import com.ascend.capstone.pdp.entity.Wishlist;

import java.util.List;

public interface WishlistService {

	public List<Wishlist> getlist(String user);

	public Wishlist addlist(Wishlist wishlist);

    public Wishlist deleteProduct(Wishlist wishlist );

   // public Wishlist updateProduct(int id , Wishlist wishlist);

   // public Wishlist getProductById(int id);	
}
