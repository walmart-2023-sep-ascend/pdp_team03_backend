package com.ascend.capstone.pdp.service.impl;

import com.ascend.capstone.pdp.entity.Addtocart;
import com.ascend.capstone.pdp.entity.CartProduct;
import com.ascend.capstone.pdp.repo.AddtocartRepo;
import com.ascend.capstone.pdp.service.AddtocartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class AddtocartServiceImpl implements AddtocartService{
	
	  @Autowired
	  private AddtocartRepo AddtocartRepo;
	  
	  @Override
	  
	  public Addtocart getlist(String email) {
	        //return WishRepo.findAll();
		 return AddtocartRepo.findbyuser(email);
	    }
	  

	@Override
	public Addtocart addlist(Addtocart addtocart) {
		Addtocart c = null;
		boolean isItemPresent =false;
			c = AddtocartRepo.findbyuser(addtocart.getEmail());

		if(c!=null && c.get_id()!=null) { // Update Cart 
			ArrayList<CartProduct> dbProductList = c.getProducts();
			List<CartProduct> reqProdList = addtocart.getProducts();

			for (CartProduct reqProduct : reqProdList)
			{
				for (CartProduct dbProduct : dbProductList)
				{

						if (dbProduct.getProductId().equals(reqProduct.getProductId()))
						{
							dbProduct.setQuantity(reqProduct.getQuantity());
							isItemPresent=true;
							break;
						}

				}
				if(!isItemPresent) {
					CartProduct p = new CartProduct();
					p.setProductId(reqProduct.getProductId());
					p.setQuantity(reqProduct.getQuantity());
					dbProductList.add(p);
				}
			}
			c.setProducts(dbProductList);

			return AddtocartRepo.save(c);
		}else { 
			if(AddtocartRepo.max()!=null) {
				addtocart.setCartId(AddtocartRepo.max()+1); // fetch the max count and insert into cart
			}
			else
				addtocart.setCartId(1);

			return AddtocartRepo.save(addtocart);
		}

	}

	  public List<Addtocart> getalllist() {
	        List<Addtocart> addtocart = AddtocartRepo.findAll();
	        return addtocart;
	    }

	@SuppressWarnings("unchecked")
	@Override
	public Addtocart deleteProduct(Addtocart addtocart) {
		Addtocart c = null;
		c = AddtocartRepo.findbyuser(addtocart.getEmail());
		
		@SuppressWarnings("rawtypes")
		Set<CartProduct> updatedProductList = new HashSet();
		ArrayList<CartProduct> dbProductList = c.getProducts();
		List<CartProduct> reqProdList = addtocart.getProducts();
		
		for (CartProduct reqProduct : reqProdList)
		{
			for (CartProduct dbProduct : dbProductList)
			{

					if (dbProduct.getProductId().equals(reqProduct.getProductId()))
					{
						updatedProductList.add(dbProduct);
					}

			}
		}
		dbProductList.removeAll(updatedProductList);
		c.setProducts(dbProductList);
		return AddtocartRepo.save(c);
		
	}
	
}
