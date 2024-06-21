package com.study4.project2024.controller;

import com.google.gson.Gson;
import com.study4.project2024.dto.SignupDTO;
import com.study4.project2024.payload.ResponseData;
import com.study4.project2024.service.imp.UsersServiceImp;
import com.study4.project2024.utils.JwtUtilsHelpers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/web")
public class WebController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtilsHelpers jwtUtilsHelpers;

    @Autowired
    private UsersServiceImp userServiceImp;

    @GetMapping("/home")
    public String homepage() {
        return "home";
    }

    @PostMapping("/signin")
    public ResponseEntity<?> signin(@RequestParam String username,
                                    @RequestParam String password) {

        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password);

        Authentication authentication = authenticationManager.authenticate(token);
        SecurityContext securityContext = SecurityContextHolder.getContext();
        securityContext.setAuthentication(authentication);

        Gson gson = new Gson();
        String data = gson.toJson(authentication);

        System.out.println("Data: "+data);
        ResponseData responseData = new ResponseData();
        responseData.setData(jwtUtilsHelpers.generateToken(data, username));
        responseData.setDesc("Dang nhap thanh cong");
        responseData.setStatusCode(200);
        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }

    @PostMapping("/signup")
    public ResponseEntity<?> addUser(@RequestBody SignupDTO signupDTO) {
        ResponseData responseData = new ResponseData();
        if (userServiceImp.addUser(signupDTO.getUsername(), signupDTO.getPassword(), "ROLE_USER")) {
            responseData.setData(true);
            responseData.setDesc("them thanh cong");
            responseData.setStatusCode(200);
            return new ResponseEntity<>(responseData, HttpStatus.OK);
        } else {
            responseData.setData(false);
            responseData.setDesc("them that bai");
            responseData.setStatusCode(200);
            return new ResponseEntity<>(responseData, HttpStatus.OK);
        }
    }

}