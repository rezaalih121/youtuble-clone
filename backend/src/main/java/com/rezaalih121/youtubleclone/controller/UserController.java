package com.rezaalih121.youtubleclone.controller;


import com.rezaalih121.youtubleclone.dto.CommentDto;
import com.rezaalih121.youtubleclone.dto.UserInfoDto;
import com.rezaalih121.youtubleclone.model.User;
import com.rezaalih121.youtubleclone.service.UserRegistrationService;
import com.rezaalih121.youtubleclone.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.Authentication;

import java.util.List;
import java.util.Set;


@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserRegistrationService userRegistrationService;
    private final UserService userService;
    @GetMapping("/register")
    public ResponseEntity<String> register(Authentication authentication){
        Jwt jwt = (Jwt)authentication.getPrincipal();
        String userId = userRegistrationService.registerUser(jwt.getTokenValue());
        System.out.println("register page"+userId);
        if(userId != null)
            return new ResponseEntity<>(userId , HttpStatus.CREATED);
        else
            return new ResponseEntity<>( "User Register failed" ,HttpStatus.BAD_REQUEST);

    }

    @PostMapping("subscribe/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public boolean subscribeUser(@PathVariable String userId){
        userService.subscribeUser(userId);
        return true;
    }

    @PostMapping("unSubscribe/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public boolean UnSubscribeUser(@PathVariable String userId){
        userService.unSubscribeUser(userId);
        return true;
    }

    @GetMapping("{userId}/history")
    public ResponseEntity<Set<String>> userHistory(@PathVariable String  userId){
        Set<String> userHistory = userService.userHistory(userId);
        if(userHistory != null)
            return new ResponseEntity<>(userHistory, HttpStatus.OK);
        else
            return new ResponseEntity<>( HttpStatus.NOT_FOUND);
    }

    @GetMapping("/userInfo")
    public ResponseEntity<UserInfoDto> userInfo(){
        UserInfoDto userInfoDto = new UserInfoDto();
        User currentUser = userService.getCurrentUser();
        userInfoDto.setId(currentUser.getId());
        userInfoDto.setSub(currentUser.getSub());
        userInfoDto.setEmail(currentUser.getEmailAddress());
        userInfoDto.setName(currentUser.getFullName());
        userInfoDto.setFamilyName(currentUser.getLastName());
        userInfoDto.setGivenName(currentUser.getFirstName());
        userInfoDto.setPicture(currentUser.getPictur());
        userInfoDto.setSubscribersCount(String.valueOf(currentUser.getSubscribers().size()));

        if(userInfoDto != null)
            return new ResponseEntity<>(userInfoDto , HttpStatus.OK);
        else
            return new ResponseEntity<>( HttpStatus.NOT_FOUND);



    }
    @PostMapping("publisherInfo/{userId}")
    public ResponseEntity<UserInfoDto> publisherInfo(@PathVariable String userId ){
        UserInfoDto userInfoDto = new UserInfoDto();
        User currentUser = userService.getUserById(userId);
        userInfoDto.setId(currentUser.getId());
        userInfoDto.setSub(currentUser.getSub());
        userInfoDto.setEmail(currentUser.getEmailAddress());
        userInfoDto.setName(currentUser.getFullName());
        userInfoDto.setFamilyName(currentUser.getLastName());
        userInfoDto.setGivenName(currentUser.getFirstName());
        userInfoDto.setPicture(currentUser.getPictur());
        userInfoDto.setSubscribersCount(String.valueOf(currentUser.getSubscribers().size()));

        if(userInfoDto != null)
            return new ResponseEntity<>(userInfoDto , HttpStatus.OK);
        else
            return new ResponseEntity<>( HttpStatus.NOT_FOUND);



    }

}
