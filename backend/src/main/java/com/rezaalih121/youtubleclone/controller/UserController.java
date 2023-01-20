package com.rezaalih121.youtubleclone.controller;


import com.rezaalih121.youtubleclone.dto.CommentDto;
import com.rezaalih121.youtubleclone.service.UserRegistrationService;
import com.rezaalih121.youtubleclone.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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
    @ResponseStatus(HttpStatus.OK)
    public String register(Authentication authentication){
        Jwt jwt = (Jwt)authentication.getPrincipal();

        userRegistrationService.registerUser(jwt.getTokenValue());

        return "User Register successfully";
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
    @ResponseStatus(HttpStatus.OK)
    public Set<String> userHistory(@PathVariable String  userId){
        return userService.userHistory(userId);
    }

}
