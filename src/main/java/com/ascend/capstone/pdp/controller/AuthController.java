package com.ascend.capstone.pdp.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ascend.capstone.pdp.entity.ERole;
import com.ascend.capstone.pdp.entity.Role;
import com.ascend.capstone.pdp.entity.User;
import com.ascend.capstone.pdp.payload.request.LoginRequest;
import com.ascend.capstone.pdp.payload.request.SignupRequest;
import com.ascend.capstone.pdp.payload.response.MessageResponse;
import com.ascend.capstone.pdp.payload.response.UserInfoResponse;
import com.ascend.capstone.pdp.repo.RoleRepository;
//import com.ascend.capstone.pdp.repo.RoleRepository;
import com.ascend.capstone.pdp.repo.UserRepository;
import com.ascend.capstone.pdp.security.jwt.JwtUtils;
import com.ascend.capstone.pdp.service.impl.UserDetailsImpl;

import jakarta.validation.Valid;


@CrossOrigin(origins = "http://localhost:9100/",allowCredentials = "true", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {

  private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

  @Autowired
  AuthenticationManager authenticationManager;

  @Autowired
  UserRepository userRepository;

  @Autowired
  RoleRepository roleRepository;

  @Autowired
  PasswordEncoder encoder;

  @Autowired
  JwtUtils jwtUtils;


  @PostMapping("/signin")
  public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

    Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword())); //getUsername

    UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

    ResponseCookie jwtCookie = jwtUtils.generateJwtCookie(userDetails);

    @SuppressWarnings("unused")
    List<String> roles = userDetails.getAuthorities().stream()
            .map(item -> item.getAuthority())
            .collect(Collectors.toList());

    logger.info("Login successful !!");

    return ResponseEntity.ok()
            //.header(HttpHeaders.SET_COOKIE, jwtCookie.toString())
            .body(new UserInfoResponse(userDetails.getUserId(),
                    userDetails.getUsername(),
                    userDetails.getEmail(),
                    jwtCookie.toString().split(";")[0].split("=")[1])
            );

  }

  @PostMapping("/signup")
  public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
    if (userRepository.existsByUsername(signUpRequest.getUsername())) {
      logger.error("Signup: Username already exists !");
      return ResponseEntity
              .badRequest()
              .body(new MessageResponse("Error: Username is already taken!"));
    }

    if (userRepository.existsByEmail(signUpRequest.getEmail())) {
      logger.error("Signup: Email already exists !");
      return ResponseEntity
              .badRequest()
              .body(new MessageResponse("Error: Email is already in use!"));
    }

    if (userRepository.existsByPhone(signUpRequest.getPhone())){
      logger.error("Signup: Phone number already exists !");
      return ResponseEntity
              .badRequest()
              .body(new MessageResponse("Error: Phone number already in use!"));
    }



    User user = new User(signUpRequest.getUsername(),
            signUpRequest.getEmail(),
            encoder.encode(signUpRequest.getPassword()),
            signUpRequest.getNamee(),
            signUpRequest.getGender(),
            signUpRequest.getPhone(),
            signUpRequest.getAddress());



    Set<String> strRoles = signUpRequest.getRoles();
    Set<Role> roles = new HashSet<>();
    strRoles.forEach(role -> {
      switch (role) {
        case "admin":
          Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                  .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
          roles.add(adminRole);

          break;
        case "guest":
          Role modRole = roleRepository.findByName(ERole.ROLE_GUEST)
                  .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
          roles.add(modRole);

          break;
        default:
          Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                  .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
          roles.add(userRole);
      }
    });




    if(userRepository.max()!=null) {
      user.setUserId(userRepository.max()+1); // fetch the max count and insert into cart
      logger.info("UserID: New UserID created !");
    }
    else {
      logger.info("UserID: First UserID created !");
      user.setUserId(1);
    }


    user.setRoles(roles);
    user.setValidated("NO");
    logger.info("Validated: User Email not validated !");
    userRepository.save(user);
    logger.info("Signup: New User created !");

    return ResponseEntity.ok(new MessageResponse("User registered successfully !!!"));
  }

}
