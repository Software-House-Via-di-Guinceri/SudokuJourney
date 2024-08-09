package com.shvg.sudokujourney.controller;

import com.google.gson.JsonObject;
import com.shvg.sudokujourney.service.GoogleService;
import com.shvg.sudokujourney.service.database.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
@Slf4j
public class AuthController {

    private final GoogleService googleService;
    private final UserService userService;

    public AuthController(GoogleService googleService, UserService databaseService) {
        this.googleService = googleService;
        this.userService = databaseService;
    }

    @GetMapping("login/oauth2/code/google")
    public String login(OAuth2AuthenticationToken token) {
        return token.getName();
    }

}
