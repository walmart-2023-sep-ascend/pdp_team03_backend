package com.ascend.capstone.pdp.service;

import com.ascend.capstone.pdp.entity.Addtocart;

import java.util.List;


public interface AddtocartService {

	public Addtocart getlist(String email);

	public Addtocart addlist(Addtocart addtocart);

    public Addtocart deleteProduct(Addtocart addtocart );
    
	  public List<Addtocart> getalllist();

}
