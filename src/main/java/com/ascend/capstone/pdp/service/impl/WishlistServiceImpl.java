package com.ascend.capstone.pdp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ascend.capstone.pdp.entity.Wishlist;
import com.ascend.capstone.pdp.repo.WishlistRepo;
import com.ascend.capstone.pdp.service.WishlistService;
@Service
public class WishlistServiceImpl implements WishlistService{

	@Autowired
	private WishlistRepo WishRepo;

	@Override

	public List<Wishlist> getlist(String user) {
		return WishRepo.findbyuser(user);
	}

	@Override
	public Wishlist addlist(Wishlist wishlist)
	{

		return WishRepo.save(wishlist);

	}

	@Override
	public Wishlist deleteProduct(Wishlist wishlistobj) {

		Wishlist wishlist = WishRepo.findByuId(wishlistobj.getId(), wishlistobj.getUser()).get();
		WishRepo.delete(wishlist);
		return wishlist;
	}


}
