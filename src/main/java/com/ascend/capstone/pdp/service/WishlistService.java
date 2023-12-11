package com.ascend.capstone.pdp.service;

import java.util.List;

import com.ascend.capstone.pdp.entity.Wishlist;

public interface WishlistService {

	public List<Wishlist> getlist(String user);

	public Wishlist addlist(Wishlist wishlist);

	public Wishlist deleteProduct(Wishlist wishlist );

}
