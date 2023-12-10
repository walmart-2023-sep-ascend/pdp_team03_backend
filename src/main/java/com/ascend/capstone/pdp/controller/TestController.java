package com.ascend.capstone.pdp.controller;

import com.ascend.capstone.pdp.payload.response.MessageResponse;
import com.ascend.capstone.pdp.payload.response.UserInfoResponse;
import com.ascend.capstone.pdp.service.impl.UserDetailsImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/test")
public class TestController {

  private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

  @GetMapping("/all")
  public String allAccess() {
    return "Public Content.";
  }

  @GetMapping("/user")
  @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
  public String userAccess() {
    return "User Content.";
  }


  //GUEST role user can access this API
  @GetMapping("/guest")
  @PreAuthorize("hasRole('GUEST')")
  public String moderatorAccess() {
    logger.info("Guest: In Guest Page !");
    return "Guest Page.";
  }

  //ADMIN role user can access this API
  @GetMapping("/verify_admin")
  @PreAuthorize("hasRole('ADMIN')")
  public String adminAccess() {

    logger.info("Admin: In Admin Page !");
    return "Admin Page.";
  }

  //API to identify user is logged-in or not
  @GetMapping("/role")
  public ResponseEntity<MessageResponse> role() {
    Authentication authentication1 = SecurityContextHolder.getContext().getAuthentication();

    if (authentication1.getName() != "anonymousUser" && authentication1 != null && authentication1.isAuthenticated()) {
      logger.info("Role: In Role API !");
      return ResponseEntity.ok().body(new MessageResponse(String.valueOf(authentication1.getAuthorities())));
    } else {
      return ResponseEntity.status(401).body(new MessageResponse("Invalid login!"));
    }

  }

  @CrossOrigin(origins = "*", allowedHeaders = "*")
  @GetMapping("/profile")
  public ResponseEntity<?> profile(Authentication authentication) {
    UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
    return ResponseEntity.ok()
            .body(new UserInfoResponse(
                    userDetails.getUserId(),
                    userDetails.getUsername(),
                    userDetails.getEmail()/*,
                    userDetails.getName()*/));
  }
}
