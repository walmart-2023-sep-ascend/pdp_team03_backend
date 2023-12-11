package com.ascend.capstone.pdp.controller;

import com.ascend.capstone.pdp.entity.Addtocart;
import com.ascend.capstone.pdp.security.jwt.JwtUtils;
import com.ascend.capstone.pdp.service.AddtocartService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = {"http://localhost:9100/","http://ascend-pgp-team2.eastus.cloudapp.azure.com:9100"},allowCredentials = "true", maxAge = 3600)
@Controller
@RestController
@RequestMapping("/api/auth")
public class AddtocartController {
	
    @Autowired
    private AddtocartService addtocartservice;
    
    @Autowired
    private JwtUtils jwtUtils;
    
    @PostMapping("/addcart")
 	
    public ResponseEntity<Object> insert_addtocart(@RequestBody Addtocart addtocart){
        try {
        	addtocartservice.addlist(addtocart);
            return ResponseHandler.generateResponse("Added to Wishlist!", HttpStatus.OK, addtocartservice.addlist(addtocart));
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, null);
        }
    }
    
    
   @DeleteMapping("/deletecart")

   public ResponseEntity<Object> delete_addtocart(@RequestBody Addtocart addtocart){
       try {
    	   addtocartservice.deleteProduct(addtocart);
           return ResponseHandler.generateResponse("Deleted from Addtocart!", HttpStatus.OK, addtocartservice.deleteProduct(addtocart));
       } catch (Exception e) {
           return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, null);
       }
   }
   
   @Value("${jwtCookieName}")
   private String jwtCookie;
   
   
    @GetMapping("/getcart")
   public Addtocart getAddtocart(HttpServletRequest req) {

    	String email = "";
        String jwt = jwtUtils.parseJwt(req);
        if (jwt != null && jwtUtils.validateJwtToken(jwt)) {
          email = jwtUtils.getUserNameFromJwtToken(jwt); //username
          System.out.println(email);
        }
        
      return  addtocartservice.getlist(email);
    
    
}

    @GetMapping("/all")
    public List<Addtocart> getCartProducts() {
    	return addtocartservice.getalllist();
    }
    
    
}
