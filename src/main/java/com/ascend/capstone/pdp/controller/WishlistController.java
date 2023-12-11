package com.ascend.capstone.pdp.controller;

import com.ascend.capstone.pdp.entity.Wishlist;
import com.ascend.capstone.pdp.security.jwt.JwtUtils;
import com.ascend.capstone.pdp.service.WishlistService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin(origins = {"http://localhost:9100/","http://ascend-pgp-team2.eastus.cloudapp.azure.com:9100"},allowCredentials = "true", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")

public class WishlistController {
	
	 @Autowired
	    private WishlistService wishlistservice;

	 @Autowired
	    private JwtUtils jwtUtils;
	    
	 @PostMapping("/addwishlist") 	
	    public ResponseEntity<Object> insert_wishlist(@RequestBody Wishlist wishlist, HttpServletRequest req){
	        try {
	        	
	        	String email = "";
	            String jwt = jwtUtils.parseJwt(req);
	            if (jwt != null && jwtUtils.validateJwtToken(jwt)) {
	              email = jwtUtils.getUserNameFromJwtToken(jwt); //username
	              System.out.println(email);
	              
	            }
	            wishlist.setUser(email);
	        	
	        	wishlistservice.addlist(wishlist);
	        	
	        	
	            return ResponseHandler.generateResponse("Added to Wishlist!", HttpStatus.OK, wishlistservice.addlist(wishlist));
	        } catch (Exception e) {
	            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, null);
	        }
	    }
	    
	    
	   @DeleteMapping("/deletewishlist")
	   public ResponseEntity<Object> delete_wishlist(@RequestBody Wishlist wishlist, HttpServletRequest req){
	       try {
	    	   
	    	   String email = "";
	            String jwt = jwtUtils.parseJwt(req);
	            if (jwt != null && jwtUtils.validateJwtToken(jwt)) {
	              email = jwtUtils.getUserNameFromJwtToken(jwt); //username
	              System.out.println(email);
	              
	            }
	            wishlist.setUser(email);
	    	   
	           return ResponseHandler.generateResponse("Deleted from Wishlist!", HttpStatus.OK, wishlistservice.deleteProduct(wishlist));
	       } catch (Exception e) {
	           return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, null);
	       }
	   }
	   
	    
	    @GetMapping("/wishlistall")
	   public List<Wishlist> getWishlist(HttpServletRequest req) {
	    	
	    	String email = "";
            String jwt = jwtUtils.parseJwt(req);
            if (jwt != null && jwtUtils.validateJwtToken(jwt)) {
              email = jwtUtils.getUserNameFromJwtToken(jwt); //username
              System.out.println(email);
              
            }
	      return  wishlistservice.getlist(email);
	    }
	    
	    @GetMapping("/validatewishlist")
	    public ResponseEntity<Object> validatewishlist(HttpServletRequest req){
	        try {
	        	
	        	String email = "";
	            String jwt = jwtUtils.parseJwt(req);
	            if (jwt != null && jwtUtils.validateJwtToken(jwt)) {
	              email = jwtUtils.getUserNameFromJwtToken(jwt); //username
	              System.out.println(email);
	              
	            }
	           	        	
	            return ResponseHandler.generateResponse("User Logged in!", HttpStatus.OK,true);
	        } catch (Exception e) {
	            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, null);
	        }
	    }
	 
	
}
