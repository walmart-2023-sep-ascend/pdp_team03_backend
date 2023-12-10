package com.ascend.capstone.pdp.entity;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;




@Document(collection ="Cart")

public class Addtocart {

	 @Id
	 	private ObjectId _id;
	    private Integer cartId;
	    private String email;
	    private ArrayList<CartProduct> products;
		public ObjectId get_id() {
			return _id;
		}
		public void set_id(ObjectId _id) {
			this._id = _id;
		}
		public Integer getCartId() {
			return cartId;
		}
		public void setCartId(Integer cartId) {
			this.cartId = cartId;
		}
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
		public ArrayList<CartProduct> getProducts() {
			return products;
		}
		public void setProducts(ArrayList<CartProduct> products) {
			this.products = products;
		}
		
	    
}
