package com.example.project.Controller;


import com.example.project.Domain.User;
import com.example.project.Payload.JWTLoginSucessReponse;
import com.example.project.Payload.LoginRequest;
import com.example.project.Security.JwtTokenProvider;
import com.example.project.Service.MapValidationErrorService;
import com.example.project.Service.UserService;
import com.example.project.Validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.example.project.Security.SecurityConstants.TOKEN_PREFIX;
@CrossOrigin(origins = "http://localhost:3000", allowedHeaders={"Accept"})
@RestController
@RequestMapping("/api/users/")
public class UserController {

    @Autowired
    private MapValidationErrorService mapValidationErrorService;

    @Autowired
    private UserService userService;

    @Autowired
    private UserValidator userValidator;

    @Autowired
    private JwtTokenProvider tokenProvider;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public ResponseEntity<JWTLoginSucessReponse> authenticateUser(@Valid @RequestBody LoginRequest loginRequest, BindingResult result){


        //ResponseEntity<> errorMap = mapValidationErrorService.MapValidationService(result);
      // if(errorMap != null) return errorMap;

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = TOKEN_PREFIX+tokenProvider.generateToken(authentication);
        String jwt1=tokenProvider.generateToken(authentication);

        JWTLoginSucessReponse jwtLoginSucessReponse=new JWTLoginSucessReponse();
        jwtLoginSucessReponse.setUserid(tokenProvider.getUserIdFromJWT(jwt1));

        return ResponseEntity.ok(new JWTLoginSucessReponse(jwtLoginSucessReponse.getUserid(),true, jwt));
       //return user.getUserId();
    }



    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody User user, BindingResult result){
        // Validate passwords match
        userValidator.validate(user,result);

        ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationService(result);
        if(errorMap != null)return errorMap;

        User newUser = userService.saveUser(user);

        return  new ResponseEntity<User>(newUser, HttpStatus.CREATED);
    }



}